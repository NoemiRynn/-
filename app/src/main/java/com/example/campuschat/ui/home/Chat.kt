package com.example.campuschat.ui.home

import android.content.Context
import android.content.Intent
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.campuschat.R
import de.hdodenhof.circleimageview.CircleImageView
import org.w3c.dom.Text

data class Chat(
    val _id: String,
    val avatar: Int,
    val nickname: String,
    val id: String,
    val time: String,
    val content: String,
    val ats: List<String>,
    val tags: List<String>,
    val picture: Int,
    val nmsg: Int,
    val ntrans: Int,
    val nlike: Int,
)

class ChatAdapter(val context: Context, val chatList: List<Chat>):
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val chat_avatar = view.findViewById<CircleImageView>(R.id.circleImageViewChat)
        val chat_nickname = view.findViewById<TextView>(R.id.textViewNicknameChat)
        val chat_id = view.findViewById<TextView>(R.id.textViewIDChat)
        val chat_time = view.findViewById<TextView>(R.id.textViewTimeChat)
        val chat_button = view.findViewById<ImageView>(R.id.imageViewButtonChat)
        val chat_content = view.findViewById<TextView>(R.id.textViewTimeChat)
        val chat_ats = view.findViewById<TextView>(R.id.textViewAtChat)
        val chat_tags = view.findViewById<TextView>(R.id.textViewTagChat)
        val chat_picture = view.findViewById<ImageView>(R.id.imageViewChat)
        val chat_nmsg = view.findViewById<TextView>(R.id.textViewChatMessageNumber)
        val chat_ntrans = view.findViewById<TextView>(R.id.textViewChatTransNumber)
        val chat_nlike = view.findViewById<TextView>(R.id.textViewChatLikeNumber)
        val chat_msg = view.findViewById<ImageView>(R.id.imageViewChatMessage)
        val chat_trans = view.findViewById<ImageView>(R.id.imageViewChatTrans)
        val chat_like = view.findViewById<ImageView>(R.id.imageViewChatLike)
        val chat_share = view.findViewById<ImageView>(R.id.imageViewChatShare)
    }

    inner class position(val start: Int, val end: Int)

    inner class AtClickableSpan(context: Context): ClickableSpan(){
        override fun onClick(p0: View) {
            // 跳转到 @用户 的主页
            Toast.makeText(context, "Click @", Toast.LENGTH_SHORT).show()
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
        }
    }

    inner class TagClickableSpan(context: Context): ClickableSpan(){
        override fun onClick(p0: View) {
            // 跳转到 #Tag 的搜索页
            Toast.makeText(context, "Click #", Toast.LENGTH_SHORT).show()

        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.chat_item, parent, false)
        val holder = ViewHolder(view)

        // 点击非响应布局跳转至 Chat 详情页
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val chat = chatList[position]
            // val intent = Intent(context, ...).apply { putExtra( , ) }
            // context.startActivity(intent)
            Toast.makeText(context, "Click Chat", Toast.LENGTH_SHORT).show()
        }

        // 点击头像跳转至个人主页
        holder.chat_avatar.setOnClickListener {
            val position = holder.adapterPosition
            val chat = chatList[position]
            Toast.makeText(context, "Click Avatar", Toast.LENGTH_SHORT).show()

        }

        // 点击下拉按钮
        holder.chat_button.setOnClickListener {
            val position = holder.adapterPosition
            val chat = chatList[position]
            Toast.makeText(context, "Click Button", Toast.LENGTH_SHORT).show()

        }

        // 点击图片转到图片详情页面
        holder.chat_picture.setOnClickListener {
            val position = holder.adapterPosition
            val chat = chatList[position]
            Toast.makeText(context, "Click Picture", Toast.LENGTH_SHORT).show()

        }

        // 跳转到发送回复页面
        holder.chat_msg.setOnClickListener {
            val position = holder.adapterPosition
            val chat = chatList[position]
            Toast.makeText(context, "Click Message", Toast.LENGTH_SHORT).show()

        }

        // 弹出站内转发底部菜单
        holder.chat_trans.setOnClickListener {
            val position = holder.adapterPosition
            val chat = chatList[position]
            Toast.makeText(context, "Click Trans", Toast.LENGTH_SHORT).show()

        }

        // 点赞，图标变色
        holder.chat_like.setOnClickListener {
            val position = holder.adapterPosition
            val chat = chatList[position]
            Toast.makeText(context, "Click Like", Toast.LENGTH_SHORT).show()

        }

        // 分享到 QQ 或 微信
        holder.chat_share.setOnClickListener {
            val position = holder.adapterPosition
            val chat = chatList[position]
            Toast.makeText(context, "Click Share", Toast.LENGTH_SHORT).show()

        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chat = chatList[position]
        Glide.with(context).load(chat.avatar).into(holder.chat_avatar)
        holder.chat_nickname.text = chat.nickname
        holder.chat_id.text = "@" + chat.id
        holder.chat_time.text = chat.time

        // @ list
        var at_str = ""
        var at_idx = mutableListOf<position>()
        var idx: Int = 0
        for (at in chat.ats) {
            idx += 1
            at_idx.add(position(idx, idx+at.length))
            idx += (at.length + 1)
            at_str += ("@" + at + " ")
        }
        val at_span = SpannableString(at_str)
        for (p in at_idx) {
            at_span.setSpan(AtClickableSpan(context), p.start, p.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        holder.chat_ats.text = at_span

        // # list
        var tag_str = ""
        var tag_idx = mutableListOf<position>()
        var idx2: Int = 0
        for (tag in chat.tags) {
            idx2 += 1
            tag_idx.add(position(idx2, idx2+tag.length))
            idx2 += (tag.length + 1)
            tag_str += ("#" + tag + " ")
        }
        val tag_span = SpannableString(tag_str)
        for (p in tag_idx) {
            tag_span.setSpan(TagClickableSpan(context), p.start, p.end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        holder.chat_tags.text = tag_span

        Glide.with(context).load(chat.picture).into(holder.chat_picture)
        holder.chat_nmsg.text = chat.nmsg.toString()
        holder.chat_ntrans.text = chat.ntrans.toString()
        holder.chat_nlike.text = chat.nlike.toString()
        holder.chat_msg.setImageResource(R.drawable.ic_black_message)
        holder.chat_trans.setImageResource(R.drawable.ic_black_share)
        holder.chat_like.setImageResource(R.drawable.ic_black_like)
        holder.chat_share.setImageResource(R.drawable.ic_black_share_one)
        holder.chat_button.setImageResource(R.drawable.ic_black_down)

    }

    override fun getItemCount() = chatList.size
}