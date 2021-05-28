package br.com.zupacademy.vinicius.mercadolivre.cadastroproduto;

import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.vinicius.mercadolivre.cadastrocategoria.CategoriaRepository;
import br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.imagens.NovasImagensRequest;
import br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.imagens.Uploader;
import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/produtos")
public class CadastroProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private Uploader uploaderFake;

	@PostMapping
	public ResponseEntity<?> cadastrarProduto(@RequestBody @Valid NovoProdutoRequest dto,
			@AuthenticationPrincipal Usuario usuario) {

		Produto produto = dto.converter(categoriaRepository, usuario);
		produtoRepository.save(produto);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/{id}/imagens")
	public ResponseEntity<?> cadastraImagens(@PathVariable("id") Long id, @Valid NovasImagensRequest request,
			@AuthenticationPrincipal Usuario usuario) {

		Set<String> links = uploaderFake.envia(request.getImagens());
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if (produto.isPresent()) {
			Produto produtoExiste = produto.get();

			if (!produtoExiste.pertenceAoUsuario(usuario)) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}

			produtoExiste.associaImagens(links);
			produtoRepository.save(produtoExiste);

			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}
}
