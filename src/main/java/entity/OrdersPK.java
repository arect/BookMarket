package entity;

public class OrdersPK implements java.io.Serializable{

    private int order_id;

    public OrdersPK() {
    }

    public OrdersPK(int id) {
        setOrder_id(id);
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    @Override
    public int hashCode() {
        return order_id;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = this == obj;

        if (obj instanceof OrdersPK) {
            OrdersPK pk = (OrdersPK) obj;
            if (order_id == pk.order_id) {
                result = true;
            }
        }
        return result;
    }
}
