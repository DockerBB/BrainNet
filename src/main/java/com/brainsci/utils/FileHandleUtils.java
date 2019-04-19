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
    public static void deleteFold(File dirFile) {
        // 如果dir对应的文件不存在，则退出
        if (!dirFile.exists()) {
            return;
        }
        Stack<File> stack = new Stack<>();
        stack.push(dirFile);
        while (!stack.isEmpty()){
            dirFile = stack.pop();
            if (dirFile.isFile()) {
                dirFile.delete();
            } else if (!dirFile.delete()){
                stack.push(dirFile);
                for (File file : dirFile.listFiles()) {
                    stack.push(file);
                }
            }
        }
        dirFile.delete();
    }
//    public List<Object> getFiles(String fileDir){
//        List<Object> fileViewer = new ArrayList<>();
//        Stack<Object> stack = new Stack<>();
//        File dir = new File(fileDir);
//        if (!dir.exists()) return fileViewer;
//        stack.push(dir);
//        stack.push(fileViewer);
//        while(!stack.isEmpty()){
//            List<Object> dirlist = (List<Object>)stack.pop();
//            List<Object> doclist = new ArrayList<>();
//            dir = (File)stack.pop();
//            for (File file : dir.listFiles()) {
//                String fileName = file.getName();
//                if (file.isDirectory()) dirlist.add(new HashMap<String, Object>(){{
//                    List<Object> fileList = new ArrayList<>();
//                    this.put("label", fileName);
//                    this.put("children", fileList);
//                    stack.push(file);
//                    stack.push(fileList);
//                }});
//            }
//            dirlist.addAll(doclist);
//        }
//        return fileViewer;
//    }
}
