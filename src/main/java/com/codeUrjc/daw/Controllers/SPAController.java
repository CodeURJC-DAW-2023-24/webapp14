package com.codeUrjc.daw.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SPAController {
    @GetMapping({"/new**/{path:[^\\.]*}", "/{path:new[^\\.]*}"})
    public String redirect() {
        return "forward:/webapp14_2/browser/index.html";
    }
}