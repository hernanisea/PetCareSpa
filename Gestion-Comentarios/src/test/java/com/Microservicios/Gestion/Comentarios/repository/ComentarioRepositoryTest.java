package com.Microservicios.Gestion.Comentarios.repository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import com.Microservicios.Gestion.Comentarios.model.Comentario;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class ComentarioRepositoryTest {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @BeforeEach
    void limpiarBD() {
        comentarioRepository.deleteAll();
    }

    @Test
    void guardarYBuscarPorUsuario() {
        Comentario comentario = new Comentario(null, "Test contenido", "2025-06-24", true, 1L, 1L);
        comentarioRepository.save(comentario);

        List<Comentario> resultado = comentarioRepository.findByIdUsuario(1L);

        assertThat(resultado).isNotEmpty();
        assertThat(resultado.get(0).getContenido()).isEqualTo("Test contenido");
    }

    @Test
    void eliminarComentarioPorId(){

        Comentario comentario = new Comentario(null, "Comentario a eliminar", "2025-06-24", true, 1L, 1L);
        Comentario guardado = comentarioRepository.save(comentario);

        comentarioRepository.deleteById(guardado.getIdComentario());

        boolean existe = comentarioRepository.findById(guardado.getIdComentario()).isPresent();
        assertThat(existe).isFalse();

    }
}

