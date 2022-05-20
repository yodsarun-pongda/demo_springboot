package com.example.demo.service;

import com.example.demo.model.CaseCovidByProvinceData;
import com.example.demo.model.CovidToDayCaseData;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CovidCaseServiceTest {
    @InjectMocks
    private CovidCaseService covidCaseService;

    @Mock
    private CovidToDayCaseData covidToDayCaseData;

//    @Before
//    void setUp() {
//        System.out.println("Hello World!!");
//    }

    @Test
    @DisplayName("Call covid today case")
    void getCovidToDayCaseDataToDay() throws JsonProcessingException {
        //Arrange
//        when(covidCaseService.callCaseToDay()).thenReturn(any(CovidToDayCaseData.class));
//        doNothing().when(covidCaseService).callCaseToDay();

        //Act
        CovidToDayCaseData covidToDayCaseDataResponse = covidCaseService.getCovidToDayCaseDataToDay();

        //Assert
        Assert.assertNotNull(covidToDayCaseDataResponse);
        Assert.assertTrue(covidToDayCaseDataResponse.getTotal_case().compareTo(new BigDecimal("0")) > 0);
        verify(covidToDayCaseData, times(1));
    }

    @Test
    void getCovidByProvinceDataTest() throws JsonProcessingException {
        List<CaseCovidByProvinceData> caseCovidByProvinceDataList = covidCaseService.getCovidByProvinceData();
        Assert.assertTrue(caseCovidByProvinceDataList.isEmpty() == false);
    }

    @Test
    void getCovidCaseByProvinceNameTest() {
        CaseCovidByProvinceData caseCovidByProvinceData = covidCaseService.filterCovidCaseByProvinceName("กรุงเทพมหานคร");
        Assert.assertNotNull(caseCovidByProvinceData);
        Assert.assertEquals("กรุงเทพมหานคร", caseCovidByProvinceData.getProvince());
    }
}
