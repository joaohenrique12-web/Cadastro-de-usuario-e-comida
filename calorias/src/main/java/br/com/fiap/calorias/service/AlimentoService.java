package br.com.fiap.calorias.service;

import br.com.fiap.calorias.dto.AlimentoCadastroDTO;
import br.com.fiap.calorias.dto.AlimentoExibicaoDTO;
import br.com.fiap.calorias.exception.AlimentoNaoEncontradoException;
import br.com.fiap.calorias.model.Alimento;
import br.com.fiap.calorias.repository.AlimentoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlimentoService {

    @Autowired
    private AlimentoRepository alimentoRepository;
    public AlimentoExibicaoDTO salvarAlimento(AlimentoCadastroDTO alimentoCadastroDTO){
        Alimento alimento = new Alimento();
        BeanUtils.copyProperties(alimentoCadastroDTO, alimento);

        alimento.setTotalCalorias(
                calcularCalorias(
                        alimento.getQuantidadeProteina(),
                        alimento.getQuantidadeCarboidrato(),
                        alimento.getQuantidadeGordura()
                )
        );

        Alimento alimentoSalvo = alimentoRepository.save(alimento);
        return new AlimentoExibicaoDTO(alimentoSalvo);
    }

    public AlimentoExibicaoDTO buscarPorId(Long id){
        Optional<Alimento> alimentoOptional = alimentoRepository.findById(id);

        if(alimentoOptional.isPresent()){
            return new AlimentoExibicaoDTO(alimentoOptional.get());
        } else {
            throw new AlimentoNaoEncontradoException("Alimento não existente");
        }
    }

    public Page<AlimentoExibicaoDTO> buscarTodos(Pageable paginacao){
        return alimentoRepository
                .findAll(paginacao)
                .map(AlimentoExibicaoDTO::new);
    }

    public void excluir(Long id){
        Optional<Alimento> alimentoOptional = alimentoRepository.findById(id);

        if(alimentoOptional.isPresent()){
            alimentoRepository.delete(alimentoOptional.get());
        } else {
            throw new AlimentoNaoEncontradoException("Alimento não encontrado");
        }

    }

    public AlimentoExibicaoDTO atualizar(AlimentoCadastroDTO alimentoCadastroDTO){
        Optional<Alimento> alimentoOptional = alimentoRepository.findById(alimentoCadastroDTO.alimentoId());

        if(alimentoOptional.isPresent()){
            Alimento alimento = new Alimento();
            BeanUtils.copyProperties(alimentoCadastroDTO, alimento);

            alimento.setTotalCalorias(
                    calcularCalorias(
                            alimento.getQuantidadeProteina(),
                            alimento.getQuantidadeCarboidrato(),
                            alimento.getQuantidadeGordura()
                    )
            );
            return new AlimentoExibicaoDTO(alimentoRepository.save(alimento));
        } else {
            throw new AlimentoNaoEncontradoException("Alimento não encontrado");
        }
    }

    public AlimentoExibicaoDTO buscarPorNome(String nome){
        Optional<Alimento> alimentoOptional = alimentoRepository.buscarPorNome(nome);

        if(alimentoOptional.isPresent()){
            return new AlimentoExibicaoDTO(alimentoOptional.get());
        } else {
            throw new RuntimeException("Alimento procurado não existente");
        }
    }

    public List<AlimentoExibicaoDTO> listarAlimentosPorFaixaDeCalorias(Double caloriaMinima, Double caloriaMaxima){
        return alimentoRepository
                .listarAlimentoPorFaixaDeCalorias(caloriaMinima, caloriaMaxima)
                .stream()
                .map(AlimentoExibicaoDTO::new)
                .toList();
    }

    public List<AlimentoExibicaoDTO> listarTotalCaloriasMenorQue(Double calorias){
        return alimentoRepository.findByTotalCaloriasLessThan(calorias);
    }

    public Double calcularCalorias(Double proteinas, Double carboidratos, Double gordura){
        Double calorias = (proteinas * 4) + (carboidratos * 4) + (gordura * 9);
        return calorias;
    }

}
