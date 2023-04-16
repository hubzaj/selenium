package org.housecallpro.utils.test;

import org.assertj.core.api.Assertions;
import org.housecallpro.utils.PriceUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class PriceUtilsTest {

    @Test
    void shouldCalculateTotalPriceWithCustomFormat() {
        // Given
        int quantity = 3;
        int price = 2000;

        // When
        String actual = PriceUtils.calculateTotalPrice(quantity, price);

        // Then
        Assertions.assertThat(actual).isEqualTo("$6000.00");
    }

    private static Stream<Arguments> stringTestCases() {
        return Stream.of(
                Arguments.of("3", "2000", "$6000.00"),
                Arguments.of("3.00", "2000.00", "$6000.00"),
                Arguments.of("3.00", "$2000.00", "$6000.00"),
                Arguments.of("1.00", "0.00", "$0.00")
        );
    }

    @ParameterizedTest
    @MethodSource("stringTestCases")
    void shouldCalculateTotalPriceWithCustomFormatForStringInput(String qty, String priceUnit, String expected) {
        // When
        String actual = PriceUtils.calculateTotalPrice(qty, priceUnit);

        // Then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

}
