package com.TUP.Final_LaboIII.business.impl;

import com.TUP.Final_LaboIII.business.AsignaturaService;
import com.TUP.Final_LaboIII.model.Asignatura;
import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.model.dto.MateriaDto;
import com.TUP.Final_LaboIII.persistence.AsignaturaDao;
import com.TUP.Final_LaboIII.persistence.impl.AsignaturaDaoImpl;

import java.util.HashMap;
import java.util.List;

public class AsignaturaServiceImpl implements AsignaturaService {

    private static final AsignaturaDao asignaturaDao = new AsignaturaDaoImpl();

    @Override
    public void nuevaAsignatura(Integer idalumno, MateriaDto materiaDto) {

    }

    @Override
    public void borrarAsignaturasAlumno(Integer idalumno) {
        asignaturaDao.deleteAlumnoAsignaturas(idalumno);
    }

    @Override
    public boolean checkCorrelatividades(Integer idalumno, List<Integer> correlativiades) {
        return false;
    }

    @Override
    public boolean tieneMateria(Integer idalumno, String nombreasignatura, String estado) {
        return false;
    }

    @Override
    public HashMap<String, String> listaMaterias(Integer idalumno) {
        return null;
    }
}
