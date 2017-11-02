package com.testapp.tools;


import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


/**
 * Created by stanislav on 01.11.17.
 */
public class ExprCalc {
    private static Expression exp;
    public static Double calcExpression(String expression) {
        try {
            exp = new ExpressionBuilder(expression).build();
        }catch (Exception e){
            return null;
        }

        if(!exp.validate().isValid()){
            return null;
        }else{
            double result = 0;
            try {
                result = exp.evaluate();
            }catch (Exception e){
                return null;
            }
            return result;
        }
    }
}
