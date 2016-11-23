package io.reactivesw.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import javax.persistence.AttributeConverter;

import io.reactivesw.catalog.productdiscounts.models.ProductDiscountValue;
import io.reactivesw.common.models.LocalizedString;

/**
 * Created by Davis on 16/11/16.
 */
public class ProductDiscountJsonConverter implements AttributeConverter<ProductDiscountValue, String> {

  private final static ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(ProductDiscountValue meta) {
    try {
      return objectMapper.writeValueAsString(meta);
    } catch (Exception ex) {
      //TODO throws the exceptions
      return null;
    }
  }

  @Override
  public ProductDiscountValue convertToEntityAttribute(String dbData) {
    try {

      return objectMapper.readValue(dbData, ProductDiscountValue.class);
    } catch (IOException ex) {
      //TODO throws the exceptions
      return null;
    }
  }

}
