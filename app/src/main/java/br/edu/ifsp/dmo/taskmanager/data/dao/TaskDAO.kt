package br.edu.ifsp.dmo.taskmanager.data.dao

import br.edu.ifsp.dmo.taskmanager.data.model.Task
import java.util.stream.Collectors

object TaskDAO {
    private var tasks: MutableList<Task> = mutableListOf()

    fun add(task: Task) {
        tasks.add(task)
    }

    fun getAll(): MutableList<Task> {
        return tasks
    }

    fun getById(id: Long): Task {
        return tasks.stream().filter {t -> t.id == id}.findFirst().orElse(null)
    }
}