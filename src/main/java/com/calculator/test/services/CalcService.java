package com.calculator.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class CalcService {

    @Autowired
    Holidays holidays;

    public BigDecimal calculate(BigDecimal salary,Integer vac_duration){  //основная функция подсчета по формуле (зарплата/29.3)*колличество дней в отпуске
        return salary.divide(BigDecimal.valueOf(29.3),2, RoundingMode.CEILING).multiply(BigDecimal.valueOf(vac_duration));
    }

    public BigDecimal calculate(BigDecimal salary, LocalDate start_vac_date,LocalDate end_vac_date){
        return calculate(salary,calcWorkdays(start_vac_date,end_vac_date));
    }

    public Integer calcWorkdays(LocalDate start_vac_date,LocalDate end_vac_date){ //функция,считающая рабочие дни в отпуске
        int workdays = 0;

        for(LocalDate date:holidays.getWorkdays()) {  //сначала сразу учитываются особые дни, которые считаются рабочими, но будут пропущены в следующем цикле как выходные
            if (start_vac_date.isBefore(date) && end_vac_date.isAfter(date))workdays++;
        }
        while(start_vac_date.isBefore(end_vac_date.plusDays(1))){
            if(!(start_vac_date.getDayOfWeek().getValue()==6 ||start_vac_date.getDayOfWeek().getValue()==7 ||holidays.getHolidays().contains(start_vac_date))){
                workdays++;
            }
            start_vac_date=start_vac_date.plusDays(1);
        }
        return workdays;
    }
}
