package br.com.zupacademy.vinicius.mercadolivre.cadastrousuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
