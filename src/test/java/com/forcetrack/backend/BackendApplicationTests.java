package com.forcetrack.backend;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Tests básicos de la aplicación Spring Boot
 * 
 * Los logs se mostrarán en la consola cuando ejecutes:
 * ./mvnw test
 */
@SpringBootTest
class BackendApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(BackendApplicationTests.class);
	private static final String SEPARATOR = "═══════════════════════════════════════════════════════";

	@Test
	void contextLoads() {
		log.info(SEPARATOR);
		log.info("🔧 TEST: Cargar contexto de Spring Boot");
		log.info(SEPARATOR);
		log.info("✅ Contexto cargado correctamente");
		log.info(SEPARATOR);
	}

}
