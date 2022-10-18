package com.calculator.test.controllers;


import com.calculator.test.services.CalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@RestController
public class CalcController {

    @Autowired
    CalcService calcService;

    @GetMapping("/calculate")
    public ResponseEntity calc(@RequestParam BigDecimal salary,@RequestParam(defaultValue = "0") Integer vac_duration,@RequestParam(required = false) String start_vac, @RequestParam(required = false) String end_vac){
        if(start_vac!=null && end_vac!=null) {
            try {
                LocalDate start_vac_date = LocalDate.parse(start_vac);
                LocalDate end_vac_date = LocalDate.parse(end_vac);
                if(start_vac_date.isAfter(end_vac_date)|| start_vac_date.isEqual(end_vac_date))return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Start date must be earlier than end date!");
                return ResponseEntity.ok(calcService.calculate(salary,start_vac_date,end_vac_date));
            } catch (DateTimeParseException ex) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't parse dates!Correct format:YYYY-MM-DD");
            }
        }
        else if(start_vac!=null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("End date of vacation not present!");
        else if(end_vac!=null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Start date of vacation not present!");
        if(vac_duration<=0)return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duration must be >0!");
        return ResponseEntity.ok(calcService.calculate(salary,vac_duration));
    }
}

