package com.testapp.controllers;

import com.testapp.db.CalcHistory;
import com.testapp.db.CalcResult;
import com.testapp.services.CalcHistoryService;
import com.testapp.services.CalcResultService;
import com.testapp.tools.CalcClass;
import com.testapp.tools.ExprCalc;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by stanislav on 01.11.17.
 */
@Controller
@RequestMapping("/rest/calc")
public class CalcRestController {

    @Autowired
    CalcHistoryService historyService;

    @Autowired
    CalcResultService resultService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<CalcHistory> getCalcHistoryList(){
        return historyService.getHistoryList();
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public List<CalcClass> uploadFileAndCalcResult(@RequestParam("file") MultipartFile file){

        List<String> inputFile =null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            inputFile = mapper.readValue(file.getInputStream(), mapper.getTypeFactory().constructCollectionType(List.class, String.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<CalcClass> list = new ArrayList<>();
        for(String str: inputFile){
            list.add(new CalcClass(str, ExprCalc.calcExpression(str)));
        }
        CalcHistory calcDate = new CalcHistory();
        calcDate.setCalcDate(new Date());
        calcDate = historyService.saveCurrentDate(calcDate);
        CalcResult result = null;
        List<CalcResult> results = new ArrayList<>();

        for(CalcClass calcClass: list){
            result = new CalcResult();
            result.setExpression(calcClass.getExpression());
            result.setResult(calcClass.getResult());
            result.setIdHistoryCalc(calcDate.getId());
            results.add(result);
        }

        resultService.saveCurrentResult(results);
        return list;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public List<CalcHistory> saveCalcResult(@RequestParam(value = "calcList") String saveList){
        System.out.println(saveList);
        return historyService.getHistoryList();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<CalcResult> getCalcResultByDate(@PathVariable(value = "id") long idHistoryCalc){
        return resultService.getResultListByHistoryId(idHistoryCalc);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<CalcHistory> deleteCalcHistory(@PathVariable(value = "id") long idHistoryCalc){
        resultService.deleteResultForDate(resultService.getResultListByHistoryId(idHistoryCalc));
        historyService.deleteDate(idHistoryCalc);
        return historyService.getHistoryList();
    }

}
