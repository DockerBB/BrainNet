package com.brainsci.form;

import java.util.List;

public class Para {
    private SignType signType;
    private ThresholdMethod thresMethod;
    private List<Double> thresSequence;
    private NetworkType netType;
    private Integer randNetworkNum = 0;
    private List<String> toDoList;
//    public Integer clustAlgor;
//    public ModulAlgor modulAlgor;
//    public Boolean DDPcFlag;
//    public String cIndex;
    private String realNetURI;

    public Integer getSignType() {
        return signType.ordinal() + 1;
    }

    public Integer getThresMethod() {
        return thresMethod.ordinal() + 1;
    }

    public List<Double> getThresSequence() {
        return thresSequence;
    }

    public Integer getNetType() {
        return netType.ordinal() + 1;
    }

    public Integer getRandNetworkNum() {
        return randNetworkNum;
    }

    public List<String> getToDoList() {
        return toDoList;
    }

    public void setSignType(SignType signType) {
        this.signType = signType;
    }

    public void setThresMethod(ThresholdMethod thresMethod) {
        this.thresMethod = thresMethod;
    }

    public void setThresSequence(List<Double> thresSequence) {
        this.thresSequence = thresSequence;
    }

    public void setNetType(NetworkType netType) {
        this.netType = netType;
    }

    public void setRandNetworkNum(Integer randNetworkNum) {
        this.randNetworkNum = randNetworkNum;
    }

    public void setToDoList(List<String> toDoList) {
        this.toDoList = toDoList;
    }

    public String getRealNetURI() {
        return realNetURI;
    }

    public void setRealNetURI(String realNetURI) {
        this.realNetURI = realNetURI;
    }
}
enum SignType {
    POSTIVE,
    NAGTIVE,
    ABSOLUTE
}
enum ThresholdMethod {
    NETWORKSPARSITY,
    MATRIXELEMENT
}
enum NetworkType {
    BINARY,
    WEIGHTED
}
//enum ClustAlgor {
//
//}
//enum ModulAlgor {
//
//}