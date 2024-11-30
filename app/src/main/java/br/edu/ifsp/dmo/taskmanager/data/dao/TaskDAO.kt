package br.edu.ifsp.dmo.taskmanager.data.dao

import br.edu.ifsp.dmo.taskmanager.data.model.Task

object TaskDAO {
    private var tasks: MutableList<Task> = mutableListOf()

    fun insertTask(task: Task) {
        if (task.isCompleted) {
            append(task)
        }
        else {
            push(task)
        }
    }

    fun append(task: Task) {
        tasks.add(task)
    }

    fun push(task: Task) {
        tasks.add(0, task)
    }

    fun getAll(): MutableList<Task> {
        return tasks
    }

    fun getById(id: Long): Task {
        return tasks.stream().filter {t -> t.id == id}.findFirst().orElse(null)
    }

    fun changeStatus(task: Task) {
        task.isCompleted = !task.isCompleted
        readjustPosition(task)
    }

    fun readjustPosition(task: Task) {
        tasks.remove(task)
        insertTask(task)
    }
}