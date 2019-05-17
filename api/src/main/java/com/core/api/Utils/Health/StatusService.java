package com.core.api.Utils.Health;

import org.springframework.stereotype.Service;

@Service
public class StatusService {

    public String getStatus(){
        return "OK";
    }
}
