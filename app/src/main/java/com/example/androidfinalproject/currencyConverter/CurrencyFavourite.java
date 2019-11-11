package com.example.androidfinalproject.currencyConverter;

/**
 * This class describe currencyfavourite object
 */
public class CurrencyFavourite {
    private String base;
    private String result;
    private long id;

    public CurrencyFavourite(String base, String result, long id){
        setBase(base);
        setResult(result);
        setId(id);
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
