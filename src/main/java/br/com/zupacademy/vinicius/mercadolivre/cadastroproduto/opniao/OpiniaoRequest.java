package br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.opniao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;

public class OpiniaoRequest {

	@NotNull
	@Range(min = 1, max = 5)
	private int nota;
	@NotBlank
	private String titulo;
	@NotBlank
	@Length(max = 500)
	private String descricao;

	public OpiniaoRequest(int nota, String titulo, String descricao) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public int getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Opiniao converter(Usuario usuario, Produto produto) {
		return new Opiniao(nota, titulo, descricao, usuario, produto);
	}

}
