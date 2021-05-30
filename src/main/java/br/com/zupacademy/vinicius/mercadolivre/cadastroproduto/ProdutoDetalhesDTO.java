package br.com.zupacademy.vinicius.mercadolivre.cadastroproduto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.imagens.ImagemResponse;
import br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.opniao.OpiniaoResponse;

public class ProdutoDetalhesDTO {

	private String nome;
	private BigDecimal valor;
	private String descricao;
	private Set<CaracteristicaResponse> caracteristicas;
	private Set<ImagemResponse> imagens;
	private List<OpiniaoResponse> opinioes;
	private int totalNotas;
	private BigDecimal mediaDeNotas;

	public ProdutoDetalhesDTO(Produto produto) {
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.descricao = produto.getDescricao();
		this.caracteristicas = produto.getCaracteristicas().stream()
				.map(caracteristica -> new CaracteristicaResponse(caracteristica)).collect(Collectors.toSet());
		this.imagens = produto.getImagens().stream().map(imagem -> new ImagemResponse(imagem))
				.collect(Collectors.toSet());
		this.totalNotas = produto.getTotalOpinioes();
		this.opinioes = produto.getOpinioes().stream().map(opiniao -> new OpiniaoResponse(opiniao))
				.collect(Collectors.toList());
		this.mediaDeNotas = produto.getMediaNotas();

	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public Set<CaracteristicaResponse> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<ImagemResponse> getImagens() {
		return imagens;
	}

	public int getTotalOpinioes() {
		return totalNotas;
	}

	public List<OpiniaoResponse> getOpinioes() {
		return opinioes;
	}
	
	public BigDecimal getMediaDeNotas() {
		return mediaDeNotas;
	}

}
