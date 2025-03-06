package com.TUP.Final_LaboIII.controllertest;

import com.TUP.Final_LaboIII.business.CarreraService;
import com.TUP.Final_LaboIII.controller.CarreraController;
import com.TUP.Final_LaboIII.model.Carrera;
import com.TUP.Final_LaboIII.model.dto.CarreraDto;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CarreraController.class)
public class CarreraControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarreraService carreraService;

    @InjectMocks
    private CarreraController carreraController;

    @Test
    void testCrearCarrera_DevuelveMensaje() throws Exception {
        when(carreraService.crearCarrera(any(CarreraDto.class))).thenReturn("Carrera creada correctamente");

        String json = """
                {
                  "nombre": "Ingeniería Informatica",
                  "departamento": 3,
                  "codigoCarrera": 101,
                  "cantCuatrimestres": 8
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/carrera")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Carrera creada correctamente"));
    }

    @Test
    void testGetCarrera_DevuelveCarrera() throws Exception {
        Carrera carrera = new Carrera("Ingeniería Informatica", 101, 8, 3);
        when(carreraService.getCarrera(101)).thenReturn(carrera);

        mockMvc.perform(MockMvcRequestBuilders.get("/carrera/101"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                          "nombre": "Ingeniería Informatica",
                          "codigoCarrera": 101,
                          "departamento": 8,
                          "cantCuatrimestres": 3,
                          "listaMaterias": []
                        }
                        """));
    }

    @Test
    void testBorrarCarrera_DevuelveMensaje() throws Exception {
        when(carreraService.borrarCarrera(101)).thenReturn("Carrera eliminada correctamente");

        mockMvc.perform(delete("/carrera/101"))
                .andExpect(status().isOk())
                .andExpect(content().string("Carrera eliminada correctamente"));
    }

    @Test
    void cuandoAccionEsInvalida_entoncesDevuelveBadRequest() throws Exception {
        mockMvc.perform(put("/carrera/101")
                        .param("nombremateria", "Fisica cuantica")
                        .param("accion", "modificar"))
                .andExpect(status().isBadRequest());
    }
}