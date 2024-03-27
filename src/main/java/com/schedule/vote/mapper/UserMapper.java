package com.schedule.vote.mapper;

import com.schedule.vote.dto.user.InUser;
import com.schedule.vote.dto.user.OutUser;
import com.schedule.vote.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface UserMapper {

    User transformaInUserParaUser (InUser inUser);

    OutUser transformaUserParaOutUser (User user);
}
