package ee.rangetweet;


import org.w3c.dom.Document;
import twitter4j.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.net.URLEncoder;

public class Main {

    static Double[] cords=new Double[6];
    public static int order = 1;
    public static boolean exit = false;

    public static void main(String[] args) {

        try {
            Twitter twitter = new TwitterFactory().getInstance();
            //Twitter4j Query Object
            Query query = new Query();
            query.setCount(Integer.parseInt(args[1]));
            //here we can specify location PS:args[0]  Why make life harder?
            getCoordinates(args[0].toString().replace(" ", "%20"));
            //Set the position and range of finding tweets
            query.setGeoCode(new GeoLocation(cords[0], cords[1]), Range(), Query.KILOMETERS);
            //siin
            //kuidagi tuleks piirata queryt query.
            //TODO tuleb välja uurida, veel ei tea
            //Result of twitter query
            QueryResult result;
            //int sizeoftweets=0;
            //Tweets from twitter query
            List<Status> tweets;
            List<Tweet> tweets2 = new ArrayList<Tweet>();
            //int count = 0;

            int nroframiningrq=0;

            do {
                //searches a page
                //we would have to find out a way to know how many requests are left
                //and then check if we have enough limits left
                //FIX-ME what a clasdkfjsdölkfjgr :D

                //Gets a response if at least 1 request is left.
                result=twitter.search(query);
                //Last read of queries. Should return a NULL if things are bad.
                nroframiningrq=result.getRateLimitStatus().getRemaining();
                //it should also acount for remaining nr of req ---->0
                //for example if we have 50 requests left but ratelimitstatus.remaining is 40
                //then we will still get an eerror because rate limit is exceeded by 10
                if(result.getRateLimitStatus().getRemaining() != 0) {
                    tweets = result.getTweets();
                    //sizeoftweets += result.getTweets().size();
                    //prints out tweets from that page
                    for (Status tweet : tweets) {

                        if (!exit) {
                            tweets2.add(new Tweet(tweet));
                            // System.out.println(count + "" + tweet.getCreatedAt() + "@" + tweet.getUser().getScreenName() + " - " + tweet.getText()); For more tweets

                            //count++;
                        }
                        /*else {
                            exit = true;
                            break;
                        }*/
                    }
                }
                // SHould leave a request for next time.
                if(nroframiningrq==1){
                    System.out.println("Something is fucky");
                    exit = true;
                    break;
                }
            } while(!exit);
            //These were used for debug. I like clutter.
            /*System.out.println("loetud tweete on : "+sizeoftweets);
            System.out.println(cords[2]);
            System.out.println(cords[3]);
            System.out.println(cords[4]);
            System.out.println(cords[5]);m
            //Range(); -- unneeded, Range() started off as a void
            */

            Tweet.sort(tweets2, args[2]);
            for (Tweet t : tweets2) {
                System.out.println();
                System.out.println("@"+t.name + "   " + t.date.toLocaleString());
                System.out.println(t.text);
                System.out.println();
            }
            System.out.println("Center of search coordinates are: " + cords[0] + "N " + cords[1] + "W");
            System.out.println("Search radius is: " + Range() + " kilometers" );


        } catch (Exception e) {
            //catch the exception
            //TODO - remove me , it's a SUCCESS!
            System.out.println("Failed to search tweets" + e);
            System.exit(-1);
        }
    }

    private static void getCoordinates(String placeName){
        try
        {
            String inputLine;
            URL url = new URL("http://nominatim.openstreetmap.org/search?q="+URLEncoder.encode(placeName, "UTF-8")+"&format=xml");
            URLConnection con = url.openConnection();
            con.setReadTimeout( 2000 );
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String responseString="";
            while ((inputLine = in.readLine()) != null) {
                responseString+=inputLine;
            }
            in.close();
            DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document parse = newDocumentBuilder.parse(new ByteArrayInputStream(responseString.getBytes("UTF-8")));
            cords[0] = Double.parseDouble(parse.getElementsByTagName("place").item(0).getAttributes().getNamedItem("lat").getTextContent());
            cords[1] = Double.parseDouble(parse.getElementsByTagName("place").item(0).getAttributes().getNamedItem("lon").getTextContent());
            String boundingbox = parse.getElementsByTagName("place").item(0).getAttributes().getNamedItem("boundingbox").getTextContent();
            String[] parts = boundingbox.split(",");
            for (int i=0;i<parts.length;i++){
                cords[2+i] = Double.parseDouble(parts[i]);
            }
            // System.out.println("BOUNDIB BOX ON :"+parse.getElementsByTagName("place").item(0).getAttributes().getNamedItem("boundingbox").getTextContent());
        }
        catch (Exception e) {
            System.out.println("Failed to get coordinates.");
        }
    }

    private static Double Range(){
        final int R = 6371; //Radius of earth in kilometers
        Double latDistance = Math.toRadians(cords[3]-cords[2]);
        Double lonDistance = Math.toRadians(cords[5] - cords[4]);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(cords[2])) * Math.cos(Math.toRadians(cords[3])) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;

    }
}
