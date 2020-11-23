package tmall.service;

import tmall.pojo.Product;
import tmall.pojo.PropertyValue;

import java.util.List;

public interface PropertyValueService {

    void init(Product p);

    void update(PropertyValue pv);

    PropertyValue get(int ptid,int pid);

    List<PropertyValue> list(int pid);


}
