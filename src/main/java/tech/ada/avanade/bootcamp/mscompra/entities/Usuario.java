package tech.ada.avanade.bootcamp.mscompra.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tech.ada.avanade.bootcamp.mscompra.dto.request.UsuarioRequestDTO;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroCartao;
    private LocalDate vencimentoCartao;
    private Boolean ativo;

    public Usuario(UsuarioRequestDTO dto) {
        this.numeroCartao = dto.numeroCartao();
        this.vencimentoCartao = dto.vencimentoCartao();
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }
}
