package edu.todo.vch.timepixels.model;

/**
 * Created by Valentin on 30/05/16.
 */

import android.net.Uri;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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
    private String tokenSecret;
    private String oauthNonce;
    private String timeStamp;


    public FlickrAutentificationClient() {
        this.oauthNonce = generateOauthNonce();
        this.timeStamp = generateTimeStamp();
        tokenSecret = "";
    }


    private String generateTimeStamp() {
        return Long.toString(System.currentTimeMillis());
    }

    private String generateOauthNonce() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private String createSigningRequest() throws UnsupportedEncodingException {
        return createSignedURI(createURIPath(), createURIParameters().toString());
    }

    private String createURIPath() {
        Uri.Builder uriPath = new Uri.Builder();
        uriPath.scheme("http")
                .authority("www.flickr.com")
                .appendPath("services")
                .appendPath("oauth")
                .appendPath("request_token");

        return uriPath.build().toString();
    }

    private StringBuilder createURIParameters() throws UnsupportedEncodingException {
        StringBuilder uriParamBuilder = new StringBuilder();
        uriParamBuilder.append("oauth_callback").append("=").append(encodeString("http://www.vhcdev.edu/oauth/test")).append("&");
        uriParamBuilder.append("oauth_consumer_key").append("=").append(CONSUMER_API_KEY).append("&");
        uriParamBuilder.append("oauth_nonce").append("=").append(oauthNonce).append("&");
        uriParamBuilder.append("oauth_signature_method").append("=").append("HMAC-SHA1").append("&");
        uriParamBuilder.append("oauth_timestamp").append("=").append(timeStamp).append("&");
        uriParamBuilder.append("oauth_version").append("=").append("1.0");

        return uriParamBuilder;
    }

    private String encodeString(String data) throws UnsupportedEncodingException {
        return URLEncoder.encode(data, ISO_8859_1);
    }

    private String createURIParametersForRequestToken(StringBuilder data, String oauthSignature) throws UnsupportedEncodingException {
        data.append("&").append("oauth_signature").append("=").append(encodeString(oauthSignature));

        return data.toString();
    }

    private String createSignedURI(String uripath, String uriParameters) throws UnsupportedEncodingException {
        return "GET" + "&" + encodeString(uripath) + "&" + encodeString(uriParameters);
    }

    private String createURIForRequestToken(String path, String parameters) {
        return path + "?" + parameters;
    }

    public String createTokenRequest() throws UnsupportedEncodingException {
        String signedURI = generateHMACSha1(createSigningRequest(), createSecretKey());
        String urlParametersForRequestToken = createURIParametersForRequestToken(createURIParameters(), signedURI);
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
        Mac mac = Mac.getInstance("HmacSHA1");
        SecretKeySpec secret = new SecretKeySpec(secretKey.getBytes("UTF-8"), mac.getAlgorithm());
        mac.init(secret);
        byte[] digest = mac.doFinal(data.getBytes("UTF-8"));

        return new String(Base64.encodeToString(digest, Base64.NO_WRAP));
    }


}
