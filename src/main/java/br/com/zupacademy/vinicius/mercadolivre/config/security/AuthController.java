package br.com.zupacademy.vinicius.mercadolivre.config.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.vinicius.mercadolivre.usuario.LoginDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService geraToken;

	@PostMapping
	public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginDTO dto) {

		UsernamePasswordAuthenticationToken dadosLogin = dto.converter();
		
		try {
			Authentication autenticacao = authManager.authenticate(dadosLogin);
			String token = geraToken.gerarToken(autenticacao);
			return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
