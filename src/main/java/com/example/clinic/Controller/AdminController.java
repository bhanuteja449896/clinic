package com.example.clinic.Controller;


import com.example.clinic.Entity.Transactions;
import com.example.clinic.Response.Response;
import com.example.clinic.Services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private AdminServices adminServices;

    @GetMapping("transactions/all")
    public List<Transactions> searchTransactions(){
        return adminServices.getAllTransactions();
    }

    @GetMapping("login/{username}/{password}")
    public Response adminLogin(@PathVariable("username") String username, @PathVariable("password") String password){
        return adminServices.adminLogin(username,password);
    }



}
