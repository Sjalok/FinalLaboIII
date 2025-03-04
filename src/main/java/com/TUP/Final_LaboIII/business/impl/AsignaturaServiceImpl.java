package com.TUP.Final_LaboIII.business.impl;

import com.TUP.Final_LaboIII.business.AlumnoService;
import com.TUP.Final_LaboIII.business.AsignaturaService;
import com.TUP.Final_LaboIII.business.MateriaService;
import com.TUP.Final_LaboIII.business.exception.CorrelatividadesException;
import com.TUP.Final_LaboIII.business.exception.NotFoundException;
import com.TUP.Final_LaboIII.controller.exception.BadRequestException;
import com.TUP.Final_LaboIII.model.Alumno;
import com.TUP.Final_LaboIII.model.Asignatura;
import com.TUP.Final_LaboIII.model.EstadoAsignatura;
import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.model.dto.MateriaDto;
import com.TUP.Final_LaboIII.persistence.AsignaturaDao;
import com.TUP.Final_LaboIII.persistence.exception.YaExistenteException;
import com.TUP.Final_LaboIII.persistence.impl.AsignaturaDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsignaturaServiceImpl implements AsignaturaService {
    @Autowired
    private static final AsignaturaDao asignaturaDao = new AsignaturaDaoImpl();
    MateriaService materiaService;
    AlumnoService alumnoService;

    EstadoAsignatura EstadoAsignatura;

    @Override
    public Alumno nuevaAsignatura(Alumno alumno, String nombremateria) {
        if (!materiaService.existeMateria(nombremateria)) {
            throw new NotFoundException("No existe una materia con ese nombre.");
        }
        if (alumnoService.getAlumnoXId(alumno.getId()) == null) {
            throw new NotFoundException("No existe un alumno con ese id.");
        }

        if (!esMateriaPerdida(alumno.getId(), nombremateria)) {
            throw new BadRequestException("El alumno ya se encuentra cursando esa materia, o ya la tiene aprobada.");
        }

        if (!checkCorrelatividades(alumno.getId(), nombremateria, "nueva")) {
            throw new CorrelatividadesException("Debe aprobar las correlatividades para inscribirse a la materia.");
        }

        Materia m = materiaService.getMateriaXNombre(nombremateria);
        Asignatura asignatura = new Asignatura();
        asignatura.setMateria(m);
        asignatura.setEstado(com.TUP.Final_LaboIII.model.EstadoAsignatura.CURSANDO);
        asignatura.setNota(0);
        asignatura.setIdAlumno(alumno.getId());

        Integer idAsignatura = asignaturaDao.newAsignatura(asignatura);

        alumno.getAsignaturas().add(idAsignatura);

        return alumno;
    }

    @Override
    public void borrarAsignaturasAlumno(Integer idalumno) {
        asignaturaDao.deleteAlumnoAsignaturas(idalumno);
    }

    @Override
    public boolean checkCorrelatividades(Integer idalumno, String nombremateria, String estado) {
        Materia materia = materiaService.getMateriaXNombre(nombremateria);

        List<Materia> correlatividades = materia.getCorrelatividades();

        if (correlatividades == null || correlatividades.isEmpty()) {
            return true;
        }

        Alumno alumno = alumnoService.getAlumnoXId(idalumno);

        List<Asignatura> asignaturasAlumno = asignaturaDao.loadAsignaturasid(idalumno);

        for (Materia correlativa : correlatividades) {
            boolean aprobada = asignaturasAlumno.stream()
                    .anyMatch(asignatura ->
                            asignatura.getMateria().getNombre().equals(correlativa.getNombre()) &&
                                    asignatura.getEstado() == EstadoAsignatura.APROBADA
                    );

            if (!aprobada) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean checkEstado(Integer idalumno, String nombremateria, String estado) {
        Alumno a = alumnoService.getAlumnoXId(idalumno);

        EstadoAsignatura nuevoEstado;
        nuevoEstado = EstadoAsignatura.valueOf(estado.toUpperCase());


        List<Asignatura> asignaturas = asignaturaDao.loadAsignaturasid(idalumno);

        for (Asignatura asignatura : asignaturas) {
            if (asignatura.getMateria().getNombre().equals(nombremateria)) {
                EstadoAsignatura estadoActual = asignatura.getEstado();

                boolean cambioValido = switch (nuevoEstado) {
                    case CURSADA -> estadoActual == EstadoAsignatura.CURSANDO;
                    case PERDIDA -> estadoActual == EstadoAsignatura.CURSANDO;
                    case APROBADA -> estadoActual == EstadoAsignatura.CURSADA;
                    default -> false;
                };

                if (!cambioValido) {
                    return false;
                }

                asignatura.setEstado(nuevoEstado);
                asignaturaDao.saveAsignatura(idalumno, asignatura);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean tieneMateria(Integer idalumno, String nombreasignatura) {
        Alumno a = alumnoService.getAlumnoXId(idalumno);

        if (!esMateriaPerdida(idalumno, nombreasignatura)) {
            throw new YaExistenteException("El alumno ya tiene aprobada esa materia o se encuentra cursandola.");
        }

        return false;
    }

    @Override
    public List<Asignatura> listaMaterias(Integer idalumno) {
        return asignaturaDao.loadAsignaturasid(idalumno);
    }

    private boolean esMateriaPerdida(Integer idalumno,String nombremateria) {
        List<Asignatura> asignaturas = asignaturaDao.loadAsignaturasid(idalumno);

        for (Asignatura asignatura : asignaturas) {
            if (asignatura.getMateria().getNombre().equalsIgnoreCase(nombremateria)) {
                if (asignatura.getEstado() == EstadoAsignatura.PERDIDA) {
                    return true;
                }
                return false;
            }
        }

        return true;
    }

    private Map<Integer,Asignatura> todasLasAsignaturas() {
        return asignaturaDao.getTodasAsignaturas();
    }
}
