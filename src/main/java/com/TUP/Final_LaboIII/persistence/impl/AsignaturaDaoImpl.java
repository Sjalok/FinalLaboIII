package com.TUP.Final_LaboIII.persistence.impl;

import com.TUP.Final_LaboIII.business.exception.NotFoundException;
import com.TUP.Final_LaboIII.model.Asignatura;
import com.TUP.Final_LaboIII.persistence.AsignaturaDao;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class AsignaturaDaoImpl  implements AsignaturaDao {

    private static Map<Integer, Asignatura> repositorioAsignaturas = new HashMap<>();

    @Override
    public Asignatura saveAsignatura(int idalumno, Asignatura asignatura) {
        return repositorioAsignaturas.entrySet().stream()
                .filter(entry -> entry.getValue().getIdAlumno().equals(idalumno) &&
                        entry.getValue().getMateriaNombre().equals(asignatura.getMateriaNombre()))
                .findFirst()
                .map(entry -> {
                    Asignatura asignaturaExistente = entry.getValue();
                    asignaturaExistente.setEstado(asignatura.getEstado()); // Solo actualiza el estado
                    return asignaturaExistente;
                })
                .orElseThrow(() -> new NotFoundException("No se encontró una asignatura para el alumno ID: " + idalumno + " con materia: " + asignatura.getMateriaNombre()));
    }

    @Override
    public Asignatura newAsignatura(Asignatura asignatura) {
        int nuevoId = repositorioAsignaturas.isEmpty() ? 1 : Collections.max(repositorioAsignaturas.keySet()) + 1;
        return repositorioAsignaturas.put(nuevoId, asignatura);
    }

    @Override
    public Asignatura loadAsignaturaDni(Integer idalumno, String nombremateria) {
        return repositorioAsignaturas.values().stream()
                .filter(a -> a.getIdAlumno().equals(idalumno) && a.getMateriaNombre().equals(nombremateria))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No se encontró una asignatura para el alumno ID: " + idalumno + " o con la materia: " + nombremateria));
    }

    @Override
    public List<Asignatura> loadAsignaturasDni(Integer idalumno) {
        List<Asignatura> asignaturas = repositorioAsignaturas.values().stream()
                .filter(a -> a.getIdAlumno().equals(idalumno))
                .collect(Collectors.toList());

        if (asignaturas.isEmpty()) {
            throw new NotFoundException("No se encontraron asignaturas para el alumno ID: " + idalumno);
        }

        return asignaturas;
    }

    public void deleteAlumnoAsignaturas(Integer idalumno) {
        repositorioAsignaturas.entrySet().removeIf(entry -> entry.getValue().getIdAlumno().equals(idalumno));
    }
}
