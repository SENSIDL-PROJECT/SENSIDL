/*
 * generated by Xtext
 */
package de.fzi.sensidl.language.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import de.fzi.sensidl.design.sensidl.EncodingSettings;
import de.fzi.sensidl.design.sensidl.SensorInterface;
import de.fzi.sensidl.design.sensidl.dataRepresentation.DataRange;
import de.fzi.sensidl.design.sensidl.dataRepresentation.DataRepresentationPackage;
import de.fzi.sensidl.design.sensidl.dataRepresentation.DataSet;
import de.fzi.sensidl.design.sensidl.dataRepresentation.Interval;
import de.fzi.sensidl.design.sensidl.dataRepresentation.LinearDataConversion;
import de.fzi.sensidl.design.sensidl.dataRepresentation.LinearDataConversionWithInterval;
import de.fzi.sensidl.design.sensidl.dataRepresentation.MeasurementData;
import de.fzi.sensidl.design.sensidl.dataRepresentation.NonMeasurementData;
import de.fzi.sensidl.design.sensidl.dataRepresentation.SensorDataDescription;
import de.fzi.sensidl.design.sensidl.sensidlPackage;
import de.fzi.sensidl.language.services.SensidlGrammarAccess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;

@SuppressWarnings("all")
public class SensidlSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private SensidlGrammarAccess grammarAccess;
	
	@Override
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == DataRepresentationPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case DataRepresentationPackage.DATA_RANGE:
				sequence_DataRange(context, (DataRange) semanticObject); 
				return; 
			case DataRepresentationPackage.DATA_SET:
				sequence_DataSet(context, (DataSet) semanticObject); 
				return; 
			case DataRepresentationPackage.INTERVAL:
				sequence_Interval(context, (Interval) semanticObject); 
				return; 
			case DataRepresentationPackage.LINEAR_DATA_CONVERSION:
				sequence_LinearDataConversion(context, (LinearDataConversion) semanticObject); 
				return; 
			case DataRepresentationPackage.LINEAR_DATA_CONVERSION_WITH_INTERVAL:
				sequence_LinearDataConversionWithInterval(context, (LinearDataConversionWithInterval) semanticObject); 
				return; 
			case DataRepresentationPackage.MEASUREMENT_DATA:
				sequence_MeasurementData(context, (MeasurementData) semanticObject); 
				return; 
			case DataRepresentationPackage.NON_MEASUREMENT_DATA:
				sequence_NonMeasurementData(context, (NonMeasurementData) semanticObject); 
				return; 
			case DataRepresentationPackage.SENSOR_DATA_DESCRIPTION:
				sequence_SensorDataDescription(context, (SensorDataDescription) semanticObject); 
				return; 
			}
		else if(semanticObject.eClass().getEPackage() == sensidlPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case sensidlPackage.ENCODING_SETTINGS:
				sequence_EncodingSettings(context, (EncodingSettings) semanticObject); 
				return; 
			case sensidlPackage.SENSOR_INTERFACE:
				sequence_SensorInterface(context, (SensorInterface) semanticObject); 
				return; 
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     range=Interval
	 */
	protected void sequence_DataRange(EObject context, DataRange semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID ID=STRING? description=DESCRIPTION? data+=Data*)
	 */
	protected void sequence_DataSet(EObject context, DataSet semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (coding=Coding endianness=Endianness alignment=INT ID=STRING?)
	 */
	protected void sequence_EncodingSettings(EObject context, EncodingSettings semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (lowerBound=DOUBLE upperBound=DOUBLE)
	 */
	protected void sequence_Interval(EObject context, Interval semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (fromInterval=Interval toInterval=Interval)
	 */
	protected void sequence_LinearDataConversionWithInterval(EObject context, LinearDataConversionWithInterval semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (scalingFactor=DOUBLE offset=DOUBLE)
	 */
	protected void sequence_LinearDataConversion(EObject context, LinearDataConversion semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         name=ID 
	 *         dataType=DataType 
	 *         unit=UNIT 
	 *         ID=STRING? 
	 *         (adjustments+=DataAdjustment adjustments+=DataAdjustment*)? 
	 *         description=DESCRIPTION?
	 *     )
	 */
	protected void sequence_MeasurementData(EObject context, MeasurementData semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         name=ID 
	 *         dataType=DataType 
	 *         constant?='constant'? 
	 *         value=STRING? 
	 *         ID=STRING? 
	 *         description=DESCRIPTION?
	 *     )
	 */
	protected void sequence_NonMeasurementData(EObject context, NonMeasurementData semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (ID=STRING? dataSets+=DataSet*)
	 */
	protected void sequence_SensorDataDescription(EObject context, SensorDataDescription semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID description=DESCRIPTION? ID=STRING? encodingSettings=EncodingSettings dataDescription=SensorDataDescription)
	 */
	protected void sequence_SensorInterface(EObject context, SensorInterface semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
