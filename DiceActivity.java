package armantinsaye.decisionmaker;

import android.content.Intent;
import android.media.AudioManager;
//import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.Random;


public class DiceActivity extends ActionBarActivity {

    //TODO: switch to SoundPool instead of MediaPlayer
    private ImageView dice;
    //private MediaPlayer mp; //= MediaPlayer.create(this, R.raw.dicemp3); //can't initialize here as file system isn't setup yet??
    private RotateAnimation anim;
    private Random rGen = new Random();
    private boolean firstTime = true;
    private SoundPool soundPool;
    private int diceSoundID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        diceSoundID = soundPool.load(this, R.raw.dicemp3, 1);

        //mp = MediaPlayer.create(this, R.raw.dicemp3);
        dice = (ImageView) findViewById(R.id.ivDice);
        dice.setImageResource(R.drawable.fulldice);
        dice.setRotationX(12f);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_home) {
            startActivity(new Intent(DiceActivity.this, HomeActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void diceIVOnClick(View view)
    {
        if (firstTime) {
            firstTime = false;
            anim = new RotateAnimation(0f, 540f, dice.getWidth() / 2, dice.getHeight() / 2);
            anim.setInterpolator(new LinearInterpolator());
            anim.setRepeatCount(Animation.ABSOLUTE);
            anim.setDuration(850);
        }
       // anim.setStartOffset(100);

        dice.setImageResource(R.drawable.fulldice);

        dice.setAlpha(.5f);
        dice.startAnimation(anim);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() { //otherwise this happens before animation finishes
            //which may mean animation is on a diff. thread
            @Override
            public void run() {

                switch (rGen.nextInt(6) + 1)
                {
                    case 1:
                        dice.setImageResource(R.drawable.dado1);
                        break;
                    case 2:
                        dice.setImageResource(R.drawable.dado2);
                        break;
                    case 3:
                        dice.setImageResource(R.drawable.dado3);
                        break;
                    case 4:
                        dice.setImageResource(R.drawable.dado4);
                        break;
                    case 5:
                        dice.setImageResource(R.drawable.dado5);
                        break;
                    case 6:
                        dice.setImageResource(R.drawable.dado6);
                        break;
                }
                dice.setAlpha(1f);
                soundPool.play(diceSoundID,1, 1, 1,0,1); //the last one is the rate of the sound/speed..hmm
                //mp.start();
            }
        }, 855);
    }

}