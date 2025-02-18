package com.example.clinic.Services;


import com.example.clinic.Entity.Admin;
import com.example.clinic.Entity.Transactions;
import com.example.clinic.Repository.AdminRepository;
import com.example.clinic.Repository.TransactionRepository;
import com.example.clinic.Response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServices {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AdminRepository adminRepository;

    public List<Transactions> getAllTransactions(){
        List<Transactions> transactions = transactionRepository.findAll();
        return transactions;
    }

    public Response adminLogin(String username,String password){
        Response response = new Response();
        Admin admin = adminRepository.findByUsername(username);
        if(admin != null){
            if(admin.getPassword().equals(password)){
                response.setDesc("Succesfull login");
                response.setRc("00");
            }
            else{
                response.setDesc("Incorrect Password");
                response.setRc("01");
            }
        }
        else{
            response.setDesc("Incorrect Username");
            response.setRc("02");
        }
        return response;

    }

}
