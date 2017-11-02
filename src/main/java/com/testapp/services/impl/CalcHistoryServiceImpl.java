package com.testapp.services.impl;

import com.testapp.db.CalcHistory;
import com.testapp.db.repository.CalcHistoryRepository;
import com.testapp.services.CalcHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by stanislav on 01.11.17.
 */
@Service
@Transactional
public class CalcHistoryServiceImpl implements CalcHistoryService{
    @Autowired
    CalcHistoryRepository historyRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CalcHistory> getHistoryList() {
        return historyRepository.findAll();
    }

    @Override
    public CalcHistory saveCurrentDate(CalcHistory history) {
        return historyRepository.save(history);
    }

    @Override
    public void deleteDate(Long id_history) {
        historyRepository.delete(id_history);
    }
}
