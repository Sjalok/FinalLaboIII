package com.TUP.Final_LaboIII.persistence.impl;

import com.TUP.Final_LaboIII.business.exception.NotFoundException;
import com.TUP.Final_LaboIII.model.Alumno;
import com.TUP.Final_LaboIII.persistence.AlumnoDao;
import com.TUP.Final_LaboIII.persistence.exception.YaExistenteException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class AlumnoDaoImpl implements AlumnoDao {
    private static Map<Long, Alumno> repositorioAlumnos = new HashMap<>();

    @Override
    public Alumno saveAlumno(int idalumno, Alumno alumno) {
        if (!findById(idalumno)) {
            throw new NotFoundException("Alumno con el id " + idalumno + " no existe.");
        }
        for (Alumno alumnos: repositorioAlumnos.values()) {
            if (idalumno == alumnos.getId()) {
                Long dniEncontrado = alumnos.getDni();
                return repositorioAlumnos.replace(dniEncontrado,alumno);
            }
        }
        return null;
    }

    @Override
    public Alumno loadAlumnoId(int idalumno) {
        for (Alumno alumno: repositorioAlumnos.values()) {
            if (idalumno == alumno.getId()) {
                return alumno;
            }
        }
        throw new NotFoundException("Alumno con el id " + idalumno + " no existe.");
    }

    @Override
    public Alumno loadAlumnoDni(Long dnialumno) {
        for (Alumno alumno:repositorioAlumnos.values()) {
            if (dnialumno.equals(alumno.getDni())){
                return alumno;
            }
        }
        throw new NotFoundException("Alumno con el dni " + dnialumno + " no existe.");
    }

    @Override
    public boolean findByDni(Long dni) {
        for (Alumno alumno: repositorioAlumnos.values()) {
            if (dni.equals(alumno.getDni())){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean findById(int idalumno) {
        for (Alumno alumno: repositorioAlumnos.values()) {
            if (idalumno == alumno.getId()) {
                return true;
            }
        }
        return false;
    }

    public Alumno newAlumno(Alumno alumno) {
        if (findByDni(alumno.getDni())) {
            throw new YaExistenteException("Alumno con DNI " + alumno.getDni() + " ya existe.");
        }
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
        throw new NotFoundException("Alumno con el id " + idalumno + " no existe.");
    }

    @Override
    public List<Integer> loadAsignaturasID(int idalumno) {
        Alumno alumno = loadAlumnoId(idalumno);
        return alumno.getAsignaturas();
    }
}
