package com.brainsci.filesys.repository;

import com.brainsci.filesys.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, String> {
}
