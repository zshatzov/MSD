
package com.avocent.plugins.generator.model.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConfigurableRightsListType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ConfigurableRightsListType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="View"/>
 *     &lt;enumeration value="Configure"/>
 *     &lt;enumeration value="ControlTargetDevicePower"/>
 *     &lt;enumeration value="Reboot"/>
 *     &lt;enumeration value="Flash"/>
 *     &lt;enumeration value="AdministerLocalAccounts"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ConfigurableRightsListType", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
@XmlEnum
public enum ConfigurableRightsListType {

    @XmlEnumValue("View")
    VIEW("View"),
    @XmlEnumValue("Configure")
    CONFIGURE("Configure"),
    @XmlEnumValue("ControlTargetDevicePower")
    CONTROL_TARGET_DEVICE_POWER("ControlTargetDevicePower"),
    @XmlEnumValue("Reboot")
    REBOOT("Reboot"),
    @XmlEnumValue("Flash")
    FLASH("Flash"),
    @XmlEnumValue("AdministerLocalAccounts")
    ADMINISTER_LOCAL_ACCOUNTS("AdministerLocalAccounts");
    private final String value;

    ConfigurableRightsListType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ConfigurableRightsListType fromValue(String v) {
        for (ConfigurableRightsListType c: ConfigurableRightsListType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
