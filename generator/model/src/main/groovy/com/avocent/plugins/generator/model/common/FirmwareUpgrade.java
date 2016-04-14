
package com.avocent.plugins.generator.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotBlank;


/**
 * <p>Java class for FirmwareUpgradeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FirmwareUpgradeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="invokeClassName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="invokeMethodName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FirmwareUpgradeType", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", propOrder = {
    "invokeClassName",
    "invokeMethodName"
})
public class FirmwareUpgrade {

    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Invoke Class Name must not be blank")
    protected String invokeClassName;
    @XmlElement(namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Invoke Method Name must not be blank")
    protected String invokeMethodName;

    /**
     * Gets the value of the invokeClassName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvokeClassName() {
        return invokeClassName;
    }

    /**
     * Sets the value of the invokeClassName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvokeClassName(String value) {
        this.invokeClassName = value;
    }

    /**
     * Gets the value of the invokeMethodName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvokeMethodName() {
        return invokeMethodName;
    }

    /**
     * Sets the value of the invokeMethodName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvokeMethodName(String value) {
        this.invokeMethodName = value;
    }

}
