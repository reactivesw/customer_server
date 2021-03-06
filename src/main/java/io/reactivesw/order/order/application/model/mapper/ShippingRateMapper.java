package io.reactivesw.order.order.application.model.mapper;

import io.reactivesw.common.model.mapper.MoneyMapper;
import io.reactivesw.order.order.domain.entity.value.ShippingRateValue;
import io.reactivesw.order.shippingmethod.application.model.ShippingRate;

/**
 * Created by Davis on 17/2/7.
 */
public final class ShippingRateMapper {
  /**
   * Instantiates a new Shipping rate mapper.
   */
  private ShippingRateMapper() {
  }

  /**
   * Model to entity shipping rate value.
   *
   * @param model the model
   * @return the shipping rate value
   */
  public static ShippingRateValue modelToEntity(ShippingRate model) {
    ShippingRateValue entity = new ShippingRateValue();

    if (model != null) {
      entity.setPrice(MoneyMapper.modelToEntity(model.getPrice()));
      entity.setFreeAbove(MoneyMapper.modelToEntity(model.getFreeAbove()));
    }

    return entity;
  }

  /**
   * Entity to model shipping rate.
   *
   * @param entity the entity
   * @return the shipping rate
   */
  public static ShippingRate entityToModel(ShippingRateValue entity) {
    ShippingRate model = new ShippingRate();

    if (entity != null) {
      model.setPrice(MoneyMapper.entityToModel(entity.getPrice()));
      model.setFreeAbove(MoneyMapper.entityToModel(entity.getFreeAbove()));
    }

    return model;
  }
}
