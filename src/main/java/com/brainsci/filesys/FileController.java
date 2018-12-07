package com.brainsci.filesys;

import com.brainsci.form.CommonResultForm;
import com.brainsci.utils.FileHandleUtils;
import com.brainsci.utils.RemoteAddrUtils;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class FileController {
    @Value("${filesys.dir}")
    private String fileDir;
//    @Value("${filesys.public-dir}")
//    private String publicDir;
    private FileHandleUtils fileHandleUtils;

    @PostMapping("/getfilelist")
    public CommonResultForm getFileList(@RequestBody List<String> acceptType, HttpServletRequest request){
        List<Object> publicList = FileHandleUtils.getFileHandleUtils().getFileStructure(fileDir + "/public", acceptType);
        Map<String, Object> publicViewer = new HashMap<String, Object>(){{
            this.put("label", "public");
            this.put("children", publicList);
        }};
        String ip = "/" + RemoteAddrUtils.getRemoteAddrUtils().getRemoteIP(request);
        List<Object> usrlicList = FileHandleUtils.getFileHandleUtils().getFileStructure(fileDir + ip, acceptType);
        Map<String, Object> usrViewer = new HashMap<String, Object>(){{
            this.put("label", "MyFile");
            this.put("children", usrlicList);
        }};
        return CommonResultForm.of200("sucess",new ArrayList<Object>(){{
            this.add(publicViewer);
            this.add(usrViewer);
        }});
    }
    @GetMapping("/MyFile/**")
    public void requestForResource(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String uri = request.getRequestURI().substring(7);
        String ip = "/" + RemoteAddrUtils.getRemoteAddrUtils().getRemoteIP(request);
        if (new File(fileDir + ip).exists()) response.sendRedirect(ip + uri);
    }
}
