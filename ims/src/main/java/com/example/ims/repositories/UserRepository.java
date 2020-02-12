package com.example.ims.repositories;

import com.example.ims.models.Product;
import com.example.ims.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUserid(String userid);
}
