package com.example.arhil.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getName();

    private String[] squares = {"0", "1", "2", "3", "4", "5", "6", "7", "8"};
    Toast toast;
    String currentPlayer = TicTac.playerX;
    String winner;
    TextView topContent, bottomContent;


    static class TicTac {
        static String playerX = "Player X";
        static String playerO = "Player O";
        static String draw = "Draw!";

        static Integer markerX = 0;
        static Integer markerO = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView btn0 = (TextView)findViewById(R.id.btn0);
        btn0.setOnClickListener(this);
        TextView btn1 = (TextView)findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        TextView btn2 = (TextView)findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        TextView btn3 = (TextView)findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        TextView btn4 = (TextView)findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
        TextView btn5 = (TextView)findViewById(R.id.btn5);
        btn5.setOnClickListener(this);
        TextView btn6 = (TextView)findViewById(R.id.btn6);
        btn6.setOnClickListener(this);
        TextView btn7 = (TextView)findViewById(R.id.btn7);
        btn7.setOnClickListener(this);
        TextView btn8 = (TextView)findViewById(R.id.btn8);
        btn8.setOnClickListener(this);

        topContent = (TextView) findViewById(R.id.topContent);
        bottomContent = (TextView) findViewById(R.id.bottomContent);
    }

    @Override
    public void onClick(View v) {
        TextView textView = (TextView) v;
        Log.d(TAG, "test");
        Integer buttonID = determineButtonID(v);
        if(buttonID == -1){
            throw new IllegalArgumentException("Invalid buttonID");
        }
        String viewText = (String)textView.getText();

        if (viewText.isEmpty()) {
            markSquare(v, buttonID);
            squares[buttonID] = currentPlayer;

        } else {
            toast = Toast.makeText(this, "You can't place a move here!", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void markSquare(View v, Integer buttonID){
        TextView textView = (TextView) v;
        if(currentPlayer == TicTac.playerX){
            textView.setText("X");
            evaluateBoard(buttonID);
        }
        else {
            textView.setText("O");
            evaluateBoard(buttonID);
        }
    }

    public boolean isDraw(Integer buttonID) {
        boolean isDraw = true;
        for(int i = 0; i < squares.length; i++){
            if(squares[i] == buttonID.toString()){
                isDraw = false;
                break;
            }
        }
        return isDraw;
    }

    public void evaluateBoard(Integer buttonID) {
        if(isWinner(buttonID)){
            topContent.setText(currentPlayer + " has won!");
        } else if (isDraw((buttonID))) {
            topContent.setText(TicTac.draw);
        }
        else {
            if(currentPlayer == TicTac.playerO){
                currentPlayer = TicTac.playerX;
            }
            else{
                currentPlayer = TicTac.playerO;
            }
        }
    }

    public int determineButtonID(View v){
        switch(v.getId()){
            case R.id.btn0 :
                return 0;
            case R.id.btn1 :
                return 1;
            case R.id.btn2 :
                return 2;
            case R.id.btn3 :
                return 3;
            case R.id.btn4 :
                return 4;
            case R.id.btn5 :
                return 5;
            case R.id.btn6 :
                return 6;
            case R.id.btn7 :
                return 7;
            case R.id.btn8 :
                return 8;
            default :
                return -1;
        }
    }

    public boolean isWinner(int buttonID) {
        boolean isWinner = false;

            if (squares[0] == squares[1] && squares[1] == squares[2] ||
                    squares[0] == squares[4] && squares[4] == squares[8] ||
                    squares[0] == squares[3] && squares[3] == squares[6]) {
                isWinner = true;
                winner = squares[0];
            }
            else if (squares[3] == squares[4] && squares[4] == squares[5]) {
                isWinner = true;
                winner = squares[3];
            }
            else if (squares[6] == squares[4] && squares[4] == squares[2] ||
                    squares[6] == squares[7] && squares[7] == squares[8]) {
                isWinner = true;
                winner = squares[6];
            }
            else if (squares[0] == squares[4] && squares[4] == squares[7]) {
                isWinner = true;
                winner = squares[0];
            }
            else if (squares[2] == squares[5] && squares[5] == squares[8]) {
                isWinner = true;
                winner = squares[2];
            }

        return isWinner;
    }
}
