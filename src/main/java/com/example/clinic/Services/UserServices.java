package com.example.clinic.Services;


import com.example.clinic.Entity.Transactions;
import com.example.clinic.Entity.User;
import com.example.clinic.Repository.TransactionRepository;
import com.example.clinic.Repository.UserRepository;
import com.example.clinic.Response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServices {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServicesService servicesService;

    @Autowired
    private EmailServices emailService;

    private final Map<String, String> otpStorage = new HashMap<>(); // Temporary OTP store

    // Generate a 6-digit OTP
    private String generateOTP() {
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    // Step 1: Send OTP when registering a new user
    public Response sendOtpToUser(String email) {
        Response response = new Response();
        User existingUser = userRepository.findByGmail(email);

        if (existingUser != null) {
            response.setDesc("User already exists.");
            response.setRc("01");
            return response;
        }

        String otp = generateOTP();
        otpStorage.put(email, otp);  // Store OTP temporarily
        emailService.sendOtpEmail(email, otp); // Send OTP to email

        response.setRc("00");
        response.setDesc("OTP sent to email.");
        return response;
    }

    // Step 2: Verify OTP and Register User
    public Response verifyOtpAndRegister(User user, String otp) {
        Response response = new Response();

        String storedOtp = otpStorage.get(user.getGmail());
        if (storedOtp == null || !storedOtp.equals(otp)) {
            response.setDesc("Invalid or expired OTP.");
            response.setRc("01");
        } else {
            userRepository.save(user);
            otpStorage.remove(user.getGmail());  // Remove OTP after successful registration
            response.setRc("00");
            response.setDesc("User successfully registered.");
        }
        return response;
    }


    public Response userLoginByMobile(String gmail,String password){
        User user = userRepository.findByGmail(gmail);
        Response response = new Response();
        if(user !=null){
            if(user.getPassword().equals(password)){
                response.setDesc("Successfull login ");
                response.setRc("00");
            }
            else{
                response.setDesc("Incorrect password");
                response.setRc("01");
            }
        }
        else{
            response.setDesc("User not found or Incorrect Mobile");
            response.setRc("02");
        }
        return response;
    }

    public User searchUserByGmail(String gmail){
        return userRepository.findByGmail(gmail);
    }

    public Response addNewTransaction(Transactions transactions){
        Response response = new Response();
        Transactions transaction = transactionRepository.findByTransactionId(transactions.getTransactionId());
        if(transaction != null){
            response.setDesc("Transaction already exist");
            response.setRc("01");
        }
        else{
            transactionRepository.save(transactions);
            User user = userRepository.findByGmail(transactions.getGmail());
            user.addTransactionToHistory(transactions);
            userRepository.save(user);
            response.setDesc("Successfully Added");
            response.setRc("00");
        }
        return response;
    }

//    public User searchUserByGmail(String gmail){
//        User user = userRepository.findByGmail(gmail);
//        return user;
//    }


}



//    public Response addUserNewService(String serviceId, String serviceStatus, String date,){
//
//    }
