package tmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tmall.mapper.ProductMapper;
import tmall.pojo.Category;
import tmall.pojo.Product;
import tmall.pojo.ProductExample;
import tmall.pojo.ProductImage;
import tmall.service.CategoryService;
import tmall.service.ProductImageService;
import tmall.service.ProductService;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductImageService productImageService;

    @Override
    public void add(Product p) {
        productMapper.insert(p);
    }

    @Override
    public void delete(int id) {
       productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product p) {
        productMapper.updateByPrimaryKeySelective(p);
    }

    @Override
    public Product get(int id) {
        Product p = productMapper.selectByPrimaryKey(id);
        setCategory(p);
        return p;
    }

    public void setCategory(Product p){
        int cid = p.getCid();
        Category c = categoryService.get(cid);
        p.setCategory(c);
    }

    public void setCategory(List<Product> ps){
        for (Product p : ps)
            setCategory(p);
    }

    @Override
    public List<Product> list(int cid) {

        ProductExample example=new ProductExample();
        example.createCriteria().andCidEqualTo(cid);
        example.setOrderByClause("id desc");
        List<Product> result= productMapper.selectByExample(example);
        setCategory(result);
        setFirstProductImage(result);
        return result;
    }

    @Override
    public void setFirstProductImage(Product p) {
        List<ProductImage> pis = productImageService.list(p.getId(), ProductImageService.type_single);

        if (!pis.isEmpty()) {
            ProductImage pi = pis.get(0);
            System.out.println(pi.getType());
            p.setFirstProductImage(pi);
        }

    }

    @Override
    public void fill(List<Category> cs) {
        for(Category c:cs) {fill(c);}

    }

    @Override
    public void fill(Category c) {
        List<Product> ps=list(c.getId());
        c.setProducts(ps);
    }

    @Override
    public void fillByRow(List<Category> cs) { //为分类填充推荐产品集合
        int ProductNumberEachRow=8;
        for(Category c:cs){
            List<Product> products=c.getProducts();
            List<List<Product>> productsByRow=new ArrayList<>();
            for(int i=0;i<products.size();i+=ProductNumberEachRow){
                int size = i+ProductNumberEachRow;
                size= size>products.size()?products.size():size;
                List<Product> productsOfEachRow =products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            c.setProductsByRow(productsByRow);

        }

    }

    public void setFirstProductImage(List<Product> ps) {
        for (Product p : ps) {
            setFirstProductImage(p);
        }
    }

}
