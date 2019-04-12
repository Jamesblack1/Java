package com.core.api.controller;

import com.core.api.models.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.core.api.services.AdminUserService;

@RestController
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;



    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @PostMapping(value = "/persona/{id}")
    public @ResponseBody
    String buscaPersona2(@PathVariable("id") Integer id){
        System.out.println("buscaPersona :: ID -> :: "+ id);
        return this.adminUserService.getNombre(id);
    }

    @PostMapping(value = "/crearPersona")
    public @ResponseBody Integer creaPersona(@RequestBody Persona persona){
        System.out.println("creaPersona PRIMERA PARTE :: DATOS -> :: " + persona.toString());
        return this.adminUserService.insertaPersona(persona);

    }

    @PostMapping(value = "/delete/persona/{id}")
    public @ResponseBody Integer deletePersona(@PathVariable("id") Integer id){
        System.out.println("deletePersona :: ID -> :: "+ id);
        return this.adminUserService.deletePersona(id);
    }

    @GetMapping(value = "/personas")
    public @ResponseBody void buscaPersonas(){
        System.out.println("BUSCA PERSONAS INICIO");
        this.adminUserService.getListPersonas();
        System.out.println("BUSCA PERSONAS TERMINO");
    }

    @PutMapping(value = "/actualiza/persona")
    public @ResponseBody Integer actualizaPersona(@RequestBody Persona persona){
        System.out.println("ACTUALIZA PERSONAS INICIO");
        return this.adminUserService.actualizaPersona(persona);
    }
}
