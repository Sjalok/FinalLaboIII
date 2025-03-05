package com.TUP.Final_LaboIII.business.impl;

import com.TUP.Final_LaboIII.business.AlumnoService;
import com.TUP.Final_LaboIII.business.AsignaturaService;
import com.TUP.Final_LaboIII.business.CarreraService;
import com.TUP.Final_LaboIII.business.MateriaService;
import com.TUP.Final_LaboIII.business.exception.CorrelatividadesException;
import com.TUP.Final_LaboIII.business.exception.NotFoundException;
import com.TUP.Final_LaboIII.model.Alumno;
import com.TUP.Final_LaboIII.model.Asignatura;
import com.TUP.Final_LaboIII.model.dto.AlumnoDto;
import com.TUP.Final_LaboIII.persistence.AlumnoDao;
import com.TUP.Final_LaboIII.persistence.impl.AlumnoDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService {
    @Autowired
    private static final AlumnoDao alumnoDao = new AlumnoDaoImpl();
    @Autowired
    AsignaturaService asignaturaService;
    @Autowired
    MateriaService materiaService;
    @Autowired
    CarreraService carreraService;
    @Override
    public Alumno getAlumnoXId(int idalumno) {
        return alumnoDao.loadAlumnoId(idalumno);
    }

    @Override
    public Alumno getAlumnoXDni(Long dnialumno) {
        return alumnoDao.loadAlumnoDni(dnialumno);
    }

    @Override
    public String crearAlumno(AlumnoDto alumnoDto) {
        if (!carreraService.carreraExist(alumnoDto.getCarrera())) {
            throw new NotFoundException("No se encontro una carrera con ese nombre.");
        }
        Alumno alumno = new Alumno();
        alumno.setNombre(alumnoDto.getNombre());
        alumno.setApellido(alumnoDto.getApellido());
        alumno.setDni(alumnoDto.getDni());
        alumno.setCarrera(alumnoDto.getCarrera());
        alumno.setAsignaturas(new ArrayList<>());
        alumno.setId(alumnoDao.IDMasAlto());

        alumnoDao.newAlumno(alumno);

        return "El alumno se creo correctamente con el ID " + alumno.getId();
    }

    @Override
    public String cambiarEstado(int idalumno, String nombreasignatura, String estado) {
        if (materiaService.getMateriaXNombre(nombreasignatura) == null) {
            throw new NotFoundException("No existe una materia con ese nombre.");
        }
        Alumno a = alumnoDao.loadAlumnoId(idalumno);
        if (!asignaturaService.checkEstado(idalumno,nombreasignatura,estado)) {
            throw new CorrelatividadesException("No se pueden saltear estados de las asignaturas.");
        }
        this.saveAlumno(idalumno, a);

        return "Se cambio el estado de la asignatura correctamente";
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
        a = asignaturaService.nuevaAsignatura(a, nombreasignatura);
        if (!asignaturaService.checkCorrelatividades(idalumno, nombreasignatura, "nueva")) {
            throw new CorrelatividadesException("Debe aprobar las correlatividades para cambiar de estado de esa materia.");
        }

        return this.saveAlumno(idalumno,a);
    }

    @Override
    public List<Asignatura> getTodasMaterias(int idalumno) {
        List<Integer> listaAsignaturas = alumnoDao.loadAsignaturasID(idalumno);
        return asignaturaService.listaMaterias(idalumno);
    }
}
