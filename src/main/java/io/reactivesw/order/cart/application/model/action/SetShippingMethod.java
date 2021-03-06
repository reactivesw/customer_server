package io.reactivesw.order.cart.application.model.action;

import io.reactivesw.common.model.Reference;
import io.reactivesw.common.model.UpdateAction;
import io.reactivesw.order.cart.infrastructure.util.CartUpdateActionUtils;
import lombok.Data;

/**
 * Created by umasuo on 16/12/15.
 */
@Data
public class SetShippingMethod implements UpdateAction {

  /**
   * Reference to a ShippingMethod.
   */
  private Reference shippingMethod;

  @Override
  public String getActionName() {
    return CartUpdateActionUtils.SET_SHIPPING_METHOD;
  }
}
