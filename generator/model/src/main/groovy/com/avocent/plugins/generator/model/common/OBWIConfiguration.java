
package com.avocent.plugins.generator.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OBWIConfigurationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OBWIConfigurationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="URLConfiguration" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OBWIConfigurationType", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", propOrder = {
    "urlConfiguration"
})
public class OBWIConfiguration {

    @XmlElement(name = "URLConfiguration", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = false)
    protected String urlConfiguration;

    /**
     * Gets the value of the urlConfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURLConfiguration() {
        return urlConfiguration;
    }

    /**
     * Sets the value of the urlConfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURLConfiguration(String value) {
        this.urlConfiguration = value;
    }

}
