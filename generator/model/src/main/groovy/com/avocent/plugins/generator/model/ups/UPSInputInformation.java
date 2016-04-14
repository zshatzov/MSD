
package com.avocent.plugins.generator.model.ups;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UPSInputInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UPSInputInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="upsInputLineBads" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsInputNumLines" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsInputFrequency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsInputVoltage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsInputCurrent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsInputTruePower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UPSInputInformationType", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", propOrder = {
    "upsInputLineBads",
    "upsInputNumLines",
    "upsInputFrequency",
    "upsInputVoltage",
    "upsInputCurrent",
    "upsInputTruePower"
})
public class UPSInputInformation {

    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsInputLineBads;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsInputNumLines;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsInputFrequency;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsInputVoltage;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsInputCurrent;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsInputTruePower;

    /**
     * Gets the value of the upsInputLineBads property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsInputLineBads() {
        return upsInputLineBads;
    }

    /**
     * Sets the value of the upsInputLineBads property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsInputLineBads(String value) {
        this.upsInputLineBads = value;
    }

    /**
     * Gets the value of the upsInputNumLines property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsInputNumLines() {
        return upsInputNumLines;
    }

    /**
     * Sets the value of the upsInputNumLines property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsInputNumLines(String value) {
        this.upsInputNumLines = value;
    }

    /**
     * Gets the value of the upsInputFrequency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsInputFrequency() {
        return upsInputFrequency;
    }

    /**
     * Sets the value of the upsInputFrequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsInputFrequency(String value) {
        this.upsInputFrequency = value;
    }

    /**
     * Gets the value of the upsInputVoltage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsInputVoltage() {
        return upsInputVoltage;
    }

    /**
     * Sets the value of the upsInputVoltage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsInputVoltage(String value) {
        this.upsInputVoltage = value;
    }

    /**
     * Gets the value of the upsInputCurrent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsInputCurrent() {
        return upsInputCurrent;
    }

    /**
     * Sets the value of the upsInputCurrent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsInputCurrent(String value) {
        this.upsInputCurrent = value;
    }

    /**
     * Gets the value of the upsInputTruePower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsInputTruePower() {
        return upsInputTruePower;
    }

    /**
     * Sets the value of the upsInputTruePower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsInputTruePower(String value) {
        this.upsInputTruePower = value;
    }

}
