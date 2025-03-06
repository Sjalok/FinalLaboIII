package com.TUP.Final_LaboIII.businesstest;

import com.TUP.Final_LaboIII.business.MateriaService;
import com.TUP.Final_LaboIII.business.exception.NotFoundException;
import com.TUP.Final_LaboIII.business.impl.ProfesorServiceImpl;
import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.model.Profesor;
import com.TUP.Final_LaboIII.model.dto.ProfesorDto;
import com.TUP.Final_LaboIII.persistence.ProfesorDao;
import com.TUP.Final_LaboIII.persistence.exception.YaExistenteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProfesorServiceImplTests {

    @InjectMocks
    private ProfesorServiceImpl profesorService;

    @Mock
    private ProfesorDao profesorDao;

    @Mock
    private MateriaService materiaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearProfesor_exitoso() {
        ProfesorDto profesorDto = new ProfesorDto("Desidirio", "Perez", 1234L, "Doctor");
        Profesor profesor = new Profesor(12345L, "Juan", "Pérez", "Matemáticas");

        when(profesorDao.findByDni(12345L)).thenReturn(false);

        String resultado = profesorService.crearProfesor(profesorDto);

        assertEquals("Se ha creado al profesor correctamente.", resultado);
    }

    @Test
    void crearProfesor_fallaPorDniExistente() {
        ProfesorDto profesorDto = new ProfesorDto("Desidirio", "Perez", 1234L, "Doctor");

        when(profesorDao.findByDni(12345L)).thenReturn(true);

        verify(profesorDao, never()).newProfesor(any(Profesor.class));
    }

    @Test
    void borrarProfesor_fallido() {
        when(profesorDao.findByDni(12345L)).thenReturn(true);

        assertThrows(NotFoundException.class, () -> profesorService.borrarProfesor(12345L));
    }

    @Test
    void borrarProfesor_fallaPorNoExistir() {
        when(profesorDao.findByDni(12345L)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> profesorService.borrarProfesor(12345L));
        verify(profesorDao, never()).deleteProfesor(anyLong());
    }

    @Test
    void getProfesor_fallido() {
        Profesor profesor = new Profesor(12345L, "Juan", "Pérez", "Matemáticas");

        when(profesorDao.loadProfesor(12345L)).thenReturn(profesor);

        assertThrows(NotFoundException.class, () -> profesorService.getProfesor(12345L));
    }

    @Test
    void getProfesor_fallaPorNoExistir() {
        when(profesorDao.loadProfesor(12345L)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> profesorService.getProfesor(12345L));
    }

    @Test
    void eliminarMateria_fallido() {
        Profesor profesor = new Profesor(12345L, "Juan", "Pérez", "Matemáticas");
        Materia materia = new Materia("Álgebra");
        profesor.setMateriasDictadas(new ArrayList<>(List.of(materia)));

        when(profesorDao.findByDni(12345L)).thenReturn(true);
        when(profesorDao.loadProfesor(12345L)).thenReturn(profesor);
        when(profesorDao.saveProfesor(any(Profesor.class))).thenReturn(profesor);

        assertThrows(NotFoundException.class, () -> profesorService.agregarOEliminarMateria(12345L, "Álgebra", "eliminar"));
    }

    @Test
    void eliminarMateria_fallaPorNoExistir() {
        Profesor profesor = new Profesor(12345L, "Juan", "Pérez", "Matemáticas");
        profesor.setMateriasDictadas(new ArrayList<>());

        when(profesorDao.findByDni(12345L)).thenReturn(true);
        when(profesorDao.loadProfesor(12345L)).thenReturn(profesor);

        assertThrows(NotFoundException.class, () -> profesorService.agregarOEliminarMateria(12345L, "Álgebra", "eliminar"));
    }
}
