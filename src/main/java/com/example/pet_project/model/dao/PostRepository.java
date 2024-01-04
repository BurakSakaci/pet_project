package com.example.pet_project.model.dao;

import com.example.pet_project.model.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p where p.animal.name= :animalName")
    List<Post> findByAnimalName(@Param("animalName") String animalName);

    @Query(value = "select p.* from post p, \"user\" u where p.user_id = u.id and u.city_id = :cityId " +
            "intersect select p.* from post p where p.animal_id = :animalId", nativeQuery = true)
    List<Post> findByAnimalIdAndCityId(@Param("cityId") Long cityId, @Param("animalId") Long animalId);


}
