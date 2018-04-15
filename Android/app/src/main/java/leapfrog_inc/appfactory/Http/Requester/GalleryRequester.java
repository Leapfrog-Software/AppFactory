package leapfrog_inc.appfactory.Http.Requester;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import leapfrog_inc.appfactory.Function.Constants;
import leapfrog_inc.appfactory.Http.Enum.GallerySortType;
import leapfrog_inc.appfactory.Http.Enum.OrganizationType;
import leapfrog_inc.appfactory.Http.HttpManager;

/**
 * Created by Leapfrog-Software on 2018/04/16.
 */

public class GalleryRequester {

    public static class GalleryData {

        public String id;
        public String name;
        public String engineerId;
        public String engineerName;
        public String engineerArea;
        public OrganizationType engineerOrganization;
        public int cost;
        public int evaluate;

        static public GalleryData create(JSONObject json) {

            try {
                String id = json.getString("id");
                String name = json.getString("name");
                String engineerId = json.getString("engineerId");
                String engineerName = json.getString("engineerName");
                String engineerArea = json.getString("engineerArea");
                OrganizationType organization = OrganizationType.create(json.getInt("engineerOrganization"));
                int cost = json.getInt("cost");
                int evaluate = json.getInt("evaluate");

                GalleryData engineerData = new GalleryData();
                engineerData.id = id;
                engineerData.name = name;
                engineerData.engineerId = engineerId;
                engineerData.engineerName = engineerName;
                engineerData.engineerArea = engineerArea;
                engineerData.engineerOrganization = organization;
                engineerData.cost = cost;
                engineerData.evaluate = evaluate;

                return engineerData;

            } catch (Exception e) {
            }

            return null;
        }
    }

    public static class GalleryResponseData {

        public int page;
        public int total;
        public ArrayList<GalleryData> galleryList = new ArrayList<GalleryData>();

        static public GalleryResponseData create(JSONObject json) {

            try {
                GalleryResponseData data = new GalleryResponseData();
                data.page = json.getInt("page");
                data.total = json.getInt("total");

                JSONArray galleries = json.getJSONArray("galleryList");
                for (int i = 0; i < galleries.length(); i++) {
                    GalleryData galleryData = GalleryData.create(galleries.getJSONObject(i));
                    if (galleryData != null) {
                        data.galleryList.add(galleryData);
                    }
                }
                return data;

            } catch (Exception e) {}

            return null;
        }
    }

    public static void get(GallerySortType sortType, int page, final GalleryRequesterCallback callback){

        HttpManager httpManager = new HttpManager(new HttpManager.HttpCallback() {
            @Override
            public void didReceiveData(boolean result, String data) {

                if (result) {
                    try {
                        GalleryResponseData responseData = GalleryResponseData.create(new JSONObject(data));
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
        url.append("command=getgalleries");
        url.append("&");
        url.append(("sort=" + sortType.toValue()));
        url.append("&");
        url.append(("page=") + String.valueOf(page));

        httpManager.execute(url.toString(), "GET", null);
    }

    public interface GalleryRequesterCallback {
        void didReceiveData(boolean result, GalleryResponseData response);
    }
}
