package com.TUP.Final_LaboIII.controller;

import com.TUP.Final_LaboIII.business.MateriaService;
import com.TUP.Final_LaboIII.business.exception.NumberFormatException;
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
        if (!nombremateria.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\sIVXLCDM]+$")) {
            throw new BadRequestException("El nombre de la materia solo puede contener letras y espacios, sin números ni caracteres especiales.");
        }
        return materiaService.getMateriaXNombre(nombremateria);
    }

    @GetMapping("/order") // investigar que iria en el requestbody aca para tomar por codigo asc o desc Y nombre asc o desc
    public List<Materia> getMateriasOrdenadas(@RequestParam(defaultValue = "nombre_asc") String order) {
        if (order != "nombre_desc" && order != "nombre_asc" && order != "codigo_asc" && order != "codigo_desc") {
            throw new BadRequestException("El orden el cual se pueden ordenar las materias es: nombre_asc, nombre_desc, codigo_asc o codigo_desc.");
        }
        return materiaService.getMateriasOrdenadas(order);
    }

    @PostMapping
    public Materia crearMateria(@RequestBody MateriaDto materiadto){
        if (!materiadto.getNombre().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\sIVXLCDM]+$")) {
            throw new BadRequestException("El nombre de la materia solo puede contener letras y espacios, sin números ni caracteres especiales.");
        }
        return materiaService.crearMateria(materiadto);
    }

    @PutMapping("/{nombremateria}")
    public Materia modificarProfesorMateria(@RequestBody MateriaDto materiadto, @RequestParam String nuevoprofesor){
            if (!nuevoprofesor.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$")) {
                throw new BadRequestException("El nombre y apellido del nuevo profesor solo puede contener letras.");
            }

            if (!materiadto.getNombre().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\sIVXLCDM]+$")) {
                throw new BadRequestException("El nombre de la materia solo puede contener letras y espacios, sin números ni caracteres especiales.");
            }

            return materiaService.saveProfesorMateria(materiadto, nuevoprofesor);
    }

    @PutMapping("/{nombremateria}")
    public Materia modificarCorrelatividadMateria(@RequestBody MateriaDto materiadto, @RequestParam String correlatividad, @RequestParam String accion){
        if (accion != "agregar" && accion != "eliminar") {
            throw new BadRequestException("La accion solo puede ser agregar o eliminar.");
        }

        if (!materiadto.getNombre().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\sIVXLCDM]+$")) {
            throw new BadRequestException("El nombre de la materia solo puede contener letras y espacios, sin números ni caracteres especiales.");
        }

        return materiaService.saveCorrelatividadMateria(materiadto, correlatividad, accion);
    }

    @DeleteMapping("/{idmateria}")
    public Materia borrarMateria(@PathVariable String codigoMateria){
        try {
            int id = Integer.parseInt(codigoMateria);
            return materiaService.deleteMateria(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El ID de la materia debe contener solo números.");
        }
    }
}
