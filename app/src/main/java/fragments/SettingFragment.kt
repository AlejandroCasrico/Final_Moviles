package fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pmovil2.LoginFinal
import com.example.pmovil2.Moreinfo
import com.example.pmovil2.R
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [setting.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingFragment : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View= inflater.inflate(R.layout.fragment_settin, container, false)
        val exitSession = view.findViewById<Button>(R.id.logout)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val more = view.findViewById<Button>(R.id.more)
        Picasso.get()
            .load("https://i.pinimg.com/564x/15/9c/85/159c85252e164184160e0a404a73d0ca.jpg")
            .into(imageView);

        more.setOnClickListener{
            activity?.let{
                val intent = Intent (it, Moreinfo::class.java)
                it.startActivity(intent)

            }
        }
        exitSession.setOnClickListener {
            activity?.let{
                val intent = Intent (it, LoginFinal::class.java)
                it.startActivity(intent)
                Toast.makeText(getActivity(),"Saliendo de la sesion!",Toast.LENGTH_SHORT).show();
            }
        }
        return view
    }


}