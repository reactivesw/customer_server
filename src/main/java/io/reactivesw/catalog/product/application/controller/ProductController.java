package io.reactivesw.catalog.product.application.controller;

import static io.reactivesw.route.ProductRouter.PRODUCT_ID;
import static io.reactivesw.route.ProductRouter.PRODUCT_ROOT;
import static io.reactivesw.route.ProductRouter.PRODUCT_WITH_ID;

import io.reactivesw.catalog.product.application.ProductApplication;
import io.reactivesw.catalog.product.application.model.Product;
import io.reactivesw.catalog.product.application.model.ProductDraft;
import io.reactivesw.catalog.product.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by Davis on 16/12/14.
 */
@RestController
public class ProductController {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

  /**
   * Product Application.
   */
  @Autowired
  private transient ProductApplication productApplication;

  /**
   * The Product service.
   */
  @Autowired
  private transient ProductService productService;

  /**
   * Create Product.
   *
   * @param productDraft the ProductDraft
   * @return the Product
   */
  @ApiOperation(value = "Create Product")
  @PostMapping(PRODUCT_ROOT)
  public Product createProduct(@RequestBody
                               @ApiParam(value = "Product Draft", required = true)
                               @Valid ProductDraft productDraft) {
    LOG.debug("enter createProduct, ProductDraft is : {}", productDraft.toString());

    Product result = productApplication.createProduct(productDraft);

    LOG.debug("end createProduct, created Product is : {}", result.toString());

    return result;
  }

  /**
   * Gets Product by id.
   *
   * @param id the id
   * @return the Product
   */
  @ApiOperation(value = "Get Product By Id")
  @GetMapping(PRODUCT_WITH_ID)
  public Product getProductById(@PathVariable(value = PRODUCT_ID)
                                @ApiParam(value = "Product ID", required = true)
                                    String id) {
    LOG.debug("enter getProductById, id is : {}", id);

    Product result = productApplication.getProductById(id);

    LOG.debug("end getProductById, get product is : {}", result.toString());

    return result;
  }

  /**
   * Gets product by slug.
   *
   * @param slug the slug
   * @return the product by slug
   */
  @ApiOperation(value = "Get Product By Slug")
  @GetMapping(PRODUCT_ROOT)
  public Product getProductBySlug(@RequestParam
                                  @ApiParam(value = "Product Slug", required = true)
                                      String slug) {
    LOG.debug("enter getProductBySlug, slug is : {}", slug);

    Product result = productService.getProductBySlug(slug);

    LOG.debug("end getProductBySlug, get product : {}", result.toString());

    return result;
  }

  /**
   * Delete product by id.
   *
   * @param id      the id
   * @param version the version
   */
  @ApiOperation(value = "delete product by id")
  @DeleteMapping(PRODUCT_WITH_ID)
  public void deleteProductById(@PathVariable(value = PRODUCT_ID)
                                  @ApiParam(value = "Product ID", required = true)
                                      String id,
                                @RequestParam
                                  @ApiParam(value = "Product Version", required = true)
                                      Integer version){
    LOG.debug("enter deleteProductById, id is {}, version is {}", id, version);

    productService.deleteProduct(id, version);

    LOG.debug("end deleteProductById, id is {}, version is {}", id, version);
  }
}
