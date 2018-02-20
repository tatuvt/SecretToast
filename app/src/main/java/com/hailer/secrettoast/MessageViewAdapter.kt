package com.hailer.secrettoast

import android.content.ClipData
import android.content.Context
import android.os.Message
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.*
import android.widget.Toast
import org.json.JSONObject
import org.json.JSONArray


class MessageViewAdapter(private val data: JSONArray, private val layoutRes: Int) : RecyclerView.Adapter<MessageViewAdapter.ViewHolder>()
{


  inner class ViewHolder(itemView: View, val viewType: Int = 0) : RecyclerView.ViewHolder(itemView)
  {
    fun bind(message: JSONObject) = with(itemView) {
      when (viewType)
      {
        0 ->
        {
          bindDefaultView(message)
        }
      }
    }


    fun bindDefaultView(message: JSONObject) = with(itemView) {
      /*Populate the view*/
      /*message.optString("message","NO MESSAGE FOUND!")*/
      message.optString("message","no message found")

    }
  }

//    override fun getItemViewType(position: Int): Int {
//        return super.getItemViewType(position)
//    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data.getJSONObject(position))

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.list_item))

}

var selected = -1

fun toastSecret(context: Context) = Toast.makeText(context, data[selected].secret, Toast.LENGTH_SHORT).show()

fun toastAllSecrets(context: Context) = Toast.makeText(context, "All secrets", Toast.LENGTH_SHORT).show()

fun ping(context: Context) = Toast.makeText(context, "Pong", Toast.LENGTH_SHORT).show()

fun ViewGroup.inflate(layoutRes: Int): View = LayoutInflater.from(context).inflate(layoutRes, this, false)
