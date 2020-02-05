package de.fzi.sensidl.generator.factory.java

import de.fzi.sensidl.design.sensidl.Endianness
import de.fzi.sensidl.design.sensidl.dataRepresentation.Data
import de.fzi.sensidl.design.sensidl.dataRepresentation.DataConversion
import de.fzi.sensidl.design.sensidl.dataRepresentation.DataRange
import de.fzi.sensidl.design.sensidl.dataRepresentation.DataSet
import de.fzi.sensidl.design.sensidl.dataRepresentation.DataType
import de.fzi.sensidl.design.sensidl.dataRepresentation.LinearDataConversion
import de.fzi.sensidl.design.sensidl.dataRepresentation.LinearDataConversionWithInterval
import de.fzi.sensidl.design.sensidl.dataRepresentation.ListData
import de.fzi.sensidl.design.sensidl.dataRepresentation.MeasurementData
import de.fzi.sensidl.design.sensidl.dataRepresentation.Method
import de.fzi.sensidl.design.sensidl.dataRepresentation.NonMeasurementData
import de.fzi.sensidl.generator.GenerationUtil
import de.fzi.sensidl.generator.SensIDLConstants
import de.fzi.sensidl.generator.SensIDLOutputConfigurationProvider
import de.fzi.sensidl.generator.factory.IDTOGenerator
import java.util.ArrayList
import java.util.HashMap
import java.util.List
import org.apache.log4j.Logger
import de.fzi.sensidl.generator.extensions.todo.SensIDLTodoTaskCustomizer

/**
 * Java code generator for the SensIDL Model. 
 * Code will be generated by running the {@code generate()}-Method
 * 
 * @author Sven Eckhardt
 * @author Pawel Bielski 
 * @author Max Peters
 * @author Fabian Scheytt 
 */
 
class JavaDTOGenerator implements IDTOGenerator {
	static val Logger logger = Logger.getLogger(JavaDTOGenerator)
	static val LONG_FORMATTED = "l"
	static val FLOAT_FORMATTED = "f";
	static val DOUBLE_FORMATTED = ".0";
	
	val String packagePrefix 
	val List<DataSet> dataSet
	
	boolean bigEndian
	
	/**
	 * The constructor calls the constructor of the superclass to set a
	 * list of DataSet-elements.
	 * @param newDataSet - represents the list of DataSet-elements.
	 */
	new(List<DataSet> newDataSet, String newPackagePrefix) {
		this.dataSet = newDataSet
		packagePrefix = newPackagePrefix
	}
	
	/**
	 * Generates the .java file for each data transfer object.
	 * @see IDTOGenerator#generate()
	 */
	override generate() {
		logger.info("Start with code-generation of a java data transfer object.")
		val filesToGenerate = new HashMap		
		
		bigEndian = isBigEndian();
		
		for (d : this.dataSet) {
			filesToGenerate.put(addFileExtensionTo(GenerationUtil.toNameUpper(d)), generateClassBody(GenerationUtil.toNameUpper(d), d))
			logger.info("File: " + addFileExtensionTo(GenerationUtil.toNameUpper(d)) + " was generated in " + SensIDLOutputConfigurationProvider.SENSIDL_GEN)
		}
	
		filesToGenerate
	}
	
	private def isBigEndian() {
		return GenerationUtil.getSensorInterface(this.dataSet.get(0).eContainer).encodingSettings.endianness == Endianness.BIG_ENDIAN
	}

	/**
	 * Generates the class
	 */
	def generateClassBody(String className, DataSet d) {
		'''
			package «packagePrefix»«GenerationUtil.getSensorInterfaceName(this.dataSet.get(0).eContainer)»;
			 
			«IF !bigEndian»
				import java.nio.ByteBuffer;
				import java.nio.ByteOrder;
				 
			«ENDIF» 
			 
			/**
			 * Data transfer object for «className»
			 *
			 * @generated
			 */
			public class «className» {
				
				private static final long serialVersionUID = 1L;
				«generateDataFieldsIncludeusedDataSets(d)»
				
				«generateConstructorIncludeusedDataSets(d, className)»
				
				/**
				 * empty constructor for the Data transfer object
				 */
				public «className»() {
				
				}
				
				«d.generateMethods»
				
				«generateDataMethodsIncludeusedDataSets(d)»
				
				«IF !bigEndian»
				«d.generateConverterMethods»
				«ENDIF»
			}
		 '''
	}
// ------------------------------ Methods------------------------------

