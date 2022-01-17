package com.buraktuysuz.fourthhomework.service;

import com.buraktuysuz.fourthhomework.converter.UserMapper;
import com.buraktuysuz.fourthhomework.dto.UserRequestDto;
import com.buraktuysuz.fourthhomework.dto.UserResponseDto;
import com.buraktuysuz.fourthhomework.entitiy.User;
import com.buraktuysuz.fourthhomework.exception.BadRequestException;
import com.buraktuysuz.fourthhomework.service.entityService.UserEntityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private  UserEntityService userEntityService;


    public UserResponseDto save(UserRequestDto userRequestDto) {
        User user = UserMapper.INSTANCE.convertToUserRequestDto(userRequestDto);
        user = userEntityService.save(user);
        UserResponseDto userResponse = UserMapper.INSTANCE.convertToUser(user);
        return userResponse;
    }


    public void deleteById(Long id) {
        User user= userEntityService.findById(id);
        if(user!=null){
            userEntityService.delete(user);
        }else{
            throw new BadRequestException("No user found for this id " + id);
        }
    }

    public List<UserResponseDto> findAll() {
        List<User> userList = userEntityService.findAll();
        List<UserResponseDto>  responseDtoList= UserMapper.INSTANCE.convertToUserRequestDtoList(userList);
        return responseDtoList;
    }
}
