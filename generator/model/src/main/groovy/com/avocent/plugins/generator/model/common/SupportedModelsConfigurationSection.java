
package com.avocent.plugins.generator.model.common;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SupportedModelsConfigurationSectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SupportedModelsConfigurationSectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ModelConfiguration" type="{http://www.emerson.com/CPGMIBMappingCommonTypes}ModelConfigurationType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SupportedModelsConfigurationSectionType", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", propOrder = {
    "modelConfiguration"
})
public class SupportedModelsConfigurationSection {

    @XmlElement(name = "ModelConfiguration", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", required = true)
    @Size(min=1, message="Must define at least one supported model configuration")
    @Valid
    protected List<ModelConfiguration> modelConfiguration = new ArrayList<>();

    /**
     * Gets the value of the modelConfiguration property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the modelConfiguration property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getModelConfiguration().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ModelConfiguration }
     * 
     * 
     */
    public List<ModelConfiguration> getModelConfiguration() {
        if (modelConfiguration == null) {
            modelConfiguration = new ArrayList<ModelConfiguration>();
        }
        return this.modelConfiguration;
    }

}
