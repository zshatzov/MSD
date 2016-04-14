
package com.avocent.plugins.generator.model.pdu;

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
 *         &lt;element name="AppliancePropertiesInformation" type="{http://www.emerson.com/CPGMIBMappingForPDUSchema}AppliancePropertiesInformationType"/>
 *         &lt;element name="PDUInformation" type="{http://www.emerson.com/CPGMIBMappingForPDUSchema}PDUInformationType"/>
 *         &lt;element name="OutletInformation" type="{http://www.emerson.com/CPGMIBMappingForPDUSchema}OutletInformationType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DiscoveryFeatureInformationType", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", propOrder = {
    "appliancePropertiesInformation",
    "pduInformation",
    "outletInformation"
})
public class DiscoveryFeatureInformation {

    @XmlElement(name = "AppliancePropertiesInformation", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected AppliancePropertiesInformation appliancePropertiesInformation;
    @XmlElement(name = "PDUInformation", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected PDUInformation pduInformation;
    @XmlElement(name = "OutletInformation", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected OutletInformation outletInformation;

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
     * Gets the value of the pduInformation property.
     * 
     * @return
     *     possible object is
     *     {@link PDUInformation }
     *     
     */
    public PDUInformation getPDUInformation() {
        return pduInformation;
    }

    /**
     * Sets the value of the pduInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PDUInformation }
     *     
     */
    public void setPDUInformation(PDUInformation value) {
        this.pduInformation = value;
    }

    /**
     * Gets the value of the outletInformation property.
     * 
     * @return
     *     possible object is
     *     {@link OutletInformation }
     *     
     */
    public OutletInformation getOutletInformation() {
        return outletInformation;
    }

    /**
     * Sets the value of the outletInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link OutletInformation }
     *     
     */
    public void setOutletInformation(OutletInformation value) {
        this.outletInformation = value;
    }

}
