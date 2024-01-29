package seifemadhamdy.postarazzi.ui.post

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import seifemadhamdy.postarazzi.data.Post
import seifemadhamdy.postarazzi.instance.RetrofitInstance

class PostViewModel : ViewModel() {
    private var postLiveData = MutableLiveData<List<Post>>()

    fun getPosts() {
        RetrofitInstance.postApi.getPosts()
            .enqueue(object : Callback<List<Post>> {
                override fun onResponse(
                    call: Call<List<Post>>,
                    response: Response<List<Post>>
                ) {
                    if (response.body() != null) {
                        postLiveData.value = response.body()
                    } else {
                        return
                    }
                }

                override fun onFailure(
                    call: Call<List<Post>>,
                    t: Throwable
                ) {
                    Log.d("TAG", t.message.toString())
                }
            })
    }

    fun observePostLiveData(): LiveData<List<Post>> {
        return postLiveData
    }
}