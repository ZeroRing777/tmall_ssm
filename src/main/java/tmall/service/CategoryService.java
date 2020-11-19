package tmall.service;

import tmall.pojo.Category;
import tmall.util.Page;

import java.util.List;

public interface CategoryService {
    List<Category> list();
    void add(Category category);
    void delete(int id);
    Category get(int id);
    void update(Category category);

}
