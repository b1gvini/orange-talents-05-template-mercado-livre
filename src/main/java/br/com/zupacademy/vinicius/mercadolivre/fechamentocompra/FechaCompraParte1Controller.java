package br.com.zupacademy.vinicius.mercadolivre.fechamentocompra;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.ProdutoRepository;
import br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.pergunta.Emails;
import br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.pergunta.FakeMailer;
import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;

@RestController
public class FechaCompraParte1Controller {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CompraRepository compraRepository;
	@Autowired
	private Emails mailer;

	@PostMapping("/compras")
	public ResponseEntity<?> novaCompra(@RequestBody @Valid NovaCompraRequest request, @AuthenticationPrincipal Usuario usuario,
			UriComponentsBuilder uriComponentsBuilder) throws BindException {
		Produto produto = produtoRepository.findById(request.getIdProduto()).get();
		int quantidade = request.getQuantidade();
		boolean abateu = produto.abataEstoque(request.getQuantidade());
		if (abateu) {
			GatewayPagamento gateway = request.getGateway();
			Compra novaCompra = new Compra(produto, quantidade, usuario, gateway);
			compraRepository.save(novaCompra);

			if (gateway.equals(GatewayPagamento.PAYPAL)) {
				String urlRetornoPaypal = uriComponentsBuilder.path("/retorno-paypal/{id}").buildAndExpand(novaCompra.getId()).toString();
				String urlFimPaypal = "paypal.com/" + novaCompra.getId() + "?redirectUrl="+ urlRetornoPaypal;
				mailer.novaCompra(novaCompra);
				return new ResponseEntity<>(urlFimPaypal, HttpStatus.FOUND);
			} else {
				String urlRetornoPagSeguro = uriComponentsBuilder.path("/retorno-pagseguro/{id}").buildAndExpand(novaCompra.getId()).toString();
				String urlFimPagSeguro = "pagseguro.com/" + novaCompra.getId() + "?redirectUrl="+ urlRetornoPagSeguro;
				mailer.novaCompra(novaCompra);
				return new ResponseEntity<>(urlFimPagSeguro, HttpStatus.FOUND);
			}
		}
		BindException problemaComEstoque = new BindException(request, "NovaCompraRequest");
		problemaComEstoque.reject(null, "Não foi possível realizar a compra por conta do estoque");
		throw problemaComEstoque;

	}
}
