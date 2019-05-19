package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bullach.android.androidjokes.JokeActivity;
import com.bullach.android.lib.JavaJokes;


public class MainActivity extends AppCompatActivity {

    // this idling resource will be used by Espresso to wait for and synchronize with Network call
    CountingIdlingResource espressoTestIdlingResource = new CountingIdlingResource("Network_Call");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        JavaJokes jokes = new JavaJokes();

        Toast.makeText(this, jokes.generateJoke(), Toast.LENGTH_SHORT).show();
    }

    public void tellAndroidJoke(View view) {
        // increment idling resource for telling Espresso wait for the network call
        espressoTestIdlingResource.increment();

        new EndpointsAsyncTask(new EndpointsAsyncTask.TaskCompleteListener() {
            @Override
            public void onTaskComplete(String result) {
                Intent intent = new Intent(MainActivity.this, JokeActivity.class);
                intent.putExtra(getString(R.string.jokeExtra), result);
                startActivity(intent);
            }
        }).execute(this);

        // decrement idling resource to tell Espresso that the Network call has been completed
        espressoTestIdlingResource.decrement();
    }

    /**
     * @return MainActvity's idling resource for Espresso testing
     */
    public CountingIdlingResource getEspressoIdlingResourceForMainActivity() {
        return espressoTestIdlingResource;
    }
}
