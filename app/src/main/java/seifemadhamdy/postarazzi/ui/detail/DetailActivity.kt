package seifemadhamdy.postarazzi.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import seifemadhamdy.postarazzi.R
import seifemadhamdy.postarazzi.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        detailViewModel.getPostData(intent.getIntExtra("POST_ID", 1))

        detailViewModel.observePostLiveData().observe(this) { postData ->
            postData.apply {
                binding.postDataTextView.text =
                    getString(
                        R.string.user_id_id_title_body,
                        userId.toString(),
                        id.toString(),
                        title,
                        body
                    )
            }
        }
    }
}