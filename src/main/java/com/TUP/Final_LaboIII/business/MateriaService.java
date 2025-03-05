package com.TUP.Final_LaboIII.business;

import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.model.dto.MateriaDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MateriaService {
    Materia getMateriaXNombre(String nombremateria);
    String crearMateria(MateriaDto materiadto);
    Materia saveCorrelatividadMateria(String nombremateria, String nombrecorrelatividad, String accion);
    Materia saveMateriaNombre(String nombremateria, String nombrematerianuevo);
    Materia deleteMateria(int codigoMateria);
    Map<Integer, Materia> getMateriasOrdenadas(String order);
    Integer getMateriaId(String nombremateria);
    boolean existeMateria(String nombremateria);
    // boolean tieneMateria(List<Integer> codigomaterias, String nombremateria);
}
