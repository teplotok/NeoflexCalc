package com.calculator.test.services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Service
public class Holidays {
    public enum Workday {
        //перечисление дней, которые были выходными но их перенесли на другую дату. Пример:05.03.2022 это суббота, однако это рабочий сокращенный день, выходной был перенесен на понедельник 07.03.2022
        FIFTH_MARCH_DAY(LocalDate.of(2022, 3, 5));
        private final LocalDate localDate;

        Workday(LocalDate localDate) {
            this.localDate = localDate;
        }
    }

    public enum Holiday {
        //перечисление праздничных дней
        FIRST_WINTER_DAY(LocalDate.of(2022, 1, 1)),
        SECOND_WINTER_DAY(LocalDate.of(2022, 1, 2)),
        THIRD_WINTER_DAY(LocalDate.of(2022, 1, 3)),
        FOURTH_WINTER_DAY(LocalDate.of(2022, 1, 4)),
        FIFTH_WINTER_DAY(LocalDate.of(2022, 1, 5)),
        SIXTH_WINTER_DAY(LocalDate.of(2022, 1, 6)),
        CHRIST_DAY(LocalDate.of(2022, 1, 7)),
        EIGHT_WINTER_DAY(LocalDate.of(2022, 1, 8)),
        DEFENDERS_DAY(LocalDate.of(2022, 2, 23)),
        WOMAN_DAY(LocalDate.of(2022, 3, 8)),
        SPRING_DAY(LocalDate.of(2022, 5, 1)),
        VICTORY_DAY(LocalDate.of(2022, 5, 9)),
        RUSSIA_DAY(LocalDate.of(2022, 6, 12)),
        UNITY_DAY(LocalDate.of(2022, 11, 4)),


        SEVENTH_MARCH_DAY(LocalDate.of(2022,3,7)),
        THIRD_MAY_DAY(LocalDate.of(2022,5,3)),
        TENTH_MAY_DAY(LocalDate.of(2022,5,10)),
        THIRTEEN_JUNE_DAY(LocalDate.of(2022,6,13));


        private final LocalDate localDate;

        Holiday(LocalDate localDate) {
            this.localDate = localDate;
        }
    }
    public Set<LocalDate> getHolidays(){
        HashSet<LocalDate> holidays=new HashSet<>();
        for(Holiday hol:Holiday.values()){
            LocalDate date=LocalDate.of(hol.localDate.getYear(),hol.localDate.getMonth(),hol.localDate.getDayOfMonth());
            holidays.add(date);
        }
        return holidays;
    }

    public Set<LocalDate> getWorkdays(){
        HashSet<LocalDate> workdays=new HashSet<>();
        for(Workday hol:Workday.values())workdays.add(LocalDate.of(hol.localDate.getYear(),hol.localDate.getMonth(),hol.localDate.getDayOfMonth()));
        return workdays;
    }
}
