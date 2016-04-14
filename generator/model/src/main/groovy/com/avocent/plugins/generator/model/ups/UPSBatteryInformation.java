
package com.avocent.plugins.generator.model.ups;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UPSBatteryInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UPSBatteryInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="upsBatteryStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsSecondsOnBattery" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsEstimatedMinutesRemaining" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsEstimatedChargeRemaining" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsBatteryVoltage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsBatteryCurrent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsBatteryTemperature" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UPSBatteryInformationType", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", propOrder = {
    "upsBatteryStatus",
    "upsSecondsOnBattery",
    "upsEstimatedMinutesRemaining",
    "upsEstimatedChargeRemaining",
    "upsBatteryVoltage",
    "upsBatteryCurrent",
    "upsBatteryTemperature"
})
public class UPSBatteryInformation {

    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsBatteryStatus;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsSecondsOnBattery;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsEstimatedMinutesRemaining;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsEstimatedChargeRemaining;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsBatteryVoltage;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsBatteryCurrent;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsBatteryTemperature;

    /**
     * Gets the value of the upsBatteryStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsBatteryStatus() {
        return upsBatteryStatus;
    }

    /**
     * Sets the value of the upsBatteryStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsBatteryStatus(String value) {
        this.upsBatteryStatus = value;
    }

    /**
     * Gets the value of the upsSecondsOnBattery property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsSecondsOnBattery() {
        return upsSecondsOnBattery;
    }

    /**
     * Sets the value of the upsSecondsOnBattery property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsSecondsOnBattery(String value) {
        this.upsSecondsOnBattery = value;
    }

    /**
     * Gets the value of the upsEstimatedMinutesRemaining property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsEstimatedMinutesRemaining() {
        return upsEstimatedMinutesRemaining;
    }

    /**
     * Sets the value of the upsEstimatedMinutesRemaining property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsEstimatedMinutesRemaining(String value) {
        this.upsEstimatedMinutesRemaining = value;
    }

    /**
     * Gets the value of the upsEstimatedChargeRemaining property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsEstimatedChargeRemaining() {
        return upsEstimatedChargeRemaining;
    }

    /**
     * Sets the value of the upsEstimatedChargeRemaining property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsEstimatedChargeRemaining(String value) {
        this.upsEstimatedChargeRemaining = value;
    }

    /**
     * Gets the value of the upsBatteryVoltage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsBatteryVoltage() {
        return upsBatteryVoltage;
    }

    /**
     * Sets the value of the upsBatteryVoltage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsBatteryVoltage(String value) {
        this.upsBatteryVoltage = value;
    }

    /**
     * Gets the value of the upsBatteryCurrent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsBatteryCurrent() {
        return upsBatteryCurrent;
    }

    /**
     * Sets the value of the upsBatteryCurrent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsBatteryCurrent(String value) {
        this.upsBatteryCurrent = value;
    }

    /**
     * Gets the value of the upsBatteryTemperature property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsBatteryTemperature() {
        return upsBatteryTemperature;
    }

    /**
     * Sets the value of the upsBatteryTemperature property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsBatteryTemperature(String value) {
        this.upsBatteryTemperature = value;
    }

}
