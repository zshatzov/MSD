
package com.avocent.plugins.generator.model.ups;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DiscoveryFeatureInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DiscoveryFeatureInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AppliancePropertiesInformation" type="{http://www.emerson.com/CPGMIBMappingForUPSSchema}AppliancePropertiesInformationType"/>
 *         &lt;element name="UPSInformation" type="{http://www.emerson.com/CPGMIBMappingForUPSSchema}UPSInformationType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DiscoveryFeatureInformationType", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", propOrder = {
    "appliancePropertiesInformation",
    "upsInformation"
})
public class DiscoveryFeatureInformation {

    @XmlElement(name = "AppliancePropertiesInformation", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected AppliancePropertiesInformation appliancePropertiesInformation;
    @XmlElement(name = "UPSInformation", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected UPSInformation upsInformation;

    /**
     * Gets the value of the appliancePropertiesInformation property.
     * 
     * @return
     *     possible object is
     *     {@link AppliancePropertiesInformation }
     *     
     */
    public AppliancePropertiesInformation getAppliancePropertiesInformation() {
        return appliancePropertiesInformation;
    }

    /**
     * Sets the value of the appliancePropertiesInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link AppliancePropertiesInformation }
     *     
     */
    public void setAppliancePropertiesInformation(AppliancePropertiesInformation value) {
        this.appliancePropertiesInformation = value;
    }

    /**
     * Gets the value of the upsInformation property.
     * 
     * @return
     *     possible object is
     *     {@link UPSInformation }
     *     
     */
    public UPSInformation getUPSInformation() {
        return upsInformation;
    }

    /**
     * Sets the value of the upsInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link UPSInformation }
     *     
     */
    public void setUPSInformation(UPSInformation value) {
        this.upsInformation = value;
    }

}
