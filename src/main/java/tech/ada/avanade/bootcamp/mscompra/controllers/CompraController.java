package tech.ada.avanade.bootcamp.mscompra.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.ada.avanade.bootcamp.mscompra.dto.request.CompraRequestDTO;
import tech.ada.avanade.bootcamp.mscompra.dto.response.CompraResponseDTO;
import tech.ada.avanade.bootcamp.mscompra.services.CompraService;

@RestController
@RequestMapping("/compra")
public class CompraController {

    private CompraService compraService;
    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompraResponseDTO realizarCompra(@RequestBody @Valid CompraRequestDTO dto){
        return this.compraService.executeCadastrar(dto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public String cancelarCompra(@RequestBody @Valid CompraRequestDTO dto) {
        return this.compraService.executeCancelar(dto);
    }
}
