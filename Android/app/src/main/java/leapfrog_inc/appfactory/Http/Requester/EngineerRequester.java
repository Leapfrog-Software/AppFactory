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

public class EngineerRequester {

    public static class EngineerData {

        public String id;
        public String name;
        public String area;
        public OrganizationType organization;
        public String profile;
        public int works;
        public int evaluate;

        static public EngineerData create(JSONObject json) {

            try {
                String id = json.getString("id");
                String name = json.getString("name");
                String area = json.getString("area");
                OrganizationType organization = OrganizationType.create(json.getInt("organization"));
                String profile = json.getString("profile").replace("<br>", "\n");
                int works = json.getInt("work");
                int evaluate = json.getInt("evaluate");

                EngineerData engineerData = new EngineerData();
                engineerData.id = id;
                engineerData.name = name;
                engineerData.area = area;
                engineerData.organization = organization;
                engineerData.profile = profile;
                engineerData.works = works;
                engineerData.evaluate = evaluate;

                return engineerData;

            } catch(Exception e) {}

            return null;
        }
    }

    public static class EngineerResponseData {

        public int page;
        public int total;
        public ArrayList<EngineerData> engineerList = new ArrayList<EngineerData>();

        static public EngineerResponseData create(JSONObject json) {

            try {
                EngineerResponseData data = new EngineerResponseData();
                data.page = json.getInt("page");
                data.total = json.getInt("total");

                JSONArray engineers = json.getJSONArray("engineers");
                for (int i = 0; i < engineers.length(); i++) {
                    EngineerData engineerData = EngineerData.create(engineers.getJSONObject(i));
                    if (engineerData != null) {
                        data.engineerList.add(engineerData);
                    }
                }
                return data;

            } catch (Exception e) {}

            return null;
        }
    }

    public static void get(int page, String keyword, OrganizationType organization, CostType cost, WorksType work, final EngineerRequesterCallback callback){

        HttpManager httpManager = new HttpManager(new HttpManager.HttpCallback() {
            @Override
            public void didReceiveData(boolean result, String data) {

                if (result) {
                    try {
                        EngineerResponseData responseData = EngineerResponseData.create(new JSONObject(data));
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
        url.append("command=getengineers");
        url.append("&");
        url.append(("page=" + String.valueOf(page)));
        url.append("&");
        url.append(("keyword=" + new String(Base64.encode(keyword.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP))));
        url.append("&");
        url.append(("organization=") + organization.toValue());
        url.append("&");
        url.append(("cost=" + cost.toValue()));
        url.append("&");
        url.append(("work=" + work.toValue()));

        httpManager.execute(url.toString(), "GET", null);
    }

    public interface EngineerRequesterCallback {
        void didReceiveData(boolean result, EngineerResponseData response);
    }
}
