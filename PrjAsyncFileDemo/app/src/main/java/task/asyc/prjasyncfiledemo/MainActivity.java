package task.asyc.prjasyncfiledemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ProgressBar loadingBar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        loadingBar = (ProgressBar) findViewById(R.id.progressBar);

    }
    //************************************************************************
    public void buttonClick(View v)
    {
        async obj = new async();
        obj.execute();
    }
    //************************************************************************
    public class async extends AsyncTask<String, Void, String>
    {
        public ArrayList readDate(String nameOfTextFile)
        {
            //Reads from a text file located in the assets folder
            ArrayList<String> objData = new ArrayList<>();
            BufferedReader objRead = null;
            String textFileData = "";
            String line;
            try {
                objRead = new BufferedReader(new InputStreamReader(getApplication().getAssets().open(nameOfTextFile)));

                while((line = objRead.readLine()) != null)
                {
                    objData.add(splitLine(line,objData));
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if (objRead != null)
                    {
                        objRead.close();
                    }
                }
                catch (IOException E)
                {
                    E.printStackTrace();
                }
            }
            return objData;
        }
        //*****************************************************************************************
        private String splitLine(String line, ArrayList obj)
        {
            //Split a string parameter and format a new string, putting each value on a new line
            String [] arrLine = line.split(",");
            String speratedLine = "";

            for(int count = 0 ; count < arrLine.length ; count++)
            {
                obj.add(arrLine[count]);
            }

            return speratedLine;
        }
        //*****************************************************************************************
        public int randomNum(int maxNum)
        {
            Random ranNum = new Random();
            int number = ranNum.nextInt(maxNum);
            return number;
        }
        //*****************************************************************************************
        @Override
        protected String doInBackground(String... params) {
            ArrayList objNames = readDate("names");
            int randomNumber = randomNum(objNames.size());
            String name = objNames.get(randomNumber).toString();
            return name;
        }
        //*****************************************************************************************
        @Override
        protected void onPreExecute() {
            loadingBar.setVisibility(View.VISIBLE);
        }
        //*****************************************************************************************
        @Override
        protected void onPostExecute(String s) {
            loadingBar.setVisibility(View.GONE);
            TextView nametv = (TextView) findViewById(R.id.randomWord);
            nametv.setText(s);
        }
    }
    //************************************************************************
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
}
