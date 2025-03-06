package com.TUP.Final_LaboIII.persistencetest;

import com.TUP.Final_LaboIII.model.Profesor;
import com.TUP.Final_LaboIII.persistence.impl.ProfesorDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ProfesorDaoImptTests {
    private ProfesorDaoImpl profesorDao;
    private Map<Integer, Profesor> repositorioPrivado;

    @BeforeEach
    void setUp() {
        repositorioPrivado = new HashMap<>();

        setRepositorioPrivadoEnProfesorDaoImpl(repositorioPrivado);

        profesorDao = new ProfesorDaoImpl();
    }

    private void setRepositorioPrivadoEnProfesorDaoImpl(Map<Integer, Profesor> repositorio) {
        try {
            Field field = ProfesorDaoImpl.class.getDeclaredField("repositorioProfesores");
            field.setAccessible(true);
            field.set(null, repositorio);
        } catch (Exception e) {
            throw new RuntimeException("Error al configurar el repositorio privado", e);
        }
    }

    @Test
    void testNewProfesor() {
        Profesor profesor = new Profesor(12345678L, "Juan", "Perez", "Doctor");
        Profesor newProfesor = profesorDao.newProfesor(profesor);

        assertNull(newProfesor);
        assertEquals(null, newProfesor);
        assertTrue(repositorioPrivado.containsValue(profesor));
        assertEquals(1, repositorioPrivado.size());
    }

    @Test
    void testLoadProfesor() {
        Profesor profesor = new Profesor(12345678L, "Juan", "Perez", "Doctor");
        repositorioPrivado.put(1, profesor);

        Profesor loadedProfesor = profesorDao.loadProfesor(12345678L);

        assertNotNull(loadedProfesor);
        assertEquals(profesor, loadedProfesor);
    }

    @Test
    void testFindByDni() {
        Profesor profesor = new Profesor(12345678L, "Juan", "Perez", "Doctor");
        repositorioPrivado.put(1, profesor);

        assertTrue(profesorDao.findByDni(12345678L));
        assertFalse(profesorDao.findByDni(87654321L));
    }

    @Test
    void testDeleteProfesor() {
        Profesor profesor = new Profesor(12345678L, "Juan", "Perez", "Doctor");
        repositorioPrivado.put(1, profesor);

        Profesor deletedProfesor = profesorDao.deleteProfesor(12345678L);

        assertNotNull(deletedProfesor);
        assertEquals(profesor, deletedProfesor);
        assertFalse(repositorioPrivado.containsValue(profesor));
    }

    @Test
    void testSaveProfesor() {
        Profesor profesor = new Profesor(12345678L, "Juan", "Perez", "Doctor");
        repositorioPrivado.put(1, profesor);

        Profesor updatedProfesor = new Profesor(12345678L, "Juan", "Perez", "Doctor");
        Profesor savedProfesor = profesorDao.saveProfesor(updatedProfesor);

        assertNotNull(savedProfesor);
        assertEquals(updatedProfesor, savedProfesor);
        assertEquals(updatedProfesor, repositorioPrivado.get(1));
    }
}
