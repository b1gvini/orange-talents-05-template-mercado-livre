package br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.pergunta;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;

@Entity
public class Pergunta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String titulo;
	
	private OffsetDateTime criadoEm;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Produto produto;
	
	@Deprecated
	public Pergunta() {
		
	}
	
	public Pergunta(String titulo, Usuario usuario, Produto produto) {
		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;
		this.criadoEm = OffsetDateTime.now();
	}
	
	public String getTitulo() {
		return this.titulo;
	}

}
