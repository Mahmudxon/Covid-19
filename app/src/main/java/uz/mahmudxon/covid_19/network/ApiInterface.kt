package uz.mahmudxon.covid_19.network

import io.reactivex.Observable
import retrofit2.http.GET
import uz.mahmudxon.covid_19.model.ReceiverData

interface ApiInterface {
    @GET("summary")
    fun getData() : Observable<ReceiverData>
}