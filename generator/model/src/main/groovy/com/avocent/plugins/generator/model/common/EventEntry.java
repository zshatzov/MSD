
package com.avocent.plugins.generator.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotBlank;


/**
 * <p>Java class for EventEntryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EventEntryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NameResourceBundleKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ShortDescriptionResourceBundleKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LongDescriptionResourceBundleKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EventArguments" type="{http://www.emerson.com/CPGMIBMappingCommonTypes}EventArgumentsType"/>
 *         &lt;element name="ElementScope">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Domain"/>
 *               &lt;enumeration value="Local"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ElementSeverity">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Information"/>
 *               &lt;enumeration value="Monitor"/>
 *               &lt;enumeration value="OK"/>
 *               &lt;enumeration value="NonCritical"/>
 *               &lt;enumeration value="Critical"/>
 *               &lt;enumeration value="NonRecoverable"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventEntryType", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", propOrder = {
    "id",
    "nameResourceBundleKey",
    "shortDescriptionResourceBundleKey",
    "longDescriptionResourceBundleKey",
    "eventArguments",
    "elementScope",
    "elementSeverity"
})
public class EventEntry {

    @XmlElement(name = "Id", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Event id must not be blank")
    protected String id;  
    @XmlElement(name = "NameResourceBundleKey", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Name must not be blank")
    protected String nameResourceBundleKey;
    @XmlElement(name = "ShortDescriptionResourceBundleKey", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Short description must not be blank")
    protected String shortDescriptionResourceBundleKey;
    @XmlElement(name = "LongDescriptionResourceBundleKey", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Long description must not be blank")
    protected String longDescriptionResourceBundleKey;
    @XmlElement(name = "EventArguments", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    protected EventArguments eventArguments;
    @XmlElement(name = "ElementScope", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    protected String elementScope;
    @XmlElement(name = "ElementSeverity", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    protected String elementSeverity;

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
     * Gets the value of the nameResourceBundleKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameResourceBundleKey() {
        return nameResourceBundleKey;
    }

    /**
     * Sets the value of the nameResourceBundleKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameResourceBundleKey(String value) {
        this.nameResourceBundleKey = value;
    }

    /**
     * Gets the value of the shortDescriptionResourceBundleKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortDescriptionResourceBundleKey() {
        return shortDescriptionResourceBundleKey;
    }

    /**
     * Sets the value of the shortDescriptionResourceBundleKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortDescriptionResourceBundleKey(String value) {
        this.shortDescriptionResourceBundleKey = value;
    }

    /**
     * Gets the value of the longDescriptionResourceBundleKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongDescriptionResourceBundleKey() {
        return longDescriptionResourceBundleKey;
    }

    /**
     * Sets the value of the longDescriptionResourceBundleKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongDescriptionResourceBundleKey(String value) {
        this.longDescriptionResourceBundleKey = value;
    }

    /**
     * Gets the value of the eventArguments property.
     * 
     * @return
     *     possible object is
     *     {@link EventArguments }
     *     
     */
    public EventArguments getEventArguments() {
        return eventArguments;
    }

    /**
     * Sets the value of the eventArguments property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventArguments }
     *     
     */
    public void setEventArguments(EventArguments value) {
        this.eventArguments = value;
    }

    /**
     * Gets the value of the elementScope property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getElementScope() {
        return elementScope;
    }

    /**
     * Sets the value of the elementScope property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setElementScope(String value) {
        this.elementScope = value;
    }

    /**
     * Gets the value of the elementSeverity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getElementSeverity() {
        return elementSeverity;
    }

    /**
     * Sets the value of the elementSeverity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setElementSeverity(String value) {
        this.elementSeverity = value;
    }

}