	/** 
	 * Generates Methods
	 * 
	 */
	def generateMethods(DataSet d){
		'''
		«FOR method : d.eContents.filter(Method)»
				
				/**
				 * Method for «method.name»
				 * «method.description»
				 * @generated
				 */	
				«method.getMethodVisibility» «method.methodReturnType» «method.name»(«method.getMethodParameter»){
					//«SensIDLTodoTaskCustomizer.sensIDLTodoTag»: Auto Generated method stub
					«IF !method.methodReturnType.equals("void")»
					return null;
					«ENDIF»
				}
		«ENDFOR»
		'''
	}
	
	/**
	 * Returns the visibility of a method
	 * 
	 */
	def getMethodVisibility(Method method) {
		if (method.visibility !== null) {
			SensIDLConstants.getVisibilityOf(method.visibility)
		} else {
			SensIDLConstants.defaultVisibility
		}
	}
	
	/**
	 * Returns the return type of a method
	 * 
	 */
	def getMethodReturnType(Method method){
		if (method.returnType != DataType.UNDEFINED){
			return method.returnType.toTypeName
		} else if (method.returnTypeDataSet !== null){
			return method.returnTypeDataSet.name
		} else {
			return "void"
		}
	}
	
	/**
	 * Returns the parameter of a method
	 * 
	 */
	def getMethodParameter(Method method) {
		var str = ""
		if (method.parameter.size > 0) {
			if (method.parameter.head.dataType != DataType.UNDEFINED) {
				str = method.parameter.head.dataType.toTypeName + " " + method.parameter.head.name
			} else if (method.parameter.head.dataTypeDataSet !== null) {
				str = method.parameter.head.dataTypeDataSet.name + " " + method.parameter.head.name
			}
			for (p : method.parameter.tail) {
				if (p.dataType != DataType.UNDEFINED) {
					str += ", " + p.dataType.toTypeName + " " + p.name
				} else if (p.dataTypeDataSet !== null) {
					str += ", " + p.dataTypeDataSet.name + " " + p.name
				}
			}
		}
		return str
	}
	
	/**
	 * returns the appropriate type name for string
	 *
	 */
	def toTypeName(String s) {
		return switch (s) {
			case "INT8": Byte.name
			case "UINT8": Byte.name
			case "INT16": Short.name
			case "UINT16": Short.name
			case "INT32": Integer.name
			case "UINT32": Integer.name
			case "INT64": Long.name
			case "UINT64": Long.name
			case "FLOAT": Float.name
			case "DOUBLE": Double.name
			case "BOOLEAN": Boolean.name
			case "STRING": String.name
		}
	}
		


// ------------------------------ Data Fields ------------------------------
	/**
	 * Generates the data fields for this data set including used data sets.
	 */
	def generateDataFieldsIncludeusedDataSets(DataSet d) {
		'''
		«FOR data : d.eContents.filter(NonMeasurementData)»
			
			«generateDataFields(data)»
		«ENDFOR» 
		«FOR data : d.eContents.filter(MeasurementData)»
			«generateDataFields(data)»
		«ENDFOR» 
		«FOR data : d.eContents.filter(ListData)»
			«generateDataFields(data)»
		«ENDFOR» 
		«FOR dataSet : d.usedDataSets»
			«generateDataFields(dataSet)»
		«ENDFOR»
		'''
	}
	/**
	 * generate the fields for datasets
	 */
	def generateDataFields(DataSet dataSet)
		'''
		/*	
		 * «dataSet.description»
		 */
		private «GenerationUtil.toNameUpper(dataSet)» «GenerationUtil.toNameLower(dataSet)»;
		'''
	def generateDataFields(ListData data){
		'''
		
		«IF data.description !== null»
		/* 
		 * «data.description»
		 */ 
		«ENDIF» 
		private List<«data.getListType»> «GenerationUtil.toNameLower(data)» = new ArrayList<«data.getListType»>();
			 '''
	}
	
