package com.buraktuysuz.fourthhomework.controller;


import com.buraktuysuz.fourthhomework.dto.*;
import com.buraktuysuz.fourthhomework.service.CollectionService;
import com.buraktuysuz.fourthhomework.service.DebtService;
import com.buraktuysuz.fourthhomework.service.UserService;
import com.buraktuysuz.fourthhomework.service.entityService.CollectionEntityService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/debts")
@AllArgsConstructor
public class DebtController {

    private DebtService debtService;
    private CollectionService collectionService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody DebtRequestDto debtRequestDto){
        DebtResponseDto debtResponseDto = debtService.save(debtRequestDto);
        return ResponseEntity.ok(debtResponseDto);
    }
     @PostMapping("/{id}/pay")
    public ResponseEntity<Object> payByDebtId(@PathVariable Long id){
         List<CollectionResponseDto> responseDtoList = collectionService.payByDebtId(id);
        return ResponseEntity.ok(responseDtoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        debtService.deleteById(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping
    public ResponseEntity findAll(){
        List<DebtResponseDto> usrUserDtoList = debtService.findAll();
        return ResponseEntity.ok(usrUserDtoList);
    }

    @GetMapping("/betweendates")
    public ResponseEntity findDebtBetweenDate(@RequestParam("first") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate  firstDate, @RequestParam("last") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate  lastDate){
        List<DebtResponseDto> usrUserDtoList = debtService.findAllBetweenDate(firstDate,lastDate);
        return ResponseEntity.ok(usrUserDtoList);
    }



}
