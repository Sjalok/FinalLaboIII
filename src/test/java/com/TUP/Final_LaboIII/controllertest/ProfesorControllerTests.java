package com.TUP.Final_LaboIII.controllertest;


import com.TUP.Final_LaboIII.business.ProfesorService;
import com.TUP.Final_LaboIII.controller.AlumnoController;
import com.TUP.Final_LaboIII.controller.ProfesorController;
import com.TUP.Final_LaboIII.model.Profesor;
import com.TUP.Final_LaboIII.model.dto.ProfesorDto;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ProfesorController.class)
public class ProfesorControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfesorService profesorService;

    @InjectMocks
    private ProfesorController profesorController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getProfesor_CasoFeliz() throws Exception {
        Profesor profesor = new Profesor(12345678L, "Desidirio", "Tranquel", "Doctor");
        when(profesorService.getProfesor(12345678L)).thenReturn(profesor);

        mockMvc.perform(get("/profesor/12345678"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dni").value(12345678L))
                .andExpect(jsonPath("$.nombre").value("Desidirio"))
                .andExpect(jsonPath("$.apellido").value("Tranquel"))
                .andExpect(jsonPath("$.titulo").value("Doctor"));
    }

    @Test
    void crearProfesor_CasoFeliz() throws Exception {
        ProfesorDto profesorDto = new ProfesorDto("Desidirio", "Tranquel", 12345678L, "Doctor");
        String json = objectMapper.writeValueAsString(profesorDto);

        when(profesorService.crearProfesor(any())).thenReturn("Profesor creado correctamente");

        mockMvc.perform(post("/profesor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Profesor creado correctamente"));
    }

    @Test
    void borrarProfesor_CasoFeliz() throws Exception {
        when(profesorService.borrarProfesor(12345678L)).thenReturn("Profesor eliminado correctamente");

        mockMvc.perform(delete("/profesor/12345678"))
                .andExpect(status().isOk())
                .andExpect(content().string("Profesor eliminado correctamente"));
    }

    @Test
    void agregarOEliminarMaterias_CasoFeliz() throws Exception {
        Profesor profesor = new Profesor(12345678L, "Desidirio", "Tranquel", "Doctor");
        when(profesorService.agregarOEliminarMateria(12345678L, "Fisica", "agregar")).thenReturn(profesor);

        mockMvc.perform(put("/profesor/12345678")
                        .param("nombremateria", "Fisica")
                        .param("accion", "agregar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dni").value(12345678L))
                .andExpect(jsonPath("$.nombre").value("Desidirio"))
                .andExpect(jsonPath("$.apellido").value("Tranquel"))
                .andExpect(jsonPath("$.titulo").value("Doctor"));
    }

    @Test
    void agregarOEliminarMaterias_NombreMateriaInvalido_DebeRetornarBadRequest() throws Exception {
        mockMvc.perform(put("/profesor/12345678")
                        .param("nombremateria", "Matemática123")
                        .param("accion", "agregar"))
                .andExpect(status().isBadRequest());
    }

}
