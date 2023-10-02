package com.test.Engine;

import java.awt.event.KeyEvent;

// Class used to create and edit levels
public class LevelEditorScene extends Scene{

    private boolean changingScene = false;
    private float timeToChangeScene = 2;

    public LevelEditorScene() {
        System.out.println("Inside level editor scene");
    }

    @Override
    public void Update(float deltaTime) {

        System.out.println(" " + (1.0f / deltaTime) + "FPS");

        if(!changingScene && KeyListener.isKeyPressed(KeyEvent.VK_SPACE)) {
            changingScene = true;
        }

        if(changingScene && timeToChangeScene > 0) {
            timeToChangeScene -= deltaTime;
            Window.get().r -= deltaTime * 5.0f;
            Window.get().g -= deltaTime * 5.0f;
            Window.get().b -= deltaTime * 5.0f;
        }
        else if(changingScene) {
            Window.changeScene(1);
        }
    }

}
