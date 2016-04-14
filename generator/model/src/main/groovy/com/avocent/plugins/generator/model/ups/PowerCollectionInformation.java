
package com.avocent.plugins.generator.model.ups;

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
 *         &lt;element name="PropertyInformation" type="{http://www.emerson.com/CPGMIBMappingForUPSSchema}PropertyInformationType"/>
 *         &lt;element name="EnvironmentInformation" type="{http://www.emerson.com/CPGMIBMappingForUPSSchema}EnvironmentInformationType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PowerCollectionInformationType", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", propOrder = {
    "propertyInformation",
    "environmentInformation"
})
public class PowerCollectionInformation {

    @XmlElement(name = "PropertyInformation", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected PropertyInformation propertyInformation;
    @XmlElement(name = "EnvironmentInformation", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
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
