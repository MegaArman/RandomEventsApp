package armantinsaye.decisionmaker;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class CoinActivity extends ActionBarActivity {
    private  ImageView coinIV;
    private Random rGen = new Random();
    private float pos = 0;
    private Timer t;
    private boolean wasClicked = false;
   // private MediaPlayer mp; //= MediaPlayer.create(this, R.raw.dicemp3); //can't initialize here as file system isn't setup yet??
    private boolean side = true; //true if heads false if tails
    private int clock = 0;
    private short increment = 50; // in ms
    private int totalTime; //also can be thought of as number of frames total
    private float scale = .9f;
    private float oldPos = 0;
    private SoundPool soundPool;
    private int coinSoundId;

    // RotateAnimation anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin);

        coinIV = (ImageView) findViewById(R.id.ivCoin);
        coinIV.setImageResource(R.drawable.heads);
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        coinSoundId = soundPool.load(this, R.raw.coindropmp3, 1);

       // mp = MediaPlayer.create(this, R.raw.coindropmp3);
        resetImage(coinIV);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_coin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_home) {
            startActivity(new Intent(CoinActivity.this, HomeActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void coinIVOnClick(View view)
    {

        if (!wasClicked)
        {
            resetFields();
            coinIV.setAlpha(.5f);

            if (side)
            {
                side = false;
                coinIV.setImageResource(R.drawable.tails);
            }
            t = new Timer();
            t.scheduleAtFixedRate(
                    new TimerTask() {
                        public void run() {

                            setImage();
                            clock+= increment;

                            if (clock >= totalTime)
                            {
                                //  wasClicked = false;
                                t.cancel();
                                t.purge();
                              //  mp.start();
                                soundPool.play(coinSoundId,1, 1, 1,0,1);

                                runOnUiThread(new Runnable() { //need this line as android only lets the threads that created UI update the UI
                                    //and timers are by default on a seperate thread
                                    @Override
                                    public void run() {
                                        wasClicked = false;
                                        resetImage(coinIV);

                                    }
                                });
                            }

                        }

                    },
                    0,      // run first occurrence after 0 ms
                    increment); // TODO can make this random for coin flip
        }

    }

    private void setImage() {
        runOnUiThread(new Runnable() { //need this line as android only lets the threads that created UI update the UI
            //and timers are by default on a seperate thread
            @Override
            public void run() {
                pos = pos + 15;
                coinIV.setRotationX(pos);

                if (clock < totalTime/2)
                {
                    scale = scale + .018f;
                }
                else
                {
                    scale = scale - .018f;
                }

                coinIV.setScaleY(scale);
                coinIV.setScaleX(scale);

                if (pos >= oldPos + 90)
                {
                    oldPos = pos;

                    if (side)
                    {
                        coinIV.setImageResource(R.drawable.tails);
                        side = false;
                    }
                    else{
                        coinIV.setImageResource(R.drawable.heads);
                        side = true;
                    }
                    //   pos = 0;
                }
                //    coinIV.setRotationX(25f);
            }
        });
    }

    private void resetImage(ImageView imageView)
    {
        scale = .5f;
        imageView.setAlpha(1f);
        imageView.setRotationX(0f);
        imageView.animate();
    }

    private void resetFields()
    {
        wasClicked = true;
        pos = 0;
        clock = 0;
        oldPos = 0;
        totalTime = (rGen.nextInt(32) + 16)*increment;
        // even and odds will have equal chance
    }

}