package com.francisca.game;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.SortedIntList;
import com.francisca.game.sprites.Pig;
import com.francisca.game.sprites.PigType;


import java.util.ArrayList;

/**
 * Created by ZeCarlos on 08/05/2016.
 */
public class Player {
    private String name;
    private PigType actualPig;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PigType getActualPig() {
        return actualPig;
    }

    public void setActualPig(PigType actualPig) {
        this.actualPig = actualPig;
    }

    public Player(String name)
    {
        this.name = name;
        this.actualPig = PigType.NORMAL;
    }

}
