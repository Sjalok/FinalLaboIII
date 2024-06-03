package com.TUP.Final_LaboIII.controller;

import com.TUP.Final_LaboIII.business.ProfesorService;
import com.TUP.Final_LaboIII.model.Profesor;
import com.TUP.Final_LaboIII.model.dto.ProfesorDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {

    ProfesorService profesorService;

    @GetMapping("/{dniprofesor}")
    public Profesor getProfesor(@PathVariable Long dni){
        return profesorService.getProfesor(dni);
    }

    @PostMapping
    public Profesor crearProfesor(@RequestBody ProfesorDto profesorDto){
        return profesorService.crearProfesor(profesorDto);
    }

    @DeleteMapping("/{dniprofesor}")
    public Profesor borrarProfesor(@PathVariable Long dni){
        return profesorService.borrarProfesor(dni);
    }
}
