package tech.ada.avanade.bootcamp.mscompra.dto.response;

import tech.ada.avanade.bootcamp.mscompra.entities.StatusCompra;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CompraResponseDTO(String numeroCartao, LocalDateTime dataCompra, String loja, BigDecimal valor, StatusCompra statusCompra) {
}
