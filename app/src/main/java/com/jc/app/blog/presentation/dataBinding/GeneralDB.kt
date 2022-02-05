package com.jc.app.blog.presentation.dataBinding

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jc.app.blog.domain.model.CommentModel
import com.jc.app.blog.domain.model.PostModel
import com.jc.app.blog.presentation.adapters.CommentListAdapter
import com.jc.app.blog.presentation.adapters.PostListAdapter

@BindingAdapter("app:submitPostList")
fun submitPostListToAdapterListAdapter(listView: RecyclerView, items: List<PostModel>?) {
    items?.let { (listView.adapter as PostListAdapter).submitList(items) }
}

@BindingAdapter("app:submitCommentList")
fun submitCommentListToAdapterListAdapter(listView: RecyclerView, items: List<CommentModel>?) {
    items?.let { (listView.adapter as CommentListAdapter).submitList(items) }
}

@BindingAdapter("app:gone_unless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setFirstText", "setSecondText")
fun setTextWithIntString(
    view: TextView,
    firstText: String?,
    secondText: String?
) {
    if (firstText != null && secondText != null){
        view.text = "$firstText $secondText"
    }
}