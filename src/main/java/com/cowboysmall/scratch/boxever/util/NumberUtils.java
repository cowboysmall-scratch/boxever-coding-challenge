package com.cowboysmall.scratch.boxever.util;

import java.math.BigDecimal;

public class NumberUtils {

    private NumberUtils() {
    }


    //_________________________________________________________________________

    public static BigDecimal toBigDecimal(Number number) {

        return new BigDecimal(number.toString());
    }
}
