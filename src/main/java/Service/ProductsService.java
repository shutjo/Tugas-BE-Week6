package Service;

import Model.ProductsEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
public class ProductsService {
    public List<ProductsEntity> productsEntityList() {
        return ProductsEntity.listAll();
    }
    public ProductsEntity getProductName(@PathParam("productName") String productName) {
        return ProductsEntity.find("productName = ?1", productName).firstResult();
    }

    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void createData(@Valid ProductsEntity products) {
        ProductsEntity data = new ProductsEntity();
        data.setProductID(products.getProductID());
        data.setProductQuantity(products.getProductQuantity());
        data.setProductPrice(products.getProductPrice());
        data.setProductCategory(products.getProductCategory());
        data.setProductBrand(products.getProductBrand());
        data.setProductName(products.getProductName());
        data.persist();
    }
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void updateProduct(@PathParam("productName") String productName, @Valid ProductsEntity products) {
        ProductsEntity existingProduct = ProductsEntity.find("productName", productName).firstResult();
        if (existingProduct == null) {
            throw new NotFoundException();
        }
        existingProduct.setProductName(products.productName);
        existingProduct.setProductBrand(products.productBrand);
        existingProduct.setProductCategory(products.productCategory);
        existingProduct.setProductPrice(products.productPrice);
        existingProduct.setProductQuantity(products.productQuantity);
        existingProduct.persist();

    }
}
