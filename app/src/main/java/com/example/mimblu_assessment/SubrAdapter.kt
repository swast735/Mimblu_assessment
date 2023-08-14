package com.example.mimblu_assessment

import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.SpannedString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.text.buildSpannedString
import androidx.recyclerview.widget.RecyclerView

class SubrAdapter(private val subrList:ArrayList<SubrDataModel>, val context: Context): RecyclerView.Adapter<SubrAdapter.ViewHolder>(){
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var head: TextView =itemView.findViewById(R.id.head)
        val  dur:TextView=itemView.findViewById(R.id.dur)
        val desc:TextView=itemView.findViewById(R.id.desc)
        val desc2:TextView=itemView.findViewById(R.id.desc2)
        val cur:TextView=itemView.findViewById(R.id.INR)
        val dis:TextView=itemView.findViewById(R.id.dis)
        val tp:TextView=itemView.findViewById(R.id.tp)
        val disDesc:TextView=itemView.findViewById(R.id.disDesc)
        val cint:Button=itemView.findViewById(R.id.cont)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubrAdapter.ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.subr_col,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return subrList.size
    }

    override fun onBindViewHolder(holder: SubrAdapter.ViewHolder, position: Int) {
        val subr=subrList[position]
        holder.head.text=subr.head
        holder.dur.text=subr.dur+" Days"
        holder.desc.text=subr.desc.subSequence(0 until 33)
        holder.desc2.text=subr.desc2
        holder.cur.text=subr.cur
        holder.dis.text=subr.dis.toString()
        holder.disDesc.text="YOU SAVE "+holder.cur.text+" "+(subr.dis-subr.tp).toString()+" WITH THIS PLAN! "
        holder.tp.text=subr.tp.toString()
        val ss: SpannableString=SpannableString(holder.disDesc.text)
        val orange=ForegroundColorSpan(Color.parseColor("#FF8170"))
        ss.setSpan(orange,13,17,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        holder.disDesc.text=ss
    }
}