package com.test.Engine;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

// Singleton
public class MouseListener {
    private static MouseListener instance;
    private double scrollX, scrollY;
    private double xPos, yPos, lastX, lastY;
    private boolean isDragging;

    // Size 3 since there are only 3 mouse buttons to code for
    private boolean mouseButtonPressed[] = new boolean[3];

    private MouseListener() {
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    // Returns singleton instance of the MouseListener
    public static MouseListener get() {
        if(MouseListener.instance == null ) {
            MouseListener.instance = new MouseListener();
        }
        return MouseListener.instance;
    }

    // Callback for mouse movement
    public static void mousePosCallback(long window, double in_xPos, double in_yPos) {
        // Set last mouse position
        get().lastX = get().xPos;
        get().lastY = get().yPos;

        // Set current mouse position
        get().xPos = in_xPos;
        get().yPos = in_yPos;

        // Check if dragging based on if mouse button is pressed
        get().isDragging = get().mouseButtonPressed[0] || get().mouseButtonPressed[1] || get().mouseButtonPressed[2];
    }

    // Callback for when mouse button is pressed
    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        // Will not work for any fancy mice buttons other than main 3
        if(button > get().mouseButtonPressed.length) return;

        if(action == GLFW_PRESS) {
            get().mouseButtonPressed[button] = true;
        }
        else if(action == GLFW_RELEASE){
            get().mouseButtonPressed[button] = false;
            get().isDragging = false;
        }
    }

    // Callback for mouse scroll wheel
    public static void mouseScrollCallback(long window, double xOffset, double yOffset) {
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }

    // End of frame reset values
    public static void endFrame() {
        get().scrollX = 0.0;
        get().scrollY = 0.0;
        get().lastX = get().xPos;
        get().lastY = get().yPos;
    }

    public static float getX() {
        return (float)get().xPos;
    }

    public static float getY() {
        return (float)get().yPos;
    }

    public static float getDx() {
        return (float)(get().lastX - get().xPos);
    }

    public static float getDy() {
        return (float)(get().lastY - get().yPos);
    }

    public static float getScrollX() {
        return (float)get().scrollX;
    }

    public static float getScrollY() {
        return (float)get().scrollY;
    }

    public static boolean getIsDragging() {
        return get().isDragging;
    }

    public static boolean mouseButtonDown(int button) {
        if(button > get().mouseButtonPressed.length) {
            return false;
        }
        return get().mouseButtonPressed[button];
    }
}
