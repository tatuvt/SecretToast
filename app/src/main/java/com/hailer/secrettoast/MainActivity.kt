package com.hailer.secrettoast

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.hailer.secrettoast.R.layout.list_item
import kotlinx.android.synthetic.main.activity_main.*
import org.joda.time.DateTime
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import org.json.JSONException

class MainActivity : AppCompatActivity()
{
    private var adapter = MessageViewAdapter(getJsonData(), list_item)

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MessageViewAdapter(getJsonData(), list_item)

        /*Lisää listalle LayoutManager*/
        message_list.layoutManager = LinearLayoutManager(this)
        /*Linkitä adapteri listaan*/
        message_list.adapter = MessageViewAdapter(getJsonData(), list_item)

        exit_btn.setOnClickListener {
            finish()
        }

        secret_btn.setOnClickListener {
            toastSecret(this)
            finish()
        }

        getJsonData()
        parseJsonData()
    }

    private fun getJsonData(): JSONArray //Laita tämä palauttamaan JSONArray ja kutsu tätä suoraan adapterin konstruktorissa
    {
        //---
        val jsonString = assets.open("data.txt").bufferedReader().use { it.readLine()}
        val jsonArray = JSONArray(jsonString)
        return jsonArray
        //--
    }

    private fun parseJsonData()
    {
        val messages = jsonArray.getJSONArray(0)
        for (i in 0 until messages.length()){
            val obj = messages.getJSONObject(i)
            messages.put("message")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        if (item?.itemId == R.id.menu_btn_secret)
        {
            toastAllSecrets(this)
            return true
        }
        else if (item?.itemId == R.id.menu_btn_ping)
        {
            ping(this)
            return true
        }
        return false
    }



}
