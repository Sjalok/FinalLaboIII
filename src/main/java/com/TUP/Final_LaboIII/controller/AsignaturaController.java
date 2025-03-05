package com.TUP.Final_LaboIII.controller;

import com.TUP.Final_LaboIII.business.AsignaturaService;
import com.TUP.Final_LaboIII.model.Asignatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/asignaturas")
public class AsignaturaController {
    @Autowired
    AsignaturaService asignaturaService;

    @GetMapping
    public Map<Integer, Asignatura> getTodasAsignaturas() {
        return asignaturaService.todasLasAsignaturas();
    }
}
