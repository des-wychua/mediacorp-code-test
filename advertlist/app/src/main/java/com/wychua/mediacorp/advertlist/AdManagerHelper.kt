package com.wychua.mediacorp.advertlist

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

object AdManagerHelper {

    private const val TAG = "AdManagerHelper"
    private var adCount = 1


    fun createAdView(context: Context, adSize: AdSize): View {
        val adContainer = LinearLayout(context)
        val adContainerParent = LinearLayout(context)

        val adView = AdView(context)
        adView.setAdSize(adSize)
        adView.adUnitId = "/4654/test/imu${adCount++}/homepage"

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

        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        return adContainerParent
    }

}
