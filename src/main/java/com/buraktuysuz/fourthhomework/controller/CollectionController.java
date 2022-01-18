package com.buraktuysuz.fourthhomework.controller;


import com.buraktuysuz.fourthhomework.dto.CollectionResponseDto;
import com.buraktuysuz.fourthhomework.dto.DebtRequestDto;
import com.buraktuysuz.fourthhomework.dto.DebtResponseDto;
import com.buraktuysuz.fourthhomework.service.CollectionService;
import com.buraktuysuz.fourthhomework.service.DebtService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/collection")
@AllArgsConstructor
public class CollectionController {

    private CollectionService collectionService;

    @GetMapping("")
    public ResponseEntity findAll(){
        List<CollectionResponseDto> collectionResponseDtos = collectionService.findAll();
        return ResponseEntity.ok(collectionResponseDtos);
    }
    @GetMapping("/betweendates")
    public ResponseEntity findDebtBetweenDate(@RequestParam("first") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate  firstDate, @RequestParam("last") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate  lastDate){
        List<CollectionResponseDto> collectionResponseDtos = collectionService.findAllBetweenDate(firstDate,lastDate);
        return ResponseEntity.ok(collectionResponseDtos);
    }





}
