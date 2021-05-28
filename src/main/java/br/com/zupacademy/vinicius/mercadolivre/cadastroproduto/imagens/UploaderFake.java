package br.com.zupacademy.vinicius.mercadolivre.cadastroproduto.imagens;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Primary
public class UploaderFake implements Uploader {

	/*
	 * recebe lista de imagens gera link para imagens que foram feitas o fake upload
	 */

	public Set<String> envia(List<MultipartFile> imagens) {
		return imagens.stream().map(img -> "http://bucket.io/" +img.getOriginalFilename()).collect(Collectors.toSet());
	}

}
