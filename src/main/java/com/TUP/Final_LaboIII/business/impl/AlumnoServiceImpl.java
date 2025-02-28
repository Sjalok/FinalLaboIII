package com.TUP.Final_LaboIII.business.impl;

import com.TUP.Final_LaboIII.business.AlumnoService;
import com.TUP.Final_LaboIII.business.exception.NotFoundException;
import com.TUP.Final_LaboIII.model.Alumno;
import com.TUP.Final_LaboIII.model.dto.AlumnoDto;
import com.TUP.Final_LaboIII.persistence.AlumnoDao;
import com.TUP.Final_LaboIII.persistence.impl.AlumnoDaoImpl;
import org.springframework.stereotype.Service;

@Service
public class AlumnoServiceImpl implements AlumnoService {
    private static final AlumnoDao alumnoDao = new AlumnoDaoImpl();
    @Override
    public Alumno getAlumnoXId(int idalumno) {
        Alumno a = alumnoDao.loadAlumnoId(idalumno);

        if (a == null) {
            throw new NotFoundException("No se ha encontrado un alumno con ese id.");
        }

        return a;
    }

    @Override
    public Alumno getAlumnoXDni(Long dnialumno) {
        Alumno a = alumnoDao.loadAlumnoDni(dnialumno);

        if (a == null) {
            throw new NotFoundException("No se ha encontrado un alumno con ese dni.");
        }

        return a;
    }

    @Override
    public Alumno crearAlumno(AlumnoDto alumnoDto) {
        return null;
    }

    @Override
    public Alumno cambiarEstado(int idalumno, String nombreasignatura, String estado) {
        return null;
    }

    @Override
    public Alumno saveAlumno(int id, Alumno alumno) {
        return null;
    }

    @Override
    public Alumno borrarAlumno(int idalumno){
        if (alumnoDao.findById(idalumno)) {
            return alumnoDao.deleteAlumno(idalumno);
        }
        throw new NotFoundException("No se ha encontrado un alumno con ese id.");
    }
}
