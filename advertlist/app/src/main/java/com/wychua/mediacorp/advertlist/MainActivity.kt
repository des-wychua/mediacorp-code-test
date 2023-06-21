package com.wychua.mediacorp.advertlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdSize

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayout: LinearLayout = findViewById(R.id.linear_layout)

        for (i in 1..20) {
            // Inflate item view layout
            val itemView = LayoutInflater.from(this).inflate(R.layout.item_layout, null) as LinearLayout

            // Set sequential ID as headline
            val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
            tvTitle.text = "Article $i"

            // Load article page on item click
            itemView.setOnClickListener {
                navigateToDetailActivity(i)
            }

            linearLayout.addView(itemView)

            if (i % 3 == 0) {
                val adView = AdManagerHelper.createAdView(this, AdSize(300,250))
                linearLayout.addView(adView)
            }
        }
    }

    private fun navigateToDetailActivity(text: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_TEXT, text)
        startActivity(intent)
    }
}
