package iss.workshop.ca;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    boolean checkCard = false;
    ImageView openedCard;
    CardObject openedObject;
    List<CardObject> gameCards = new ArrayList<>();
    Integer numMatches = 0;
    int elapsedMillis;
    int elapsedSeconds;
    MediaPlayer bgSoundMP;
    MediaPlayer correctSoundMP;
    MediaPlayer wrongSoundMP;
    private List<ImageView> cards = new ArrayList<>();
    private static final int w = 300;
    private static final int h = 300;
    Chronometer timerChronometer;
    private long recordingTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // retrieve list of 6 URLs from MainActivity
        Intent intent = getIntent();
        ArrayList<String> selectedImagesURL = (ArrayList<String>) intent.getSerializableExtra("imgSelectedUrl");

        // add URLs to each object
        String url1 = selectedImagesURL.get(0);
        CardObject cardObj1 = new CardObject(url1, 1);

        String url2 = selectedImagesURL.get(1);
        CardObject cardObj2 = new CardObject(url2,2);

        String url3 = selectedImagesURL.get(2);
        CardObject cardObj3 = new CardObject(url3,3);

        String url4 = selectedImagesURL.get(3);
        CardObject cardObj4 = new CardObject(url4,4);

        String url5 = selectedImagesURL.get(4);
        CardObject cardObj5 = new CardObject(url5,5);

        String url6 = selectedImagesURL.get(5);
        CardObject cardObj6 = new CardObject(url6,6);

        //Implement Sounds
        bgSoundMP = MediaPlayer.create(this, R.raw.bg);
        correctSoundMP = MediaPlayer.create(this, R.raw.correct);
        wrongSoundMP = MediaPlayer.create(this, R.raw.wrong);

        //Implement Timer
        timerChronometer = (Chronometer)findViewById(R.id.chronometer);
        timerChronometer.setOnChronometerTickListener(cArg -> {
            if(numMatches == 6){
                bgSoundMP.stop();
                timerChronometer.stop();
                elapsedMillis = (int) (SystemClock.elapsedRealtime() - timerChronometer.getBase());
                elapsedSeconds = (int) (elapsedMillis / 1000);
                AlertDialog.Builder dlg = new AlertDialog.Builder(GameActivity.this)
                        .setTitle(R.string.gameComplete)
                        .setMessage("You took " + elapsedSeconds + " seconds to complete the game!")
                        .setPositiveButton(R.string.backToHome,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent1 = new Intent(GameActivity.this, MainActivity.class);
                                        startActivity(intent1);
                                    }
                                });

                dlg.show();

            }
        });

        //Start Background Music
        bgSoundMP.setLooping(true);
        bgSoundMP.start();

        //Stat Timer
        timerChronometer.setBase(SystemClock.elapsedRealtime());
        timerChronometer.start();
        timerChronometer.setFormat("Elapsed Time : %s");

        // create list of 12 objects
        gameCards.add(cardObj1);
        gameCards.add(cardObj2);
        gameCards.add(cardObj3);
        gameCards.add(cardObj4);
        gameCards.add(cardObj5);
        gameCards.add(cardObj6);
        gameCards.add(cardObj1);
        gameCards.add(cardObj2);
        gameCards.add(cardObj3);
        gameCards.add(cardObj4);
        gameCards.add(cardObj5);
        gameCards.add(cardObj6);

        // shuffle gameCards (List of CardObject)
        Collections.shuffle(gameCards);

        setUpCards();

    }

    protected void setUpCards() {
        int[] card_ids = {
                R.id.card1, R.id.card2, R.id.card3, R.id.card4, R.id.card5, R.id.card6,
                R.id.card7, R.id.card8, R.id.card9, R.id.card10, R.id.card11, R.id.card12
        };

        // initialize numMatches
        TextView matched = findViewById(R.id.numMatches);
        matched.setText(numMatches.toString() + " of 6 matches");


        for (int i = 0; i < card_ids.length; i++) {
            ImageView card = findViewById(card_ids[i]);
            int index = i;

            String coverImage = "https://cdn.stocksnap.io/img-thumbs/960w/architecture-building_I0PMKBPL2N.jpg";
            Picasso.get().load(coverImage).resize(w,h).into(card);

            if (card != null) {
                card.setOnClickListener(v -> {
                    if (card.getTag()=="opened") {
                        return;
                    } else if (!checkCard) {
                        card.setTag("opened");
                        String url = gameCards.get(index).getUrl();
                        Picasso.get().load(url).resize(w,h).into(card);
                        openedCard = card;
                        openedObject = gameCards.get(index);
                        checkCard = true;
                    } else {
                        if(!matchCards(gameCards.get(index))) {
                            openedCard.setTag(null);
                            card.setTag(null);
                            String url = gameCards.get(index).getUrl();
                            Picasso.get().load(url).resize(w,h).into(card);

                            card.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Picasso.get().load(coverImage).resize(w,h).into(card);
                                }
                            }, 1000);
                            wrongSoundMP.start();
                            Picasso.get().load(coverImage).resize(w,h).into(openedCard);
                            Animation showCard = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animate_card);
                            openedCard.startAnimation(showCard);
                        } else {
                            card.setTag("opened");
                            String url = gameCards.get(index).getUrl();
                            Picasso.get().load(url).resize(w,h).into(card);
                            numMatches++;
                            correctSoundMP.start();
                            matched.setText(numMatches.toString() + " of 6 matches");
                        }
                        openedCard = null;
                        openedObject = null;
                        checkCard = false;
                    }
                });
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        bgSoundMP.pause();
        recordingTime = SystemClock.elapsedRealtime()- timerChronometer.getBase();

    }

    @Override
    protected void onRestart(){
        super.onRestart();
        bgSoundMP.start();
        timerChronometer.setBase(SystemClock.elapsedRealtime() - recordingTime);
        timerChronometer.start();
    }


    public boolean matchCards(CardObject cardObj)
    {
        if (cardObj.getId() == openedObject.getId())
            return true;
        else
            return false;
    }

}
