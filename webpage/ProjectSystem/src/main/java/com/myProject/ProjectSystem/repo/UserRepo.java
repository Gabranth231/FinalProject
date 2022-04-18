package com.myProject.ProjectSystem.repo;

import com.myProject.ProjectSystem.model.File;
import com.myProject.ProjectSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Query("FROM User WHERE name = ?1")
    User findByName(String name);
    @Query("FROM File WHERE userID = ?1")
    List<File> findByUserID(int userID);
}
