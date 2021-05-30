package br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.opniao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;

@Entity
public class Opiniao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Range(min=1, max=10)
	private int nota;
	
	@NotBlank
	private String titulo;
	
	@Column(columnDefinition = "text")
	@Length(max = 500)
	private String descricao;

	@ManyToOne
	private Usuario usuario;

	@ManyToOne
	private Produto produto;

	@Deprecated
	public Opiniao() {

	}

	public Opiniao(int nota, String titulo, String descricao, Usuario usuario, Produto produto) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
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

	
}
