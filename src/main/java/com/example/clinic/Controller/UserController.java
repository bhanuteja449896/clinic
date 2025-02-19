package com.example.clinic.Controller;


import com.example.clinic.Entity.Transactions;
import com.example.clinic.Entity.User;
import com.example.clinic.Response.Response;
import com.example.clinic.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserServices userServices;

    // Step 1: Send OTP to email
    @PostMapping("send-otp/{email}")
    public Response sendOtp(@PathVariable("email") String email) {
        return userServices.sendOtpToUser(email);
    }

    // Step 2: Verify OTP and Register User
    @PostMapping("verify-otp")
    public Response verifyOtpAndRegister(@RequestBody User user, @RequestParam String otp) {
        return userServices.verifyOtpAndRegister(user, otp);
    }

    @GetMapping("login/{gmail}/{password}")
    public Response userLogin(@PathVariable("gmail") String gmail,@PathVariable("password") String password){
        return userServices.userLoginByMobile(gmail,password);
    }

    @GetMapping("data/{gmail}")
    public User searchUserDataByMobile(@PathVariable("gmail") String gmail){
        return userServices.searchUserByGmail(gmail);
    }

    @PostMapping("transaction/add")
    public Response addNewTransaction(@RequestBody Transactions transactions){
        return userServices.addNewTransaction(transactions);
    }

//    @GetMapping("")

}