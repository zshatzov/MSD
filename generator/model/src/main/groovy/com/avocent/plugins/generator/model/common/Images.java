
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
 * <p>Java class for ImagesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ImagesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ImagesGrouping" type="{http://www.emerson.com/CPGMIBMappingCommonTypes}ImagesGroupingType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImagesType", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", propOrder = {
    "imagesGrouping"
})
public class Images {

    @XmlElement(name = "ImagesGrouping", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    @Valid
    @Size(min=1, message="Must define at least one image group")
    protected List<ImagesGrouping> imagesGrouping = new ArrayList<>();

    /**
     * Gets the value of the imagesGrouping property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the imagesGrouping property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImagesGrouping().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ImagesGrouping }
     * 
     * 
     */
    public List<ImagesGrouping> getImagesGrouping() {
        if (imagesGrouping == null) {
            imagesGrouping = new ArrayList<ImagesGrouping>();
        }
        return this.imagesGrouping;
    }

}
