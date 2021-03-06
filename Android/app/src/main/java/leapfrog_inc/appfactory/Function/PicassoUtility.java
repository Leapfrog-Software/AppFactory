package leapfrog_inc.appfactory.Function;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import leapfrog_inc.appfactory.R;

/**
 * Created by Leapfrog-Software on 2018/04/22.
 */

public class PicassoUtility {

    public static void getEngineerImage(Context context, String url, ImageView imageView) {
        if (url.length() == 0) {
            imageView.setImageResource(R.drawable.no_image);
            return;
        }
        Picasso.with(context)
                .load(url).networkPolicy(NetworkPolicy.NO_STORE)
                .noFade()
                .error(R.drawable.no_image)
                .into(imageView);
    }

    public static void getWorkImage(Context context, String url, ImageView imageView) {
        Picasso.with(context)
                .load(url).networkPolicy(NetworkPolicy.NO_STORE)
                .noFade()
                .into(imageView);
    }
}
