package com.example.clinic.Repository;


import com.example.clinic.Entity.Transactions;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends MongoRepository<Transactions,String> {
    Transactions findByTransactionId(String transactionId);
}
