package com.myProject.ProjectSystem.service;

import com.myProject.ProjectSystem.model.File;
import com.myProject.ProjectSystem.model.User;
import com.myProject.ProjectSystem.type.FileData;
import com.myProject.ProjectSystem.type.Transfer;

import java.util.List;


public interface UserService {
    public String login(User user);
    public User saveUser(User user);
    public List<User> getAllUsers();
    public User addFile(FileData fileData);
    public List<File> getFiles(User user);
    public FileData transfer(Transfer transfer);
}
