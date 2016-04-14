
package com.avocent.plugins.generator.model.common;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CustomCodeInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomCodeInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FirmwareUpgrade" type="{http://www.emerson.com/CPGMIBMappingCommonTypes}FirmwareUpgradeType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomCodeInformationType", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", propOrder = {
    "firmwareUpgrade"
})
public class CustomCodeInformation {

    @XmlElement(name = "FirmwareUpgrade", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    @Valid
    protected FirmwareUpgrade firmwareUpgrade;

    /**
     * Gets the value of the firmwareUpgrade property.
     * 
     * @return
     *     possible object is
     *     {@link FirmwareUpgrade }
     *     
     */
    public FirmwareUpgrade getFirmwareUpgrade() {
        return firmwareUpgrade;
    }

    /**
     * Sets the value of the firmwareUpgrade property.
     * 
     * @param value
     *     allowed object is
     *     {@link FirmwareUpgrade }
     *     
     */
    public void setFirmwareUpgrade(FirmwareUpgrade value) {
        this.firmwareUpgrade = value;
    }

}
