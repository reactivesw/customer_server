package io.reactivesw.order.payment.application.controller;

import static io.reactivesw.route.PaymentRouter.CUSTOMER_ID;
import static io.reactivesw.route.PaymentRouter.PAYMENT_ROOT;
import static io.reactivesw.route.PaymentRouter.PAYMENT_WITH_CUSTOMER_ID;

import io.reactivesw.order.payment.application.model.CreditCard;
import io.reactivesw.order.payment.application.model.Payment;
import io.reactivesw.order.payment.application.model.action.AddCreditCardAction;
import io.reactivesw.order.payment.domain.service.PaymentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

/**
 * Created by Davis on 17/1/4.
 */
@RestController
public class PaymentController {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(PaymentController.class);

  /**
   * payment service.
   */
  @Autowired
  private transient PaymentService paymentService;

  /**
   * get credit cards by customer id.
   *
   * @param customerId customer id
   * @return list of credit cards
   */
  @ApiOperation("get credit cards by customer id")
  @GetMapping(PAYMENT_WITH_CUSTOMER_ID)
  public List<CreditCard> getCreditCards(@PathVariable(CUSTOMER_ID)
                                         @ApiParam(value = "subjectId", required = true)
                                             String customerId) {
    LOG.debug("enter getCreditCards, customer id is : {}", customerId);
    List<CreditCard> result = paymentService.getCreditCards(customerId);
    LOG.debug("end getCreditCards, result size is : {}", result.size());
    return result;
  }

  /**
   * Add credit cards list.
   *
   * @param customerId          the customer id
   * @param addCreditCardAction the add credit card action
   * @return the list
   */
  @ApiOperation("update customer credit card")
  @PutMapping(PAYMENT_WITH_CUSTOMER_ID)
  public List<CreditCard> addCreditCards(@PathVariable(CUSTOMER_ID)
                                         @ApiParam(value = "subjectId", required = true)
                                             String customerId,
                                         @RequestBody
                                         @ApiParam(value = "CategoryEntity Update Fields",
                                             required = true)
                                         @Valid AddCreditCardAction addCreditCardAction) {
    LOG.debug("enter updateCreditCards, customer id is : {}", customerId);

    List<CreditCard> result = paymentService.addCreditCard(customerId, addCreditCardAction);

    LOG.debug("end updateCreditCards");

    return result;
  }

  /**
   * checkout.
   *
   * @param amount amount to paid
   * @param token  payment method token
   * @return Payment
   */
  @ApiOperation("checkout")
  @PostMapping(PAYMENT_ROOT)
  public Payment checkout(@RequestParam String customerId,
                          @RequestParam String amount,
                          @RequestParam String token) {

    LOG.debug("enter checkout, customer id is : {} amount is : {}, token is : {}",
        customerId, amount, token);
    Payment result = paymentService.checkout(customerId, amount, token);
    LOG.debug("end checkout, result is : {}", result);
    return result;
  }
}
