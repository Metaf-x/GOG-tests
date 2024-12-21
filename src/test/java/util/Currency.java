package util;

public enum Currency {
    USD("$"), RUB("₽");
    public final String symbol;

    Currency(String symbol) {
        this.symbol = symbol;
    }
}
