package com.xiaoniucr.jdk8;


import java.time.LocalDate;

public class LocalDateTest {

    public static void main(String[] args) {

        LocalDate today = LocalDate.parse("2019-07-28");
        LocalDate oneMonthBefore = today.minusMonths(1);
        LocalDate threeMonthBefore = today.minusMonths(3);
        LocalDate sixMonthBefore = today.minusMonths(6);

        System.out.println(today);
        System.out.println(oneMonthBefore + "====>" +today.minusDays(1));
        System.out.println(threeMonthBefore + "===>" +oneMonthBefore.minusDays(1));
        System.out.println(sixMonthBefore + "===>" + threeMonthBefore.minusDays(1));
        System.out.println("<===" +sixMonthBefore.minusDays(1));
    }
}
