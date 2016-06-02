package com.francisca.game;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.SortedIntList;
import com.francisca.game.sprites.Pig;


import java.util.ArrayList;

/**
 * Created by ZeCarlos on 08/05/2016.
 */
public class Player {
    private String name;
    private Pig actualPig;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pig getActualPig() {
        return actualPig;
    }

    public void setActualPig(Pig actualPig) {
        this.actualPig = actualPig;
    }

    public Player(String name)
    {
        this.name = name;
        this.actualPig = new Pig();
    }

}
