
package com.avocent.plugins.generator.model.pdu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PowerOperationsInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PowerOperationsInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PDUCapabilitiesInformation" type="{http://www.emerson.com/CPGMIBMappingForPDUSchema}PDUCapabilitiesInformationType"/>
 *         &lt;element name="PDUOutletsCapabilitiesInformation" type="{http://www.emerson.com/CPGMIBMappingForPDUSchema}PDUOutletsCapabilitiesInformationType"/>
 *         &lt;element name="PDUOperationsInformation" type="{http://www.emerson.com/CPGMIBMappingForPDUSchema}PDUOperationsInformationType"/>
 *         &lt;element name="PDUOutletsOperationsInformation" type="{http://www.emerson.com/CPGMIBMappingForPDUSchema}PDUOutletsOperationsInformationType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PowerOperationsInformationType", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", propOrder = {
    "pduCapabilitiesInformation",
    "pduOutletsCapabilitiesInformation",
    "pduOperationsInformation",
    "pduOutletsOperationsInformation"
})
public class PowerOperationsInformation {

    @XmlElement(name = "PDUCapabilitiesInformation", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected PDUCapabilitiesInformation pduCapabilitiesInformation;
    @XmlElement(name = "PDUOutletsCapabilitiesInformation", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected PDUOutletsCapabilitiesInformation pduOutletsCapabilitiesInformation;
    @XmlElement(name = "PDUOperationsInformation", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected PDUOperationsInformation pduOperationsInformation;
    @XmlElement(name = "PDUOutletsOperationsInformation", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected PDUOutletsOperationsInformation pduOutletsOperationsInformation;

    /**
     * Gets the value of the pduCapabilitiesInformation property.
     * 
     * @return
     *     possible object is
     *     {@link PDUCapabilitiesInformation }
     *     
     */
    public PDUCapabilitiesInformation getPDUCapabilitiesInformation() {
        return pduCapabilitiesInformation;
    }

    /**
     * Sets the value of the pduCapabilitiesInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PDUCapabilitiesInformation }
     *     
     */
    public void setPDUCapabilitiesInformation(PDUCapabilitiesInformation value) {
        this.pduCapabilitiesInformation = value;
    }

    /**
     * Gets the value of the pduOutletsCapabilitiesInformation property.
     * 
     * @return
     *     possible object is
     *     {@link PDUOutletsCapabilitiesInformation }
     *     
     */
    public PDUOutletsCapabilitiesInformation getPDUOutletsCapabilitiesInformation() {
        return pduOutletsCapabilitiesInformation;
    }

    /**
     * Sets the value of the pduOutletsCapabilitiesInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PDUOutletsCapabilitiesInformation }
     *     
     */
    public void setPDUOutletsCapabilitiesInformation(PDUOutletsCapabilitiesInformation value) {
        this.pduOutletsCapabilitiesInformation = value;
    }

    /**
     * Gets the value of the pduOperationsInformation property.
     * 
     * @return
     *     possible object is
     *     {@link PDUOperationsInformation }
     *     
     */
    public PDUOperationsInformation getPDUOperationsInformation() {
        return pduOperationsInformation;
    }

    /**
     * Sets the value of the pduOperationsInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PDUOperationsInformation }
     *     
     */
    public void setPDUOperationsInformation(PDUOperationsInformation value) {
        this.pduOperationsInformation = value;
    }

    /**
     * Gets the value of the pduOutletsOperationsInformation property.
     * 
     * @return
     *     possible object is
     *     {@link PDUOutletsOperationsInformation }
     *     
     */
    public PDUOutletsOperationsInformation getPDUOutletsOperationsInformation() {
        return pduOutletsOperationsInformation;
    }

    /**
     * Sets the value of the pduOutletsOperationsInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PDUOutletsOperationsInformation }
     *     
     */
    public void setPDUOutletsOperationsInformation(PDUOutletsOperationsInformation value) {
        this.pduOutletsOperationsInformation = value;
    }

}
