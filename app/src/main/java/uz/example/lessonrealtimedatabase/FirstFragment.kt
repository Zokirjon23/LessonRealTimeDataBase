package uz.example.lessonrealtimedatabase

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import uz.example.lessonrealtimedatabase.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private val cache = Cache.cache
    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val db = FirebaseDatabase.getInstance().reference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val user : User? = null
        binding.buttonFirst.setOnClickListener {
           val user = User(
                SystemClock.elapsedRealtime(), binding.editText.text.toString()
            )

            cache?.setId(user.id)

            db.child("users").child(SystemClock.elapsedRealtime().toString()).setValue(user
            ).addOnSuccessListener {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }.addOnFailureListener {
                Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}