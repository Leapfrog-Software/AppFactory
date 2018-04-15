package leapfrog_inc.appfactory.Http.Requester;

import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import leapfrog_inc.appfactory.Function.Constants;
import leapfrog_inc.appfactory.Http.Enum.CostType;
import leapfrog_inc.appfactory.Http.Enum.OrganizationType;
import leapfrog_inc.appfactory.Http.Enum.WorksType;
import leapfrog_inc.appfactory.Http.HttpManager;

/**
 * Created by Leapfrog-Software on 2018/04/16.
 */

public class EngineerDetailRequester {

    public static class EngineerDetailWorkData {

        public String id;
        public String name;
        public String os;
        public String language;
        public String function;
        public int cost;
        public int evaluate;
        public boolean source;

        static public EngineerDetailWorkData create(JSONObject json) {

            try {
                String id = json.getString("id");
                String name = json.getString("name");
                String os = json.getString("os");
                String language = json.getString("language");
                String function = json.getString("function");
                int cost = json.getInt("cost");
                int evaluate = json.getInt("evaluate");
                boolean source = json.getBoolean("source");

                EngineerDetailWorkData data = new EngineerDetailWorkData();
                data.id = id;
                data.name = name;
                data.os = os;
                data.language = language;
                data.function = function;
                data.cost = cost;
                data.evaluate = evaluate;
                data.source = source;

                return data;

            } catch(Exception e) {}

            return null;
        }
    }

    public static class EngineerDetailResponseData {

        public String id;
        public String name;
        public String area;
        public OrganizationType organization;
        public int evaluate;
        public String profile;
        public ArrayList<EngineerDetailWorkData> works = new ArrayList<EngineerDetailWorkData>();

        static public EngineerDetailResponseData create(JSONObject json) {

            try {
                EngineerDetailResponseData data = new EngineerDetailResponseData();
                data.id = json.getString("id");
                data.name = json.getString("name");
                data.area = json.getString("area");
                data.organization = OrganizationType.create(json.getInt("organization"));
                data.evaluate = json.getInt("evaluate");
                data.profile = json.getString("profile");

                JSONArray works = json.getJSONArray("works");
                for (int i = 0; i < works.length(); i++) {
                    EngineerDetailWorkData workData = EngineerDetailWorkData.create(works.getJSONObject(i));
                    if (workData != null) {
                        data.works.add(workData);
                    }
                }
                return data;

            } catch (Exception e) {}

            return null;
        }
    }

    public static void get(String id, final EngineerDetailRequesterCallback callback){

        HttpManager httpManager = new HttpManager(new HttpManager.HttpCallback() {
            @Override
            public void didReceiveData(boolean result, String data) {

                if (result) {
                    try {
                        EngineerDetailResponseData responseData = EngineerDetailResponseData.create(new JSONObject(data));
                        if (responseData != null) {
                            callback.didReceiveData(true, responseData);
                            return;
                        }
                    } catch (Exception e) {}
                }
                callback.didReceiveData(false, null);
            }
        });

        StringBuffer url = new StringBuffer();
        url.append(Constants.ServerApiUrl);
        url.append("?");
        url.append("command=getengineerdetail");
        url.append("&");
        url.append(("id=" + id));

        httpManager.execute(url.toString(), "GET", null);
    }

    public interface EngineerDetailRequesterCallback {
        void didReceiveData(boolean result, EngineerDetailResponseData response);
    }
}

