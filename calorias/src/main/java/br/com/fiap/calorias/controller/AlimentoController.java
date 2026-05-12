package br.com.fiap.calorias.controller;

import br.com.fiap.calorias.dto.AlimentoCadastroDTO;
import br.com.fiap.calorias.dto.AlimentoExibicaoDTO;
import br.com.fiap.calorias.service.AlimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class AlimentoController {

    @Autowired
    private AlimentoService alimentoService;

    @PostMapping("/alimentos")
    @ResponseStatus(HttpStatus.CREATED)
    public AlimentoExibicaoDTO salvar(
            @Valid
            @RequestBody AlimentoCadastroDTO alimentoCadastroDTO){
        return alimentoService.salvarAlimento(alimentoCadastroDTO);
    }

    @GetMapping("/alimentos")
    @ResponseStatus(HttpStatus.OK)
    public Page<AlimentoExibicaoDTO> listarTodos(
           @PageableDefault(size = 2, page = 0) Pageable paginacao
    ){
        return alimentoService.buscarTodos(paginacao);
    }

    @GetMapping("/alimentos/{alimentoId}")
    public ResponseEntity<AlimentoExibicaoDTO> buscarPorId(
            @PathVariable Long alimentoId){
        try {
            return ResponseEntity.ok(alimentoService.buscarPorId(alimentoId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/alimentos/{alimentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long alimentoId){
        alimentoService.excluir(alimentoId);
    }

    @PutMapping("/alimentos")
    public ResponseEntity<AlimentoExibicaoDTO> atualizar(
            @Valid
            @RequestBody AlimentoCadastroDTO alimentoCadastroDTO){
        try {
            AlimentoExibicaoDTO alimentoExibicaoDTO =
                    alimentoService.atualizar(alimentoCadastroDTO);
            return ResponseEntity.ok(alimentoExibicaoDTO);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/alimentos", params = "nome")
    public ResponseEntity<AlimentoExibicaoDTO> buscarPorNome(@RequestParam String nome){

        try {
            return ResponseEntity
                    .ok(alimentoService.buscarPorNome(nome));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @RequestMapping(value = "/alimentos", params = {"caloriasMinima", "caloriasMaxima"})
    @ResponseStatus(HttpStatus.OK)
    public List<AlimentoExibicaoDTO> listarAlimentosPorFaixaDeCalorias(
            @RequestParam Double caloriasMinima,
            @RequestParam Double caloriasMaxima
    ) {
        return alimentoService.listarAlimentosPorFaixaDeCalorias(caloriasMinima, caloriasMaxima);
    }

    public List<AlimentoExibicaoDTO> listarTotalCaloriasMenorQue(
            @RequestParam Double caloriasMenorQue
    ){
        return alimentoService.listarTotalCaloriasMenorQue(caloriasMenorQue);
    }


}
