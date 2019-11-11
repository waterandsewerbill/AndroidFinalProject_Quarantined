package com.example.androidfinalproject.currencyConverter;

/**
 * This class describe currency object
 */
public class CurrencyObject {
    private String base;
    private String name;
    private String rate;

    public CurrencyObject(String name, String rate, String base){
        setName(name);
        setRate(rate);
        setBase(base);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }


    public String getBase() {
        return base;
    }

    /**
     * This metthod set
     * @param base
     */
    public void setBase(String base) {
        this.base = base;
    }
}
