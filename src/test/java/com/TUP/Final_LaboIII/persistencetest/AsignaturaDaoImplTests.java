package com.TUP.Final_LaboIII.persistencetest;

import com.TUP.Final_LaboIII.model.Alumno;
import com.TUP.Final_LaboIII.model.Asignatura;
import com.TUP.Final_LaboIII.model.EstadoAsignatura;
import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.persistence.impl.AsignaturaDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AsignaturaDaoImplTests {
    private AsignaturaDaoImpl asignaturaDao;
    private Map<Integer, Asignatura> repositorioPrivado;

    @BeforeEach
    void setUp() {
        repositorioPrivado = new HashMap<>();
        setRepositorioPrivadoEnAsignaturaDaoImpl(repositorioPrivado);
        asignaturaDao = new AsignaturaDaoImpl();
    }

    private void setRepositorioPrivadoEnAsignaturaDaoImpl(Map<Integer, Asignatura> repositorio) {
        try {
            Field field = AsignaturaDaoImpl.class.getDeclaredField("repositorioAsignaturas");
            field.setAccessible(true);
            field.set(null, repositorio);
        } catch (Exception e) {
            throw new RuntimeException("Error al configurar el repositorio privado", e);
        }
    }

    @Test
    void testNewAsignatura() {
        Materia materia1 = new Materia("Histora Moderna");
        Asignatura asignatura = new Asignatura(materia1, EstadoAsignatura.CURSADA,0,1);
        Integer idGenerado = asignaturaDao.newAsignatura(asignatura);

        assertNotNull(idGenerado);
        assertTrue(repositorioPrivado.containsValue(asignatura));
        assertEquals(1, repositorioPrivado.size());
    }

    @Test
    void testSaveAsignatura() {
        Materia materia1 = new Materia("Histora Moderna");
        Asignatura asignatura = new Asignatura(materia1, EstadoAsignatura.CURSADA,0,1);
        repositorioPrivado.put(1, asignatura);

        Asignatura updatedAsignatura = new Asignatura(materia1, EstadoAsignatura.APROBADA,0,1);
        Asignatura savedAsignatura = asignaturaDao.saveAsignatura(1, updatedAsignatura);

        assertNotNull(savedAsignatura);
        assertEquals(EstadoAsignatura.APROBADA, savedAsignatura.getEstado());
    }

    @Test
    void testLoadAsignaturasid() {
        Materia materia1 = new Materia("Histora Moderna");
        Asignatura asignatura1 = new Asignatura(materia1, EstadoAsignatura.CURSADA,0,1);
        Materia materia2 = new Materia("Histora Moderna II");
        Asignatura asignatura2 = new Asignatura(materia2, EstadoAsignatura.CURSADA,0,1);
        repositorioPrivado.put(1, asignatura1);
        repositorioPrivado.put(2, asignatura2);

        List<Asignatura> asignaturas = asignaturaDao.loadAsignaturasid(1);

        assertNotNull(asignaturas);
        assertEquals(2, asignaturas.size());
    }

    @Test
    void testDeleteAlumnoAsignaturas() {
        Materia materia1 = new Materia("Histora Moderna");
        Asignatura asignatura1 = new Asignatura(materia1, EstadoAsignatura.CURSADA,0,1);
        Materia materia2 = new Materia("Histora Moderna II");
        Asignatura asignatura2 = new Asignatura(materia2, EstadoAsignatura.CURSADA,0,1);
        repositorioPrivado.put(1, asignatura1);
        repositorioPrivado.put(2, asignatura2);

        asignaturaDao.deleteAlumnoAsignaturas(1);

        assertTrue(repositorioPrivado.isEmpty());
    }
}
