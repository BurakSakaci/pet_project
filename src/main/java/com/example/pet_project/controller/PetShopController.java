package com.example.pet_project.controller;

import com.example.pet_project.model.dto.*;
import com.example.pet_project.model.entities.*;
import com.example.pet_project.service.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/petShop")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PetShopController {

    private AnimalService animalService;
    private ApplicationService applicationService;
    private CityService cityService;
    private PostService postService;
    private ProductService productService;
    private UserService userService;


    @PostMapping("/addAnimal")
    public ResponseEntity<Void> addAnimal(@Valid @RequestBody Animal animal) {
        animalService.createAnimal(animal);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getAllAnimals")
    public ResponseEntity<List<AnimalDTO>> getAllAnimals() {
        return new ResponseEntity(animalService.getAllAnimals(), HttpStatus.OK);
    }

    @GetMapping("getAnimalById/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
        Animal animal = animalService.getAnimalById(id);
        if (animal != null) {
            return new ResponseEntity<>(animal, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Animal> updateAnimal(@Valid @RequestBody Animal animal) {
        Animal updatedAnimal = animalService.updateAnimal(animal);
        if (updatedAnimal != null) {
            return new ResponseEntity<>(updatedAnimal, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createApplication")
    public ResponseEntity<Application> createApplication(@RequestBody Application application) {
        Application createdApplication = applicationService.createApplication(application);
        return new ResponseEntity<>(createdApplication, HttpStatus.CREATED);
    }

    @GetMapping("/getApplicationById/{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable Long id) {
        Application application = applicationService.getApplicationById(id);
        if (application != null) {
            return new ResponseEntity<>(application, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllApplications")
    public ResponseEntity<List<Application>> getAllApplications() {
        List<Application> applications = applicationService.getAllApplications();
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    @PutMapping("/updateApplication")
    public ResponseEntity<Application> updateApplication(@Valid @RequestBody Application application) {
        Application updatedApplication = applicationService.updateApplication(application);
        if (updatedApplication != null) {
            return new ResponseEntity<>(updatedApplication, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createCity")
    public ResponseEntity<City> createCity(@RequestBody City city) {
        City createdCity = cityService.createCity(city);
        return new ResponseEntity<>(createdCity, HttpStatus.CREATED);
    }

    @GetMapping("/getCityById/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Long id) {
        City city = cityService.getCityById(id);
        if (city != null) {
            return new ResponseEntity<>(city, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllCities")
    public ResponseEntity<List<CityDto>> getAllCities() {
        List<CityDto> cities = cityService.getAllCities();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @PutMapping("/updateCity")
    public ResponseEntity<City> updateCity(@Valid @RequestBody City city) {
        City updatedCity = cityService.updateCity(city);
        if (updatedCity != null) {
            return new ResponseEntity<>(updatedCity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createPost")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        try {
            Post post = postService.createPost(postDTO);
            PostDTO createdPost = postService.postToPostDto(post);
            return new ResponseEntity<>(createdPost, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("hata");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/updatePost")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO) {
        try {
            Post post = postService.updatePost(postDTO);
            PostDTO createdPost = postService.postToPostDto(post);
            return new ResponseEntity<>(createdPost, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getPostById/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        if (post != null) {
            return new ResponseEntity<>(post, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllPosts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/findPostsByAnimalName")
    public ResponseEntity<List<Post>> findPostsByAnimalName(@RequestParam String animalName) {
        return new ResponseEntity<>(postService.findByAnimalName(animalName), HttpStatus.OK);
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody ProductDTO product) {
        Product updatedProduct = productService.updateProduct(product);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/checkUserLogin")
    public ResponseEntity<Integer> checkUserLogin(@RequestBody UserLoginDTO userLoginDTO) {
        Integer userLogin = userService.checkUserLogin(userLoginDTO);
        return new ResponseEntity<>(userLogin, HttpStatus.OK);
    }

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getUserByUserName")
    public ResponseEntity<User> getUserByUserName(@RequestParam String userName) {
        User user = userService.getUserByUserName(userName);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@Valid @RequestBody UserDTO user) {
        User updatedUser = userService.updateUser(user);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/deleteAnimal/{id}")
    public ResponseEntity<String> deleteAnimal(@PathVariable Long id) {
        String message = "";
        boolean deleted = true;
        try {
            animalService.deleteAnimal(id);
        } catch (Exception e) {
            deleted = false;
            message = e.getMessage();
        }
        if (deleted) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteApplication/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable Long id) {
        String message = "";
        boolean deleted = true;
        try {
            applicationService.deleteApplication(id);
        } catch (Exception e) {
            deleted = false;
            message = e.getMessage();
        }
        if (deleted) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteCity/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable Long id) {
        String message = "";
        boolean deleted = true;
        try {
            cityService.deleteCity(id);
        } catch (Exception e) {
            deleted = false;
            message = e.getMessage();
        }
        if (deleted) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        String message = "";
        boolean deleted = true;
        try {
            postService.deletePost(id);
        } catch (Exception e) {
            deleted = false;
            message = e.getMessage();
        }
        if (deleted) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        String message = "";
        boolean deleted = true;
        try {
            productService.deleteProduct(id);
        } catch (Exception e) {
            deleted = false;
            message = e.getMessage();
        }
        if (deleted) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean deleted = true;
        String message = "";
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            deleted = false;
            message = e.getMessage();
        }
        if (deleted) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
