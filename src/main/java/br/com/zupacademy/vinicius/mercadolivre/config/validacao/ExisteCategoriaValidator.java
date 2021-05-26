package br.com.zupacademy.vinicius.mercadolivre.config.validacao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.vinicius.mercadolivre.cadastrocategoria.Categoria;
import br.com.zupacademy.vinicius.mercadolivre.cadastrocategoria.CategoriaDTO;
import br.com.zupacademy.vinicius.mercadolivre.cadastrocategoria.CategoriaRepository;

@Component
public class ExisteCategoriaValidator implements Validator {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return CategoriaDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		if (errors.hasErrors()) {
			return;
		}

		CategoriaDTO dto = (CategoriaDTO) target;
		Long idCategoria = dto.getIdCategoriaMae();

		if (idCategoria != null) {
			Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);
			if(categoria.isEmpty()) {
				errors.rejectValue("idCategoriaMae", null, "Id de categoria inválido");
			}

		}

	}

}
