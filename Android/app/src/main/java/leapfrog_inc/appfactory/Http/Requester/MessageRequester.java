package leapfrog_inc.appfactory.Http.Requester;

import android.util.Base64;

import org.json.JSONObject;

import leapfrog_inc.appfactory.Function.Constants;
import leapfrog_inc.appfactory.Http.HttpManager;

/**
 * Created by Leapfrog-Software on 2018/04/16.
 */

public class MessageRequester {

    public static void send(String sender, String target, String message, final MessageRequesterCallback callback) {

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
        url.append("command=sendmessage");
        url.append("&");
        url.append(("sender=" + new String(Base64.encode(sender.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP))));
        url.append("&");
        url.append(("target=" + target));
        url.append("&");
        url.append(("message=" + new String(Base64.encode(message.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP))));

        httpManager.execute(url.toString(), "GET", null);
    }

    public interface MessageRequesterCallback {
        void didReceiveData(boolean result);
    }
}
