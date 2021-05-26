package br.com.zupacademy.vinicius.mercadolivre.cadastrocategoria;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.vinicius.mercadolivre.config.validacao.UniqueValue;

public class CategoriaDTO {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;

	private Long idCategoriaMae;
	
	public CategoriaDTO(String nome, Long idCategoriaMae) {
		this.nome = nome;
		this.idCategoriaMae = idCategoriaMae;
	}

	public Categoria converter(CategoriaRepository categoriaRepository) {
		if (idCategoriaMae == null) {
			return new Categoria(nome);
		}
		Optional<Categoria> possivelCategoria = categoriaRepository.findById(idCategoriaMae);
		if (possivelCategoria.isEmpty()) {
			throw new NoSuchElementException("Id de categoria não encontrado");
		}
		Categoria novaCategoria = new Categoria(nome);
		novaCategoria.setCategoriaMae(possivelCategoria.get());
		return novaCategoria;
	}

	public String getNome() {
		return nome;
	}

	public Long getIdCategoriaMae() {
		return idCategoriaMae;
	}

}