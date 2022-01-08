import org.jsoup.Jsoup;
import org.json.*;

import java.net.MalformedURLException;
import java.net.URL;

public class youtubeAPI {
    public static void main(String[] args) {
        String[] apple = new String[3];
        String[] banana = new String[9];   
        String[] cat = new String[9];
        String[] dog = new String[3];
        String[] element = new String[3];
        try {
            String youtubeSearch = "阿神";
            String json = Jsoup.connect("https://www.googleapis.com/youtube/v3/search?part=snippet&q=" + youtubeSearch + "&key=AIzaSyDUKYPZIaz4O3t5dyqClNuppGp3V9m-fPI&type=video&maxResults=3").ignoreContentType(true).execute().body();
//          format json by https://jsoneditoronline.org/
//          System.out.println(json);

            JSONObject resObject = new JSONObject(json);
            var Object = resObject.getJSONArray("items");

            for (int i = 0; i < 3; i++) {
                var items = Object.getJSONObject(i);
//              System.out.println(items);
                var id = items.getJSONObject("id");
//              System.out.println(id);
                var videoID = id.getString("videoId");
//              System.out.println(videoID);
                apple[i] = videoID;
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
//      for(int i = 0 ; i <3 ; i++){
//        System.out.println(apple[i]);
//      }
        try {
            for (int i = 0; i < 3; i++) {
                String json = Jsoup.connect("https://www.googleapis.com/youtube/v3/commentThreads?part=snippet&key=AIzaSyDUKYPZIaz4O3t5dyqClNuppGp3V9m-fPI&videoId=" + apple[i] + "&maxResults=3&order=relevance&textFormat=plainText ").ignoreContentType(true).execute().body();
//              System.out.println(json);

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
        } 
        catch (Exception e) {
            System.out.println(e);
        }
       
        try {
            for (int i = 0; i < 3; i++){
                String json = Jsoup.connect("https://www.googleapis.com/youtube/v3/videos?part=snippet&key=AIzaSyDUKYPZIaz4O3t5dyqClNuppGp3V9m-fPI&id=" + apple[i] + "&maxResults=3").ignoreContentType(true).execute().body();
//              System.out.println(json);

                JSONObject Object = new JSONObject(json);
                var items = Object.getJSONArray("items");
//              System.out.println(items);
                var array = items.getJSONObject(0);
//              System.out.println(array);
                var snippet = array.getJSONObject("snippet");
//              System.out.println(snippet);
                var publishedAt = snippet.getString("publishedAt");
//              System.out.println(publishedAt);
                dog[i] = publishedAt;
                var title = snippet.getString("title");
                System.out.println(title);
                element[i]=title;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
