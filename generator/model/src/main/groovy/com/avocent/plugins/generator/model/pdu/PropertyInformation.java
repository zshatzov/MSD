
package com.avocent.plugins.generator.model.pdu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PropertyInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PropertyInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Vendor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Model" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NumberOfOutlets" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OutletId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsThreePhasePDU" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NumberOfOutletsPerCircuit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NumberOfCircuits" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropertyInformationType", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", propOrder = {
    "vendor",
    "model",
    "numberOfOutlets",
    "outletId",
    "isThreePhasePDU",
    "numberOfOutletsPerCircuit",
    "numberOfCircuits"
})
public class PropertyInformation {

    @XmlElement(name = "Vendor", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected String vendor;
    @XmlElement(name = "Model", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected String model;
    @XmlElement(name = "NumberOfOutlets", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected String numberOfOutlets;
    @XmlElement(name = "OutletId", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected String outletId;
    @XmlElement(name = "IsThreePhasePDU", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected String isThreePhasePDU;
    @XmlElement(name = "NumberOfOutletsPerCircuit", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected String numberOfOutletsPerCircuit;
    @XmlElement(name = "NumberOfCircuits", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected String numberOfCircuits;

    /**
     * Gets the value of the vendor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * Sets the value of the vendor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendor(String value) {
        this.vendor = value;
    }

    /**
     * Gets the value of the model property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the value of the model property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModel(String value) {
        this.model = value;
    }

    /**
     * Gets the value of the numberOfOutlets property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumberOfOutlets() {
        return numberOfOutlets;
    }

    /**
     * Sets the value of the numberOfOutlets property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumberOfOutlets(String value) {
        this.numberOfOutlets = value;
    }

    /**
     * Gets the value of the outletId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutletId() {
        return outletId;
    }

    /**
     * Sets the value of the outletId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutletId(String value) {
        this.outletId = value;
    }

    /**
     * Gets the value of the isThreePhasePDU property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsThreePhasePDU() {
        return isThreePhasePDU;
    }

    /**
     * Sets the value of the isThreePhasePDU property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsThreePhasePDU(String value) {
        this.isThreePhasePDU = value;
    }

    /**
     * Gets the value of the numberOfOutletsPerCircuit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumberOfOutletsPerCircuit() {
        return numberOfOutletsPerCircuit;
    }

    /**
     * Sets the value of the numberOfOutletsPerCircuit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumberOfOutletsPerCircuit(String value) {
        this.numberOfOutletsPerCircuit = value;
    }

    /**
     * Gets the value of the numberOfCircuits property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumberOfCircuits() {
        return numberOfCircuits;
    }

    /**
     * Sets the value of the numberOfCircuits property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumberOfCircuits(String value) {
        this.numberOfCircuits = value;
    }

}
