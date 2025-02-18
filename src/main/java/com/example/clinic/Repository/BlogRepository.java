package com.example.clinic.Repository;

import com.example.clinic.Entity.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends MongoRepository<Blog, String> {
    Blog findByName(String name);
}
