package com.application.muhammadshiraz.ui.article

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.application.muhammadshiraz.R
import com.application.muhammadshiraz.databinding.ItemCarBinding
import com.application.muhammadshiraz.model.Car

class CarListAdapter: RecyclerView.Adapter<CarListAdapter.ViewHolder>() {
    private lateinit var carList:List<Car>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCarBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_car, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(carList[position])
    }

    override fun getItemCount(): Int {
        return if(::carList.isInitialized) carList.size else 0
    }

    fun updateCarList(carList:List<Car>){
        this.carList = carList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemCarBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = CarViewModel()

        fun bind(car: Car){
            viewModel.bind(car)
            binding.viewModel = viewModel
        }
    }
}