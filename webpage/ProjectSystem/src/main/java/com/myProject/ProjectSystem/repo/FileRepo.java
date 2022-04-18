package com.myProject.ProjectSystem.repo;

import com.myProject.ProjectSystem.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepo extends JpaRepository<File, Integer> {
}
