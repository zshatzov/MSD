
package com.avocent.plugins.generator.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FaultManagementInterfaceSectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FaultManagementInterfaceSectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetStatusInformationTimeout" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="GetSessionInformationTimeout" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="DisconnectSessionTimeout" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FaultManagementInterfaceSectionType", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", propOrder = {
    "getStatusInformationTimeout",
    "getSessionInformationTimeout",
    "disconnectSessionTimeout"
})
public class FaultManagementInterfaceSection {

    @XmlElement(name = "GetStatusInformationTimeout", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    protected Integer getStatusInformationTimeout;
    @XmlElement(name = "GetSessionInformationTimeout", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    protected Integer getSessionInformationTimeout;
    @XmlElement(name = "DisconnectSessionTimeout", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    protected Integer disconnectSessionTimeout;

    /**
     * Gets the value of the getStatusInformationTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGetStatusInformationTimeout() {
        return getStatusInformationTimeout;
    }

    /**
     * Sets the value of the getStatusInformationTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGetStatusInformationTimeout(Integer value) {
        this.getStatusInformationTimeout = value;
    }

    /**
     * Gets the value of the getSessionInformationTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGetSessionInformationTimeout() {
        return getSessionInformationTimeout;
    }

    /**
     * Sets the value of the getSessionInformationTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGetSessionInformationTimeout(Integer value) {
        this.getSessionInformationTimeout = value;
    }

    /**
     * Gets the value of the disconnectSessionTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDisconnectSessionTimeout() {
        return disconnectSessionTimeout;
    }

    /**
     * Sets the value of the disconnectSessionTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDisconnectSessionTimeout(Integer value) {
        this.disconnectSessionTimeout = value;
    }

}
