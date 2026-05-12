package br.com.fiap.calorias.service;

import br.com.fiap.calorias.dto.UsuarioAtualizacaoDTO;
import br.com.fiap.calorias.dto.UsuarioCadastroDTO;
import br.com.fiap.calorias.dto.UsuarioExibicaoDTO;
import br.com.fiap.calorias.exception.UsuarioNaoEncontradoException;
import br.com.fiap.calorias.model.Usuario;
import br.com.fiap.calorias.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioExibicaoDTO salvarUsuario(UsuarioCadastroDTO usuarioDTO) {
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioDTO.senha());

        Usuario usuario = new  Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        usuario.setSenha(senhaCriptografada);


        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioExibicaoDTO(usuarioSalvo);
    }

    public UsuarioExibicaoDTO buscarPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if(usuarioOptional.isPresent()){
            return new UsuarioExibicaoDTO(usuarioOptional.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuário não existe no banco de dados.");
        }

    }

    public List<UsuarioExibicaoDTO> buscarTodosUsuarios() {
        return usuarioRepository
                .findAll()
                .stream()
                .map(UsuarioExibicaoDTO::new)
                .toList();
    }

    public void excluir(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if(usuarioOptional.isPresent()){
            usuarioRepository.delete(usuarioOptional.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado");
        }
    }

    public UsuarioExibicaoDTO atualizar(Long id, UsuarioAtualizacaoDTO usuarioAtualizacaoDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        BeanUtils.copyProperties(usuarioAtualizacaoDTO, usuario);
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);

            return new UsuarioExibicaoDTO(usuarioAtualizado);
    }

//    public UsuarioExibicaoDTO buscarPorEmail(String email) {
//        Optional<Usuario> usuarioOptional =
//                usuarioRepository.findByEmail(email);
//
//        if(usuarioOptional.isPresent()){
//            return new UsuarioExibicaoDTO(usuarioOptional.get());
//        } else
//            throw new UsuarioNaoEncontradoException("Usuário não existe no banco de dados.");
//    }
}
