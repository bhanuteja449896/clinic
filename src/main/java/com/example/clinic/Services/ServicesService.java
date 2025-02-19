package com.example.clinic.Services;


import com.example.clinic.Entity.Services;
import com.example.clinic.Repository.ServicesRepository;
import com.example.clinic.Response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Services searchServiceById(String id){
        Optional<Services> services = servicesRepository.findById(id);
        return services.get();
    }

    public Response deleteServiceById(String id){
        Response response = new Response();
        Optional<Services> services = servicesRepository.findById(id);
        if(services.isPresent()){
            servicesRepository.deleteById(id);
            response.setRc("00");
            response.setDesc(id+"Service successfully deleted");
        }
        else{
            response.setRc("01");
            response.setDesc("Service Not found with id"+id);
        }
        return response;
    }


    public Response modifyService(String id, Services updatedService) {
        Response response = new Response();
        Optional<Services> optionalService = servicesRepository.findById(id);

        if (optionalService.isPresent()) {
            Services existingService = optionalService.get();

            // Update only the provided fields
            if (updatedService.getName() != null) {
                existingService.setName(updatedService.getName());
            }
            if (updatedService.getImage() != null) {
                existingService.setImage(updatedService.getImage());
            }
            if (updatedService.getDesc() != null) {
                existingService.setDesc(updatedService.getDesc());
            }
            if (updatedService.getPrice() != null) {
                existingService.setPrice(updatedService.getPrice());
            }
            if (updatedService.getDocter() != null) {
                existingService.setDocter(updatedService.getDocter());
            }
            if (updatedService.getLocation() != null) {
                existingService.setLocation(updatedService.getLocation());
            }
            if (updatedService.getLikes() != null) {
                existingService.setLikes(updatedService.getLikes());
            }
            if (updatedService.getViews() != null) {
                existingService.setViews(updatedService.getViews());
            }
            if (updatedService.getSlots() != null) {
                existingService.setSlots(updatedService.getSlots());
            }

            servicesRepository.save(existingService);

            response.setRc("00");
            response.setDesc("Service updated successfully");
        } else {
            response.setRc("01");
            response.setDesc("Service not found with id: " + id);
        }
        return response;
    }



}


//('post',delete-service/${id}  ('post', modify-service/${id}); ('GET', services/${id});]  get admin/data/gmail
//post contact/userdata
//and also get admin/data/gmail