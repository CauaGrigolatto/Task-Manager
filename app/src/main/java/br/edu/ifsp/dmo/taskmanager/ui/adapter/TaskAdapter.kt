package br.edu.ifsp.dmo.taskmanager.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import br.edu.ifsp.dmo.taskmanager.R
import br.edu.ifsp.dmo.taskmanager.data.model.Task
import br.edu.ifsp.dmo.taskmanager.databinding.TaskListItemBinding
import br.edu.ifsp.dmo.taskmanager.ui.listener.TaskClickListener

class TaskAdapter(
    context: Context,
    private var tasks: List<Task>,
    private val clickListener: TaskClickListener) : ArrayAdapter<Task>(context, R.layout.task_list_item, tasks) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: TaskListItemBinding

        if (convertView == null) {
            binding = TaskListItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )

            binding.root.tag = binding
        }
        else {
            binding = convertView.tag as TaskListItemBinding
        }

        val task = getItem(position)

        if (task != null) {
            binding.textTaskDescription.text = task.description

            if (task.isCompleted) {
                binding.imageTaskStatus.setColorFilter(ContextCompat.getColor(context, R.color.green))
            }
            else {
                binding.imageTaskStatus.setColorFilter(ContextCompat.getColor(context, R.color.red))
            }

            binding.imageTaskStatus.setOnClickListener {
                clickListener.clickDone(position)
            }
        }

        return binding.root
    }

    fun updateTasks(newTasks: List<Task>) {
        tasks = newTasks
        clear()
        addAll(tasks)
        notifyDataSetChanged()
    }
}