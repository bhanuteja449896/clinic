package com.example.clinic.Controller;


import com.example.clinic.Entity.Contact;
import com.example.clinic.Entity.Services;
import com.example.clinic.Repository.ContactRepository;
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

    @Autowired
    private ContactRepository contactRepository;

    @PostMapping("add")
    public Response addServices(@RequestBody Services services){
        return servicesService.addServicesData(services);
    }

    @GetMapping("all")
    public List<Services> getServices(){
        return servicesService.getAllServices();
    }

    @GetMapping("{id}")
    public Services findServicesById(@PathVariable("id") String id){
        return servicesService.searchServiceById(id);
    }

    @DeleteMapping("delete-service/{id}")
    public Response deleteServiceById(@PathVariable("id") String id){
        return servicesService.deleteServiceById(id);
    }

    @PutMapping("modify-service/{id}")
    public Response modifyService(@PathVariable("id") String id, @RequestBody Services updatedService) {
        return servicesService.modifyService(id, updatedService);
    }

    @PostMapping("add/contact/user-data")
    public Response addContactUserData(@RequestBody Contact contact){
        Response response = new Response();
        contactRepository.save(contact);
        response.setDesc("Successfully added");
        response.setRc("00");
        return response;
    }


}
