
package com.avocent.plugins.generator.model.ups;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UPSInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UPSInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SerialNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ModelNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IsSwitched" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IsMetered" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UPSInformationType", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", propOrder = {
    "id",
    "name",
    "status",
    "serialNumber",
    "modelNumber",
    "isSwitched",
    "isMetered"
})
public class UPSInformation {

    @XmlElement(name = "Id", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected String id;
    @XmlElement(name = "Name", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected String name;
    @XmlElement(name = "Status", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected String status;
    @XmlElement(name = "SerialNumber", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected String serialNumber;
    @XmlElement(name = "ModelNumber", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String modelNumber;
    @XmlElement(name = "IsSwitched", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String isSwitched;
    @XmlElement(name = "IsMetered", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected String isMetered;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the serialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Sets the value of the serialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerialNumber(String value) {
        this.serialNumber = value;
    }

    /**
     * Gets the value of the modelNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModelNumber() {
        return modelNumber;
    }

    /**
     * Sets the value of the modelNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModelNumber(String value) {
        this.modelNumber = value;
    }

    /**
     * Gets the value of the isSwitched property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsSwitched() {
        return isSwitched;
    }

    /**
     * Sets the value of the isSwitched property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsSwitched(String value) {
        this.isSwitched = value;
    }

    /**
     * Gets the value of the isMetered property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsMetered() {
        return isMetered;
    }

    /**
     * Sets the value of the isMetered property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsMetered(String value) {
        this.isMetered = value;
    }

}
