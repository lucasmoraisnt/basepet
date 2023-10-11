package br.com.fiap.epictask.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {
    
    @Autowired
    TaskRepository repository;

    public List<Task> findAll(){
        return repository.findAll();
    }

    public boolean delete(Long id){
        var task = repository.findById(id);

        if(task.isEmpty()) return false;

        repository.deleteById(id);
        return true;
    }

    public void save(Task task) {
        repository.save(task);
    }

}
