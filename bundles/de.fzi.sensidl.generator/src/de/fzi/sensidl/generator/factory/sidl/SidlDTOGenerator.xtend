package de.fzi.sensidl.generator.factory.sidl

import de.fzi.sensidl.design.sensidl.dataRepresentation.Data
import de.fzi.sensidl.design.sensidl.dataRepresentation.DataSet
import de.fzi.sensidl.design.sensidl.dataRepresentation.MeasurementData
import de.fzi.sensidl.generator.GenerationUtil
import de.fzi.sensidl.generator.SensIDLOutputConfigurationProvider
import de.fzi.sensidl.generator.factory.IDTOGenerator
import java.util.HashMap
import java.util.List
import org.apache.log4j.Logger
import org.eclipse.emf.ecore.EObject

/**
 * The sild file generator for the SensIDL Model. 
 * Code will be generated by running the {@code generate()}-Method
 * @author Sven Eckhardt
 */
class SidlDTOGenerator implements IDTOGenerator {
	
	static var String OHM_SIGN = "Ω";
	static var String CORRECT_OHM_SIGN_REPRESENTATION = "Ohm";

	static Logger logger = Logger.getLogger(SidlDTOGenerator)

	List<DataSet> dataSet

	/**
	 * The constructor calls the constructor of the superclass to set a
	 * list of DataSet-elements.
	 * @param newDataSet - represents the list of DataSet-elements.
	 */
	new(List<DataSet> sets) {
		this.dataSet = sets
	}

	/**
	 * Generates the .sidl file for each data transfer object.
	 * @see IDTOGenerator#generate()
	 */
	override generate() {
		logger.info("Start with code-generation of a JavaScript data transfer object.")

		val filesToGenerate = new HashMap<String, CharSequence>

		filesToGenerate.put(addFileExtensionTo(GenerationUtil.getSensorInterfaceName(this.dataSet.get(0).eContainer)),
			generateClass(GenerationUtil.getSensorInterfaceName(this.dataSet.get(0).eContainer),
				this.dataSet.get(0).eContainer))

		logger.info(
			"File: " + addFileExtensionTo(GenerationUtil.getSensorInterfaceName(this.dataSet.get(0).eContainer)) +
				" was generated in " + SensIDLOutputConfigurationProvider.SENSIDL_GEN)

		filesToGenerate
	}

	/**
	 * Generates the Classes
	 */
	def generateClass(String name, EObject sensorInterface) {
		'''
		sensorInterface «name» {
			encoding: SENSIDL_BINARY, endianness: BIG_ENDIAN, alignment: 1 BIT
			sensorData {
				«FOR d : dataSet»
					«d.generateDataSet»
				«ENDFOR»
				
			}
		}'''
	}

	/**
	 * generates the dataSets
	 */
	def generateDataSet(DataSet d) {
		'''
			dataSet «d.name» «d.getUses»{
				«FOR data : d.eContents.filter(Data)»
					«data.name» as «data.dataType» «data.createUnit» «data.createDescription»
				«ENDFOR»
			}
		'''
	}

	/**
	 * generates the description for data
	 */
	def createDescription(Data d) {
		if (d.description !== null) {
			'''/** «d.description» */ '''
		} else {
			''''''
		}
	}

	/**
	 * generates the units for the data
	 */
	def createUnit(Data d) {
		if (d instanceof MeasurementData) {
			if (!(d.getUnit.toString.isNullOrEmpty)) {
				'''in «d.unit.toString.convertUnitStringIfNecessary»'''
			} else {
				'''in Dimensionless'''
			}
		} else {
			''''''
		}
	}
	
	def convertUnitStringIfNecessary(String unit) {
		if (unit.equals(OHM_SIGN)) {
			return CORRECT_OHM_SIGN_REPRESENTATION;
		}
		
		return unit;
	}

	/**
	 * generates the uses dataSets for the dataSets
	 */
	def getUses(DataSet d) {
		if (!d.usedDataSets.empty) {
			'''uses «d.usedDataSets.head.name» «FOR dataSet : d.usedDataSets.tail», «dataSet.name»«ENDFOR»'''
		} else {
			''''''
		}

	}

	override addFileExtensionTo(String ClassName) {
		return ClassName + ".sidl"
	}

	override toTypeName(Data data) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

}
