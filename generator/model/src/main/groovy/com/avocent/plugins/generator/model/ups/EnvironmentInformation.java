
package com.avocent.plugins.generator.model.ups;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnvironmentInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EnvironmentInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EnvironmentTemperature" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TempUnitOfMeasurement">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Celsius"/>
 *               &lt;enumeration value="Fahrenheit"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="EnvironmentHumidity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnvironmentInformationType", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", propOrder = {
    "environmentTemperature",
    "tempUnitOfMeasurement",
    "environmentHumidity"
})
public class EnvironmentInformation {

    @XmlElement(name = "EnvironmentTemperature", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected String environmentTemperature;
    @XmlElement(name = "TempUnitOfMeasurement", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected String tempUnitOfMeasurement;
    @XmlElement(name = "EnvironmentHumidity", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected String environmentHumidity;

    /**
     * Gets the value of the environmentTemperature property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnvironmentTemperature() {
        return environmentTemperature;
    }

    /**
     * Sets the value of the environmentTemperature property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnvironmentTemperature(String value) {
        this.environmentTemperature = value;
    }

    /**
     * Gets the value of the tempUnitOfMeasurement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTempUnitOfMeasurement() {
        return tempUnitOfMeasurement;
    }

    /**
     * Sets the value of the tempUnitOfMeasurement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTempUnitOfMeasurement(String value) {
        this.tempUnitOfMeasurement = value;
    }

    /**
     * Gets the value of the environmentHumidity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnvironmentHumidity() {
        return environmentHumidity;
    }

    /**
     * Sets the value of the environmentHumidity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnvironmentHumidity(String value) {
        this.environmentHumidity = value;
    }

}
