package com.seyrancom.consulting.app.service.impl;

import com.seyrancom.consulting.app.domain.entity.Product;
import com.seyrancom.consulting.app.repository.dao.impl.ProductDao;
import com.seyrancom.consulting.core.service.AbstractService;
import com.seyrancom.consulting.core.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@AppService
public class ProductServiceImpl extends AbstractService {

    @Autowired
    private ProductDao productDao;

    public void add(Product product) {
        productDao.create(product);
    }

    public void addAll(Collection<Product> products) {
        for (Product product : products) {
            productDao.create(product);
        }
    }

    @Transactional(readOnly = true)
    public List<Product> listAll() {
        return productDao.findAll();
    }

}