package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
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

    @Test
    public void testGroupSave(){
        //Given
        Group group = new Group(1L,"test1");
        //When
        groupRepository.save(group);
        List<Group> groups = groupRepository.findAll();
        //Then
        assertTrue(groups.size()>0);
    }

    @Test
    public void testGroupFindById(){
        //Given
        Group group = new Group(1L,"test");
        groupRepository.save(group);
        //When
        Optional<Group> groupResult = groupRepository.findById(group.getId());
        //Then
    }

    @Test
    public void testGroupFindAll(){
        //Given
        Group group1 = new Group(1L,"test1");
        Group group2 = new Group(2L,"test2");
        Group group3 = new Group(3L,"test3");
        groupRepository.save(group1);
        groupRepository.save(group2);
        groupRepository.save(group3);
        //When
        List<Group> groups = groupRepository.findAll();
        //Then
        assertEquals(3,groups.size());
    }

}