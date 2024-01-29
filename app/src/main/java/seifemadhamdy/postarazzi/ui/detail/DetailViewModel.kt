package seifemadhamdy.postarazzi.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import seifemadhamdy.postarazzi.data.Post
import seifemadhamdy.postarazzi.instance.RetrofitInstance

class DetailViewModel : ViewModel() {
    private var postLiveData = MutableLiveData<Post>()

    fun getPostData(postId: Int) {
        RetrofitInstance.postApi.getPosts()
            .enqueue(object : Callback<List<Post>> {
                override fun onResponse(
                    call: Call<List<Post>>,
                    response: Response<List<Post>>
                ) {
                    postLiveData.value = response.body()?.get(postId - 1)
                }

                override fun onFailure(
                    call: Call<List<Post>>,
                    t: Throwable
                ) {
                    Log.d("TAG", t.message.toString())
                }
            })
    }

    fun observePostLiveData(): LiveData<Post> {
        return postLiveData
    }
}