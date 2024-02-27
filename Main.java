import java.util.Scanner;

// Currency interface representing different currencies
interface Currency {
    String getCurrencyCode();

    double convertTo(double amount, Currency targetCurrency);
}

// Concrete implementation of Currency
class USDollar implements Currency {
    private static final String CURRENCY_CODE = "USD";

    @Override
    public String getCurrencyCode() {
        return CURRENCY_CODE;
    }

    @Override
    public double convertTo(double amount, Currency targetCurrency) {
        // Simplified conversion rates for demonstration
        if (targetCurrency instanceof Euro) {
            return amount * 0.85;
        } else if (targetCurrency instanceof JapaneseYen) {
            return amount * 108.92;
        } else {
            // Default to no conversion
            return amount;
        }
    }
}

class Euro implements Currency {
    private static final String CURRENCY_CODE = "EUR";

    @Override
    public String getCurrencyCode() {
        return CURRENCY_CODE;
    }

    @Override
    public double convertTo(double amount, Currency targetCurrency) {
        // Simplified conversion rates for demonstration
        if (targetCurrency instanceof USDollar) {
            return amount * 1.18;
        } else if (targetCurrency instanceof JapaneseYen) {
            return amount * 127.68;
        } else {
            // Default to no conversion
            return amount;
        }
    }
}

class JapaneseYen implements Currency {
    private static final String CURRENCY_CODE = "JPY";

    @Override
    public String getCurrencyCode() {
        return CURRENCY_CODE;
    }

    @Override
    public double convertTo(double amount, Currency targetCurrency) {
        // Simplified conversion rates for demonstration
        if (targetCurrency instanceof USDollar) {
            return amount * 0.0092;
        } else if (targetCurrency instanceof Euro) {
            return amount * 0.0078;
        } else {
            // Default to no conversion
            return amount;
        }
    }
}

// CurrencyConverter class responsible for performing conversions
class CurrencyConverter {
    public double convert(double amount, Currency sourceCurrency, Currency targetCurrency) {
        if (sourceCurrency == null || targetCurrency == null) {
            throw new IllegalArgumentException("Invalid currency");
        }

        if (sourceCurrency.equals(targetCurrency)) {
            return amount; // No conversion needed
        }

        // Perform conversion
        return sourceCurrency.convertTo(amount, targetCurrency);
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write the number: ");
        double amountToConvert = scanner.nextInt();
        
        CurrencyConverter currencyConverter = new CurrencyConverter();

        Currency usDollar = new USDollar();
        Currency euro = new Euro();
        Currency japaneseYen = new JapaneseYen();


        // Convert USD to Euro
        double convertedToEuro = currencyConverter.convert(amountToConvert, usDollar, euro);
        System.out.println(amountToConvert + " USD is equivalent to " + convertedToEuro + " EUR");

        // Convert Euro to JPY
        double convertedToYen = currencyConverter.convert(convertedToEuro, euro, japaneseYen);
        System.out.println(convertedToEuro + " EUR is equivalent to " + convertedToYen + " JPY");
    }
}
