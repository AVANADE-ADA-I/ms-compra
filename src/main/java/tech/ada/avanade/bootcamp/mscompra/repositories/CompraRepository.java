package tech.ada.avanade.bootcamp.mscompra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.avanade.bootcamp.mscompra.entities.Compra;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public interface CompraRepository extends JpaRepository<Compra, Long> {
    Optional<Compra> findByUsuarioIdAndLojaAndDataCompraAndValor(Long usuarioId, String loja, LocalDateTime dataCompra, BigDecimal valor);
}
