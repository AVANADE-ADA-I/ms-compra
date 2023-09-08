package tech.ada.avanade.bootcamp.mscompra.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tech.ada.avanade.bootcamp.mscompra.dto.request.UsuarioRequestDTO;
import tech.ada.avanade.bootcamp.mscompra.entities.Usuario;
import tech.ada.avanade.bootcamp.mscompra.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public String executeCadastrar(UsuarioRequestDTO dto) {
        System.out.println(dto);
        var usuario = new Usuario(dto);
        this.usuarioRepository.save(usuario);
        return "Cadastrado";
    }

    @Transactional
    public String executeDesativar(String numeroCartao) {
        var usuarioOptional = this.usuarioRepository.findByNumeroCartao(numeroCartao);
        if (usuarioOptional.isEmpty()) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        usuarioOptional.get().desativar();
        this.usuarioRepository.save(usuarioOptional.get());
        return "Desativado";
    }

    protected Usuario buscarUsuario(String numeroCartao) {
        var usuarioOptional = this.usuarioRepository.findByNumeroCartao(numeroCartao);
        if (usuarioOptional.isEmpty()) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        return usuarioOptional.get();
    }
}
