package com.buraktuysuz.fourthhomework.controller;


import com.buraktuysuz.fourthhomework.dto.DebtRequestDto;
import com.buraktuysuz.fourthhomework.dto.DebtResponseDto;
import com.buraktuysuz.fourthhomework.dto.UserRequestDto;
import com.buraktuysuz.fourthhomework.dto.UserResponseDto;
import com.buraktuysuz.fourthhomework.service.DebtService;
import com.buraktuysuz.fourthhomework.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/debts")
@AllArgsConstructor
public class DebtController {

    private DebtService debtService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody DebtRequestDto debtRequestDto){
        DebtResponseDto debtResponseDto = debtService.save(debtRequestDto);
        return ResponseEntity.ok(debtResponseDto);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        debtService.deleteById(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping
    public ResponseEntity allUser(){
        List<DebtResponseDto> usrUserDtoList = debtService.findAll();
        return ResponseEntity.ok(usrUserDtoList);
    }

}
