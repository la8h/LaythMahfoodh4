package layth.mahfoodh.s991523983;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
/*
Layth Mahfoodh s991523983 PROG38448
 */

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

//import layth.mahfoodh.s991523983.ui.login.LoginFragment;

public class LaythActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set default fragment
        loadFragment(new HomeFrag());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // to have the drawer sliding when click on ic_menu

        // if comment out these 3 lines, your drawer is not pulled from action bar!
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        // check what happens if you comment out line below (change to Up Key)
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        //display in short period of time
                        Toast.makeText(getApplicationContext(), menuItem.getTitle(),
                                Toast.LENGTH_LONG).show();

                        // Launch the corresponding fragment
                        Fragment fragment = null;
                        Class fragmentClass = null;
                        switch (menuItem.getItemId()) {
                            case R.id.nav_camera: // display ad
                                fragmentClass = HomeFrag.class;
                                break;
                            case R.id.nav_gallery: // canada
                                fragmentClass = DownloadFrag.class;
                                break;
                            case R.id.nav_manage: // Map
                                fragmentClass = WebServiceFrag.class;
                                break;
                            default:
             //                   fragmentClass = LoginFragment.class;
                        }

                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // Insert the fragment by replacing any existing fragment
                       loadFragment(fragment);


                        return true;
                    }
                });


        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );



    }

    // When main drawer is opened
    // when press on 3 lines, what to do
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mDrawerLayout.openDrawer(GravityCompat.START);

        switch (item.getItemId()) {
            case android.R.id.home: // START to slide from left
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;


        }
        return super.onOptionsItemSelected(item);


    }

    public void open(){

        Toast.makeText(this, "You selected Speed up!!", Toast.LENGTH_LONG).show();

    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flContent, fragment);
        transaction.commit();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the tools bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle tools bar item clicks here. The tool bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
        {
            case R.id.start:
                //displayTextView.setText("You selected start!");
                Toast.makeText(this, "You selected start!", Toast.LENGTH_LONG).show();
                break;
            case R.id.accel:
                Toast.makeText(this, "You selected Speed up!!", Toast.LENGTH_LONG).show();
                break;
            case R.id.stop: Toast.makeText(this, "You selected stop!", Toast.LENGTH_LONG).show();
                break;
            case R.id.decel:
                Toast.makeText(this, "You selected Slow Down!", Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

*/


    public void callIntent(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.start:
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.cbc.ca"));
                startActivity(intent);
                break;
            case R.id.accel:
                intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:(416)289-5000"));
                startActivity(intent);
                break;
            case R.id.decel:
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.cp24.ca"));

                startActivity(intent);
            default:
                break;
        }
    }


    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        // create alert dialog

        AlertDialog.Builder builder = new AlertDialog.Builder(LaythActivity.this);
        builder.setTitle("Do you want to exit?");


        builder.setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user pressed "yes", then he is allowed to exit from application
                        finish();
                    }

                    /*
                    public void onClick(DialogInterface dialog, int id) {

                        //display in short period of time
                        Toast.makeText(getApplicationContext(), "Yes is clicked",
                                Toast.LENGTH_LONG).show();

                    } */
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();

                        //display in short period of time
                        Toast.makeText(getApplicationContext(), "No is clicked",
                                Toast.LENGTH_LONG).show();
                    }
                });


// create alert dialog
        AlertDialog alertDialog = builder.create();




        alertDialog.show();

      //  builder.show();
    }





}
