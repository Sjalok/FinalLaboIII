package com.TUP.Final_LaboIII.controller;

import com.TUP.Final_LaboIII.business.CarreraService;
import com.TUP.Final_LaboIII.business.exception.NumberFormatException;
import com.TUP.Final_LaboIII.controller.exception.BadRequestException;
import com.TUP.Final_LaboIII.model.Carrera;
import com.TUP.Final_LaboIII.model.dto.CarreraDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/carrera")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @PostMapping
    public String crearCarrera(@RequestBody CarreraDto carreraDto){
        try {
            Integer codigo = Integer.parseInt(String.valueOf(carreraDto.getCodigoCarrera()));
            Integer cuatris = Integer.parseInt(String.valueOf(carreraDto.getCantCuatrimestres()));
            Integer departamento = Integer.parseInt(String.valueOf(carreraDto.getDepartamento()));

            if (codigo <= 0) {
                throw new BadRequestException("Código de carrera no puede ser menor a 1.");
            }

            if (cuatris <= 0 || cuatris > 12) {
                throw new BadRequestException("La cantidad de cuatrimestres no puede ser 0 o mayor a 12.");
            }

            if (carreraDto.getNombre() == null) {
                throw new BadRequestException("Todos los campos son obligatorios.");
            }

            if (departamento < 1) {
                throw new BadRequestException("El codigo de departamento no puede ser menor a 1.");
            }

            if (carreraDto.getNombre() == null || carreraDto.getNombre().trim().isEmpty() ||
                    carreraDto.getDepartamento() <= 0 ||
                    carreraDto.getCodigoCarrera() <= 0 ||
                    carreraDto.getCantCuatrimestres() <= 0 || carreraDto.getCantCuatrimestres() > 12) {

                throw new BadRequestException("Todos los campos son obligatorios y deben tener valores válidos.");
            }

            return carreraService.crearCarrera(carreraDto);

        } catch (NumberFormatException e) {
            throw new BadRequestException("El codigo o la cantidad de cuatrimestres deben ser numeros enteros.");
        }
    }

    @GetMapping("/{codigocarrera}")
    public Carrera getCarrera(@PathVariable String codigocarrera) {
        try {
            int id = Integer.parseInt(codigocarrera);
            return carreraService.getCarrera(id);
        } catch (NumberFormatException e) {
            throw new java.lang.NumberFormatException();
        }
    }

    @GetMapping("/carreras")
    public HashMap<String, List<String>> getTodasCarreras(){return carreraService.getTodasLasCarreras();}

    @DeleteMapping("/{codigocarrera}")
    public String borrarCarrera(@PathVariable String codigocarrera){
        try {
            int codigo = Integer.parseInt(codigocarrera);
            return carreraService.borrarCarrera(codigo);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El codigo de la carrera debe contener solo números.");
        }
    }

    @PutMapping("/{codigocarrera}")
    public Carrera agregarOSacarMateria(@PathVariable String codigocarrera, @RequestParam String nombremateria, @RequestParam String accion) {
        accion = accion.toLowerCase();
        if (!accion.equals("eliminar") && !accion.equals("agregar")) {
            throw new BadRequestException("La accion solo puede ser eliminar o agregar.");
        }
        if (!nombremateria.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\sIVXLCDM]+$")) {
            throw new BadRequestException("El nombre de la materia solo puede contener letras y espacios, sin números ni caracteres especiales.");
        }
        try {
            Integer codigo = Integer.parseInt(codigocarrera);
            return carreraService.agregarOEliminarMaterias(codigo, nombremateria, accion);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El codigo de la carrera debe contener solo números.");
        }
    }
}
