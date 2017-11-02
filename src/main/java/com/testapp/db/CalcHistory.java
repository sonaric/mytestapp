package com.testapp.db;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by stanislav on 01.11.17.
 */
@Entity
@Table(name = "calc_history")
public class CalcHistory implements Serializable{
    public Long id;
    public Date calcDate;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "date_calc")
    public Date getCalcDate() {
        return calcDate;
    }

    public void setCalcDate(Date calcDate) {
        this.calcDate = calcDate;
    }
}
