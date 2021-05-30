package br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.pergunta;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
public class CadastroPerguntaController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PerguntaRepository perguntaRepository;
	
	@Autowired
	private Mensageria mensageria;
	
	@PostMapping("/produtos/{id}/perguntas")
	public ResponseEntity<?> perguntar(@PathVariable("id") Long id, @RequestBody @Valid PerguntaRequest request,
			@AuthenticationPrincipal Usuario usuario) {
		
		Optional<Produto> produto = produtoRepository.findById(id);
		if(produto.isEmpty()) {
			ResponseEntity.notFound().build();
		}
		Produto produtoExiste = produto.get();
		Pergunta pergunta = request.converter(usuario, produtoExiste);
		perguntaRepository.save(pergunta);
		mensageria.enviaPerguntaParaUsuarioDono(pergunta, produtoExiste);

		return ResponseEntity.ok().build();
	}
}
