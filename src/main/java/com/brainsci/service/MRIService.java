package com.brainsci.service;

import com.brainsci.form.NetAnalysisOption;
import com.brainsci.security.util.GsonPlus;
import com.brainsci.utils.FileHandleUtils;
import com.brainsci.utils.MatlabUtils;
import com.brainsci.utils.ZipUtils;
import com.brainsci.websocket.form.WebSocketMessageForm;
import com.brainsci.websocket.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@Service
public class MRIService {
    private final String filedir;
    public MRIService(@Value("${filesys.dir}") String filedir) {
        this.filedir = filedir;
    }
    @Async
    public void cpac(String userHomeDir, String task, String token, String paraJson){
        File path = new File(filedir + userHomeDir + "/fmri/"+task);
        File fmripub = new File(filedir+"./public/fmri/");
        File working = new File(filedir + userHomeDir + "/fmri/"+task+"/working");
        File zipTag = new File(working.getAbsolutePath() + ".zip");
        if (working.exists())FileHandleUtils.deleteFold(working);// 判断文件夹是否存在，如果存在就删除
        zipTag.delete();// 删除原有压缩文件
        paraJson = paraJson.replaceAll("/public/fmri",fmripub.getAbsolutePath().replaceAll("/./","/"));
        System.out.println(paraJson);
        String[] cmd = {"python","/home/cdj/brainnet/python_pipeline/fmri_pipeline_parameter.py",path.getAbsolutePath(), paraJson};
        WebSocketServer.sendMessage(GsonPlus.GSON.toJson(new WebSocketMessageForm("fmriState", "running")),token);
        try{
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = br.readLine();
            while(p.isAlive())
                if (line != null){
                    WebSocketServer.sendMessage(GsonPlus.GSON.toJson(new WebSocketMessageForm("fmri", line)),token);
                    System.out.println(line);
                    line = br.readLine();
                }
            BufferedReader err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            line = err.readLine();
            while(line != null){
                WebSocketServer.sendMessage(GsonPlus.GSON.toJson(new WebSocketMessageForm("fmri", line)),token);
                System.out.println(line);
                line = err.readLine();
            }
            System.out.println("exit("+p.exitValue()+")");
            if (p.exitValue()!=0){
                WebSocketServer.sendMessage(GsonPlus.GSON.toJson(new WebSocketMessageForm("fmri", "error")),token);
                WebSocketServer.sendMessage(GsonPlus.GSON.toJson(new WebSocketMessageForm("fmriState", "error")),token);
                System.out.println("error");
            } else if (working.exists()) {
                WebSocketServer.sendMessage(GsonPlus.GSON.toJson(new WebSocketMessageForm("fmri", " compress...")),token);
                System.out.println("compress...");
                FileOutputStream fos = new FileOutputStream(zipTag);
                ZipUtils.toZip(working.getAbsolutePath(), fos, true);
                WebSocketServer.sendMessage(GsonPlus.GSON.toJson(new WebSocketMessageForm("fmri", "finish")),token);
                WebSocketServer.sendMessage(GsonPlus.GSON.toJson(new WebSocketMessageForm("fmriState", "finish")),token);
                System.out.println("finish");
            }else {
                System.out.println("compress fail");
                WebSocketServer.sendMessage(GsonPlus.GSON.toJson(new WebSocketMessageForm("fmri", "compress fail")),token);
                WebSocketServer.sendMessage(GsonPlus.GSON.toJson(new WebSocketMessageForm("fmriState", "error")),token);
            }
            br.close();
            err.close();
            p.destroy();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Async
    public void sMRI(String userHomeDir, String task, String token, String paraJson){
        File path = new File(filedir + userHomeDir + "/smri/"+task);
        File smripub = new File(filedir+"./public/smri/");
        File working = new File(filedir + userHomeDir + "/smri/"+task+"/working");
        File zipTag = new File(working.getAbsolutePath() + ".zip");
        if (working.exists())FileHandleUtils.deleteFold(working);// 判断文件夹是否存在，如果存在就删除
        zipTag.delete();// 删除原有压缩文件
        paraJson = paraJson.replaceAll("/public/smri",smripub.getAbsolutePath().replaceAll("/./","/"));
        System.out.println(paraJson);
        String[] cmd = {"python","/home/cdj/brainnet/python_pipeline/T1_surface/freesurfer_recon.py",path.getAbsolutePath(), paraJson};
        WebSocketServer.sendMessage(GsonPlus.GSON.toJson(new WebSocketMessageForm("smriState", "running")),token);
        try{
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = br.readLine();
            while(p.isAlive())
                if (line != null){
                    WebSocketServer.sendMessage(GsonPlus.GSON.toJson(new WebSocketMessageForm("smri", line)),token);
                    System.out.println(line);
                    line = br.readLine();
                }
            BufferedReader err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            line = err.readLine();
            while(line != null){
                WebSocketServer.sendMessage(GsonPlus.GSON.toJson(new WebSocketMessageForm("smri", line)),token);
                System.out.println(line);
                line = err.readLine();
            }
            System.out.println("exit("+p.exitValue()+")");
            if (p.exitValue()!=0){
                WebSocketServer.sendMessage(GsonPlus.GSON.toJson(new WebSocketMessageForm("smri", "error")),token);
                WebSocketServer.sendMessage(GsonPlus.GSON.toJson(new WebSocketMessageForm("smriState", "error")),token);
                System.out.println("error");
            } else if (working.exists()) {
                WebSocketServer.sendMessage(GsonPlus.GSON.toJson(new WebSocketMessageForm("smri", " compress...")),token);
                System.out.println("compress...");
                FileOutputStream fos = new FileOutputStream(zipTag);
                ZipUtils.toZip(working.getAbsolutePath(), fos, true);
                WebSocketServer.sendMessage(GsonPlus.GSON.toJson(new WebSocketMessageForm("smri", "finish")),token);
                WebSocketServer.sendMessage(GsonPlus.GSON.toJson(new WebSocketMessageForm("smriState", "finish")),token);
                System.out.println("finish");
            }else {
                System.out.println("compress fail");
                WebSocketServer.sendMessage(GsonPlus.GSON.toJson(new WebSocketMessageForm("smri", "compress fail")),token);
                WebSocketServer.sendMessage(GsonPlus.GSON.toJson(new WebSocketMessageForm("smriState", "error")),token);
            }
            br.close();
            err.close();
            p.destroy();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
