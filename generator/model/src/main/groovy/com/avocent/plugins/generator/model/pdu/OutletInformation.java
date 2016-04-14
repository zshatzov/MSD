
package com.avocent.plugins.generator.model.pdu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OutletInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OutletInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Capabilities" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsSwitched" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IsMetered" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OutletInformationType", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", propOrder = {
    "id",
    "name",
    "capabilities",
    "status",
    "isSwitched",
    "isMetered"
})
public class OutletInformation {

    @XmlElement(name = "Id", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected String id;
    @XmlElement(name = "Name", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected String name;
    @XmlElement(name = "Capabilities", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema")
    protected String capabilities;
    @XmlElement(name = "Status", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected String status;
    @XmlElement(name = "IsSwitched", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema")
    protected String isSwitched;
    @XmlElement(name = "IsMetered", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema")
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
     * Gets the value of the capabilities property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCapabilities() {
        return capabilities;
    }

    /**
     * Sets the value of the capabilities property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCapabilities(String value) {
        this.capabilities = value;
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
