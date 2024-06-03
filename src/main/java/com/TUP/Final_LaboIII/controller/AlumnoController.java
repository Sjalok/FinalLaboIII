package com.TUP.Final_LaboIII.controller;

import com.TUP.Final_LaboIII.business.AlumnoService;
import com.TUP.Final_LaboIII.model.Alumno;
import com.TUP.Final_LaboIII.model.dto.AlumnoDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {
    private AlumnoService alumnoService;

    @GetMapping("/{idalumno}")
    public Alumno getAlumnoXId(@PathVariable int idalumno){
        return alumnoService.getAlumnoXId(idalumno);
    }

    @GetMapping("/{dnialumno}")
    public Alumno getAlumnoXDni(@PathVariable Long dni) {
        return alumnoService.getAlumnoXDni(dni);
    }

    @PostMapping
    public Alumno crearAlumno(@RequestBody AlumnoDto alumnoDto) {
        return alumnoService.crearAlumno(alumnoDto);
    }

    @PutMapping("/{idalumno}")
    public Alumno putAlumno(@PathVariable int idalumno, @RequestBody Alumno alumno){
        Alumno alumnoAEncontrar = alumnoService.findById(idalumno);
        return alumnoService.saveAlumno(alumno, alumnoAEncontrar);
    }

    @DeleteMapping("/{idalumno}")
    public Alumno borrarAlumno(@PathVariable int idalumno){
        return alumnoService.borrarAlumno(idalumno);
    }
}
