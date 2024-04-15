package com.example.mechinetest

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private var chatRecyclerView: RecyclerView? = null
    private var commentList = ArrayList<Comment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ivProfileIcon = findViewById<ImageView>(R.id.iv_profile)
        val chatPersonTwo = findViewById<ImageView>(R.id.iv_chat_two)
        val chatPersonThree = findViewById<ImageView>(R.id.iv_chat_three)
        val chatPersonFour = findViewById<ImageView>(R.id.iv_chat_four)
        val chatPersonFive = findViewById<ImageView>(R.id.iv_chat_five)
        val fab = findViewById<FloatingActionButton>(R.id.add_person_fab)
        val ivPlay = findViewById<ImageView>(R.id.iv_play)
        chatRecyclerView = findViewById(R.id.rv_chatList)
        val resourceId = R.drawable.person_profile

        Glide.with(this)
            .load(resourceId)
            .apply(RequestOptions.circleCropTransform())
            .into(ivProfileIcon)

        val bitmapTwo = (ResourcesCompat.getDrawable(resources,R.drawable.person_eight, null) as BitmapDrawable).bitmap
        val bitmapThree = (ResourcesCompat.getDrawable(resources,R.drawable.person_six, null) as BitmapDrawable).bitmap
        val bitmapFour = (ResourcesCompat.getDrawable(resources,R.drawable.person_one, null) as BitmapDrawable).bitmap
        val bitmapFive = (ResourcesCompat.getDrawable(resources,R.drawable.person_one, null) as BitmapDrawable).bitmap

        chatPersonTwo.setImageBitmap(roundImage(bitmapTwo))
        chatPersonThree.setImageBitmap(roundImage(bitmapThree))
        chatPersonFour.setImageBitmap(roundImage(bitmapFour))
        chatPersonFive.setImageBitmap(roundImage(bitmapFive))

        fab.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorPrimary))
        fab.setOnClickListener {
            ivPlay.visibility = View.VISIBLE
            Glide.with(this)
                .load(R.drawable.play) // Replace `your_gif_image` with the name of your GIF image file
                .into(ivPlay)
        }

        val comment = Comment()
        comment.apply {
            this.message = "Thomas Sent a gift to Tom"
        }
        for(i in 1..10) {
            commentList.add(comment)
        }
        initCommentRecycleView()
    }

    private fun initCommentRecycleView() {
        chatRecyclerView?.layoutManager = LinearLayoutManager(this)
        val chatAdapter = ChatListAdapter(commentList)
        chatRecyclerView?.adapter = chatAdapter
    }

    private fun roundImage(bitmap: Bitmap): Bitmap {
        val bitmapRounded = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config)
        val canvas = Canvas(bitmapRounded)
        val paint = Paint().apply {
            isAntiAlias = true
            shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        }
        canvas.drawRoundRect(RectF(0.0f, 0.0f, bitmap.width.toFloat(), bitmap.height.toFloat()), 30f, 30f, paint)
        return bitmapRounded
    }
}
