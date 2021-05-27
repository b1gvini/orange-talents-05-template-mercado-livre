package br.com.zupacademy.vinicius.mercadolivre.cadastroproduto;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.vinicius.mercadolivre.cadastrocategoria.CategoriaRepository;
import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/produtos")
public class CadastroProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired 
	private CategoriaRepository categoriaRepository;

	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoProdutoRequest dto, @AuthenticationPrincipal Usuario usuario){
		
		Produto produto = dto.converter(categoriaRepository, usuario);
		produtoRepository.save(produto);
		
		return ResponseEntity.ok().build();
	}
}
