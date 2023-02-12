package com.example.chattingapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.auth.FirebaseAuth

class MessageAdaptor(val context: Context,val messagelist: ArrayList<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECIEVE=1;
    val ITEM_SENT=2;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType==1){
            val veiw: View= LayoutInflater.from(context).inflate(R.layout.recieve,parent,false)
            return RecivedViewHolder(veiw)
        }
        else{
            //inflate sent
            val veiw: View= LayoutInflater.from(context).inflate(R.layout.sent,parent,false)
            return SentViewHolder(veiw)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curentmessage = messagelist[position]
        if(holder.javaClass==SentViewHolder::class.java){

            val viewHolder=holder  as SentViewHolder
            holder.SentMessage.text=curentmessage.message
            //do for sent view holder
        }
        else{
            //stuff for recived holder
            val viewHolder = holder as RecivedViewHolder
            holder.RecieveMessage.text=curentmessage.message

        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentmessage = messagelist[position]
        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentmessage.senderId)){
            return ITEM_SENT
        }
        else{
            return ITEM_RECIEVE
        }
    }

    override fun getItemCount(): Int {
        return messagelist.size
    }
    class SentViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val SentMessage = itemView.findViewById<TextView>(R.id.txt_sentmsg)



    }
    class RecivedViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val RecieveMessage = itemView.findViewById<TextView>(R.id.txt_recievemsg)

    }

}