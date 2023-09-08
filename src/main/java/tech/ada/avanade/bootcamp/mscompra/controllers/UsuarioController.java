package tech.ada.avanade.bootcamp.mscompra.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.ada.avanade.bootcamp.mscompra.dto.request.UsuarioRequestDTO;
import tech.ada.avanade.bootcamp.mscompra.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String cadastrarUsuario(@RequestBody @Valid UsuarioRequestDTO dto) {
        return this.usuarioService.executeCadastrar(dto);
    }

    @DeleteMapping("/{numeroCartao}")
    @ResponseStatus(HttpStatus.OK)
    public String desativarUsuario(@PathVariable String numeroCartao) {
        return this.usuarioService.executeDesativar(numeroCartao);
    }
}