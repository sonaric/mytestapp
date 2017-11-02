package com.testapp.tools;

import java.io.Serializable;

/**
 * Created by stanislav on 01.11.17.
 */
public class CalcClass implements Serializable{
    private String expression;
    private double result;

    public CalcClass(){}

    public CalcClass(String expression, double result){
        this.expression = expression;
        this.result = result;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