	/**
	 * Generates the fields for the measurement data
	 */
	def generateDataFields(MeasurementData d) {
		'''
			/*
			 «IF d.description !== null»
			 * «d.description»
			 * 
			 «ENDIF» 
			 * Unit: «d.unit»
			 */
			private «d.toTypeName» «GenerationUtil.toNameLower(d)»;
			
			«IF d.hasLinearDataConversionWithInterval»
			/*
			«IF d.description !== null» * «d.description»
			 * 
			«ENDIF» 
			 * Unit: «d.unit»
			 * Adjusted
			 */
			private «d.getDataConversionType.toTypeName» «GenerationUtil.toNameLower(d)»Adjusted;
			«ENDIF»
		'''
	}

	/**
	 * Generates the fields for the non measurement data
	 */
	def generateDataFields(NonMeasurementData d) {
		if (d.constant) {
			'''
				«IF d.description !== null»
				/*
				 *«d.description»
				 «IF d.dataType.isUnsigned» 
				 *
				 * Java has no option for unsigned data types, so if the data has an 
				 * unsigned data type the value is calculated by subtracting the maximum 
				 * value from the signed data type and adding it again, if it is used.
				 *
				«ENDIF»
				 */
				«ENDIF» 
				private «d.toTypeName» «d.name.toUpperCase» = «IF d.dataType.isUnsigned»(«d.toSimpleTypeName») («d.value» - «d.toTypeName».MAX_VALUE)«ELSE»«IF d.dataType == DataType.STRING»«d.value»«ELSE»«d.formatedValue»«ENDIF»«ENDIF»;
			'''
		} else {
			'''
				«IF d.description !== null»
				 /*
				  *«d.description»
				  */
				«ENDIF» 
				private «d.toTypeName» «GenerationUtil.toNameLower(d)»«IF !d.value.nullOrEmpty» = «IF d.dataType == DataType.STRING»«d.value»«ELSE»«d.formatedValue»«ENDIF»«ENDIF»;
			'''
		}

	}
	
	private def getFormatedValue(NonMeasurementData data) {
		var value = data.value;
		
		if (data.toTypeName.equals(Long.name)) {
			value += LONG_FORMATTED
		}
		
		if (data.toTypeName.equals(Float.name)) {
			value += FLOAT_FORMATTED
		}
		
		if (data.toTypeName.equals(Double.name)) {
			value += DOUBLE_FORMATTED;
		}
		
		value;
	}
	
	
// ------------------------------ Constructor ------------------------------	
	/**
	 * Generates the constructor for this data set including used data sets.
	 */
	def generateConstructorIncludeusedDataSets(DataSet d, String className) {
		if (d.getNonConstantData.size > 0) {
		'''
			/**
			 * Constructor for the Data transfer object
			 */
			public «className»(«d.generateConstructorArgumentsIncludeusedDataSetss») {
				«FOR data : d.eContents.filter(MeasurementData)»
«««					«IF data.hasLinearDataConversionWithInterval»
«««						«IF data.excludedMethods.contains("setter")»
«««							//set«GenerationUtil.toNameUpper(data)»(«GenerationUtil.toNameLower(data)»); // no setter was generated
«««						«ELSE»
«««							set«GenerationUtil.toNameUpper(data)»(«GenerationUtil.toNameLower(data)»);
«««						«ENDIF»
«««					«ELSE»
						this.«GenerationUtil.toNameLower(data)» = «IF data.dataType.isUnsigned»(«data.toSimpleTypeName») («GenerationUtil.toNameLower(data)» - «data.toTypeName».MAX_VALUE)«ELSE»«GenerationUtil.toNameLower(data)»«ENDIF»;
«««					«ENDIF»
				«ENDFOR»
				«FOR data : d.eContents.filter(NonMeasurementData)»
					«IF !data.constant»
						this.«GenerationUtil.toNameLower(data)» = «IF data.dataType.isUnsigned»(«data.toSimpleTypeName») («GenerationUtil.toNameLower(data)» - «data.toTypeName».MAX_VALUE)«ELSE»«GenerationUtil.toNameLower(data)»«ENDIF»;
					«ENDIF»
				«ENDFOR»
				«FOR pdataSet : d.usedDataSets»
					this.«GenerationUtil.toNameLower(pdataSet)» = «GenerationUtil.toNameLower(pdataSet)»;
				«ENDFOR»
			}
		'''
		} else {
			''''''
		}
	}
	
