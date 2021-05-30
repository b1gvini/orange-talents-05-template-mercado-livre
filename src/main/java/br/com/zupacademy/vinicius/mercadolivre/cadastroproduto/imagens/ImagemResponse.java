package br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.imagens;

import br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.ImagemProduto;

public class ImagemResponse {

	private String link;

	public ImagemResponse(ImagemProduto imagemProduto) {
		this.link = imagemProduto.getLink();
	}

	public String getLink() {
		return link;
	}
	
}