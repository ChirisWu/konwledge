package com.chiris.valid.controller;

import com.chiris.valid.annotation.ValidateDateRange;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ValidateController {

    @GetMapping("/valid")
    @ValidateDateRange(mostMoth = 12, beginDateArgName = "begin", endDateArgName = "end")
    public String test(@RequestParam(name = "begin_date") Date begin,
                       @RequestParam(name = "end_date") Date end){

        return "valid";
    }
}
