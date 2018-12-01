package com.brainsci.utils;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;

public class FileHandleUtils {
    File root;
    public static FileHandleUtils fileHandleUtils;
    FileHandleUtils(@Value("${filesys.dir}") String path){
        root = new File(path);
    }
    public static FileHandleUtils getFileHandleUtils(){
        return fileHandleUtils;
    }
    public String getFileStructure(){

        return null;
    }
}
