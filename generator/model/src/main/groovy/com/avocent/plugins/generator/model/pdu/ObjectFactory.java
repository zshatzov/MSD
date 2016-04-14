
package com.avocent.plugins.generator.model.pdu;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import com.avocent.plugins.generator.model.common.ConfigManagementInterfaceSection;
import com.avocent.plugins.generator.model.common.CustomCodeInformation;
import com.avocent.plugins.generator.model.common.FaultManagementInterfaceSection;
import com.avocent.plugins.generator.model.common.FileConfiguration;
import com.avocent.plugins.generator.model.common.FirmwareUpgrade;
import com.avocent.plugins.generator.model.common.HelpSection;
import com.avocent.plugins.generator.model.common.IdentitySection;
import com.avocent.plugins.generator.model.common.Image;
import com.avocent.plugins.generator.model.common.Images;
import com.avocent.plugins.generator.model.common.ImagesGrouping;
import com.avocent.plugins.generator.model.common.ModelConfiguration;
import com.avocent.plugins.generator.model.common.NMMXMLInformation;
import com.avocent.plugins.generator.model.common.OBWIConfiguration;
import com.avocent.plugins.generator.model.common.OperationInterfaceSection;
import com.avocent.plugins.generator.model.common.SNMPConfiguration;
import com.avocent.plugins.generator.model.common.SupportedFilesConfigurationSection;
import com.avocent.plugins.generator.model.common.SupportedModelsConfigurationSection;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.avocent.plugins.generator.model.pdu package. 
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

    private final static QName _MIBMappingInformation_QNAME = new QName("http://www.emerson.com/CPGMIBMappingForPDUSchema", "MIBMappingInformation");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.avocent.plugins.generator.model.pdu
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MIBMappingInformation }
     * 
     */
    public MIBMappingInformation createMIBMappingInformationType() {
        return new MIBMappingInformation();
    }

    /**
     * Create an instance of {@link PDUCapabilitiesInformation }
     * 
     */
    public PDUCapabilitiesInformation createPDUCapabilitiesInformation() {
        return new PDUCapabilitiesInformation();
    }

    /**
     * Create an instance of {@link PowerPerOutletInformation }
     * 
     */
    public PowerPerOutletInformation createPowerPerOutletInformation() {
        return new PowerPerOutletInformation();
    }

    /**
     * Create an instance of {@link PowerPerPhaseInformation }
     * 
     */
    public PowerPerPhaseInformation createPowerPerPhaseInformation() {
        return new PowerPerPhaseInformation();
    }
 
    /**
     * Create an instance of {@link EnvironmentInformation }
     * 
     */
    public EnvironmentInformation createEnvironmentInformation() {
        return new EnvironmentInformation();
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
     * Create an instance of {@link PDUOutletsOperationsInformation }
     * 
     */
    public PDUOutletsOperationsInformation createPDUOutletsOperationsInformation() {
        return new PDUOutletsOperationsInformation();
    }
 
    /**
     * Create an instance of {@link PropertyInformation }
     * 
     */
    public PropertyInformation createPropertyInformation() {
        return new PropertyInformation();
    }

    /**
     * Create an instance of {@link PowerOperationsInformation }
     * 
     */
    public PowerOperationsInformation createPowerOperationsInformation() {
        return new PowerOperationsInformation();
    }
 
    /**
     * Create an instance of {@link PowerCollectionInformation }
     * 
     */
    public PowerCollectionInformation createPowerCollectionInformation() {
        return new PowerCollectionInformation();
    }

    /**
     * Create an instance of {@link PowerOverallInformation }
     * 
     */
    public PowerOverallInformation createPowerOverallInformation() {
        return new PowerOverallInformation();
    }

    /**
     * Create an instance of {@link PDUOutletsCapabilitiesInformation }
     * 
     */
    public PDUOutletsCapabilitiesInformation createPDUOutletsCapabilitiesInformation() {
        return new PDUOutletsCapabilitiesInformation();
    }

    /**
     * Create an instance of {@link PDUInformation }
     * 
     */
    public PDUInformation createPDUInformation() {
        return new PDUInformation();
    }

    /**
     * Create an instance of {@link PowerPerCircuitInformation }
     * 
     */
    public PowerPerCircuitInformation createPowerPerCircuitInformation() {
        return new PowerPerCircuitInformation();
    }

    /**
     * Create an instance of {@link OutletInformation }
     * 
     */
    public OutletInformation createOutletInformation() {
        return new OutletInformation();
    } 

    /**
     * Create an instance of {@link PDUOperationsInformation }
     * 
     */
    public PDUOperationsInformation createPDUOperationsInformation() {
        return new PDUOperationsInformation();
    }

    /**
     * Create an instance of {@link FirmwareUpgrade }
     * 
     */
    public FirmwareUpgrade createFirmwareUpgrade() {
        return new FirmwareUpgrade();
    }

    /**
     * Create an instance of {@link FaultManagementInterfaceSection }
     * 
     */
    public FaultManagementInterfaceSection createFaultManagementInterfaceSection() {
        return new FaultManagementInterfaceSection();
    }

    /**
     * Create an instance of {@link SupportedModelsConfigurationSection }
     * 
     */
    public SupportedModelsConfigurationSection createSupportedModelsConfigurationSectionType() {
        return new SupportedModelsConfigurationSection();
    }

    /**
     * Create an instance of {@link FileConfiguration }
     * 
     */
    public FileConfiguration createFileConfiguration() {
        return new FileConfiguration();
    }

    /**
     * Create an instance of {@link SupportedFilesConfigurationSection }
     * 
     */
    public SupportedFilesConfigurationSection createSupportedFilesConfigurationSection() {
        return new SupportedFilesConfigurationSection();
    }

    /**
     * Create an instance of {@link IdentitySection }
     * 
     */
    public IdentitySection createIdentitySection() {
        return new IdentitySection();
    }

    /**
     * Create an instance of {@link OperationInterfaceSection }
     * 
     */
    public OperationInterfaceSection createOperationInterfaceSection() {
        return new OperationInterfaceSection();
    }

    /**
     * Create an instance of {@link Images }
     * 
     */
    public Images createImagesType() {
        return new Images();
    }

    /**
     * Create an instance of {@link ConfigManagementInterfaceSection }
     * 
     */
    public ConfigManagementInterfaceSection createConfigManagementInterfaceSection() {
        return new ConfigManagementInterfaceSection();
    }

    /**
     * Create an instance of {@link Image }
     * 
     */
    public Image createImage() {
        return new Image();
    }

    /**
     * Create an instance of {@link NMMXMLInformation }
     * 
     */
    public NMMXMLInformation createNMMXMLInformation() {
        return new NMMXMLInformation();
    }

    /**
     * Create an instance of {@link ModelConfiguration }
     * 
     */
    public ModelConfiguration createModelConfiguration() {
        return new ModelConfiguration();
    }

    /**
     * Create an instance of {@link OBWIConfiguration }
     * 
     */
    public OBWIConfiguration createOBWIConfiguration() {
        return new OBWIConfiguration();
    }

    /**
     * Create an instance of {@link CustomCodeInformation }
     * 
     */
    public CustomCodeInformation createCustomCodeInformation() {
        return new CustomCodeInformation();
    }

    /**
     * Create an instance of {@link ImagesGrouping }
     * 
     */
    public ImagesGrouping createImagesGrouping() {
        return new ImagesGrouping();
    }

    /**
     * Create an instance of {@link HelpSection }
     * 
     */
    public HelpSection createHelpSection() {
        return new HelpSection();
    }

    /**
     * Create an instance of {@link SNMPConfiguration}
     * 
     */
    public SNMPConfiguration createSNMPConfiguration() {
        return new SNMPConfiguration();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MIBMappingInformation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.emerson.com/CPGMIBMappingForPDUSchema", name = "MIBMappingInformation")
    public JAXBElement<MIBMappingInformation> createMIBMappingInformation(MIBMappingInformation value) {
        return new JAXBElement<MIBMappingInformation>(_MIBMappingInformation_QNAME, MIBMappingInformation.class, null, value);
    }

}
