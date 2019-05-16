package com.core.api.services;

import com.core.api.models.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.core.api.mapper.AdminUserMapper;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminUserService.class);

    public String getNombre(Integer id){
        LOGGER.info("getNombre :: ID -> :: {0}", id);
        return this.adminUserMapper.getNombre(id);
    }
    public Persona getPersona(Integer id){
        LOGGER.info("getNombre :: ID -> :: {0}", id);
        return this.adminUserMapper.getPersona(id);
    }

    public Integer insertaPersona(Persona persona){
        LOGGER.info("insertaPersona :: Nombre y Apellido -> :: {0}", persona.toString());
        return this.adminUserMapper.insertaPersona(persona);
    }

    public Integer deletePersona(Integer id){
        LOGGER.info("deletePersona :: ID -> :: {0}", id);
        return this.adminUserMapper.deletePersona(id);
    }

    public void getListPersonas(){
        LOGGER.info("getListPersonas  :: ");
        List<Persona> listado = this.adminUserMapper.getListPersonas();
        for (Persona persona: listado) {
            LOGGER.info("Persona -> :: {}" , persona.toString());
        }
        LOGGER.info("PROCESO FINALIZADO");
    }

    public Integer actualizaPersona(Persona newData){
        LOGGER.info("actualizaPersona :: Inicio ");
        if(newData.getId() == null){
            LOGGER.info("EL ID DE ENTRADA ES NULL :: FIN ");
            return 0;
        }else{
            LOGGER.info("Parametros Entrada:: -> ::" + newData.toString());
            Persona oldData = this.getPersona(newData.getId());
            LOGGER.info("Parametros sacados de la bd:: -> ::{0}" , oldData.toString());
            //VALIDACIÓN PARAMETROS


            String nombre = (newData.getNombre() != "" && newData.getNombre() != null) ? newData.getNombre() : oldData.getNombre();
            String apellido = (newData.getApellido() != "" && newData.getApellido() != null) ? newData.getApellido(): oldData.getApellido();

            newData.setNombre(nombre);
            newData.setApellido(apellido);
            LOGGER.info("Parametros enviados:: -> :: {0}" , newData.toString());

            return this.adminUserMapper.actualizaPersona(newData);
        }
    }
}
