package Game;

public class Door {
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
}