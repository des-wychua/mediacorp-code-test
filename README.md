# mediacorp-code-test

Please build a simple android or iOS mobile app with these features:

1) App load straight into a listing page (“homepage”), where the number of items (“articles”) can be infinite, but no less than 20 items.
2) After every 3 items on listing page, display 1 ad, with sequential ID. E.g.
   - After first 3 items, call for /4654/ad test/imu1/homepage
   - After 6 items, call for ad /4654/test/imu2/homepage
3) Each item should have a sequential ID shown as headline, e.g., article 1, article 2...
4) When clicked on an item, user is presented with an article page, like how a normal article on a news page.
5) After every 2 paragraphs, display 1 ad, with sequential ID. 
   - /4654/ad test/imu1/article
   - /4654/ad test/imu2/article
6) Homepage ads should only call for 300x250 size
7) Article page ads should call for ad sizes in alternative pattern.
   - Odd numbers call for 300x250 size only
   - Even numbers call for both 300x250 and 300x600 sizes. Expect ad server to provide ad for either of these sizes and display it properly.
8) Article page ads should have custom targeting, where key is articleID and value is the ID of the article.
9) All ads should be surrounded by white margin, black border, grey padding.
10) If no ad is served, do not leave gaps nor show any of the margin, border nor padding.

Here are some information and reference links that will be relevant. Please make assumptions if there’s missing information.
- https://developers.google.com/ad-manager/mobile-ads-sdk/android/quick-start
- https://developers.google.com/ad-manager/mobile-ads-sdk/ios/quick-start

• Ad unit ID starts with /4654/test
