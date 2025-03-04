package com.TUP.Final_LaboIII.business.impl;

import com.TUP.Final_LaboIII.business.CarreraService;
import com.TUP.Final_LaboIII.business.MateriaService;
import com.TUP.Final_LaboIII.business.exception.NotFoundException;
import com.TUP.Final_LaboIII.model.Carrera;
import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.model.dto.CarreraDto;
import com.TUP.Final_LaboIII.persistence.CarreraDao;
import com.TUP.Final_LaboIII.persistence.exception.YaExistenteException;
import com.TUP.Final_LaboIII.persistence.impl.CarreraDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CarreraServiceImpl implements CarreraService {
    @Autowired
    private static final CarreraDao carreraDao = new CarreraDaoImpl();
    MateriaService materiaService;
    @Override
    public Carrera crearCarrera(CarreraDto carreraDto) {
        Carrera carrera = new Carrera(carreraDto.getNombre(), carreraDto.getCodigoCarrera(), carreraDto.getDepartamento(), carreraDto.getCantCuatrimestres());
        return carreraDao.newCarrera(carrera);
    }

    @Override
    public Carrera getCarrera(int codigocarrera) {
        Carrera c = carreraDao.loadCarrera(codigocarrera);

        if (c == null) {
            throw new NotFoundException("No existe una carrera con ese codigo.");
        }

        return c;
    }


    private Carrera saveCarrera(Carrera carrera) {
        return carreraDao.saveCarrera(carrera.getCodigoCarrera(), carrera);
    }

    @Override
    public HashMap<String, List<String>> getTodasLasCarreras() {
        return carreraDao.mostrarTodasLasCarreras();
    }

    @Override
    public Carrera borrarCarrera(int codigocarrera) {
        if (carreraDao.findByCode(codigocarrera)) {
            return carreraDao.deleteCarrera(codigocarrera);
        }
        throw new NotFoundException("No existe una carrera con ese codigo.");
    }

    @Override
    public Carrera agregarOEliminarMaterias(int codigocarrera, String nombremateria, String accion) {
        if (!carreraDao.findByCode(codigocarrera)) {
            throw new NotFoundException("No existe una carrera con ese codigo.");
        }
        Carrera carrera = carreraDao.loadCarrera(codigocarrera);
        Materia m = materiaService.getMateriaXNombre(nombremateria);

        List<Materia> materias = carrera.getListaMaterias();

        if ("agregar".equals(accion)) {
            if (materias.stream().anyMatch(materia -> materia.getNombre().equals(nombremateria))) {
                throw new YaExistenteException("La carrera ya tiene la materia: " + nombremateria);
            }
            materias.add(m);
        }
        else if ("eliminar".equals(accion)) {
            if (materias.removeIf(materia -> materia.getNombre().equals(nombremateria))) {
            } else {
                throw new NotFoundException("La carrera no tiene la carrera: " + nombremateria);
            }
        }

        return saveCarrera(carrera);
    }
}
