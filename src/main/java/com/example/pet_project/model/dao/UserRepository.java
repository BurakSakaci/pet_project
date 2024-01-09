package com.example.pet_project.model.dao;

import com.example.pet_project.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByUserName(String userName);
    @Query(value = "SELECT * from check_user_login(:p_username, :p_password)", nativeQuery = true)
    Integer checkUserLogin(@Param("p_username") String username, @Param("p_password") String password);

}
