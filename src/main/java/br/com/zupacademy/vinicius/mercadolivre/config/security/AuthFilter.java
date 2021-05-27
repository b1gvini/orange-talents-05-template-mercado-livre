package br.com.zupacademy.vinicius.mercadolivre.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;
import br.com.zupacademy.vinicius.mercadolivre.usuario.UsuarioRepository;

public class AuthFilter extends OncePerRequestFilter {


	private TokenService tokenService;
	
	private UsuarioRepository usuarioRepository;

	public AuthFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarToken(request);
		boolean valido = tokenService.isTokenValido(token);
		if(valido) {
			autenticarUsuario(token);
		}

		filterChain.doFilter(request, response);
	}

	private void autenticarUsuario(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);
		Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
		if(usuario.isEmpty()) {
			throw new AccessDeniedException("Token inválido para este usuário.");
		}
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario.get(), null, usuario.get().getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		boolean tokenInvalido = (token == null || token.isEmpty() || !token.startsWith("Bearer "));
		if (tokenInvalido) {
			return null;
		}

		return token.substring(7, token.length());
	}

}
