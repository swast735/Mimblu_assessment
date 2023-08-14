package com.example.mimblu_assessment

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.mimblu_assessment.databinding.ActivityMainBinding


class SympAdapter(private val sympList:ArrayList<DataModel>, context: Context) :RecyclerView.Adapter<SympAdapter.ViewHolder>(){
    private val i=context.getSharedPreferences("SharedPref",MODE_PRIVATE )
    private lateinit var sn:String
    lateinit var id: String
    var c:Int=0
     private var setString=ArrayList<String>()
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val  symname: CheckBox =itemView.findViewById(R.id.cb)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.symp_row,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return sympList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val symp=sympList[position]
        val h=holder.symname
        sn=symp.sym
        id=symp.id
        h.text=sn
        h.setOnClickListener {
            if (holder.symname.isChecked) {
                c++
                setString.add(holder.symname.text.toString())
                i.edit().putString("SYN", setString.toString()).apply()
                i.edit().putInt("ELGBL", c).apply()
            }
            else  if(!h.isChecked) {
                setString.remove(h.text.toString())
                c--
                i.edit().putString("SYN", setString.toString()).apply()
                i.edit().putInt("ELGBL", c).apply()
            }
            Log.d("ELGBL",i.getInt("ELGBL",-1).toString())
        }
    }
}
