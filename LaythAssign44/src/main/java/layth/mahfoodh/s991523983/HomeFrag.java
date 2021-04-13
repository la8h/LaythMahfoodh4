/*
Layth Mahfoodh s991523983 PROG38448
 */

package layth.mahfoodh.s991523983;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeFrag extends Fragment {
    // Remove the below line after defining your own ad unit ID.
    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";

    private static final int START_LEVEL = 1;
    private int mLevel;
    private Button mNextLevelButton;
    private InterstitialAd mInterstitialAd;
    private TextView mLevelTextView;

//add date
    private TextView dateTime;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_home, container, false);







    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dateTime = (TextView) view.findViewById(R.id.date_time);
/*
 Thread t = new Thread() {

    @Override
    public void run() {

        try{
         while(!isInterrupted()) {
             Thread.sleep(1000);

             getActivity().runOnUiThread(new Runnable() {
                 @Override
                 public void run() {

*/
                     //add date
                  //   dateTime = (TextView) view.findViewById(R.id.date_time);
                     calendar = Calendar.getInstance();

                     long date = System.currentTimeMillis();

                     dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");

                     String dateDisplay = dateFormat.format(date);
                     //  date = dateFormat.format(calendar.getTime());
                     dateTime.setText(dateDisplay);
        {
 /*
             });
         }
              } catch (InterruptedException e) {
        }
    }
};
   t.start();
*/

        }


        // Create the next level button, which tries to show an interstitial when clicked.
        mNextLevelButton = view.findViewById(R.id.next_level_button);

        // Create the text view to show the level number.
        mLevelTextView = view.findViewById(R.id.tvLayth);
        mLevel = START_LEVEL;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() == null || getActivity().getApplicationContext() == null) return;
        final Context appContext = getActivity().getApplicationContext();

        /*
        mNextLevelButton.setEnabled(false);
        mNextLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInterstitial(appContext);
            }
          */


//*        });

/*
        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
        mInterstitialAd = newInterstitialAd(appContext);
        loadInterstitial();
        // Toasts the test ad message on the screen.
        // Remove this after defining your own ad unit ID.
   //     Toast.makeText(appContext, TOAST_TEXT, Toast.LENGTH_LONG).show();
*/








    }



    /*
    private InterstitialAd newInterstitialAd(final Context context) {
        InterstitialAd interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(getString(R.string.my_name));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mNextLevelButton.setEnabled(true);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                mNextLevelButton.setEnabled(true);
            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
                goToNextLevel(context);
            }
        });
        return interstitialAd;
    }

    private void showInterstitial(Context context) {
        // Show the ad if it"s ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(context, "Ad did not load", Toast.LENGTH_SHORT).show();
            goToNextLevel(context);
        }
    }

    private void loadInterstitial() {
        // Disable the next level button and load the ad.
        mNextLevelButton.setEnabled(false);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void goToNextLevel(Context context) {
        // Show the next level and reload the ad to prepare for the level after.
        mLevelTextView.setText(context.getString(R.string.level_text, ++mLevel));
        mInterstitialAd = newInterstitialAd(context);
        loadInterstitial();
    }

    */


}