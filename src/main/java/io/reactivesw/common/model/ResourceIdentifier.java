package io.reactivesw.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Davis on 16/11/17.
 */
@Data
@AllArgsConstructor
public class ResourceIdentifier {
  /**
   * The Type id.
   */
  private String typeId;

  /**
   * The Id.
   */
  private String id;

  /**
   * The Key.
   */
//  private String key;
}
