
package com.avocent.plugins.generator.model.common;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotBlank;

/**
 * <p>Java class for IdentitySectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdentitySectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NMMId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Domain" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LicenseId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="VendorName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PluginName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PluginDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PluginVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ImplementationClass" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Locales" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentitySectionType", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", propOrder = {
    "nmmId",
    "domain",
    "licenseId",
    "vendorName",
    "pluginName",
    "pluginDescription",
    "pluginVersion",
    "implementationClass",
    "locales"
})
public class IdentitySection {

    @XmlElement(name = "NMMId", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="NMM id must not be blank")
    protected String nmmId;
    @XmlElement(name = "Domain", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Domain must not be blank")
    protected String domain;
    @XmlElement(name = "LicenseId", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    protected int licenseId;
    @XmlElement(name = "VendorName", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Vendor name must not be blank")
    protected String vendorName;
    @XmlElement(name = "PluginName", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Plugin Name must not be blank")
    protected String pluginName;
    @XmlElement(name = "PluginDescription", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Plugin description must not be blank")
    protected String pluginDescription;
    @XmlElement(name = "PluginVersion", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Plugin version must not be blank")
    @Pattern(regexp="^\\d{1,}\\.\\d{1,}\\.\\d{1,}\\.\\d{1,}$", message="Pulugin Version must follow  this form 'n.n.n.n'")
    protected String pluginVersion;
    @XmlElement(name = "ImplementationClass", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Implementation class must not be blank")
    protected String implementationClass;
    @XmlElement(name = "Locales", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @NotBlank(message="Locales must not be blanked")
    protected String locales;
    
    public IdentitySection() {    
	}

	/**
     * Gets the value of the nmmId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNMMId() {
        return nmmId;
    }

    /**
     * Sets the value of the nmmId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNMMId(String value) {
        this.nmmId = value;
    }

    /**
     * Gets the value of the domain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Sets the value of the domain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomain(String value) {
        this.domain = value;
    }

    /**
     * Gets the value of the licenseId property.
     * 
     */
    public int getLicenseId() {
        return licenseId;
    }

    /**
     * Sets the value of the licenseId property.
     * 
     */
    public void setLicenseId(int value) {
        this.licenseId = value;
    }

    /**
     * Gets the value of the vendorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendorName() {
        return vendorName;
    }

    /**
     * Sets the value of the vendorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendorName(String value) {
        this.vendorName = value;
    }

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
     * Gets the value of the pluginDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPluginDescription() {
        return pluginDescription;
    }

    /**
     * Sets the value of the pluginDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPluginDescription(String value) {
        this.pluginDescription = value;
    }

    /**
     * Gets the value of the pluginVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPluginVersion() {
        return pluginVersion;
    }

    /**
     * Sets the value of the pluginVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPluginVersion(String value) {
        this.pluginVersion = value;
    }

    /**
     * Gets the value of the implementationClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImplementationClass() {
        return implementationClass;
    }

    /**
     * Sets the value of the implementationClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImplementationClass(String value) {
        this.implementationClass = value;
    }

    /**
     * Gets the value of the locales property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocales() {
        return locales;
    }

    /**
     * Sets the value of the locales property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocales(String value) {
        this.locales = value;
    }

}
