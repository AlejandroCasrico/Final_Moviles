package fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.pmovil2.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [baseline.newInstance] factory method to
 * create an instance of this fragment.
 */
class BaselineFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_baseline,container,false)
        val Shpreferences = view.findViewById<Button>(R.id.preferences)
        Shpreferences.setOnClickListener{
            Log.d("element","valor")
            val sharedPreference = this.requireActivity()
                .getSharedPreferences("pref", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.putString("username","Jesus")
            editor.putBoolean("alreadyVisitedOnboarding",true)
            editor.commit()
        }
        // Inflate the layout for this fragment
        return view
    }


}