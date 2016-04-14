
package com.avocent.plugins.generator.model.ups;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UPSIdentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UPSIdentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="upsIdentManufacturer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="upsIdentModel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="upsIdentUPSSoftwareVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="upsIdentAgentSoftwareVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="upsIdentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UPSIdentType", namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", propOrder = {
    "upsIdentManufacturer",
    "upsIdentModel",
    "upsIdentUPSSoftwareVersion",
    "upsIdentAgentSoftwareVersion",
    "upsIdentName"
})
public class UPSIdent {

    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected String upsIdentManufacturer;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected String upsIdentModel;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected String upsIdentUPSSoftwareVersion;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected String upsIdentAgentSoftwareVersion;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", required = true)
    protected String upsIdentName;

    /**
     * Gets the value of the upsIdentManufacturer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsIdentManufacturer() {
        return upsIdentManufacturer;
    }

    /**
     * Sets the value of the upsIdentManufacturer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsIdentManufacturer(String value) {
        this.upsIdentManufacturer = value;
    }

    /**
     * Gets the value of the upsIdentModel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsIdentModel() {
        return upsIdentModel;
    }

    /**
     * Sets the value of the upsIdentModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsIdentModel(String value) {
        this.upsIdentModel = value;
    }

    /**
     * Gets the value of the upsIdentUPSSoftwareVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsIdentUPSSoftwareVersion() {
        return upsIdentUPSSoftwareVersion;
    }

    /**
     * Sets the value of the upsIdentUPSSoftwareVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsIdentUPSSoftwareVersion(String value) {
        this.upsIdentUPSSoftwareVersion = value;
    }

    /**
     * Gets the value of the upsIdentAgentSoftwareVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsIdentAgentSoftwareVersion() {
        return upsIdentAgentSoftwareVersion;
    }

    /**
     * Sets the value of the upsIdentAgentSoftwareVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsIdentAgentSoftwareVersion(String value) {
        this.upsIdentAgentSoftwareVersion = value;
    }

    /**
     * Gets the value of the upsIdentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpsIdentName() {
        return upsIdentName;
    }

    /**
     * Sets the value of the upsIdentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpsIdentName(String value) {
        this.upsIdentName = value;
    }

}
