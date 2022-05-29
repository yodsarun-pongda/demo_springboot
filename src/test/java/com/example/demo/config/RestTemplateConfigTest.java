package com.example.demo.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RestTemplateConfigTest {
    @Test
    public void callCustomRestTemplateApiTest() {
        RestTemplateConfig restTemplateConfig = new RestTemplateConfig();
        String result = restTemplateConfig.callCustomRestTemplateApi();
    }
}
