package io.reactivesw.order.cart.application.model.action;

import io.reactivesw.common.model.UpdateAction;
import lombok.Data;

/**
 * Created by umasuo on 16/12/15.
 */
@Data
public class SetCustomerEmail implements UpdateAction {

  /**
   * new email.
   */
  private String email;

  @Override
  public String getActionName() {
    return null;
  }
}
