package api_Yatharth;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public static Place details(String reference) {
    HttpURLConnection conn = null;
    StringBuilder jsonResults = new StringBuilder();
    try {
        StringBuilder sb = new StringBuilder(PLACES_API_BASE);
        sb.append(TYPE_DETAILS);
        sb.append(OUT_JSON);
        sb.append("?sensor=false");
        sb.append("&key=" + API_KEY);
        sb.append("&reference=" + URLEncoder.encode(reference, "utf8"));

        URL url = new URL(sb.toString());
        conn = (HttpURLConnection) url.openConnection();
        InputStreamReader in = new InputStreamReader(conn.getInputStream());

        // Load the results into a StringBuilder
        int read;
        char[] buff = new char[1024];
        while ((read = in.read(buff)) != -1) {
            jsonResults.append(buff, 0, read);
        }
    } catch (MalformedURLException e) {
        Log.e(LOG_TAG, "Error processing Places API URL", e);
        return null;
    } catch (IOException e) {
        Log.e(LOG_TAG, "Error connecting to Places API", e);
        return null;
    } finally {
        if (conn != null) {
            conn.disconnect();
        }
    }

    Place place = null;
    try {
        // Create a JSON object hierarchy from the results
        JSONObject jsonObj = new JSONObject(jsonResults.toString()).getJSONObject("result");

        place = new Place();
        place.icon = jsonObj.getString("icon");
        place.name = jsonObj.getString("name");
        place.formatted_address = jsonObj.getString("formatted_address");
        if (jsonObj.has("formatted_phone_number")) {
            place.formatted_phone_number = jsonObj.getString("formatted_phone_number");
        }
    } catch (JSONException e) {
        Log.e(LOG_TAG, "Error processing JSON results", e);
    }

    return place;
}