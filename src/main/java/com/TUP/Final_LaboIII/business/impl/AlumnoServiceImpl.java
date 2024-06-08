package com.TUP.Final_LaboIII.business.impl;

import com.TUP.Final_LaboIII.business.AlumnoService;
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
        return alumnoDao.loadAlumnoId(idalumno);
    }

    @Override
    public Alumno getAlumnoXDni(Long dnialumno) {
        return alumnoDao.loadAlumnoDni(dnialumno);
    }

    @Override
    public Alumno crearAlumno(AlumnoDto alumnoDto) {
        return null;
    }

    @Override
    public Alumno saveAlumno(int id, Alumno alumno) {
        return null;
    }

    @Override
    public Alumno borrarAlumno(int idalumno){
        return alumnoDao.deleteAlumno(idalumno);
    }
}
