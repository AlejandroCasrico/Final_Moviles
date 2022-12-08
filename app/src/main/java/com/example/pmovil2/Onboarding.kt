package com.example.pmovil2

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.ramotion.paperonboarding.PaperOnboardingFragment
import com.ramotion.paperonboarding.PaperOnboardingPage


class Onboarding : AppCompatActivity() {
    private var fragmentManager: FragmentManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        fragmentManager = supportFragmentManager

        // new instance is created and data is took from an
        // array list known as getDataonborading
        val paperOnboardingFragment = PaperOnboardingFragment.newInstance(
            dataforOnboarding
        )
        val fragmentTransaction = fragmentManager!!.beginTransaction()

        // fragmentTransaction method is used
        // do all the transactions or changes
        // between different fragments
        fragmentTransaction.add(R.id.frame_layout, paperOnboardingFragment)

        // all the changes are committed
        fragmentTransaction.commit()
    }

    // the first string is to show the main title ,
    // second is to show the message below the
    // title, then color of background is passed ,
    // then the image to show on the screen is passed
    // and at last icon to navigate from one screen to other
    private val dataforOnboarding: ArrayList<PaperOnboardingPage>
        private get() {

            // the first string is to show the main title ,
            // second is to show the message below the
            // title, then color of background is passed ,
            // then the image to show on the screen is passed
            // and at last icon to navigate from one screen to other
            val source = PaperOnboardingPage(
                "Bienvenido a nuestra nueva aplicación",
                "Recorrido rapiido",
                Color.parseColor("#ffb174"),
                R.drawable.gfgimg,
                R.drawable.search
            )
            val source1 = PaperOnboardingPage(
                "¿Cómo se usa?",
                "Empecemos a trabajar dentro de la app",
                Color.parseColor("#22eaaa"),
                R.drawable.practice_gfg,
                R.drawable.practice
            )
            val source2 = PaperOnboardingPage(
                "Pongamoslo a prueba!",
                "Es tu hora de emprender tu nuevo viaje",
                Color.parseColor("#ee5a5a"),
                R.drawable.gfg_contribute,
                R.drawable.training
            )

            // array list is used to store
            // data of onbaording screen
            val elements = ArrayList<PaperOnboardingPage>()

            // all the sources(data to show on screens)
            // are added to array list
            elements.add(source)
            elements.add(source1)
            elements.add(source2)
            return elements
        }
}