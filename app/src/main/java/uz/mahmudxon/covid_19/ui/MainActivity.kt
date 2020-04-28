package uz.mahmudxon.covid_19.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import uz.mahmudxon.covid_19.R

class MainActivity : AppCompatActivity() {

    lateinit var navController : NavController
    lateinit var navGraph: NavGraph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navGraph = navController.graph
        navGraph.startDestination = R.id.splashFragment
        navController.graph = navGraph
    }
}
