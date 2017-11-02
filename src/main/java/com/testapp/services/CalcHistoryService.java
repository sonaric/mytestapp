package com.testapp.services;

import com.testapp.db.CalcHistory;

import java.util.List;

/**
 * Created by stanislav on 01.11.17.
 */
public interface CalcHistoryService {
    List<CalcHistory> getHistoryList();
    CalcHistory saveCurrentDate(CalcHistory history);
    void deleteDate(Long id_history);
}
