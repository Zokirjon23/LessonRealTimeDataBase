package uz.example.lessonrealtimedatabase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.example.lessonrealtimedatabase.databinding.ItemReceiverBinding
import uz.example.lessonrealtimedatabase.databinding.ItemSenderBinding

class ChatAdapter(private val myId: Long) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list = ArrayList<Massage>()

    inner class ReceiverHolder(private val binding: ItemReceiverBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(m: Massage) {
            binding.massageText.text = m.massage
        }
    }

    inner class SenderHolder(private val binding: ItemSenderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(m: Massage) {
            binding.massageText.text = m.massage
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position].ownerId == myId) {
            R.layout.item_sender
        } else
            R.layout.item_receiver
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == R.layout.item_sender) SenderHolder(
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
        if (holder.itemViewType == R.layout.item_sender) {
            (holder as SenderHolder).bind(list[position])
        } else {
            (holder as ReceiverHolder).bind(list[position])
        }
    }


    override fun getItemCount() = list.size

    fun insertMassage(m: Massage) {
        list.add(m)
        notifyItemInserted(list.size - 1)
    }

    fun getScrollPosition() : Int{
       return list.size-1
    }
}