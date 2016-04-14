
package com.avocent.plugins.generator.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArgumentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArgumentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ArgumentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsSymbol" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArgumentType", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", propOrder = {
    "argumentName",
    "isSymbol"
})
public class EventArgument {

    @XmlElement(name = "ArgumentName", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    protected String argumentName;
    @XmlElement(name = "IsSymbol", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    protected boolean isSymbol;

    /**
     * Gets the value of the argumentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArgumentName() {
        return argumentName;
    }

    /**
     * Sets the value of the argumentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArgumentName(String value) {
        this.argumentName = value;
    }

    /**
     * Gets the value of the isSymbol property.
     * 
     */
    public boolean isIsSymbol() {
        return isSymbol;
    }

    /**
     * Sets the value of the isSymbol property.
     * 
     */
    public void setIsSymbol(boolean value) {
        this.isSymbol = value;
    }

}
