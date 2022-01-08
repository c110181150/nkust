import org.jsoup.Jsoup;
import org.json.*;

import java.net.MalformedURLException;
import java.net.URL;


public class youtubeAPI {
    public static void main(String[] args) {
        String[] apple = new String[3];
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
                apple[i] = videoID;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
//        for(int i = 0 ; i <3 ; i++){
//        System.out.println(apple[i]);
//        }
    }
}
