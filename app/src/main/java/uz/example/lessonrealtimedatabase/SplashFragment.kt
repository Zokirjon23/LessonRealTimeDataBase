package uz.example.lessonrealtimedatabase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

class SplashFragment : Fragment(R.layout.fragment_splash) {


    private val cache = Cache.cache
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (cache?.getId() == 0L){
            findNavController().navigate(R.id.action_splashFragment_to_FirstFragment)
        }else{
            findNavController().navigate(R.id.action_splashFragment_to_SecondFragment)
        }
    }
}