package com.TUP.Final_LaboIII.controller;

import com.TUP.Final_LaboIII.business.CarreraService;
import com.TUP.Final_LaboIII.business.exception.NumberFormatException;
import com.TUP.Final_LaboIII.controller.exception.BadRequestException;
import com.TUP.Final_LaboIII.model.Carrera;
import com.TUP.Final_LaboIII.model.dto.CarreraDto;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/carrera")
public class CarreraController {

    private CarreraService carreraService;

    @PostMapping
    public Carrera crearCarrera(@RequestBody CarreraDto carreraDto){
        try {
            Integer codigo = Integer.parseInt(String.valueOf(carreraDto.getCodigoCarrera()));
            Integer cuatris = Integer.parseInt(String.valueOf(carreraDto.getCantCuatrimestres()));

            if (codigo <= 0) {
                throw new BadRequestException("Código de carrera no puede ser menor a 1.");
            }

            if (cuatris <= 0 || cuatris > 12) {
                throw new BadRequestException("La cantidad de cuatrimestres no puede ser 0 o mayor a 12.");
            }

            if (carreraDto.getNombre() == null) {
                throw new BadRequestException("Todos los campos son obligatorios.");
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
            throw new NumberFormatException("El ID de la carrera debe contener solo números.");
        }
    }

    @GetMapping("/carreras")
    public HashMap<String, List<String>> getTodasCarreras(){return carreraService.getTodasLasCarreras();}

    @DeleteMapping("/{codigocarrera}")
    public Carrera borrarCarrera(@PathVariable String codigocarrera){
        try {
            int codigo = Integer.parseInt(codigocarrera);
            return carreraService.borrarCarrera(codigo);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("El codigo de la carrera debe contener solo números.");
        }
    }
}