	/**
	 * Generates the Constructor arguments
	 */
	def generateConstructorArgumentsIncludeusedDataSetss(DataSet d) {
		var dataList = d.getNonConstantData
		if (dataList.size > 0) {
			var firstElement = dataList.get(0).toTypeName + " " + GenerationUtil.toNameLower(dataList.get(0))
			dataList.remove(0)
			if(d.usedDataSets.size > 0){
				'''«firstElement»«FOR data : dataList», «data.toTypeName» «GenerationUtil.toNameLower(data)»«ENDFOR»«FOR pdataSet : d.usedDataSets», «GenerationUtil.toNameUpper(pdataSet)» «GenerationUtil.toNameLower(pdataSet)»«ENDFOR»'''
			} else {
				'''«firstElement»«FOR data :dataList», «data.toTypeName» «GenerationUtil.toNameLower(data)»«ENDFOR»'''
			}
		} else {
			''''''
		}
	}
	
	
	/**
	 * returns all non constant data (i.e. all measurement data and all non constant non measurement data)
	 */
	def getNonConstantData(DataSet d){
		// create an ArrayList with all data that is not a constant NonMeasurementData (which will not be constructor arguments)
		var dataList = new ArrayList<Data>();
		var dataSet = d
		
		for (data : dataSet.eContents.filter(Data)) {
			if (data instanceof NonMeasurementData) {
				var nmdata = data as NonMeasurementData
				if (!nmdata.constant) {
					dataList.add(data)
				}
			} else if (!(data instanceof ListData)) {
				dataList.add(data)
			}
		}
		return dataList
	}

// ------------------------------ Getter and Setter ------------------------------
	
	/**
	 * Generates the getter and setter methods for the data of this data set including used data sets.
	 */
	def generateDataMethodsIncludeusedDataSets(DataSet d) {
		'''
		«FOR data : d.eContents.filter(MeasurementData)»
			«IF !data.excludedMethods.contains("getter")»
				«generateGetter(data)»
			«ENDIF»
			«IF !data.excludedMethods.contains("setter")»
				«generateSetter(data)»
			«ENDIF»
		«ENDFOR»
		«FOR data : d.eContents.filter(NonMeasurementData)»
			«IF !data.excludedMethods.contains("getter")»
				«generateGetter(data)»
			«ENDIF»
			«IF !data.excludedMethods.contains("setter")»
				«generateSetter(data)»
			«ENDIF»
		«ENDFOR»
		«FOR data : d.eContents.filter(ListData)»
				«generateAddMethod(data)»
				
				«generateGetter(data)»
				
				«generateSetter(data)»
		«ENDFOR»
		«FOR pdataSet : d.usedDataSets»
			
			«generateGetter(pdataSet)»
			
			«generateSetter(pdataSet)»
		«ENDFOR»
		'''
	}
	
	/**
	 * return the type of the list
	 */
	def getListType(ListData data){
		if (data.dataType != DataType.UNDEFINED){
			return data.dataType.toTypeName
		} else if (data.dataTypeDataSet !== null){
			return data.dataTypeDataSet.name
		}
	}
	
	/**
	 * returns the Getter Name
	 */
	def toGetterName(Data d) {
		'''get«d.name.replaceAll("[^a-zA-Z0-9]", "").toFirstUpper»'''
	}

	
	/**
	 * returns the Setter Name
	 */
	def toSetterName(Data d) {
		'''set«d.name.replaceAll("[^a-zA-Z0-9]", "").toFirstUpper»'''
	}
	
	
// ------------------------------ Getter ------------------------------

	/** 
	 * Generates the Getter Method for the list data
	 */
	
	def generateGetter(ListData data){
		'''
		/**
		 * Get the «data.name» list
		 *
		 */
		public List<«data.getListType»> «data.toGetterName»(){
			return this.«GenerationUtil.toNameLower(data)»;
		}


		/**
		 * Get the a specific element from the «data.name» list or null if not found
		 *
		 */
		public «data.getListType» «data.toGetterName»(«data.getListType» obj){
			if(this.«GenerationUtil.toNameLower(data)».indexOf(obj) == -1){
				return null;
			} 
			return this.«GenerationUtil.toNameLower(data)».get(this.«GenerationUtil.toNameLower(data)».indexOf(obj));
		}
		'''
	}
	
