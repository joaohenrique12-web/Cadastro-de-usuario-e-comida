package br.com.fiap.calorias.controller;

import br.com.fiap.calorias.dto.UsuarioAtualizacaoDTO;
import br.com.fiap.calorias.dto.UsuarioCadastroDTO;
import br.com.fiap.calorias.dto.UsuarioExibicaoDTO;
import br.com.fiap.calorias.model.Usuario;
import br.com.fiap.calorias.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDTO salvar(@Valid @RequestBody UsuarioCadastroDTO usuarioCadastroDTO) {
        return usuarioService.salvarUsuario(usuarioCadastroDTO);
    }

    @GetMapping("/usuarios")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioExibicaoDTO> listarTodos() {
        return usuarioService.buscarTodosUsuarios();
    }

    @GetMapping("/usuarios/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UsuarioExibicaoDTO> buscarPorId(@PathVariable Long id){
//        try {
//            return ResponseEntity.ok(usuarioService.buscarPorId(id));
//
//        } catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
        return ResponseEntity.ok(usuarioService.buscarPorId(id));

    }

    @PutMapping("/usuarios")
    public ResponseEntity<UsuarioExibicaoDTO> atualizar(
            @Valid
            @PathVariable Long id,
            @RequestBody UsuarioAtualizacaoDTO usuarioDTO){

        return ResponseEntity.ok(usuarioService.atualizar(id, usuarioDTO));
    }

    @DeleteMapping("/usuarios/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long usuarioId){
         usuarioService.excluir(usuarioId);
    }

//    @RequestMapping(value = "/usuarios", params = "email")
//    @ResponseStatus(HttpStatus.OK)
//    public UsuarioExibicaoDTO buscarPorEmail(@RequestParam String email){
//        return usuarioService.buscarPorEmail(email);
//    }
}
