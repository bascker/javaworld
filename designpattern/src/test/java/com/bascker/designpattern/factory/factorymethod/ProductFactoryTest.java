package com.bascker.designpattern.factory.factorymethod;

import com.bascker.designpattern.factory.factorymethod.sample.product.Phone;
import com.bascker.designpattern.factory.factorymethod.sample.product.Product;
import com.bascker.designpattern.factory.factorymethod.sample.product.ProductFactory;
import com.bascker.designpattern.factory.factorymethod.sample.product.Tv;
import org.junit.Assert;
import org.junit.Test;

/**
 * ProductFactory Unit Test
 *
 * @author bascker
 */
public class ProductFactoryTest {

    @Test
    public void test () throws InstantiationException, IllegalAccessException {
        final Product phone = ProductFactory.newProduct(Phone.class);
        final Product tv = ProductFactory.newProduct(Tv.class);
        Assert.assertEquals("Phone", phone.getName());
        Assert.assertEquals("TV", tv.getName());
    }

}
