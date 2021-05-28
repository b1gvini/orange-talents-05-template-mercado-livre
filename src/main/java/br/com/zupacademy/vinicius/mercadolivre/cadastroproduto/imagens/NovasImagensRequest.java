package br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.imagens;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class NovasImagensRequest {

	@Size(min=1)
	@NotNull
	List<MultipartFile> imagens = new ArrayList<>();

	public NovasImagensRequest(@Size(min = 1) List<MultipartFile> imagens) {
		this.imagens = imagens;
	}

	public List<MultipartFile> getImagens(){
		return imagens;
	}
	
	
	
	
}