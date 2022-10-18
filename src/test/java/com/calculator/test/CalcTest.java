package com.calculator.test;


import com.calculator.test.services.CalcService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
public class CalcTest {

    @Autowired
    CalcService calcService;

    @Test
    void calc(){
        Assertions.assertEquals(new BigDecimal("42662.25"),calcService.calculate(BigDecimal.valueOf(50000),25));
        Assertions.assertEquals(new BigDecimal("24428.04"),calcService.calculate(BigDecimal.valueOf(25562),28));
        Assertions.assertEquals(new BigDecimal("15481.34"),calcService.calculate(BigDecimal.valueOf(32400),14));
    }

    @Test
    void calcWorkdays(){
        Assertions.assertEquals(12,calcService.calcWorkdays(LocalDate.of(2022,10,01),LocalDate.of(2022,10,18)));
        Assertions.assertEquals(10,calcService.calcWorkdays(LocalDate.of(2021,12,25),LocalDate.of(2022,01,15)));
        Assertions.assertEquals(11,calcService.calcWorkdays(LocalDate.of(2022,06,01),LocalDate.of(2022,06,16)));
    }

    @Test
    void calcWithPeriod(){
        Assertions.assertEquals(new BigDecimal("38225.28"),calcService.calculate(BigDecimal.valueOf(70000),LocalDate.of(2022,7,15),LocalDate.of(2022,8,7)));
        Assertions.assertEquals(new BigDecimal("18798.72"),calcService.calculate(BigDecimal.valueOf(45900),LocalDate.of(2022,5,1),LocalDate.of(2022,5,20)));
        Assertions.assertEquals(new BigDecimal("9829.44"),calcService.calculate(BigDecimal.valueOf(24000),LocalDate.of(2022,6,1),LocalDate.of(2022,6,19)));

    }
}