package dev.itboot.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.itboot.rest.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
