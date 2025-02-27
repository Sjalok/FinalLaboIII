package com.TUP.Final_LaboIII.business;

import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.model.dto.MateriaDto;

import java.util.HashMap;
import java.util.List;

public interface MateriaService {
    Materia getMateriaXNombre(String nombremateria);

    Materia crearMateria(MateriaDto materiadto);

    Materia saveMateria(Materia materia);

    Materia deleteMateria(int codigoMateria);

    List<Materia> getMateriasOrdenadas(String order);
}
