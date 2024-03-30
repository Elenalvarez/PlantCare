package com.yao.plantcare.my_plants.list_plants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yao.plantcare.R
import com.yao.plantcare.database.Plant.PlantEntity

class ListPlantsAdapter: RecyclerView.Adapter<ListPlantsAdapter.MyViewHolder>() {

    private var plantList = emptyList<PlantEntity>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val text_common_name: TextView
        val text_specie: TextView
        val image_level: ImageView
        val image_location: ImageView
        val image_list: ImageView

        init {
            text_common_name = itemView.findViewById(R.id.text_common_name)
            text_specie = itemView.findViewById(R.id.text_specie)
            image_level = itemView.findViewById(R.id.image_level)
            image_location = itemView.findViewById(R.id.image_location)
            image_list = itemView.findViewById(R.id.image_list)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return plantList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = plantList[position]
        holder.text_common_name.text = currentItem.commonName.toString()
        holder.text_specie.text = currentItem.species.toString()
        val level_drawable: Int = getDrawable(currentItem.level.toString())
        holder.image_level.setImageResource(level_drawable)
        val location_drawable: Int = getDrawable(currentItem.location.toString())
        holder.image_location.setImageResource(location_drawable)
        val image_drawable: Int = getDrawable(currentItem.image.toString())
        holder.image_list.setImageResource(image_drawable)
    }

    private fun getDrawable(param: String): Int {
        var d: Int = 0

        if (param == "Dificil") d = R.drawable.ic_dificil
        if (param == "Medio") d = R.drawable.ic_medio
        if (param == "Sencillo") d = R.drawable.ic_sencillo
        if (param == "Exterior") d = R.drawable.ic_exterior
        if (param == "Interior") d = R.drawable.ic_interior
        if (param == "aloe") d = R.drawable.aloe
        if (param == "fresas") d = R.drawable.fresas
        if (param == "hierbabuena") d = R.drawable.hierbabuena
        if (param == "jazmin") d = R.drawable.jazmin
        if (param == "poto") d = R.drawable.poto
        if (param == "romero") d = R.drawable.romero

        return d
    }

    fun setData(plants: List<PlantEntity>){
        this.plantList = plants
        notifyDataSetChanged()
    }


}