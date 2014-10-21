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
import java.util.List;

public class Main {

    static Double[] cords=new Double[3];

    public static void main(String[] args) {

        try {

            Twitter twitter = new TwitterFactory().getInstance();

            //Twitter4j Query Object
            Query query = new Query();

            //here we can specify location PS:args[0]
            getCoordinates("Tallinn".toString().replace(" ","%20"));

            //cords[0]=23.4453515;

            //Set the position and range of finding tweets
            query.setGeoCode(new GeoLocation(cords[0], cords[1]), 0.1, Query.KILOMETERS);

            //Result of twitter query
            QueryResult result;

            //Tweets from twitter query
            List<Status> tweets;
            do {
                result = twitter.search(query);
                tweets = result.getTweets();

                for (Status tweet : tweets) {
                    System.out.println(/* tweet.getCreatedAt() + */"@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                }

            } while((query = result.nextQuery()) != null);

        } catch (Exception e) {
            //catch the exception
            System.out.println("Failed to search tweets");
            System.exit(-1);
        }
    }

    private static void getCoordinates(String placeName){

        try{

            String inputLine;

            URL url = new URL("http://nominatim.openstreetmap.org/search?q="+placeName+"&format=xml");

            URLConnection con = url.openConnection();

            //just-in-case things go bad....
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

            //cords[2] = Double.parseDouble(parse.getElementsByTagName("place").item(0).getAttributes().getNamedItem("boundingbox").getTextContent());


        } catch (Exception e) {
            System.out.println("Failed to get coordinates!!!" + e);
        }
    }
}
