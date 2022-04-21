package com.myProject.ProjectSystem.service;

import com.myProject.ProjectSystem.AES.Decryption;
import com.myProject.ProjectSystem.AES.Encryption128;
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
    private Encryption128 myObj = new Encryption128();
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private FileRepo fileRepo;
    @Override
    public User saveUser(User user) {
        String str = String.valueOf(myObj.getKey());
        user.setKey(str);

        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public String login(User user) {
        User userCheck = userRepo.findByName(user.getName());
        if(userCheck != null){
            return "Succeed";
        }
        return "Fail";
    }

    @Override
    public User addFile(FileData fileData) {
        User user = userRepo.findByName(fileData.getName());
        File file = new File();//encrypt here
        String encrypted = myObj.encryption(fileData.getText(), user.getKey());
        file.setText(encrypted);
        file.setUserID(user.getId());
        fileRepo.save(file);
        return user;
    }

    @Override
    public List<File> getFiles(User username) {
        User myUser = userRepo.findByName(username.getName());
        Decryption Dobj = new Decryption(myUser.getKey());
        List<File> files = userRepo.findByUserID(myUser.getId());
        for(File f : files){
            String text = Dobj.decryption(f.getText());
            f.setText(text);
        }
        return files;
    }
}
