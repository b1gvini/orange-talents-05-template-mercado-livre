package br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.opniao;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.ProdutoRepository;
import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;

@RestController
public class CadastroOpiniaoController {
	@Autowired
	private OpiniaoRepository opiniaoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@PostMapping("/produtos/{id}/opinioes")
	public ResponseEntity<OpiniaoRequest> cadastrar(@PathVariable("id") Long id,
			@AuthenticationPrincipal Usuario usuario, @RequestBody @Valid OpiniaoRequest request) {

		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Opiniao opniao = request.converter(usuario, produto.get());
		opiniaoRepository.save(opniao);
		return ResponseEntity.ok().build();

	}
}
