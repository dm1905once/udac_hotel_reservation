package model;

public class Room implements iRoom {
    protected String roomNumber;
    protected double price;
    protected RoomType roomType;

    public Room(String roomNumber, double price, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public String toString(){
        return "ROOM= Number: " + roomNumber + " Price: " + price + " Type: " + roomType;
    }

}
