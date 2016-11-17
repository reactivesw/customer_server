package io.reactivesw.customer.server.cart.models.draft;

import io.reactivesw.customer.server.cart.models.CartDiscountTarget;
import io.reactivesw.customer.server.cart.models.CartDiscountValue;
import io.reactivesw.customer.server.common.types.LocalizedString;

import java.time.ZonedDateTime;

/**
 * Created by Davis on 16/11/17.
 */
public class CartDiscountDraft {

  /**
   * The Name.
   */
  private LocalizedString name;

  /**
   * The Description.
   */
  private LocalizedString description;

  /**
   * The Value.
   */
  private CartDiscountValue value;

  /**
   * A valid CartDiscount predicate.
   */
  private String cartPredicate;

  /**
   * The Target.
   */
  private CartDiscountTarget target;


  /**
   * The string must contain a number between 0 and 1.
   * A discount with greater sort order is prioritized higher than a discount with lower sort order.
   * The sort order must be unambiguous among all cart discounts.
   */
  private String sortOrder;

  /**
   * Only active discount can be applied to the cart.
   */
  private Boolean isActive;

  /**
   * The Valid from.
   */
  private ZonedDateTime validFrom;

  /**
   * The Valid until.
   */
  private ZonedDateTime validUntil;

  /**
   * States whether the discount can only be used in a connection with a DiscountCode.
   */
  private Boolean requiresDiscountCode;
}