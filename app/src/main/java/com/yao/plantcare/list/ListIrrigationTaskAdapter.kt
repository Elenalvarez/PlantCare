package com.yao.plantcare.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yao.plantcare.R
import com.yao.plantcare.database.MyPlants.MyPlantEntity

class ListIrrigationTaskAdapter: RecyclerView.Adapter<ListIrrigationTaskAdapter.MyViewHolder>() {

    private var myPlantList = emptyList<MyPlantEntity>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val text_name: TextView
        val text_task: TextView
        val img: ImageView
        val task_row: androidx.constraintlayout.widget.ConstraintLayout

        init {
            text_name = itemView.findViewById(R.id.task_name)
            text_task = itemView.findViewById(R.id.task)
            img = itemView.findViewById(R.id.image_task_list)
            task_row = itemView.findViewById(R.id.task_row)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.task_row, parent, false)
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
        holder.text_task.text = "Regar"

        holder.task_row.getViewById(R.id.btn_task).setOnClickListener {
            // TODO: update de las tareas
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