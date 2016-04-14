
package com.avocent.plugins.generator.model.pdu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PowerCollectionInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PowerCollectionInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PropertyInformation" type="{http://www.emerson.com/CPGMIBMappingForPDUSchema}PropertyInformationType"/>
 *         &lt;element name="PowerOverallInformation" type="{http://www.emerson.com/CPGMIBMappingForPDUSchema}PowerOverallInformationType"/>
 *         &lt;element name="PowerPerCircuitInformation" type="{http://www.emerson.com/CPGMIBMappingForPDUSchema}PowerPerCircuitInformationType"/>
 *         &lt;element name="PowerPerPhaseInformation" type="{http://www.emerson.com/CPGMIBMappingForPDUSchema}PowerPerPhaseInformationType"/>
 *         &lt;element name="PowerPerOutletInformation" type="{http://www.emerson.com/CPGMIBMappingForPDUSchema}PowerPerOutletInformationType"/>
 *         &lt;element name="EnvironmentInformation" type="{http://www.emerson.com/CPGMIBMappingForPDUSchema}EnvironmentInformationType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PowerCollectionInformationType", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", propOrder = {
    "propertyInformation",
    "powerOverallInformation",
    "powerPerCircuitInformation",
    "powerPerPhaseInformation",
    "powerPerOutletInformation",
    "environmentInformation"
})
public class PowerCollectionInformation {

    @XmlElement(name = "PropertyInformation", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected PropertyInformation propertyInformation;
    @XmlElement(name = "PowerOverallInformation", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected PowerOverallInformation powerOverallInformation;
    @XmlElement(name = "PowerPerCircuitInformation", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected PowerPerCircuitInformation powerPerCircuitInformation;
    @XmlElement(name = "PowerPerPhaseInformation", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected PowerPerPhaseInformation powerPerPhaseInformation;
    @XmlElement(name = "PowerPerOutletInformation", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected PowerPerOutletInformation powerPerOutletInformation;
    @XmlElement(name = "EnvironmentInformation", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected EnvironmentInformation environmentInformation;

    /**
     * Gets the value of the propertyInformation property.
     * 
     * @return
     *     possible object is
     *     {@link PropertyInformation }
     *     
     */
    public PropertyInformation getPropertyInformation() {
        return propertyInformation;
    }

    /**
     * Sets the value of the propertyInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertyInformation }
     *     
     */
    public void setPropertyInformation(PropertyInformation value) {
        this.propertyInformation = value;
    }

    /**
     * Gets the value of the powerOverallInformation property.
     * 
     * @return
     *     possible object is
     *     {@link PowerOverallInformation }
     *     
     */
    public PowerOverallInformation getPowerOverallInformation() {
        return powerOverallInformation;
    }

    /**
     * Sets the value of the powerOverallInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PowerOverallInformation }
     *     
     */
    public void setPowerOverallInformation(PowerOverallInformation value) {
        this.powerOverallInformation = value;
    }

    /**
     * Gets the value of the powerPerCircuitInformation property.
     * 
     * @return
     *     possible object is
     *     {@link PowerPerCircuitInformation }
     *     
     */
    public PowerPerCircuitInformation getPowerPerCircuitInformation() {
        return powerPerCircuitInformation;
    }

    /**
     * Sets the value of the powerPerCircuitInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PowerPerCircuitInformation }
     *     
     */
    public void setPowerPerCircuitInformation(PowerPerCircuitInformation value) {
        this.powerPerCircuitInformation = value;
    }

    /**
     * Gets the value of the powerPerPhaseInformation property.
     * 
     * @return
     *     possible object is
     *     {@link PowerPerPhaseInformation }
     *     
     */
    public PowerPerPhaseInformation getPowerPerPhaseInformation() {
        return powerPerPhaseInformation;
    }

    /**
     * Sets the value of the powerPerPhaseInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PowerPerPhaseInformation }
     *     
     */
    public void setPowerPerPhaseInformation(PowerPerPhaseInformation value) {
        this.powerPerPhaseInformation = value;
    }

    /**
     * Gets the value of the powerPerOutletInformation property.
     * 
     * @return
     *     possible object is
     *     {@link PowerPerOutletInformation }
     *     
     */
    public PowerPerOutletInformation getPowerPerOutletInformation() {
        return powerPerOutletInformation;
    }

    /**
     * Sets the value of the powerPerOutletInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PowerPerOutletInformation }
     *     
     */
    public void setPowerPerOutletInformation(PowerPerOutletInformation value) {
        this.powerPerOutletInformation = value;
    }

    /**
     * Gets the value of the environmentInformation property.
     * 
     * @return
     *     possible object is
     *     {@link EnvironmentInformation }
     *     
     */
    public EnvironmentInformation getEnvironmentInformation() {
        return environmentInformation;
    }

    /**
     * Sets the value of the environmentInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnvironmentInformation }
     *     
     */
    public void setEnvironmentInformation(EnvironmentInformation value) {
        this.environmentInformation = value;
    }

}
