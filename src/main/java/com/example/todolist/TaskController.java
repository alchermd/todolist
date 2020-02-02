package com.example.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

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

    @PostMapping("/")
    public Task createTask(@RequestBody Map<String, Object> payload) {
        Task task = new Task();
        task.setBody((String) payload.get("body"));
        task.setFinished(false);

        taskRepository.save(task);

        return task;
    }

    @DeleteMapping("/{taskId}/")
    public ResponseEntity<?> deleteTask(@PathVariable Integer taskId) {
        taskRepository.deleteById(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
