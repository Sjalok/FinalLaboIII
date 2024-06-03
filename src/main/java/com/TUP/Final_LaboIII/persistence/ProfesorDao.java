package com.TUP.Final_LaboIII.persistence;

import com.TUP.Final_LaboIII.model.Profesor;

public interface ProfesorDao {

    Profesor newProfesor(Profesor profesor);

    Profesor loadProfesor(Long dni);

    Profesor deleteProfesor(Long dni);
}
