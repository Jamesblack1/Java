package com.core.api.Utils.Tools;

import org.springframework.stereotype.Component;

@Component
public class ToolBD {

    public String builderUrlConnection(String url, String user, String pass){
        String respuesta;
        if(url.length()>1 && user.length()>1 && pass.length()>1){
            String urlBuilder[] = url.split("@");
            respuesta = urlBuilder[0] + user + "/" + pass + "@" + urlBuilder[1];
        }else{
            respuesta = "Faltan Parametros";
        }
        return respuesta;
    }
}
