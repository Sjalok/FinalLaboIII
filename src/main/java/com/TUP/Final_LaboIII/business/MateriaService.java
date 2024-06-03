package com.TUP.Final_LaboIII.business;

import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.model.dto.MateriaDto;

public interface MateriaService {
    Materia getMateriaXId(int codigomateria);

    Materia getMateriaXNombre(String nombremateria);

    Materia crearMateria(MateriaDto materiadto);

    Materia findById(int codigomateria);

    Materia saveMateria(Materia materia, Materia materiaAEncontrar);

    Materia deleteMateria(int codigoMateria);
}
