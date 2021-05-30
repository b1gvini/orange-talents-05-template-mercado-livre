package br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.pergunta;

import org.springframework.stereotype.Component;

import br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.Produto;

@Component
public class Mensageria {

	public void enviaPerguntaParaUsuarioDono(Pergunta pergunta, Produto produto) {
		String tituloPergunta = pergunta.getTitulo();
		String emailDonoProduto = produto.getUsuario().getEmail();
		System.out.println(tituloPergunta + "\nenviado para: " + emailDonoProduto);
	}
}
