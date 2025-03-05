package com.TUP.Final_LaboIII.controller;

import com.TUP.Final_LaboIII.business.ProfesorService;
import com.TUP.Final_LaboIII.business.exception.NumberFormatException;
import com.TUP.Final_LaboIII.controller.exception.BadRequestException;
import com.TUP.Final_LaboIII.model.Profesor;
import com.TUP.Final_LaboIII.model.dto.ProfesorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {
    @Autowired
    ProfesorService profesorService;

    @GetMapping("/{dniprofesor}")
    public Profesor getProfesor(@PathVariable String dniprofesor){
        try {
            Long DNI = Long.parseLong(dniprofesor);
            return profesorService.getProfesor(DNI);
        } catch (NumberFormatException e) {
            throw new java.lang.NumberFormatException();
        }
    }

    @PostMapping
    public String crearProfesor(@RequestBody ProfesorDto profesorDto){
        try {
            Long dni = Long.parseLong(profesorDto.getDni().toString());

            if (!profesorDto.getNombre().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$")) {
                throw new BadRequestException("El nombre solo puede contener letras.");
            }
            if (!profesorDto.getApellido().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$")) {
                throw new BadRequestException("El apellido solo puede contener letras.");
            }
            if (!profesorDto.getTitulo().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                throw new BadRequestException("La carrera solo puede contener letras y espacios.");
            }

            if (profesorDto.getNombre() == null || profesorDto.getApellido() == null ||
                    profesorDto.getDni() == null || profesorDto.getTitulo() == null) {
                throw new BadRequestException("Todos los campos son obligatorios.");
            }

            return profesorService.crearProfesor(profesorDto);

        } catch (NumberFormatException e) {
            throw new BadRequestException("El DNI del alumno debe contener solo números.");
        }
    }

    @DeleteMapping("/{dniprofesor}")
    public String borrarProfesor(@PathVariable String dniprofesor){
        try {
            Long DNI = Long.parseLong(dniprofesor);
            return profesorService.borrarProfesor(DNI);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El DNI del profesor debe contener solo números.");
        }
    }

    @PutMapping("/{dniprofesor}")
    public Profesor agregarOEliminarMaterias(@PathVariable String dniprofesor,@RequestParam String nombremateria, @RequestParam String accion) {
        accion = accion.toLowerCase();
        if (!accion.equals("eliminar") && !accion.equals("agregar")) {
            throw new BadRequestException("La accion solo puede ser eliminar o agregar.");
        }
        if (!nombremateria.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\sIVXLCDM]+$")) {
            throw new BadRequestException("El nombre de la materia solo puede contener letras y espacios, sin números ni caracteres especiales.");
        }
        try {
            Long DNI = Long.parseLong(dniprofesor);
            return profesorService.agregarOEliminarMateria(DNI, nombremateria,accion);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El DNI del profesor debe contener solo números.");
        }
    }
}
