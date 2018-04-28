package leapfrog_inc.appfactory.Fragment.Gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import leapfrog_inc.appfactory.Fragment.BaseFragment;
import leapfrog_inc.appfactory.Http.Enum.GallerySortType;
import leapfrog_inc.appfactory.R;

/**
 * Created by Leapfrog-Software on 2018/04/28.
 */

public class GallerySortFragment extends BaseFragment {

    private GallerySortType mDefaultSortType;
    private GallerySortFragmentCallback mCallback;

    public void set(GallerySortType sortType, GallerySortFragmentCallback callback) {
        mDefaultSortType = sortType;
        mCallback = callback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View view = inflater.inflate(R.layout.fragment_gallery_sort, null);

        initContents(view, mDefaultSortType);
        initAction(view);

        return view;
    }

    private void initContents(View view, GallerySortType sortType) {

        if (sortType == GallerySortType.evaluate) {
            view.findViewById(R.id.evaluateSelectedImageView).setVisibility(View.VISIBLE);
            view.findViewById(R.id.costSelectedImageView).setVisibility(View.INVISIBLE);
        } else {
            view.findViewById(R.id.evaluateSelectedImageView).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.costSelectedImageView).setVisibility(View.VISIBLE);
        }
    }

    private void initAction(View view) {

        ((Button)view.findViewById(R.id.closeButton1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close(null);
            }
        });

        ((Button)view.findViewById(R.id.closeButton2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close(null);
            }
        });

        ((Button)view.findViewById(R.id.evaluateButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initContents(getView(), GallerySortType.evaluate);
                close(GallerySortType.evaluate);
            }
        });

        ((Button)view.findViewById(R.id.costButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initContents(getView(), GallerySortType.cost);
                close(GallerySortType.cost);
            }
        });
    }

    private void close(final GallerySortType sortType) {

        View view = getView();
        if (view == null) return;

        AlphaAnimation animation = new AlphaAnimation(1, 0);
        animation.setDuration(200);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                if (sortType != null) {
                    mCallback.didSelect(sortType);
                }
                popFragment(AnimationType.none);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        view.startAnimation(animation);
    }

    public interface GallerySortFragmentCallback {
        void didSelect(GallerySortType sortType);
    }
}
