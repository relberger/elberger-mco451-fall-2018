package com.example.rachel.homework3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;

public class GridGameAdapter extends RecyclerView.Adapter<GridGameAdapter.ViewHolder>
{
    private static int DEFAULT_ELEMENTS = 16;
    private boolean[] mSquares;
    private int mElements;
    private int mWinningNumber;
    private Random mGenerator;

    public GridGameAdapter()
    {
        this(DEFAULT_ELEMENTS);
    }

    public GridGameAdapter(int elements)
    {
        if (elements % Math.sqrt(elements) == 0)
        {
            mSquares = new boolean[elements];
            mElements = elements;
            mGenerator = new Random();

            startGame();
        }
        else
        {
            throw new IllegalArgumentException("Number of Squares must allow for a perfect square board");
        }
        ;
    }

    private void startGame()
    {
        startGameWith(mGenerator.nextInt(mElements));
    }

    public void startNewGame()
    {
        endCurrentGame();
        startGame();
    }

    @NonNull
    @Override
    public GridGameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item, viewGroup, false);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull GridGameAdapter.ViewHolder viewHolder, int i)
    {
        viewHolder.mButton.setText(Integer.toString(i));
    }

    @Override
    public int getItemCount()
    {
        return mSquares.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private final Button mButton;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            mButton = itemView.findViewById(R.id.button);
        }
    }

    public void overwriteWinningNumber(int newWinningNumber)
    {
        if (newWinningNumber >= 0 && newWinningNumber < mSquares.length)
        {
            endCurrentGame();
            startGameWith(newWinningNumber);
        }
        else
        {
            throw new IllegalArgumentException("This number is not valid winning number");
        }
    }

    private void endCurrentGame()
    {
        mSquares[mWinningNumber] = false;
    }

    private void startGameWith(int winningNumber)
    {
        mWinningNumber = winningNumber;
        mSquares[mWinningNumber] = true;
    }

    public int getWinningNumber()
    {
        return mWinningNumber;
    }

    public boolean isWinner(int elementNumber)
    {
        return mSquares[elementNumber];
    }
}
