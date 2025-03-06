package com.TUP.Final_LaboIII.persistencetest;

import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.persistence.impl.MateriaDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MateriaDaoImplTests {
    private MateriaDaoImpl materiaDao;
    private Map<Integer, Materia> repositorioPrivado;

    @BeforeEach
    void setUp() {
        repositorioPrivado = new HashMap<>();
        setRepositorioPrivadoEnMateriaDaoImpl(repositorioPrivado);
        materiaDao = new MateriaDaoImpl();
    }

    private void setRepositorioPrivadoEnMateriaDaoImpl(Map<Integer, Materia> repositorio) {
        try {
            Field field = MateriaDaoImpl.class.getDeclaredField("repositorioMaterias");
            field.setAccessible(true);
            field.set(null, repositorio);
        } catch (Exception e) {
            throw new RuntimeException("Error al configurar el repositorio privado", e);
        }
    }

    @Test
    void testNewMateria() {
        Materia materia = new Materia("Matematicas");
        Materia newMateria = materiaDao.newMateria(materia);

        assertNull(newMateria);
        assertTrue(repositorioPrivado.containsValue(materia));
        assertEquals(1, repositorioPrivado.size());
    }

    @Test
    void testDeleteMateria() {
        Materia materia = new Materia("Fisica");
        repositorioPrivado.put(10, materia);

        Materia deletedMateria = materiaDao.deleteMateria(10);

        assertNotNull(deletedMateria);
        assertEquals(materia, deletedMateria);
        assertFalse(repositorioPrivado.containsKey(10));
    }

    @Test
    void testLoadMateriaNombre() {
        Materia materia = new Materia("Quimica");
        repositorioPrivado.put(20, materia);

        Materia loadedMateria = materiaDao.loadMateriaNombre("Quimica");

        assertNotNull(loadedMateria);
        assertEquals(materia, loadedMateria);
    }

    @Test
    void testFindByCode() {
        Materia materia = new Materia("Biologia");
        repositorioPrivado.put(30, materia);

        assertTrue(materiaDao.findByCode(30));
        assertFalse(materiaDao.findByCode(99));
    }

    @Test
    void testSaveMateria() {
        Materia materia = new Materia("Historia");
        repositorioPrivado.put(40, materia);

        Materia savedMateria = materiaDao.cambiarNombreMateria(materia, "Historia Moderna");

        assertNotNull(savedMateria);
        assertEquals("Historia Moderna", savedMateria.getNombre());
    }

    @Test
    void testCambiarNombreMateria() {
        Materia materia = new Materia("Geografia");
        repositorioPrivado.put(50, materia);

        Materia updatedMateria = materiaDao.cambiarNombreMateria(materia, "Geografia Politica");

        assertNotNull(updatedMateria);
        assertEquals("Geografia Politica", updatedMateria.getNombre());
    }

    @Test
    void testGetTodasLasMaterias() {
        Materia materia1 = new Materia("Arte");
        Materia materia2 = new Materia("Musica");
        repositorioPrivado.put(60, materia1);
        repositorioPrivado.put(70, materia2);

        Map<Integer, Materia> todasLasMaterias = materiaDao.getTodasLasMaterias();

        assertNotNull(todasLasMaterias);
        assertEquals(2, todasLasMaterias.size());
    }
}
