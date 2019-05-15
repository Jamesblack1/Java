package com.core.api.controller;

import com.core.api.component.StatusAppComponent;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

@RestController
public class MetricasController {
    private static final Logger LOG = LoggerFactory.getLogger(MetricasController.class);

    @Autowired
    private StatusAppComponent statusApp;



    @GetMapping(value = "/actuator/status", produces = "application/json")
    public @ResponseBody
    String getStatus() throws Exception {
        LOG.info("status INICIO");
        String json = new Gson().toJson(this.statusApp.getStatus());
        LOG.info("Status [response] -> {}", json);
        LOG.info("status TERMINO");
        return json;
    }

}
