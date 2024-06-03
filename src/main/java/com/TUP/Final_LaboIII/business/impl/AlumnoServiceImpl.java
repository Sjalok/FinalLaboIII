package com.TUP.Final_LaboIII.business.impl;

import com.TUP.Final_LaboIII.business.AlumnoService;
import com.TUP.Final_LaboIII.model.Alumno;
import com.TUP.Final_LaboIII.model.dto.AlumnoDto;
import org.springframework.stereotype.Service;

@Service
public class AlumnoServiceImpl implements AlumnoService {
    @Override
    public Alumno getAlumnoXId(int Idalumno) {
        return null;
    }

    @Override
    public Alumno getAlumnoXDni(Long dnialumno) {
        return null;
    }

    @Override
    public Alumno crearAlumno(AlumnoDto alumnoDto) {
        return null;
    }

    @Override
    public Alumno findById(int idalumno) {
        return null;
    }

    @Override
    public Alumno saveAlumno(Alumno alumnoacopiar, Alumno alumno) {
        return null;
    }

    @Override
    public Alumno borrarAlumno(int idalumno) {
        return null;
    }
}
