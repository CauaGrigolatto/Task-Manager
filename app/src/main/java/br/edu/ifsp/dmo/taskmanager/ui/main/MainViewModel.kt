package br.edu.ifsp.dmo.taskmanager.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.ifsp.dmo.taskmanager.data.dao.TaskDAO
import br.edu.ifsp.dmo.taskmanager.data.model.Filter
import br.edu.ifsp.dmo.taskmanager.data.model.Task

class MainViewModel : ViewModel() {
    private val taskDAO = TaskDAO
    var filter: Filter = Filter.ALL

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
        loadTasks()
    }

    fun insertTask(description: String) {
        taskDAO.insertTask(Task(description, false))
        _insertTask.value = true
        loadTasks()
    }

    fun updateTask(position: Int) {
        val task = _tasks.value!![position]
        taskDAO.changeStatus(task)
        _updateTask.value = true
        loadTasks()
    }

    private fun mock() {
        taskDAO.insertTask(Task("Arrumar a cama", false))
        taskDAO.insertTask(Task("Estudar Spring MVC", true))
        taskDAO.insertTask(Task("Fazer o trabalho de DMO1", false))
    }

    fun loadTasks() {
        when(filter) {
            Filter.DONE -> {
                loadDone()
            }
            Filter.TODO -> {
                loadTodo()
            }
            else -> {
                loadAll()
            }
        }
    }

    private fun loadAll() {
        _tasks.value = taskDAO.getAll()
    }

    private fun loadDone() {
        _tasks.value = taskDAO.getDone()
    }

    private fun loadTodo() {
        _tasks.value = taskDAO.getTodo()
    }
}