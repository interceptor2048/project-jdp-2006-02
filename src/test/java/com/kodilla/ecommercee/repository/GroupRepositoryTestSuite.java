package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.service.GroupDbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupRepositoryTestSuite {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupDbService groupDbService;

    @Test
    public void testGroupSave(){
        //Given
        Group group = new Group(1L,"test1");
        //When
        groupRepository.save(group);
        List<Group> groups = groupRepository.findAll();
        //Then
        assertTrue(groups.size()>0);
        //CleanUp
        groupRepository.delete(group);
    }

    @Test
    public void testGroupFindById() {
        //Given
        Group group = new Group(1L, "test");
        groupDbService.saveGroup(group);
        //When
        Long id = groupDbService.getGroup(group.getId()).getId();
        Group sunshine = groupDbService.getGroup(id);

        //Then
        assertEquals(sunshine.getId(), id);

        //CleanUp
        groupRepository.delete(group);
    }

    @Test
    public void testGroupFindAll(){
        //Given
        Group group1 = new Group(2L,"test2");
        Group group2 = new Group(3L,"test3");
        Group group3 = new Group(4L,"test4");
        groupRepository.save(group1);
        groupRepository.save(group2);
        groupRepository.save(group3);
        //When
        List<Group> groups = groupRepository.findAll();
        //Then
        assertEquals(3,groups.size());
        //CleanUp
        groupRepository.delete(group1);
        groupRepository.delete(group2);
        groupRepository.delete(group3);
    }

}