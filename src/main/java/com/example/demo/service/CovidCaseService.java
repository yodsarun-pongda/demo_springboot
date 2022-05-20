package com.example.demo.service;

import com.example.demo.model.CaseCovidByProvinceData;
import com.example.demo.model.CovidToDayCaseData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ReferenceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class CovidCaseService {
    @Autowired
    private CovidToDayCaseData covidToDayCaseData;

    private RestTemplate restTemplate = new RestTemplate();

    public CovidToDayCaseData getCovidToDayCaseDataToDay() throws JsonProcessingException {
        String url = "https://covid19.ddc.moph.go.th/api/Cases/today-cases-all";
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        String responseEntityBody = responseEntity.getBody();
        byte[] ptext = responseEntityBody.getBytes(StandardCharsets.ISO_8859_1);
        responseEntityBody = new String(ptext, StandardCharsets.UTF_8);
        try {
            CovidToDayCaseData covidToDayCaseDataResponse = (CovidToDayCaseData) new ObjectMapper().readValue(responseEntityBody, new TypeReference<List<CovidToDayCaseData>>(){}).get(0);
            return covidToDayCaseDataResponse;
        } catch (Exception e) {
            return null;
        }
    }

    public List<CaseCovidByProvinceData> getCovidByProvinceData() {
        String url = "https://covid19.ddc.moph.go.th/api/Cases/today-cases-by-provinces";
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        String responseEntityBody = responseEntity.getBody();
        byte[] ptext = responseEntityBody.getBytes(StandardCharsets.ISO_8859_1);
        responseEntityBody = new String(ptext, StandardCharsets.UTF_8);
        try {
            List<CaseCovidByProvinceData> caseCovidByProvinceDataList = (List<CaseCovidByProvinceData>) new ObjectMapper().readValue(responseEntityBody, new TypeReference<List<CaseCovidByProvinceData>>(){});
            return caseCovidByProvinceDataList;
        } catch (Exception e) {
            return null;
        }
    }

    public CaseCovidByProvinceData filterCovidCaseByProvinceName(String provinceName) {
        List<CaseCovidByProvinceData> caseCovidByProvinceDataList = getCovidByProvinceData();
        CaseCovidByProvinceData caseCovidByProvinceData = caseCovidByProvinceDataList.stream().filter(covidCase -> covidCase.getProvince().equals(provinceName)).collect(toSingleton());
        return caseCovidByProvinceData;
    }

    private static <T> Collector<T, ?, T> toSingleton() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() != 1) {
                        throw new IllegalStateException();
                    }
                    return list.get(0);
                }
        );
    }
}
