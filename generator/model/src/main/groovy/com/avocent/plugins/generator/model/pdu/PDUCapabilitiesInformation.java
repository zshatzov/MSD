
package com.avocent.plugins.generator.model.pdu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PDUCapabilitiesInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PDUCapabilitiesInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IsPowerOnSupported" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsPowerOffSupported" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsRebootSupported" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsPowerCycleSupported" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PDUCapabilitiesInformationType", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", propOrder = {
    "isPowerOnSupported",
    "isPowerOffSupported",
    "isRebootSupported",
    "isPowerCycleSupported"
})
public class PDUCapabilitiesInformation {

    @XmlElement(name = "IsPowerOnSupported", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected String isPowerOnSupported;
    @XmlElement(name = "IsPowerOffSupported", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected String isPowerOffSupported;
    @XmlElement(name = "IsRebootSupported", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected String isRebootSupported;
    @XmlElement(name = "IsPowerCycleSupported", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected String isPowerCycleSupported;

    /**
     * Gets the value of the isPowerOnSupported property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsPowerOnSupported() {
        return isPowerOnSupported;
    }

    /**
     * Sets the value of the isPowerOnSupported property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsPowerOnSupported(String value) {
        this.isPowerOnSupported = value;
    }

    /**
     * Gets the value of the isPowerOffSupported property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsPowerOffSupported() {
        return isPowerOffSupported;
    }

    /**
     * Sets the value of the isPowerOffSupported property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsPowerOffSupported(String value) {
        this.isPowerOffSupported = value;
    }

    /**
     * Gets the value of the isRebootSupported property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsRebootSupported() {
        return isRebootSupported;
    }

    /**
     * Sets the value of the isRebootSupported property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsRebootSupported(String value) {
        this.isRebootSupported = value;
    }

    /**
     * Gets the value of the isPowerCycleSupported property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsPowerCycleSupported() {
        return isPowerCycleSupported;
    }

    /**
     * Sets the value of the isPowerCycleSupported property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsPowerCycleSupported(String value) {
        this.isPowerCycleSupported = value;
    }

}
