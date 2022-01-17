package com.buraktuysuz.fourthhomework.controller;


import com.buraktuysuz.fourthhomework.dto.UserRequestDto;
import com.buraktuysuz.fourthhomework.dto.UserResponseDto;
import com.buraktuysuz.fourthhomework.entitiy.User;
import com.buraktuysuz.fourthhomework.service.UserService;
import com.buraktuysuz.fourthhomework.service.entityService.UserEntityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto = userService.save(userRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping
    public ResponseEntity allUser(){
        List<UserResponseDto> usrUserDtoList = userService.findAll();
        return ResponseEntity.ok(usrUserDtoList);
    }

}
