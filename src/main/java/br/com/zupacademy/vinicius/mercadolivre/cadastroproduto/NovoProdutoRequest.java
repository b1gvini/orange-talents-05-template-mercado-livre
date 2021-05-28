package br.com.zupacademy.vinicius.mercadolivre.cadastroproduto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.vinicius.mercadolivre.cadastrocategoria.Categoria;
import br.com.zupacademy.vinicius.mercadolivre.cadastrocategoria.CategoriaRepository;
import br.com.zupacademy.vinicius.mercadolivre.config.validacao.ExistingID;
import br.com.zupacademy.vinicius.mercadolivre.config.validacao.UniqueValue;
import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;

public class NovoProdutoRequest {

	@NotBlank
	@UniqueValue(domainClass = Produto.class, fieldName = "nome")
	private String nome;

	@Positive
	@NotNull
	private BigDecimal valor;

	@PositiveOrZero
	private Integer quantidade;

	@Size(min = 3)
	@Valid
	private Set<NovaCaracteristicaRequest> caracteristicas = new HashSet<>();

	@Length(max = 1000)
	private String descricao;

	@NotNull
	@ExistingID(domainClass = Categoria.class, fieldName = "id", message = "Id de categoria não existente.")
	private Long idCategoria;

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Set<NovaCaracteristicaRequest> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public Produto converter(CategoriaRepository categoriaRepository, Usuario usuario) {
		Optional<Categoria> categoria = categoriaRepository.findById(this.idCategoria);

		return new Produto(this.nome, this.valor, this.quantidade, this.descricao, categoria.get(),
				this.caracteristicas, usuario);

	}

}
