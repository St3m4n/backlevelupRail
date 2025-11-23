package com.levelup.backend.config;

import com.levelup.backend.model.Categoria;
import com.levelup.backend.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(1)
public class CategoriaSeeder implements CommandLineRunner {
    private final CategoriaRepository categoriaRepository;

    private static final List<CategoriaSeed> CATEGORY_SEED = List.of(
            new CategoriaSeed(1, "Juegos de Mesa"),
            new CategoriaSeed(2, "Accesorios"),
            new CategoriaSeed(3, "Consolas"),
            new CategoriaSeed(4, "Computadores Gamers"),
            new CategoriaSeed(5, "Sillas Gamers"),
            new CategoriaSeed(6, "Mouse"),
            new CategoriaSeed(7, "Mousepad"),
            new CategoriaSeed(8, "Poleras Personalizadas"),
            new CategoriaSeed(9, "Polerones Gamers Personalizados")
    );

    @Override
    @Transactional
    public void run(String... args) {
        for (CategoriaSeed seed : CATEGORY_SEED) {
            categoriaRepository.findByNombreIgnoreCase(seed.nombre())
                    .orElseGet(() -> saveCategory(seed.nombre()));
        }
        log.debug("Seeded {} categorias", CATEGORY_SEED.size());
    }

    private Categoria saveCategory(String nombre) {
        Categoria categoria = Categoria.builder().nombre(nombre).build();
        return categoriaRepository.save(categoria);
    }

    private record CategoriaSeed(int id, String nombre) {
    }
}
