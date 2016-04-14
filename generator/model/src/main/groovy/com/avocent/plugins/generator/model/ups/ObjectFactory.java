
package com.avocent.plugins.generator.model.ups;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.avocent.plugins.generator.model.ups package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MIBMappingInformation_QNAME = new QName("http://www.emerson.com/CPGMIBMappingForUPSSchema", "MIBMappingInformation");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.avocent.plugins.generator.model.ups
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MIBMappingInformation }
     * 
     */
    public MIBMappingInformation createMIBMappingInformation() {
        return new MIBMappingInformation();
    }

    /**
     * Create an instance of {@link UPSBatteryInformation }
     * 
     */
    public UPSBatteryInformation createUPSBatteryInformation() {
        return new UPSBatteryInformation();
    }

    /**
     * Create an instance of {@link UPSIdent }
     * 
     */
    public UPSIdent createUPSIdent() {
        return new UPSIdent();
    }

    /**
     * Create an instance of {@link UPSInformation }
     * 
     */
    public UPSInformation createUPSInformation() {
        return new UPSInformation();
    }

    /**
     * Create an instance of {@link UPSBypassInformation }
     * 
     */
    public UPSBypassInformation createUPSBypassInformation() {
        return new UPSBypassInformation();
    }

    /**
     * Create an instance of {@link UPSOutputInformation }
     * 
     */
    public UPSOutputInformation createUPSOutputInformation() {
        return new UPSOutputInformation();
    }
 
    /**
     * Create an instance of {@link UPSInputInformation }
     * 
     */
    public UPSInputInformation createUPSInputInformation() {
        return new UPSInputInformation();
    }

    /**
     * Create an instance of {@link EnvironmentInformation }
     * 
     */
    public EnvironmentInformation createEnvironmentInformation() {
        return new EnvironmentInformation();
    } 

    /**
     * Create an instance of {@link UPSControlInformation }
     * 
     */
    public UPSControlInformation createUPSControlInformation() {
        return new UPSControlInformation();
    }

    /**
     * Create an instance of {@link RFC1628Information }
     * 
     */
    public RFC1628Information createRFC1628Information() {
        return new RFC1628Information();
    }

    /**
     * Create an instance of {@link DiscoveryFeatureInformation }
     * 
     */
    public DiscoveryFeatureInformation createDiscoveryFeatureInformation() {
        return new DiscoveryFeatureInformation();
    }

    /**
     * Create an instance of {@link AppliancePropertiesInformation }
     * 
     */
    public AppliancePropertiesInformation createAppliancePropertiesInformation() {
        return new AppliancePropertiesInformation();
    }
 
    /**
     * Create an instance of {@link PropertyInformation }
     * 
     */
    public PropertyInformation createPropertyInformation() {
        return new PropertyInformation();
    }

    /**
     * Create an instance of {@link UPSOperationsInformation }
     * 
     */
    public UPSOperationsInformation createUPSOperationsInformation() {
        return new UPSOperationsInformation();
    } 

    /**
     * Create an instance of {@link PowerCollectionInformation }
     * 
     */
    public PowerCollectionInformation createPowerCollectionInformation() {
        return new PowerCollectionInformation();
    } 

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MIBMappingInformation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.emerson.com/CPGMIBMappingForUPSSchema", name = "MIBMappingInformation")
    public JAXBElement<MIBMappingInformation> createMIBMappingInformation(MIBMappingInformation value) {
        return new JAXBElement<MIBMappingInformation>(_MIBMappingInformation_QNAME, MIBMappingInformation.class, null, value);
    }

}
