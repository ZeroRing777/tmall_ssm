package tmall.service;

import tmall.pojo.Product;

import java.util.List;

public interface ProductService {

    void add(Product p);
    void delete(int id);
    void update(Product p);
    Product get(int id);
    List <Product> list(int cid);
    void setFirstProductImage(Product p);
}
