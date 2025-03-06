package com.TUP.Final_LaboIII.controllertest;

import com.TUP.Final_LaboIII.business.MateriaService;
import com.TUP.Final_LaboIII.controller.MateriaController;
import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.model.dto.MateriaDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MateriaController.class)
@ExtendWith(SpringExtension.class)
public class MateriaControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MateriaService materiaService;

    @InjectMocks
    private MateriaController materiaController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getMateriaXNombre_CasoFeliz() throws Exception {
        List<Materia> correlatividades = Collections.emptyList();
        Materia materia = new Materia("Matematicas", correlatividades);
        when(materiaService.getMateriaXNombre("Matematicas")).thenReturn(materia);

        mockMvc.perform(get("/materia/Matematicas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Matematicas"))
                .andExpect(jsonPath("$.correlatividades").isEmpty());
    }

    @Test
    void getMateriaXNombre_NombreInvalido_DebeRetornarBadRequest() throws Exception {
        mockMvc.perform(get("/materia/Matematica123"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getMateriasOrdenadas_CasoFeliz() throws Exception {
        Map<Integer, Materia> materias = new HashMap<>();
        List<Materia> correlatividades = Collections.emptyList();
        materias.put(101, new Materia("Matemáticas", correlatividades));
        when(materiaService.getMateriasOrdenadas("nombre_asc")).thenReturn(materias);

        mockMvc.perform(get("/materia/order").param("order", "nombre_asc"))
                .andExpect(status().isOk());
    }

    @Test
    void getMateriasOrdenadas_OrdenInvalido_DebeRetornarBadRequest() throws Exception {
        mockMvc.perform(get("/materia/order").param("order", "random"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void crearMateria_CasoFeliz() throws Exception {
        MateriaDto materiaDto = new MateriaDto("Fisica");

        String json = objectMapper.writeValueAsString(materiaDto);

        when(materiaService.crearMateria(any(MateriaDto.class))).thenReturn("Se ha creado la materia correctamente.");

        mockMvc.perform(post("/materia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Se ha creado la materia correctamente."));
    }

    @Test
    void modificarCorrelatividadMateria_CasoFeliz() throws Exception {
        List<Materia> correlatividades = Collections.emptyList();
        Materia materia = new Materia("Fisica", correlatividades);
        when(materiaService.saveCorrelatividadMateria("Fisica", "Analisis matematico I", "agregar")).thenReturn(materia);

        mockMvc.perform(put("/materia/Física/correlativas")
                        .param("correlatividad", "Analisis matematico I")
                        .param("accion", "agregar"))
                .andExpect(status().isOk());
    }

    @Test
    void modificarCorrelatividadMateria_AccionInvalida_DebeRetornarBadRequest() throws Exception {
        mockMvc.perform(put("/materia/Física/correlativas")
                        .param("correlatividad", "Matemáticas")
                        .param("accion", "invalid"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void modificarNombreMateria_CasoFeliz() throws Exception {
        List<Materia> correlatividades = Collections.emptyList();
        Materia materia = new Materia("Algebra", correlatividades);
        when(materiaService.saveMateriaNombre("Matemáticas", "Algebra")).thenReturn(materia);

        mockMvc.perform(put("/materia/Matemáticas/Álgebra"))
                .andExpect(status().isOk());
    }

    @Test
    void borrarMateria_CasoFeliz() throws Exception {
        List<Materia> correlatividades = Collections.emptyList();
        Materia materia = new Materia("Química", correlatividades);
        when(materiaService.deleteMateria(104)).thenReturn(materia);

        mockMvc.perform(delete("/materia/104"))
                .andExpect(status().isOk());
    }
    @Test
    void borrarMateria_IdInvalido_DebeRetornarBadRequest() throws Exception {
        mockMvc.perform(delete("/materia/abc"))
                .andExpect(status().isBadRequest());
    }
}
