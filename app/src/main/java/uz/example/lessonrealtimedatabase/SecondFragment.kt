package uz.example.lessonrealtimedatabase

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import uz.example.lessonrealtimedatabase.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private val cache = Cache.cache
    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    private val db = FirebaseDatabase.getInstance().reference
    private val adapter = ChatAdapter(cache!!.getId())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.chatList.adapter = adapter
        binding.send.setOnClickListener {
            val text = binding.editText.text.toString()
            val id = SystemClock.elapsedRealtime()
            val massage = Massage(id, cache!!.getId(), text)
            db.child("messages").child(id.toString()).setValue(massage)
                .addOnSuccessListener {
                    Log.d("VVVV", "onViewCreated: Send")
                }.addOnFailureListener {
                    Log.d("VVVV", "onViewCreated: Error")
                }
            binding.editText.setText("")
        }

        db.child("messages").addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val data = snapshot.getValue<Massage>()!!
                Log.d("VVVV", "onChildAdded: ${data.massage}")
                adapter.insertMassage(snapshot.getValue<Massage>()!!)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}