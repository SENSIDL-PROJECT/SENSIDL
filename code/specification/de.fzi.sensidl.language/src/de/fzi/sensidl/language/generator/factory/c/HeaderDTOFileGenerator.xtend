package de.fzi.sensidl.language.generator.factory.c

import com.google.common.base.Strings
import de.fzi.sensidl.design.sensidl.dataRepresentation.Data
import de.fzi.sensidl.design.sensidl.dataRepresentation.DataSet
import de.fzi.sensidl.design.sensidl.dataRepresentation.MeasurementData
import de.fzi.sensidl.design.sensidl.dataRepresentation.NonMeasurementData
import de.fzi.sensidl.language.generator.GenerationUtil
import de.fzi.sensidl.language.generator.SensIDLConstants
import de.fzi.sensidl.language.generator.SensIDLOutputConfigurationProvider
import de.fzi.sensidl.language.generator.factory.IDTOGenerator
import java.util.HashMap
import java.util.List
import org.apache.log4j.Logger

/**
 * This class implements a part of the CDTOGenerator. This class is responsible for 
 * the generation of the header-files.
 */
class HeaderDTOGenerator extends CDTOGenerator {

	private static Logger logger = Logger.getLogger(HeaderDTOGenerator)
	
	/**
	 * The constructor calls the constructor of the superclass to set a list of DataSet-elements.
	 * @param newDataSet Represents the list of DataSet-elements.
	 */
	new(List<DataSet> newDataSet) {
		super(newDataSet)
	}

	/**
	 * Triggers the code-generation for the C-struct type definition.
	 * @param dataset Represents the model element for the struct.
	 */
	def compile(DataSet dataset) {
		'''«generateStruct(dataset.name.toFirstUpper, dataset)»'''
	}

	/**
	 * @see IDTOGenerator#generate()
	 */
	override HashMap<String,CharSequence> generate() {
		logger.info("Start with code-generation of the data transfer object header-file.")
		val filesToGenerate = new HashMap()

		for (dataset : this.dataSets) {

			val fileName = addFileExtensionTo(GenerationUtil.toNameUpper(dataset))

			filesToGenerate.put(fileName, generateStruct(dataset.name.toFirstUpper, dataset))
			logger.info("File: " + fileName + " was generated in " + SensIDLOutputConfigurationProvider.SENSIDL_GEN)
		}
		
		filesToGenerate
	}

	/**
	 * Generates a typedefinition of a struct.
	 * @param structName Represents the name of the struct.
	 * @param dataset Represents the model element for the struct.
	 */
	def generateStruct(String structName, DataSet dataset) {
		'''
			/**\brief		«IF !Strings.isNullOrEmpty(dataset.description)»«dataset.description»«ENDIF»  
			«FOR data : dataset.eContents.filter(Data)»
				«generateDescription(data)»
			«ENDFOR»
			*/
			
			#ifndef «structName.toUpperCase»_H
			#define «structName.toUpperCase»_H
			
			#include <stdint.h> 
			#include "«GenerationUtil.getUtilityFileName(dataset, SensIDLConstants.HEADER_EXTENSION)»"
			
			«FOR data : dataset.eContents.filter(NonMeasurementData)»				
			«IF data.constant && data.name == "bDeviceType"»
			#ifndef RADIO_DEVICE_TYPE_VALUE
			#define RADIO_DEVICE_TYPE_VALUE «data.value»
			#endif
			«ENDIF»
			«ENDFOR»			
						
			typedef struct
			{
					«generateVariablesIncludeParentDataSet(dataset)»
							
			} «GenerationUtil.toNameUpper(dataset)»;
			
			extern «GenerationUtil.toNameUpper(dataset)» «GenerationUtil.toNameLower(dataset)»;

			«generateInitDatasetPrototype(dataset)»
			
			«generateDataMethodsPrototypesIncludeParentDataSet(dataset)»
						

			#endif
		'''
	}
	
	/**
	 * Generates the data fields for this data set including used data sets.
	 */
	def generateVariablesIncludeParentDataSet(DataSet d) {
		var dataSet = d
		var dataFieldsString =''''''
		
		while (dataSet!==null) {
			for (data : dataSet.eContents.filter(NonMeasurementData)) {
				dataFieldsString += generateVariable(data)
				dataFieldsString += System.getProperty("line.separator");
				}
			for (data : dataSet.eContents.filter(MeasurementData)) {
				dataFieldsString += generateVariable(data)
				dataFieldsString += System.getProperty("line.separator");
				}
			dataSet = dataSet.parentDataSet
		}
		return dataFieldsString
	}	
	
