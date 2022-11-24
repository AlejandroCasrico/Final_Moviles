package com.example.pmovil2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import androidx.fragment.app.FragmentManager
import com.ramotion.paperonboarding.PaperOnboardingFragment
import com.ramotion.paperonboarding.PaperOnboardingPage
class Onboarding : AppCompatActivity() {
    private var fragmentManager: FragmentManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentManager = supportFragmentManager

        val paperOnboardingFragment = PaperOnboardingFragment.newInstance(
            dataforOnboarding
        )
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.add(R.id.frame_layout, paperOnboardingFragment)

        fragmentTransaction.commit()
    }

    private val dataforOnboarding: ArrayList<PaperOnboardingPage>
        private get() {

            val source = PaperOnboardingPage(
                "Gfg",
                "Welcome to GeeksForGeeks",
                Color.parseColor("#ffb174"),
                R.drawable.gfgimg,
                R.drawable.search
            )
            val source1 = PaperOnboardingPage(
                "Practice",
                "Practice questions from all topics",
                Color.parseColor("#22eaaa"),
                R.drawable.practice_gfg,
                R.drawable.training
            )
            val source2 = PaperOnboardingPage(
                "",
                " ",
                Color.parseColor("#ee5a5a"),
                R.drawable.gfg_contribute,
                R.drawable.practice
            )

            val elements = ArrayList<PaperOnboardingPage>()


            elements.add(source)
            elements.add(source1)
            elements.add(source2)
            return elements
        }
}