/**
 * SimpleTwitStream
 *  memonkey at gmail
 */

import twitter4j.*;
import twitter4j.auth.AccessToken;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public class MainStream {



    public static void main(String[] args) throws IOException, TwitterException {

//keywords to search for in the stream
        final String[] keywords = {"coupon", "promo", "discount", "fucking", "fuck"};

//Your Twitter App's Consumer Key
        String consumerKey = "XXX";

//Your Twitter App's Consumer Secret
        String consumerSecret = "XXX";

//Your Twitter Access Token
        String accessToken = "XXX";

//Your Twitter Access Token Secret
        String accessTokenSecret = "XXX";

//Instantiate a re-usable and thread-safe factory
        TwitterStreamFactory twitterStreamFactory = new TwitterStreamFactory();

//Instantiate a new Twitter instance
        TwitterStream twitterStream = twitterStreamFactory.getInstance();

//setup OAuth Consumer Credentials
        twitterStream.setOAuthConsumer(consumerKey, consumerSecret);

//setup OAuth Access Token
        twitterStream.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));

        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {

            //loop through keywords
                for(int i = 0; i < keywords.length-1; i++) {
                    if(status.getText().contains(keywords[i])){
                        System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                    }
                }
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                //System.out.println("Got a status deletion notice id: " + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int i) {
                System.out.println("Got track limitation notice: " + i);
            }

            @Override
            public void onScrubGeo(long l, long l2) {
                System.out.println("Got scrub_geo event userId: " + l + " upToStatusId: " + l2);
            }

            @Override
            public void onStallWarning(StallWarning stallWarning) {
                System.out.println("Got a stall warning: " + stallWarning);
            }

            @Override
            public void onException(Exception e) {
                e.printStackTrace();
            }
        };

        twitterStream.addListener(listener);
        twitterStream.sample();



    }

}