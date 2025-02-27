package com.TUP.Final_LaboIII.business.impl;

import com.TUP.Final_LaboIII.business.ProfesorService;
import com.TUP.Final_LaboIII.business.exception.NotFoundException;
import com.TUP.Final_LaboIII.model.Profesor;
import com.TUP.Final_LaboIII.model.dto.ProfesorDto;
import com.TUP.Final_LaboIII.persistence.ProfesorDao;
import com.TUP.Final_LaboIII.persistence.impl.ProfesorDaoImpl;
import org.springframework.stereotype.Service;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    private static final ProfesorDao profesorDao = new ProfesorDaoImpl();
    @Override
    public Profesor getProfesor(Long dni) {
        Profesor p = profesorDao.loadProfesor(dni);

        if (p == null) {
            throw new NotFoundException("No se ha encontrado a un profesor con ese dni.");
        }

        return p;
    }

    @Override
    public Profesor crearProfesor(ProfesorDto profesorDto) {
        return null;
    }

    @Override
    public Profesor borrarProfesor(Long dni) {
        if (profesorDao.findByDni(dni)) {
            profesorDao.deleteProfesor(dni);
        }
        throw new NotFoundException("No se ha encontrado a un profesor con ese dni.");
    }
}
