
package com.avocent.plugins.generator.model.ups;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UPSControlInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UPSControlInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="upsShutdownType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsShutdownAfterDelay" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsStartupAfterDelay" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsRebootWithDuration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsAutoRestart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UPSControlInformationType", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", propOrder = {
    "upsShutdownType",
    "upsShutdownAfterDelay",
    "upsStartupAfterDelay",
    "upsRebootWithDuration",
    "upsAutoRestart"
})
public class UPSControlInformation {

    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsShutdownType;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsShutdownAfterDelay;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsStartupAfterDelay;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsRebootWithDuration;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsAutoRestart;

    /**
     * Gets the value of the upsShutdownType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsShutdownType() {
        return upsShutdownType;
    }

    /**
     * Sets the value of the upsShutdownType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsShutdownType(String value) {
        this.upsShutdownType = value;
    }

    /**
     * Gets the value of the upsShutdownAfterDelay property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsShutdownAfterDelay() {
        return upsShutdownAfterDelay;
    }

    /**
     * Sets the value of the upsShutdownAfterDelay property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsShutdownAfterDelay(String value) {
        this.upsShutdownAfterDelay = value;
    }

    /**
     * Gets the value of the upsStartupAfterDelay property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsStartupAfterDelay() {
        return upsStartupAfterDelay;
    }

    /**
     * Sets the value of the upsStartupAfterDelay property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsStartupAfterDelay(String value) {
        this.upsStartupAfterDelay = value;
    }

    /**
     * Gets the value of the upsRebootWithDuration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsRebootWithDuration() {
        return upsRebootWithDuration;
    }

    /**
     * Sets the value of the upsRebootWithDuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsRebootWithDuration(String value) {
        this.upsRebootWithDuration = value;
    }

    /**
     * Gets the value of the upsAutoRestart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsAutoRestart() {
        return upsAutoRestart;
    }

    /**
     * Sets the value of the upsAutoRestart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsAutoRestart(String value) {
        this.upsAutoRestart = value;
    }

}
