package com.example.clinic.Services;


import com.example.clinic.Entity.Services;
import com.example.clinic.Repository.ServicesRepository;
import com.example.clinic.Response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesService {
    @Autowired
    private ServicesRepository servicesRepository;

    public Response addServicesData(Services services){
        Response response = new Response();
        Services oldServices = servicesRepository.findByName(services.getName());
        if (oldServices!=null){
            response.setDesc("Already services exist");
            response.setRc("01");
            return response;
        }
        servicesRepository.save(services);
        response.setRc("00");
        response.setDesc("New services Successfully added");
        return response;
    }

    public List<Services> getAllServices(){
        return servicesRepository.findAll();
    }

}
