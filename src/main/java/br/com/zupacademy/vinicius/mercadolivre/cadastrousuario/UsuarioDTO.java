package br.com.zupacademy.vinicius.mercadolivre.cadastrousuario;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioDTO {

    @Email @NotBlank
    private String email;
    @Length(min = 6) @NotBlank
    private String senhaRAW;

    public UsuarioDTO(String email, String senhaRAW){
        this.email = email;
        this.senhaRAW = senhaRAW;
    }

    public Usuario converter(){
        return new Usuario(email,senhaRAW);
    }
}
