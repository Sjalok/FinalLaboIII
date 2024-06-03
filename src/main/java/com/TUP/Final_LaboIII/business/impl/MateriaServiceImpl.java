package com.TUP.Final_LaboIII.business.impl;

import com.TUP.Final_LaboIII.business.MateriaService;
import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.model.dto.MateriaDto;
import org.springframework.stereotype.Service;

@Service
public class MateriaServiceImpl implements MateriaService {
    @Override
    public Materia getMateriaXId(int codigomateria) {
        return null;
    }

    @Override
    public Materia getMateriaXNombre(String nombremateria) {
        return null;
    }

    @Override
    public Materia crearMateria(MateriaDto materiadto) {
        return null;
    }

    @Override
    public Materia findById(int codigomateria) {
        return null;
    }

    @Override
    public Materia saveMateria(Materia materia, Materia materiaAEncontrar) {
        return null;
    }

    @Override
    public Materia deleteMateria(int codigoMateria) {
        return null;
    }
}
