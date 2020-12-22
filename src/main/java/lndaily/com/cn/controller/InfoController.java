package lndaily.com.cn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("info")
public class InfoController {

    @RequestMapping("newinfo")
    public String newinfo(String info){

        return "success";
    }
}

