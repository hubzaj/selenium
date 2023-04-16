package org.housecallpro.utils.test;

import org.assertj.core.api.Assertions;
import org.housecallpro.utils.PriceUtils;
import org.junit.jupiter.api.Test;

public class PriceUtilsTest {

    @Test
    void shouldCalculateTotalPriceWithCustomFormat() {
        // Given
        int price = 2000;
        int quantity = 3;

        // When
        String actual = PriceUtils.calculateTotalPrice(quantity, price);

        // Then
        Assertions.assertThat(actual).isEqualTo("$6000.00");
    }

}
