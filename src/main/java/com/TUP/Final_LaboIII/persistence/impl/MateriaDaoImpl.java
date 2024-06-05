package com.TUP.Final_LaboIII.persistence.impl;

import com.TUP.Final_LaboIII.model.Alumno;
import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.persistence.MateriaDao;
import com.TUP.Final_LaboIII.persistence.exception.NotFoundException;
import com.TUP.Final_LaboIII.persistence.exception.WrongCodeException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MateriaDaoImpl implements MateriaDao {

    private static Map<Integer, Materia> repositorioMaterias = new HashMap<>();
    @Override
    public Materia loadMateriaId(int idmateria) {
        for (Materia materia: repositorioMaterias.values()) {
            if (idmateria == materia.getMateriaId()) {
                return materia;
            }
        }
        throw new NotFoundException("No se encuentra una materia con ese id.");
    }

    @Override
    public Materia loadMateriaNombre(String nombremateria) {
        for (Materia materia: repositorioMaterias.values()) {
            if (nombremateria == materia.getNombre()) {
                return materia;
            }
        }
        throw new NotFoundException("No se encuentra una materia con ese nombre.");
    }

    @Override
    public Materia newMateria(Materia materia) {
        return repositorioMaterias.put(materia.getMateriaId(), materia);
    }

    @Override
    public boolean findByCode(int codigomateria) {
        for (Materia materia: repositorioMaterias.values()) {
            if (codigomateria == materia.getMateriaId()) {
                throw new WrongCodeException("Hay un problema con el codigo: ya se encuentra una materia con ese codigo.");
            }
        }
        return false;
    }

    @Override
    public Materia saveMateria(int codigomateria, Materia materia) {
        for (Materia materias: repositorioMaterias.values()) {
            if (codigomateria == materia.getMateriaId()) {
                return repositorioMaterias.replace(codigomateria,materia);
            }
        }
        throw new NotFoundException("No se encuentra una materia con ese nombre.");
    }

    @Override
    public Materia deleteMateria(int codigomateria) {
        for (Materia materia: repositorioMaterias.values()) {
            if (codigomateria == materia.getMateriaId()) {
                return repositorioMaterias.remove(codigomateria);
            }
        }
        throw new NotFoundException("No se encuentra una materia con ese nombre.");
    }
}
