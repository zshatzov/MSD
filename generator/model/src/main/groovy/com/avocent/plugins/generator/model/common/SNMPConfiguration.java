
package com.avocent.plugins.generator.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.avocent.plugins.generator.model.validator.AtLestOne;


/**
 * <p>Java class for SNMPConfigurationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SNMPConfigurationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IsSNMPv1Supported" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="IsSNMPv3Supported" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SNMPConfigurationType", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", propOrder = {
    "isSNMPv1Supported",
    "isSNMPv3Supported"
})
@AtLestOne
public class SNMPConfiguration {

    @XmlElement(name = "IsSNMPv1Supported", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    protected boolean isSNMPv1Supported;
    @XmlElement(name = "IsSNMPv3Supported", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    protected boolean isSNMPv3Supported;

    /**
     * Gets the value of the isSNMPv1Supported property.
     * 
     */
    public boolean isIsSNMPv1Supported() {
        return isSNMPv1Supported;
    }

    /**
     * Sets the value of the isSNMPv1Supported property.
     * 
     */
    public void setIsSNMPv1Supported(boolean value) {
        this.isSNMPv1Supported = value;
    }

    /**
     * Gets the value of the isSNMPv3Supported property.
     * 
     */
    public boolean isIsSNMPv3Supported() {
        return isSNMPv3Supported;
    }

    /**
     * Sets the value of the isSNMPv3Supported property.
     * 
     */
    public void setIsSNMPv3Supported(boolean value) {
        this.isSNMPv3Supported = value;
    }

}
