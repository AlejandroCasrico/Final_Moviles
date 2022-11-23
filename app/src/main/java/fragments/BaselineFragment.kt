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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

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
        val view: View= inflater.inflate(R.layout.fragment_baseline, container, false)
        return view
        // Inflate the layout for this fragment

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        (childFragmentManager.findFragmentById(
            R.id.maps
        ) as? SupportMapFragment)?.getMapAsync { googleMap ->


            val mMap = googleMap
            val zoomLevel = 4.0f
            val direccion = LatLng(28.622839645061102, -106.11506317116468)
            print(mMap.isMyLocationEnabled)
            for(i in 0..2){
                mMap.addMarker(
                    MarkerOptions()
                        .position(LatLng(28.622839645061102 +i, -106.11506317116468 +i))
                        .title("Marker")
                )
            }
            mMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(direccion,zoomLevel)
            )
        }
    }




}