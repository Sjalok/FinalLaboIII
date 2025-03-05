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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CarreraServiceImpl implements CarreraService {
    @Autowired
    private static final CarreraDao carreraDao = new CarreraDaoImpl();
    @Autowired
    MateriaService materiaService;
    @Override
    public String crearCarrera(CarreraDto carreraDto) {
        Carrera carrera = new Carrera(carreraDto.getNombre(), carreraDto.getCodigoCarrera(), carreraDto.getDepartamento(), carreraDto.getCantCuatrimestres());
        if (carreraDao.findByName(carrera.getNombre())) {
            throw new YaExistenteException("Ya existe una carrera con ese nombre.");
        }
        carreraDao.newCarrera(carrera);
        return "Se ha creado la carrera correctamente.";
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
    public String borrarCarrera(int codigocarrera) {
        if (carreraDao.findByCode(codigocarrera)) {
            carreraDao.deleteCarrera(codigocarrera);
            return "Se ha borrado la carrera correctamente.";
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

        List<Materia> materias = new ArrayList<>(carrera.getListaMaterias());

        if ("agregar".equalsIgnoreCase(accion)) {
            if (materias.stream().anyMatch(materia -> materia.getNombre().equals(nombremateria))) {
                throw new YaExistenteException("La carrera ya tiene la materia: " + nombremateria);
            }
            materias.add(m);
            System.out.println(materias);
        }
        else if ("eliminar".equalsIgnoreCase(accion)) {
            if (materias.removeIf(materia -> materia.getNombre().equals(nombremateria))) {
            } else {
                throw new NotFoundException("La carrera no tiene la carrera: " + nombremateria);
            }
        }

        carrera.setListaMaterias(materias);

        return saveCarrera(carrera);
    }

    @Override
    public boolean carreraExist(String nombrecarrera) {
        return carreraDao.findByName(nombrecarrera);
    }
}