	/** 
	 * Generates the Getter Method for the dataset
	 */
	
	def generateGetter(DataSet d){
		'''
		/**
		 * @return the «GenerationUtil.toNameLower(d)»
		 *
		 */
		public «GenerationUtil.toNameUpper(d)» get«GenerationUtil.toNameUpper(d)»() {
			return this.«GenerationUtil.toNameLower(d)»;
		}
		'''
	}

	/** 
	 * Generates the Getter Method for the measurement data
	 */
	def generateGetter(MeasurementData d) {
		'''
			«IF !d.hasLinearDataConversionWithInterval»
				/**
				 «IF d.dataType.isUnsigned» 
				 * Java has no option for unsigned data types, so if the data has an 
				 * unsigned data type the value is calculated by subtracting the maximum 
				 * value from the signed data type and adding it again, if it is used.
				 *
				 «ENDIF»
				 * @return the «GenerationUtil.toNameLower(d)»
				 */
				public «d.toTypeName» «d.toGetterName»() {
					return «IF d.dataType.isUnsigned»(«d.toSimpleTypeName») (this.«GenerationUtil.toNameLower(d)» + «d.toTypeName».MAX_VALUE)«ELSE»this.«GenerationUtil.toNameLower(d)»«ENDIF»;
				}
			«ELSE»
				/**
				 * @return the «GenerationUtil.toNameLower(d)»
				 */
				public «d.dataConversionType.toTypeName» «d.toGetterName»(){
					return this.«GenerationUtil.toNameLower(d)»Adjusted;
				}
				
				/**
				 * @return the «GenerationUtil.toNameLower(d)»
				 */
				public «d.toTypeName» «d.toGetterName»NotAdjusted(){
					return «IF d.dataType.isUnsigned»(«d.toSimpleTypeName») (this.«GenerationUtil.toNameLower(d)» + «d.toTypeName».MAX_VALUE)«ELSE»this.«GenerationUtil.toNameLower(d)»«ENDIF»;
				}
			«ENDIF»
		'''
	}

	/** 
	 * Generates the Getter Method for the non measurement data
	 */
	def generateGetter(NonMeasurementData d) {
		'''
			/**
			 «IF d.dataType.isUnsigned» 
			 * Java has no option for unsigned data types, so if the data has an 
			 * unsigned data type the value is calculated by subtracting the maximum 
			 * value from the signed data type and adding it again, if it is used.
			 *
			 «ENDIF»
			 * @return the «GenerationUtil.toNameLower(d)»
			 */
			public «d.toTypeName» «d.toGetterName»() {
				return «IF d.dataType.isUnsigned»(«d.toSimpleTypeName») («IF d.constant»«d.name.toUpperCase»«ELSE»this.«GenerationUtil.toNameLower(d)»«ENDIF» + «d.toTypeName».MAX_VALUE)«ELSE»this.«IF d.constant»«d.name.toUpperCase»«ELSE»«GenerationUtil.toNameLower(d)»«ENDIF»«ENDIF»;
			}
		'''
	}


// ------------------------------ Setter ------------------------------

	/**
	 * Generates the add method to add data to a data List
	 */
	def generateAddMethod(ListData data){
		'''
		/**
		 * Add Data to the «data.name» list
		 *
		 * @param «data.name.toFirstLower»Element 
		 *				the object to add to the list
		 *
		 */
		public void add«data.name.toFirstUpper»(«data.getListType» «data.name.toFirstLower»Element){
			«GenerationUtil.toNameLower(data)».add(«data.name.toFirstLower»Element);
		}
		'''
	}
	
	/**
	 * Generates the Setter Method for list data
	 */
	def generateSetter(ListData data){
		'''
		/**
		 * Set the «data.name» list
		 *
		 * @param «data.name.toFirstLower»
		 * 				the list to set
		 *
		 */
		public void «data.toSetterName»(List<«data.getListType»> «data.name.toFirstLower»){
			this.«GenerationUtil.toNameLower(data)» = «data.name.toFirstLower»;
		}
		'''
	}
	
