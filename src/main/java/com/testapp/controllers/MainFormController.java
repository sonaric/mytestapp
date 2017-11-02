package com.testapp.controllers;

import com.testapp.db.CalcHistory;
import com.testapp.db.repository.CalcHistoryRepository;
import com.testapp.db.repository.CalcResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * @author Stanislav Omelyanenko
 */
@Controller
@RequestMapping("/")
public class MainFormController {
    /**
     *
     * @return logical view name
     */
    @RequestMapping(method = RequestMethod.GET)
    public String viewMainPage(){
        return "index";
    }
}
