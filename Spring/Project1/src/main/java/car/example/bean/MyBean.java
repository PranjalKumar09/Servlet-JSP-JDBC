package car.example.bean;

public class MyBean {
    private String message;
    public void setMessage(String message) {
        this.message = message;
    }
    public void ShowMessage(){
        System.out.println(message);
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "message='" + message + '\'' +
                '}';
    }
}