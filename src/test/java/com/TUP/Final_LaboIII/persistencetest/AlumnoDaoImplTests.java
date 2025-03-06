package com.TUP.Final_LaboIII.persistencetest;

import com.TUP.Final_LaboIII.model.Alumno;
import com.TUP.Final_LaboIII.model.Asignatura;
import com.TUP.Final_LaboIII.model.EstadoAsignatura;
import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.persistence.impl.AlumnoDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AlumnoDaoImplTests {
    private AlumnoDaoImpl alumnoDao;
    private Map<Long, Alumno> repositorioPrivado;

    @BeforeEach
    void setUp() {
        repositorioPrivado = new HashMap<>();

        setRepositorioPrivadoEnAlumnoDaoImpl(repositorioPrivado);

        alumnoDao = new AlumnoDaoImpl();
    }

    private void setRepositorioPrivadoEnAlumnoDaoImpl(Map<Long, Alumno> repositorio) {
        try {
            Field field = AlumnoDaoImpl.class.getDeclaredField("repositorioAlumnos");
            field.setAccessible(true);
            field.set(null, repositorio);
        } catch (Exception e) {
            throw new RuntimeException("Error al configurar el repositorio privado", e);
        }
    }

    @Test
    void testSaveAlumno() {
        Alumno alumno = new Alumno(1, "Juan", "Pérez", 12345678L, "Ingeniería");
        repositorioPrivado.put(1L,alumno);
        Alumno savedAlumno = alumnoDao.saveAlumno(1, alumno);

        assertNull(savedAlumno);
        assertEquals(null, savedAlumno);
        assertTrue(repositorioPrivado.containsValue(alumno));
    }

    @Test
    void testLoadAlumnoId() {
        Alumno alumno = new Alumno(1, "Juan", "Pérez", 12345678L, "Ingeniería");
        repositorioPrivado.put(1L, alumno);

        Alumno loadedAlumno = alumnoDao.loadAlumnoId(1);

        assertNotNull(loadedAlumno);
        assertEquals(alumno, loadedAlumno);
    }

    @Test
    void testFindByDni() {
        Alumno alumno = new Alumno(1, "Juan", "Pérez", 12345678L, "Ingeniería");
        repositorioPrivado.put(1L, alumno);

        assertTrue(alumnoDao.findByDni(12345678L));
        assertFalse(alumnoDao.findByDni(87654321L));
    }

    @Test
    void testNewAlumno() {
        Alumno alumno = new Alumno(1, "Juan", "Pérez", 12345678L, "Ingeniería");

        Alumno resultadoPut = alumnoDao.newAlumno(alumno);

        assertNull(resultadoPut);

        assertTrue(repositorioPrivado.containsValue(alumno));
        assertEquals(alumno, repositorioPrivado.get(12345678L));
    }

    @Test
    void testDeleteAlumno() {
        Alumno alumno = new Alumno(1, "Juan", "Pérez", 12345678L, "Ingeniería");
        repositorioPrivado.put(12345678L, alumno);

        Alumno deletedAlumno = alumnoDao.deleteAlumno(1);

        assertNotNull(deletedAlumno);
        assertEquals(alumno, deletedAlumno);

        assertFalse(repositorioPrivado.containsValue(alumno));
        assertFalse(repositorioPrivado.containsKey(12345678L));
    }
    @Test
    void testIDMasAlto() {
        Alumno alumno1 = new Alumno(1, "Juan", "Pérez", 12345678L, "Ingeniería");
        Alumno alumno2 = new Alumno(2, "Ana", "Gómez", 87654321L, "Abogacía");
        repositorioPrivado.put(1L, alumno1);
        repositorioPrivado.put(2L, alumno2);

        Integer idMasAlto = alumnoDao.IDMasAlto();

        assertNotNull(idMasAlto);
        assertEquals(3, idMasAlto);
    }
}
