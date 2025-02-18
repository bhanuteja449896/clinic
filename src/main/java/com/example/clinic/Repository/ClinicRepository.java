package com.example.clinic.Repository;

import com.example.clinic.Entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepository extends MongoRepository<Admin,String> {
//    Admin findByMobile(String mobile);
}
