package com.example.androidfinalproject.currencyConverter;

/**
 * This class describe currency object
 * @author :Wajahat
 */
public class CurrencyObject {
    private String base;
    private String name;
    private String rate;

    /**
     * Init constructor
     * @param name
     * @param rate
     * @param base
     */
    public CurrencyObject(String name, String rate, String base){
        setName(name);
        setRate(rate);
        setBase(base);
    }

    /**
     * getName
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * setName
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getRate
     * @return
     */
    public String getRate() {
        return rate;
    }

    /**
     * setRate
     * @param rate
     */
    public void setRate(String rate) {
        this.rate = rate;
    }

    /**
     * getBase
     * @return
     */
    public String getBase() {
        return base;
    }

    /**
     * This metthod setBase
     * @param base
     */
    public void setBase(String base) {
        this.base = base;
    }
}
