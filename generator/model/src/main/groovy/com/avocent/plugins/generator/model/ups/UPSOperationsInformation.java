
package com.avocent.plugins.generator.model.ups;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UPSOperationsInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UPSOperationsInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PowerOnOperationMapping" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PowerOffOperationMapping" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PowerCycleOperationMapping" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UPSOperationsInformationType", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", propOrder = {
    "powerOnOperationMapping",
    "powerOffOperationMapping",
    "powerCycleOperationMapping"
})
public class UPSOperationsInformation {

    @XmlElement(name = "PowerOnOperationMapping", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected String powerOnOperationMapping;
    @XmlElement(name = "PowerOffOperationMapping", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected String powerOffOperationMapping;
    @XmlElement(name = "PowerCycleOperationMapping", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected String powerCycleOperationMapping;

    /**
     * Gets the value of the powerOnOperationMapping property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPowerOnOperationMapping() {
        return powerOnOperationMapping;
    }

    /**
     * Sets the value of the powerOnOperationMapping property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPowerOnOperationMapping(String value) {
        this.powerOnOperationMapping = value;
    }

    /**
     * Gets the value of the powerOffOperationMapping property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPowerOffOperationMapping() {
        return powerOffOperationMapping;
    }

    /**
     * Sets the value of the powerOffOperationMapping property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPowerOffOperationMapping(String value) {
        this.powerOffOperationMapping = value;
    }

    /**
     * Gets the value of the powerCycleOperationMapping property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPowerCycleOperationMapping() {
        return powerCycleOperationMapping;
    }

    /**
     * Sets the value of the powerCycleOperationMapping property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPowerCycleOperationMapping(String value) {
        this.powerCycleOperationMapping = value;
    }

}
