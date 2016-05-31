package edu.todo.vch.timepixels.model;

/**
 * Created by Valentin on 30/05/16.
 */

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Flickr Client for network requests
 */
public class FlickrClient {

    private static final String  API_KEY = "789f557cecdbf7438a540d518485589b";
    private static final String  API_SECRET = "af54f2a63f266a8e";
    private static final String  ISO_8859_1 = "ISO-8859-1";
    private static final String  HMAC_SHA1_ALGORITHM = "HmacSHA1";

    private static final String  URI_SCHEME = "https";
    private static final String  URI_AUTHORITY = "www.flickr.com";
    private static final String  URI_SERVICE_PATH = "services";
    private static final String  URI_OAUTH_PATH = "oauth";
    private static final String  URI_REQUEST_TOKEN_PATH = "request_token";
    private static final String  OAUTH_NONCE = "oauth_nonce";
    private static final String  OAUTH_TIMESTAMP = "oauth_timestamp";
    private static final String  OAUTH_CONSUMER_KEY = "oauth_consumer_key";
    private static final String  OAUTH_SIGNATURE_METHOD = "oauth_signature_method";
    private static final String  OAUTH_VERSION = "oauth_version";
    private static final String  OAUTH_CALLBACK = "oauth_callback";
    private static final String  OAUTH_SIGNIATURE = "oauth_signature";


    private static final String  PARAM_SIGNATURE_METHOD = "HMAC-SHA1";
    private static final String  PARAM__VERSION = "1.0";
    private static final String  PARAM__CALLBACK = "http://www.vhcdev.edu/oauth/test";

    private String tokenSecret = "";
    private String secretAppKey = API_SECRET + "&" + tokenSecret;

    //---------------------------------------------------------------
    private String Timestamp= "1316657628";
    private String Nonce = "C2F26CD5C075BA9050AD8EE90644CF29";
    private String Consumer_Key = "768fe946d252b119746fda82e1599980";
    private String Version = "1.0";
    private String Signature_Method =  "HMAC-SHA1";
    private String Callback_URL = "http://www.wackylabs.net/oauth/test";
    private String Consumer_Secret = "1a3c208e172d3edc";
            private String Key_APP = Consumer_Secret + "&" + "";
     ///////////////////////////////////////
    private String oauthNounce = generateOauthNonce();
    private String timeStamp = generateTimeStamp();
    private String sha1 ;


    public String createSigningRequest() throws UnsupportedEncodingException {
//        System.out.println("App Key --> " + secretAppKey);
//        System.out.println("Alphanumeric String--> " + generateOauthNonce());
//        Uri.Builder uriBuilder = new Uri.Builder();
//        uriBuilder.scheme(URI_SCHEME)
//                .authority(URI_AUTHORITY)
//                .appendPath(URI_SERVICE_PATH)
//                .appendPath(URI_OAUTH_PATH)
//                .appendPath(URI_REQUEST_TOKEN_PATH)
//                .appendQueryParameter(OAUTH_CALLBACK, Callback_URL)
//                .appendQueryParameter(OAUTH_CONSUMER_KEY, Consumer_Key)
//                .appendQueryParameter(OAUTH_NONCE,  Nonce)
//                .appendQueryParameter(OAUTH_SIGNATURE_METHOD, Signature_Method)
//                .appendQueryParameter(OAUTH_TIMESTAMP, Timestamp)
//                .appendQueryParameter(OAUTH_VERSION, Version);

        String part1= "GET";
        String part2 = URLEncoder.encode("https://www.flickr.com/services/oauth/request_token", ISO_8859_1);
        String part3 = URLEncoder.encode("oauth_callback=http://www.wackylabs.net/oauth/test&oauth_consumer_key=768fe946d252b119746fda82e1599980&oauth_nonce=C2F26CD5C075BA9050AD8EE90644CF29&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1316657628&oauth_version=1.0",ISO_8859_1);

        String baseString = part1 + "&" + part2 + "&" + part3;

        System.out.println("string part2 encoded:  " + part2);
        System.out.println("string part3 encoded:  " + part3);
        System.out.println("Base String:  " + baseString);


//        System.out.println("String    --------- " + uriBuilder.build().toString());
//        System.out.println("Encoded String   --------- " + URLEncoder.encode(uriBuilder.build().toString(),  ISO_8859_1));
//        return URLEncoder.encode(uriBuilder.build().toString(),  ISO_8859_1);

        return baseString;
    }

    public String createTokenRequest() throws UnsupportedEncodingException {
//       sha1 =generateHMACSha1();
//        Uri.Builder uriBuilder = new Uri.Builder();
//        uriBuilder.scheme(URI_SCHEME)
//                .authority(URI_AUTHORITY)
//                .appendPath(URI_SERVICE_PATH)
//                .appendPath(URI_OAUTH_PATH)
//                .appendPath(URI_REQUEST_TOKEN_PATH)
//                .appendQueryParameter(OAUTH_CALLBACK, Callback_URL)
//                .appendQueryParameter(OAUTH_CONSUMER_KEY, Consumer_Key)
//                .appendQueryParameter(OAUTH_NONCE,  Nonce)
//                .appendQueryParameter(OAUTH_SIGNIATURE, sha1)
//                .appendQueryParameter(OAUTH_SIGNATURE_METHOD, Signature_Method)
//                .appendQueryParameter(OAUTH_TIMESTAMP, Timestamp)
//                .appendQueryParameter(OAUTH_VERSION, Version);

        String link = "https://www.flickr.com/services/oauth/request_token?oauth_callback=" + URLEncoder.encode("http://www.wackylabs.net/oauth/test", ISO_8859_1) + "&oauth_consumer_key=768fe946d252b119746fda82e1599980&oauth_nonce=C2F26CD5C075BA9050AD8EE90644CF29&oauth_timestamp=1316657628&oauth_signature_method=HMAC-SHA1&oauth_version=1.0&oauth_signature=0fhNGlzpFNAsTme%2FhDfUb5HPB5U%3D";

//        System.out.println("Final link ---> " + uriBuilder.build().toString());
//
//        return  uriBuilder.build().toString();
        return link;
    }

    private String generateTimeStamp(){
        return  Long.toString(System.currentTimeMillis());
    }

//    public String generateSigniature() throws UnsupportedEncodingException {
//        StringBuilder sb = new StringBuilder("GET&");
//        sb.append(createSigningRequest());
//        return sb.toString();
//    }

    private String generateOauthNonce(){
        return UUID.randomUUID().toString().replace("-", "");
    }
   // String data, String keyConsumer


    public String generateHMACSha1()  {

        try {

            byte[] keyBytes = Key_APP.getBytes("UTF-8");
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_SHA1_ALGORITHM);

            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);

            byte[] bytes = mac.doFinal(createSigningRequest().getBytes("UTF-8"));

            return new String(Base64.encodeToString(bytes, Base64.NO_WRAP));


        } catch (Exception e ) {
            throw new RuntimeException(e);
        }






    }


}
