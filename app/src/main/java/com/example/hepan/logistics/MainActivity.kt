package com.example.hepan.logistics

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        var adapter = LogisticAdapter(gene())
        rvShow.apply {
            setHasFixedSize(true)
            layoutManager = manager
            this.adapter = adapter
        }
    }

    fun gene():List<String>{
        var l = listOf("成都石羊孵化园】 签收,签收人: 田丽 (13509098987), 感谢使用中通快递,期待再次为您服务!\n2018-04-25 10:06:46"
        ,"【成都市】 快件离开 【成都中转】 发往 【成都石羊孵化园】\n2018-04-24 23:56:42",
                "成都市】 快件到达 【成都中转】\n2018-04-24 23:56:23",
                "漯河市】 快件离开 【漯河中转】 发往 【成都中转】\n2018-04-23 21:31:12",
                "快递已揽件,快递员李明, 电话 13567670008\n2018-04-03 08:09:00")
        return l
    }
}
