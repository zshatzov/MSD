
package com.avocent.plugins.generator.model.ups;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UPSBypassInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UPSBypassInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="upsBypassFrequency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsBypassNumLines" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsBypassVoltage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsBypassCurrent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsBypassPower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UPSBypassInformationType", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", propOrder = {
    "upsBypassFrequency",
    "upsBypassNumLines",
    "upsBypassVoltage",
    "upsBypassCurrent",
    "upsBypassPower"
})
public class UPSBypassInformation {

    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsBypassFrequency;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsBypassNumLines;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsBypassVoltage;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsBypassCurrent;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsBypassPower;

    /**
     * Gets the value of the upsBypassFrequency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsBypassFrequency() {
        return upsBypassFrequency;
    }

    /**
     * Sets the value of the upsBypassFrequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsBypassFrequency(String value) {
        this.upsBypassFrequency = value;
    }

    /**
     * Gets the value of the upsBypassNumLines property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsBypassNumLines() {
        return upsBypassNumLines;
    }

    /**
     * Sets the value of the upsBypassNumLines property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsBypassNumLines(String value) {
        this.upsBypassNumLines = value;
    }

    /**
     * Gets the value of the upsBypassVoltage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsBypassVoltage() {
        return upsBypassVoltage;
    }

    /**
     * Sets the value of the upsBypassVoltage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsBypassVoltage(String value) {
        this.upsBypassVoltage = value;
    }

    /**
     * Gets the value of the upsBypassCurrent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsBypassCurrent() {
        return upsBypassCurrent;
    }

    /**
     * Sets the value of the upsBypassCurrent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsBypassCurrent(String value) {
        this.upsBypassCurrent = value;
    }

    /**
     * Gets the value of the upsBypassPower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsBypassPower() {
        return upsBypassPower;
    }

    /**
     * Sets the value of the upsBypassPower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsBypassPower(String value) {
        this.upsBypassPower = value;
    }

}
