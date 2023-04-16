package org.housecallpro.utils;

import lombok.experimental.UtilityClass;
import org.javamoney.moneta.Money;
import org.javamoney.moneta.format.CurrencyStyle;

import javax.money.Monetary;
import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;

import static java.util.Locale.US;

@UtilityClass
public class PriceUtils {

    private static final MonetaryAmountFormat FORMAT = MonetaryFormats.getAmountFormat(AmountFormatQueryBuilder
            .of(US)
            .set(CurrencyStyle.SYMBOL)
            .set("pattern", "¤0.00")
            .build());

    public String calculateTotalPrice(double quantity, double price) {
        Money money = Money.of(price, Monetary.getCurrency(US));
        money = money.multiply(quantity);
        return FORMAT.format(money);
    }

    public String calculateTotalPrice(String quantity, String price) {
        price = price.contains("$") ? price.replace("$", "") : price;
        return calculateTotalPrice(Double.parseDouble(quantity), Double.parseDouble(price));
    }

}
