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

    static Double[] cords=new Double[6];

    public static void main(String[] args) {

        try {

            Twitter twitter = new TwitterFactory().getInstance();

            //Twitter4j Query Object
            Query query = new Query();

            //here we can specify location PS:args[0]
            getCoordinates("Tallinn".toString().replace(" ","%20"));

            //cords[0]=23.4453515;

            //TODO-- implement radius


            //Set the position and range of finding tweets
            query.setGeoCode(new GeoLocation(cords[0], cords[1]), 1, Query.KILOMETERS);


            //Result of twitter query
            QueryResult result;
            int sizeoftweets=0;
            //Tweets from twitter query
            List<Status> tweets;
            do {
                result = twitter.search(query);
                tweets = result.getTweets();
                sizeoftweets+=result.getTweets().size();
                for (Status tweet : tweets) {
                    System.out.println( tweet.getCreatedAt() + "@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                }

            } while((query = result.nextQuery()) != null);
            System.out.println("Masiivis on : "+sizeoftweets);
            System.out.println(cords[2]);
            System.out.println(cords[3]);
            System.out.println(cords[4]);
            System.out.println(cords[5]);
            Range();
            System.out.println("Center of search coordinates are: " + cords[0] + "N " + cords[1] + "W");
        } catch (Exception e) {
            //catch the exception
            System.out.println("Failed to search tweets" + e);
            System.exit(-1);
        }
    }

    private static void getCoordinates(String placeName){

        try{

            String inputLine;

            URL url = new URL("http://nominatim.openstreetmap.org/search?q="+placeName+"&format=xml");

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
        } catch (Exception e) {
            System.out.println("Failed to get coordinates!!!" + e);
        }
    }

    private static void Range(){
        final int R = 6371; //Radius of earth in kilometers
        Double latDistance = Math.toRadians(cords[3]-cords[2]);
        Double lonDistance = Math.toRadians(cords[5] - cords[4]);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(cords[2])) * Math.cos(Math.toRadians(cords[3])) *
                Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        Double distance = R * c;
        System.out.println("Radius of searched area: " + distance  + " kilometers");

        }
}
