package seifemadhamdy.postarazzi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import seifemadhamdy.postarazzi.data.Post
import seifemadhamdy.postarazzi.databinding.ItemPostBinding
import seifemadhamdy.postarazzi.navigator.Navigator
import javax.inject.Inject

class PostAdapter @Inject constructor(private val navigator: Navigator) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    private var postArrayList = ArrayList<Post>()

    fun setPostArrayList(newPostArrayList: List<Post>) {
        val diffCallback = PostDiffCallback(postArrayList, newPostArrayList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        postArrayList.clear()
        postArrayList.addAll(newPostArrayList)

        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = postArrayList[position]

        holder.binding.userIdValueTextView.text = post.userId.toString()
        holder.binding.idValueTextView.text = post.id.toString()
        holder.binding.titleTextView.text = post.title
        holder.binding.bodyTextView.text = post.body

        holder.itemView.setOnClickListener {
            postArrayList[position].id?.let { postId ->
                navigator.navigateToDetailScreen(
                    holder.itemView.context, postId
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return postArrayList.size
    }

    private class PostDiffCallback(
        private val oldList: List<Post>, private val newList: List<Post>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}