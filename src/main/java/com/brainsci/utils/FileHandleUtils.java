package com.brainsci.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class FileHandleUtils {
    public static FileHandleUtils fileHandleUtils = new FileHandleUtils();
    public static FileHandleUtils getFileHandleUtils(){
        return fileHandleUtils;
    }
    public List<Object> getFileStructure(String fileDir, List<String> acceptType){
        List<Object> fileViewer = new ArrayList<>();
        Stack<Object> stack = new Stack<>();
        File dir = new File(fileDir);
        if (!dir.exists()) return fileViewer;
        stack.push(dir);
        stack.push(fileViewer);
        while(!stack.isEmpty()){
            List<Object> dirlist = (List<Object>)stack.pop();
            List<Object> doclist = new ArrayList<>();
            dir = (File)stack.pop();
            for (File file : dir.listFiles()) {
                String fileName = file.getName();
                if (file.isDirectory()) dirlist.add(new HashMap<String, Object>(){{
                    List<Object> fileList = new ArrayList<>();
                    this.put("label", fileName);
                    this.put("children", fileList);
                    stack.push(file);
                    stack.push(fileList);
                }});
                else if (acceptType == null || acceptType.isEmpty() || acceptType.contains('*' + fileName.substring(fileName.lastIndexOf('.'))))
                    doclist.add(new HashMap<String, Object>(){{ this.put("label", fileName); }});
            }
            dirlist.addAll(doclist);
        }
        return fileViewer;
    }
}
