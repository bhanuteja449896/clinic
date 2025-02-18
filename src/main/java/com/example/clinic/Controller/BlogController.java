package com.example.clinic.Controller;

import com.example.clinic.Entity.Blog;
import com.example.clinic.Response.Response;
import com.example.clinic.Services.BlogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blogs/")
public class BlogController {

    @Autowired
    private BlogServices blogServices;

    @PostMapping("add")
    public Response addNewBlog(@RequestBody Blog blog){
        return blogServices.addBlog(blog);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> searchBlogById(@PathVariable("id") String id) {
        Optional<Blog> blog = blogServices.getBlogById(id);

        if (blog.isPresent()) {
            return ResponseEntity.ok(blog.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Blog not found with id: " + id);
        }
    }


    @GetMapping("all")
    public List<Blog> getAllBlogs(){
        return blogServices.getBlogs();
    }

    @DeleteMapping("/delete/{id}")
    public Response deleteBlogById(@PathVariable("id") String id){
        return blogServices.deleteBlog(id);
    }

    @PutMapping("update-likes/{id}")
    public Response updateLikes(@PathVariable("id") String id, @RequestParam("increment") int increment) {
        return blogServices.updateBlogLikes(id, increment);
    }





}
