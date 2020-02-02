package com.example.todolist;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    
    @GetMapping("/")
    public Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @PutMapping("/{taskId}/")
    public Task updateTask(@PathVariable Integer taskId, @RequestBody Map<String, Object> payload) {
        Task task = taskRepository.findById(taskId)
                .orElse(null);

        if (task == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task does not exist");
        }

        task.setBody((String) payload.get("body"));
        task.setFinished((Boolean) payload.get("finished"));
        taskRepository.save(task);

        return task;
    }
}
