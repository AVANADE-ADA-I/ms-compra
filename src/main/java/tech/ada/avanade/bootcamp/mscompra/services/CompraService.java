package tech.ada.avanade.bootcamp.mscompra.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tech.ada.avanade.bootcamp.mscompra.dto.request.CompraRequestDTO;
import tech.ada.avanade.bootcamp.mscompra.dto.response.CompraResponseDTO;
import tech.ada.avanade.bootcamp.mscompra.entities.Compra;
import tech.ada.avanade.bootcamp.mscompra.exceptions.AppException;
import tech.ada.avanade.bootcamp.mscompra.repositories.CompraRepository;

@Service
public class CompraService {

    private CompraRepository compraRepository;
    private UsuarioService usuarioService;
    public CompraService(CompraRepository compraRepository, UsuarioService usuarioService) {
        this.compraRepository = compraRepository;
        this.usuarioService = usuarioService;
    }

    @Transactional
    public CompraResponseDTO executeCadastrar(CompraRequestDTO dto) {
        var usuario = usuarioService.buscarUsuario(dto.numeroCartao());
        System.out.println(dto);
        System.out.println(usuario);
        var compra = new Compra(dto, usuario);
        System.out.println(compra);
        if (!usuario.getAtivo()) {
            throw new AppException("Cartão está desativado.");
        }
        if (usuario.getVencimentoCartao().isBefore(compra.getDataCompra().toLocalDate())) {
            throw new AppException("Cartão está vencido.");
        }
        compra.finalizarCompra();
        var compraRegistrada = compraRepository.save(compra);
        return compraRegistrada.dto();
    }

    @Transactional
    public String executeCancelar(CompraRequestDTO dto) {
        var usuario = usuarioService.buscarUsuario(dto.numeroCartao());
        var compraOptional = this.compraRepository.findByUsuarioIdAndLojaAndDataCompraAndValor(usuario.getId(), dto.loja(), dto.dataCompra(), dto.valor());
        if (compraOptional.isEmpty()) {
            throw new EntityNotFoundException("Compra não encontrada");
        }
        compraOptional.get().cancelar();
        this.compraRepository.save(compraOptional.get());
        return "Cancelada";
    }
}
