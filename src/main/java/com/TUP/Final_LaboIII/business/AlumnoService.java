package com.TUP.Final_LaboIII.business;

import com.TUP.Final_LaboIII.model.Alumno;
import com.TUP.Final_LaboIII.model.dto.AlumnoDto;

public interface AlumnoService {
    Alumno getAlumnoXId(int Idalumno);

    Alumno getAlumnoXDni(Long dnialumno);

    Alumno crearAlumno(AlumnoDto alumnoDto);

    Alumno saveAlumno(int id, Alumno alumno);

    Alumno borrarAlumno(int idalumno);
}
