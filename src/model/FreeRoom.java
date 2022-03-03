package model;

public class FreeRoom extends Room {
    public FreeRoom(String roomNumber, double price, RoomType roomType) {
        super(roomNumber, 0.0, roomType);
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public String toString(){
        return "FREE ROOM= Number: " + roomNumber + " Price: " + price + " Type: " + roomType;
    }
}
