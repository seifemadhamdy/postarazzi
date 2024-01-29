package seifemadhamdy.postarazzi.instance

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import seifemadhamdy.postarazzi.api.PostApi

object RetrofitInstance {
    val postApi: PostApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostApi::class.java)
    }
}