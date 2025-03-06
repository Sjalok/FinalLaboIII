package com.TUP.Final_LaboIII.persistencetest;

import com.TUP.Final_LaboIII.model.Carrera;
import com.TUP.Final_LaboIII.persistence.impl.CarreraDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CarreraDaoImplTests {
    private CarreraDaoImpl carreraDao;
    private Map<Integer, Carrera> repositorioPrivado;

    @BeforeEach
    void setUp() {
        repositorioPrivado = new HashMap<>();

        setRepositorioPrivadoEnCarreraDaoImpl(repositorioPrivado);

        carreraDao = new CarreraDaoImpl();
    }

    private void setRepositorioPrivadoEnCarreraDaoImpl(Map<Integer, Carrera> repositorio) {
        try {
            Field field = CarreraDaoImpl.class.getDeclaredField("repositorioCarreras");
            field.setAccessible(true);
            field.set(null, repositorio);
        } catch (Exception e) {
            throw new RuntimeException("Error al configurar el repositorio privado", e);
        }
    }

    @Test
    void testNewCarrera() {
        Carrera carrera = new Carrera("Ingenieria en Baldosas", 55,10,10);
        Carrera newCarrera = carreraDao.newCarrera(carrera);

        assertNull(newCarrera);
        assertEquals(null, newCarrera);
        assertTrue(repositorioPrivado.containsValue(carrera));
        assertEquals(1, repositorioPrivado.size());
    }

    @Test
    void testDeleteCarrera() {
        Carrera carrera = new Carrera("Ingenieria en Baldosas", 55,10,10);
        repositorioPrivado.put(55, carrera);

        Carrera deletedCarrera = carreraDao.deleteCarrera(55);

        assertNotNull(deletedCarrera);
        assertEquals(carrera, deletedCarrera);
        assertFalse(repositorioPrivado.containsValue(carrera));
    }

    @Test
    void testLoadCarrera() {
        Carrera carrera = new Carrera("Ingenieria en Baldosas", 55,10,10);
        repositorioPrivado.put(1, carrera);

        Carrera loadedCarrera = carreraDao.loadCarrera(55);

        assertNotNull(loadedCarrera);
        assertEquals(carrera, loadedCarrera);
    }

    @Test
    void testFindByCode() {
        Carrera carrera = new Carrera("Ingenieria en Baldosas", 55,10,10);
        repositorioPrivado.put(1, carrera);

        assertTrue(carreraDao.findByCode(55));
        assertFalse(carreraDao.findByCode(2));
    }

    @Test
    void testFindByName() {
        Carrera carrera = new Carrera("Ingenieria en Baldosas", 55,10,10);
        repositorioPrivado.put(1, carrera);

        assertTrue(carreraDao.findByName("Ingenieria en Baldosas"));
        assertFalse(carreraDao.findByName("Licenciatura en casi todo"));
    }

    @Test
    void testMostrarTodasLasCarreras() {
        Carrera carrera1 = new Carrera("Ingenieria en Baldosas", 55,10,10);
        Carrera carrera2 = new Carrera("Ingenieria termonuclear", 56,11,18);
        repositorioPrivado.put(1, carrera1);
        repositorioPrivado.put(2, carrera2);

        HashMap<String, List<String>> todasLasCarreras = carreraDao.mostrarTodasLasCarreras();

        assertNotNull(todasLasCarreras);
        assertTrue(todasLasCarreras.containsKey("Carreras:"));
        assertEquals(2, todasLasCarreras.get("Carreras:").size());
    }

    /**@Test
    void testSaveCarrera() {
        Carrera carrera = new Carrera("Ingenieria en Baldosas", 55,10,10);
        repositorioPrivado.put(55, carrera);

        Carrera updatedCarrera = new Carrera("Ingenieria en Baldosas y Mamposteria", 55,10,10);
        Carrera savedCarrera = carreraDao.saveCarrera(55, updatedCarrera);

        assertNotNull(savedCarrera);
        assertEquals(updatedCarrera, savedCarrera);
        assertEquals(updatedCarrera, repositorioPrivado.get(1));
    } */
}
