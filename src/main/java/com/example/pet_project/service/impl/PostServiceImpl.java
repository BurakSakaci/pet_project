package com.example.pet_project.service.impl;

import com.example.pet_project.model.dao.AnimalRepository;
import com.example.pet_project.model.dao.PostRepository;
import com.example.pet_project.model.dao.UserRepository;
import com.example.pet_project.model.dto.PostDTO;
import com.example.pet_project.model.entities.Animal;
import com.example.pet_project.model.entities.Post;
import com.example.pet_project.model.entities.User;
import com.example.pet_project.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public Post createPost(PostDTO postDTO) {
        postDTO.setCreatedDate(new Date());
        return repository.save(postDTOToPost(postDTO));
    }

    @Override
    public Post getPostById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    @Override
    public Post updatePost(PostDTO post) {
        return post != null ? repository.save(postDTOToPost(post)) : null;
    }

    @Override
    public void deletePost(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Post> findByAnimalName(String animalName) {
        List<Post> posts = null;
        if (animalName != null) {
            posts = repository.findByAnimalName(animalName);
        }
        return posts;
    }

    public Post postDTOToPost(PostDTO postDTO) {
        Post post = new Post();
        post.setId(postDTO.getId());

        // You'll need to fetch the User entity by userId and set it here
        if (postDTO.getUserName() != null) {
            String userName = postDTO.getUserName();
            User createdBy = userRepository.getByUserName(userName);
            post.setCreatedBy(createdBy);
        }

        post.setCreatedDate(postDTO.getCreatedDate());
        post.setUpdatedDate(postDTO.getUpdatedDate());
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setIsActive(postDTO.getIsActive());
        post.setImageUrl(postDTO.getImageUrl());
        if (postDTO.getAnimalName() != null) {
            String animalName = postDTO.getAnimalName();
            Animal animal = animalRepository.getByName(animalName);
            post.setAnimal(animal);
        }
        return post;
    }

    @Override
    public PostDTO postToPostDto(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setUserName(post.getCreatedBy().getUserName());
        postDTO.setCreatedDate(post.getCreatedDate());
        postDTO.setUpdatedDate(post.getUpdatedDate());
        postDTO.setTitle(post.getTitle());
        postDTO.setDescription(post.getDescription());
        postDTO.setIsActive(post.getIsActive());

        // If you want to map the animal information as well, you can do it here
        if (post.getAnimal() != null) {
            postDTO.setAnimalName(post.getAnimal().getName());
        }

        return postDTO;
    }

    @Override
    public List<Post> findByAnimalIdAndCityId(Long cityId, Long animalId) {
        return repository.findByAnimalIdAndCityId(cityId, animalId);
    }
}
