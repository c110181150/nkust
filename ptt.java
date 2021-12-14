import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Connection.Response; //回傳回應
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document; //宣告函式庫
import org.jsoup.nodes.Element; //宣告函式庫
import org.jsoup.select.Elements; //宣告函式庫

    public class ptt {
        public static void main(String[] args) {
            try {
                String root_url = "https://www.ptt.cc/bbs"; //訪問網址
                String root_url_nobbs = "https://www.ptt.cc/"; //訪問網址
                String sub_url; //網路抓取器
                Document ptt_board_main; //文件
                Document ptt_board_sub; //文件類別
                String before_check = "";//before_check宣告為空字串
                Map<String, String> cookies=new HashMap<String, String>();

                Document ptt_index = Jsoup.connect(root_url).get();
                Elements ptt_boards = ptt_index.select("div.b-ent a");
                for (Element line : ptt_boards) {
                    before_check = line.attr("abs:href");
                    Response url_check = Jsoup.connect(before_check).followRedirects(true).execute();

                    if (url_check.url().toString().contains("over18"))
                    {
                        Response res = Jsoup
                                .connect(url_check.url().toString())
                                .data("yes", "yes")
                                .method(Connection.Method.POST)
                                .execute();
                        cookies = res.cookies();
                    }
                    ptt_board_main = Jsoup.connect(before_check).cookies(cookies).get();
                    Element ptt_board_main_CT = ptt_board_main.select("div.r-ent div.title a").first();

                    String ptt_board_main_CT_title= ptt_board_main_CT.text();
                    String ptt_board_main_CT_url= ptt_board_main_CT.attr("href");
                    sub_url=root_url_nobbs + ptt_board_main_CT_url;

                    System.out.println("" +ptt_board_main.title() + " " + sub_url);
                    System.out.println(ptt_board_main_CT_title);


                    ptt_board_sub = Jsoup.connect(sub_url).cookies(cookies).get();

                    String push="";
                    try {
                        Element ptt_sub_push = ptt_board_sub.select("div.push span[class=f3 hl push-userid]").first();
                        push += ptt_sub_push.text();
                        ptt_sub_push = ptt_board_sub.select("div.push span[class=f3 push-content]").first();
                        push = push + ptt_sub_push.text();
                        System.out.println("==>"+push);
                    }catch (Exception e){
                        System.out.println("==>無留言");
                    }
                    System.out.println();

                }
            }catch (Exception e){
                System.out.println(e);
            }
        }

    }