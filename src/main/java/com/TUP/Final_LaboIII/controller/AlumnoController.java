package com.TUP.Final_LaboIII.controller;

import com.TUP.Final_LaboIII.business.AlumnoService;
import com.TUP.Final_LaboIII.business.exception.NumberFormatException;
import com.TUP.Final_LaboIII.controller.exception.BadRequestException;
import com.TUP.Final_LaboIII.model.Alumno;
import com.TUP.Final_LaboIII.model.Asignatura;
import com.TUP.Final_LaboIII.model.dto.AlumnoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/{idalumno}")
    public Alumno getAlumnoXId(@PathVariable String idalumno){
        try {
            int id = Integer.parseInt(idalumno);
            return alumnoService.getAlumnoXId(id);
        } catch (NumberFormatException e) {
            throw new java.lang.NumberFormatException();
        }
    }

    @GetMapping("/obteneralumno/{dnialumno}")
    public Alumno getAlumnoXDni(@PathVariable String dnialumno) {
        try {
            Long dni = Long.parseLong(dnialumno);
            return alumnoService.getAlumnoXDni(dni);
        } catch (NumberFormatException e) {
            throw new java.lang.NumberFormatException();
        }
    }

    @PostMapping
    public String crearAlumno(@RequestBody AlumnoDto alumnoDto) {
        try {
            Long dni = Long.parseLong(alumnoDto.getDni().toString());

            if (!alumnoDto.getNombre().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$")) {
                throw new BadRequestException("El nombre solo puede contener letras.");
            }
            if (!alumnoDto.getApellido().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$")) {
                throw new BadRequestException("El apellido solo puede contener letras.");
            }
            if (!alumnoDto.getCarrera().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                throw new BadRequestException("La carrera solo puede contener letras y espacios.");
            }

            if (alumnoDto.getNombre() == null || alumnoDto.getApellido() == null ||
                    alumnoDto.getDni() == null || alumnoDto.getCarrera() == null) {
                throw new BadRequestException("Todos los campos son obligatorios.");
            }

            return alumnoService.crearAlumno(alumnoDto);

        } catch (NumberFormatException e) {
            throw new BadRequestException("El DNI del alumno debe contener solo números.");
        }
    }

    @PutMapping("/{idalumno}/asignatura/{nombreasignatura}")
    public String cambiarEstado(@PathVariable String idalumno, @PathVariable String nombreasignatura, @RequestParam String estado) {
        try {
            int id = Integer.parseInt(idalumno);

            System.out.println("Valor de 'estado': '" + estado + "'");

            if (estado == null || estado.trim().isEmpty()) {
                throw new BadRequestException("El parámetro 'estado' es obligatorio.");
            }

            if (!nombreasignatura.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\sIVXLCDM]+$")) {
                throw new BadRequestException("El nombre de la asignatura no debe contener caracteres especiales ni números.");
            }

            if (!estado.equalsIgnoreCase("cursada") && !estado.equalsIgnoreCase("perdida") && !estado.equalsIgnoreCase("aprobada")) {
                throw new BadRequestException("El estado no puede ser distinto a cursada, perdida o aprobada.");
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

    @PutMapping("/{idalumno}/{nombreasignatura}")
    public Alumno inscribirAMateria(@PathVariable String idalumno, @PathVariable String nombreasignatura) {
        try {
            int id = Integer.parseInt(idalumno);

            if (!nombreasignatura.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\sIVXLCDM]+$")) {
                throw new BadRequestException("El nombre de la asignatura no debe contener caracteres especiales.");
            }

            return alumnoService.inscribirseAMateria(id, nombreasignatura);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El ID del alumno debe contener solo números.");
        }
    }

    @GetMapping("/{idalumno}/materias")
    public List<Asignatura> verTodasLasMaterias(@PathVariable String idalumno) {
        try {
            int id = Integer.parseInt(idalumno);
            return alumnoService.getTodasMaterias(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El ID del alumno debe contener solo números.");
        }
    }
}
