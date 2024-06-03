package com.TUP.Final_LaboIII.controller;

import com.TUP.Final_LaboIII.business.MateriaService;
import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.model.dto.MateriaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping("/{idmateria}")
    public Materia getMateriaXId(@PathVariable int codigomateria) {
        return materiaService.getMateriaXId(codigomateria);
    }

    @GetMapping("/{nombremateria}")
    public Materia getMateriaXNombre(@PathVariable String nombremateria){
        return materiaService.getMateriaXNombre(nombremateria);
    }

    @PostMapping
    public Materia crearMateria(MateriaDto materiadto){
        return materiaService.crearMateria(materiadto);
    }

    @PutMapping("/{idmateria}")
    public Materia modificarMateria(@PathVariable int codigomateria, @RequestBody Materia materia){
        Materia materiaAEncontrar = materiaService.findById(codigomateria);
        return materiaService.saveMateria(materia, materiaAEncontrar);
    }

    @DeleteMapping("/{idmateria}")
    public Materia borrarMateria(@PathVariable int codigoMateria){
        return materiaService.deleteMateria(codigoMateria);
    }
}
