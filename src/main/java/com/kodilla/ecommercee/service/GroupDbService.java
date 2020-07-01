package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupDbService {

    @Autowired
    GroupRepository groupRepository;

    public List<Group> getAllGroups() { return groupRepository.findAll(); }

    public Group getGroup(Long id){
        return groupRepository.findById(id).orElse(new Group());
    }

    public Group saveGroup(Group group){
        return groupRepository.save(group);
    }

}
