package com.core.api.controller;

import com.core.api.models.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.core.api.services.AdminUserService;

@RestController
public class AdminUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminUserController.class);

    @Autowired
    private AdminUserService adminUserService;



    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @PostMapping(value = "/persona/{id}")
    public @ResponseBody
    String buscaPersona(@PathVariable("id") Integer id){
        LOGGER.info("buscaPersona :: ID -> :: "+ id);
        return this.adminUserService.getNombre(id);
    }

    @PostMapping(value = "/crearPersona")
    public @ResponseBody Integer creaPersona(@RequestBody Persona persona){
        LOGGER.info("creaPersona PRIMERA PARTE :: DATOS -> :: " + persona.toString());
        return this.adminUserService.insertaPersona(persona);

    }

    @PostMapping(value = "/delete/persona/{id}")
    public @ResponseBody Integer deletePersona(@PathVariable("id") Integer id){
        LOGGER.info("deletePersona :: ID -> :: "+ id);
        return this.adminUserService.deletePersona(id);
    }

    @GetMapping(value = "/personas")
    public @ResponseBody void buscaPersonas(){
        LOGGER.info("BUSCA PERSONAS INICIO");
        this.adminUserService.getListPersonas();
        LOGGER.info("BUSCA PERSONAS TERMINO");
    }

    @PutMapping(value = "/actualiza/persona")
    public @ResponseBody Integer actualizaPersona(@RequestBody Persona persona){
        LOGGER.info("ACTUALIZA PERSONAS INICIO");
        return this.adminUserService.actualizaPersona(persona);
    }
}
