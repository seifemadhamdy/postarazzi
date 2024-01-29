package seifemadhamdy.postarazzi.ui.post

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import seifemadhamdy.postarazzi.adapter.PostAdapter
import seifemadhamdy.postarazzi.databinding.ActivityPostBinding
import seifemadhamdy.postarazzi.module.NavigationModule

@AndroidEntryPoint
class PostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostBinding
    private lateinit var postViewModel: PostViewModel
    private lateinit var postAdapter: PostAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareRecyclerView()
        postViewModel = ViewModelProvider(this)[PostViewModel::class.java]
        postViewModel.getPosts()
        postViewModel.observePostLiveData().observe(this) { postArrayList ->
            postAdapter.setPostArrayList(postArrayList)
        }
    }

    private fun prepareRecyclerView() {
        postAdapter = PostAdapter(NavigationModule.provideNavigator())
        binding.postsRecyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = postAdapter
        }
    }
}