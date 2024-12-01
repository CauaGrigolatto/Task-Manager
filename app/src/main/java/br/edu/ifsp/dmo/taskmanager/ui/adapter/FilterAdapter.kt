package br.edu.ifsp.dmo.taskmanager.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import br.edu.ifsp.dmo.taskmanager.data.model.Filter
import br.edu.ifsp.dmo.taskmanager.databinding.SpinnerFilterItemBinding

class FilterAdapter(
        context: Context,
        private val filters: List<Filter>) : ArrayAdapter<Filter>(context, 0, filters) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val spinnerBinding: SpinnerFilterItemBinding

        if (convertView == null) {
            spinnerBinding = SpinnerFilterItemBinding.inflate(LayoutInflater.from(context), parent, false)
        }
        else {
            spinnerBinding = SpinnerFilterItemBinding.bind(convertView)
        }

        spinnerBinding.filterItem.text = filters[position].toString()
        return spinnerBinding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(position, convertView, parent)
    }
}