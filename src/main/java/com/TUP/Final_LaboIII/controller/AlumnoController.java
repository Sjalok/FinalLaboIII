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
    public Alumno getAlumnoXDni(@PathVariable Long dnialumno) {
        return alumnoService.getAlumnoXDni(dnialumno);
    }

    @PostMapping
    public Alumno crearAlumno(@RequestBody AlumnoDto alumnoDto) {
        return alumnoService.crearAlumno(alumnoDto);
    }

    @PutMapping("/{idalumno}")
    public Alumno putAlumno(@PathVariable int idalumno, @RequestBody Alumno alumno){
        return alumnoService.saveAlumno(idalumno, alumno);
    }

    @PutMapping("/{idalumno}/asignatura/{idasignatura}")
    public Alumno cambiarEstado(@PathVariable int idalumno, @PathVariable int idasignatura, @RequestParam String estado) {
        return alumnoService.cambiarEstado(idalumno, idasignatura, estado);
    }

    @DeleteMapping("/{idalumno}")
    public Alumno borrarAlumno(@PathVariable int idalumno){
        return alumnoService.borrarAlumno(idalumno);
    }
}
