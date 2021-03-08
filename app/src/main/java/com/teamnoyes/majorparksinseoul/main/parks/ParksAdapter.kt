package com.teamnoyes.majorparksinseoul.main.parks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teamnoyes.majorparksinseoul.R
import com.teamnoyes.majorparksinseoul.databinding.ParksFragmentBinding
import com.teamnoyes.majorparksinseoul.databinding.ParksItemBinding
import com.teamnoyes.majorparksinseoul.datamodel.ModelParks
import com.teamnoyes.majorparksinseoul.main.MainFragmentDirections

class ParksAdapter(): ListAdapter<ModelParks, ParksAdapter.ParksViewHolder>(ParksDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParksViewHolder {
        return ParksViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ParksViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ParksViewHolder private constructor(val binding: ParksItemBinding): RecyclerView.ViewHolder(binding.root){

        init {
            binding.btnParksItem.setOnClickListener {
                it.findNavController().navigate(MainFragmentDirections.actionMainFragmentToParklistFragment())
            }
        }
        fun bind(item: ModelParks){
            binding.parksItemViewModel = item
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): ParksViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ParksItemBinding.inflate(layoutInflater, parent, false)
                return ParksViewHolder(view)
            }
        }
    }

}

class ParksDiffCallback: DiffUtil.ItemCallback<ModelParks>(){
    override fun areItemsTheSame(oldItem: ModelParks, newItem: ModelParks): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ModelParks, newItem: ModelParks): Boolean {
        return oldItem == newItem
    }
}