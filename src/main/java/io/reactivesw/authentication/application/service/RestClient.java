package io.reactivesw.authentication.application.service;

import io.reactivesw.authentication.infrastructure.util.JwtUtil;
import io.reactivesw.common.exception.ConflictException;
import io.reactivesw.common.exception.CreateResourceFailed;
import io.reactivesw.common.util.ServiceLocator;
import io.reactivesw.customer.customer.application.model.Customer;
import io.reactivesw.customer.customer.application.model.SignupWithEmail;
import io.reactivesw.route.CustomerRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by umasuo on 17/1/21.
 * TODO Rest call should be replaced.
 */
@Component
public class RestClient {

  /**
   * logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(RestClient.class);

  /**
   * RestTemplate.
   */
  private transient RestTemplate restTemplate = new RestTemplate();

  /**
   * service locator.
   * //TODO we should use register to replace this.
   */
  private transient ServiceLocator serviceLocator;

  /**
   * http token for call other services.
   */
  private transient final HttpHeaders headers;

  /**
   * constructor. generate the service token here.
   *
   * @param serviceLocator
   * @param jwtUtil
   */
  @Autowired
  public RestClient(ServiceLocator serviceLocator, JwtUtil jwtUtil) {
    this.serviceLocator = serviceLocator;
    String token = jwtUtil.generateServiceToken("authentication");
    headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + token);
  }

  /**
   * Gets Address from customer service.
   *
   * @param email the address id
   */
  public Customer getCustomerByEmail(String email) {
    LOG.debug("enter: email: {}", email);

    String url = serviceLocator.getCustomer() + CustomerRouter.getCustomerWithEmail(email);
    Customer customer = null;
    try {
      ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.GET, new
          HttpEntity<Object>(headers), Customer.class);
      customer = (Customer) responseEntity.getBody();
    } catch (RestClientException e) {
      LOG.warn("RestClientException: when call customer service with google token.");
    }
    LOG.debug("exit: customer: {}", customer);
    return customer;
  }

  /**
   * google token.
   *
   * @param gToken String
   * @return Customer
   */
  public Customer getCustomerByGoogleToken(String gToken) {
    LOG.debug("enter: google token: {}", gToken);
    String url = serviceLocator.getCustomer() + CustomerRouter.getCustomerWithGoogle(gToken);
    Customer customer = null;
    try {
      ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.GET, new
          HttpEntity<Object>(headers), Customer.class);
      customer = (Customer) responseEntity.getBody();
    } catch (RestClientException e) {
      LOG.warn("RestClientException: when call customer service with google token.");
    }
    LOG.debug("exit: customer: {}", customer);
    return customer;
  }

  /**
   * create customer with email & password.
   *
   * @param email
   * @param password
   * @return
   */
  public Customer createCustomerByEmail(String email, String password) {
    LOG.debug("enter: email: {}", email);

    String url = serviceLocator.getCustomer() + CustomerRouter.CUSTOMER_ROOT;
    Customer customer;
    SignupWithEmail emailModel = new SignupWithEmail();
    emailModel.setEmail(email);
    emailModel.setPassword(password);
    try {
      ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.POST,
          new HttpEntity<SignupWithEmail>(emailModel, headers), Customer.class);
      customer = (Customer) responseEntity.getBody();
    } catch (HttpClientErrorException e) {
      LOG.warn("RestClientException: when call customer service with email.");
      if (e.getStatusCode() == HttpStatus.CONFLICT) {
        throw new ConflictException("Customer already exist.");
      }
      throw new CreateResourceFailed("Create new customer with email failed.");
    }
    LOG.debug("exit: customer: {}", customer);
    return customer;
  }


  /**
   * rest setter for test.
   *
   * @param restTemplate
   */
  protected void setRestTemplate(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }
}
