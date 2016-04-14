
package com.avocent.plugins.generator.model.common;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotBlank;


/**
 * <p>Java class for ModelConfigurationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ModelConfigurationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UniqueId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CascadedType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Classification">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Unit"/>
 *               &lt;enumeration value="MeteredPowerConnection"/>
 *               &lt;enumeration value="MonitoredPowerConnection"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Scope">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Global"/>
 *               &lt;enumeration value="Local"/>
 *               &lt;enumeration value="System"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DisplayString" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ImagesGroupingName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Capabilities" type="{http://www.emerson.com/CPGMIBMappingCommonTypes}CapabilitiesType"/>
 *         &lt;element name="SupportedFirmwareFile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SupportedTemplateFile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/> 
 *         &lt;element name="ConfigurableRights" type="{http://www.emerson.com/CPGMIBMappingCommonTypes}ConfigurableRightsType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModelConfigurationType", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", propOrder = {
    "uniqueId",
    "cascadedType",
    "classification",
    "scope",
    "displayString",
    "imagesGroupingName",
    "capabilities",
    "supportedFirmwareFile",
    "supportedTemplateFile",    
    "configurableRights"
})
public class ModelConfiguration {

    @XmlElement(name = "UniqueId", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Unique id must not be blank")
    protected String uniqueId;
    @XmlElement(name = "CascadedType", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Cascaded type must not be blank")
    protected String cascadedType; 
    @XmlElement(name = "Classification", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Classification must not be blank")
    protected String classification;
    @XmlElement(name = "Scope", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Scope must not be blank")
    protected String scope;
    @XmlElement(name = "DisplayString", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Display string must not be blank")
    protected String displayString;
    @XmlElement(name = "ImagesGroupingName", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Images group name must not be blank")
    protected String imagesGroupingName;
    @XmlList
    @XmlElement(name = "Capabilities", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @Size(min=1, message="Must specify at least one capability entry")
    protected List<CapabilitiesListType> capabilities = new ArrayList<>();
    @XmlElement(name = "SupportedFirmwareFile", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    protected String supportedFirmwareFile;
    @XmlElement(name = "SupportedTemplateFile", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    protected String supportedTemplateFile;
	@XmlList
    @XmlElement(name = "ConfigurableRights", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
	@Size(min=1, message="Must specify at least one configurable rights entry")
    protected List<ConfigurableRightsListType> configurableRights = new ArrayList<ConfigurableRightsListType>();
    /**
     * Gets the value of the value property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the value property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CapabilitiesListType }
     * 
     * 
     */
    public List<CapabilitiesListType> getCapabilities() {
        if (capabilities == null) {
        	capabilities = new ArrayList<>();
        }
        return this.capabilities;
    }

    /**
     * Gets the value of the classification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassification() {
        return classification;
    }

    /**
     * Gets the value of the value property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the value property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConfigurableRightsListType }
     * 
     * 
     */
    public List<ConfigurableRightsListType> getConfigurableRights() {
        if (configurableRights == null) {
        	configurableRights = new ArrayList<>();
        }
        return this.configurableRights;
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
     * Gets the value of the imagesGroupingName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagesGroupingName() {
        return imagesGroupingName;
    }

    /**
     * Gets the value of the scope property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScope() {
        return scope;
    }

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
     * Gets the value of the cascadedType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCascadedType() {
        return cascadedType;
    }

    /**
     * Sets the value of the classification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassification(String value) {
        this.classification = value;
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
     * Sets the value of the imagesGroupingName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagesGroupingName(String value) {
        this.imagesGroupingName = value;
    }

	/**
     * Sets the value of the scope property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScope(String value) {
        this.scope = value;
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
     * Sets the value of the cascadedType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCascadedType(String value) {
        this.cascadedType = value;
    }
    /**
     * Gets the value of the supportedFirmwareFile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupportedFirmwareFile() {
        return supportedFirmwareFile;
    }

    /**
     * Sets the value of the supportedFirmwareFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupportedFirmwareFile(String value) {
        this.supportedFirmwareFile = value;
    }

    /**
     * Gets the value of the supportedTemplateFile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupportedTemplateFile() {
        return supportedTemplateFile;
    }

    /**
     * Sets the value of the supportedTemplateFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupportedTemplateFile(String value) {
        this.supportedTemplateFile = value;
    }
}
