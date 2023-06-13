package com.wychua.mediacorp.advertlist

import android.view.View
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {

    private lateinit var adManager: AdManager
    private lateinit var listingScrollView: ScrollView
    private lateinit var articleScrollView: ScrollView
    private lateinit var articleContainer: LinearLayout
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize AdManager
        MobileAds.initialize(this)

        // Create AdManager instance
        adManager = AdManager()

        // Initialize views
        listingScrollView = findViewById(R.id.listing_scrollview)
        articleScrollView = findViewById(R.id.article_scrollview)
        articleContainer = findViewById(R.id.article_container)
        backButton = findViewById(R.id.back_button)

        // Load listing page
        loadListingPage()
    }

    override fun onBackPressed() {
        articleScrollView.visibility = View.GONE
        backButton.visibility = View.GONE
        listingScrollView.visibility = View.VISIBLE

    }

    private fun loadListingPage() {
        val listingContainer: LinearLayout = findViewById(R.id.listing_container)
        val numberOfItems = 20 // Minimum number of items

        for (i in 1..numberOfItems) {
            // Display sequential ID as headline
            val headline = "Article $i"

            // Create item view and add it to the listing container
            val itemView = createItemView(headline, i)
            listingContainer.addView(itemView)

            // Display ad after every 3 items
            if (i % 3 == 0) {
                val adId = "imu${i / 3}"
                val adView = adManager.createAdView(this, AdSize.MEDIUM_RECTANGLE, adId, "homepage")
                listingContainer.addView(adView)
            }
        }
    }

    private fun createItemView(headline: String, id: Int): View {
        // Inflate item view layout
        val itemView = LayoutInflater.from(this).inflate(R.layout.item_view, null) as LinearLayout

        // Set sequential ID as headline
        val headlineTextView = itemView.findViewById<TextView>(R.id.headline_textview)
        headlineTextView.text = headline

        // Load article page on item click
        itemView.setOnClickListener {
            loadArticlePage(headline, id)
        }

        return itemView
    }

    private fun loadArticlePage(articleId: String, id: Int) {
        // Hide listing container, show article container and back button
        listingScrollView.visibility = View.GONE
        articleScrollView.visibility = View.VISIBLE
        backButton.visibility = View.VISIBLE

        articleContainer.removeAllViews()

        val paragraphs = 8 // Number of paragraphs in the article

        for (i in 1..paragraphs) {
            // Inflate paragraph view layout
            val paragraphView =
                LayoutInflater.from(this).inflate(R.layout.paragraph_view, null) as LinearLayout

            // Set paragraph text
            val paragraphTextView = paragraphView.findViewById<TextView>(R.id.paragraph_textview)
            paragraphTextView.text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean luctus venenatis pellentesque. Cras metus orci, hendrerit nec commodo id, sodales id tellus. Fusce et orci nec eros pellentesque elementum. Cras quis odio nec purus consequat gravida.\n"

            // Add paragraph view to the article container
            articleContainer.addView(paragraphView)

            // Display ad after every 2 paragraphs
            if (i % 2 == 0) {
                val adId = "imu${i / 2}"
                val adSize = if (id % 2 != 0) AdSize.MEDIUM_RECTANGLE else AdSize.LARGE_BANNER
                val adView =
                    adManager.createAdView(this, adSize, adId, "article", articleId)
                articleContainer.addView(adView)
            }
        }
    }

    fun onBackButtonClick(view: View) {

        articleScrollView.visibility = View.GONE
        backButton.visibility = View.GONE
        listingScrollView.visibility = View.VISIBLE
    }
}

class AdManager {

    fun createAdView(context: Context, adSize: AdSize, adId: String, adType: String, articleId: String? = null): AdView {
        val adView = AdView(context)
        adView.setAdSize(adSize)
        adView.adUnitId = "/4654/test/$adId/$adType"

        val adRequestBuilder = AdRequest.Builder()

        // Set custom targeting for article page ads
//        if (adType == "article" && articleId != null) {
//            adRequestBuilder.addCustomTargeting("articleID", articleId)
//        }

        val adRequest = adRequestBuilder.build()
        adView.loadAd(adRequest)

        return adView
    }
}
