package com.TUP.Final_LaboIII.business.impl;

import com.TUP.Final_LaboIII.business.MateriaService;
import com.TUP.Final_LaboIII.business.ProfesorService;
import com.TUP.Final_LaboIII.business.exception.NotFoundException;
import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.model.Profesor;
import com.TUP.Final_LaboIII.model.dto.ProfesorDto;
import com.TUP.Final_LaboIII.persistence.ProfesorDao;
import com.TUP.Final_LaboIII.persistence.exception.YaExistenteException;
import com.TUP.Final_LaboIII.persistence.impl.ProfesorDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfesorServiceImpl implements ProfesorService {
    @Autowired
    private static final ProfesorDao profesorDao = new ProfesorDaoImpl();
    @Autowired
    MateriaService materiaService;
    @Override
    public Profesor getProfesor(Long dni) {
        Profesor p = profesorDao.loadProfesor(dni);

        if (p == null) {
            throw new NotFoundException("No se ha encontrado a un profesor con ese dni.");
        }

        return p;
    }

    @Override
    public String crearProfesor(ProfesorDto profesorDto) {
        if (profesorDao.findByDni(profesorDto.getDni())) {
            throw new YaExistenteException("Ya existe un profesor con ese DNI.");
        }

        Profesor profesor = new Profesor(profesorDto.getDni(), profesorDto.getNombre(), profesorDto.getApellido(), profesorDto.getTitulo());

        profesorDao.newProfesor(profesor);

        return "Se ha creado al profesor correctamente.";
    }

    @Override
    public String borrarProfesor(Long dni) {
        if (profesorDao.findByDni(dni)) {
            profesorDao.deleteProfesor(dni);
            return "se ha borrado correctamente el profesor.";
        }
        throw new NotFoundException("No se ha encontrado a un profesor con ese dni.");
    }

    @Override
    public Profesor agregarOEliminarMateria(Long dni, String nombremateria, String accion) {
        if (!profesorDao.findByDni(dni)) {
            throw new NotFoundException("No se ha encontrado a un profesor con ese dni.");
        }
        Profesor p = profesorDao.loadProfesor(dni);
        Materia m = materiaService.getMateriaXNombre(nombremateria);

        List<Materia> materias = new ArrayList<>(p.getMateriasDictadas());

        if ("agregar".equals(accion)) {
            if (materias.stream().anyMatch(materia -> materia.getNombre().equals(nombremateria))) {
                throw new YaExistenteException("El profesor ya dicta la materia: " + nombremateria);
            }
            materias.add(m);
        }
        else if ("eliminar".equals(accion)) {
            if (materias.removeIf(materia -> materia.getNombre().equals(nombremateria))) {
            } else {
                throw new NotFoundException("El profesor no dicta la materia: " + nombremateria);
            }
        }

        p.setMateriasDictadas(materias);

        return saveProfesor(p);
    }

    private Profesor saveProfesor(Profesor profesor) {
        return profesorDao.saveProfesor(profesor);
    }
}
