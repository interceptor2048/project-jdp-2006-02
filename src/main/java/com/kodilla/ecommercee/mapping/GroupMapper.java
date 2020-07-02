package com.kodilla.ecommercee.mapping;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.GroupDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper {

    public Group mapToGroup(GroupDto groupDto){
        return new Group(groupDto.getId(),groupDto.getName());
    }

    public GroupDto mapToGroupDto(Group group){
        return new GroupDto(group.getId(),group.getName());
    }

    public List<GroupDto> mapToGroupDtoList(List<Group> groupList){
        return groupList.stream()
                .map(group->new GroupDto(group.getId(),group.getName()))
                .collect(Collectors.toList());
    }

}
