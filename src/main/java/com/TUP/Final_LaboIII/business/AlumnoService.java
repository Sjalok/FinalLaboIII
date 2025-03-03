package com.TUP.Final_LaboIII.business;

import com.TUP.Final_LaboIII.model.Alumno;
import com.TUP.Final_LaboIII.model.Asignatura;
import com.TUP.Final_LaboIII.model.dto.AlumnoDto;

import java.util.HashMap;
import java.util.List;

public interface AlumnoService {
    Alumno getAlumnoXId(int idalumno);

    Alumno getAlumnoXDni(Long dnialumno);

    Alumno crearAlumno(AlumnoDto alumnoDto);

    Alumno cambiarEstado(int idalumno, String nombreasignatura, String estado);

    Alumno borrarAlumno(int idalumno);

    Alumno inscribirseAMateria(int idalumno, String nombreasignatura);

    List<Asignatura> getTodasMaterias(int idalumno);
}