	/** 
	 * Generates the Init Method for the dataset initialization
	 */	
	def generateInitDatasetPrototype(DataSet dataset) {
		'''
		/**
		* @Initialization of the «dataset.name.toFirstUpper» dataset
		*/
		void init«dataset.name.toFirstUpper»(«dataset.name.toFirstUpper»* p);
		'''		
	}
	
	/**
	 * Generates the getter and setter methods prototypes for the data of this data set including used data sets.
	 */
	def generateDataMethodsPrototypesIncludeParentDataSet(DataSet d) {
		var dataSet = d
		var methodsString =''''''
		var parentDataSet = dataSet		
		while (dataSet!==null) {
	
			for (data : dataSet.eContents.filter(NonMeasurementData)) {
				methodsString += generateGetterPrototype(data, parentDataSet)
				methodsString += System.getProperty("line.separator");
				methodsString += generateSetterPrototype(data, parentDataSet)
				methodsString += System.getProperty("line.separator");
			}
			for (data : dataSet.eContents.filter(MeasurementData)) {
				methodsString += generateGetterPrototype(data, parentDataSet)
				methodsString += System.getProperty("line.separator");
				methodsString += generateSetterPrototype(data, parentDataSet)
				methodsString += System.getProperty("line.separator");
			}
			dataSet = dataSet.parentDataSet
		}
		return methodsString
	}	
	
	/** 
	 * Generates the Getter Method for the measurement data
	 */
	dispatch def generateGetterPrototype(MeasurementData d, DataSet dataset) {
		'''
		/**
		* @return the «d.name.toFirstUpper»
		*/
		«d.toTypeName» get_«dataset.name.toFirstUpper»_«d.name.replaceAll("[^a-zA-Z0-9]", "")»(«dataset.name.toFirstUpper»* p);
		
		
		'''
	}
	
	/** 
	 * Generates the Setter Method for the measurement data
	 */	
	dispatch def generateSetterPrototype(MeasurementData d, DataSet dataset) {
		'''
		/**
		 * @param pointer to dataset, «d.name.toFirstLower»
		 *			the «d.name.toFirstLower» to set
		 */
		void set_«dataset.name.toFirstUpper»_«d.name.replaceAll("[^a-zA-Z0-9]", "")»(«dataset.name.toFirstUpper»* p, «d.toTypeName» «d.name.toFirstLower» );
		
		'''
	}
		
	/** 
	 * Generates the Getter Method for the non measurement data
	 */
	dispatch def generateGetterPrototype(NonMeasurementData d, DataSet dataset) {
		'''
		/**
		* @return the «d.name.toFirstUpper»
		*/
		«d.toTypeName» get_«dataset.name.toFirstUpper»_«d.name.replaceAll("[^a-zA-Z0-9]", "")»(«dataset.name.toFirstUpper»* p);
		
		'''
	}
	
	/** 
	 * Generates the Setter Method for the non measurement data
	 */	
	dispatch def generateSetterPrototype(NonMeasurementData d, DataSet dataset) {
		'''
		«IF !d.constant»
		/**
		 * @param pointer to dataset, «d.name.toFirstLower»
		 *			the «d.name.toFirstLower» to set
		 */
		void set_«dataset.name.toFirstUpper»_«d.name.replaceAll("[^a-zA-Z0-9]", "")»(«dataset.name.toFirstUpper»* p, «d.toTypeName» «d.name.toFirstLower» );
		
		«ENDIF»
		'''
	}		
	
	/**
	 * Generates a description for measured data.
	 * @param data
	 * 			represents data which was measured by a sensor.
	 */
	dispatch def generateDescription(MeasurementData data) {
		'''
			* \param		«data.name»: «IF !Strings.isNullOrEmpty(data.description)»«data.description»«ENDIF»
			*				Measured in unit: «data.unit» 
		'''
	}

	/**
	 * Generates a description for non-measured data.
	 * @param data
	 * 			represents variable or constant non-measured data.
	 */
	dispatch def generateDescription(NonMeasurementData data) {
		'''
			* \param		«data.name»: «IF !Strings.isNullOrEmpty(data.description)»«data.description»«ENDIF»
		'''
	}

	/**
	 * Generates a c variable for measured data in the struct.
	 * @param data
	 * 			represents data which was measured by a sensor.
	 */
	dispatch def generateVariable(MeasurementData data) {
		'''«data.toTypeName» «GenerationUtil.toNameLower(data)»;'''
	}

	/**
	 * Generates a c variable for non-measured data in the struct.
	 * @param data
	 * 			represents variable or constant non-measured data.
	 */
	dispatch def generateVariable(NonMeasurementData data) {
		'''«data.toTypeName» «GenerationUtil.toNameLower(data)»;'''
	}

	/**
	 * @see IDTOGenerator#addFileExtensionTo(String)
	 */
	override addFileExtensionTo(String className) {
		return className + SensIDLConstants.HEADER_EXTENSION
	}
}