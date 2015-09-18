package armantinsaye.decisionmaker;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.*;


public class MainActivity extends ActionBarActivity {

//    @Override
//    public void onBackPressed() {
//        //overrides so back button does nothing for this activity
//    }

    ArrayList<String> decisionList;
    //-----------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        decisionList = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //to make buttons light up:
        final Button addButton = (Button) findViewById(R.id.buttonAdd);
        addButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    addButton.setAlpha(1f);
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    addButton.setAlpha(.7f);
                }
                return false;
            }

        });

        final Button decideButton = (Button) findViewById(R.id.buttonDecide);
        decideButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    decideButton.setAlpha(1f);
            } else
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    decideButton.setAlpha(.7f);
                }
                return false;
            }
        });

        final Button resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    resetButton.setAlpha(1f);
                } else
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    resetButton.setAlpha(.7f);
                }
                return false;
            }
        });
    }




        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

   public void decisionOnClick(View view)
   {
       InputMethodManager inputManager = (InputMethodManager)
               getSystemService(Context.INPUT_METHOD_SERVICE);

       if (getCurrentFocus() != null)
       inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
               InputMethodManager.HIDE_NOT_ALWAYS);

       if (decisionList.size() > 0)
       {
           Random rand = new Random();
           int decisionIndex = rand.nextInt(decisionList.size());
           ((TextView) findViewById(R.id.tvDecision)).setText(decisionList.get(decisionIndex));
       }
   }

    public void addButtonOnClick(View view) {
        // Button decide = (Button)view;
        String input = ((EditText) findViewById(R.id.evDecisions)).getText().toString();
        if (input.length() > 0)
        {
            decisionList.add(input);
            ((TextView) findViewById(R.id.tvCounter)).setText(Integer.toString(decisionList.size()));
            ((EditText) findViewById(R.id.evDecisions)).setText("");
        }
    }
    public void resetButtonOnClick(View view)
    {
        decisionList.clear();
        ((EditText)findViewById(R.id.evDecisions)).setText("");
        ((TextView)findViewById(R.id.tvDecision)).setText("");
        ((TextView)findViewById(R.id.tvCounter)).setText("0");
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        if (id == R.id.action_home) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}