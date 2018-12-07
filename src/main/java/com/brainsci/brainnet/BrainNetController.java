package com.brainsci.brainnet;

import com.brainsci.form.CommonResultForm;
import com.brainsci.form.Para;
import com.brainsci.service.GretnaService;
import com.brainsci.utils.RemoteAddrUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
public class BrainNetController {

    private final GretnaService gretnaService;

    @Autowired
    public BrainNetController(GretnaService gretnaService) {
        this.gretnaService = gretnaService;
    }

    @ApiOperation(value = "ÍøÂç·ÖÎö")
    @PostMapping(value = "/gretna")
    public CommonResultForm gretnaNetworkAnalysis(@RequestBody Para para, HttpServletRequest request){
        String usrIP = RemoteAddrUtils.getRemoteAddrUtils().getRemoteIP(request);
        return gretnaService.networkAnalysis(usrIP, para);
    }
}