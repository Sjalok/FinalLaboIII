package com.TUP.Final_LaboIII.business;

import com.TUP.Final_LaboIII.model.Alumno;
import com.TUP.Final_LaboIII.model.Asignatura;
import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.model.dto.MateriaDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AsignaturaService {
    Alumno nuevaAsignatura(Alumno alumno, String nombremateria);
    void borrarAsignaturasAlumno(Integer idalumno);
    boolean checkCorrelatividades(Integer idalumno, String nombremateria, String estado);
    boolean checkEstado(Integer idalumno, String nombremateria, String estado);
    boolean tieneMateria(Integer idalumno, String nombreasignatura);
    List<Asignatura> listaMaterias(Integer idalumno);
    Map<Integer,Asignatura> todasLasAsignaturas();
}
