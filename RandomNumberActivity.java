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
import android.widget.Toast;

import java.util.Random;


public class RandomNumberActivity extends ActionBarActivity {

    private EditText lowBowBoundsET;
    private EditText upperBoundsET;
    private TextView answerTV;
    private InputMethodManager inputManager;
    private Context context;
    private Random rGen = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_number);

       //RelativeLayout rLayout = (RelativeLayout) findViewById(R.id.random_layout);
       lowBowBoundsET = (EditText) findViewById(R.id.etLow);
       upperBoundsET = (EditText) findViewById(R.id.etUpper);
       answerTV = (TextView) findViewById(R.id.tvAnswer);

       inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        context = getApplicationContext();


        final Button decideButton = (Button) findViewById(R.id.btnRandom);
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

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_random_number, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        if (id == R.id.action_home) {
            startActivity(new Intent(RandomNumberActivity.this, HomeActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

  public void btnRandomOnClick(View view) {
      String inputLow = lowBowBoundsET.getText().toString();
      String inputHigh = upperBoundsET.getText().toString();

      double low = 0;
      double high = 0;

      if (inputHigh.length() > 0 && inputLow.length() > 0) //check is needed in case the strings are empty parseInt will bug
      {
          if (inputLow.length() < 10 && inputHigh.length() < 10)
          {
              low = Integer.parseInt(inputLow);
              high = Integer.parseInt(inputHigh);

              if (low >= high) {
                  Toast.makeText(context, "lower must be < upper!", Toast.LENGTH_SHORT).show();
              }
              else
              {
                  setAnswer(low, high);
              }
          }
          else
          {
              Toast.makeText(context, "#s must be < 10 digits!", Toast.LENGTH_SHORT).show();
          }
      }

      if (getCurrentFocus() != null) //Return the view in this Window that currently has focus,
      // or null if there are none. Note that this does not look in any containing Window.
      {
          inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                  InputMethodManager.HIDE_NOT_ALWAYS);
      }

  }

    private void setAnswer(double low, double high)
    {
      double answer = rGen.nextInt((int)high-(int)low + 1) + low; //need +1 bc nextInt is exclusive
      answerTV.setText(Integer.toString((int)answer));
    }


    public void layOutOnClick(View view)
    {

        if (getCurrentFocus() != null) //Return the view in this Window that currently has focus,
        // or null if there are none. Note that this does not look in any containing Window.
        {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void highOnClick(View view)
    {
        inputManager.showSoftInput(upperBoundsET, InputMethodManager.SHOW_IMPLICIT);
    }
  }

//TODO make it work for decimals and negatives