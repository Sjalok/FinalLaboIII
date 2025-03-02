package com.TUP.Final_LaboIII.controller;

import com.TUP.Final_LaboIII.business.ProfesorService;
import com.TUP.Final_LaboIII.business.exception.NumberFormatException;
import com.TUP.Final_LaboIII.business.impl.ProfesorServiceImpl;
import com.TUP.Final_LaboIII.controller.exception.BadRequestException;
import com.TUP.Final_LaboIII.model.Profesor;
import com.TUP.Final_LaboIII.model.dto.ProfesorDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {

    ProfesorService profesorService;

    @GetMapping("/{dniprofesor}")
    public Profesor getProfesor(@PathVariable String dni){
        try {
            Long DNI = Long.parseLong(dni);
            return profesorService.getProfesor(DNI);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El DNI del profesor debe contener solo números.");
        }
    }

    @PostMapping
    public Profesor crearProfesor(@RequestBody ProfesorDto profesorDto){
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
    public Profesor borrarProfesor(@PathVariable String dni){
        try {
            Long DNI = Long.parseLong(dni);
            return profesorService.borrarProfesor(DNI);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El DNI del profesor debe contener solo números.");
        }
    }
}
