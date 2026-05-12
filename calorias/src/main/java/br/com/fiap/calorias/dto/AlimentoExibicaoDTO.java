package br.com.fiap.calorias.dto;

import br.com.fiap.calorias.model.Alimento;

public record AlimentoExibicaoDTO(
        Long alimentoId,
        String nome,
        String porcao,
        Double quantidadeProteina,
        Double quantidadeCarboidrato,
        Double quantidadeGordura,
        Double quantidadeCalorias
) {
    public AlimentoExibicaoDTO(Alimento alimento){
        this(
                alimento.getAlimentoId(),
                alimento.getNome(),
                alimento.getPorcao(),
                alimento.getQuantidadeProteina(),
                alimento.getQuantidadeCarboidrato(),
                alimento.getQuantidadeGordura(),
                alimento.getTotalCalorias()
        );
    }
}
