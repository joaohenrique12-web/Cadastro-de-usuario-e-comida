package br.com.fiap.calorias.dto;

import jakarta.validation.constraints.NotBlank;

public record AlimentoCadastroDTO(
        Long alimentoId,


        @NotBlank(message = "O nome do alimento é obrigatório!")
        String nome,

        @NotBlank(message = "A quantidade de porção é obrigatória!")
        String porcao,

        @NotBlank(message = "A quantidade de proteína é obrigatória!")
        Double quantidadeProteina,

        @NotBlank(message = "A quantidade de carboidrato é obrigatória!")
        Double quantidadeCarboidrato,

        Double quantidadeGordura
) {
}
