package com.TUP.Final_LaboIII.persistence.impl;

import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.persistence.MateriaDao;
import com.TUP.Final_LaboIII.business.exception.NotFoundException;
import com.TUP.Final_LaboIII.business.exception.WrongCodeException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MateriaDaoImpl implements MateriaDao {

    private static Map<Integer, Materia> repositorioMaterias = new HashMap<>();
    @Override
    public Materia loadMateriaId(int idmateria) {
        if (!findByCode(idmateria)) {
            throw new NotFoundException("No se encontro una materia con el id " + idmateria);
        }
        return repositorioMaterias.get(idmateria);
    }

    @Override
    public Materia loadMateriaNombre(String nombremateria) {
        for (Materia materia: repositorioMaterias.values()) {
            if (nombremateria == materia.getNombre()) {
                return materia;
            }
        }
        throw new NotFoundException("No se encontro una materia llamada " + nombremateria);
    }

    public Materia newMateria(Materia materia) {
        int nuevoId = repositorioMaterias.isEmpty() ? 1 : Collections.max(repositorioMaterias.keySet()) + 1;
        return repositorioMaterias.put(nuevoId, materia);
    }

    @Override
    public boolean findByCode(int codigomateria) {
        return repositorioMaterias.containsKey(codigomateria);
    }

    @Override
    public Materia saveMateria(int codigomateria, Materia materia) {
        if (!findByCode(codigomateria)) {
            throw new NotFoundException("No se encontro una materia con el id " + codigomateria);
        } else if (!repositorioMaterias.get(codigomateria).getNombre().equals(materia.getNombre())) {
            throw new NotFoundException("No se encontro la materia o no coinciden los valores");
        }
        return repositorioMaterias.replace(codigomateria, materia);
    }

    @Override
    public Materia deleteMateria(int codigomateria) {
        if (!findByCode(codigomateria)) {
            throw new NotFoundException("No se encontro una materia con el id " + codigomateria);
        }
        return repositorioMaterias.remove(codigomateria);
    }
}
