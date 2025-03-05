package com.TUP.Final_LaboIII.persistence;

import com.TUP.Final_LaboIII.model.Materia;

import java.util.HashMap;
import java.util.Map;

public interface MateriaDao {
    Materia loadMateriaNombre(String nombremateria);
    Materia newMateria(Materia materia);
    boolean findByCode(int codigomateria);
    Materia saveMateria(Materia materia);
    Materia cambiarNombreMateria(Materia materia, String nombrematerianuevo);
    Materia deleteMateria(int codigomateria);
    Map<Integer,Materia> getTodasLasMaterias();
}
