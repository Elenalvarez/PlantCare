package com.yao.plantcare.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.RecyclerView
import com.yao.plantcare.R
import com.yao.plantcare.database.MyPlants.MyPlantEntity
import com.yao.plantcare.my_plants.MyPlantsFragment

class ListMyPlantsAdapter : RecyclerView.Adapter<ListMyPlantsAdapter.MyViewHolder>() {

    private var myPlantList = emptyList<MyPlantEntity>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val text_name: TextView
        val text_irr: TextView
        val text_fer: TextView
        val img: ImageView
        val row: androidx.constraintlayout.widget.ConstraintLayout

        init {
            text_name = itemView.findViewById(R.id.text_name)
            text_irr = itemView.findViewById(R.id.ay_irrigation)
            text_fer = itemView.findViewById(R.id.ay_fertilize)
            img = itemView.findViewById(R.id.image_my_plant_list)
            row = itemView.findViewById(R.id.my_plant_row)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.my_plant_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return myPlantList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = myPlantList[position]
        val img: Int = getDrawable(currentItem.image)

        holder.img.setImageResource(img)
        holder.text_name.text = currentItem.name
        holder.text_irr.text = buildString {
            append("Regado hace: ")
            append(currentItem.lastIrrigation)
        }

        holder.text_fer.text = buildString {
            append("Fertilizado hace: ")
            append(currentItem.lastFertilize)
        }

        holder.row.setOnClickListener {
            currentItem.id?.let { it1 ->
                holder.itemView.findFragment<MyPlantsFragment>().toPlantFragment(currentItem.plantId,
                    it1
                )
            }
        }
    }

    private fun getDrawable(param: String): Int{
        var d: Int = 0

        if (param == "aloe") d = R.drawable.aloe
        if (param == "fresas") d = R.drawable.fresas
        if (param == "hierbabuena") d = R.drawable.hierbabuena
        if (param == "jazmin") d = R.drawable.jazmin
        if (param == "poto") d = R.drawable.poto
        if (param == "romero") d = R.drawable.romero

        return d
    }

    fun setData(my_plants: List<MyPlantEntity>){
        this.myPlantList = my_plants
        notifyDataSetChanged()
    }

}

