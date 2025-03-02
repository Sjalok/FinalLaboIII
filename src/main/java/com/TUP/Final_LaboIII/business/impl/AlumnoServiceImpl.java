package com.TUP.Final_LaboIII.business.impl;

import com.TUP.Final_LaboIII.business.AlumnoService;
import com.TUP.Final_LaboIII.business.AsignaturaService;
import com.TUP.Final_LaboIII.business.exception.NotFoundException;
import com.TUP.Final_LaboIII.model.Alumno;
import com.TUP.Final_LaboIII.model.dto.AlumnoDto;
import com.TUP.Final_LaboIII.persistence.AlumnoDao;
import com.TUP.Final_LaboIII.persistence.impl.AlumnoDaoImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService {
    private static final AlumnoDao alumnoDao = new AlumnoDaoImpl();
    AsignaturaService asignaturaService;
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
        Alumno alumno = new Alumno();
        alumno.setNombre(alumnoDto.getNombre());
        alumno.setApellido(alumnoDto.getApellido());
        alumno.setDni(alumnoDto.getDni());
        alumno.setCarrera(alumnoDto.getCarrera());
        alumno.setAsignaturas(new ArrayList<>());

        return alumnoDao.newAlumno(alumno);
    }

    @Override
    public Alumno cambiarEstado(int idalumno, String nombreasignatura, String estado) {
        Alumno a = alumnoDao.loadAlumnoId(idalumno);
        asignaturaService.tieneMateria(idalumno, nombreasignatura, estado);
        asignaturaService.checkCorrelatividades(idalumno, a.getAsignaturas());

        return this.saveAlumno(idalumno, a);
    }

    private Alumno saveAlumno(int id, Alumno alumno) {
        return alumnoDao.saveAlumno(id, alumno);
    }

    @Override
    public Alumno borrarAlumno(int idalumno){
        if (alumnoDao.findById(idalumno)) {
            asignaturaService.borrarAsignaturasAlumno(idalumno);
            return alumnoDao.deleteAlumno(idalumno);
        }
        throw new NotFoundException("No se ha encontrado un alumno con ese id.");
    }

    @Override
    public Alumno inscribirseAMateria(int idalumno, String nombreasignatura) {
        Alumno a = alumnoDao.loadAlumnoId(idalumno);
        asignaturaService.tieneMateria(idalumno, nombreasignatura, "cursando");
        asignaturaService.checkCorrelatividades(idalumno, a.getAsignaturas());

        return this.saveAlumno(idalumno,a);
    }

    @Override
    public HashMap<String, String> getTodasMaterias(int idalumno) {
        List<Integer> listaAsignaturas = alumnoDao.loadAsignaturasID(idalumno);
        return asignaturaService.listaMaterias(idalumno);
    }
}
