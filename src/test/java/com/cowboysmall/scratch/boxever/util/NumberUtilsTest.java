package com.cowboysmall.scratch.boxever.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class NumberUtilsTest {

    @Test
    public void toBigDecimal() {

        assertThat(NumberUtils.toBigDecimal(Integer.parseInt("100")), is(new BigDecimal("100")));
        assertThat(NumberUtils.toBigDecimal(Long.parseLong("100")), is(new BigDecimal("100")));
        assertThat(NumberUtils.toBigDecimal(Float.parseFloat("100.0")), is(new BigDecimal("100.0")));
        assertThat(NumberUtils.toBigDecimal(Double.parseDouble("100.0")), is(new BigDecimal("100.0")));
    }
}
