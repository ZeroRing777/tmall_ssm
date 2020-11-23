package tmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tmall.mapper.OrderMapper;
import tmall.pojo.Order;
import tmall.pojo.OrderExample;
import tmall.pojo.User;
import tmall.service.OrderService;
import tmall.service.UserService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserService userService;

    @Override
    public void add(Order o) {
        orderMapper.insert(o);
    }

    @Override
    public void delete(int id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Order o) {
         orderMapper.updateByPrimaryKeySelective(o);
    }

    @Override
    public Order get(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Order> list() {
        OrderExample example=new OrderExample();
        example.setOrderByClause("id desc");
        List<Order> result =orderMapper.selectByExample(example);
        setUser(result);
        return result;
    }


    public void setUser(List<Order> os){
        for(Order o:os){
            setUser(o);
        }
    }

    public void setUser(Order o){
        int uid = o.getUid();
        User u = userService.get(uid);
        o.setUser(u);

    }

}


