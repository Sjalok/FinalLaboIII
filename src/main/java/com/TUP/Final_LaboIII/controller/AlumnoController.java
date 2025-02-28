package com.TUP.Final_LaboIII.controller;

import com.TUP.Final_LaboIII.business.AlumnoService;
import com.TUP.Final_LaboIII.business.exception.NumberFormatException;
import com.TUP.Final_LaboIII.controller.exception.BadRequestException;
import com.TUP.Final_LaboIII.model.Alumno;
import com.TUP.Final_LaboIII.model.dto.AlumnoDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {
    private AlumnoService alumnoService;

    @GetMapping("/{idalumno}")
    public Alumno getAlumnoXId(@PathVariable String idalumno){
        try {
            int id = Integer.parseInt(idalumno);
            return alumnoService.getAlumnoXId(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El ID del alumno debe contener solo números.");
        }
    }

    @GetMapping("/{dnialumno}")
    public Alumno getAlumnoXDni(@PathVariable String dnialumno) {
        try {
            Long dni = Long.parseLong(dnialumno);
            return alumnoService.getAlumnoXDni(dni);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El DNI del alumno debe contener solo números.");
        }
    }

    @PostMapping
    public Alumno crearAlumno(@RequestBody AlumnoDto alumnoDto) {
        return alumnoService.crearAlumno(alumnoDto);
    }

    @PutMapping("/{idalumno}")
    public Alumno putAlumno(@PathVariable String idalumno, @RequestBody Alumno alumno){
        try {
            int id = Integer.parseInt(idalumno);
            return alumnoService.saveAlumno(id, alumno);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El ID del alumno debe contener solo números.");
        }
    }

    @PutMapping("/{idalumno}/asignatura/{nombreasignatura}")
    public Alumno cambiarEstado(@PathVariable String idalumno, @PathVariable String nombreasignatura, @RequestParam String estado) {
        try {
            int id = Integer.parseInt(idalumno);

            if (!nombreasignatura.matches("^[a-zA-Z0-9]+$")) {
                throw new BadRequestException("El nombre de la asignatura no debe contener caracteres especiales.");
            }

            return alumnoService.cambiarEstado(id, nombreasignatura, estado);
        } catch (NumberFormatException e) {
            throw new BadRequestException("El ID del alumno debe contener solo números.");
        }
    }

    @DeleteMapping("/{idalumno}")
    public Alumno borrarAlumno(@PathVariable String idalumno){
        try {
            int id = Integer.parseInt(idalumno);
            return alumnoService.borrarAlumno(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El ID del alumno debe contener solo números.");
        }
    }
}
