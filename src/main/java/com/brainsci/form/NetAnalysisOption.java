package com.brainsci.form;

import java.util.ArrayList;
import java.util.List;

public class NetAnalysisOption {
    private List<Integer> selPreInd;
    private List<Integer> selPostInd;
    private Integer signType = 1;
    private Integer thresMethod = 1;
    private List<Double> thresSequence;
    private Integer netType = 1;
    private Integer randNetworkNum = 100;
    private Integer clustAlgor = 1;
    private Integer modulAlgor = 1;
    private Integer dDPcFlag = 1;
    private List<String> realNetURI;
    private List<Double> cIndex;

    public List<Integer> getSelPreInd() {
        return selPreInd;
    }

    public void setSelPreInd(List<Integer> selPreInd) {
        this.selPreInd = selPreInd;
    }

    public List<Integer> getSelPostInd() {
        return selPostInd;
    }

    public void setSelPostInd(List<Integer> selPostInd) {
        this.selPostInd = selPostInd;
    }

    public Integer getSignType() {
        return signType;
    }

    public void setSignType(Integer signType) {
        this.signType = signType;
    }

    public Integer getThresMethod() {
        return thresMethod;
    }

    public void setThresMethod(Integer thresMethod) {
        this.thresMethod = thresMethod;
    }

    public List<Double> getThresSequence() {
        return thresSequence;
    }

    public void setThresSequence(List<Double> thresSequence) {
        this.thresSequence = thresSequence;
    }

    public Integer getNetType() {
        return netType;
    }

    public void setNetType(Integer netType) {
        this.netType = netType;
    }

    public Integer getRandNetworkNum() {
        return randNetworkNum;
    }

    public void setRandNetworkNum(Integer randNetworkNum) {
        this.randNetworkNum = randNetworkNum;
    }

    public Integer getClustAlgor() {
        return clustAlgor;
    }

    public void setClustAlgor(Integer clustAlgor) {
        this.clustAlgor = clustAlgor;
    }

    public Integer getModulAlgor() {
        return modulAlgor;
    }

    public void setModulAlgor(Integer modulAlgor) {
        this.modulAlgor = modulAlgor;
    }

    public Integer getdDPcFlag() {
        return dDPcFlag;
    }

    public void setdDPcFlag(Integer dDPcFlag) {
        this.dDPcFlag = dDPcFlag;
    }

    public List<Double> getcIndex() {
        return cIndex;
    }

    public void setcIndex(List<Double> cIndex) {
        this.cIndex = cIndex;
    }

    public List<String> getRealNetURI() {
        return realNetURI;
    }

    public void setRealNetURI(List<String> realNetURI) {
        this.realNetURI = realNetURI;
    }
}
