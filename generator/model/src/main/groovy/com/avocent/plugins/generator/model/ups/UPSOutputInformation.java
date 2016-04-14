
package com.avocent.plugins.generator.model.ups;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UPSOutputInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UPSOutputInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="upsOutputSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsOutputFrequency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsOutputNumLines" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsOutputVoltage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsOutputCurrent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsOutputPower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="upsOutputPercentLoad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UPSOutputInformationType", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", propOrder = {
    "upsOutputSource",
    "upsOutputFrequency",
    "upsOutputNumLines",
    "upsOutputVoltage",
    "upsOutputCurrent",
    "upsOutputPower",
    "upsOutputPercentLoad"
})
public class UPSOutputInformation {

    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsOutputSource;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsOutputFrequency;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsOutputNumLines;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsOutputVoltage;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsOutputCurrent;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsOutputPower;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String upsOutputPercentLoad;

    /**
     * Gets the value of the upsOutputSource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsOutputSource() {
        return upsOutputSource;
    }

    /**
     * Sets the value of the upsOutputSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsOutputSource(String value) {
        this.upsOutputSource = value;
    }

    /**
     * Gets the value of the upsOutputFrequency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsOutputFrequency() {
        return upsOutputFrequency;
    }

    /**
     * Sets the value of the upsOutputFrequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsOutputFrequency(String value) {
        this.upsOutputFrequency = value;
    }

    /**
     * Gets the value of the upsOutputNumLines property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsOutputNumLines() {
        return upsOutputNumLines;
    }

    /**
     * Sets the value of the upsOutputNumLines property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsOutputNumLines(String value) {
        this.upsOutputNumLines = value;
    }

    /**
     * Gets the value of the upsOutputVoltage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsOutputVoltage() {
        return upsOutputVoltage;
    }

    /**
     * Sets the value of the upsOutputVoltage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsOutputVoltage(String value) {
        this.upsOutputVoltage = value;
    }

    /**
     * Gets the value of the upsOutputCurrent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsOutputCurrent() {
        return upsOutputCurrent;
    }

    /**
     * Sets the value of the upsOutputCurrent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsOutputCurrent(String value) {
        this.upsOutputCurrent = value;
    }

    /**
     * Gets the value of the upsOutputPower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsOutputPower() {
        return upsOutputPower;
    }

    /**
     * Sets the value of the upsOutputPower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsOutputPower(String value) {
        this.upsOutputPower = value;
    }

    /**
     * Gets the value of the upsOutputPercentLoad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsOutputPercentLoad() {
        return upsOutputPercentLoad;
    }

    /**
     * Sets the value of the upsOutputPercentLoad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsOutputPercentLoad(String value) {
        this.upsOutputPercentLoad = value;
    }

}
