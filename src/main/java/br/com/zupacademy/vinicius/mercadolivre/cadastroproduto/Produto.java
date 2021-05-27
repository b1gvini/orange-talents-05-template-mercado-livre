package br.com.zupacademy.vinicius.mercadolivre.cadastroproduto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.vinicius.mercadolivre.cadastrocategoria.Categoria;
import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;

	@ManyToOne
	private Usuario usuario;

	@Positive
	@NotNull
	private BigDecimal valor;

	@PositiveOrZero
	private Integer quantidade;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
	@Size(min = 3)
	private Set<Caracteristica> caracteristicas = new HashSet<>();

	@Column(columnDefinition = "text")
	@Length(max = 1000)
	private String descricao;

	@ManyToOne
	private Categoria categoria;

	private OffsetDateTime criadoEm;

	@Deprecated
	public Produto() {

	}

	public Produto(String nome, BigDecimal valor, Integer quantidade, String descricao, Categoria categoria,
			Set<NovaCaracteristicaRequest> caracteristicas, Usuario usuario) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.caracteristicas = caracteristicas.stream()
				.map(caracteristicaDTO -> new Caracteristica(caracteristicaDTO, this)).collect(Collectors.toSet());
		;
		this.usuario = usuario;
		this.criadoEm = OffsetDateTime.now();
	}

}