	/**
	 * Generates the Setter Method for the dataset
	 */
	def generateSetter(DataSet d){
		'''
		/**
		 * @param info
		 *            the «GenerationUtil.toNameLower(d)» to set
		 */
		public void set«GenerationUtil.toNameUpper(d)»(«GenerationUtil.toNameUpper(d)» «GenerationUtil.toNameLower(d)») {
			this.«GenerationUtil.toNameLower(d)» = «GenerationUtil.toNameLower(d)»;
		}
		'''
	}
	/** 
	 * Generates the Setter Method for the measurement data
	 */
	def generateSetter(MeasurementData d) {
		'''
			«IF d.adjustments.empty == false»
				«FOR dataAdj : d.adjustments»
					«IF dataAdj instanceof DataRange»
						/**
						 * @param «GenerationUtil.toNameLower(d)»
						 *            the «GenerationUtil.toNameLower(d)» to set
						 */
						public void «d.toSetterName»WithRange(«d.toTypeName» «GenerationUtil.toNameLower(d)»){
							if («GenerationUtil.toNameLower(d)» >= «dataAdj.range.lowerBound» && «GenerationUtil.toNameLower(d)» <= «dataAdj.range.upperBound»)
								this.«GenerationUtil.toNameLower(d)» = «GenerationUtil.toNameLower(d)»;
							else
								throw new IllegalArgumentException("value is out of defined range");	
						} 
					«ELSEIF dataAdj instanceof DataConversion»	
						/**
						 * @param «GenerationUtil.toNameLower(d)»
						 *            the «GenerationUtil.toNameLower(d)» to set
						 */
						public void «d.toSetterName»WithDataConversion(«d.toTypeName» «GenerationUtil.toNameLower(d)») {
							try {
								«d.generateSetterBodyForMeasurementData(dataAdj as DataConversion)»
							} catch (IllegalArgumentException e) {
								//Do something
							}
						}
«««						«IF dataAdj instanceof DataConversion»						
«««							«IF dataAdj instanceof LinearDataConversion»
«««								/**
«««								 * @param «GenerationUtil.toNameLower(d)»  
«««								 *			the «GenerationUtil.toNameLower(d)» to set
«««								 */
«««								public void «d.toSetterName»(«d.toTypeName» «GenerationUtil.toNameLower(d)»){
«««						
«««									this.«GenerationUtil.toNameLower(d)» =  «GenerationUtil.toNameLower(d)» * («d.toSimpleTypeName») «dataAdj.scalingFactor» + («d.toSimpleTypeName») «dataAdj.offset»;
«««								} 
«««							«ELSE»
«««							«IF dataAdj instanceof LinearDataConversionWithInterval»
«««								/**
«««								 * @param «GenerationUtil.toNameLower(d)»  
«««								 *			the «GenerationUtil.toNameLower(d)» to set
«««								 */
«««								public void «d.toSetterName»(«d.toTypeName» «GenerationUtil.toNameLower(d)»){
«««									if («GenerationUtil.toNameLower(d)» >= «dataAdj.fromInterval.lowerBound» && «GenerationUtil.toNameLower(d)» <= «dataAdj.fromInterval.upperBound»){												
«««								
«««										«d.toTypeName» oldMin = («d.toSimpleTypeName») «dataAdj.fromInterval.lowerBound»;
«««										«d.toTypeName» oldMax = («d.toSimpleTypeName») «dataAdj.fromInterval.upperBound»;
«««										«d.toTypeName» newMin = («d.toSimpleTypeName») «dataAdj.toInterval.lowerBound»;
«««										«d.toTypeName» newMax = («d.toSimpleTypeName») «dataAdj.toInterval.upperBound»;
«««								
«««										this.«GenerationUtil.toNameLower(d)» = («d.toSimpleTypeName») ((((«GenerationUtil.toNameLower(d)» - oldMin) * (newMax - newMin)) / (oldMax - oldMin)) + newMin);
«««									}
«««									else
«««										throw new IllegalArgumentException("value is out of defined source Interval");
«««								} 		
«««							«ENDIF»
«««						«ENDIF»
					«ENDIF»				
				«ENDFOR»
			«ENDIF»
			/**
			 * @param «GenerationUtil.toNameLower(d)»  
			 *            the «GenerationUtil.toNameLower(d)» to set
			 */
			public void «d.toSetterName»(«d.toTypeName» «GenerationUtil.toNameLower(d)») {
				
				this.«GenerationUtil.toNameLower(d)» = «IF d.dataType.isUnsigned»(«d.toSimpleTypeName») («GenerationUtil.toNameLower(d)» - «d.toTypeName».MAX_VALUE)«ELSE»«GenerationUtil.toNameLower(d)»«ENDIF»;
			} 
			
		''' 

	}
	
