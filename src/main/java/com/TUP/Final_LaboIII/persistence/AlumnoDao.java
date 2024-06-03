package com.TUP.Final_LaboIII.persistence;

import com.TUP.Final_LaboIII.model.Alumno;

public interface AlumnoDao {
    Alumno saveAlumno (int idalumno, Alumno alumno);

    Alumno loadAlumnoId(int idalumno);

    Alumno loadAlumnoDni(Long dnialumno);

    Alumno findAlumno(int idalumno);

    Alumno newAlumno(Alumno alumno);

    Alumno deleteAlumno(int idalumno);
}
