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
	@NotNull
	private Usuario usuario;

	@Positive
	@NotNull
	private BigDecimal valor;

	@PositiveOrZero
	private Integer quantidade;

	@Column(columnDefinition = "text")
	@Length(max = 1000)
	private String descricao;

	@ManyToOne
	private Categoria categoria;

	private OffsetDateTime criadoEm;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
	@Size(min = 3)
	private Set<Caracteristica> caracteristicas = new HashSet<>();

	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "produto")
	private Set<ImagemProduto> imagens = new HashSet<>();

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public void associaImagens(Set<String> links) {

		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link))
				.collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}

	public boolean pertenceAoUsuario(Usuario usuarioDono) {

		return usuario.equals(usuarioDono);
	}

}
