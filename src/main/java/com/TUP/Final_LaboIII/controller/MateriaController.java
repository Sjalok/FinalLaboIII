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
import java.util.Map;

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
    public Map<Integer, Materia> getMateriasOrdenadas(@RequestParam(defaultValue = "nombre_asc") String order) {
        if (!order.equals("nombre_desc") && !order.equals("nombre_asc") && !order.equals("codigo_asc") && !order.equals("codigo_desc")) {
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
    public Materia modificarCorrelatividadMateria(@PathVariable String nombremateria, @RequestParam String correlatividad, @RequestParam String accion){
        if (!accion.equals("agregar") && !accion.equals("eliminar")) {
            throw new BadRequestException("La accion solo puede ser agregar o eliminar.");
        }

        if (!nombremateria.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\sIVXLCDM]+$")) {
            throw new BadRequestException("El nombre de la materia solo puede contener letras y espacios, sin números ni caracteres especiales.");
        }

        return materiaService.saveCorrelatividadMateria(nombremateria, correlatividad, accion);
    }

    @PutMapping("/{nombremateria}")
    public Materia modificarNombreMateria(@PathVariable String nombremateria, @RequestParam String nuevonombre) {
        if (!nombremateria.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\sIVXLCDM]+$") || !nuevonombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\sIVXLCDM]+$")) {
            throw new BadRequestException("El nombre de la materia o la nueva materia solo puede contener letras y espacios, sin números ni caracteres especiales.");
        }
        return materiaService.saveMateriaNombre(nombremateria, nuevonombre);
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
