package br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.pergunta;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zupacademy.vinicius.mercadolivre.fechamentocompra.Compra;

@Component
public class Emails {

	@Autowired
	private Mailer mailer;

	public void novaPergunta(@NotNull @Valid Pergunta pergunta) {
		mailer.send("<html>...</html>", "Nova pergunta...", pergunta.getUsuario().getEmail(),
				"novapergunta@nossomercadolivre.com", pergunta.getUsuario().getEmail());
	}
	
	public void novaCompra(Compra novaCompra) {
		mailer.send("Nova compra do produto "+ novaCompra.getProdutoEscolhido().getNome() +"\nQuantidade: "+ novaCompra.getQuantidade()+ "\nConfirmada"
				, "Nova venda realizada..", "novavenda@nossomercadolivre.com", "bot@nossomercadolivre.com", novaCompra.getProdutoEscolhido().getUsuario().getEmail());
	}

}
