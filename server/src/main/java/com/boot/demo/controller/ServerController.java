package com.boot.demo.controller;

import com.boot.demo.bean.ServerQuery;
import com.boot.demo.common.Result;
import com.boot.demo.common.ResultGenerator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/server")
public class ServerController {


    @PostMapping("/test")
    private Result<Map> testo(@Validated @RequestBody ServerQuery query) {
        return ResultGenerator.genSuccessResult();
    }

}
