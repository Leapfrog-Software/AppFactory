package leapfrog_inc.appfactory.Function;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Leapfrog-Software on 2018/04/15.
 */

public class SaveData {

    private static SaveData container = null;

    public Context mContext;
    public boolean didInitialized = false;

    private SaveData(){}

    public static SaveData getInstance(){
        if(container == null){
            container = new SaveData();
        }
        return container;
    }

    public void initialize(Context context) {

        mContext = context;

        SharedPreferences data = context.getSharedPreferences(Constants.SharedPreferenceKey.Key, Context.MODE_PRIVATE);

        didInitialized = data.getBoolean(Constants.SharedPreferenceKey.didInitialized, false);
    }

    public void save() {

        SharedPreferences data = mContext.getSharedPreferences(Constants.SharedPreferenceKey.Key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();

        editor.putBoolean(Constants.SharedPreferenceKey.didInitialized, didInitialized);

        editor.apply();
    }
}
