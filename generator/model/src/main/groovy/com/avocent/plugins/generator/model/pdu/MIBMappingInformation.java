
package com.avocent.plugins.generator.model.pdu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.avocent.plugins.generator.model.common.CustomCodeInformation;
import com.avocent.plugins.generator.model.common.NMMXMLInformation;
import com.avocent.plugins.generator.model.common.OBWIConfiguration;
import com.avocent.plugins.generator.model.common.SNMPConfiguration;
import com.avocent.plugins.generator.model.common.TrapsInformation;

/**
 * <p>Java class for MIBMappingInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MIBMappingInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DeviceType">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="PDU"/>
 *               &lt;enumeration value="UPS"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DiscoveryFeatureInformation" type="{http://www.emerson.com/CPGMIBMappingForPDUSchema}DiscoveryFeatureInformationType"/>
 *         &lt;element name="PowerOperationsInformation" type="{http://www.emerson.com/CPGMIBMappingForPDUSchema}PowerOperationsInformationType"/>
 *         &lt;element name="PowerCollectionInformation" type="{http://www.emerson.com/CPGMIBMappingForPDUSchema}PowerCollectionInformationType"/>
 *         &lt;element name="TrapsInformation" type="{http://www.emerson.com/CPGMIBMappingForPDUSchema}TrapsInformationType"/>
 *         &lt;element name="OBWIConfiguration" type="{http://www.emerson.com/CPGMIBMappingCommonTypes}OBWIConfigurationType"/>
 *         &lt;element name="SNMPConfiguration" type="{http://www.emerson.com/CPGMIBMappingCommonTypes}SNMPConfigurationType"/>
 *         &lt;element name="NMMXMLInformation" type="{http://www.emerson.com/CPGMIBMappingCommonTypes}NMMXMLInformationType"/>
 *         &lt;element name="CustomCodeInformation" type="{http://www.emerson.com/CPGMIBMappingCommonTypes}CustomCodeInformationType" minOccurs="0"/>         
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement(name="MIBMappingInformation", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MIBMappingInformationType", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", propOrder = {
    "deviceType",
    "discoveryFeatureInformation",
    "powerOperationsInformation",
    "powerCollectionInformation",
    "trapsInformation",
    "obwiConfiguration",
    "snmpConfiguration",
    "nmmxmlInformation",
    "customCodeInformation"
})
public class MIBMappingInformation {

    @XmlElement(name = "DeviceType", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected String deviceType;
    @XmlElement(name = "DiscoveryFeatureInformation", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected DiscoveryFeatureInformation discoveryFeatureInformation;
    @XmlElement(name = "PowerOperationsInformation", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected PowerOperationsInformation powerOperationsInformation;
    @XmlElement(name = "PowerCollectionInformation", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected PowerCollectionInformation powerCollectionInformation;
    @XmlElement(name = "TrapsInformation", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected TrapsInformation trapsInformation;
    @XmlElement(name = "OBWIConfiguration", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected OBWIConfiguration obwiConfiguration;
    @XmlElement(name = "SNMPConfiguration", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected SNMPConfiguration snmpConfiguration;
    @XmlElement(name = "NMMXMLInformation", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected NMMXMLInformation nmmxmlInformation;
    @XmlElement(name = "CustomCodeInformation", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = false)
    protected CustomCodeInformation customCodeInformation;

    /**
     * Gets the value of the deviceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * Sets the value of the deviceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceType(String value) {
        this.deviceType = value;
    }

    /**
     * Gets the value of the discoveryFeatureInformation property.
     * 
     * @return
     *     possible object is
     *     {@link DiscoveryFeatureInformation }
     *     
     */
    public DiscoveryFeatureInformation getDiscoveryFeatureInformation() {
        return discoveryFeatureInformation;
    }

    /**
     * Sets the value of the discoveryFeatureInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiscoveryFeatureInformation }
     *     
     */
    public void setDiscoveryFeatureInformation(DiscoveryFeatureInformation value) {
        this.discoveryFeatureInformation = value;
    }

    /**
     * Gets the value of the powerOperationsInformation property.
     * 
     * @return
     *     possible object is
     *     {@link PowerOperationsInformation }
     *     
     */
    public PowerOperationsInformation getPowerOperationsInformation() {
        return powerOperationsInformation;
    }

    /**
     * Sets the value of the powerOperationsInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PowerOperationsInformation }
     *     
     */
    public void setPowerOperationsInformation(PowerOperationsInformation value) {
        this.powerOperationsInformation = value;
    }

    /**
     * Gets the value of the powerCollectionInformation property.
     * 
     * @return
     *     possible object is
     *     {@link PowerCollectionInformation }
     *     
     */
    public PowerCollectionInformation getPowerCollectionInformation() {
        return powerCollectionInformation;
    }

    /**
     * Sets the value of the powerCollectionInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PowerCollectionInformation }
     *     
     */
    public void setPowerCollectionInformation(PowerCollectionInformation value) {
        this.powerCollectionInformation = value;
    }

    /**
     * Gets the value of the trapsInformation property.
     * 
     * @return
     *     possible object is
     *     {@link TrapsInformation }
     *     
     */
    public TrapsInformation getTrapsInformation() {
        return trapsInformation;
    }

    /**
     * Sets the value of the trapsInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrapsInformation }
     *     
     */
    public void setTrapsInformation(TrapsInformation value) {
        this.trapsInformation = value;
    }

    /**
     * Gets the value of the obwiConfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link OBWIConfigurationType }
     *     
     */
    public OBWIConfiguration getOBWIConfiguration() {
        return obwiConfiguration;
    }

    /**
     * Sets the value of the obwiConfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link OBWIConfigurationType }
     *     
     */
    public void setOBWIConfiguration(OBWIConfiguration value) {
        this.obwiConfiguration = value;
    }

    /**
     * Gets the value of the snmpConfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link SNMPConfigurationType }
     *     
     */
    public SNMPConfiguration getSNMPConfiguration() {
        return snmpConfiguration;
    }

    /**
     * Sets the value of the snmpConfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link SNMPConfigurationType }
     *     
     */
    public void setSNMPConfiguration(SNMPConfiguration value) {
        this.snmpConfiguration = value;
    }

    /**
     * Gets the value of the nmmxmlInformation property.
     * 
     * @return
     *     possible object is
     *     {@link NMMXMLInformationType }
     *     
     */
    public NMMXMLInformation getNMMXMLInformation() {
        return nmmxmlInformation;
    }

    /**
     * Sets the value of the nmmxmlInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link NMMXMLInformationType }
     *     
     */
    public void setNMMXMLInformation(NMMXMLInformation value) {
        this.nmmxmlInformation = value;
    }
    
    /**
     * Gets the value of the customCodeInformation property.
     * 
     * @return
     *     possible object is
     *     {@link CustomCodeInformation }
     *     
     */
    public CustomCodeInformation getCustomCodeInformation() {
        return customCodeInformation;
    }

    /**
     * Sets the value of the customCodeInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomCodeInformation }
     *     
     */
    public void setCustomCodeInformation(CustomCodeInformation value) {
        this.customCodeInformation = value;
    }

}
