package com.TUP.Final_LaboIII.persistence;

import com.TUP.Final_LaboIII.model.Materia;

public interface MateriaDao {
    Materia loadMateriaId(int idmateria);

    Materia loadMateriaNombre(String nombremateria);

    Materia newMateria(Materia materia);

    boolean findByCode(int codigomateria);

    Materia saveMateria(int codigomateria, Materia materia);

    Materia deleteMateria(int codigomateria);
}
