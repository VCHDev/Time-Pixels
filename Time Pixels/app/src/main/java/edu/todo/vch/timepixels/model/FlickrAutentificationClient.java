package edu.todo.vch.timepixels.model;

/**
 * Created by Valentin on 30/05/16.
 */

import android.util.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Flickr Autentification Client for network requests
 */
public class FlickrAutentificationClient {
    private static final String CONSUMER_API_KEY = "789f557cecdbf7438a540d518485589b";
    private static final String CONSUMER_API_SECRET = "af54f2a63f266a8e";
    private static final String ISO_8859_1 = "ISO-8859-1";
    private static final String HMAC_SHA1 = "HmacSHA1";


    private static final class Oauth {
        private static final String CALLBACK = "oauth_callback";
        private static final String CONSUMER_KEY = "oauth_consumer_key";
        private static final String NONCE = "oauth_nonce";
        private static final String SIGNATURE_METHOD = "oauth_signature_method";
        private static final String TIMESTAMP = "oauth_timestamp";
        private static final String VERSION = "oauth_version";
        private static final String SIGNATURE = "oauth_signature";
    }

    private static final class Uri {
        private static final String SCHEME = "http";
        private static final String AUTHORITY = "www.flickr.com";
        private static final String SERVICES = "services";
        private static final String OAUTH = "oauth";
        private static final String REQUEST_TOKEN = "request_token";
    }


    private static final class OauthParams {
        private static final String VERSION = "1.0";
        private static final String CALLBACK_ADDRESS = "http://www.vhcdev.edu/oauth/test";
        private static final String SIGNATURE = "HMAC-SHA1";

    }


    private String tokenSecret;
    private String oauthNonce;
    private String timeStamp;
    private String token;


    public FlickrAutentificationClient() {
        this.oauthNonce = generateOauthNonce();
        this.timeStamp = generateTimeStamp();
        tokenSecret = "";
        token = "";
    }


    private String generateTimeStamp() {
        return Long.toString(System.currentTimeMillis());
    }

    private String generateOauthNonce() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public String createSigningRequest() throws UnsupportedEncodingException {
        return createSignedURI(createURIPath(), createURIParam().build().toString());
    }

    private String createURIPath() {
        android.net.Uri.Builder uriPath = new android.net.Uri.Builder();
        uriPath.scheme(Uri.SCHEME)
                .authority(Uri.AUTHORITY)
                .appendPath(Uri.SERVICES)
                .appendPath(Uri.OAUTH)
                .appendPath(Uri.REQUEST_TOKEN);

        return uriPath.build().toString();
    }

    private android.net.Uri.Builder createURIParam() throws UnsupportedEncodingException {
        android.net.Uri.Builder uriParam = new android.net.Uri.Builder()
                .appendQueryParameter(Oauth.CALLBACK, OauthParams.CALLBACK_ADDRESS)
                .appendQueryParameter(Oauth.CONSUMER_KEY, CONSUMER_API_KEY)
                .appendQueryParameter(Oauth.NONCE, oauthNonce)
                .appendQueryParameter(Oauth.SIGNATURE_METHOD, OauthParams.SIGNATURE)
                .appendQueryParameter(Oauth.TIMESTAMP, timeStamp)
                .appendQueryParameter(Oauth.VERSION, OauthParams.VERSION);

        return uriParam;
    }

    private String createURIParamForRequestToken(android.net.Uri.Builder data, String oauthSignature) throws UnsupportedEncodingException {
        data.appendQueryParameter(Oauth.SIGNATURE, oauthSignature);
        return data.build().toString();
    }

    private String encodeString(String data) throws UnsupportedEncodingException {
        return URLEncoder.encode(data, ISO_8859_1);
    }

    private String createSignedURI(String uriPath, String uriParameters) throws UnsupportedEncodingException {
        return "GET" + "&" + encodeString(uriPath) + "&" + encodeString(uriParameters.replace("?", ""));
    }

    private String createURIForRequestToken(String path, String parameters) {
        return path + parameters;
    }

    public String createTokenRequest() throws UnsupportedEncodingException {
        String signedURI = generateHMACSha1(createSigningRequest(), createSecretKey());
        String urlParametersForRequestToken = createURIParamForRequestToken(createURIParam(), signedURI);
        return createURIForRequestToken(createURIPath(), urlParametersForRequestToken);
    }

    private String createSecretKey() {
        return CONSUMER_API_SECRET + "&" + tokenSecret;
    }

    private String generateHMACSha1(String data, String secretKey) {
        try {
            return hashURIForRequestToken(data, secretKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String hashURIForRequestToken(String data, String secretKey) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        Mac mac = Mac.getInstance(HMAC_SHA1);
        SecretKeySpec secret = new SecretKeySpec(secretKey.getBytes("UTF-8"), mac.getAlgorithm());
        mac.init(secret);
        byte[] digest = mac.doFinal(data.getBytes("UTF-8"));

        return new String(Base64.encodeToString(digest, Base64.NO_WRAP));
    }


    public void autentificate() {
        try {
            getRequestToken(createTokenRequest());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    private void getRequestToken(String uri) {
        HttpURLConnection urlConnection  = null;
        try {
            urlConnection = getHttpURLConnectionToAccesRequestTokens(uri);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }


    }

 
    private HttpURLConnection getHttpURLConnectionToAccesRequestTokens(String uri) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) new URL(uri).openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String line;
        String responseString = "";

        while (( line = reader.readLine()) != null )
            responseString += line;

        getTokensFromResponse(responseString);

        return urlConnection;
    }

    private void getTokensFromResponse(String responseString) {
        String[] tokens = responseString.split("&");
        Map<String, String> tokenMap = new HashMap<>();
        extractTokensFromStringArrayToKeyValueMap(tokens, tokenMap);
        extractTokenAndSecretTokenFromMap(tokenMap);

        System.out.println("----- tokenSecret " + tokenSecret + " --- token === " + token);
    }

    private void extractTokenAndSecretTokenFromMap(Map<String, String> tokenMap) {
        for (Map.Entry<String, String> entry : tokenMap.entrySet()) {
            System.out.println(entry.getKey() + "//" + entry.getValue());

            if (entry.getKey().equals("oauth_token_secret") ) {
                tokenSecret = entry.getValue();
            } else if (entry.getKey().equals("oauth_token")) {
                token = entry.getValue();
            }
        }
    }

    private void extractTokensFromStringArrayToKeyValueMap(String[] tokens, Map<String, String> tokenMap) {
        for (String tokenLine: tokens) {
            String[] keYValue = tokenLine.split("=");
            tokenMap.put(keYValue[0], keYValue[1]);
        }
    }


}
