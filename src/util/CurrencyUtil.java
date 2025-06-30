package util;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtil {

    public static String formatRupiah(int amount) {
        Locale indonesia = Locale.forLanguageTag("id-ID");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(indonesia);
        return formatter.format(amount);
    }

    public static String formatRupiahPlain(int amount) {
        Locale indonesia = Locale.forLanguageTag("id-ID");
        NumberFormat formatter = NumberFormat.getNumberInstance(indonesia);
        return formatter.format(amount);
    }
}