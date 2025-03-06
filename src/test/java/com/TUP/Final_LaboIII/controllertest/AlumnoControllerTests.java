package com.TUP.Final_LaboIII.controllertest;

import com.TUP.Final_LaboIII.business.AlumnoService;
import com.TUP.Final_LaboIII.controller.AlumnoController;
import com.TUP.Final_LaboIII.controller.exception.BadRequestException;
import com.TUP.Final_LaboIII.model.Alumno;
import com.TUP.Final_LaboIII.model.Asignatura;
import com.TUP.Final_LaboIII.model.EstadoAsignatura;
import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.model.dto.AlumnoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.beans.Beans;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AlumnoController.class)
public class AlumnoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlumnoService alumnoService;

    @InjectMocks
    private AlumnoController alumnoController;

    @Test
    void testGetAlumnoXId_DevuelveAlumno() throws Exception {
        Alumno alumno = new Alumno(1, "Juan", "Pérez", 12345678L, "Ingenieria");
        when(alumnoService.getAlumnoXId(1)).thenReturn(alumno);

        mockMvc.perform(get("/alumno/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                          "id": 1,
                          "nombre": "Juan",
                          "apellido": "Pérez",
                          "dni": 12345678,
                          "carrera": "Ingenieria"
                        }
                        """));
    }

    @Test
    void testCrearAlumno_DevuelveMensaje() throws Exception {
        when(alumnoService.crearAlumno(any(AlumnoDto.class))).thenReturn("Alumno creado correctamente");

        String json = """
                {
                  "nombre": "María",
                  "apellido": "Gómez",
                  "dni": 87654321,
                  "carrera": "Arquitectura"
                }
                """;

        mockMvc.perform(post("/alumno")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Alumno creado correctamente"));
    }

    @Test
    void testBorrarAlumno_ConIdInvalido_DeberiaLanzarExcepcion() throws Exception {
        String idInvalido = "abc";

        mockMvc.perform(delete("/alumno/{idalumno}", idInvalido))
                .andExpect(result -> {
                    assertTrue(result.getResolvedException() instanceof java.lang.NumberFormatException);
                    assertEquals("El ID del alumno debe contener solo números.",
                            result.getResolvedException().getMessage());
                })
                .andExpect(status().isBadRequest());
    }

    @Test
    void testVerTodasLasMaterias_ConIdValido_DeberiaRetornarListaDeMaterias() throws Exception {
        Materia materia1 = new Materia("Fisica cuantica");
        Materia materia2 = new Materia("Procesamiento de datos");

        List<Asignatura> materias = Arrays.asList(
                new Asignatura(materia1, EstadoAsignatura.CURSANDO, 0,1),
                new Asignatura(materia2,EstadoAsignatura.CURSANDO,0,1)
        );
        when(alumnoService.getTodasMaterias(1)).thenReturn(materias);


        mockMvc.perform(get("/alumno/{idalumno}/materias", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].materiaNombre").value("Fisica cuantica"))
                .andExpect(jsonPath("$[0].estado").value("CURSANDO"))
                .andExpect(jsonPath("$[1].materiaNombre").value("Procesamiento de datos"))
                .andExpect(jsonPath("$[1].estado").value("CURSANDO"));
    }

    @Test
    void testInscribirAMateria_ConNombreAsignaturaInvalido_DeberiaLanzarBadRequestException() throws Exception {
        String idAlumnoValido = "1";
        String nombreAsignaturaInvalido = "Matemáticas 2@";

        mockMvc.perform(put("/alumno/{idalumno}/{nombreasignatura}", idAlumnoValido, nombreAsignaturaInvalido))
                .andExpect(result -> {
                    // Verifica que se produzca una excepción y sea del tipo BadRequestException
                    assertTrue(result.getResolvedException() instanceof BadRequestException);
                    assertEquals("El nombre de la asignatura no debe contener caracteres especiales.",
                            result.getResolvedException().getMessage());
                })
                .andExpect(status().isBadRequest());
    }
}