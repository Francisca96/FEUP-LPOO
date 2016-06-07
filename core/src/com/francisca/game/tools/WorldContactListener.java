package com.francisca.game.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.francisca.game.sprites.elements.Coin;

/**
 * Created by ZeCarlos on 03/06/2016.
 */
public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        System.out.println("Tocou");
        if(fixA.getUserData() instanceof Coin || fixB.getUserData() instanceof Coin)
        {
            Fixture coin = fixA.getUserData() instanceof Coin ? fixA : fixB;
            Fixture object = coin == fixA ? fixB : fixA;

            ((Coin) coin.getUserData()).onCreatingCollision();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
