package com.example.clinic.Services;

import com.example.clinic.Entity.Blog;
import com.example.clinic.Repository.BlogRepository;
import com.example.clinic.Response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogServices {

    @Autowired
    private BlogRepository blogRepository;

    public Response addBlog(Blog blog){
        Response response = new Response();
        blogRepository.save(blog);
        response.setDesc("Blog added successfully");
        response.setRc("00");
        return response;
    }

    public List<Blog> getBlogs(){
        return blogRepository.findAll();
    }

    public Optional<Blog> getBlogById(String id){
        return blogRepository.findById(id);
    }

    public Response deleteBlog(String id){
        Response response = new Response();
        Optional<Blog> blog = getBlogById(id);
        if(blog.isPresent()){
            blogRepository.deleteById(id);
            response.setRc("00");
            response.setDesc(id+"Blog Successfully Deleted");
        }
        else{
            response.setRc("01");
            response.setDesc(id+"Blog Not present in Database");
        }
            return response;
    }

    public Response updateBlogLikes(String id, int increment) {
        Response response = new Response();
        Optional<Blog> optionalBlog = blogRepository.findById(id);

        if (optionalBlog.isPresent()) {
            Blog blog = optionalBlog.get();

            // Ensure likes field is not null to prevent NullPointerException
            if (blog.getLikes() == null) {
                blog.setLikes(0);
            }

            // Increment or decrement likes
            int updatedLikes = blog.getLikes() + increment;
            blog.setLikes(updatedLikes);

            // Save the updated blog
            blogRepository.save(blog);

            response.setRc("00");
            response.setDesc("Blog likes updated successfully. New likes count: " + updatedLikes);
        } else {
            response.setRc("01");
            response.setDesc("Blog not found with id: " + id);
        }

        return response;
    }


}
