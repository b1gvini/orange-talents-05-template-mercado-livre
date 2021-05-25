package br.com.zupacademy.vinicius.mercadolivre.cadastrousuario;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email @NotBlank
    private String email;
    @Length(min=6)
    private String senha;

    private OffsetDateTime criadoEm;

    public Usuario(){

    }
    public Usuario(String email, String senha){
        this.email = email;
        this.senha = new BCryptPasswordEncoder().encode(senha);
        this.criadoEm = OffsetDateTime.now();
    }
	public String getEmail() {
		return email;
	}
    
    


}
