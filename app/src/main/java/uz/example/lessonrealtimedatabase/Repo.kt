package uz.example.lessonrealtimedatabase

import android.util.Log
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue

class Repo {
    private val db = FirebaseDatabase.getInstance().reference

    private var onAdded: ((DataSnapshot) -> Unit)? = null

    fun onChanged(onAdded: (DataSnapshot) -> Unit,onChanged: (DataSnapshot) -> Unit) {
        db.child("users").addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("VVV", snapshot.getValue<User>()?.name.toString())
                onAdded(snapshot)
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

    fun onAddedListener(f: (DataSnapshot) -> Unit) {
        onAdded = f
    }
}