package uz.mahmudxon.covid_19.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import uz.mahmudxon.covid_19.util.Constants.BASE_URL
import java.util.concurrent.TimeUnit

object CovidClient {
    private var retrofit: Retrofit? = null

    val instanse: Retrofit
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }


    private fun getClient(token: String): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor { chain ->
                val originalRequest = chain.request()
                val builder = originalRequest.newBuilder()
//                builder.addHeader("Content-Type", "application/json")
//                builder.addHeader("Authorization", token)
                val newRequest = builder.build()
                chain.proceed(newRequest)
            }
            .connectTimeout(10000L, TimeUnit.MILLISECONDS)
            .readTimeout(10000L, TimeUnit.MILLISECONDS)
            .writeTimeout(10000L, TimeUnit.MILLISECONDS)
            .build()
    }

    fun inalidate() {
        retrofit = null
    }

}