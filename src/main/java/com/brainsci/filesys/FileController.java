package com.brainsci.filesys;

import com.brainsci.form.CommonResultForm;
import com.brainsci.utils.FileHandleUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class FileController {
    @Value("${filesys.dir}")
    private String fileDir;
    @Value("${filesys.public-dir}")
    private String publicDir;
    private FileHandleUtils fileHandleUtils;

    @Bean
    public CommonResultForm getFileList(){
        File root = new File(fileDir);
        return CommonResultForm.of200("sucess", root);
    }
}
