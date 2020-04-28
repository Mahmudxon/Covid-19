package uz.mahmudxon.covid_19.ui.splash

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import uz.mahmudxon.covid_19.R

class SplashFragment : Fragment(R.layout.fragment_splash) {

    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        object : CountDownTimer(2000, 2000) {
            override fun onFinish() {
                navController.navigate(R.id.action_splashFragment_to_mainFragment)
            }

            override fun onTick(millisUntilFinished: Long) {
                TODO("Not yet implemented")
            }
        }.start()
    }

}
