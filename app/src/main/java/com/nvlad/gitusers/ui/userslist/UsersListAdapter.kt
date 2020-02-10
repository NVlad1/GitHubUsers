package com.nvlad.gitusers.ui.userslist

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nvlad.gitusers.model.GithubUser
import com.nvlad.gitusers.R
import com.nvlad.gitusers.ui.user.UserActivity
import com.nvlad.gitusers.utils.Extras.EXTRA_LOGIN
import java.lang.ref.WeakReference

class UsersListAdapter(var users: List<GithubUser>, val _context: Context?, val prefetch: () -> (Unit)):
    RecyclerView.Adapter<UsersListAdapter.UserHolder>() {
    val itemsBeforePrefetch = 5

    override fun getItemCount(): Int = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_user, parent, false)
        return UserHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val context = _context ?: return
        if (users.size - position <= itemsBeforePrefetch) {prefetch()}
        holder.userLogin = users[position].login
        holder.text_name.text = users[position].login
        Glide.with(context)
            .load(users[position].avatarURL)
            .apply(RequestOptions.circleCropTransform().placeholder(R.drawable.ic_empty_photo))
            .into(holder.image_user)
    }

    inner class UserHolder(view: View) : RecyclerView.ViewHolder(view) {

        val text_name: TextView
        val image_user: ImageView
        var userLogin: String? = null

        init {
            text_name = view.findViewById(R.id.text_name)
            image_user = view.findViewById(R.id.image_user)
            itemView.setOnClickListener { onClick() }
        }

        private fun onClick(){
            val context = _context ?: return
            val login = userLogin ?: return
            val intent = Intent(context, UserActivity::class.java)
            intent.putExtra(EXTRA_LOGIN, login)
            val activity = context as? Activity
            if (activity != null && Build.VERSION.SDK_INT >= 21) {
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, image_user,
                    ViewCompat.getTransitionName(image_user)!!)
                context.startActivity(intent, options.toBundle())
            } else {
                context.startActivity(intent)
            }
        }
    }
}