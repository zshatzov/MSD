
package com.avocent.plugins.generator.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotBlank;


/**
 * <p>Java class for FileConfigurationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FileConfigurationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UniqueId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DisplayString" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Category" type="{http://www.emerson.com/CPGMIBMappingCommonTypes}CategoryType"/>
 *         &lt;element name="FamilyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OemCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FileConfigurationType", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", propOrder = {
    "uniqueId",
    "displayString",
    "category",
    "familyCode",
    "oemCode"
})
public class FileConfiguration {

    @XmlElement(name = "UniqueId", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Unique Id must not be blank")
    protected String uniqueId;
    @XmlElement(name = "DisplayString", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Display String must not be blank")
    protected String displayString;
    @XmlElement(name = "Category", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @XmlSchemaType(name = "string")   
    protected CategoryType category;
    @XmlElement(name = "FamilyCode", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    protected String familyCode;
    @XmlElement(name = "OemCode", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    protected String oemCode;

    /**
     * Gets the value of the uniqueId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * Sets the value of the uniqueId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUniqueId(String value) {
        this.uniqueId = value;
    }

    /**
     * Gets the value of the displayString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayString() {
        return displayString;
    }

    /**
     * Sets the value of the displayString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayString(String value) {
        this.displayString = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link CategoryType }
     *     
     */
    public CategoryType getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link CategoryType }
     *     
     */
    public void setCategory(CategoryType value) {
        this.category = value;
    }

    /**
     * Gets the value of the familyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFamilyCode() {
        return familyCode;
    }

    /**
     * Sets the value of the familyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFamilyCode(String value) {
        this.familyCode = value;
    }

    /**
     * Gets the value of the oemCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOemCode() {
        return oemCode;
    }

    /**
     * Sets the value of the oemCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOemCode(String value) {
        this.oemCode = value;
    }

}
