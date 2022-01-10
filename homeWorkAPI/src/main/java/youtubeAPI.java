import org.jsoup.Jsoup;
import org.json.*;

import java.net.MalformedURLException;
import java.net.URL;

public class youtubeAPI {
    public static void main(String[] args) throws MalformedURLException {
        var k = 0;
        String[] VideoID = new String[3];
        String[] Comment = new String[9];
        String[] CommentTime = new String[9];
        String[] PublishTime = new String[3];
        String[] Title = new String[3];
        try {
            String youtubeSearch = "阿神";
            String json = Jsoup.connect("https://www.googleapis.com/youtube/v3/search?part=snippet&q=" + youtubeSearch + "&key=AIzaSyDUKYPZIaz4O3t5dyqClNuppGp3V9m-fPI&type=video&maxResults=3").ignoreContentType(true).execute().body();
            //format json by https://jsoneditoronline.org/
//            System.out.println(json);

            JSONObject resObject = new JSONObject(json);
            var Object = resObject.getJSONArray("items");

            for (int i = 0; i < 3; i++) {
                var items = Object.getJSONObject(i);
//                System.out.println(items);
                var id = items.getJSONObject("id");
//                System.out.println(id);
                var videoID = id.getString("videoId");
//                System.out.println(videoID);
                VideoID[i] = videoID;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
//        for(int i = 0 ; i <3 ; i++){
//        System.out.println(apple[i]);
//        }

        try {
            for (int i = 0; i < 3; i++) {
                String json = Jsoup.connect("https://www.googleapis.com/youtube/v3/commentThreads?part=snippet&key=AIzaSyDUKYPZIaz4O3t5dyqClNuppGp3V9m-fPI&videoId=" + VideoID[i] + "&maxResults=3&order=relevance&textFormat=plainText ").ignoreContentType(true).execute().body();
//                System.out.println(json);

                JSONObject Object = new JSONObject(json);
                var items = Object.getJSONArray("items");
//                System.out.println(items);
                for (int j = 0; j < 3; j++) {
                    var array = items.getJSONObject(j);
//                    System.out.println(array);
                    var snippet = array.getJSONObject("snippet");
//                    System.out.println(snippet);
                    var topLevelComment = snippet.getJSONObject("topLevelComment");
//                    System.out.println(topLevelComment);
                    var snippet2 = topLevelComment.getJSONObject("snippet");
//                    System.out.println(snippet2);
                    var comment = snippet2.getString("textDisplay");
//                    System.out.println(Comment);

                    Comment[k] = comment;
                    var commentTime = snippet2.getString("publishedAt");
//                    System.out.println(commentTime);
                    CommentTime[k] = commentTime;
                    k++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }


        try {
            for (int i = 0; i < 3; i++) {
//                System.out.println(apple[i]);
                String json = Jsoup.connect("https://www.googleapis.com/youtube/v3/videos?part=snippet&key=AIzaSyDUKYPZIaz4O3t5dyqClNuppGp3V9m-fPI&id=" + VideoID[i] + "&maxResults=3").ignoreContentType(true).execute().body();
//                System.out.println(json);

                JSONObject Object = new JSONObject(json);
                var items = Object.getJSONArray("items");
//                System.out.println(items);
                var array = items.getJSONObject(0);
//                System.out.println(array);
                var snippet = array.getJSONObject("snippet");
//                System.out.println(snippet);
                var publishedAt = snippet.getString("publishedAt");
//                System.out.println(publishedAt);
                PublishTime[i] = publishedAt;
                var title = snippet.getString("title");
//                System.out.println(title);
                Title[i] = title;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

//      for(int i = 0 ; i <10 ; i++){
//      System.out.println(cat[i]);
//      }
        k = 0;
        for (int i = 0; i < 3; i++) {
            URL channel = new URL("https://www.youtube.com/watch?v=" + VideoID[i]);
            System.out.println("網址:" + channel);
            System.out.println("標題:" + Title[i]);
            System.out.println("發布時間:" + PublishTime[i]);
            for (int j = 0; j < 3; j++) {
                System.out.println("評論" + j + ":" + Comment[k]);
                System.out.println("留言時間:" + CommentTime[k]);
                k++;
            }
            System.out.println("--------------------------------------------");
        }
    }
}
