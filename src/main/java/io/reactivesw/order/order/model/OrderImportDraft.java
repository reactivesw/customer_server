package io.reactivesw.order.order.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.reactivesw.common.model.Address;
import io.reactivesw.common.model.CustomFieldsDraft;
import io.reactivesw.common.model.Money;
import io.reactivesw.common.model.Reference;
import io.reactivesw.order.cart.application.model.ShippingInfo;
import io.reactivesw.order.cart.application.model.TaxedPrice;
import io.reactivesw.order.cart.infrastructure.enums.InventoryMode;
import io.reactivesw.order.order.enums.OrderState;
import io.reactivesw.order.order.enums.PaymentState;
import io.reactivesw.order.order.enums.ShipmentState;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by Davis on 16/11/17.
 */
@Data
public class OrderImportDraft {

  /**
   * String that unique identifies an order.
   * It can be used to create more human-readable (in contrast to ID) identifier for the order.
   * It should be unique within a project.
   */
  private String orderNumber;

  /**
   * The Customer id.
   */
  private String customerId;

  /**
   * The Customer email.
   */
  private String customerEmail;

  /**
   * Array of LineItemImportDraft.
   * If not given customLineItems must not be empty.
   * Optional.
   */
  private List<LineItemImportDraft> lineItems;

  /**
   * Array of CustomLineItem.
   * If not given lineItems must not be empty.
   * Optional.
   */
//  private List<CustomLineItem> customLineItems;

  /**
   * The Total price.
   */
  private Money totalPrice;

  /**
   * The taxes are calculated based on the shipping address.
   */
  private TaxedPrice taxedPrice;


  /**
   * The Shipping address.
   */
  private Address shippingAddress;

  /**
   * The Billing address.
   */
  private Address billingAddress;

  /**
   * Reference to a CustomerGroup.
   * Set when the customer is set and the customer is a member of a customer group.
   * Used for product variant price selection.
   * Optional.
   */
  private Reference customerGroup;

  /**
   * A two-digit country code as per ↗ ISO 3166-1 alpha-2 .
   * Used for product variant price selection.
   */
  private String country;

  /**
   * If not given the Open state will be assigned by default.
   */
  private OrderState orderState;

  /**
   * The Shipment state.
   */
  private ShipmentState shipmentState;

  /**
   * The Payment state.
   */
  private PaymentState paymentState;

  /**
   * Set if the ShippingMethod is set.
   */
  private ShippingInfo shippingInfo;

  /**
   * The Completed at.
   */
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime completedAt;

  /***
   * The custom fields.
   */
  private CustomFieldsDraft custom;

  /**
   * If not given the mode None will be assigned by default.
   */
  private InventoryMode inventoryMode;
}
