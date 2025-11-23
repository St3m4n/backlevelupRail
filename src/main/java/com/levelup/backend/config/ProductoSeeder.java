package com.levelup.backend.config;

import com.levelup.backend.model.Categoria;
import com.levelup.backend.model.Producto;
import com.levelup.backend.repository.CategoriaRepository;
import com.levelup.backend.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(2)
public class ProductoSeeder implements CommandLineRunner {
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    private static final Map<Integer, String> CATEGORY_BY_ID = Map.of(
            1, "Juegos de Mesa",
            2, "Accesorios",
            3, "Consolas",
            4, "Computadores Gamers",
            5, "Sillas Gamers",
            6, "Mouse",
            7, "Mousepad",
            8, "Poleras Personalizadas",
            9, "Polerones Gamers Personalizados"
    );

    private static final List<ProductSeed> PRODUCT_SEED = List.of(
            new ProductSeed("JM001", "Catan", "Catan es un clásico juego de estrategia donde los jugadores colonizan una isla, comercian recursos y compiten por la supremacía.", "Kosmos", "Level-Up", "https://levelupstorage2.blob.core.windows.net/productos/JM001.webp", 29990, 12, 3, 1),
            new ProductSeed("JM002", "Carcassonne", "Carcassonne es un juego de colocación de losetas en el que construyes ciudades, caminos y monasterios para sumar puntos.", "Hans im Glück", "Level-Up", "https://levelupstorage2.blob.core.windows.net/productos/JM002.png", 24990, 7, 2, 1),
            new ProductSeed("JM003", "Dixit", "Dixit es un creativo juego de cartas ilustradas donde la imaginación y la interpretación son clave para ganar.", "Libellud", "Level-Up", "https://levelupstorage2.blob.core.windows.net/productos/JM003.webp", 21990, 10, 3, 1),
            new ProductSeed("AC001", "Control Xbox Series X", "Control inalámbrico original para Xbox Series X|S con ergonomía mejorada y respuesta precisa.", "Microsoft", "Level-Up", "https://levelupstorage2.blob.core.windows.net/productos/AC001.jpg", 59990, 5, 2, 2),
            new ProductSeed("AC002", "Auriculares HyperX Cloud II", "Headset HyperX Cloud II con sonido envolvente virtual 7.1 y micrófono con cancelación de ruido.", "HyperX", "Level-Up", "https://levelupstorage2.blob.core.windows.net/productos/AC002.webp", 79990, 8, 2, 2),
            new ProductSeed("AC003", "Teclado Mecánico Redragon Kumara", "Teclado mecánico compacto Redragon Kumara con switches durables e iluminación RGB.", "Redragon", "Level-Up", "https://levelupstorage2.blob.core.windows.net/productos/AC003.png", 39990, 15, 4, 2),
            new ProductSeed("CO001", "PlayStation 5", "La consola PS5 ofrece gráficos de nueva generación, carga ultrarrápida con SSD y gatillos adaptativos.", "Sony", "Level-Up", "https://levelupstorage2.blob.core.windows.net/productos/CO001.webp", 549990, 2, 1, 3),
            new ProductSeed("CO002", "Nintendo Switch OLED", "La consola Nintendo Switch OLED ofrece una pantalla OLED de 7 pulgadas, audio mejorado y un soporte ajustable para una experiencia portátil y en TV.", "Nintendo", "Level-Up", "https://levelupstorage2.blob.core.windows.net/productos/CO002.webp", 349990, 4, 1, 3),
            new ProductSeed("CG001", "PC Gamer ASUS ROG Strix", "PC gamer ASUS ROG Strix de alto rendimiento, ideal para juegos AAA y streaming.", "ASUS", "Level-Up", "https://levelupstorage2.blob.core.windows.net/productos/CG001.png", 1299990, 3, 1, 4),
            new ProductSeed("CG002", "Notebook Gamer MSI Katana", "Notebook MSI Katana con GPU dedicada y pantalla de alta tasa de refresco para gaming fluido.", "MSI", "Level-Up", "https://levelupstorage2.blob.core.windows.net/productos/CG002.jpg", 999990, 5, 1, 4),
            new ProductSeed("SG001", "Silla Gamer Secretlab Titan", "Silla gamer Secretlab Titan con soporte ergonómico y materiales premium.", "Secretlab", "Level-Up", "https://levelupstorage2.blob.core.windows.net/productos/SG001.jpg", 349990, 4, 1, 5),
            new ProductSeed("SG002", "Silla Gamer Cougar Armor One", "Silla Cougar Armor One con estructura resistente y cojines ajustables.", "Cougar", "Level-Up", "https://levelupstorage2.blob.core.windows.net/productos/SG002.png", 199990, 6, 2, 5),
            new ProductSeed("MS001", "Mouse Logitech G502 HERO", "Mouse Logitech G502 HERO con sensor de alta precisión y pesos ajustables.", "Logitech", "Level-Up", "https://levelupstorage2.blob.core.windows.net/productos/MS001.webp", 49990, 18, 4, 6),
            new ProductSeed("MS002", "Mouse Razer DeathAdder V2", "Mouse Razer DeathAdder V2 con diseño ergonómico y switches ópticos Razer.", "Razer", "Level-Up", "https://levelupstorage2.blob.core.windows.net/productos/MS002.webp", 39990, 12, 3, 6),
            new ProductSeed("MP001", "Mousepad Razer Goliathus Extended Chroma", "Mousepad extendido con iluminación RGB Chroma y superficie optimizada para precisión.", "Razer", "Level-Up", "https://levelupstorage2.blob.core.windows.net/productos/MP001.jpg", 29990, 10, 2, 7),
            new ProductSeed("MP002", "Mousepad Logitech G Powerplay", "Mousepad Logitech Powerplay con carga inalámbrica continua para mouse compatibles.", "Logitech", "Level-Up", "https://levelupstorage2.blob.core.windows.net/productos/MP002.jpg", 99990, 3, 1, 7),
            new ProductSeed("PP001", "Polera Gamer Personalizada 'Level-Up'", "Polera personalizada Level-Up con diseño gamer, tela suave y resistente.", "Level-Up", "Level-Up", "https://levelupstorage2.blob.core.windows.net/productos/PP001.png", 14990, 20, 5, 8),
            new ProductSeed("PP002", "Polera Retro Arcade", "Polera temática retro arcade con estampado de alta calidad y ajuste cómodo.", "Level-Up", "Level-Up", "https://levelupstorage2.blob.core.windows.net/productos/PP002.png", 15990, 15, 4, 8),
            new ProductSeed("PG001", "Polerón Gamer Hoodie 'Respawn'", "Polerón con capucha estilo gamer, interior suave y estampado Respawn.", "Level-Up", "Level-Up", "https://levelupstorage2.blob.core.windows.net/productos/PG001.png", 24990, 12, 3, 9),
            new ProductSeed("PG002", "Polerón Level-Up Logo", "Polerón con logo Level-Up, ideal para el día a día con estilo gamer.", "Level-Up", "Level-Up", "https://levelupstorage2.blob.core.windows.net/productos/PG002.png", 26990, 10, 3, 9),
            new ProductSeed("SKU-DCDBFBC1", "algo", "algo bvasdasdas", "aaa", "aaa", "https://inaturalist-open-data.s3.amazonaws.com/photos/175267007/original.jpg", 20000, 10, 5, 2)
    );

