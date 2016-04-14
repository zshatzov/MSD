
package com.avocent.plugins.generator.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConfigManagementInterfaceSectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConfigManagementInterfaceSectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EnrollTimeout" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="DeenrollTimeout" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="DiscoveryTimeout" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="DiscoveryByRangeTimeout" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PushNamesTimeout" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PullFileTimeout" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PushFileTimeout" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="GetDataTimeout" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="GetExtendedDataTimeout" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfigManagementInterfaceSectionType", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", propOrder = {
    "enrollTimeout",
    "deenrollTimeout",
    "discoveryTimeout",
    "discoveryByRangeTimeout",
    "pushNamesTimeout",
    "pullFileTimeout",
    "pushFileTimeout",
    "getDataTimeout",
    "getExtendedDataTimeout"
})
public class ConfigManagementInterfaceSection {

    @XmlElement(name = "EnrollTimeout", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    protected Integer enrollTimeout;
    @XmlElement(name = "DeenrollTimeout", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    protected Integer deenrollTimeout;
    @XmlElement(name = "DiscoveryTimeout", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    protected Integer discoveryTimeout;
    @XmlElement(name = "DiscoveryByRangeTimeout", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    protected Integer discoveryByRangeTimeout;
    @XmlElement(name = "PushNamesTimeout", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    protected Integer pushNamesTimeout;
    @XmlElement(name = "PullFileTimeout", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    protected Integer pullFileTimeout;
    @XmlElement(name = "PushFileTimeout", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    protected Integer pushFileTimeout;
    @XmlElement(name = "GetDataTimeout", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    protected Integer getDataTimeout;
    @XmlElement(name = "GetExtendedDataTimeout", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    protected Integer getExtendedDataTimeout;

    /**
     * Gets the value of the enrollTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEnrollTimeout() {
        return enrollTimeout;
    }

    /**
     * Sets the value of the enrollTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEnrollTimeout(Integer value) {
        this.enrollTimeout = value;
    }

    /**
     * Gets the value of the deenrollTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDeenrollTimeout() {
        return deenrollTimeout;
    }

    /**
     * Sets the value of the deenrollTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDeenrollTimeout(Integer value) {
        this.deenrollTimeout = value;
    }

    /**
     * Gets the value of the discoveryTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDiscoveryTimeout() {
        return discoveryTimeout;
    }

    /**
     * Sets the value of the discoveryTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDiscoveryTimeout(Integer value) {
        this.discoveryTimeout = value;
    }

    /**
     * Gets the value of the discoveryByRangeTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDiscoveryByRangeTimeout() {
        return discoveryByRangeTimeout;
    }

    /**
     * Sets the value of the discoveryByRangeTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDiscoveryByRangeTimeout(Integer value) {
        this.discoveryByRangeTimeout = value;
    }

    /**
     * Gets the value of the pushNamesTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPushNamesTimeout() {
        return pushNamesTimeout;
    }

    /**
     * Sets the value of the pushNamesTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPushNamesTimeout(Integer value) {
        this.pushNamesTimeout = value;
    }

    /**
     * Gets the value of the pullFileTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPullFileTimeout() {
        return pullFileTimeout;
    }

    /**
     * Sets the value of the pullFileTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPullFileTimeout(Integer value) {
        this.pullFileTimeout = value;
    }

    /**
     * Gets the value of the pushFileTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPushFileTimeout() {
        return pushFileTimeout;
    }

    /**
     * Sets the value of the pushFileTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPushFileTimeout(Integer value) {
        this.pushFileTimeout = value;
    }

    /**
     * Gets the value of the getDataTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGetDataTimeout() {
        return getDataTimeout;
    }

    /**
     * Sets the value of the getDataTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGetDataTimeout(Integer value) {
        this.getDataTimeout = value;
    }

    /**
     * Gets the value of the getExtendedDataTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGetExtendedDataTimeout() {
        return getExtendedDataTimeout;
    }

    /**
     * Sets the value of the getExtendedDataTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGetExtendedDataTimeout(Integer value) {
        this.getExtendedDataTimeout = value;
    }

}
