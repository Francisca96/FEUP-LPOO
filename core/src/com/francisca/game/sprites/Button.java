package com.francisca.game.sprites;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Francisca on 06/06/16.
 */
public class Button {
    public static ImageButton createButtonWithImage(TextureRegionDrawable textureRegionDrawable) {
        ImageButton.ImageButtonStyle imageButtonStyle = new ImageButton.ImageButtonStyle();
        imageButtonStyle.pressedOffsetX = 2;
        imageButtonStyle.pressedOffsetY = -2;
        imageButtonStyle.imageUp = textureRegionDrawable;
        imageButtonStyle.imageDown = textureRegionDrawable;
        return new ImageButton(imageButtonStyle);
    }
}

