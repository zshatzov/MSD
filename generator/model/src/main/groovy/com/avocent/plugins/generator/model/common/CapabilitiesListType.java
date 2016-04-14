
package com.avocent.plugins.generator.model.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CapabilitiesListType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CapabilitiesListType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PowerOn"/>
 *     &lt;enumeration value="PowerOff"/>
 *     &lt;enumeration value="PowerCycle"/>
 *     &lt;enumeration value="Reboot"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CapabilitiesListType", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
@XmlEnum
public enum CapabilitiesListType {

    @XmlEnumValue("PowerOn")
    POWER_ON("PowerOn"),
    @XmlEnumValue("PowerOff")
    POWER_OFF("PowerOff"),
    @XmlEnumValue("PowerCycle")
    POWER_CYCLE("PowerCycle"),
    @XmlEnumValue("Reboot")
    REBOOT("Reboot");
    private final String value;

    CapabilitiesListType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CapabilitiesListType fromValue(String v) {
        for (CapabilitiesListType c: CapabilitiesListType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
