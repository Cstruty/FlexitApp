package com.example.flexit.quote_of_day;


import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuoteOfDayClient extends QuoteOfDay {

    private static final String TAG = "QuoteOfDayClient";
    private static final long DELAY = 100;
    private static final Pattern quote_body_p = Pattern.compile("(?<=\"q\":\")(.*)(?=\",\"a)");
    private static final Pattern quote_author_p = Pattern.compile("(?<=\"a\":\")(.*)(?=\",\"h)");
    private String APIURL;
    private String Port;
    private StringRequest request;
    private final Context context;
    private JSONObject jsonQuote;
    private Quote quote;
    private boolean gotResponse = false;
    private boolean success = false;
    private static final int timeout_count = 50;


    public QuoteOfDayClient(Context context) {
        this.context = context;
    }

    protected QuoteOfDayClient() {
        this.context = null;
    }

    private String getURL() {
        return "https://zenquotes.io/api/random";
    }

    private void parseAuthor(String expression) throws ParseException {
        Matcher author_m = quote_author_p.matcher(expression);
        if (author_m.find()) {
            quote.setAuthor(author_m.group(0));
        } else {
            throw new ParseException("Unable to find Author", 0);
        }
    }

    private void parseBody(String expression) throws ParseException {
        Matcher body_m = quote_body_p.matcher(expression);
        if (body_m.find()) {
            quote.setBody(body_m.group(0));
        } else {
            throw new ParseException("Unable to find quote body", 0);
        }
    }

    private void parseJson(String expression) throws ParseException {
        quote = new Quote();
        parseBody(expression);
        parseAuthor(expression);
    }

    protected String testParseAuthor(String jsonString) throws ParseException {
        parseBody(jsonString);
        return quote.getBody();
    }

    protected String testParseBody(String jsonString) throws ParseException {
        parseBody(jsonString);
        return quote.getBody();
    }

    private Response.Listener<String> getListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    parseJson(response);
                } catch (ParseException e) {
                    Log.e(TAG, "Unable to parse string \n" + response + "\n" + e.getMessage());
                    gotResponse = true;
                    success = false;
                    return;
                }
                gotResponse = true;
                success = true;
                Log.d(TAG, "Got response");
            }
        };
    }

    private Response.ErrorListener getErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.w(TAG, "Unable to get response from API");
                Log.w(TAG, error.toString());
                gotResponse = true;
            }
        };
    }


    private void awaitForQuote() {
        int count = 0;
        while (!gotResponse && count != timeout_count) {
            try {
                Thread.sleep(DELAY);
                Log.d(TAG, "awaiting response...");
                count++;
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    protected void makeRequest() {
        RequestQueue queue = Volley.newRequestQueue(this.context);
        String url = this.getURL();

        request = new StringRequest(Request.Method.GET, url, getListener(), getErrorListener());
        queue.add(request);
        queue.start();
    }

    @Override
    public Quote getQuote() throws IOException {
        if (context == null) {
            return null;
        }
        makeRequest();
        awaitForQuote();
        Log.d(TAG, "running response");

        if (!success) {
            throw new IOException("Unable to talk to Quote of Day API");
        }

//        return new Quote();

        return quote;
    }
}
