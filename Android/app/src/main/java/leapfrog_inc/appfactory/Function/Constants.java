package leapfrog_inc.appfactory.Function;

/**
 * Created by Leapfrog-Software on 2018/04/15.
 */

public class Constants {

    public static String ServerRootUrl = "http://10.0.2.2/appfac/";
    public static String ServerApiUrl = Constants.ServerRootUrl + "srv.php";
    public static String ServerEngineerImageDirectory = Constants.ServerRootUrl + "data/img/user/";

    public static int HttpConnectTimeout = 10000;
    public static int HttpReadTimeout = 10000;

    public static class SharedPreferenceKey {
        public static String Key = "AppFactory";
        public static String didInitialized = "DidInitialized";
    }
}