	dispatch def generateSetterBodyForMeasurementData(MeasurementData data, LinearDataConversion conversion) {
		'''
			final double offset = «conversion.offset»;
			final double scalingFactor = «conversion.scalingFactor»;
			
			this.«GenerationUtil.toNameLower(data)» = («data.toSimpleTypeName») «GenerationUtil.getSensorInterfaceName(data.eContainer)»«SensIDLConstants.UTILITY_CLASS_NAME».«SensIDLConstants.LINEAR_CONVERSION_METHOD_NAME»(«GenerationUtil.toNameLower(data)», scalingFactor, offset);
		'''
	}
	
	dispatch def generateSetterBodyForMeasurementData(MeasurementData data, LinearDataConversionWithInterval conversion) {
		'''
			«conversion.dataType.toTypeName» oldMin = («conversion.dataType.toSimpleTypeName») «conversion.fromInterval.lowerBound»;
			«conversion.dataType.toTypeName» oldMax = («conversion.dataType.toSimpleTypeName») «conversion.fromInterval.upperBound»;
			«conversion.dataType.toTypeName» newMin = («conversion.dataType.toSimpleTypeName») «conversion.toInterval.lowerBound»;
			«conversion.dataType.toTypeName» newMax = («conversion.dataType.toSimpleTypeName») «conversion.toInterval.upperBound»;
			
			this.«GenerationUtil.toNameLower(data)» = «IF data.dataType.isUnsigned»(«data.toSimpleTypeName») («GenerationUtil.toNameLower(data)» - «data.toTypeName».MAX_VALUE)«ELSE»«GenerationUtil.toNameLower(data)»«ENDIF»;
			this.«GenerationUtil.toNameLower(data)»Adjusted = («conversion.dataType.toSimpleTypeName») «GenerationUtil.getSensorInterfaceName(data.eContainer)»«SensIDLConstants.UTILITY_CLASS_NAME».«SensIDLConstants.LINEAR_CONVERSION_WITH_INTERVAL_METHOD_NAME»(«GenerationUtil.toNameLower(data)», oldMin, oldMax, newMin, newMax);
		'''
	}

	/** 
	 * Generates the Setter Method for the non measurement data
	 */
	def generateSetter(NonMeasurementData d) {
		if (d.constant) {
			'''
				// no setter for constant value
			'''
		} else {
			'''
				/**
				 «IF d.dataType.isUnsigned» 
				 * Java has no option for unsigned data types, so if the data has an 
				 * unsigned data type the value is calculated by subtracting the maximum 
				 * value from the signed data type and adding it again, if it is used.
				 *
				 «ENDIF»
				 * @param «GenerationUtil.toNameLower(d)»
				 *            the «GenerationUtil.toNameLower(d)» to set
				 */
				public void «d.toSetterName»(«d.toTypeName» «GenerationUtil.toNameLower(d)»){
					this.«GenerationUtil.toNameLower(d)» = «IF d.dataType.isUnsigned»(«d.toSimpleTypeName») («GenerationUtil.toNameLower(d)» - «d.toTypeName».MAX_VALUE)«ELSE»«GenerationUtil.toNameLower(d)»«ENDIF»;
				} 
			'''
		}
	}

// ------------------------------ Other Methods ------------------------------
	/**
	 * Maps to the corresponding language data type.
	 * @see IDTOGenerator#toTypeName(Data)
	 */
	override toTypeName(Data d) {
		return toTypeName(d.dataType)
	}
	
	/**
	 * returns the appropriate type name for DataType
	 */
	def toTypeName(DataType d) {
		return switch (d) {
			case INT8: Byte.name
			case UINT8: Byte.name
			case INT16: Short.name
			case UINT16: Short.name
			case INT32: Integer.name
			case UINT32: Integer.name
			case INT64: Long.name
			case UINT64: Long.name
			case FLOAT: Float.name
			case DOUBLE: Double.name
			case BOOLEAN: Boolean.name
			case STRING: String.name
			default: ""
		}
	}
	
