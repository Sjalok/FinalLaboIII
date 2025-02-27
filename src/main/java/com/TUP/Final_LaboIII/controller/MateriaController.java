package com.TUP.Final_LaboIII.controller;

import com.TUP.Final_LaboIII.business.MateriaService;
import com.TUP.Final_LaboIII.controller.exception.BadRequestException;
import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.model.dto.MateriaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping("/{nombremateria}")
    public Materia getMateriaXNombre(@PathVariable String nombremateria){
        return materiaService.getMateriaXNombre(nombremateria);
    }

    @GetMapping("/order") // investigar que iria en el requestbody aca para tomar por codigo asc o desc Y nombre asc o desc
    public List<Materia> getMateriasOrdenadas(@RequestParam(defaultValue = "nombre_desc") String order) {
        return materiaService.getMateriasOrdenadas(order);
    }

    @PostMapping
    public Materia crearMateria(@RequestBody MateriaDto materiadto){
        return materiaService.crearMateria(materiadto);
    }

    @PutMapping("/{idmateria}")
    public Materia modificarMateria(@PathVariable Integer idmateria, @RequestBody Materia materia){
        if (materia.getMateriaId() != idmateria) {
            throw new BadRequestException("El ID de la materia en la URL y en el body no coinciden.");
        }
        return materiaService.saveMateria(materia);
    }

    @DeleteMapping("/{idmateria}")
    public Materia borrarMateria(@PathVariable int codigoMateria){
        return materiaService.deleteMateria(codigoMateria);
    }
}
