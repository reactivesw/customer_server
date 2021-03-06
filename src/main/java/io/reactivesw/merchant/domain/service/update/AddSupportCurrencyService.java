package io.reactivesw.merchant.domain.service.update;

import io.reactivesw.common.model.Update;
import io.reactivesw.common.model.UpdateAction;
import io.reactivesw.merchant.application.model.action.AddSupportCurrencyAction;
import io.reactivesw.merchant.application.model.mapper.CurrencyMapper;
import io.reactivesw.merchant.domain.entity.CurrencyValue;
import io.reactivesw.merchant.domain.entity.InternationalEntity;
import io.reactivesw.merchant.infrastructure.util.CurrencyMap;
import io.reactivesw.merchant.infrastructure.util.InternationalActionUtils;
import io.reactivesw.merchant.infrastructure.validator.CurrencyValidator;

import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Davis on 17/1/9.
 */
@Service(value = InternationalActionUtils.ADD_SUPPORT_CURRENCY)
public class AddSupportCurrencyService implements Update<InternationalEntity> {

  /**
   * add support currency.
   *
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(InternationalEntity entity, UpdateAction action) {

    String currencyCode = ((AddSupportCurrencyAction) action).getCurrencyCode();

    CurrencyValidator.validate(currencyCode);

    CurrencyValue currencyValue = CurrencyMapper.modelToEntity(
        CurrencyMap.getCurrencyByCode(currencyCode));

    Set<CurrencyValue> supportCurrencies = entity.getSupportedCurrencies();
    supportCurrencies.add(currencyValue);

    entity.setSupportedCurrencies(supportCurrencies);
  }
}
