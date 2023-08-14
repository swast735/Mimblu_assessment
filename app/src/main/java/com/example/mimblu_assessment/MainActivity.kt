package com.example.mimblu_assessment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.mimblu_assessment.databinding.ActivityMainBinding
import com.example.mimblu_assessment.databinding.SympRowBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val lybind: View=layoutInflater.inflate(R.layout.symp_row,null)
        setContentView(bind.root)
        val i = this.getSharedPreferences("SharedPref", MODE_PRIVATE)
        val list = ArrayList<DataModel>()
        val hashMap = HashMap<String, String>()
        val api = "http://dev.mimblu.com/mimblu-yii2-1552/api/user/symptoms"
        val rv: RecyclerView?
        rv = bind.symplist
        val rq: RequestQueue = Volley.newRequestQueue(this)
        val req = JsonObjectRequest(
            Request.Method.GET, api, null,
            { res ->
                val ja = res.getJSONArray("list")
                for (j in 0 until ja.length()) {
                    val jo = ja.getJSONObject(j)
                    val symp = DataModel(jo.getString("title"), (jo.getString("id")))
                    hashMap[jo.getString("title")] = jo.getString("id")
                    list.add(symp)
                }
                rv.layoutManager = LinearLayoutManager(this)
                val adapter = SympAdapter(list, this)
                rv.adapter = adapter
            },
            { err ->
                Log.d("TAG", err.message.toString())
            })
        rq.add(req)
        var a: Set<String>
//        bind.next.isEnabled=false
        val cb:CardView=lybind.findViewById(R.id.cblist)
            bind.next.isEnabled=true
            bind.next.setOnClickListener {
                    val v = ArrayList<String>()
                    val s = i.getString("SYN", " ").toString()
                    if (s.isNotEmpty()) {
                        a = s.split(", ").toSet()
                        for (j in a) {
                            v.add(hashMap[j.removePrefix("[").removeSuffix("]")].toString())
                        }
                        val int = Intent(this@MainActivity, Subscription::class.java)
                        int.putExtra("IDs", v)
                        startActivity(int)
                    }
                }
    }
}