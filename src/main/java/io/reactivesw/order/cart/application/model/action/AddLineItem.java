package io.reactivesw.order.cart.application.model.action;

import io.reactivesw.common.model.UpdateAction;
import io.reactivesw.order.cart.application.model.LineItemDraft;
import io.reactivesw.order.cart.infrastructure.util.CartUpdateActionUtils;
import io.swagger.annotations.ApiModel;

/**
 * Created by umasuo on 16/12/1.
 */
@ApiModel
public class AddLineItem extends LineItemDraft implements UpdateAction {
  @Override
  public String getActionName() {
    return CartUpdateActionUtils.ADD_LINE_ITEM;
  }
}
