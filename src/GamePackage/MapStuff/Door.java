package GamePackage.MapStuff;

import java.io.Serializable;

/**
 * All of this is possibly wrong, might change later //TODO fix this documentation
 * <p>
 * verticalDoor[0][0] will be 🡻 for room[0][0] and 🡹 for room [1][0]
 * verticalDoor[0][1] will be 🡻 for room[0][1] and 🡹 for room [1][1]
 * verticalDoor[0][2] will be 🡻 for room[0][2] and 🡹 for room [1][2]
 * verticalDoor[0][3] will be 🡻 for room[0][3] and 🡹 for room [1][3]
 * <p>
 * verticalDoor[1][0] will be 🡻 for room[1][0] and 🡹 for room [2][0]
 * verticalDoor[1][1] will be 🡻 for room[1][1] and 🡹 for room [2][1]
 * verticalDoor[1][2] will be 🡻 for room[1][2] and 🡹 for room [2][2]
 * verticalDoor[1][3] will be 🡻 for room[1][3] and 🡹 for room [2][3]
 * <p>
 * verticalDoor[2][0] will be 🡻 for room[2][0] and 🡹 for room [3][0]
 * verticalDoor[2][1] will be 🡻 for room[2][1] and 🡹 for room [3][1]
 * verticalDoor[2][2] will be 🡻 for room[2][2] and 🡹 for room [3][2]
 * verticalDoor[2][3] will be 🡻 for room[2][3] and 🡹 for room [3][3]
 * <p>
 * Therefore we can see.
 * // If we want 🡹 door of a room[x][y], we do verticalDoor[x][y-1]
 * // If we want 🡻 door of a room[x][y], we do verticalDoor[x][y]
 * <p>
 * <p>
 * horizontalDoor[0][0] will be 🡺 for room[0][0] and 🡸 for room [1][0]
 * horizontalDoor[0][1] will be 🡺 for room[0][1] and 🡸 for room [1][1]
 * horizontalDoor[0][2] will be 🡺 for room[0][2] and 🡸 for room [1][2]
 * horizontalDoor[0][3] will be 🡺 for room[0][3] and 🡸 for room [1][3]
 * <p>
 * <p>
 * horizontalDoor[1][0] will be 🡺 for room[1][0] and 🡸 for room [2][0]
 * horizontalDoor[1][1] will be 🡺 for room[1][1] and 🡸 for room [2][1]
 * horizontalDoor[1][2] will be 🡺 for room[1][2] and 🡸 for room [2][2]
 * horizontalDoor[1][3] will be 🡺 for room[1][3] and 🡸 for room [2][3]
 * <p>
 * <p>
 * horizontalDoor[2][0] will be 🡺 for room[2][0] and 🡸 for room [3][0]
 * horizontalDoor[2][1] will be 🡺 for room[2][1] and 🡸 for room [3][1]
 * horizontalDoor[2][2] will be 🡺 for room[2][2] and 🡸 for room [3][2]
 * horizontalDoor[2][3] will be 🡺 for room[2][3] and 🡸 for room [3][3]
 * <p>
 * <p>
 * Therefore we can see.
 * If we want 🡸 door of a room[x][y], we do verticalDoor[x-1][y]
 * If we want 🡺 door of a room[x][y], we do verticalDoor[x][y]
 */
/*
 * This is right though.
 *
 * If we want 🡹 door of a room[x][y], we do verticalDoor[x][y-1]
 * If we want 🡻 door of a room[x][y], we do verticalDoor[x][y]
 * If we want 🡸 door of a room[x][y], we do verticalDoor[x-1][y]
 * If we want 🡺 door of a room[x][y], we do verticalDoor[x][y]
 */
public class Door implements Serializable {
    // Field variables
    private boolean locked;

    /**
     * Will have a color if it requires a key to open it
     */
    private DoorColor doorColor;

    // Constructors

    /**
     * Constructor in case it is not locked
     */
    public Door(boolean locked) {
        this.locked = locked;
        // TODO could just have unlocked doors be gray?
        if (!locked) {
            this.doorColor = DoorColor.GRAY;
        }
    }

    /**
     * Constructor in case it is locked, therefore need a color for the door as well
     */
    public Door(boolean locked, DoorColor doorColor) {
        this.locked = locked;
        this.doorColor = doorColor;
    }

    // Methods
    private void unlockDoor(String colorOfKey) {
        // TODO implement opening of door it key color matches.
    }

    // Getters and setter
    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public DoorColor getDoorColor() {
        return doorColor;
    }

    public void setDoorColor(DoorColor doorColor) {
        this.doorColor = doorColor;
    }

    @Override
    public String toString() {
        return "Door{" +
                "locked=" + locked +
                '}';
    }
}