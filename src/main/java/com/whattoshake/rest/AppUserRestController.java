package com.whattoshake.rest;

import com.whattoshake.dto.AppUserDTO;
import com.whattoshake.model.user.AppUser;
import com.whattoshake.service.user.impl.AppUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/user")
public class AppUserRestController {
    private final AppUserServiceImpl appUserService;

    @Autowired
    public AppUserRestController(@Qualifier("appUserServiceImpl") AppUserServiceImpl appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("create_user")
    public ResponseEntity<Boolean> createUser(@RequestBody Map<String, String> userJSON) {
        return new ResponseEntity<>(appUserService.save(userJSON), HttpStatus.CREATED);
    }

    @GetMapping("get_user/{userId}")
    public ResponseEntity<AppUser> getUserById(@PathVariable String userId) {
        return new ResponseEntity<>(appUserService.findById(userId), HttpStatus.OK);
    }

    @PutMapping("update_user/{userId}")
    public ResponseEntity<AppUser> updateUser(@PathVariable String userId, @RequestBody AppUserDTO userDTO) {
        return new ResponseEntity<>(appUserService.update(userId, userDTO), HttpStatus.OK);
    }

}
