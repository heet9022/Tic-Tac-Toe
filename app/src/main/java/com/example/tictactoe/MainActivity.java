package com.example.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import static android.os.Build.ID;

public class MainActivity extends AppCompatActivity {

    int count = 0;
    int gameState[] = {2,2,2,2,2,2,2,2,2};
    int winningPos[][] = {{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};
    boolean flag = false;

    public void newGame(View view)
    {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public int result(int variable)
    {
        for(int i=0;i<8;i++)
        {
            int check = 0;
            for(int j = 0;j<3;j++)
                if(gameState[winningPos[i][j]]==variable)
                    check++;

            if(check==3)
                return variable;
        }
        return -1;
    }

    public void toast(String s)
    {
        Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
    }


    public void fade(View view)
    {
        if(flag)
            return;
        ImageView cell = (ImageView) view;
        cell.animate().alpha(1f).setDuration(500);
        int tapped = Integer.parseInt(cell.getTag().toString());
        if(gameState[tapped]!=2)
            return;
        count++;
        if(count%2==1)
        {
            cell.setImageResource(R.drawable.x);
            gameState[tapped] = 1;
        }
        else
        {
            cell.setImageResource(R.drawable.o);
            gameState[tapped] = 0;
        }
        cell.animate().rotationY(360f).setDuration(2000);
        //System.out.println(Integer.toString(result(gameState[tapped])));
        if(count>4)
            switch(result(gameState[tapped]))
            {
                case -1:
                    if(count>=9)
                    {
                        toast("It's a draw!");
                        flag = true;
                    }
                    break;
                case 0:
                    toast("0 wins the game!");
                    flag = true;
                    break;
                case 1:
                    toast("X wins the game!");
                    flag = true;
                    break;
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
