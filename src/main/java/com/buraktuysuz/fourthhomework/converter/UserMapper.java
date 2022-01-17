package com.buraktuysuz.fourthhomework.converter;


import com.buraktuysuz.fourthhomework.dto.UserRequestDto;
import com.buraktuysuz.fourthhomework.dto.UserResponseDto;
import com.buraktuysuz.fourthhomework.entitiy.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User convertToUserRequestDto(UserRequestDto userRequestDto);

    @Mapping(target = "fullName", source = ".", qualifiedByName = "toFullName")
    UserResponseDto convertToUser(User user);

    @Mapping(target = "fullName", source = ".", qualifiedByName = "toFullName")
    List<UserResponseDto> convertToUserRequestDtoList(List<User> userList);

    @Named("toFullName")
    default String translateToFullName(User user) {
        return user.getFirstName() +" "+ user.getLastName();
    }


}
