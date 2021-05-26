package br.com.zupacademy.vinicius.mercadolivre.cadastrocategoria;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.vinicius.mercadolivre.config.validacao.ExisteCategoriaValidator;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	private ExisteCategoriaValidator existeCategoriaValidator;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(existeCategoriaValidator);
	}
	
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@PostMapping
	public ResponseEntity<CategoriaDTO> cadastrar(@RequestBody @Valid CategoriaDTO dto){
		Categoria categoria = dto.converter(categoriaRepository);
		categoriaRepository.save(categoria);
		return ResponseEntity.ok().build();
	}

}
