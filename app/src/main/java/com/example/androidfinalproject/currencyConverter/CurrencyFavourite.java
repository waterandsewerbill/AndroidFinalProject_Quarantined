package com.example.androidfinalproject.currencyConverter;

/**
 * This class describe currencyfavourite object
 * @author :Wajahat
 */
public class CurrencyFavourite {
    private String base;
    private String result;
    private long id;

    /**
     * Init constructor
     * @param base
     * @param result
     * @param id
     */
    public CurrencyFavourite(String base, String result, long id) {
        setBase(base);
        setResult(result);
        setId(id);
    }

    /**
     * get Base
     * @return
     */
    public String getBase() {
        return base;
    }

    /**
     * setBase
     * @param base
     */
    public void setBase(String base) {
        this.base = base;
    }

    /**
     * getResult
     * @return
     */
    public String getResult() {
        return result;
    }

    /**
     * setResult
     * @param result
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * getId
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * setId
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

}
