
package com.example.mechinetest
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ChatListAdapter(private val commentList: ArrayList<Comment>) :
    RecyclerView.Adapter<ChatListAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment: Comment = commentList[position]
        holder.onBind(comment)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.comment_list_adapter, parent, false)
        )
    }

    class ViewHolder(view: View) :  RecyclerView.ViewHolder(view) {
        private val tvComment: TextView = view.findViewById(R.id.tv_comment)
        val ivImage: ImageView = view.findViewById(R.id.user_image)
        fun onBind(comment: Comment) {
            tvComment.text = comment.message

            Glide.with(itemView.context)
                .load(R.drawable.person_profile)
                .apply(RequestOptions.circleCropTransform())
                .into(ivImage)
        }
    }
}
