package io.reactivesw.catalog.products.models.attributes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.reactivesw.common.enums.LocalizedEnumValue;

import java.util.List;

import static io.reactivesw.common.utils.ReactiveswInternalUtils.listOf;

public final class LocalizedEnumAttributeType extends AttributeTypeBase {
    private final List<LocalizedEnumValue> values;

    @JsonCreator
    private LocalizedEnumAttributeType(final List<LocalizedEnumValue> values) {
        this.values = values;
    }

    public List<LocalizedEnumValue> getValues() {
        return values;
    }

    @JsonIgnore
    public static LocalizedEnumAttributeType of(final LocalizedEnumValue mandatoryValue, final LocalizedEnumValue ... moreValues) {
        final List<LocalizedEnumValue> localizedEnumValues = listOf(mandatoryValue, moreValues);
        return of(localizedEnumValues);
    }

    @JsonIgnore
    public static LocalizedEnumAttributeType of(final List<LocalizedEnumValue> values) {
        return new LocalizedEnumAttributeType(values);
    }
}
