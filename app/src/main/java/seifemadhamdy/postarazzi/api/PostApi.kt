package seifemadhamdy.postarazzi.api

import retrofit2.Call
import retrofit2.http.GET
import seifemadhamdy.postarazzi.data.Post

interface PostApi {
    @GET("posts")
    fun getPosts(): Call<List<Post>>
}