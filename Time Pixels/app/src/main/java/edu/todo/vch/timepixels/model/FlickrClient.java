package edu.todo.vch.timepixels.model;

/**
 * Created by Valentin on 30/05/16.
 */

import android.net.Uri;

import java.net.URLEncoder;

/**
 * Flickr Client for network requests
 */
public class FlickrClient {

    private static final String  API_KEY = "789f557cecdbf7438a540d518485589b";
    private static final String  API_SECRET = "af54f2a63f266a8e";

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

    private static final String  PARAM_NONCE = "321312312b31j2h3v21jh4v12412kj312kj";

    private static final String  PARAM_SIGNATURE_METHOD = "HMAC-SHA1";
    private static final String  PARAM__VERSION = "1.0";
    private static final String  PARAM__CALLBACK = "http://www.vhcdev.edu/oauth/test";

    private String tokenSecret = "";
    private String Key = API_SECRET + "&" + tokenSecret;

    private Uri.Builder uriBuilder;


    public void login(){
    }

    public String createSigningRequest(){
        System.out.println("App Key --> " + Key);
        uriBuilder = new Uri.Builder();
        uriBuilder.scheme(URI_SCHEME)
                .authority(URI_AUTHORITY)
                .appendPath(URI_SERVICE_PATH)
                .appendPath(URI_OAUTH_PATH)
                .appendPath(URI_REQUEST_TOKEN_PATH)
                .appendQueryParameter(OAUTH_NONCE,  PARAM_NONCE)
                .appendQueryParameter(OAUTH_TIMESTAMP, Long.toString(System.currentTimeMillis()))
                .appendQueryParameter(OAUTH_CONSUMER_KEY, API_KEY)
                .appendQueryParameter(OAUTH_SIGNATURE_METHOD, PARAM_SIGNATURE_METHOD)
                .appendQueryParameter(OAUTH_VERSION, PARAM__VERSION)
                .appendQueryParameter(OAUTH_CALLBACK, PARAM__CALLBACK);

        return uriBuilder.build()..toString();
    }


}
