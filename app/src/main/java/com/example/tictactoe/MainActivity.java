package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;
    int CPlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winPositions = {{0,1,2}, {3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public static int count =0;
    public void playerTap(View view){

        ImageView img = (ImageView) view;
        int tImage = Integer.parseInt(img.getTag().toString());

        if(!gameActive){
            gameReset(view);
        }

        if(gameState[tImage]==2){
            count++;
            if (count==9){
                gameActive = false;
            }
            gameState[tImage]=CPlayer;


            if(CPlayer==0){
                img.setImageResource(R.drawable.jetha);
                CPlayer=1;
                TextView status= findViewById(R.id.status);
                status.setText("Bapuji's Turn - Tap to Play");
            }
            else{
                img.setImageResource(R.drawable.champak);
                CPlayer=0;
                TextView status= findViewById(R.id.status);
                status.setText("Jetha's Turn - Tap to Play");
            }

        }
        int flag=0;
        for(int[] winPosition : winPositions){
            if(gameState[winPosition[0]]==gameState[winPosition[1]]&&
            gameState[winPosition[1]]==gameState[winPosition[2]]&&
            gameState[winPosition[0]]!=2){
                flag=1;
                String win;
                gameActive=false;

                if(gameState[winPosition[0]]==0){
                    win = "'Chai Piyo Biscit Khao'";
                }
                else{
                    win="'Nahane Ja Nahane Ja'";
                }
                TextView status=(TextView) findViewById(R.id.status);
                status.setText(win);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        gameReset(view);
                    }
                }, 3000);





            }
        }
        if (count==9 && flag==0){
            TextView status = (TextView) findViewById(R.id.status);
            status.setText("Match Draw");
        }

    }

    public void gameReset(View view){
        gameActive=true;
        count=0;
        CPlayer=0;
        for(int i=0;i<9;i++){
            gameState[i]=2;
        }
        ((ImageView) findViewById(R.id.b1)).setImageResource(0);
        ((ImageView) findViewById(R.id.b2)).setImageResource(0);
        ((ImageView) findViewById(R.id.b3)).setImageResource(0);
        ((ImageView) findViewById(R.id.b4)).setImageResource(0);
        ((ImageView) findViewById(R.id.b5)).setImageResource(0);
        ((ImageView) findViewById(R.id.b6)).setImageResource(0);
        ((ImageView) findViewById(R.id.b7)).setImageResource(0);
        ((ImageView) findViewById(R.id.b8)).setImageResource(0);
        ((ImageView) findViewById(R.id.b0)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("Jetha's Turn - Tap to play");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
}