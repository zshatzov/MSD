
package com.avocent.plugins.generator.model.common;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EventEntriesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EventEntriesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EventEntry" type="{http://www.emerson.com/CPGMIBMappingCommonTypes}EventEntryType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventEntriesType", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes", propOrder = {
    "eventEntry"
})
public class EventEntries {

    @XmlElement(name = "EventEntry", namespace = "http://www.emerson.com/CPGMIBMappingCommonTypes")
    @Valid
    protected List<EventEntry> eventEntry = new ArrayList<>();

    /**
     * Gets the value of the eventEntry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eventEntry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEventEntry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EventEntry }
     * 
     * 
     */
    public List<EventEntry> getEventEntry() {
        if (eventEntry == null) {
            eventEntry = new ArrayList<EventEntry>();
        }
        return this.eventEntry;
    }

}
