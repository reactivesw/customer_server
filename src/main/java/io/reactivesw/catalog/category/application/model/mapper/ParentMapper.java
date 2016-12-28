package io.reactivesw.catalog.category.application.model.mapper;

import io.reactivesw.catalog.category.application.model.action.SetParent;
import io.reactivesw.catalog.category.domain.entity.CategoryEntity;
import io.reactivesw.common.model.UpdateAction;

/**
 * Created by Davis on 16/12/7.
 */
public class ParentMapper implements CategoryUpdateMapper<CategoryEntity> {

  @Override
  public void handle(CategoryEntity entity, UpdateAction action) {
    SetParent setParent = (SetParent)action;
    entity.setParent(setParent.getParent().getId());
    // TODO: 16/12/28 should change ancestors and subCategory's ancestors
  }
}
