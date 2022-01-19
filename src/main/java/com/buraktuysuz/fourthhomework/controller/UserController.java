package com.buraktuysuz.fourthhomework.controller;


import com.buraktuysuz.fourthhomework.dto.CollectionResponseDto;
import com.buraktuysuz.fourthhomework.dto.DebtResponseDto;
import com.buraktuysuz.fourthhomework.dto.UserRequestDto;
import com.buraktuysuz.fourthhomework.dto.UserResponseDto;
import com.buraktuysuz.fourthhomework.entitiy.Debt;
import com.buraktuysuz.fourthhomework.entitiy.User;
import com.buraktuysuz.fourthhomework.service.CollectionService;
import com.buraktuysuz.fourthhomework.service.DebtService;
import com.buraktuysuz.fourthhomework.service.UserService;
import com.buraktuysuz.fourthhomework.service.entityService.UserEntityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private DebtService debtService;
    private CollectionService collectionService;

    @PostMapping
    public ResponseEntity save(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto = userService.save(userRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping
    public ResponseEntity allUser(){
        List<UserResponseDto> usrUserDtoList = userService.findAll();
        return ResponseEntity.ok(usrUserDtoList);
    }

    @GetMapping("/{id}/debts")
    public ResponseEntity findUserDebts(@PathVariable Long id){
        List<DebtResponseDto> debtResponseDtoList = debtService.findAllUserDebts(id);
        return ResponseEntity.ok(debtResponseDtoList);
    }

    @GetMapping("/{id}/calculatedebtstotal")
    public ResponseEntity findUserDebtsAmountTotalwWithInterest(@PathVariable Long id){
        BigDecimal total = debtService.findUserDebtsAmountTotalwWithInterest(id);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/{id}/overduedebts")
    public ResponseEntity findAllUserDebtsOverdue(@PathVariable Long id){
        List<DebtResponseDto> debtResponseDtoList = debtService.findAllUserDebtsOverdue(id);
        return ResponseEntity.ok(debtResponseDtoList);
    }
    @GetMapping("/{id}/overduedebtstotal")
    public ResponseEntity findAllUserDebtsOverdueAmountTotal(@PathVariable Long id){
        BigDecimal total  = debtService.findAllUserDebtsOverdueAmountTotal(id);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/{id}/collections")
    public ResponseEntity findUserCollection(@PathVariable Long id){
        List<CollectionResponseDto> collectionResponseDtos = collectionService.findCollectionByUserId(id);
        return ResponseEntity.ok(collectionResponseDtos);
    }
    @GetMapping("/{id}/interestcollections")
    public ResponseEntity findUserInterestCollections(@PathVariable Long id){
        List<CollectionResponseDto> collectionResponseDtos = collectionService.findCollectionInterestByUserId(id);
        return ResponseEntity.ok(collectionResponseDtos);
    }

  @GetMapping("/{id}/interestcollectionstotal")
    public ResponseEntity findUserInterestCollectionTotal(@PathVariable Long id){
        BigDecimal total = collectionService.findCollectionInterestTotalByUserId(id);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/{id}/currentinteresttotal")
    public ResponseEntity findUserCurrentInterestTotal(@PathVariable Long id){
        BigDecimal total = debtService.findUserCurrentInterestTotal(id);
        return ResponseEntity.ok(total);
    }


}
