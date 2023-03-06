package Controller;

import Model.ProductsEntity;
import Service.ProductsService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
@Path("/products")
public class ProductController extends ProductsService {
    @Inject
    ProductsService productsService;
    @GET
    public List<ProductsEntity> productsEntityList() {
        return productsService.productsEntityList();
    }
    @GET
    @Path("/{productName}")
    @Override
    public ProductsEntity getProductName(String productName) {
        return super.getProductName(productName);
    }
    @POST
    public Response createProduct(ProductsEntity products) {
        productsService.createData(products);
        return Response.status(Response.Status.CREATED).build();
    }
    @PUT
    @Path("/{productName}")
    public void updateProduct(@PathParam("productName") String productName, @Valid ProductsEntity products) {
        productsService.updateProduct(productName, products);
    }
    @DELETE
    @Path("/{productName}")
    @Transactional
    public Response deleteProduct(@PathParam("productName") String productName) {
        ProductsEntity existingProduct = ProductsEntity.find("productName", productName).firstResult();
        if (existingProduct == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingProduct.delete();
        return Response.noContent().build();
    }
}





