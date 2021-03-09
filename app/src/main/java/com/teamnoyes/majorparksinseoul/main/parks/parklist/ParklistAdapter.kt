package com.teamnoyes.majorparksinseoul.main.parks.parklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.teamnoyes.majorparksinseoul.databinding.ParklistItemBinding
import com.teamnoyes.majorparksinseoul.datamodel.ModelParklist

class ParklistAdapter: ListAdapter<ModelParklist, ParklistAdapter.ParklistViewHolder>(ParklistDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParklistViewHolder {
        return ParklistViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ParklistViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ParklistViewHolder private constructor(val binding: ParklistItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: ModelParklist){
            binding.parklistItemViewModel = item
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup): ParklistViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ParklistItemBinding.inflate(layoutInflater, parent, false)
                return ParklistViewHolder(view)
            }
        }
    }
}

class ParklistDiffCallback: DiffUtil.ItemCallback<ModelParklist>(){
    override fun areItemsTheSame(oldItem: ModelParklist, newItem: ModelParklist): Boolean {
        return oldItem.P_IDX == newItem.P_IDX
    }

    override fun areContentsTheSame(oldItem: ModelParklist, newItem: ModelParklist): Boolean {
        return oldItem == newItem
    }
}