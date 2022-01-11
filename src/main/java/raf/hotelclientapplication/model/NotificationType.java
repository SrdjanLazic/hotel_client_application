package raf.hotelclientapplication.model;

public class NotificationType {

    private Long id;
    private Type type;
    private String message;

    public NotificationType(){

    }

    public NotificationType(Type type, String message) {
        this.type = type;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
