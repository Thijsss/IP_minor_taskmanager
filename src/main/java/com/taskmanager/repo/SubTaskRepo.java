package com.taskmanager.repo;

import com.taskmanager.model.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubTaskRepo extends JpaRepository<SubTask, Long> {
}
