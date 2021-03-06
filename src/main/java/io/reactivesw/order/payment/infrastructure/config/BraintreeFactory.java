package io.reactivesw.order.payment.infrastructure.config;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Davis on 16/12/27.
 */
@Configuration
public class BraintreeFactory {
  /**
   * merchant id.
   */
  //  @Value("#{environment.BRAINTREE_MERCHANT_ID}")
  @Value("${braintree.merchant-id}")
  public transient String merchantId;

  /**
   * public key.
   */
  @Value("${braintree.public-key}")
  public transient String publicKey;

  /**
   * private key.
   */
  @Value("${braintree.private-key}")
  public transient String privateKey;

  /**
   * braintree environment, default is sanbox.
   */
  public transient Environment environment;

  /**
   * create gateway bean.
   * @return BrainTreeGateway
   */
  @Bean
  public BraintreeGateway getBraintreeGateway() {
    return new BraintreeGateway(
        environment,
        merchantId,
        publicKey,
        privateKey
    );
  }

  /**
   * set braintree environment.
   * @param environment
   */
  @Value("${braintree.environment}")
  public void setEnvironment(String environment) {
    switch (environment) {
      case "sanbox":
        this.environment = Environment.SANDBOX;
        break;
      case "production":
        this.environment = Environment.PRODUCTION;
        break;
      case "development":
        this.environment = Environment.DEVELOPMENT;
        break;
      default:
        this.environment = Environment.SANDBOX;
        break;
    }
  }
}
