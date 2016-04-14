
package com.avocent.plugins.generator.model.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CategoryType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CategoryType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Firmware"/>
 *     &lt;enumeration value="ConfigTemplate"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CategoryType", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
@XmlEnum
public enum CategoryType {

    @XmlEnumValue("Firmware")
    FIRMWARE("Firmware"),
    @XmlEnumValue("ConfigTemplate")
    CONFIG_TEMPLATE("ConfigTemplate");
    private final String value;

    CategoryType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CategoryType fromValue(String v) {
        for (CategoryType c: CategoryType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
