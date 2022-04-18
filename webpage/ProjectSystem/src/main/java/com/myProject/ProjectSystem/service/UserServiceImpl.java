package com.myProject.ProjectSystem.service;

import com.myProject.ProjectSystem.model.File;
import com.myProject.ProjectSystem.model.User;
import com.myProject.ProjectSystem.repo.FileRepo;
import com.myProject.ProjectSystem.repo.UserRepo;
import com.myProject.ProjectSystem.type.FileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private FileRepo fileRepo;
    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User addFile(FileData fileData) {
        User user = userRepo.findByName(fileData.getName());
        File file = new File();
        file.setText(fileData.getText());
        file.setUserID(user.getId());
        fileRepo.save(file);
        return user;
    }

    @Override
    public List<File> getFiles(User username) {
        User myUser = userRepo.findByName(username.getName());
        return userRepo.findByUserID(myUser.getId());
    }
}
