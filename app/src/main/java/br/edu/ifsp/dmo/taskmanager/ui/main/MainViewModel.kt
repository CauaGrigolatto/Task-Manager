package br.edu.ifsp.dmo.taskmanager.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.ifsp.dmo.taskmanager.data.dao.TaskDAO
import br.edu.ifsp.dmo.taskmanager.data.model.Task

class MainViewModel : ViewModel() {
    private val taskDAO = TaskDAO

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>>
        get() {
            return _tasks
        }

    private val _insertTask = MutableLiveData<Boolean>()
    val insertTask: LiveData<Boolean> = _insertTask

    private val _updateTask = MutableLiveData<Boolean>()
    val updateTask: LiveData<Boolean>
        get() {
            return _updateTask
        }

    init {
        mock()
        load()
    }

    fun insertTask(description: String) {
        taskDAO.add(Task(description, false))
        _insertTask.value = true
        load()
    }

    fun updateTask(position: Int) {
        val task = taskDAO.getAll()[position]
        task.isCompleted = !task.isCompleted
        _updateTask.value = true
        load()
    }

    private fun mock() {
        taskDAO.add(Task("Arrumar a cama", false))
        taskDAO.add(Task("Estudar Spring MVC", true))
        taskDAO.add(Task("Fazer o trabalho de DMO1", false))
    }

    private fun load() {
        _tasks.value = taskDAO.getAll()
    }
}