package io.reactivesw.catalog.product.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.reactivesw.catalog.productdiscount.model.DiscountedPrice;
import io.reactivesw.common.model.CustomFields;
import io.reactivesw.common.model.Money;
import io.reactivesw.common.model.Reference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.ZonedDateTime;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Price {

  @ApiModelProperty(value = "The unique ID of this price.",
      required = true,
      readOnly = true)
  private String id;

  @ApiModelProperty(required = true)
  private Money value;

  @ApiModelProperty(value = "A two-digit country code as per ↗ ISO 3166-1 alpha-2 .")
  private String country;

  @ApiModelProperty(value = "A reference to a customer group.")
  private Reference customerGroup;

  @ApiModelProperty(value = "A reference to a channel.")
  private Reference channel;

  @ApiModelProperty(value = "Date from which the price is valid.")
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime validFrom;

  @ApiModelProperty(value = "Date until which the price is valid.")
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime validUntil;

  @ApiModelProperty(value = "Set if a matching ProductDiscount exists. " +
      "If set, the Cart will use the discounted value for the cart price calculation." +
      "When a relative discount is applied and the fraction part of the discounted price is 0.5, " +
      "the discounted price is rounded in favor of the customer with the half down rounding.")
  private DiscountedPrice discounted;

  @ApiModelProperty
  private CustomFields custom;

}
