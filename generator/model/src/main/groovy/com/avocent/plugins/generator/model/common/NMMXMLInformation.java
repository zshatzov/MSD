
package com.avocent.plugins.generator.model.common;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotBlank;

/**
 * <p>Java class for NMMXMLInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NMMXMLInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdentitySection" type="{http://www.emerson.com/CPGMIBMappingCommonTypes}IdentitySectionType"/>
 *         &lt;element name="MinimumDSViewVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="HelpSection" type="{http://www.emerson.com/CPGMIBMappingCommonTypes}HelpSectionType"/>
 *         &lt;element name="OperationInterfaceSection" type="{http://www.emerson.com/CPGMIBMappingCommonTypes}OperationInterfaceSectionType"/>
 *         &lt;element name="ConfigManagementInterfaceSection" type="{http://www.emerson.com/CPGMIBMappingCommonTypes}ConfigManagementInterfaceSectionType"/>
 *         &lt;element name="FaultManagementInterfaceSection" type="{http://www.emerson.com/CPGMIBMappingCommonTypes}FaultManagementInterfaceSectionType" minOccurs="0"/>
 *         &lt;element name="ToolSetupTimeout" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="SupportedModelsConfigurationSection" type="{http://www.emerson.com/CPGMIBMappingCommonTypes}SupportedModelsConfigurationSectionType"/>
 *         &lt;element name="SupportedFilesConfigurationSection" type="{http://www.emerson.com/CPGMIBMappingCommonTypes}SupportedFilesConfigurationSectionType" minOccurs="0"/>
 *         &lt;element name="Images" type="{http://www.emerson.com/CPGMIBMappingCommonTypes}ImagesType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NMMXMLInformationType", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", propOrder = {
    "identitySection",
    "minimumDSViewVersion",
    "helpSection",
    "operationInterfaceSection",
    "configManagementInterfaceSection",
    "faultManagementInterfaceSection",
    "toolSetupTimeout",
    "supportedModelsConfigurationSection",
    "supportedFilesConfigurationSection",
    "images"
})
public class NMMXMLInformation {

    @XmlElement(name = "IdentitySection", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @Valid
    protected IdentitySection identitySection = new IdentitySection();
    @XmlElement(name = "MinimumDSViewVersion", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Minimum DSView Version must not be blank")
    protected String minimumDSViewVersion;
    @XmlElement(name = "HelpSection", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @Valid
    protected HelpSection helpSection =  new HelpSection();
    @XmlElement(name = "OperationInterfaceSection", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @Valid
    protected OperationInterfaceSection operationInterfaceSection = new OperationInterfaceSection();
    @XmlElement(name = "ConfigManagementInterfaceSection", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @Valid
    protected ConfigManagementInterfaceSection configManagementInterfaceSection = new ConfigManagementInterfaceSection();
    @XmlElement(name = "FaultManagementInterfaceSection", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    @Valid
    protected FaultManagementInterfaceSection faultManagementInterfaceSection = new FaultManagementInterfaceSection();
    @XmlElement(name = "ToolSetupTimeout", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    protected Integer toolSetupTimeout;
    @XmlElement(name = "SupportedModelsConfigurationSection", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @Valid
    protected SupportedModelsConfigurationSection supportedModelsConfigurationSection = new SupportedModelsConfigurationSection();
    @XmlElement(name = "SupportedFilesConfigurationSection", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = false)
    @Valid
    protected SupportedFilesConfigurationSection supportedFilesConfigurationSection = new SupportedFilesConfigurationSection();
    @XmlElement(name = "Images", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @Valid
    protected Images images = new Images();

    /**
     * Gets the value of the identitySection property.
     * 
     * @return
     *     possible object is
     *     {@link IdentitySection }
     *     
     */
    public IdentitySection getIdentitySection() {
        return identitySection;
    }

    /**
     * Sets the value of the identitySection property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentitySection }
     *     
     */
    public void setIdentitySection(IdentitySection value) {
        this.identitySection = value;
    }

    /**
     * Gets the value of the minimumDSViewVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinimumDSViewVersion() {
        return minimumDSViewVersion;
    }

    /**
     * Sets the value of the minimumDSViewVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinimumDSViewVersion(String value) {
        this.minimumDSViewVersion = value;
    }

    /**
     * Gets the value of the helpSection property.
     * 
     * @return
     *     possible object is
     *     {@link HelpSection }
     *     
     */
    public HelpSection getHelpSection() {
        return helpSection;
    }

    /**
     * Sets the value of the helpSection property.
     * 
     * @param value
     *     allowed object is
     *     {@link HelpSection }
     *     
     */
    public void setHelpSection(HelpSection value) {
        this.helpSection = value;
    }

    /**
     * Gets the value of the operationInterfaceSection property.
     * 
     * @return
     *     possible object is
     *     {@link OperationInterfaceSection }
     *     
     */
    public OperationInterfaceSection getOperationInterfaceSection() {
        return operationInterfaceSection;
    }

    /**
     * Sets the value of the operationInterfaceSection property.
     * 
     * @param value
     *     allowed object is
     *     {@link OperationInterfaceSection }
     *     
     */
    public void setOperationInterfaceSection(OperationInterfaceSection value) {
        this.operationInterfaceSection = value;
    }

    /**
     * Gets the value of the configManagementInterfaceSection property.
     * 
     * @return
     *     possible object is
     *     {@link ConfigManagementInterfaceSection }
     *     
     */
    public ConfigManagementInterfaceSection getConfigManagementInterfaceSection() {
        return configManagementInterfaceSection;
    }

    /**
     * Sets the value of the configManagementInterfaceSection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfigManagementInterfaceSection }
     *     
     */
    public void setConfigManagementInterfaceSection(ConfigManagementInterfaceSection value) {
        this.configManagementInterfaceSection = value;
    }

    /**
     * Gets the value of the faultManagementInterfaceSection property.
     * 
     * @return
     *     possible object is
     *     {@link FaultManagementInterfaceSection }
     *     
     */
    public FaultManagementInterfaceSection getFaultManagementInterfaceSection() {
        return faultManagementInterfaceSection;
    }

    /**
     * Sets the value of the faultManagementInterfaceSection property.
     * 
     * @param value
     *     allowed object is
     *     {@link FaultManagementInterfaceSection }
     *     
     */
    public void setFaultManagementInterfaceSection(FaultManagementInterfaceSection value) {
        this.faultManagementInterfaceSection = value;
    }

    /**
     * Gets the value of the toolSetupTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getToolSetupTimeout() {
        return toolSetupTimeout;
    }

    /**
     * Sets the value of the toolSetupTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setToolSetupTimeout(Integer value) {
        this.toolSetupTimeout = value;
    }

    /**
     * Gets the value of the supportedModelsConfigurationSection property.
     * 
     * @return
     *     possible object is
     *     {@link SupportedModelsConfigurationSection }
     *     
     */
    public SupportedModelsConfigurationSection getSupportedModelsConfigurationSection() {
        return supportedModelsConfigurationSection;
    }

    /**
     * Sets the value of the supportedModelsConfigurationSection property.
     * 
     * @param value
     *     allowed object is
     *     {@link SupportedModelsConfigurationSection }
     *     
     */
    public void setSupportedModelsConfigurationSection(SupportedModelsConfigurationSection value) {
        this.supportedModelsConfigurationSection = value;
    }

    /**
     * Gets the value of the supportedFilesConfigurationSection property.
     * 
     * @return
     *     possible object is
     *     {@link SupportedFilesConfigurationSection }
     *     
     */
    public SupportedFilesConfigurationSection getSupportedFilesConfigurationSection() {
        return supportedFilesConfigurationSection;
    }

    /**
     * Sets the value of the supportedFilesConfigurationSection property.
     * 
     * @param value
     *     allowed object is
     *     {@link SupportedFilesConfigurationSection }
     *     
     */
    public void setSupportedFilesConfigurationSection(SupportedFilesConfigurationSection value) {
        this.supportedFilesConfigurationSection = value;
    }

    /**
     * Gets the value of the images property.
     * 
     * @return
     *     possible object is
     *     {@link Images }
     *     
     */
    public Images getImages() {
        return images;
    }

    /**
     * Sets the value of the images property.
     * 
     * @param value
     *     allowed object is
     *     {@link Images }
     *     
     */
    public void setImages(Images value) {
        this.images = value;
    }

}
