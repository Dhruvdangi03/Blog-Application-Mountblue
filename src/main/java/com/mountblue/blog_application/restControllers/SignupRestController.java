package com.mountblue.blog_application.restControllers;

import com.mountblue.blog_application.DTO.SignupRequest;
import com.mountblue.blog_application.services.BlogUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupRestController {
    private final BlogUserService blogUserService;

    public SignupRestController(BlogUserService blogUserService) {
        this.blogUserService = blogUserService;
    }

    @PostMapping("/rest/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest){
        blogUserService.saveBlogUser(signupRequest);
        return ResponseEntity.ok("Signup was Successful !");
    }
}
