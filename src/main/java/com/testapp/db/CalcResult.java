package com.testapp.db;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by stanislav on 01.11.17.
 */
@Entity
@Table(name = "calc_result")
public class CalcResult implements Serializable {
    public Long idCalc;
    public Long idHistoryCalc;
    public String expression;
    public double result;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calc")
    public Long getIdCalc() {
        return idCalc;
    }

    public void setIdCalc(Long idCalc) {
        this.idCalc = idCalc;
    }

    @Column(name = "id_calc_history")
    public Long getIdHistoryCalc() {
        return idHistoryCalc;
    }

    public void setIdHistoryCalc(Long idHistoryCalc) {
        this.idHistoryCalc = idHistoryCalc;
    }

    @Column(name = "expression")
    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Column(name = "result")
    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
