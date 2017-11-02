package com.testapp.services.impl;

import com.testapp.db.CalcResult;
import com.testapp.db.repository.CalcResultRepository;
import com.testapp.services.CalcResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by stanislav on 01.11.17.
 */
@Service
@Transactional
public class CalcResultServiceImpl implements CalcResultService {

    @Autowired
    CalcResultRepository resultRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CalcResult> getResultListByHistoryId(Long idCalcHistory) {
        return resultRepository.findCalcResultsByIdHistoryCalc(idCalcHistory);
    }

    @Override
    public void saveCurrentResult(List<CalcResult> results) {
        if(results != null){
            for(CalcResult current: results){
                resultRepository.save(current);
            }
        }

    }

    @Override
    public void deleteResultForDate(List<CalcResult> idHistoryCalcList) {
        if(idHistoryCalcList != null){
           for(CalcResult result: idHistoryCalcList){
               resultRepository.delete(result.getIdCalc());
           }
        }

    }
}
