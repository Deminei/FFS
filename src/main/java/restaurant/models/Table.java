package restaurant.models;

public class Table {
    private int tableId;
    private int tableSize;
    private Status status;


    public enum Status {
        AVAILABLE,
        RESERVED,
        OCCUPIED
    }
    public Table(int tableId, int tableSize, Status status) {
        this.tableId = tableId;
        this.tableSize = tableSize;
        this.status = status;
    }
    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getTableSize() {
        return tableSize;
    }

    public void setTableSize(int tableSize) {
        this.tableSize = tableSize;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }

    @Override
    public String toString() {
        return "\nTable ID: " + tableId + ", Table Size: " + tableSize + ", Status: " + status;
    }
}
