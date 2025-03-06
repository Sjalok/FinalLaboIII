package com.TUP.Final_LaboIII.businesstest;

import com.TUP.Final_LaboIII.business.AlumnoService;
import com.TUP.Final_LaboIII.business.MateriaService;
import com.TUP.Final_LaboIII.business.exception.NotFoundException;
import com.TUP.Final_LaboIII.business.impl.AsignaturaServiceImpl;
import com.TUP.Final_LaboIII.model.Alumno;
import com.TUP.Final_LaboIII.model.Asignatura;
import com.TUP.Final_LaboIII.model.EstadoAsignatura;
import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.persistence.AsignaturaDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AsignaturaServiceImplTests {
    @Mock
    private AsignaturaDao asignaturaDao;

    @Mock
    private MateriaService materiaService;

    @Mock
    private AlumnoService alumnoService;

    @InjectMocks
    private AsignaturaServiceImpl asignaturaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testNuevaAsignatura_Success() {
        Alumno alumno = new Alumno();
        alumno.setId(1);
        alumno.setAsignaturas(new ArrayList<>());
        String materiaNombre = "Matematica";

        Materia materia = new Materia();
        materia.setNombre(materiaNombre);

        when(materiaService.existeMateria(materiaNombre)).thenReturn(true);
        when(materiaService.getMateriaXNombre(materiaNombre)).thenReturn(materia);
        when(asignaturaDao.newAsignatura(any(Asignatura.class))).thenReturn(100);
        when(asignaturaDao.loadAsignaturasid(alumno.getId())).thenReturn(Collections.emptyList());

        Alumno resultado = asignaturaService.nuevaAsignatura(alumno, materiaNombre);

        assertNotNull(resultado);
        assertFalse(resultado.getAsignaturas().isEmpty());
    }

    @Test
    void testNuevaAsignatura_MateriaNoExiste() {
        when(materiaService.existeMateria("Matematica"))
                .thenReturn(false);

        Alumno alumno = new Alumno();
        alumno.setId(1);

        assertThrows(NotFoundException.class,
                () -> asignaturaService.nuevaAsignatura(alumno, "Matematica"));
    }

    @Test
    void testCheckCorrelatividades_Success() {
        Materia materia = new Materia();
        materia.setNombre("Fisica");
        List<Materia> correlatividades = new ArrayList<>();
        Materia correlativa = new Materia();
        correlativa.setNombre("Matematica");
        correlatividades.add(correlativa);
        materia.setCorrelatividades(correlatividades);

        Asignatura asignatura = new Asignatura();
        asignatura.setMateria(correlativa);
        asignatura.setEstado(EstadoAsignatura.APROBADA);
        List<Asignatura> asignaturasAlumno = List.of(asignatura);

        when(materiaService.getMateriaXNombre("Fisica")).thenReturn(materia);
        when(alumnoService.getAlumnoXId(1)).thenReturn(new Alumno());
        when(asignaturaDao.loadAsignaturasid(1)).thenReturn(asignaturasAlumno);

        boolean resultado = asignaturaService.checkCorrelatividades(1, "Fisica", "nueva");
        assertFalse(resultado);
    }

    @Test
    void testCheckEstado_CambioInvalido() {
        Alumno alumno = new Alumno();
        alumno.setId(1);

        Asignatura asignatura = new Asignatura();
        Materia materia = new Materia();
        materia.setNombre("Matematica");
        asignatura.setMateria(materia);
        asignatura.setEstado(EstadoAsignatura.CURSANDO);

        when(alumnoService.getAlumnoXId(1)).thenReturn(alumno);
        when(asignaturaDao.loadAsignaturasid(1)).thenReturn(List.of(asignatura));

        boolean resultado = asignaturaService.checkEstado(1, "Matematica", "APROBADA");
        assertFalse(resultado);
    }

}
