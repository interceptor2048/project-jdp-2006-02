package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.service.GroupDbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupRepositoryTestSuite {

    @Autowired
    private GroupDbService groupDbService;

    @Test
    public void testGroupSave(){
        //Given
        Group group = new Group("test1");
        //When
        groupDbService.saveGroup(group);
        List<Group> groups = groupDbService.getAllGroups();
        //Then
        assertTrue(groups.size()>0);
        //CleanUp
        groupDbService.deleteGroup(group);
    }

    @Test
    public void testGroupFindById(){
        //Given
        Group group = new Group("test1");
        groupDbService.saveGroup(group);
        //When
        Group result = groupDbService.getGroupById(group.getId());
        //Then
        assertEquals(group.getName(),result.getName());
        //CleanUp
        groupDbService.deleteGroup(group);
    }

    @Test
    public void testGroupFindAll(){
        //Given
        Group group1 = new Group("test2");
        Group group2 = new Group("test3");
        Group group3 = new Group("test4");
        groupDbService.saveGroup(group1);
        groupDbService.saveGroup(group2);
        groupDbService.saveGroup(group3);
        //When
        List<Group> groups = groupDbService.getAllGroups();
        //Then
        assertEquals(3,groups.size());
        assertEquals(group2.getName(),groups.get(1).getName());
        //CleanUp
        groupDbService.deleteGroup(group1);
        groupDbService.deleteGroup(group2);
        groupDbService.deleteGroup(group3);
    }

}