
package com.avocent.plugins.generator.model.common;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotBlank;


/**
 * <p>Java class for TrapsInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrapsInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PluginName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EnterpriseOID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TrapMIBBase" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EventEntries" type="{http://www.emerson.com/CPGMIBMappingCommonTypes}EventEntriesType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrapsInformationType", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", propOrder = {
    "pluginName",
    "enterpriseOID",
    "trapMIBBase",
    "eventEntries"
})
public class TrapsInformation {

    @XmlElement(name = "PluginName", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Plugin name must not be blank")
    protected String pluginName;
    @XmlElement(name = "EnterpriseOID", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Enterprise OID must not be blank")
    protected String enterpriseOID;
    @XmlElement(name = "TrapMIBBase", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Trap MIB base must not be blank")
    protected String trapMIBBase;
    @XmlElement(name = "EventEntries", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @Valid
    protected EventEntries eventEntries = new EventEntries();

    /**
     * Gets the value of the pluginName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPluginName() {
        return pluginName;
    }

    /**
     * Sets the value of the pluginName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPluginName(String value) {
        this.pluginName = value;
    }

    /**
     * Gets the value of the enterpriseOID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnterpriseOID() {
        return enterpriseOID;
    }

    /**
     * Sets the value of the enterpriseOID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnterpriseOID(String value) {
        this.enterpriseOID = value;
    }

    /**
     * Gets the value of the trapMIBBase property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrapMIBBase() {
        return trapMIBBase;
    }

    /**
     * Sets the value of the trapMIBBase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrapMIBBase(String value) {
        this.trapMIBBase = value;
    }

    /**
     * Gets the value of the eventEntries property.
     * 
     * @return
     *     possible object is
     *     {@link EventEntries }
     *     
     */
    public EventEntries getEventEntries() {
        return eventEntries;
    }

    /**
     * Sets the value of the eventEntries property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventEntries }
     *     
     */
    public void setEventEntries(EventEntries value) {
        this.eventEntries = value;
    }

}
