package com.brainsci.brainnet;

import com.brainsci.form.CommonResultForm;
import com.brainsci.form.Para;
import com.brainsci.service.GretnaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class BrainNetController {

    private final GretnaService gretnaService;

    @Autowired
    public BrainNetController(GretnaService gretnaService) {
        this.gretnaService = gretnaService;
    }

    @ApiOperation(value = "�������")
    @PostMapping(value = "/gretna")
    public CommonResultForm gretnaNetworkAnalysis(@RequestBody Para para){
        return gretnaService.networkAnalysis(para);
    }
}