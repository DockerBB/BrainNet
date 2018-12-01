package com.brainsci.service;

import com.brainsci.form.CommonResultForm;
import com.brainsci.form.Para;
import com.brainsci.utils.GretnaUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class GretnaService {
    public GretnaService(@Value("${filesys.gretna-output}") String gretnaPath, @Value("${filesys.path-matlab}") String matlabPath) {
        File temp = new File(gretnaPath + "/temp");// 判断文件夹是否存在，如果不存在就创建
        GretnaUtils.matlabApplication = new File(matlabPath);
        GretnaUtils.path = new File(gretnaPath);
        if (!temp.exists()) {
            temp.mkdir();
        }
    }

    @Async
    public CommonResultForm networkAnalysis(Para para){
        int SType = para.getSignType();
        int TType = para.getThresMethod();
        int NType = para.getNetType();
        List<Double> Thres = para.getThresSequence();
        int randNetworkNum = para.getRandNetworkNum();
        int ClustAlgor = 1;
        double AUCInterval = Thres.get(1) - Thres.get(0);
        List<String> label = para.getToDoList();
//        GretnaUtils.genNetwork(SType, TType, Thres, NType, randNetworkNum);
//        if (label.contains("Global - Small-World"))GretnaUtils.smallWorld(NType, ClustAlgor, AUCInterval);
//        if (label.contains("Global - Efficiency"))GretnaUtils.globalEfficiency(NType, AUCInterval);
//        if (label.contains("Global - Rich-Club"))GretnaUtils.richClub(NType);
//        if (label.contains("Global - Assortativity"))GretnaUtils.assortativity(NType, AUCInterval);
//        if (label.contains("Global - Synchronization"))GretnaUtils.synchronization(NType, AUCInterval);
//        if (label.contains("Global - Hierarchy"))GretnaUtils.hierarchy(NType, AUCInterval);
//        if (label.contains("Nodal - Shortest Path Length"));GretnaUtils.shortestPathLength(NType, AUCInterval);
//        if (label.contains("Nodal - Efficiency"));GretnaUtils.nodalEfficiency(NType, AUCInterval);
//        if (label.contains("Nodal - Local Efficiency"));GretnaUtils.localEfficiency(NType, AUCInterval);
//        if (label.contains("Nodal - Degree Centrality"));GretnaUtils.degreeCentrality(NType, AUCInterval);
//        if (label.contains("Nodal - Betweenness Centrality"));GretnaUtils.betweennessCentrality(NType, AUCInterval);
//        if (label.contains("Nodal - Community Index"));GretnaUtils.communityIndex(NType, AUCInterval);
//        if (label.contains("Nodal - Participant Coefficient"));GretnaUtils.participantCoefficient(NType, AUCInterval);
//        if (label.contains("Modular - Interaction"));GretnaUtils.interaction(NType, AUCInterval);
        return CommonResultForm.of204("sucess");
    }
}
