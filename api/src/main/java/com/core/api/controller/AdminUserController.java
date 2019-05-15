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

    @ApiOperation(value = "Find an user", notes = "Return a user by Id" )
    @PostMapping(value = "/persona/{id}")
    public @ResponseBody
    String buscaPersona(@PathVariable("id") Integer id){
        LOGGER.info("buscaPersona :: ID -> :: {0}", id);
        return this.adminUserService.getNombre(id);
    }

    @PostMapping(value = "/crearPersona")
    public @ResponseBody Integer creaPersona(@RequestBody Persona persona){
        LOGGER.info("creaPersona PRIMERA PARTE :: DATOS -> :: {0}" , persona.toString());
        return this.adminUserService.insertaPersona(persona);

    }

    @PostMapping(value = "/delete/persona/{id}")
    public @ResponseBody Integer deletePersona(@PathVariable("id") Integer id){
        LOGGER.info("deletePersona :: ID -> :: {0}", id);
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

    @GetMapping(value = "/status", produces = "application/json")
    public @ResponseBody
    String getStatus() throws Exception {
        LOGGER.info("status INICIO");
        String json = new Gson().toJson(StatusAppComponent.getInstance().getStatus());
        LOGGER.info("Status [response] -> {}", json);
        LOGGER.info("status TERMINO");
        return json;
    }
}
