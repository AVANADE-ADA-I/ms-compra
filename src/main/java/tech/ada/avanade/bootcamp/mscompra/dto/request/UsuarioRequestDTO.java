package tech.ada.avanade.bootcamp.mscompra.dto.request;

import java.time.LocalDate;

public record UsuarioRequestDTO(String numeroCartao, LocalDate vencimentoCartao) {
}
