package armantinsaye.decisionmaker;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


public class HomeActivity extends ActionBarActivity {


    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //dice
        final Button diceBtn = (Button) findViewById(R.id.buttonDice);
        diceBtn.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    diceBtn.setAlpha(1f);
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    diceBtn.setAlpha(.8f);
                }
                return false;
            }
        });

        //Write
        final Button writeBtn = (Button) findViewById(R.id.buttonWrite);
        writeBtn.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    writeBtn.setAlpha(1f);
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    writeBtn.setAlpha(.8f);
                }
                return false;
            }
        });

        //Coin
        final Button coinBtn = (Button) findViewById(R.id.buttonCoin);
        coinBtn.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    coinBtn.setAlpha(1f);
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    coinBtn.setAlpha(.8f);
                }
                return false;
            }
        });

        final Button rndBtn = (Button) findViewById(R.id.btnRGen);
        rndBtn.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    rndBtn.setAlpha(1f);
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    rndBtn.setAlpha(.8f);
                }
                return false;
            }
        });

    }

    public void diceOnClick(View view)
    {
        startActivity(new Intent(HomeActivity.this, DiceActivity.class));
    }

    public void buttonWriteOnClick(View view)
    {
        startActivity(new Intent(HomeActivity.this, MainActivity.class));
    }

    public void btnCoinOnClick(View view)
    {
        startActivity(new Intent(HomeActivity.this, CoinActivity.class));
    }


    public void btnRGenOnClick(View view)
    {
        startActivity(new Intent(HomeActivity.this, RandomNumberActivity.class));
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_home, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
    }

