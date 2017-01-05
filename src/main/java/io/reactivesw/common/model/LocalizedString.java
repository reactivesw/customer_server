package io.reactivesw.common.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.reactivesw.common.util.LocalizedStringSerializer;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Davis on 16/11/16.
 */
@JsonSerialize(using = LocalizedStringSerializer.class)
@Data
public class LocalizedString {

  /**
   * The Localized.
   */
  private Map<String, String> localized;

  /**
   * private contructor.
   */
  public LocalizedString() {
    if (localized == null) {
      localized = new HashMap<>();
    }
  }

  @Override
  public String toString() {
    return "LocalizedString{" +
        "localized=" + localized +
        '}';
  }

  @JsonCreator
  public LocalizedString(Map<String, String> localized) {
    this.localized = localized;
  }

  /**
   * Gets localized.
   *
   * @return the localized
   */
  public Map<String, String> getLocalized() {
    return localized;
  }

  /**
   * Sets localized.
   *
   * @param localized the localized
   */
  @JsonCreator
  public void setLocalized(Map<String, String> localized) {
    this.localized = localized;
  }

  /**
   * Add key value string.
   *
   * @param key   the key
   * @param value the value
   * @return the string
   */
  public void addKeyValue(String key, String value) {
    localized.put(key, value);
  }

  /**
   * Clear.
   */
  public void clear() {
    localized.clear();
  }
}
