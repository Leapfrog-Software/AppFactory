package leapfrog_inc.appfactory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import leapfrog_inc.appfactory.Fragment.FragmentController;
import leapfrog_inc.appfactory.Fragment.Splash.SplashFragment;
import leapfrog_inc.appfactory.Function.SaveData;

public class MainActivity extends AppCompatActivity {

    private FragmentController mFragmentController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SaveData.getInstance().initialize(this);

        mFragmentController = new FragmentController();
        mFragmentController.initialize(getSupportFragmentManager(), R.id.container);
        mFragmentController.stack(new SplashFragment(), FragmentController.AnimationType.none);
    }

    public FragmentController getFragmentController() {
        return mFragmentController;
    }
}
