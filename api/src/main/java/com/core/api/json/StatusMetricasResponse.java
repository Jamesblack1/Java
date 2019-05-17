package com.core.api.json;

public class StatusMetricasResponse {

    private TransportMetricasResponse transport = new TransportMetricasResponse();
    private ServiceMetricasResponse service = new ServiceMetricasResponse();

    public TransportMetricasResponse getTransport() {
        return transport;
    }

    public void setTransport(TransportMetricasResponse transport) {
        this.transport = transport;
    }

    public ServiceMetricasResponse getService() {
        return service;
    }

    public void setService(ServiceMetricasResponse service) {
        this.service = service;
    }


}
