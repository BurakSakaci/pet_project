package com.example.pet_project.service.impl;

import com.example.pet_project.model.dao.ApplicationRepository;
import com.example.pet_project.model.dto.ApplicationDTO;
import com.example.pet_project.model.entities.Application;
import com.example.pet_project.model.entities.Post;
import com.example.pet_project.model.entities.User;
import com.example.pet_project.service.ApplicationService;
import com.example.pet_project.service.PostService;
import com.example.pet_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository repository;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Override
    public Application createApplication(ApplicationDTO applicationDTO) {
        try {
            Application application = new Application();
            User user = userService.getUserById(applicationDTO.getUserId());
            Post post = postService.getPostById(applicationDTO.getPostId());

            application.setUser(user);
            application.setPost(post);
            application.setIsActive(applicationDTO.getIsActive());
            application.setApplyDate(new Date());
            return repository.save(application);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Application getApplicationById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Application> getAllApplications() {
        return repository.findAll();
    }

    @Override
    public Application updateApplication(Application application) {
        return application != null ? repository.save(application) : null;
    }

    @Override
    public void deleteApplication(Long id) {
        repository.deleteById(id);
    }
}
