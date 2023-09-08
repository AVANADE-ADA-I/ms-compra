package tech.ada.avanade.bootcamp.mscompra.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tech.ada.avanade.bootcamp.mscompra.dto.request.CompraRequestDTO;
import tech.ada.avanade.bootcamp.mscompra.dto.response.CompraResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "compras")
@Getter
@NoArgsConstructor
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataCompra;
    private String loja;
    private BigDecimal valor;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @Enumerated(EnumType.STRING)
    private StatusCompra statusCompra;

    public Compra(CompraRequestDTO dto, Usuario usuario) {
        this.dataCompra = dto.dataCompra();
        this.loja = dto.loja();
        this.valor = dto.valor();
        this.statusCompra = StatusCompra.PENDENTE;
        this.usuario = usuario;
    }

    public CompraResponseDTO dto() {
        return new CompraResponseDTO(this.usuario.getNumeroCartao(), this.dataCompra, this.loja, this.valor, this.statusCompra);
    }
    public void finalizarCompra() {
        this.statusCompra = StatusCompra.FINALIZADA;
    }
    public void cancelar() {
        this.statusCompra = StatusCompra.CANCELADA;
    }
}
