package uz.example.lessonrealtimedatabase

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.example.lessonrealtimedatabase.databinding.ItemReceiverBinding
import uz.example.lessonrealtimedatabase.databinding.ItemSenderBinding
import kotlin.math.log

class ChatAdapter(private val myId: Long) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list = ArrayList<Massage>()
    val sender = 1

    inner class ReceiverHolder(private val binding: ItemReceiverBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(m: Massage) {
            Log.d("BBBB", "bind: R")
            binding.massageText.text = m.massage
        }
    }

    inner class SenderHolder(private val binding: ItemSenderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(m: Massage) {
            Log.d("BBBB", "bind: S")
            binding.massageText.text = m.massage
        }
    }

    override fun getItemViewType(position: Int): Int {
        Log.d("BBBB", "getItemViewType: ")
        return if (list[position].ownerId == myId) {
            sender
        } else
            0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("BBBB", "onCreateViewHolder: ")
        return if (viewType == sender) SenderHolder(
            ItemSenderBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_sender, parent, false
                )
            )
        ) else {
            ReceiverHolder(
                ItemReceiverBinding.bind(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_receiver, parent, false)
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == sender) {
            (holder as SenderHolder).bind(list[position])
        } else {
            (holder as ReceiverHolder).bind(list[position])
        }
    }


    override fun getItemCount() = list.size

    fun insertMassage(m: Massage) {
        Log.d("BBBB", "insertMassage: ")
        list.add(m)
        notifyItemInserted(list.size - 1)
    }
}