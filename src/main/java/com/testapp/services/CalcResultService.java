package com.testapp.services;

import com.testapp.db.CalcResult;

import java.util.List;

/**
 * Created by stanislav on 01.11.17.
 */
public interface CalcResultService {
    List<CalcResult> getResultListByHistoryId(Long id_calc_history);
    void saveCurrentResult(List<CalcResult> results);
    void deleteResultForDate(List<CalcResult> idHistoryCalcList);
}
