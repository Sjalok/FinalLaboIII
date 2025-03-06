package com.TUP.Final_LaboIII.businesstest;

import com.TUP.Final_LaboIII.business.exception.NotFoundException;
import com.TUP.Final_LaboIII.business.impl.MateriaServiceImpl;
import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.model.dto.MateriaDto;
import com.TUP.Final_LaboIII.persistence.MateriaDao;
import com.TUP.Final_LaboIII.persistence.exception.YaExistenteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MateriaServiceImplTests {

    @Mock
    private MateriaDao materiaDao;

    @InjectMocks
    private MateriaServiceImpl materiaService;

    private Materia materia;

    @BeforeEach
    void setUp() {
        materia = new Materia("Matematicas");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMateriaXNombre() {
        when(materiaDao.loadMateriaNombre("Matematicas")).thenReturn(materia);

        assertThrows(NotFoundException.class, () -> materiaService.getMateriaXNombre("Matematicas"));
    }

    private void assertEquals(String matemáticas, String nombre) {
    }

    @Test
    void testSaveCorrelatividadMateria_AgregarFallido() {
        Materia correlativa = new Materia("Fisica");
        materia.setCorrelatividades(new ArrayList<>());

        when(materiaDao.getTodasLasMaterias()).thenReturn(Map.of(1, materia, 2, correlativa));
        when(materiaDao.loadMateriaNombre("Matematicas")).thenReturn(materia);
        when(materiaDao.loadMateriaNombre("Fisica")).thenReturn(correlativa);
        when(materiaDao.saveMateria(any(Materia.class))).thenReturn(materia);

        assertThrows(NotFoundException.class, () -> materiaService.saveCorrelatividadMateria("Matematicas", "Fisica", "agregar"));
    }

    @Test
    void testDeleteMateria_fallido() {
        when(materiaDao.findByCode(1)).thenReturn(true);
        when(materiaDao.deleteMateria(1)).thenReturn(materia);

        assertThrows(NotFoundException.class, () -> materiaService.deleteMateria(1));
    }
}
