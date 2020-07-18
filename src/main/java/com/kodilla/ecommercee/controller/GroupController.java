package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.mapping.GroupMapper;
import com.kodilla.ecommercee.service.GroupDbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/group")
@CrossOrigin("*")
public class GroupController {

    public final Logger LOGGER = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    GroupDbService groupDbService;

    @Autowired
    GroupMapper groupMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getGroups")
    public List<GroupDto> getGroups(){
        LOGGER.info("The following groups were found " + groupDbService.getAllGroups());
        return groupMapper.mapToGroupDtoList(groupDbService.getAllGroups());
    }

    @RequestMapping(method = RequestMethod.POST, value = "createGroup", consumes = APPLICATION_JSON_VALUE)
    public GroupDto createGroup(@RequestBody GroupDto groupDto){
        LOGGER.info("The group " + groupDto.getName() + " was successfully created.");
        return groupMapper.mapToGroupDto(groupDbService.saveGroup(groupMapper.mapToGroup(groupDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getGroup")
    public GroupDto getGroup(@RequestParam Long id){
        LOGGER.info("The following group " + id + " has been selected.");
        return  groupMapper.mapToGroupDto(groupDbService.getGroup(id).orElseThrow(GroupNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateGroup", consumes = APPLICATION_JSON_VALUE)
    public GroupDto updateGroup(@RequestBody GroupDto groupDto){
        LOGGER.info("The following group " + groupDto.getName() + " has been updated.");
        return groupMapper.mapToGroupDto(groupDbService.saveGroup(groupMapper.mapToGroup(groupDto)));
    }

}
