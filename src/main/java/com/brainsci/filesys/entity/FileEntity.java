package com.brainsci.filesys.entity;

import com.brainsci.security.entity.UserBaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "file_info")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fileId;
    private String name;
    private String md5;
    private String path;
    private String uploadtime;
    @ManyToOne
    @JoinColumn
    private UserBaseEntity owner;

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(String uploadtime) {
        this.uploadtime = uploadtime;
    }

    public UserBaseEntity getOwner() {
        return owner;
    }

    public void setOwner(UserBaseEntity owner) {
        this.owner = owner;
    }
}
