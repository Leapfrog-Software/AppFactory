package leapfrog_inc.appfactory.Http.Requester;

import android.util.Base64;

import org.json.JSONObject;

import leapfrog_inc.appfactory.Function.Constants;
import leapfrog_inc.appfactory.Http.HttpManager;

/**
 * Created by Leapfrog-Software on 2018/04/16.
 */

public class EstimateRequester {

    public static class EstimateRequestData {
        public String target;
        public String purpose;
        public String description;
        public boolean ios;
        public boolean android;
        public boolean chat;
        public boolean camera;
        public boolean movie;
        public boolean push;
        public boolean map;
        public boolean geofence;
        public boolean settle;
        public boolean credit;
        public boolean user;
        public boolean sns;
        public String notes;
        public String email;
    }

    public static void send(EstimateRequestData requestData, final EstimateRequesterCallback callback) {

        HttpManager httpManager = new HttpManager(new HttpManager.HttpCallback() {
            @Override
            public void didReceiveData(boolean result, String data) {

                if (result) {
                    try {
                        JSONObject json = new JSONObject(data);
                        if (json.getString("result").equals("0")) {
                            callback.didReceiveData(true);
                            return;
                        }
                    } catch (Exception e) {}
                }
                callback.didReceiveData(false);
            }
        });

        StringBuffer url = new StringBuffer();
        url.append(Constants.ServerApiUrl);
        url.append("?");
        url.append("command=estimate");
        url.append("&");
        url.append(("target=" + requestData.target));
        url.append("&");
        url.append(("purpose=" + new String(Base64.encode(requestData.purpose.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP))));
        url.append("&");
        url.append(("description=" + new String(Base64.encode(requestData.description.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP))));
        url.append("&");
        url.append(("ios=" + (requestData.ios ? "1" : "0")));
        url.append("&");
        url.append(("android=" + (requestData.android ? "1" : "0")));
        url.append("&");
        url.append(("chat=" + (requestData.ios ? "1" : "0")));
        url.append("&");
        url.append(("camera=" + (requestData.ios ? "1" : "0")));
        url.append("&");
        url.append(("movie=" + (requestData.ios ? "1" : "0")));
        url.append("&");
        url.append(("push=" + (requestData.ios ? "1" : "0")));
        url.append("&");
        url.append(("map=" + (requestData.ios ? "1" : "0")));
        url.append("&");
        url.append(("geofence=" + (requestData.ios ? "1" : "0")));
        url.append("&");
        url.append(("settle=" + (requestData.ios ? "1" : "0")));
        url.append("&");
        url.append(("credit=" + (requestData.ios ? "1" : "0")));
        url.append("&");
        url.append(("user=" + (requestData.ios ? "1" : "0")));
        url.append("&");
        url.append(("sns=" + (requestData.ios ? "1" : "0")));
        url.append("&");
        url.append(("notes=" + new String(Base64.encode(requestData.notes.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP))));
        url.append("&");
        url.append(("email=" + new String(Base64.encode(requestData.email.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP))));

        httpManager.execute(url.toString(), "GET", null);
    }

    public interface EstimateRequesterCallback {
        void didReceiveData(boolean result);
    }
}
