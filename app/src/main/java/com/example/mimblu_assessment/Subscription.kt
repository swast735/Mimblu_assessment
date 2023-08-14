package com.example.mimblu_assessment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.mimblu_assessment.databinding.ActivitySubscriptionBinding


class Subscription : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind:ActivitySubscriptionBinding= ActivitySubscriptionBinding.inflate(layoutInflater)
        setContentView(bind.root)
        val uri="http://dev.mimblu.com/mimblu-yii2-1552/api/plan/all"
        val list = ArrayList<SubrDataModel>()
        bind.prev.setOnClickListener {
            val i=Intent(this@Subscription,MainActivity::class.java)
            startActivity(i)
        }
        var title:String
        var dur:String
        var desc:String
        var desc2:String
        var cur:String
        var dis:Int
        var tp:Int
        val rv=bind.subrList
        val rq:RequestQueue=Volley.newRequestQueue(this)
        val req=JsonObjectRequest(Request.Method.GET,uri,null,
            {res->
                val ja=res.getJSONArray("list")
                for(i in 0 until ja.length()){
                    val jo=ja.getJSONObject(i)
                    title=(jo.getString("title"))
                     dur=(jo.getString("duration"))
                     desc=(jo.getString("description"))
                    desc2=(jo.getString("video_description"))
                    cur=(jo.getString("currency_code"))
                    dis=(jo.getInt("discounted_price"))
                    tp=(jo.getInt("total_price"))
                    val subr = SubrDataModel(title, dur,desc,desc2,cur,dis,tp)
                    list.add(subr)
                    val layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                    rv.layoutManager=layoutManager
                    val adapter=SubrAdapter(list,this)
                    rv.adapter=adapter
                }
            },
            {err->
                Log.d("TAG", err.message.toString())
            })
        rq.add(req)

    }
}