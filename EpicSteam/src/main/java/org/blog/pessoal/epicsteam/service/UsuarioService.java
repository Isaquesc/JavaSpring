package org.blog.pessoal.epicsteam.service;

import java.nio.charset.Charset;
import java.util.*;
import org.blog.pessoal.epicsteam.model.UserLogin;
import org.apache.commons.codec.binary.Base64;
import org.blog.pessoal.epicsteam.model.Usuario;
import org.blog.pessoal.epicsteam.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository bancoRepository;

    // REGRA DE NEGOCIO (CADASTRANDO O USUARIO) - CRIPTOGRAFANDO A SENHA
    public Usuario CadastrarUsuario(Usuario usuario) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    String senhaEncoder = encoder.encode(usuario.getSenha());
    usuario.setSenha(senhaEncoder);

    return bancoRepository.save(usuario);
    }

    //LOGIN (COMPARANDO A SENHAS)
    public Optional<UserLogin> Logar(Optional <UserLogin> user){
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    Optional<Usuario> usuario = bancoRepository.findByUsuario(user.get().getUsuario());

    if(usuario.isPresent()) {
        if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())){

            String auth = user.get().getUsuario() + ":" + user.get().getSenha();
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);

            user.get().setToken(authHeader);
            user.get().setNome(usuario.get().getNome());
            user.get().setSenha(usuario.get().getSenha());

            return user;
        }
    }

    return null;

    }
    
}