	/**
	 * returns true if the DataType is an unsigned DataType
	 */
	def isUnsigned(DataType d){
		if (d == DataType.UINT8 || d == DataType.UINT16 || d == DataType.UINT32 ||d == DataType.UINT64 ){
			return true
		}
		return false
	}

	/**
	 * returns the appropriate simple type name suitable for casting
	 */
	def toSimpleTypeName(Data d){
		return toSimpleTypeName(d.dataType)
	}
	
	/**
	 * returns the appropriate simple type name suitable for casting for dataType
	 */
	def toSimpleTypeName(DataType d){
		return switch (d) {
			case INT8: "byte"
			case UINT8: "byte"
			case INT16: "short"
			case UINT16: "short"
			case INT32: "int"
			case UINT32: "int"
			case INT64: "long"
			case UINT64: "long"
			case FLOAT: "float"
			case DOUBLE: "double"
			case BOOLEAN: "boolean"
			case STRING: "String"
			default: ""
		}
	}
	
	/** 
	 * returns the Data Conversion type (at the moment 
	 * only LinearDataConversionWithIntervall has a type)
	 * 
	 */
	def getDataConversionType(MeasurementData d){
		if (d.adjustments.empty){
			return null
		}
		var conversion = d.adjustments.filter(LinearDataConversionWithInterval);
		if (conversion.empty || conversion === null){
			return null
		}
		return conversion.head.dataType
	}
	
	/**
	 * @return true if the MeasurementData is adjusted 
	 * with linear data conversion with interval
	 */
	def hasLinearDataConversionWithInterval(MeasurementData data){
		return !data.adjustments.filter(LinearDataConversionWithInterval).empty
	}
	

	override addFileExtensionTo(String ClassName) {
		return ClassName + SensIDLConstants.JAVA_EXTENSION
	}
	
// ------------------------------ Little Endian Converter Methods ------------------------------

	def generateConverterMethods(DataSet d) {
		'''
			«FOR pdataSet : d.usedDataSets»
				/**
				 * Converts a big endian «GenerationUtil.toNameUpper(pdataSet)» Object into a little endian «GenerationUtil.toNameUpper(pdataSet)» Object
				 *	
				 * @param the «GenerationUtil.toNameUpper(pdataSet)» Object to convert
				 * @return «GenerationUtil.toNameUpper(pdataSet)» the converted «GenerationUtil.toNameUpper(pdataSet)» Object
				 *
				 */
				public «GenerationUtil.toNameUpper(pdataSet)» convertToLittleEndian(«GenerationUtil.toNameUpper(pdataSet)» «GenerationUtil.toNameLower(pdataSet)»){
					//«SensIDLTodoTaskCustomizer.sensIDLTodoTag»: implement Method
					return null;
				}
			«ENDFOR»
			
			«convertAllToTLitteEndian(d)»
			
		'''
	}
	
	def convertAllToTLitteEndian(DataSet d) {
		'''
			public void «SensIDLConstants.JAVA_CONVERT_ALL_TO_LITTLE_ENDIAN_METHOD_NAME»(){
				«FOR data : d.eContents.filter(MeasurementData)»
					«GenerationUtil.toNameLower(data)» = «GenerationUtil.getSensorInterfaceName(data.eContainer)»«SensIDLConstants.UTILITY_CLASS_NAME».convertToLittleEndian(«GenerationUtil.toNameLower(data)»);
				«ENDFOR»
				«FOR data : d.eContents.filter(NonMeasurementData)»
					«IF data.isConstant»
						«data.name.toUpperCase» = «GenerationUtil.getSensorInterfaceName(data.eContainer)»«SensIDLConstants.UTILITY_CLASS_NAME».convertToLittleEndian(«data.name.toUpperCase»);
					«ELSE»
						«GenerationUtil.toNameLower(data)» = «GenerationUtil.getSensorInterfaceName(data.eContainer)»«SensIDLConstants.UTILITY_CLASS_NAME».convertToLittleEndian(«GenerationUtil.toNameLower(data)»);
					«ENDIF»
				«ENDFOR»
				«FOR pdataSet : d.usedDataSets»
					«GenerationUtil.toNameLower(pdataSet)» = convertToLittleEndian(«GenerationUtil.toNameLower(pdataSet)»);
				«ENDFOR»
			}
			
		'''
	}
}
