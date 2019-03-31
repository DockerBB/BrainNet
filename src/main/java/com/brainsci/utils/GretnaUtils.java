package com.brainsci.utils;

import com.brainsci.form.Para;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class GretnaUtils {
    public static File matlabApplication;
    private static GretnaUtils gretnaUtils = new GretnaUtils();
    public static GretnaUtils getGretnaUtils(){
        return gretnaUtils;
    }
    /**
     * @param SType         - The type of matrix sign
                                1: Positive
                                2: Negative
                                3: Absolute
     * @param TType         - The method of thresholding
                                1: Sparity
                                2: Similarity
     * @param Thres         - Threshold Sequence
     * @param NType         - The type of network
                                1: Binary
                                2: Weighted
     * @param RandNum       - The number of random network
     */
    public void genNetwork(File path, File realNetFile, int SType, int TType, List<Double> Thres, int NType, int RandNum){
        String initialCommand =
                "cd '" + path.getAbsolutePath() + "';"+
                String.format("gretna_RUN_ThresMat(load('" + realNetFile.getAbsolutePath() + "'),'./temp/RealNet.mat',%d,%d,%s,%d);", SType, TType, Thres.toString(), NType) +
                String.format("gretna_RUN_GenRandNet('./temp/RealNet.mat','./temp/RandNet.mat',%d,%d);", NType, RandNum);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-noFigureWindows",
                "-nodesktop",
                "-nosplash",
                "-logfile matlab.log",
                "-r", initialCommand + "exit;"};
        try{
            System.out.println(path.getAbsolutePath());
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            System.out.println("ReadNet.mat & RandNet.mat is created!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @param NType          - The type of network
                                1: Binary
                                2: Weighted
     * @param ClustAlgor    - The alogrithm to estimate clusting coefficient
                                1: Onnela et al., 2005
                                2: Barrat et al., 2009
     * @param AUCInterval   - The interval to estimate AUC, 0 if just one threshold
     */
    @Async
    public void smallWorld(File path, int NType, int ClustAlgor, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_SmallWorld('./temp/RealNet.mat','./temp/RandNet.mat','./temp/SW.mat',%d,%d,%f)", NType,  ClustAlgor,  AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-noFigureWindows",
                "-nodesktop",
                "-nosplash",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd).waitFor();
            Thread.sleep(100*1000);
            System.out.println("small world");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Async
    public void globalEfficiency(File path, int NType, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+
                        String.format("gretna_RUN_GEfficiency('./temp/RealNet.mat','./temp/RandNet.mat', './temp/EFF.mat', %d, %f)", NType, AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-noFigureWindows",
                "-nodesktop",
                "-nosplash",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd).waitFor();
            Thread.sleep(100*1000);
            System.out.println("global efficiency");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Async
    public void richClub(File path, int NType){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_RichClub('./temp/RealNet.mat','./temp/RandNet.mat', './temp/RC.mat', %d)", NType);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-noFigureWindows",
                "-nodesktop",
                "-nosplash",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd).waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Async
    public void assortativity(File path, int NType, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_Assortativity('./temp/RealNet.mat','./temp/RandNet.mat','./temp/ASS.mat',%d,%f)", NType,  AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-noFigureWindows",
                "-nodesktop",
                "-nosplash",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd).waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Async
    public void synchronization(File path, int NType, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_Synchronization('./temp/RealNet.mat','./temp/RandNet.mat','./temp/SYN.mat',%d,%f)", NType,  AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-noFigureWindows",
                "-nodesktop",
                "-nosplash",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd).waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Async
    public void hierarchy(File path, int NType, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_Hierarchy('./temp/RealNet.mat','./temp/RandNet.mat','./temp/HIE.mat',%d,%f)", NType,  AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-noFigureWindows",
                "-nodesktop",
                "-nosplash",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd).waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Async
    public void shortestPathLength(File path, int NType, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_NodalShortestPath('./temp/RealNet.mat', './temp/NLP.mat',%d,%f)", NType,  AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-noFigureWindows",
                "-nodesktop",
                "-nosplash",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd).waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Async
    public void nodalEfficiency(File path, int NType, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_NodalEfficiency('./temp/RealNet.mat', './temp/NE.mat',%d,%f)", NType,  AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-noFigureWindows",
                "-nodesktop",
                "-nosplash",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd).waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Async
    public void localEfficiency(File path, int NType, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_NodalLocalEfficiency('./temp/RealNet.mat', './temp/NLE.mat',%d,%f)", NType,  AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-noFigureWindows",
                "-nodesktop",
                "-nosplash",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd).waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Async
    public void degreeCentrality(File path, int NType, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_DegreeCentrality('./temp/RealNet.mat', './temp/DC.mat',%d,%f)", NType,  AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-noFigureWindows",
                "-nodesktop",
                "-nosplash",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd).waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Async
    public void betweennessCentrality(File path, int NType, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_BetweennessCentrality('./temp/RealNet.mat', './temp/BC.mat',%d,%f)", NType,  AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-noFigureWindows",
                "-nodesktop",
                "-nosplash",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd).waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Async
    public void communityIndex(File path, int NType, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_CommunityIndex('./temp/RealNet.mat', './temp/CI.mat', opt.NType, opt.MType, opt.DDPcInd)", NType,  AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-noFigureWindows",
                "-nodesktop",
                "-nosplash",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd).waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Async
    public void participantCoefficient(File path, int NType, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_ParticipantCoefficient('./temp/RealNet.mat', './temp/PC.mat', opt.CIndex)", NType,  AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-noFigureWindows",
                "-nodesktop",
                "-nosplash",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd).waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void interaction(File path, int NType, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_ModularInteraction('./temp/RealNet.mat', './temp/MI.mat', opt.NType, opt.CIndex)", NType,  AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-noFigureWindows",
                "-nodesktop",
                "-nosplash",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd).waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
