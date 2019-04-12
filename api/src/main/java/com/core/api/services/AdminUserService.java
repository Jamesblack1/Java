package com.core.api.services;

import com.core.api.models.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.core.api.mapper.AdminUserMapper;
import java.util.List;

@Service
public class AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    public String getNombre(Integer id){
        System.out.println("getNombre :: ID -> :: "+ id);
        return this.adminUserMapper.getNombre(id);
    }
    public Persona getPersona(Integer id){
        System.out.println("getNombre :: ID -> :: "+ id);
        return this.adminUserMapper.getPersona(id);
    }

    public Integer insertaPersona(Persona persona){
        System.out.println("insertaPersona :: Nombre y Apellido -> :: "+ persona.toString());
        return this.adminUserMapper.insertaPersona(persona);
    }

    public Integer deletePersona(Integer id){
        System.out.println("deletePersona :: ID -> :: "+ id);
        return this.adminUserMapper.deletePersona(id);
    }

    public void getListPersonas(){
        System.out.println("getListPersonas  :: ");
        List<Persona> listado = this.adminUserMapper.getListPersonas();
        for (Persona persona: listado) {
            System.out.println("Persona -> :: " + persona.getNombre() + " "+ persona.getApellido());
        }
        System.out.println("PROCESO FINALIZADO");
    }

    public Integer actualizaPersona(Persona newData){
        System.out.println("actualizaPersona :: Inicio ");
        if(newData.getId() == null){
            System.out.println("EL ID DE ENTRADA ES NULL :: FIN ");
            return 0;
        }else{
            System.out.println("Parametros Entrada:: -> ::" + newData.toString());
            Persona oldData = this.getPersona(newData.getId());
            System.out.println("Parametros sacados de la bd:: -> ::" + oldData.toString());
            //VALIDACIÓN PARAMETROS
            String nombre = (newData.getNombre() != "" && newData.getNombre() != null) ? newData.getNombre() : oldData.getNombre();
            String apellido = (newData.getApellido() != "" && newData.getApellido() != null) ? newData.getApellido(): oldData.getApellido();

            newData.setNombre(nombre);
            newData.setApellido(apellido);
            System.out.println("Parametros enviados:: -> ::" + newData.toString());

            return this.adminUserMapper.actualizaPersona(newData);
        }
    }
}
