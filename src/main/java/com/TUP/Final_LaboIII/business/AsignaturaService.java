package com.TUP.Final_LaboIII.business;

import com.TUP.Final_LaboIII.model.Asignatura;
import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.model.dto.MateriaDto;

import java.util.HashMap;
import java.util.List;

public interface AsignaturaService {
    void nuevaAsignatura(Integer idalumno, MateriaDto materiaDto);
    void borrarAsignaturasAlumno(Integer idalumno);
    boolean checkCorrelatividades(Integer idalumno, List<Integer> correlativiades);
    boolean tieneMateria(Integer idalumno, String nombreasignatura, String estado);
    HashMap<String, String> listaMaterias(Integer idalumno);
}
