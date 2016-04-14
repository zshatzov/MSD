
package com.avocent.plugins.generator.model.ups;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AppliancePropertiesInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AppliancePropertiesInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SysObjectId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SysDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SysContact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SysName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SysLocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SysSerialNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SysIdentModel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SysVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppliancePropertiesInformationType", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", propOrder = {
    "sysObjectId",
    "sysDescription",
    "sysContact",
    "sysName",
    "sysLocation",
    "sysSerialNo",
    "sysIdentModel",
    "sysVersion"
})
public class AppliancePropertiesInformation {

    @XmlElement(name = "SysObjectId", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", defaultValue = "sysObjectID")
    protected String sysObjectId;
    @XmlElement(name = "SysDescription", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String sysDescription;
    @XmlElement(name = "SysContact", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String sysContact;
    @XmlElement(name = "SysName", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String sysName;
    @XmlElement(name = "SysLocation", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String sysLocation;
    @XmlElement(name = "SysSerialNo", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String sysSerialNo;
    @XmlElement(name = "SysIdentModel", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String sysIdentModel;
    @XmlElement(name = "SysVersion", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema")
    protected String sysVersion;

    /**
     * Gets the value of the sysObjectId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysObjectId() {
        return sysObjectId;
    }

    /**
     * Sets the value of the sysObjectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysObjectId(String value) {
        this.sysObjectId = value;
    }

    /**
     * Gets the value of the sysDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysDescription() {
        return sysDescription;
    }

    /**
     * Sets the value of the sysDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysDescription(String value) {
        this.sysDescription = value;
    }

    /**
     * Gets the value of the sysContact property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysContact() {
        return sysContact;
    }

    /**
     * Sets the value of the sysContact property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysContact(String value) {
        this.sysContact = value;
    }

    /**
     * Gets the value of the sysName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysName() {
        return sysName;
    }

    /**
     * Sets the value of the sysName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysName(String value) {
        this.sysName = value;
    }

    /**
     * Gets the value of the sysLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysLocation() {
        return sysLocation;
    }

    /**
     * Sets the value of the sysLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysLocation(String value) {
        this.sysLocation = value;
    }

    /**
     * Gets the value of the sysSerialNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysSerialNo() {
        return sysSerialNo;
    }

    /**
     * Sets the value of the sysSerialNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysSerialNo(String value) {
        this.sysSerialNo = value;
    }

    /**
     * Gets the value of the sysIdentModel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysIdentModel() {
        return sysIdentModel;
    }

    /**
     * Sets the value of the sysIdentModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysIdentModel(String value) {
        this.sysIdentModel = value;
    }

    /**
     * Gets the value of the sysVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSysVersion() {
        return sysVersion;
    }

    /**
     * Sets the value of the sysVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSysVersion(String value) {
        this.sysVersion = value;
    }

}
