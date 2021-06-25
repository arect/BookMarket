package entity;

public class MyMessage {
    private static String msg = "";

    public static void setMsg(String msg) {
        MyMessage.msg = msg;
    }

    public static String getMsg() {
        return msg;
    }
}
