package com.TUP.Final_LaboIII.persistence;

import com.TUP.Final_LaboIII.model.Asignatura;

import java.util.List;

public interface AsignaturaDao {
    Asignatura saveAsignatura(int idalumno, Asignatura asignatura);
    Asignatura newAsignatura(Asignatura asignatura);
    Asignatura loadAsignaturaDni(Integer idalumno, String nombremateria);
    List<Asignatura> loadAsignaturasDni(Integer idalumno);
    void deleteAlumnoAsignaturas(Integer idalumno);
}
