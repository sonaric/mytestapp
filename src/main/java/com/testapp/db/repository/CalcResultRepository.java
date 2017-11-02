package com.testapp.db.repository;

import com.testapp.db.CalcResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by stanislav on 01.11.17.
 */
public interface CalcResultRepository extends JpaRepository<CalcResult, Long> {
    List<CalcResult> findCalcResultsByIdHistoryCalc(Long idHistoryCalc);
}
