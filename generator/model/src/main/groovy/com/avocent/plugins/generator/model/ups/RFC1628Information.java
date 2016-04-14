
package com.avocent.plugins.generator.model.ups;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Follows the RFC1628 MIB layout.
 * 			
 * 
 * <p>Java class for RFC1628Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RFC1628Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="upsIdent" type="{http://www.emerson.com/CPGMIBMappingForUPSSchema}UPSIdentType"/>
 *         &lt;element name="upsBatteryInformation" type="{http://www.emerson.com/CPGMIBMappingForUPSSchema}UPSBatteryInformationType"/>
 *         &lt;element name="upsInputInformation" type="{http://www.emerson.com/CPGMIBMappingForUPSSchema}UPSInputInformationType"/>
 *         &lt;element name="upsOutputInformation" type="{http://www.emerson.com/CPGMIBMappingForUPSSchema}UPSOutputInformationType"/>
 *         &lt;element name="upsBypassInformation" type="{http://www.emerson.com/CPGMIBMappingForUPSSchema}UPSBypassInformationType"/>
 *         &lt;element name="upsControlInformation" type="{http://www.emerson.com/CPGMIBMappingForUPSSchema}UPSControlInformationType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RFC1628Type", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", propOrder = {
    "upsIdent",
    "upsBatteryInformation",
    "upsInputInformation",
    "upsOutputInformation",
    "upsBypassInformation",
    "upsControlInformation"
})
public class RFC1628Information {

    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected UPSIdent upsIdent;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected UPSBatteryInformation upsBatteryInformation;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected UPSInputInformation upsInputInformation;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected UPSOutputInformation upsOutputInformation;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected UPSBypassInformation upsBypassInformation;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected UPSControlInformation upsControlInformation;

    /**
     * Gets the value of the upsIdent property.
     * 
     * @return
     *     possible object is
     *     {@link UPSIdent }
     *     
     */
    public UPSIdent getUpsIdent() {
        return upsIdent;
    }

    /**
     * Sets the value of the upsIdent property.
     * 
     * @param value
     *     allowed object is
     *     {@link UPSIdent }
     *     
     */
    public void setUpsIdent(UPSIdent value) {
        this.upsIdent = value;
    }

    /**
     * Gets the value of the upsBatteryInformation property.
     * 
     * @return
     *     possible object is
     *     {@link UPSBatteryInformation }
     *     
     */
    public UPSBatteryInformation getUpsBatteryInformation() {
        return upsBatteryInformation;
    }

    /**
     * Sets the value of the upsBatteryInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link UPSBatteryInformation }
     *     
     */
    public void setUpsBatteryInformation(UPSBatteryInformation value) {
        this.upsBatteryInformation = value;
    }

    /**
     * Gets the value of the upsInputInformation property.
     * 
     * @return
     *     possible object is
     *     {@link UPSInputInformation }
     *     
     */
    public UPSInputInformation getUpsInputInformation() {
        return upsInputInformation;
    }

    /**
     * Sets the value of the upsInputInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link UPSInputInformation }
     *     
     */
    public void setUpsInputInformation(UPSInputInformation value) {
        this.upsInputInformation = value;
    }

    /**
     * Gets the value of the upsOutputInformation property.
     * 
     * @return
     *     possible object is
     *     {@link UPSOutputInformation }
     *     
     */
    public UPSOutputInformation getUpsOutputInformation() {
        return upsOutputInformation;
    }

    /**
     * Sets the value of the upsOutputInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link UPSOutputInformation }
     *     
     */
    public void setUpsOutputInformation(UPSOutputInformation value) {
        this.upsOutputInformation = value;
    }

    /**
     * Gets the value of the upsBypassInformation property.
     * 
     * @return
     *     possible object is
     *     {@link UPSBypassInformation }
     *     
     */
    public UPSBypassInformation getUpsBypassInformation() {
        return upsBypassInformation;
    }

    /**
     * Sets the value of the upsBypassInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link UPSBypassInformation }
     *     
     */
    public void setUpsBypassInformation(UPSBypassInformation value) {
        this.upsBypassInformation = value;
    }

    /**
     * Gets the value of the upsControlInformation property.
     * 
     * @return
     *     possible object is
     *     {@link UPSControlInformation }
     *     
     */
    public UPSControlInformation getUpsControlInformation() {
        return upsControlInformation;
    }

    /**
     * Sets the value of the upsControlInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link UPSControlInformation }
     *     
     */
    public void setUpsControlInformation(UPSControlInformation value) {
        this.upsControlInformation = value;
    }

}
