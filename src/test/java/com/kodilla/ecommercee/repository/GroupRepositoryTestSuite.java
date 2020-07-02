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
        Group group = new Group("test1");
        //When
        groupRepository.save(group);
        List<Group> groups = groupRepository.findAll();
        //Then
        assertTrue(groups.size()>0);
        //CleanUp
        groupRepository.delete(group);
    }

    @Test
    public void testGroupFindById(){
        //Given
        Group group = new Group("test2");
        groupRepository.save(group);
        //When
        Optional<Group> resultGroup = groupRepository.findById(group.getId());
        //Then
        assertTrue(resultGroup.isPresent());
        assertEquals(group.getName(),resultGroup.get().getName());
        //CleanUp
        groupRepository.delete(group);
    }

    @Test
    public void testGroupFindAll(){
        //Given
        Group group1 = new Group("test3");
        Group group2 = new Group("test4");
        Group group3 = new Group("test5");
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