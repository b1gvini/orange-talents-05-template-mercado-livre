package br.com.zupacademy.vinicius.mercadolivre.cadastroproduto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Caracteristica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;
	
	@ManyToOne
	private Produto produto;
	
	@Deprecated
	public Caracteristica() {
		
	}
	
	public Caracteristica(NovaCaracteristicaRequest caracteristicaDTO, Produto produto) {
		this.nome = caracteristicaDTO.getNome();
		this.descricao = caracteristicaDTO.getDescricao();
		this.produto = produto;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
