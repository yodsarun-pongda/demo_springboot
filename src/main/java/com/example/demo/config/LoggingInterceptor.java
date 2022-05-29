package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
public class LoggingInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        log.debug("Request body: [{}] httpMethod: [{}]", new String(body, StandardCharsets.UTF_8), request.getMethod());
        ClientHttpResponse response = execution.execute(request, body);
        InputStreamReader isr = new InputStreamReader(response.getBody(), StandardCharsets.UTF_8);
        String bodyString = new BufferedReader(isr).lines().collect(Collectors.joining("\n"));
        log.debug("Response body: [{}] Header: [{}]", bodyString, response.getHeaders());
        return response;
    }
}
