package com.example.android.scarne;

        import android.content.Context;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.util.Random;
        import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    int userScore = 0;
    int compScore = 0;
    int localScore = 0;
    boolean userTurn = true;

    int diceIndex[] = {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6 };

    TextView t_score,comp_score,u_score;
    ImageView img;
    Button bRoll;
    Button bReset;
    Button bHold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t_score = (TextView) findViewById(R.id.t_score);
        comp_score = (TextView) findViewById(R.id.comp_score);
        u_score = (TextView) findViewById(R.id.u_score);
        img = (ImageView) findViewById(R.id.img);
        bRoll = (Button) findViewById(R.id.button);
        bReset = (Button) findViewById(R.id.button3);
        bHold = (Button) findViewById(R.id.button2);

        bHold.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                holdButton();
            }
        });

        bRoll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rollButton();
            }
        });

        bReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetButton();
            }
        });
    }

    public void rollButton(){

        Random r = new Random();
        int dice = r.nextInt(6) + 1;
        //  String imagePath = "@drawable/dice" + dice;
        img.setImageResource(diceIndex[dice - 1]);
        if(dice == 1){
            userTurn = !userTurn;
            localScore = 0;
        }
        else{
            localScore = localScore + dice;
        }
        String setScore = "Current_Score: " + localScore;
        u_score.setText(setScore);
        checkWinCase();

    }

    public void checkWinCase() {
        int score = 0;
        if(userTurn){
            score = userScore + localScore;
        }
        else{
            score = compScore + localScore;
        }

        if(score >= 50){
            bHold.setClickable(false);
            bRoll.setClickable(false);

            Context context = getApplicationContext();
            CharSequence gameOver = "Game Over!";
            CharSequence newGame = "Starting new game!";

            Toast.makeText(context, gameOver, Toast.LENGTH_LONG).show();
            Toast.makeText(context, newGame, Toast.LENGTH_LONG).show();


            try {
                TimeUnit.MILLISECONDS.sleep(5000);
                resetButton();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void resetButton(){
        localScore = 0;
        userScore = 0;
        compScore = 0;

        String playerScore = "Player: " + localScore;
        t_score.setText(playerScore);
        String opponentScore = "Opponent: " + localScore;
        comp_score.setText(opponentScore);
        String setScore = "Current_Score: " + localScore;
        u_score.setText(setScore);

        bHold.setClickable(true);
        bRoll.setClickable(true);

    }

    public void holdButton(){

        if(userTurn){
            userScore = userScore + localScore;
            String playerScore = "Player: " + userScore;
            t_score.setText(playerScore);
        }
        else{
            compScore = compScore + localScore;
            String opponentScore = "Opponent: " + compScore;
            comp_score.setText(opponentScore);
        }
        userTurn = !userTurn;
        localScore = 0;

        String setScore = "Current_Score: " + localScore;
        u_score.setText(setScore);
    }

}

