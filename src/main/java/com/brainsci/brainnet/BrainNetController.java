package com.brainsci.brainnet;

import com.brainsci.form.CommonResultForm;
import com.brainsci.form.NetAnalysisOption;
import com.brainsci.security.repository.UserBaseRepository;
import com.brainsci.security.util.GsonPlus;
import com.brainsci.service.CPACService;
import com.brainsci.service.GretnaService;
import com.brainsci.utils.MatlabUtils;
import com.brainsci.utils.RemoteAddrUtils;
import com.brainsci.websocket.form.WebSocketMessageForm;
import com.brainsci.websocket.server.WebSocketServer;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

@RestController
public class BrainNetController {

    private final GretnaService gretnaService;
    private final CPACService cpacService;
    private final UserBaseRepository userBaseRepository;

    @Autowired
    public BrainNetController(GretnaService gretnaService, CPACService cpacService, UserBaseRepository userBaseRepository) {
        this.gretnaService = gretnaService;
        this.cpacService = cpacService;
        this.userBaseRepository = userBaseRepository;
    }

    @ApiOperation(value = "网络分析")
    @PostMapping(value = "/gretna/{token}")
    public CommonResultForm gretnaNetworkAnalysis(@RequestBody NetAnalysisOption para, @PathVariable("token") String token, HttpServletRequest request, HttpSession httpSession){
        String userHomeDir = userBaseRepository.getOne((String) httpSession.getAttribute("username")).getHomeDirectory();
        if (MatlabUtils.state.containsKey(userHomeDir)) return CommonResultForm.of204("Tasks are "+MatlabUtils.state.get(userHomeDir)+", please wait");
        MatlabUtils.state.put(userHomeDir, "submitted");
        WebSocketServer.sendMessage(GsonPlus.GSON.toJson(new WebSocketMessageForm("gretnaState", "submitted")),token);
        gretnaService.networkAnalysis(userHomeDir,token, para);
        return CommonResultForm.of204("Tasks are queuing");
    }
    @ApiOperation(value = "网络分析")
    @PostMapping(value = "/cpac/{task}/{token}")
    public CommonResultForm cpac(@PathVariable("task") String task,@PathVariable("token") String token,@RequestBody Map<String, String> map, HttpServletRequest request, HttpSession httpSession) throws Exception{
        String userHomeDir = userBaseRepository.getOne((String) httpSession.getAttribute("username")).getHomeDirectory();
        String str = map.get("jsonstr");
        WebSocketServer.sendMessage(GsonPlus.GSON.toJson(new WebSocketMessageForm("cpacState", "submitted")),token);
        cpacService.cpac(userHomeDir, task, token, str);
        return CommonResultForm.of204("success");
    }
    @ApiOperation(value = "网络模块状态")
    @PostMapping(value = "/gretnaState")
    public CommonResultForm gretnaState(HttpSession httpSession){
        String userHomeDir = userBaseRepository.getOne((String) httpSession.getAttribute("username")).getHomeDirectory();
        if (MatlabUtils.state.containsKey(userHomeDir)) return CommonResultForm.of204("Tasks are "+MatlabUtils.state.get(userHomeDir)+", please wait");
        MatlabUtils.state.put(userHomeDir, "submitted");
        return CommonResultForm.of204("Tasks are queuing");
    }
    @RequestMapping(value = "/")
    public void index(HttpServletResponse response) throws IOException{
        response.sendRedirect("/bsci/index.html");
    }
}