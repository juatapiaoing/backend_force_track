-- Eliminar tablas obsoletas
DROP TABLE IF EXISTS semanas CASCADE;

-- Tabla de Usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    nombre_usuario VARCHAR(255) NOT NULL UNIQUE,
    correo VARCHAR(255) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Bloques (Entrenamiento)
CREATE TABLE IF NOT EXISTS bloques (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(100),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_bloque_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE
);

-- Tabla de Días (Sesiones)
-- Se elimina la tabla intermedia 'semanas'. Los días pertenecen directamente al bloque.
CREATE TABLE IF NOT EXISTS dias (
    id SERIAL PRIMARY KEY,
    bloque_id INTEGER NOT NULL,
    nombre VARCHAR(255) NOT NULL, -- Ej: "Miércoles 26 de Noviembre de 2025"
    notas TEXT,
    fecha TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    numero_semana INTEGER DEFAULT 1, -- Mantenido por compatibilidad o agrupación lógica futura
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_dia_bloque FOREIGN KEY (bloque_id) REFERENCES bloques(id) ON DELETE CASCADE
);

-- Tabla de Ejercicios
CREATE TABLE IF NOT EXISTS ejercicios (
    id SERIAL PRIMARY KEY,
    dia_id INTEGER NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    descanso_segundos INTEGER DEFAULT 90,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_ejercicio_dia FOREIGN KEY (dia_id) REFERENCES dias(id) ON DELETE CASCADE
);

-- Tabla de Series
CREATE TABLE IF NOT EXISTS series (
    id SERIAL PRIMARY KEY,
    ejercicio_id INTEGER NOT NULL,
    peso DECIMAL(10, 2) DEFAULT 0.0,
    repeticiones INTEGER DEFAULT 0,
    rir INTEGER DEFAULT 0, -- Repetitions in Reserve
    completada BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_serie_ejercicio FOREIGN KEY (ejercicio_id) REFERENCES ejercicios(id) ON DELETE CASCADE
);

-- Índices para mejorar el rendimiento
CREATE INDEX IF NOT EXISTS idx_bloques_usuario_id ON bloques(usuario_id);
CREATE INDEX IF NOT EXISTS idx_dias_bloque_id ON dias(bloque_id);
CREATE INDEX IF NOT EXISTS idx_ejercicios_dia_id ON ejercicios(dia_id);
CREATE INDEX IF NOT EXISTS idx_series_ejercicio_id ON series(ejercicio_id);
