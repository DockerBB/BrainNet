package com.brainsci.filesys;

import java.util.List;

public class FileTreeStructure {
    String label;
    List<FileTreeStructure> children;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<FileTreeStructure> getChildren() {
        return children;
    }

    public void setChildren(List<FileTreeStructure> children) {
        this.children = children;
    }
}
