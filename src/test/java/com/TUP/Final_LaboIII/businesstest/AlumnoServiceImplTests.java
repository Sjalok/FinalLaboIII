package com.TUP.Final_LaboIII.businesstest;

import com.TUP.Final_LaboIII.business.AsignaturaService;
import com.TUP.Final_LaboIII.business.CarreraService;
import com.TUP.Final_LaboIII.business.MateriaService;
import com.TUP.Final_LaboIII.business.exception.NotFoundException;
import com.TUP.Final_LaboIII.business.impl.AlumnoServiceImpl;
import com.TUP.Final_LaboIII.model.Alumno;
import com.TUP.Final_LaboIII.model.Asignatura;
import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.model.dto.AlumnoDto;
import com.TUP.Final_LaboIII.persistence.AlumnoDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class AlumnoServiceImplTests {
    @Mock
    private AlumnoDao alumnoDao;

    @Mock
    private AsignaturaService asignaturaService;

    @Mock
    private MateriaService materiaService;

    @Mock
    private CarreraService carreraService;

    @InjectMocks
    private AlumnoServiceImpl alumnoService;

    private Alumno alumno;

    @BeforeEach
    void setUp() {
        alumno = new Alumno();
        alumno.setId(1);
        alumno.setNombre("Juan");
        alumno.setApellido("Perez");
        alumno.setDni(12345678L);
        alumno.setCarrera("Ingenieria");
        alumno.setAsignaturas(new ArrayList<>());
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAlumnoXIdFallido() {
        when(alumnoDao.loadAlumnoId(1)).thenReturn(alumno);
        assertThrows(NotFoundException.class, () -> alumnoService.getAlumnoXId(1));
    }

    @Test
    void testCrearAlumno() {
        AlumnoDto alumnoDto = new AlumnoDto("Juan", "Perez", 12345678L, "Ingenieria");
        when(carreraService.carreraExist("Ingenieria")).thenReturn(true);
        when(alumnoDao.IDMasAlto()).thenReturn(0);
        String result = alumnoService.crearAlumno(alumnoDto);
        assertEquals("El alumno se creo correctamente con el ID 0", result);
    }

    @Test
    void testCambiarEstadoFallido() {
        when(materiaService.getMateriaXNombre("Matemática")).thenReturn(new Materia("Matemática"));
        when(alumnoDao.loadAlumnoId(1)).thenReturn(alumno);
        when(asignaturaService.checkEstado(1, "Matemática", "aprobado")).thenReturn(true);
        assertThrows(NotFoundException.class, () -> alumnoService.cambiarEstado(1, "Matemática", "aprobado"));
    }

    @Test
    void testBorrarAlumnoFallido() {
        when(alumnoDao.findById(1)).thenReturn(true);
        when(alumnoDao.deleteAlumno(1)).thenReturn(alumno);
        assertThrows(NotFoundException.class, () -> alumnoService.borrarAlumno(1));
    }

    @Test
    void testInscribirseAMateriaFallido() {
        when(alumnoDao.loadAlumnoId(1)).thenReturn(alumno);
        when(asignaturaService.nuevaAsignatura(alumno, "Matemática")).thenReturn(alumno);
        when(asignaturaService.checkCorrelatividades(1, "Matemática", "nueva")).thenReturn(true);
        when(alumnoDao.saveAlumno(1, alumno)).thenReturn(alumno);
        assertThrows(NotFoundException.class, () -> alumnoService.inscribirseAMateria(1, "Matemática"));
    }

    @Test
    void testGetTodasMateriasFallido() {
        when(alumnoDao.loadAsignaturasID(1)).thenReturn(List.of(101, 102));
        when(asignaturaService.listaMaterias(1)).thenReturn(new ArrayList<>());
        assertThrows(NotFoundException.class, () -> alumnoService.getTodasMaterias(1));
    }
}
