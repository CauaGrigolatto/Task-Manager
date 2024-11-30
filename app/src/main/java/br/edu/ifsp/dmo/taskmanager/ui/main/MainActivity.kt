package br.edu.ifsp.dmo.taskmanager.ui.main

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.taskmanager.R
import br.edu.ifsp.dmo.taskmanager.databinding.ActivityMainBinding
import br.edu.ifsp.dmo.taskmanager.databinding.DialogNewTaskBinding
import br.edu.ifsp.dmo.taskmanager.ui.adapter.TaskAdapter
import br.edu.ifsp.dmo.taskmanager.ui.listener.TaskClickListener

class MainActivity : AppCompatActivity(), TaskClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        configListView()
        configOnClickListener()
        configObservers()
    }

    override fun clickDone(position: Int) {
        viewModel.updateTask(position)
    }

    private fun configListView() {
        adapter = TaskAdapter(this, mutableListOf(), this)
        binding.listTasks.adapter = adapter
    }

    private fun configObservers() {
        viewModel.tasks.observe(this, Observer {
            adapter.updateTasks(it)
        })

        viewModel.insertTask.observe(this, Observer {
            val str: String = if (it) {
                getString(R.string.task_inserted_sucess)
            }
            else {
                getString(R.string.task_inserted_error)
            }

            Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
        })
    }

    private fun configOnClickListener() {
        binding.buttonAddTasks.setOnClickListener( {
            openDialogNewTask()
        })
    }

    private fun openDialogNewTask() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_new_task, null)
        var bindingDialog = DialogNewTaskBinding.bind(dialogView)

        val builder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle(getString(R.string.add_new_task))
            .setPositiveButton(
                getString(R.string.save),
                DialogInterface.OnClickListener {dialog, which ->
                    val description = bindingDialog.editDescription.text.toString()
                    viewModel.insertTask(description)
                    dialog.dismiss()
                }
            )
            .setNegativeButton(
                getString(R.string.cancel),
                DialogInterface.OnClickListener {dialog, which ->
                    dialog.dismiss()
                }
            )

        val dialog = builder.create()
        dialog.show()
    }
}