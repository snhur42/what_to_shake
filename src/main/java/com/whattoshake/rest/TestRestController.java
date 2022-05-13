package com.whattoshake.rest;

import com.whattoshake.service.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("test_rest")
public class TestRestController {
    private final AppUserService appUserService;

    @Autowired
    public TestRestController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @GetMapping()
    public String test() {
        return "Test";
    }


    @PostMapping("create_user")
    public ResponseEntity<Boolean> createClient(@RequestBody Map<String, String> userJSON) {
        return new ResponseEntity<>(appUserService.save(userJSON), HttpStatus.CREATED);
    }
}
