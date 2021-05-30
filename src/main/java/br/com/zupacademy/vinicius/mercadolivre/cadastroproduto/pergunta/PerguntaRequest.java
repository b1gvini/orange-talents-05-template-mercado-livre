package br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.pergunta;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;

public class PerguntaRequest {

	@NotBlank
	private String titulo;

	@JsonCreator(mode = Mode.PROPERTIES)
	public PerguntaRequest(String titulo) {
		this.titulo = titulo;
	}
	
	public Pergunta converter(Usuario usuario, Produto produto) {
		return new Pergunta(titulo, usuario, produto);
	}
	
}
