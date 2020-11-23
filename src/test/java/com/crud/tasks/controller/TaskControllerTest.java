package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DbService service;
    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldFetchTasks() throws Exception {
        //  Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1, "title", "content"));

        when(service.getAllTasks()).thenReturn(taskList);
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1, "title", "content"));
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);
        //  When & Then
        mockMvc.perform(get("/v1/task/getTasks"))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("title")))
                .andExpect((jsonPath("$[0].content", is("content"))));
    }

    @Test
    public void shouldFetchTask() throws Exception {
        //  Given
        Task task = new Task(1, "title", "content");
        TaskDto taskDto = new TaskDto(1, "title", "content");

        when(service.getTask(1l)).thenReturn(java.util.Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //  When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //  Given
        Task task = new Task(1, "title", "content");
        //  When & Then
        mockMvc.perform(delete("/v1/task/deleteTask?taskId=1"));
        verify(service, times(1)).deleteTask(1l);
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //  Given
        TaskDto taskDto = new TaskDto(1, "title", "content");
        Task task = new Task(1, "title", "content");

        when(service.saveTask(any())).thenReturn(task);
        when(taskMapper.mapToTask(any())).thenReturn(task);
        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //  When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("title")))
                .andExpect(jsonPath("$.content", is("content")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //  Given
        TaskDto taskDto = new TaskDto(1, "Test1", "Test2");

        Task task = new Task(1, "title", "content");

        when(service.saveTask(any())).thenReturn(task);
        when(taskMapper.mapToTask(any())).thenReturn(task);
        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
//        When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent));

        verify(service, times(1)).saveTask(task);
    }
}

