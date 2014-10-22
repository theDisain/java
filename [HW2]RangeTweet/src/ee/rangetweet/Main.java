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
            //here we can specify location PS:args[0]  Why make life harder?
            getCoordinates("New York".toString().replace(" ", "%20"));
            //Set the position and range of finding tweets
            query.setGeoCode(new GeoLocation(cords[0], cords[1]), Range(), Query.KILOMETERS);
            //siin
            //kuidagi tuleks piirata queryt query.
            //TODO tuleb välja uurida, veel ei tea
            //Result of twitter query
            QueryResult result;
            int sizeoftweets=0;
            //Tweets from twitter query
            List<Status> tweets;
            int count = 0;

            int nroframiningrq=0;

            do {
                //searches a page
                //we would have to find out a way to know how many requests are left
                //and then check if we have inafffff limits left
                //FIX-ME what a clasdkfjsdölkfjgr :D

                //probleem on praegu selles, et ta ei tohiks seda searchi üldse teha kui ratelimit on käes
                //aga ma ei tea seda ennem kui olen juba requesti ära teinud... tuleb mõelda

                //kui nüüd siia järgmise käivitamisega tullakse siis saadakse response kätte sest 1 request on veel alles
                result=twitter.search(query);
                //siis saan siin veel viimast korda lugeda et palju siis järgi on :D ilmselt NULL sest see esimene lugemine lõpetas viimse
                nroframiningrq=result.getRateLimitStatus().getRemaining();
                //it should also acount for remaining nr of req ---->0
                //for example if we have 50 requests left but ratelimitstatus.remaining is 40
                //then we will still get an eerror because rate limit is exceeded by 10
                if(result.getRateLimitStatus().getRemaining() != 0) {
                    tweets = result.getTweets();
                    sizeoftweets += result.getTweets().size();
                    //prints out tweets from that page
                    for (Status tweet : tweets) {
                        if (count < 1) {
                            System.out.println(count + "" + tweet.getCreatedAt() + "@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                            count++;
                        } else {

                            break;
                        }
                    }
                }
                //nüüd jääb järgmiseks korraks üks request alles
                if(nroframiningrq==1){
                    break;
                }
            } while(((query = result.nextQuery()) != null));
            //TODO - need võib välja kommenteerida hiljem siis :)
            System.out.println("loetud tweete on : "+sizeoftweets);
            System.out.println(cords[2]);
            System.out.println(cords[3]);
            System.out.println(cords[4]);
            System.out.println(cords[5]);

            //Range(); -- unneeded, Range() started off as a void

            System.out.println("Center of search coordinates are: " + cords[0] + "N " + cords[1] + "W");
            System.out.println("Search radius is: " + Range() + " kilometers" );



        } catch (Exception e) {
            //catch the exception siis ärme siin viga prindi
            //TODO - remove me BAMM I JUST FUCKIN DID IT
            System.out.println("Failed to search tweets");
            System.exit(-1);
        }
    }

    private static void getCoordinates(String placeName){
        try
        {
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
        }
        catch (Exception e) {
            System.out.println("Failed to get coordinates!!!" + e);
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
