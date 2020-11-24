package tmall.service;

import tmall.pojo.Order;
import tmall.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {

    void add(OrderItem oi);

    void delete(int id);

    void update(OrderItem oi);

    OrderItem get(int id);

    List<OrderItem> list();

    void fill(List<Order> os);

    void fill(Order o);


}
