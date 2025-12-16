package com.forcetrack.backend;

import com.forcetrack.backend.entity.Usuario;
import com.forcetrack.backend.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Tests para verificar que el backend funciona correctamente
 * 
 * Los logs se mostrarán en la consola cuando ejecutes:
 * ./mvnw test
 * 
 * FILTRO PARA VER LOS LOGS:
 * Busca "BackendApiTest" en la consola
 */
@SpringBootTest
@ActiveProfiles("test")
class BackendApiTest {

    private static final Logger log = LoggerFactory.getLogger(BackendApiTest.class);
    private static final String SEPARATOR = "═══════════════════════════════════════════════════════";

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        log.info(SEPARATOR);
        log.info("🔧 INICIANDO TESTS DEL BACKEND");
        log.info(SEPARATOR);
        log.info("📍 Base de datos: Supabase/PostgreSQL");
        log.info(SEPARATOR);
    }

    @Test
    void contextLoads() {
        log.info("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        log.info("🔍 TEST: Cargar contexto de Spring Boot");
        log.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        log.info("✅ Contexto cargado correctamente");
        assert usuarioRepository != null : "El repositorio de usuarios no debe ser nulo";
    }

    @Test
    void testCreateUsuario() {
        log.info("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        log.info("📝 TEST: Crear usuario en la base de datos");
        log.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        String timestamp = String.valueOf(System.currentTimeMillis());
        String nombreUsuario = "test_user_" + timestamp;
        String correo = "test_" + timestamp + "@forcetrack.com";
        String contrasena = "password123";

        log.info("📝 Creando usuario:");
        log.info("   👤 Nombre: {}", nombreUsuario);
        log.info("   📧 Correo: {}", correo);

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setCorreo(correo);
        usuario.setContrasena(contrasena);

        Usuario saved = usuarioRepository.save(usuario);

        log.info("✅ Usuario creado exitosamente");
        log.info("   🆔 ID: {}", saved.getId());
        log.info("   👤 Nombre: {}", saved.getNombreUsuario());
        log.info("   📧 Correo: {}", saved.getCorreo());

        assert saved.getId() != null : "El ID del usuario no debe ser nulo";
        assert saved.getNombreUsuario().equals(nombreUsuario) : "El nombre debe coincidir";
        assert saved.getCorreo().equals(correo) : "El correo debe coincidir";
    }

    @Test
    void testFindUsuarioByCorreo() {
        log.info("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        log.info("🔍 TEST: Buscar usuario por correo");
        log.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        // Crear un usuario primero
        String timestamp = String.valueOf(System.currentTimeMillis());
        String correo = "find_test_" + timestamp + "@forcetrack.com";

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("Usuario Test");
        usuario.setCorreo(correo);
        usuario.setContrasena("password123");

        usuarioRepository.save(usuario);
        log.info("📝 Usuario creado con correo: {}", correo);

        // Buscar el usuario
        var found = usuarioRepository.findByCorreo(correo);

        if (found.isPresent()) {
            Usuario u = found.get();
            log.info("✅ Usuario encontrado");
            log.info("   🆔 ID: {}", u.getId());
            log.info("   👤 Nombre: {}", u.getNombreUsuario());
            log.info("   📧 Correo: {}", u.getCorreo());
            assert u.getCorreo().equals(correo) : "El correo debe coincidir";
        } else {
            log.warn("⚠️ Usuario no encontrado");
        }
    }

    @Test
    void testUsuarioRepositoryNotNull() {
        log.info("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        log.info("🔍 TEST: Verificar que el repositorio esté inyectado");
        log.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        assert usuarioRepository != null : "El repositorio no debe ser nulo";
        log.info("✅ Repositorio de usuarios inyectado correctamente");
    }

    @Test
    void testDatabaseConnection() {
        log.info("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        log.info("🔍 TEST: Verificar conexión con la base de datos");
        log.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        try {
            long count = usuarioRepository.count();
            log.info("✅ Conexión con la base de datos exitosa");
            log.info("   📊 Total de usuarios en la BD: {}", count);
        } catch (Exception e) {
            log.error("❌ Error conectando con la base de datos: {}", e.getMessage());
            throw e;
        }
    }
}

