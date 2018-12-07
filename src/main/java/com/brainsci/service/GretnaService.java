package com.brainsci.service;

import com.brainsci.form.CommonResultForm;
import com.brainsci.form.Para;
import com.brainsci.utils.GretnaUtils;
import com.brainsci.utils.RemoteAddrUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class GretnaService {
    private final String filedir;
    public GretnaService(@Value("${filesys.dir}") String filedir, @Value("${filesys.path-matlab}") String matlabPath) {
        this.filedir = filedir;
        GretnaUtils.matlabApplication = new File(matlabPath);
    }

    @Async
    public CommonResultForm networkAnalysis(String userIP, Para para){
        File temp = new File(filedir + "/" + userIP);
        if (!temp.exists()) {
            temp.mkdirs();// 判断文件夹是否存在，如果不存在就创建
        }
        int SType = para.getSignType();
        int TType = para.getThresMethod();
        int NType = para.getNetType();
        List<Double> Thres = para.getThresSequence();
        int randNetworkNum = para.getRandNetworkNum();
        int ClustAlgor = 1;
        double AUCInterval = Thres.get(1) - Thres.get(0);
        File realNetFile = new File(filedir + "/" + para.getRealNetURI());
        List<String> label = para.getToDoList();
        GretnaUtils.genNetwork(temp, realNetFile, SType, TType, Thres, NType, randNetworkNum);
        if (label.contains("Global - Small-World"))GretnaUtils.smallWorld(temp, NType, ClustAlgor, AUCInterval);
        if (label.contains("Global - Efficiency"))GretnaUtils.globalEfficiency(temp, NType, AUCInterval);
        if (label.contains("Global - Rich-Club"))GretnaUtils.richClub(temp, NType);
        if (label.contains("Global - Assortativity"))GretnaUtils.assortativity(temp, NType, AUCInterval);
        if (label.contains("Global - Synchronization"))GretnaUtils.synchronization(temp, NType, AUCInterval);
        if (label.contains("Global - Hierarchy"))GretnaUtils.hierarchy(temp, NType, AUCInterval);
        if (label.contains("Nodal - Shortest Path Length"))GretnaUtils.shortestPathLength(temp, NType, AUCInterval);
        if (label.contains("Nodal - Efficiency"))GretnaUtils.nodalEfficiency(temp, NType, AUCInterval);
        if (label.contains("Nodal - Local Efficiency"))GretnaUtils.localEfficiency(temp, NType, AUCInterval);
        if (label.contains("Nodal - Degree Centrality"))GretnaUtils.degreeCentrality(temp, NType, AUCInterval);
        if (label.contains("Nodal - Betweenness Centrality"))GretnaUtils.betweennessCentrality(temp, NType, AUCInterval);
//        if (label.contains("Nodal - Community Index"))GretnaUtils.communityIndex(NType, AUCInterval);
//        if (label.contains("Nodal - Participant Coefficient"))GretnaUtils.participantCoefficient(NType, AUCInterval);
//        if (label.contains("Modular - Interaction"))GretnaUtils.interaction(NType, AUCInterval);
        return CommonResultForm.of204("sucess");
    }
}
