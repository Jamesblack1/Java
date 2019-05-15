package com.core.api.json.response;

import java.util.ArrayList;
import java.util.List;

public class StatusAppMetricasResponse {

    private List<StatusMetricasResponse> status = new ArrayList<>();

    public List<StatusMetricasResponse> getStatus() {
        return status;
    }

    public void setStatus(List<StatusMetricasResponse> status) {
        this.status = status;
    }

}
