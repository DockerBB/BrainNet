package com.brainsci.utils;

import com.brainsci.form.Para;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GretnaUtils {
    public static File path;
    public static File matlabApplication;
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
    public static void genNetwork(int SType, int TType, List<Double> Thres, int NType, int RandNum){
        String initialCommand =
                "cd '" + path.getAbsolutePath() + "';"+
                String.format("gretna_RUN_ThresMat(load('./Edge_AAL90_Binary.txt'),'./temp/RealNet.mat',%d,%d,%s,%d);", SType, TType, Thres.toString(), NType) +
                String.format("gretna_RUN_GenRandNet('./temp/RealNet.mat','./temp/RandNet.mat',%d,%d);", NType, RandNum);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-automation",
                "-noFigureWindows",
                "-nodesktop",
                "-r", "\"" + initialCommand + "exit;\""};
        try{
            System.out.println(path.getAbsolutePath());
            Runtime.getRuntime().exec(cmd).waitFor();
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
    public static void smallWorld(int NType, int ClustAlgor, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_SmallWorld('./temp/RealNet.mat','./temp/RandNet.mat','./temp/SW.mat',%d,%d,%f)", NType,  ClustAlgor,  AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-automation",
                "-noFigureWindows",
                "-nodesktop",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    public static void globalEfficiency(int NType, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+
                        String.format("gretna_RUN_GEfficiency('./temp/RealNet.mat','./temp/RandNet.mat', './temp/EFF.mat', %d, %f)", NType, AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-automation",
                "-noFigureWindows",
                "-nodesktop",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    public static void richClub(int NType){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_RichClub('./temp/RealNet.mat','./temp/RandNet.mat', './temp/RC.mat', %d)", NType);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-automation",
                "-noFigureWindows",
                "-nodesktop",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    public static void assortativity(int NType, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_Assortativity('./temp/RealNet.mat','./temp/RandNet.mat','./temp/ASS.mat',%d,%f)", NType,  AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-automation",
                "-noFigureWindows",
                "-nodesktop",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    public static void synchronization(int NType, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_Synchronization('./temp/RealNet.mat','./temp/RandNet.mat','./temp/SYN.mat',%d,%f)", NType,  AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-automation",
                "-noFigureWindows",
                "-nodesktop",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    public static void hierarchy(int NType, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_Hierarchy('./temp/RealNet.mat','./temp/RandNet.mat','./temp/HIE.mat',%d,%f)", NType,  AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-automation",
                "-noFigureWindows",
                "-nodesktop",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    public static void shortestPathLength(int NType, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_NodalShortestPath('./temp/RealNet.mat', './temp/NLP.mat',%d,%f)", NType,  AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-automation",
                "-noFigureWindows",
                "-nodesktop",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    public static void nodalEfficiency(int NType, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_NodalEfficiency('./temp/RealNet.mat', './temp/NE.mat',%d,%f)", NType,  AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-automation",
                "-noFigureWindows",
                "-nodesktop",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    public static void localEfficiency(int NType, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_NodalLocalEfficiency('./temp/RealNet.mat', './temp/NLE.mat',%d,%f)", NType,  AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-automation",
                "-noFigureWindows",
                "-nodesktop",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    public static void degreeCentrality(int NType, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_DegreeCentrality('./temp/RealNet.mat', './temp/DC.mat',%d,%f)", NType,  AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-automation",
                "-noFigureWindows",
                "-nodesktop",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    public static void betweennessCentrality(int NType, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_BetweennessCentrality('./temp/RealNet.mat', './temp/BC.mat',%d,%f)", NType,  AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-automation",
                "-noFigureWindows",
                "-nodesktop",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    public static void communityIndex(int NType, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_CommunityIndex('./temp/RealNet.mat', './temp/CI.mat', opt.NType, opt.MType, opt.DDPcInd)", NType,  AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-automation",
                "-noFigureWindows",
                "-nodesktop",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    public static void participantCoefficient(int NType, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_ParticipantCoefficient('./temp/RealNet.mat', './temp/PC.mat', opt.CIndex)", NType,  AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-automation",
                "-noFigureWindows",
                "-nodesktop",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    public static void interaction(int NType, double AUCInterval){
        String matlabCommand =
                "cd '" + path.getAbsolutePath() + "';"+ String.format("gretna_RUN_ModularInteraction('./temp/RealNet.mat', './temp/MI.mat', opt.NType, opt.CIndex)", NType,  AUCInterval);
        String[] cmd = {
                matlabApplication.getAbsolutePath(),
                "-automation",
                "-noFigureWindows",
                "-nodesktop",
                "-r", matlabCommand + ";exit"};
        try{
            Runtime.getRuntime().exec(cmd);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
