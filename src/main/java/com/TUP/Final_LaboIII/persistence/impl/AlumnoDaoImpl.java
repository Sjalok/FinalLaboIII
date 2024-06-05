package com.TUP.Final_LaboIII.persistence.impl;

import com.TUP.Final_LaboIII.model.Alumno;
import com.TUP.Final_LaboIII.persistence.AlumnoDao;
import com.TUP.Final_LaboIII.persistence.exception.WrongDniException;
import com.TUP.Final_LaboIII.persistence.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
@Repository
public class AlumnoDaoImpl implements AlumnoDao {
    private static Map<Long, Alumno> repositorioAlumnos = new HashMap<>();

    @Override
    public Alumno saveAlumno(int idalumno, Alumno alumno) {
        for (Alumno alumnos: repositorioAlumnos.values()) {
            if (idalumno == alumnos.getId()) {
                Long dniEncontrado = alumnos.getDni();
                return repositorioAlumnos.replace(dniEncontrado,alumno);
            }
        }
        throw new NotFoundException("El alumno con ese id no se encuentra guardado.");
    }

    @Override
    public Alumno loadAlumnoId(int idalumno) {
        for (Alumno alumno: repositorioAlumnos.values()) {
            if (idalumno == alumno.getId()) {
                return alumno;
            }
        }
        throw new NotFoundException("El alumno con ese id no se encuentra guardado.");
    }

    @Override
    public Alumno loadAlumnoDni(Long dnialumno) {
        for (Alumno alumno:repositorioAlumnos.values()) {
            if (dnialumno.equals(alumno.getDni())){
                return alumno;
            }
        }
        throw new NotFoundException("El alumno con ese dni no se encuentra guardado.");
    }

    @Override
    public boolean findByDni(Long dni) {
        for (Alumno alumno: repositorioAlumnos.values()) {
            if (dni.equals(alumno.getDni())){
                throw new WrongDniException("Hay un error con el dni: ya existe otro alumno con ese dni.");
            }
        }
        return false;
    }

    @Override
    public Alumno newAlumno(Alumno alumno) {
        return repositorioAlumnos.put(alumno.getDni(), alumno);
    }

    @Override
    public Alumno deleteAlumno(int idalumno) {
        for (Alumno alumno: repositorioAlumnos.values() ) {
            if (alumno.getId() == idalumno) {
                Long dniEncontrado = alumno.getDni();
                return repositorioAlumnos.remove(dniEncontrado);
            }
        }
        throw new NotFoundException("El alumno con ese id no se encuentra guardado.");
    }
}
