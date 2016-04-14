
package com.avocent.plugins.generator.model.pdu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PowerPerOutletInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PowerPerOutletInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MeasurementInAmps" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MeasurementInVoltsEst" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MeasurementInVoltsRead" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MeasurementInWattsEst" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MeasurementInWattsRead" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PDUStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OverCurrentThreshold" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TurnOnInterval" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PowerPerOutletInformationType", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", propOrder = {
    "measurementInAmps",
    "measurementInVoltsEst",
    "measurementInVoltsRead",
    "measurementInWattsEst",
    "measurementInWattsRead",
    "pduStatus",
    "overCurrentThreshold",
    "turnOnInterval"
})
public class PowerPerOutletInformation {

    @XmlElement(name = "MeasurementInAmps", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected String measurementInAmps;
    @XmlElement(name = "MeasurementInVoltsEst", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected String measurementInVoltsEst;
    @XmlElement(name = "MeasurementInVoltsRead", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected String measurementInVoltsRead;
    @XmlElement(name = "MeasurementInWattsEst", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected String measurementInWattsEst;
    @XmlElement(name = "MeasurementInWattsRead", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected String measurementInWattsRead;
    @XmlElement(name = "PDUStatus", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected String pduStatus;
    @XmlElement(name = "OverCurrentThreshold", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected String overCurrentThreshold;
    @XmlElement(name = "TurnOnInterval", namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", required = true)
    protected String turnOnInterval;

    /**
     * Gets the value of the measurementInAmps property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeasurementInAmps() {
        return measurementInAmps;
    }

    /**
     * Sets the value of the measurementInAmps property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeasurementInAmps(String value) {
        this.measurementInAmps = value;
    }

    /**
     * Gets the value of the measurementInVoltsEst property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeasurementInVoltsEst() {
        return measurementInVoltsEst;
    }

    /**
     * Sets the value of the measurementInVoltsEst property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeasurementInVoltsEst(String value) {
        this.measurementInVoltsEst = value;
    }

    /**
     * Gets the value of the measurementInVoltsRead property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeasurementInVoltsRead() {
        return measurementInVoltsRead;
    }

    /**
     * Sets the value of the measurementInVoltsRead property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeasurementInVoltsRead(String value) {
        this.measurementInVoltsRead = value;
    }

    /**
     * Gets the value of the measurementInWattsEst property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeasurementInWattsEst() {
        return measurementInWattsEst;
    }

    /**
     * Sets the value of the measurementInWattsEst property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeasurementInWattsEst(String value) {
        this.measurementInWattsEst = value;
    }

    /**
     * Gets the value of the measurementInWattsRead property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeasurementInWattsRead() {
        return measurementInWattsRead;
    }

    /**
     * Sets the value of the measurementInWattsRead property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeasurementInWattsRead(String value) {
        this.measurementInWattsRead = value;
    }

    /**
     * Gets the value of the pduStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPDUStatus() {
        return pduStatus;
    }

    /**
     * Sets the value of the pduStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPDUStatus(String value) {
        this.pduStatus = value;
    }

    /**
     * Gets the value of the overCurrentThreshold property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOverCurrentThreshold() {
        return overCurrentThreshold;
    }

    /**
     * Sets the value of the overCurrentThreshold property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOverCurrentThreshold(String value) {
        this.overCurrentThreshold = value;
    }

    /**
     * Gets the value of the turnOnInterval property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTurnOnInterval() {
        return turnOnInterval;
    }

    /**
     * Sets the value of the turnOnInterval property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTurnOnInterval(String value) {
        this.turnOnInterval = value;
    }

}
