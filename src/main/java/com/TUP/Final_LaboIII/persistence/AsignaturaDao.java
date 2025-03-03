package com.TUP.Final_LaboIII.persistence;

import com.TUP.Final_LaboIII.model.Asignatura;

import java.util.List;
import java.util.Map;

public interface AsignaturaDao {
    Asignatura saveAsignatura(int idalumno, Asignatura asignatura);
    Integer newAsignatura(Asignatura asignatura);
    Asignatura loadAsignaturaid(Integer idalumno, String nombremateria);
    List<Asignatura> loadAsignaturasid(Integer idalumno);
    void deleteAlumnoAsignaturas(Integer idalumno);
    Map<Integer, Asignatura> getTodasAsignaturas();
}
