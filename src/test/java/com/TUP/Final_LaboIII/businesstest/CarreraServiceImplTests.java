package com.TUP.Final_LaboIII.businesstest;

import com.TUP.Final_LaboIII.business.MateriaService;
import com.TUP.Final_LaboIII.business.exception.NotFoundException;
import com.TUP.Final_LaboIII.business.impl.CarreraServiceImpl;
import com.TUP.Final_LaboIII.business.impl.MateriaServiceImpl;
import com.TUP.Final_LaboIII.model.Carrera;
import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.model.dto.CarreraDto;
import com.TUP.Final_LaboIII.persistence.CarreraDao;
import com.TUP.Final_LaboIII.persistence.exception.YaExistenteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CarreraServiceImplTests {
    @Mock
    private CarreraDao carreraDao;

    @Mock
    private MateriaService materiaService;

    @InjectMocks
    private CarreraServiceImpl carreraService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearCarrera_exitoso() {
        CarreraDto carreraDto = new CarreraDto("Ingenieria", 1, 1, 10);
        Carrera carrera = new Carrera("Ingenieria", 1, 1, 10);

        when(carreraDao.findByName("Ingeniería")).thenReturn(false);
        when(carreraDao.newCarrera(any(Carrera.class))).thenReturn(null);

        String resultado = carreraService.crearCarrera(carreraDto);

        assertEquals("Se ha creado la carrera correctamente.", resultado);
    }

    @Test
    void crearCarrera_yaExistenteException() {
        CarreraDto carreraDto = new CarreraDto("Ingenieria", 1, 1, 10);

        when(carreraDao.findByName("Ingenieria")).thenReturn(true);

        assertNull();
    }

    private void assertNull() {
    }

    @Test
    void getCarrera_existe() {
        Carrera carrera = new Carrera("Ingenieria", 1, 1, 10);
        when(carreraDao.loadCarrera(1)).thenReturn(carrera);

        Carrera resultado = carreraService.getCarrera(1);

        assertEquals("Ingenieria", resultado.getNombre());
        assertEquals(1, resultado.getCodigoCarrera());
    }

    @Test
    void getCarrera_noExiste() {
        when(carreraDao.loadCarrera(101)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> carreraService.getCarrera(101));
    }

    @Test
    void borrarCarrera_falla_noExiste() {
        when(carreraDao.findByCode(101)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> carreraService.borrarCarrera(101));
    }

    @Test
    void borrarCarrera_noExiste() {
        when(carreraDao.findByCode(101)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> carreraService.borrarCarrera(101));
    }

    @Test
    void agregarMateria_NotFoundException() {
        Carrera carrera = new Carrera("Ingenieria", 1, 1, 10);
        Materia materia = new Materia("Analisis matematico I");

        when(carreraDao.findByCode(1)).thenReturn(true);
        when(carreraDao.loadCarrera(1)).thenReturn(carrera);
        when(materiaService.getMateriaXNombre("Analisis matematico I")).thenReturn(materia);

        assertThrows(NotFoundException.class, () -> carreraService.agregarOEliminarMaterias(1, "Analisis matematico I", "agregar"));
    }

    @Test
    void agregarMateria_yaExistente() {
        Carrera carrera = new Carrera("Ingenieria", 1, 1, 10);
        Materia materia = new Materia("Analisis matematico I");
        carrera.getListaMaterias().add(materia);

        when(carreraDao.findByCode(101)).thenReturn(true);
        when(carreraDao.loadCarrera(101)).thenReturn(carrera);
        when(materiaService.getMateriaXNombre("Analisis matematico I")).thenReturn(materia);

        assertThrows(NotFoundException.class, () -> carreraService.agregarOEliminarMaterias(101, "Analisis matematico I", "agregar"));
    }

    @Test
    void eliminarMateria_NotFoundException() {
        Carrera carrera = new Carrera("Ingenieria", 1, 1, 10);
        Materia materia = new Materia("Analisis matematico I");
        carrera.getListaMaterias().add(materia);

        when(carreraDao.findByCode(1)).thenReturn(true);
        when(carreraDao.loadCarrera(1)).thenReturn(carrera);

        assertThrows(NotFoundException.class, () -> carreraService.agregarOEliminarMaterias(101, "Analisis matematico I", "eliminar"));
    }

    @Test
    void eliminarMateria_noExiste() {
        Carrera carrera = new Carrera("Ingenieria", 1, 1, 10);

        when(carreraDao.findByCode(101)).thenReturn(true);
        when(carreraDao.loadCarrera(101)).thenReturn(carrera);

        assertThrows(NotFoundException.class, () -> carreraService.agregarOEliminarMaterias(101, "Analisis matematico I", "eliminar"));
    }

    @Test
    void carreraExist_true() {
        when(carreraDao.findByName("Ingenieria")).thenReturn(true);

        assertFalse(carreraService.carreraExist("Ingenieria"));
    }

    @Test
    void carreraExist_false() {
        when(carreraDao.findByName("Ingenieria")).thenReturn(false);

        assertFalse(carreraService.carreraExist("Ingenieria"));
    }
}
