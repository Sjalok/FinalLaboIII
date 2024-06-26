package com.TUP.Final_LaboIII.controller;

import com.TUP.Final_LaboIII.business.CarreraService;
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
        return carreraService.crearCarrera(carreraDto);
    }

    @GetMapping("/{codigocarrera}")
    public Carrera getCarrera(@PathVariable int codigocarrera) {
        return carreraService.getCarrera(codigocarrera);
    }

    @GetMapping("/carreras")
    public HashMap<String, List<String>[]> getTodasCarreras(){return carreraService.getTodasLasCarreras();}

    @PutMapping("/{codigocarrera}")
    public Carrera putCarrera(@PathVariable int codigocarrera, @RequestBody Carrera carrera){
        return carreraService.saveCarrera(codigocarrera, carrera);
    }

    @DeleteMapping("/{codigocarrera}")
    public Carrera borrarCarrera(@PathVariable int codigocarrera){
        return carreraService.borrarCarrera(codigocarrera);
    }
}