    @Override
    @Transactional
    public void run(String... args) {
        int saved = 0;
        for (ProductSeed seed : PRODUCT_SEED) {
            if (productoRepository.existsByCodigo(seed.codigo())) {
                continue;
            }
            String categoryName = CATEGORY_BY_ID.get(seed.categoriaId());
            if (categoryName == null) {
                log.warn("Skip {} because category id {} is unknown", seed.codigo(), seed.categoriaId());
                continue;
            }
            Categoria categoria = categoriaRepository.findByNombreIgnoreCase(categoryName)
                    .orElseGet(() -> categoriaRepository.save(Categoria.builder().nombre(categoryName).build()));
            Producto producto = Producto.builder()
                    .codigo(seed.codigo())
                    .nombre(seed.nombre())
                    .descripcion(seed.descripcion())
                    .fabricante(seed.fabricante())
                    .distribuidor(seed.distribuidor())
                    .imagenUrl(seed.imagenUrl())
                    .precio(BigDecimal.valueOf(seed.precio()))
                    .stock(seed.stock())
                    .stockCritico(seed.stockCritico())
                    .categoria(categoria)
                    .build();
            productoRepository.save(producto);
            saved++;
        }
        log.debug("Seeded {} nuevos productos", saved);
    }

    private record ProductSeed(
            String codigo,
            String nombre,
            String descripcion,
            String fabricante,
            String distribuidor,
            String imagenUrl,
            long precio,
            int stock,
            int stockCritico,
            int categoriaId
    ) {
    }
}
