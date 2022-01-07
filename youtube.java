public class youtube {
    String[] banana = new String[9];
    String[] cat = new String[9];
    try {
        for (int i = 0; i < 3; i++) {
            String json = Jsoup.connect("https://www.googleapis.com/youtube/v3/commentThreads?part=snippet&key=AIzaSyDUKYPZIaz4O3t5dyqClNuppGp3V9m-fPI&videoId=" + apple[i] + "&maxResults=3&order=relevance&textFormat=plainText ").ignoreContentType(true).execute().body();
//          System.out.println(json);

            JSONObject Object = new JSONObject(json);
            var items = Object.getJSONArray("items");

            for (int j = 0; j < 3; j++) {
                var array = items.getJSONObject(j);
                var snippet = array.getJSONObject("snippet");
                var topLevelComment = snippet.getJSONObject("topLevelComment");
                var snippet2 = topLevelComment.getJSONObject("snippet");
                var Comment = snippet2.getString("textDisplay");
                banana[j] = Comment;
                var commentTime = snippet2.getString("publishedAt");
                cat[j] = commentTime;
            }
        }
    } catch (Exception e) {
        System.out.println(e);
    }
}
