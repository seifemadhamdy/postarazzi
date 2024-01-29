package seifemadhamdy.postarazzi.navigator

import android.content.Context
import android.content.Intent
import seifemadhamdy.postarazzi.ui.detail.DetailActivity

class Navigator {

    fun navigateToDetailScreen(context: Context, postId: Int) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("POST_ID", postId)
        context.startActivity(intent)
    }
}