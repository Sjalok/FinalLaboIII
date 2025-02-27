package com.TUP.Final_LaboIII.business.impl;

import com.TUP.Final_LaboIII.business.MateriaService;
import com.TUP.Final_LaboIII.business.exception.NotFoundException;
import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.model.dto.MateriaDto;
import com.TUP.Final_LaboIII.persistence.MateriaDao;
import com.TUP.Final_LaboIII.persistence.impl.MateriaDaoImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MateriaServiceImpl implements MateriaService {

    private static final MateriaDao materiaDao = new MateriaDaoImpl();

    @Override
    public Materia getMateriaXNombre(String nombremateria) {
        Materia m = materiaDao.loadMateriaNombre(nombremateria);

        if (m == null) {
            throw new NotFoundException("No existe una materia con ese nombre.");
        }

        return m;
    }

    @Override
    public Materia crearMateria(MateriaDto materiadto) {
        return null;
    }

    @Override
    public Materia saveMateria(Materia materia) {
        return null;
    }

    @Override
    public Materia deleteMateria(int codigoMateria) {
        if (materiaDao.findByCode(codigoMateria)) {
            return materiaDao.deleteMateria(codigoMateria);
        }

        throw new NotFoundException("No existe una materia con ese codigo.");
    }

    @Override
    public List<Materia> getMateriasOrdenadas(String order) {
        return null;
    }
}
