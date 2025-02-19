package com.example.clinic.Services;


import com.example.clinic.Entity.Admin;
import com.example.clinic.Entity.Transactions;
import com.example.clinic.Entity.User;
import com.example.clinic.Repository.AdminRepository;
import com.example.clinic.Repository.TransactionRepository;
import com.example.clinic.Repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

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

    public Response transactionStatusUpdate(String transactionId, String status) {
        Response response = new Response();

        // Find transaction by transactionId
        Transactions transaction = transactionRepository.findByTransactionId(transactionId);
        if (transaction == null) {
            response.setDesc("Transaction Not Found");
            response.setRc("01");
            return response;
        }
        User user = userRepository.findByGmail(transaction.getGmail());
        if (user == null) {
            response.setDesc("User Not Found");
            response.setRc("02");
            return response;
        }

        // Update status in Transactions collection
        transaction.setStatus(status);
        transactionRepository.save(transaction);

        // Update status inside User's history
        List<Transactions> history = user.getHistory();
        for (Transactions trans : history) {
            if (trans.getTransactionId().equals(transactionId)) {
                trans.setStatus(status);
                break;  // No need to continue loop after finding the transaction
            }
        }
        // Save updated user history
        user.setHistory(history);
        userRepository.save(user);

        response.setDesc("Transaction Status Updated Successfully");
        response.setRc("00");
        return response;
    }

    public Admin searchAdminByUsername(String username){
        return adminRepository.findByUsername(username);
    }


}
