package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.google.common.base.Objects;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskMapperTests {
    @Test
    public void TaskToTaskDtoMapperTest() {
//        Given
        Task task = new Task(1,"title","content");
        TaskMapper taskMapper = new TaskMapper();
//        When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
//        Then
        assertThat(Objects.equal(task, taskDto));
    }
    @Test
    public void TaskDtoToTaskMapperTest() {
//        Given
        TaskDto taskDto = new TaskDto(1,"title","content");
        TaskMapper taskMapper = new TaskMapper();
//        When
        Task task = taskMapper.mapToTask(taskDto);
//        Then
        assertThat(Objects.equal(task, taskDto));
    }
    @Test
    public void TaskListToTaskDtoListMapperTest() {
  //        Given
        Task task1 = new Task(1,"title","content");
        Task task2 = new Task(2,"title2","content2");
        List<Task> taskList = new ArrayList<Task>();
        taskList.add(task1);
        taskList.add(task2);
        TaskMapper taskMapper = new TaskMapper();
//        When
        List<TaskDto> taskListDto = taskMapper.mapToTaskDtoList(taskList);
//        Then
        assertThat(Objects.equal(taskList, taskListDto));
    }
}
