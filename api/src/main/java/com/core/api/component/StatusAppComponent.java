package com.core.api.component;

import com.core.api.Utils.Health.StatusService;
import com.core.api.json.StatusAppMetricasResponse;
import com.core.api.json.StatusMetricasResponse;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class StatusAppComponent {

    private static final Logger LOG = LoggerFactory.getLogger(StatusAppComponent.class);

    private static final StatusAppComponent _instanceSingleton = new StatusAppComponent();

    @Autowired
    private StatusAppJdbcComponent statusAppJdbcComponent;


    private static StatusAppMetricasResponse _statusResponse = null;

    private Gson gson = new Gson();

    private ApplicationContext applicationContext = null;

    private StatusService statusService;

    public static StatusAppComponent getInstance(ApplicationContext applicationContext) {
        _instanceSingleton.applicationContext = applicationContext;
        if (_instanceSingleton.statusService == null) {
            _instanceSingleton.statusService = (StatusService) _instanceSingleton.applicationContext.getBean(StatusService.class);
        }
        return _instanceSingleton;
    }

    public static StatusAppComponent getInstance() {
        return _instanceSingleton;
    }


    public StatusAppMetricasResponse getStatusInstance() throws Exception {
        StatusAppMetricasResponse statusResponse = new StatusAppMetricasResponse();
        statusResponse.getStatus().add(this.getStatusServicio());
        statusResponse.getStatus().add(this.getStatusMetricasConnectJDBC());
        _statusResponse = statusResponse;
        LOG.info("Analizando STATUS :: {}", this.gson.toJson(_statusResponse));
        return _statusResponse;
    }

    /**
     * @return
     * @throws Exception
     */
    public StatusAppMetricasResponse getStatus() throws Exception {
        if (_statusResponse == null) {
            _statusResponse = this.getStatusInstance();
        }
        return _statusResponse;
    }

    public StatusMetricasResponse getStatusServicio() {
        String request = this.statusService.getStatus();
        String status = "ERROR";
        LOG.info(" Request Status {}", request);
        if (request != null && !"".equals(request)) {
            status = "OK";
        }

        StatusMetricasResponse response = new StatusMetricasResponse();
        response.getService().setName("Status APP");
        response.getService().setStatus(status);
        return response;
    }

    protected StatusMetricasResponse getStatusMetricasConnectJDBC() throws Exception {
        StatusMetricasResponse response = new StatusMetricasResponse();
        response.getService().setName("BD");
        response.getService().setUrl(this.statusAppJdbcComponent.getAppUrlJDBC());
        response.getTransport().setHost("");
        response.getTransport().setPort(0);
        response.getTransport().setProtocol("JDBC");
        String status = "ERROR";
        LOG.info("boolean :: -> {}", this.statusAppJdbcComponent.testConnectionBD());
       if (this.statusAppJdbcComponent.testConnectionBD()) {
            status = "OK";
        }
        response.getTransport().setStatus(status);
        return response;
    }

}
