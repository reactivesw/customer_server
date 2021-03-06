package io.reactivesw.catalog.producttype.application.model.action;

import io.reactivesw.catalog.product.application.model.attributes.AttributeDefinition;
import io.reactivesw.catalog.producttype.infrastructure.util.ProductTypeActionUtils;
import io.reactivesw.common.model.UpdateAction;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Davis on 16/12/12.
 */
@Getter
@Setter
public class SetAttributeOrder implements UpdateAction {
  /**
   * The Attributes.
   */
  @NotNull
  @Size(min = 1)
  private List<AttributeDefinition> attributes;

  @Override
  public String getActionName() {
    return ProductTypeActionUtils.SET_ATTRIBUTE_ORDER;
  }
}
