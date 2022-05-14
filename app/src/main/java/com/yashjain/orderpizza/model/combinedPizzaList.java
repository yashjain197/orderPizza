package com.yashjain.orderpizza.model;

public class combinedPizzaList {
    private int number;
    private String PizzaType;
    private String crustType;

    public combinedPizzaList(int number,String pizzaType,String crustType){
        this.number=number;
        this.PizzaType=pizzaType;
        this.crustType=crustType;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPizzaType() {
        return PizzaType;
    }

    public void setPizzaType(String pizzaType) {
        PizzaType = pizzaType;
    }

    public String getCrustType() {
        return crustType;
    }

    public void setCrustType(String crustType) {
        this.crustType = crustType;
    }
}
