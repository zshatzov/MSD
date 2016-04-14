
package com.avocent.plugins.generator.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OperationInterfaceSectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OperationInterfaceSectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PerformOperationTimeout" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="PerformOperationTimeoutWithStatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperationInterfaceSectionType", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", propOrder = {
    "performOperationTimeout",
    "performOperationTimeoutWithStatus"
})
public class OperationInterfaceSection {

    @XmlElement(name = "PerformOperationTimeout", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    protected int performOperationTimeout;
    @XmlElement(name = "PerformOperationTimeoutWithStatus", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    protected int performOperationTimeoutWithStatus;

    /**
     * Gets the value of the performOperationTimeout property.
     * 
     */
    public int getPerformOperationTimeout() {
        return performOperationTimeout;
    }

    /**
     * Sets the value of the performOperationTimeout property.
     * 
     */
    public void setPerformOperationTimeout(int value) {
        this.performOperationTimeout = value;
    }

    /**
     * Gets the value of the performOperationTimeoutWithStatus property.
     * 
     */
    public int getPerformOperationTimeoutWithStatus() {
        return performOperationTimeoutWithStatus;
    }

    /**
     * Sets the value of the performOperationTimeoutWithStatus property.
     * 
     */
    public void setPerformOperationTimeoutWithStatus(int value) {
        this.performOperationTimeoutWithStatus = value;
    }

}
