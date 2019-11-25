package com.example.androidfinalproject.currencyConverter;

/**
 * This class describe currencyhistory class
 * @author :Wajahat
 */
public class CurrencyHistory {
    private String base;
    private String result;
    private float amount;
    private float converted;
    private long id;

    /**
     * Init constructor
     * @param base
     * @param result
     * @param id
     * @param amount
     * @param converted
     */
    public CurrencyHistory(String base, String result, long id, float amount, float converted){
        setBase(base);
        setResult(result);
        setId(id);
        setAmount(amount);
        setConverted(converted);
    }

    /**
     * getBase
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

    /**
     * getAmount
     * @return
     */
    public float getAmount() {
        return amount;
    }

    /**
     * setAmount
     * @param amount
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * getConverted
     * @return
     */
    public float getConverted() {
        return converted;
    }

    /**
     * setConverted
     * @param converted
     */
    public void setConverted(float converted) {
        this.converted = converted;
    }
}
