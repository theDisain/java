package ee.rangetweet;

import twitter4j.Status;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by Joonas on 23.10.2014.
 */
public class Tweet {
    public Date date;
    public String name;
    public String text;

    public Tweet(Status status){
        date = status.getCreatedAt();
        name = status.getUser().getScreenName();
        text = status.getText();
    }
    public static List<Tweet> sort(List<Tweet> tweets, String type) {
        if (type.contains("asc"))
            Main.order = -1;
        else
            Main.order = 1;
        if (type.startsWith("date"))
            Collections.sort(tweets, byDate);
        if (type.startsWith("name"))
            Collections.sort(tweets, byName);
        if (type.startsWith("text"))
            Collections.sort(tweets, byText);
        return tweets;
    }
    static Comparator<Tweet> byName = new Comparator<Tweet>() {
        public int compare(Tweet left, Tweet right) {
            return Main.order*((left).name.toLowerCase().compareTo((right).name.toLowerCase()));
        }
    };

    static Comparator<Tweet> byDate = new Comparator<Tweet>() {
        public int compare(Tweet left, Tweet right) {
            return Main.order*(((left).date).compareTo(((right).date)));
        }
    };

    static Comparator<Tweet> byText = new Comparator<Tweet>() {
        public int compare(Tweet left, Tweet right) {
            return Main.order*((left).text.toLowerCase().compareTo((right).text.toLowerCase()));
        }
    };

}
