package br.com.zupacademy.vinicius.mercadolivre.usuario.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;
import br.com.zupacademy.vinicius.mercadolivre.usuario.UsuarioRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class CadastroUsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioDTO dto){
        Usuario usuario = dto.converter();
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }

}
