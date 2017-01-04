package io.reactivesw.order.cart.application.model;

import io.reactivesw.common.model.Money;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by umasuo on 16/11/17.
 */
@ApiModel(description = "Represents the portions that sum up to the totalGross field of a " +
    "TaxedPrice. " +
    "The portions are calculated from the TaxRates. " +
    "If a tax rate has SubRates, they are used and can be identified by name. " +
    "Tax portions from line items that have the same rate and name will be accumulated to the " +
    "same tax portion.\n" +
    "\n")
@Data
public class TaxPortion {

  @ApiModelProperty(required = false)
  private String name;

  @ApiModelProperty(value = "A number in the range [0..1]", required = true)
  private Float rate;

  @ApiModelProperty(required = true)
  private Money amount;
}
