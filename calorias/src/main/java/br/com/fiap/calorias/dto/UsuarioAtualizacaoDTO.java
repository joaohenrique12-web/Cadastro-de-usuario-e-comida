package br.com.fiap.calorias.dto;

public record UsuarioAtualizacaoDTO(
        Long usuarioId,
        String nome,
        String email,
        String senha
) {
}
