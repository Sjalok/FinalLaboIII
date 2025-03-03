package com.TUP.Final_LaboIII.business;

import com.TUP.Final_LaboIII.model.Profesor;
import com.TUP.Final_LaboIII.model.dto.ProfesorDto;

public interface ProfesorService {
    Profesor getProfesor(Long dni);

    Profesor crearProfesor(ProfesorDto profesorDto);

    Profesor borrarProfesor(Long dni);

    Profesor agregarOEliminarMateria(Long dni, String nombremateria, String accion);
}