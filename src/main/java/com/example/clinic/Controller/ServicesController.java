package com.example.clinic.Controller;


import com.example.clinic.Entity.Services;
import com.example.clinic.Response.Response;
import com.example.clinic.Services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services/")
@CrossOrigin(origins = "*")
public class ServicesController {

    @Autowired
    private ServicesService servicesService;

    @PostMapping("add")
    public Response addServices(@RequestBody Services services){
        return servicesService.addServicesData(services);
    }

    @GetMapping("all")
    public List<Services> getServices(){
        return servicesService.getAllServices();
    }

}
