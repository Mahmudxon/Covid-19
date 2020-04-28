package uz.mahmudxon.covid_19.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*
import retrofit2.create
import uz.mahmudxon.covid_19.R
import uz.mahmudxon.covid_19.adapter.CountryAdapter
import uz.mahmudxon.covid_19.dialog.CountryDialog
import uz.mahmudxon.covid_19.model.Country
import uz.mahmudxon.covid_19.model.Global
import uz.mahmudxon.covid_19.network.ApiInterface
import uz.mahmudxon.covid_19.network.CovidClient
import java.util.*

class MainFragment : Fragment(R.layout.fragment_main) {

    lateinit var navController: NavController
    lateinit var adapter: CountryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CountryAdapter()
        list?.layoutManager = LinearLayoutManager(requireContext())
        list?.adapter = adapter



        navController = Navigation.findNavController(view)
        showMap?.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_webFragment)
        }
        adapter.listener = {
            CountryDialog(requireContext(), it).show()
        }

        request()
        wall?.setOnRefreshListener {
            request()
            wall?.isRefreshing = false
        }
    }

    private fun request() {
        wall?.visibility = View.VISIBLE
        loading?.visibility = View.VISIBLE

        val api = CovidClient.instanse.create<ApiInterface>()
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            api.getData()
                .subscribeOn(Schedulers.io()) //background
                .observeOn(AndroidSchedulers.mainThread()) // return main tread
                .subscribe({
                    setAdapterData(it.Countries)
                    setGlobalData(it.Global)
                }, {

                    loading.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                })
        )
    }

    private fun setAdapterData(countries: ArrayList<Country>) {
        var data = countries
        Collections.sort(data, MyComparator())
        adapter.swapdata(data)
    }

    private fun setGlobalData(global: Global) {
        confirmed?.text = global.TotalConfirmed.toString()
        recover?.text = global.TotalRecovered.toString()
        deaths?.text = global.TotalDeaths.toString()
        new_confirmed?.text = "+" + global.NewConfirmed.toString()
        new_recover?.text = "+" + global.NewRecovered.toString()
        new_deaths?.text = "+" + global.NewDeaths.toString()
        loading.visibility = View.GONE
        wall?.visibility = View.GONE
        showMap?.show()
    }

    class MyComparator : Comparator<Country> {

        override fun compare(o1: Country, o2: Country): Int {
            return o2.TotalConfirmed.compareTo(o1.TotalConfirmed)
        }


    }
}
