package com.TUP.Final_LaboIII.business.impl;

import com.TUP.Final_LaboIII.business.MateriaService;
import com.TUP.Final_LaboIII.business.exception.NotFoundException;
import com.TUP.Final_LaboIII.controller.exception.BadRequestException;
import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.model.dto.MateriaDto;
import com.TUP.Final_LaboIII.persistence.MateriaDao;
import com.TUP.Final_LaboIII.persistence.exception.YaExistenteException;
import com.TUP.Final_LaboIII.persistence.impl.MateriaDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MateriaServiceImpl implements MateriaService {
    @Autowired
    private static final MateriaDao materiaDao = new MateriaDaoImpl();

    @Override
    public Materia getMateriaXNombre(String nombremateria) {
        Materia m = materiaDao.loadMateriaNombre(nombremateria);

        return m;
    }

    @Override
    public String crearMateria(MateriaDto materiadto) {
        if (existeMateria(materiadto.getNombre())) {
            throw new YaExistenteException("Ya existe una materia con ese nombre.");
        } else {
            Materia m = new Materia(materiadto.getNombre());
            materiaDao.newMateria(m);
            return "Se ha creado la materia correctamente.";
        }
    }

    @Override
    public Materia saveCorrelatividadMateria(String nombremateria, String nombrecorrelatividad, String accion) {
        if (!existeMateria(nombremateria)) {
            throw new NotFoundException("No existe una materia con el nombre: " + nombremateria);
        }
        if (!existeMateria(nombrecorrelatividad)) {
            throw new NotFoundException("No existe una materia con el nombre: " + nombrecorrelatividad);
        }

        Materia materia = getMateriaXNombre(nombremateria);
        Materia correlativa = getMateriaXNombre(nombrecorrelatividad);

        List<Materia> correlativas = new ArrayList<>(materia.getCorrelatividades());

        if ("agregar".equals(accion)) {
            if (correlativas.stream().anyMatch(m -> m.getNombre().equalsIgnoreCase(nombrecorrelatividad))) {
                throw new YaExistenteException("La materia ya tiene como correlativa a: " + nombrecorrelatividad);
            }
            correlativas.add(correlativa);
        }
        else {
            if (!correlativas.removeIf(m -> m.getNombre().equalsIgnoreCase(nombrecorrelatividad))) {
                throw new NotFoundException("La materia no tiene como correlativa a: " + nombrecorrelatividad);
            }
        }

        materia.setCorrelatividades(correlativas);

        return materiaDao.saveMateria(materia);
    }

    @Override
    public Materia saveMateriaNombre(String nombremateria, String nombrematerianuevo) {
        Materia materia = getMateriaXNombre(nombremateria);

        if (existeMateria(nombrematerianuevo)) {
            throw new YaExistenteException("Ya existe una materia con el nombre: " + nombrematerianuevo);
        }

        return materiaDao.cambiarNombreMateria(materia, nombrematerianuevo);
    }

    @Override
    public Materia deleteMateria(int codigoMateria) {
        if (materiaDao.findByCode(codigoMateria)) {
            return materiaDao.deleteMateria(codigoMateria);
        }

        throw new NotFoundException("No existe una materia con ese codigo.");
    }

    @Override
    public Map<Integer,Materia> getMateriasOrdenadas(String order) {
        Map<Integer, Materia> materias = materiaDao.getTodasLasMaterias();

        List<Map.Entry<Integer, Materia>> listaMaterias = new ArrayList<>(materias.entrySet());

        switch (order) {
            case "nombre_asc":
                listaMaterias.sort(Comparator.comparing(entry -> entry.getValue().getNombre()));
                break;
            case "nombre_desc":
                listaMaterias.sort(Comparator.comparing((Map.Entry<Integer, Materia> entry) -> entry.getValue().getNombre()).reversed());
                break;
            case "codigo_asc":
                listaMaterias.sort(Comparator.comparing(Map.Entry::getKey));
                break;
            case "codigo_desc":
                listaMaterias.sort(Comparator.comparing(Map.Entry<Integer, Materia>::getKey).reversed());
                break;
            default:
                throw new BadRequestException("Orden inválido: " + order);
        }

        Map<Integer, Materia> materiasOrdenadas = new LinkedHashMap<>();
        for (Map.Entry<Integer, Materia> entry : listaMaterias) {
            materiasOrdenadas.put(entry.getKey(), entry.getValue());
        }

        return materiasOrdenadas;
    }

    @Override
    public Integer getMateriaId(String nombremateria) {
        Map<Integer, Materia> materias = materiaDao.getTodasLasMaterias();

        for (Map.Entry<Integer, Materia> entry : materias.entrySet()) {
            if (entry.getValue().getNombre().equalsIgnoreCase(nombremateria)) {
                return entry.getKey();
            }
        }

        return 0;
    }

    @Override
    public boolean existeMateria(String nombremateria) {
        return materiaDao.getTodasLasMaterias().values().stream()
                .anyMatch(m -> m.getNombre().equals(nombremateria));
    }

    /** @Override
    public boolean tieneMateria(List<Integer> codigomaterias, String nombremateria) {
        Map<Integer, Materia> materias = materiaDao.getTodasLasMaterias();

        for (Integer codigo : codigomaterias) {
            if (materias.containsKey(codigo)) {
                Materia materia = materias.get(codigo);

                if (materia.getNombre().equalsIgnoreCase(nombremateria)) {
                    return true;
                }
            }
        }

        return false;
    } */
}
