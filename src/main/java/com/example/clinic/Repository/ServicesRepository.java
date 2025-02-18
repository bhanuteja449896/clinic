package com.example.clinic.Repository;


import com.example.clinic.Entity.Admin;
import com.example.clinic.Entity.Services;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends MongoRepository<Services,String> {
    Services findByName(String name);
}
