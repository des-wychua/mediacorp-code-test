# mediacorp-code-test

Please build a simple android or iOS mobile app with these features:
• App load straight into a listing page (“homepage”), where the number of items
(“articles”) can be infinite, but no less than 20 items.
• After every 3 items on listing page, display 1 ad, with sequential ID. E.g.
  o After first 3 items, call for /4654/ad test/imu1/homepage
  o After 6 items, call for ad /4654/test/imu2/homepage
• Each item should have a sequential ID shown as headline, e.g., article 1, article 2...
• When clicked on an item, user is presented with an article page, like how a normal article on a news page.
• After every 2 paragraphs, display 1 ad, with sequential ID. 
  o /4654/ad test/imu1/article
  o /4654/ad test/imu2/article
• Homepage ads should only call for 300x250 size
• Article page ads should call for ad sizes in alternative pattern.
  o Odd numbers call for 300x250 size only
  o Even numbers call for both 300x250 and 300x600 sizes. Expect ad server to
provide ad for either of these sizes and display it properly.
• Article page ads should have custom targeting, where key is articleID and value is the ID of the article.
• All ads should be surrounded by white margin, black border, grey padding.
• If no ad is served, do not leave gaps nor show any of the margin, border nor padding.

Here are some information and reference links that will be relevant. Please make assumptions if there’s missing information.
• https://developers.google.com/ad-manager/mobile-ads-sdk/android/quick-start
• https://developers.google.com/ad-manager/mobile-ads-sdk/ios/quick-start

• Ad unit ID starts with /4654/test
