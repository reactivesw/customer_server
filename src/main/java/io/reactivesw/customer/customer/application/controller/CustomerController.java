package io.reactivesw.customer.customer.application.controller;

import io.reactivesw.customer.customer.application.model.Customer;
import io.reactivesw.customer.customer.application.model.mapper.CustomerMapper;
import io.reactivesw.customer.customer.application.service.CustomerApplication;
import io.reactivesw.customer.customer.domain.entity.CustomerEntity;
import io.reactivesw.customer.customer.domain.service.CustomerService;
import io.reactivesw.route.CustomerRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by umasuo on 16/12/27.
 */
@RestController
public class CustomerController {

  /**
   * logger.
   */
  private final static Logger LOG = LoggerFactory.getLogger(CustomerController.class);

  /**
   * customer service.
   */
  @Autowired
  private transient CustomerService customerService;

  /**
   * customer service.
   */
  @Autowired
  private transient CustomerApplication customerApplication;

  /**
   * login with email.
   *
   * @param email String
   * @return Customer
   */
  @PostMapping(value = CustomerRouter.CUSTOMER_ROOT, params = "email")
  public Customer getCustomerWithEmail(String email) {
    LOG.info("enter: email:", email);

    CustomerEntity entity = customerService.getByEmail(email);

    Customer customer = CustomerMapper.entityToModel(entity);

    LOG.info("exit: customer:", customer);
    return customer;
  }

  /**
   * login with google.
   *
   * @param gToken String
   * @return Customer
   */
  @PostMapping(value = CustomerRouter.CUSTOMER_ROOT, params = "gToken")
  public Customer getCustomerWithGoogleToken(String gToken) throws Exception {
    LOG.info("enter: idToken:", gToken);

    Customer customer = customerApplication.getOrCreateWithGoogleToken(gToken);


    LOG.info("exit: customer:", customer);
    return customer;
  }

}
