package br.com.zupacademy.vinicius.mercadolivre.fechamentocompra;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.Produto;
import br.com.zupacademy.vinicius.mercadolivre.usuario.Usuario;

@Entity
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Produto produtoEscolhido;
	@NotNull
	private int quantidade;
	@ManyToOne
	@NotNull
	private Usuario usuarioComprador;

	@Enumerated(EnumType.STRING)
	private GatewayPagamento gatewayPagamento;
	
	@Enumerated(EnumType.STRING)
	private StatusCompra status;
	
	@Deprecated
	public Compra() {
		
	}

	public Compra(Produto produto, int quantidade, Usuario usuario, GatewayPagamento gatewayPagamento) {
		this.produtoEscolhido = produto;
		this.quantidade = quantidade;
		this.usuarioComprador = usuario;
		this.gatewayPagamento = gatewayPagamento;
		this.status = StatusCompra.INICIADA;
	}

	@Override
	public String toString() {
		return "Compra [produtoEscolhido=" + produtoEscolhido + ", quantidade=" + quantidade + ", usuarioComprador="
				+ usuarioComprador + "]";
	}

	public Long getId() {
		return id;
	}

	public Produto getProdutoEscolhido() {
		return produtoEscolhido;
	}

	public int getQuantidade() {
		return quantidade;
	}
	
}
