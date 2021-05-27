package br.com.zupacademy.vinicius.mercadolivre.usuario.cadastro;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.zupacademy.vinicius.mercadolivre.config.validacao.UniqueValue;
import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioDTO {

	@Email
	@NotBlank
	@UniqueValue(domainClass = Usuario.class, fieldName = "email")
	private String email;
	
	
	@Length(min = 6)
	@NotBlank
	private String senhaRAW;
	
	public UsuarioDTO(String email, String senhaRAW) {
		this.email = email;
		this.senhaRAW = senhaRAW;
	}

	public Usuario converter() {
		return new Usuario(email, senhaRAW);
	}

	public String getEmail() {
		return email;
	}
	
}
