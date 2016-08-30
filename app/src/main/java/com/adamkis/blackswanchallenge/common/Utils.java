package com.adamkis.blackswanchallenge.common;

import android.util.Log;
import android.view.View;

/**
 * Created by akis on 30/08/16.
 */
public class Utils {

    public static final String LOG_TAG = "BlackSwan";

    public static void log(String string) {
        longLog(LOG_TAG, string);
    }

    public static void longLog( String string){
        longLog(LOG_TAG, string);
    }

    public static void longLog(String tag, String string){
        StringBuffer sb = new StringBuffer(string);
        if (sb.length() > 4000) {
            int chunkCount = sb.length() / 4000;
            for (int i = 0; i <= chunkCount; i++) {
                int max = 4000 * (i + 1);
                if (max >= sb.length()) {
                    log(tag, sb.substring(4000 * i));
                } else {
                    log(tag, sb.substring(4000 * i, max));
                }
            }
        } else {
            log(tag, sb.toString());
        }
    }

    public static void log(String tag, String string) {
        Log.i(tag, string);
    }


    public static void crossfadeViews(View firstView, View secondView, boolean showFirst, boolean animate, int animationDurationMillis){

        float loadingAlpha;
        float contentAlpha;

        if( showFirst ){
            loadingAlpha = 1f;
            contentAlpha = 0f;
        }
        else{
            loadingAlpha = 0f;
            contentAlpha = 1f;
        }

        if( firstView != null && firstView.getAlpha() != loadingAlpha ){
            if( animate ) {
                firstView.animate()
                        .alpha(loadingAlpha)
                        .setDuration(animationDurationMillis)
//                        .setInterpolator(new DecelerateInterpolator())
                        .setListener(null);
            }
            else{
                firstView.setAlpha(loadingAlpha);
            }
        }
        if( secondView != null && secondView.getAlpha() != contentAlpha ) {
            if( animate ) {
                secondView.animate()
                        .alpha(contentAlpha)
                        .setDuration(animationDurationMillis)
//                        .setInterpolator(new AccelerateDecelerateInterpolator())
                        .setListener(null);
            }
            else{
                secondView.setAlpha(contentAlpha);
            }
        }

    }

    private static final int loadingAnimationDurationMillis = 1200;

    public static void hideLoadingAnimated( View loading, View container ){
        crossfadeViews(loading, container, false, true, loadingAnimationDurationMillis);
    }

    public static void showLoadingImmediate( View loading, View container ){
        crossfadeViews(loading, container, true, false, 0);
    }


    public static void revealViewAnimated( View view ){
        crossfadeViews(view, null, true, true, loadingAnimationDurationMillis);
    }


}
