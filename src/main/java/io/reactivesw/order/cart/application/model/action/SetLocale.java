package io.reactivesw.order.cart.application.model.action;

import io.reactivesw.common.model.UpdateAction;
import lombok.Data;

/**
 * Sets the locale. Must be one of the languages supported for this Project.
 * Created by umasuo on 16/12/15.
 */
@Data
public class SetLocale implements UpdateAction {

  private String locale;

  @Override
  public String getActionName() {
    return null;
  }
}
