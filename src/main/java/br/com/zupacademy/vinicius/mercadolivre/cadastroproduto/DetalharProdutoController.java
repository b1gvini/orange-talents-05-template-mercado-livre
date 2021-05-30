package br.com.zupacademy.vinicius.mercadolivre.cadastroproduto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetalharProdutoController {
	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping("/produtos/{id}")
	public ResponseEntity<ProdutoDetalhesDTO> buscar(@PathVariable("id") Long id){
		Optional<Produto> possivelProduto = produtoRepository.findById(id);
		if(possivelProduto.isEmpty()){
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(new ProdutoDetalhesDTO(possivelProduto.get()));
	}
}
