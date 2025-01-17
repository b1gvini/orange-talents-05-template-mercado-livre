package br.com.zupacademy.vinicius.mercadolivre.config.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroHandler {
	
		@Autowired
		private MessageSource messageSource;
	
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public List<ErroDTO> handle(MethodArgumentNotValidException exception) {
			List<ErroDTO> dto = new ArrayList<>();
			List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
			
			fieldErrors.forEach(e -> {
				String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
				ErroDTO erro = new ErroDTO(e.getField(), mensagem);
				dto.add(erro);
			
			});
			
			return dto;
		}
		
		@ResponseStatus(HttpStatus.BAD_REQUEST)
		@ExceptionHandler(BindException.class)
		public List<ErroDTO> handleBind(BindException exception) {
			List<ErroDTO> dto = new ArrayList<>();
			List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
			if(!fieldErrors.isEmpty()) {
				fieldErrors.forEach(e -> {
					String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
					ErroDTO erro = new ErroDTO(e.getField(), mensagem);
					dto.add(erro);
					
				});
			
			return dto;
			}
			List<ObjectError> globals= exception.getBindingResult().getGlobalErrors();
			globals.forEach(e -> {
				String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
				ErroDTO erro = new ErroDTO(e.getDefaultMessage(), mensagem);
				dto.add(erro);
				
			});
			return dto;
		}
		

}
