package com.wychua.mediacorp.advertlist

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.admanager.AdManagerAdRequest

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TEXT = "extra_text"
        const val TAG = "DetailActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val linearLayout: LinearLayout = findViewById(R.id.linear_layout_detail)

        val index = intent.getIntExtra(EXTRA_TEXT, -1)
        addTitleItem(linearLayout, "Article $index")

        for (i in 1..8) {
            val textView = TextView(this)
            textView.text =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean luctus venenatis pellentesque. Cras metus orci, hendrerit nec commodo id, sodales id tellus. Fusce et orci nec eros pellentesque elementum. Cras quis odio nec purus consequat gravida.\n"
            linearLayout.addView(textView)

            if (i % 2 == 0) {
                val adView = createAdView()
                linearLayout.addView(adView)
            }
        }
    }

    private fun addTitleItem(linearLayout: LinearLayout, title: String) {
        val titleTextView = TextView(this)
        titleTextView.text = title
        titleTextView.textSize = 24f
        titleTextView.setPadding(16, 16, 16, 16)
        linearLayout.addView(titleTextView)
    }

    private fun createAdView(): LinearLayout {
        val index = intent.getIntExtra(EXTRA_TEXT, -1)

        val adContainer = LinearLayout(this)
        val adContainerParent = LinearLayout(this)

        val adView = AdView(this)
        adView.setAdSize(
            if (index % 2 == 0) AdSize.getInlineAdaptiveBannerAdSize(
                300,
                600
            ) else AdSize(300, 250)
        )
        adView.adUnitId = "/4654/test/imu${adCount}/article"



        adView.adListener = object : AdListener() {
            override fun onAdClicked() {
                Log.d(TAG, "AdUnitId clicked: ${adView.adUnitId}")
            }

            override fun onAdLoaded() {
                // Add black paddings to the ad view
                adView.setBackgroundColor(Color.GRAY)
                adView.setPadding(16, 16, 16, 16)

                // Wrap the ad view with a LinearLayout to apply the paddings
                adContainer.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                adContainer.orientation = LinearLayout.VERTICAL
                adContainer.setBackgroundColor(Color.BLACK)
                adContainer.setPadding(16, 16, 16, 16)
                adContainer.addView(adView)

                // Wrap the ad view with a LinearLayout to apply the paddings
                adContainerParent.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                adContainerParent.orientation = LinearLayout.VERTICAL
                adContainerParent.setBackgroundColor(Color.WHITE)
                adContainerParent.setPadding(16, 16, 16, 32)
                adContainerParent.addView(adContainer)
            }

            override fun onAdFailedToLoad(p0: LoadAdError) {
                Log.d(TAG, "LoadAdError: ${p0.responseInfo.toString()}")
            }
        }

        val adRequest =
            AdManagerAdRequest.Builder().addCustomTargeting("articleID", "imu${adCount++}").build()
        adView.loadAd(adRequest)

        return adContainerParent
    }

    private var adCount = 1
}
