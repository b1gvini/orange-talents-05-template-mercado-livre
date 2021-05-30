package br.com.zupacademy.vinicius.mercadolivre.cadastroproduto;

public class CaracteristicaResponse {

	private String nome;

	private String descricao;
	
	public CaracteristicaResponse(Caracteristica caracteristica) {
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

}
