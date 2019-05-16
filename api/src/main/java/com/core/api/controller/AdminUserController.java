package com.core.api.controller;

import com.core.api.component.StatusAppComponent;
import com.core.api.models.Persona;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Busca persona por id", response = String.class)
    @PostMapping(value = "/persona/{id}")
    public @ResponseBody
    String buscaPersona(@PathVariable("id") Integer id){
        LOGGER.info("buscaPersona :: ID -> :: {0}", id);
        return this.adminUserService.getNombre(id);
    }

    @ApiOperation(value = "Crea una persona", response = Integer.class)
    @PostMapping(value = "/crearPersona")
    public @ResponseBody Integer creaPersona(@RequestBody Persona persona){
        LOGGER.info("creaPersona PRIMERA PARTE :: DATOS -> :: {0}" , persona.toString());
        return this.adminUserService.insertaPersona(persona);

    }

    @ApiOperation(value = "Borrra una persona", response = Integer.class)
    @PostMapping(value = "/delete/persona/{id}")
    public @ResponseBody Integer deletePersona(@PathVariable("id") Integer id){
        LOGGER.info("deletePersona :: ID -> :: {0}", id);
        return this.adminUserService.deletePersona(id);
    }

    @ApiOperation(value = "Trae un listado de personas", response = Integer.class)
    @GetMapping(value = "/personas")
    public @ResponseBody void buscaPersonas(){
        LOGGER.info("BUSCA PERSONAS INICIO");
        this.adminUserService.getListPersonas();
        LOGGER.info("BUSCA PERSONAS TERMINO");
    }

    @ApiOperation(value = "Actualiza una persona", response = Integer.class)
    @PutMapping(value = "/actualiza/persona")
    public @ResponseBody Integer actualizaPersona(@RequestBody Persona persona){
        LOGGER.info("ACTUALIZA PERSONAS INICIO");
        return this.adminUserService.actualizaPersona(persona);
    }


}
