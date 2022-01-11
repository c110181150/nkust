import org.jsoup.Jsoup;
import org.json.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class youtubeAPI {
    public static void main(String[] args) throws MalformedURLException {
        int allComment = 0;
        int videoNumber = 0;  //影片數量
        int commentNumber = 0; //留言數量
        String youtubeSearch = "";

        Scanner scanner = new Scanner(System.in);
        System.out.println("請輸入關鍵字、影片數量、評論數量(以Tab鍵分開)：");
        youtubeSearch = scanner.next();
        videoNumber = scanner.nextInt();
        commentNumber = scanner.nextInt();
        System.out.println("您輸入的關鍵字為：" + youtubeSearch);
        System.out.println("您輸入的影片數量為：" + videoNumber);
        System.out.println("您輸入的評論數量為：" + commentNumber );
        
        String[] VideoID = new String[videoNumber];
        String[] Comment = new String[videoNumber*commentNumber];
        String[] CommentTime = new String[videoNumber*commentNumber];
        String[] PublishTime = new String[videoNumber];
        String[] Title = new String[videoNumber];
        
        try {
            String json = Jsoup.connect("https://www.googleapis.com/youtube/v3/search?part=snippet&q=" + youtubeSearch + "&key=AIzaSyDUKYPZIaz4O3t5dyqClNuppGp3V9m-fPI&type=video&maxResults="+videoNumber).ignoreContentType(true).execute().body();
//            System.out.println(json);

            JSONObject resObject = new JSONObject(json);
            JSONArray Object = resObject.getJSONArray("items");
            for (int i = 0; i < videoNumber ; i++) {
                JSONObject items = Object.getJSONObject(i);
//                System.out.println(items);
                JSONObject id = items.getJSONObject("id");
//                System.out.println(id);
                String videoID = id.getString("videoId");
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
            for (int i = 0; i < videoNumber ; i++) {
                String json = Jsoup.connect("https://www.googleapis.com/youtube/v3/commentThreads?part=snippet&key=AIzaSyDUKYPZIaz4O3t5dyqClNuppGp3V9m-fPI&videoId=" + VideoID[i] + "&maxResults="+commentNumber+"&order=relevance&textFormat=plainText ").ignoreContentType(true).execute().body();
//                System.out.println(json);

                JSONObject Object = new JSONObject(json);
                JSONArray items = Object.getJSONArray("items");

//                System.out.println(items);
                for (int j = 0; j < commentNumber; j++) {
                    JSONObject array = items.getJSONObject(j);
//                    System.out.println(array);
                    JSONObject snippet = array.getJSONObject("snippet");
//                    System.out.println(snippet);
                    JSONObject topLevelComment = snippet.getJSONObject("topLevelComment");
//                    System.out.println(topLevelComment);
                    JSONObject snippet2 = topLevelComment.getJSONObject("snippet");
//                    System.out.println(snippet2);
                    String comment = snippet2.getString("textDisplay");
//                    System.out.println(Comment);

                    Comment[allComment] = comment;
                    String commentTime = snippet2.getString("publishedAt");
//                    System.out.println(commentTime);
                    CommentTime[allComment] = commentTime;
                    allComment++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }


        try {
            for (int i = 0; i < videoNumber; i++) {
//                System.out.println(apple[i]);
                String json = Jsoup.connect("https://www.googleapis.com/youtube/v3/videos?part=snippet&key=AIzaSyDUKYPZIaz4O3t5dyqClNuppGp3V9m-fPI&id=" + VideoID[i] + "&maxResults="+videoNumber).ignoreContentType(true).execute().body();
//                System.out.println(json);

                JSONObject Object = new JSONObject(json);
                JSONArray items = Object.getJSONArray("items");
//                System.out.println(items);
                JSONObject array = items.getJSONObject(0);
//                System.out.println(array);
                JSONObject snippet = array.getJSONObject("snippet");
//                System.out.println(snippet);
                String publishedAt = snippet.getString("publishedAt");
//                System.out.println(publishedAt);
                PublishTime[i] = publishedAt;
                String title = snippet.getString("title");
//                System.out.println(title);
                Title[i] = title;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

//      for(int i = 0 ; i <10 ; i++){
//      System.out.println(cat[i]);
//      }
        allComment = 0;
        for (int i = 0; i < videoNumber; i++) {
            URL channel = new URL("https://www.youtube.com/watch?v=" + VideoID[i]);
            System.out.println("網址:" + channel);
            System.out.println("標題:" + Title[i]);
            System.out.println("發佈時間:" + PublishTime[i]);
            for (int j = 0; j < commentNumber ; j++) {
                System.out.println("評論" + j + ":" + Comment[allComment]);
                System.out.println("留言時間:" + CommentTime[allComment]);
                allComment++;
            }
            System.out.println("--------------------------------------------");
        }
    }
}
