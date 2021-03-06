package io.reactivesw.common.model;

import org.springframework.context.ApplicationContext;

/**
 * we may got two kind of update: just use the data in action, or still use data from other service.
 * if we just use the data in action, we can only use action mapper to set the data.
 * if we need get data from other palace, we should use update service.
 * Created by umasuo on 16/12/7.
 */
public interface Update<E> {

  /**
   * put the value in action to entity.
   *
   * @param entity E
   * @param action UpdateAction
   */
  void handle(E entity, UpdateAction action);

  default void handle(E entity, UpdateAction action, ApplicationContext context) {
    Update service = (Update) context.getBean(action.getActionName());
    service.handle(entity, action);
  }
}
