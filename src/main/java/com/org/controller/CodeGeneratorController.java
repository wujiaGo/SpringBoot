package com.org.controller;

import com.org.util.CodeGenerator;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
@RequestMapping("/code")
public class CodeGeneratorController {

    @PostMapping("/start")
    public String start(@Param("table") String table) {
        try {
            CodeGenerator.start(table);
            return "构造成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "构造失败";
        }
    }
}
