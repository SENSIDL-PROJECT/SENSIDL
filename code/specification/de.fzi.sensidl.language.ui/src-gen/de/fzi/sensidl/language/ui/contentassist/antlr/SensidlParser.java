/*
 * generated by Xtext
 */
package de.fzi.sensidl.language.ui.contentassist.antlr;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

import com.google.inject.Inject;

import de.fzi.sensidl.language.services.SensidlGrammarAccess;

public class SensidlParser extends AbstractContentAssistParser {
	
	@Inject
	private SensidlGrammarAccess grammarAccess;
	
	private Map<AbstractElement, String> nameMappings;
	
	@Override
	protected de.fzi.sensidl.language.ui.contentassist.antlr.internal.InternalSensidlParser createParser() {
		de.fzi.sensidl.language.ui.contentassist.antlr.internal.InternalSensidlParser result = new de.fzi.sensidl.language.ui.contentassist.antlr.internal.InternalSensidlParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}
	
	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getDataSetAccess().getAlternatives_7(), "rule__DataSet__Alternatives_7");
					put(grammarAccess.getMethodAccess().getAlternatives_0(), "rule__Method__Alternatives_0");
					put(grammarAccess.getMethodAccess().getAlternatives_6_1(), "rule__Method__Alternatives_6_1");
					put(grammarAccess.getMethodAccess().getAlternatives_6_1_0(), "rule__Method__Alternatives_6_1_0");
					put(grammarAccess.getMethodParameterAccess().getAlternatives_0(), "rule__MethodParameter__Alternatives_0");
					put(grammarAccess.getMethodParameterAccess().getAlternatives_0_0(), "rule__MethodParameter__Alternatives_0_0");
					put(grammarAccess.getDataAccess().getAlternatives(), "rule__Data__Alternatives");
					put(grammarAccess.getListDataAccess().getAlternatives_4(), "rule__ListData__Alternatives_4");
					put(grammarAccess.getMeasurementDataAccess().getAlternatives_6(), "rule__MeasurementData__Alternatives_6");
					put(grammarAccess.getMeasurementDataAccess().getAlternatives_7_1(), "rule__MeasurementData__Alternatives_7_1");
					put(grammarAccess.getNonMeasurementDataAccess().getAlternatives_2(), "rule__NonMeasurementData__Alternatives_2");
					put(grammarAccess.getNonMeasurementDataAccess().getAlternatives_3(), "rule__NonMeasurementData__Alternatives_3");
					put(grammarAccess.getNonMeasurementDataAccess().getAlternatives_5_1(), "rule__NonMeasurementData__Alternatives_5_1");
					put(grammarAccess.getMeasurementDataNotAdjustableAccess().getAlternatives_6_2(), "rule__MeasurementDataNotAdjustable__Alternatives_6_2");
					put(grammarAccess.getDataConversionAccess().getAlternatives(), "rule__DataConversion__Alternatives");
					put(grammarAccess.getCodingAccess().getAlternatives(), "rule__Coding__Alternatives");
					put(grammarAccess.getEndiannessAccess().getAlternatives(), "rule__Endianness__Alternatives");
					put(grammarAccess.getDataTypeNotAdjustableAccess().getAlternatives(), "rule__DataTypeNotAdjustable__Alternatives");
					put(grammarAccess.getDataTypeAccess().getAlternatives(), "rule__DataType__Alternatives");
					put(grammarAccess.getSensorInterfaceAccess().getGroup(), "rule__SensorInterface__Group__0");
					put(grammarAccess.getSensorInterfaceAccess().getGroup_3(), "rule__SensorInterface__Group_3__0");
					put(grammarAccess.getEncodingSettingsAccess().getGroup(), "rule__EncodingSettings__Group__0");
					put(grammarAccess.getEncodingSettingsAccess().getGroup_12(), "rule__EncodingSettings__Group_12__0");
					put(grammarAccess.getSensorDataDescriptionAccess().getGroup(), "rule__SensorDataDescription__Group__0");
					put(grammarAccess.getSensorDataDescriptionAccess().getGroup_2(), "rule__SensorDataDescription__Group_2__0");
					put(grammarAccess.getDataSetAccess().getGroup(), "rule__DataSet__Group__0");
					put(grammarAccess.getDataSetAccess().getGroup_2(), "rule__DataSet__Group_2__0");
					put(grammarAccess.getDataSetAccess().getGroup_3(), "rule__DataSet__Group_3__0");
					put(grammarAccess.getDataSetAccess().getGroup_4(), "rule__DataSet__Group_4__0");
					put(grammarAccess.getMethodAccess().getGroup(), "rule__Method__Group__0");
					put(grammarAccess.getMethodAccess().getGroup_4(), "rule__Method__Group_4__0");
					put(grammarAccess.getMethodAccess().getGroup_6(), "rule__Method__Group_6__0");
					put(grammarAccess.getMethodAccess().getGroup_9(), "rule__Method__Group_9__0");
					put(grammarAccess.getMethodParameterAccess().getGroup(), "rule__MethodParameter__Group__0");
					put(grammarAccess.getListDataAccess().getGroup(), "rule__ListData__Group__0");
					put(grammarAccess.getMeasurementDataAccess().getGroup(), "rule__MeasurementData__Group__0");
					put(grammarAccess.getMeasurementDataAccess().getGroup_5(), "rule__MeasurementData__Group_5__0");
					put(grammarAccess.getMeasurementDataAccess().getGroup_6_0(), "rule__MeasurementData__Group_6_0__0");
					put(grammarAccess.getMeasurementDataAccess().getGroup_6_1(), "rule__MeasurementData__Group_6_1__0");
					put(grammarAccess.getMeasurementDataAccess().getGroup_6_2(), "rule__MeasurementData__Group_6_2__0");
					put(grammarAccess.getMeasurementDataAccess().getGroup_7(), "rule__MeasurementData__Group_7__0");
					put(grammarAccess.getMeasurementDataAccess().getGroup_7_1_0(), "rule__MeasurementData__Group_7_1_0__0");
					put(grammarAccess.getMeasurementDataAccess().getGroup_7_1_0_1(), "rule__MeasurementData__Group_7_1_0_1__0");
					put(grammarAccess.getMeasurementDataAccess().getGroup_7_1_1(), "rule__MeasurementData__Group_7_1_1__0");
					put(grammarAccess.getMeasurementDataAccess().getGroup_7_1_1_1(), "rule__MeasurementData__Group_7_1_1_1__0");
					put(grammarAccess.getNonMeasurementDataAccess().getGroup(), "rule__NonMeasurementData__Group__0");
					put(grammarAccess.getNonMeasurementDataAccess().getGroup_3_0(), "rule__NonMeasurementData__Group_3_0__0");
					put(grammarAccess.getNonMeasurementDataAccess().getGroup_3_1(), "rule__NonMeasurementData__Group_3_1__0");
					put(grammarAccess.getNonMeasurementDataAccess().getGroup_4(), "rule__NonMeasurementData__Group_4__0");
					put(grammarAccess.getNonMeasurementDataAccess().getGroup_5(), "rule__NonMeasurementData__Group_5__0");
					put(grammarAccess.getNonMeasurementDataAccess().getGroup_5_1_0(), "rule__NonMeasurementData__Group_5_1_0__0");
					put(grammarAccess.getNonMeasurementDataAccess().getGroup_5_1_0_1(), "rule__NonMeasurementData__Group_5_1_0_1__0");
					put(grammarAccess.getNonMeasurementDataAccess().getGroup_5_1_1(), "rule__NonMeasurementData__Group_5_1_1__0");
					put(grammarAccess.getNonMeasurementDataAccess().getGroup_5_1_1_1(), "rule__NonMeasurementData__Group_5_1_1_1__0");
					put(grammarAccess.getMeasurementDataNotAdjustableAccess().getGroup(), "rule__MeasurementDataNotAdjustable__Group__0");
					put(grammarAccess.getMeasurementDataNotAdjustableAccess().getGroup_5(), "rule__MeasurementDataNotAdjustable__Group_5__0");
					put(grammarAccess.getMeasurementDataNotAdjustableAccess().getGroup_6(), "rule__MeasurementDataNotAdjustable__Group_6__0");
					put(grammarAccess.getMeasurementDataNotAdjustableAccess().getGroup_6_2_0(), "rule__MeasurementDataNotAdjustable__Group_6_2_0__0");
					put(grammarAccess.getMeasurementDataNotAdjustableAccess().getGroup_6_2_0_1(), "rule__MeasurementDataNotAdjustable__Group_6_2_0_1__0");
					put(grammarAccess.getMeasurementDataNotAdjustableAccess().getGroup_6_2_1(), "rule__MeasurementDataNotAdjustable__Group_6_2_1__0");
					put(grammarAccess.getMeasurementDataNotAdjustableAccess().getGroup_6_2_1_1(), "rule__MeasurementDataNotAdjustable__Group_6_2_1_1__0");
					put(grammarAccess.getDataRangeAccess().getGroup(), "rule__DataRange__Group__0");
					put(grammarAccess.getIntervalAccess().getGroup(), "rule__Interval__Group__0");
					put(grammarAccess.getLinearDataConversionAccess().getGroup(), "rule__LinearDataConversion__Group__0");
					put(grammarAccess.getLinearDataConversionWithIntervalAccess().getGroup(), "rule__LinearDataConversionWithInterval__Group__0");
					put(grammarAccess.getDOUBLEAccess().getGroup(), "rule__DOUBLE__Group__0");
					put(grammarAccess.getDOUBLEAccess().getGroup_1(), "rule__DOUBLE__Group_1__0");
					put(grammarAccess.getUNITAccess().getGroup(), "rule__UNIT__Group__0");
					put(grammarAccess.getUNITAccess().getGroup_1(), "rule__UNIT__Group_1__0");
					put(grammarAccess.getSensorInterfaceAccess().getNameAssignment_1(), "rule__SensorInterface__NameAssignment_1");
					put(grammarAccess.getSensorInterfaceAccess().getDescriptionAssignment_2(), "rule__SensorInterface__DescriptionAssignment_2");
					put(grammarAccess.getSensorInterfaceAccess().getIDAssignment_3_2(), "rule__SensorInterface__IDAssignment_3_2");
					put(grammarAccess.getSensorInterfaceAccess().getEncodingSettingsAssignment_5(), "rule__SensorInterface__EncodingSettingsAssignment_5");
					put(grammarAccess.getSensorInterfaceAccess().getDataDescriptionAssignment_6(), "rule__SensorInterface__DataDescriptionAssignment_6");
					put(grammarAccess.getEncodingSettingsAccess().getCodingAssignment_2(), "rule__EncodingSettings__CodingAssignment_2");
					put(grammarAccess.getEncodingSettingsAccess().getEndiannessAssignment_6(), "rule__EncodingSettings__EndiannessAssignment_6");
					put(grammarAccess.getEncodingSettingsAccess().getAlignmentAssignment_10(), "rule__EncodingSettings__AlignmentAssignment_10");
					put(grammarAccess.getEncodingSettingsAccess().getIDAssignment_12_2(), "rule__EncodingSettings__IDAssignment_12_2");
					put(grammarAccess.getSensorDataDescriptionAccess().getIDAssignment_2_2(), "rule__SensorDataDescription__IDAssignment_2_2");
					put(grammarAccess.getSensorDataDescriptionAccess().getDataSetsAssignment_4(), "rule__SensorDataDescription__DataSetsAssignment_4");
					put(grammarAccess.getDataSetAccess().getNameAssignment_1(), "rule__DataSet__NameAssignment_1");
					put(grammarAccess.getDataSetAccess().getUsedDataSetsAssignment_2_1(), "rule__DataSet__UsedDataSetsAssignment_2_1");
					put(grammarAccess.getDataSetAccess().getUsedDataSetsAssignment_3_1(), "rule__DataSet__UsedDataSetsAssignment_3_1");
					put(grammarAccess.getDataSetAccess().getIDAssignment_4_2(), "rule__DataSet__IDAssignment_4_2");
					put(grammarAccess.getDataSetAccess().getDescriptionAssignment_5(), "rule__DataSet__DescriptionAssignment_5");
					put(grammarAccess.getDataSetAccess().getDataAssignment_7_0(), "rule__DataSet__DataAssignment_7_0");
					put(grammarAccess.getDataSetAccess().getMethodAssignment_7_1(), "rule__DataSet__MethodAssignment_7_1");
					put(grammarAccess.getMethodAccess().getVisibilityAssignment_0_0(), "rule__Method__VisibilityAssignment_0_0");
					put(grammarAccess.getMethodAccess().getVisibilityAssignment_0_1(), "rule__Method__VisibilityAssignment_0_1");
					put(grammarAccess.getMethodAccess().getVisibilityAssignment_0_2(), "rule__Method__VisibilityAssignment_0_2");
					put(grammarAccess.getMethodAccess().getVisibilityAssignment_0_3(), "rule__Method__VisibilityAssignment_0_3");
					put(grammarAccess.getMethodAccess().getNameAssignment_1(), "rule__Method__NameAssignment_1");
					put(grammarAccess.getMethodAccess().getParameterAssignment_3(), "rule__Method__ParameterAssignment_3");
					put(grammarAccess.getMethodAccess().getParameterAssignment_4_1(), "rule__Method__ParameterAssignment_4_1");
					put(grammarAccess.getMethodAccess().getReturnTypeAssignment_6_1_0_0(), "rule__Method__ReturnTypeAssignment_6_1_0_0");
					put(grammarAccess.getMethodAccess().getReturnTypeAssignment_6_1_0_1(), "rule__Method__ReturnTypeAssignment_6_1_0_1");
					put(grammarAccess.getMethodAccess().getReturnTypeDataSetAssignment_6_1_1(), "rule__Method__ReturnTypeDataSetAssignment_6_1_1");
					put(grammarAccess.getMethodAccess().getIDAssignment_9_2(), "rule__Method__IDAssignment_9_2");
					put(grammarAccess.getMethodAccess().getDescriptionAssignment_10(), "rule__Method__DescriptionAssignment_10");
					put(grammarAccess.getMethodParameterAccess().getDataTypeAssignment_0_0_0(), "rule__MethodParameter__DataTypeAssignment_0_0_0");
					put(grammarAccess.getMethodParameterAccess().getDataTypeAssignment_0_0_1(), "rule__MethodParameter__DataTypeAssignment_0_0_1");
					put(grammarAccess.getMethodParameterAccess().getDataTypeDataSetAssignment_0_1(), "rule__MethodParameter__DataTypeDataSetAssignment_0_1");
					put(grammarAccess.getMethodParameterAccess().getNameAssignment_1(), "rule__MethodParameter__NameAssignment_1");
					put(grammarAccess.getListDataAccess().getNameAssignment_0(), "rule__ListData__NameAssignment_0");
					put(grammarAccess.getListDataAccess().getDataTypeAssignment_4_0(), "rule__ListData__DataTypeAssignment_4_0");
					put(grammarAccess.getListDataAccess().getDataTypeDataSetAssignment_4_1(), "rule__ListData__DataTypeDataSetAssignment_4_1");
					put(grammarAccess.getMeasurementDataAccess().getNameAssignment_0(), "rule__MeasurementData__NameAssignment_0");
					put(grammarAccess.getMeasurementDataAccess().getDataTypeAssignment_2(), "rule__MeasurementData__DataTypeAssignment_2");
					put(grammarAccess.getMeasurementDataAccess().getUnitAssignment_4(), "rule__MeasurementData__UnitAssignment_4");
					put(grammarAccess.getMeasurementDataAccess().getIDAssignment_5_2(), "rule__MeasurementData__IDAssignment_5_2");
					put(grammarAccess.getMeasurementDataAccess().getAdjustmentsAssignment_6_0_1(), "rule__MeasurementData__AdjustmentsAssignment_6_0_1");
					put(grammarAccess.getMeasurementDataAccess().getAdjustmentsAssignment_6_1_1(), "rule__MeasurementData__AdjustmentsAssignment_6_1_1");
					put(grammarAccess.getMeasurementDataAccess().getAdjustmentsAssignment_6_2_1(), "rule__MeasurementData__AdjustmentsAssignment_6_2_1");
					put(grammarAccess.getMeasurementDataAccess().getAdjustmentsAssignment_6_2_3(), "rule__MeasurementData__AdjustmentsAssignment_6_2_3");
					put(grammarAccess.getMeasurementDataAccess().getExcludedMethodsAssignment_7_1_0_0(), "rule__MeasurementData__ExcludedMethodsAssignment_7_1_0_0");
					put(grammarAccess.getMeasurementDataAccess().getExcludedMethodsAssignment_7_1_0_1_1(), "rule__MeasurementData__ExcludedMethodsAssignment_7_1_0_1_1");
					put(grammarAccess.getMeasurementDataAccess().getExcludedMethodsAssignment_7_1_1_0(), "rule__MeasurementData__ExcludedMethodsAssignment_7_1_1_0");
					put(grammarAccess.getMeasurementDataAccess().getExcludedMethodsAssignment_7_1_1_1_1(), "rule__MeasurementData__ExcludedMethodsAssignment_7_1_1_1_1");
					put(grammarAccess.getMeasurementDataAccess().getDescriptionAssignment_8(), "rule__MeasurementData__DescriptionAssignment_8");
					put(grammarAccess.getNonMeasurementDataAccess().getNameAssignment_0(), "rule__NonMeasurementData__NameAssignment_0");
					put(grammarAccess.getNonMeasurementDataAccess().getDataTypeAssignment_2_0(), "rule__NonMeasurementData__DataTypeAssignment_2_0");
					put(grammarAccess.getNonMeasurementDataAccess().getDataTypeAssignment_2_1(), "rule__NonMeasurementData__DataTypeAssignment_2_1");
					put(grammarAccess.getNonMeasurementDataAccess().getConstantAssignment_3_0_0(), "rule__NonMeasurementData__ConstantAssignment_3_0_0");
					put(grammarAccess.getNonMeasurementDataAccess().getValueAssignment_3_0_3(), "rule__NonMeasurementData__ValueAssignment_3_0_3");
					put(grammarAccess.getNonMeasurementDataAccess().getValueAssignment_3_1_2(), "rule__NonMeasurementData__ValueAssignment_3_1_2");
					put(grammarAccess.getNonMeasurementDataAccess().getIDAssignment_4_2(), "rule__NonMeasurementData__IDAssignment_4_2");
					put(grammarAccess.getNonMeasurementDataAccess().getExcludedMethodsAssignment_5_1_0_0(), "rule__NonMeasurementData__ExcludedMethodsAssignment_5_1_0_0");
					put(grammarAccess.getNonMeasurementDataAccess().getExcludedMethodsAssignment_5_1_0_1_1(), "rule__NonMeasurementData__ExcludedMethodsAssignment_5_1_0_1_1");
					put(grammarAccess.getNonMeasurementDataAccess().getExcludedMethodsAssignment_5_1_1_0(), "rule__NonMeasurementData__ExcludedMethodsAssignment_5_1_1_0");
					put(grammarAccess.getNonMeasurementDataAccess().getExcludedMethodsAssignment_5_1_1_1_1(), "rule__NonMeasurementData__ExcludedMethodsAssignment_5_1_1_1_1");
					put(grammarAccess.getNonMeasurementDataAccess().getDescriptionAssignment_6(), "rule__NonMeasurementData__DescriptionAssignment_6");
					put(grammarAccess.getMeasurementDataNotAdjustableAccess().getNameAssignment_0(), "rule__MeasurementDataNotAdjustable__NameAssignment_0");
					put(grammarAccess.getMeasurementDataNotAdjustableAccess().getDataTypeAssignment_2(), "rule__MeasurementDataNotAdjustable__DataTypeAssignment_2");
					put(grammarAccess.getMeasurementDataNotAdjustableAccess().getUnitAssignment_4(), "rule__MeasurementDataNotAdjustable__UnitAssignment_4");
					put(grammarAccess.getMeasurementDataNotAdjustableAccess().getIDAssignment_5_2(), "rule__MeasurementDataNotAdjustable__IDAssignment_5_2");
					put(grammarAccess.getMeasurementDataNotAdjustableAccess().getExcludedMethodsAssignment_6_2_0_0(), "rule__MeasurementDataNotAdjustable__ExcludedMethodsAssignment_6_2_0_0");
					put(grammarAccess.getMeasurementDataNotAdjustableAccess().getExcludedMethodsAssignment_6_2_0_1_1(), "rule__MeasurementDataNotAdjustable__ExcludedMethodsAssignment_6_2_0_1_1");
					put(grammarAccess.getMeasurementDataNotAdjustableAccess().getExcludedMethodsAssignment_6_2_1_0(), "rule__MeasurementDataNotAdjustable__ExcludedMethodsAssignment_6_2_1_0");
					put(grammarAccess.getMeasurementDataNotAdjustableAccess().getExcludedMethodsAssignment_6_2_1_1_1(), "rule__MeasurementDataNotAdjustable__ExcludedMethodsAssignment_6_2_1_1_1");
					put(grammarAccess.getMeasurementDataNotAdjustableAccess().getDescriptionAssignment_7(), "rule__MeasurementDataNotAdjustable__DescriptionAssignment_7");
					put(grammarAccess.getDataRangeAccess().getRangeAssignment_1(), "rule__DataRange__RangeAssignment_1");
					put(grammarAccess.getIntervalAccess().getLowerBoundAssignment_1(), "rule__Interval__LowerBoundAssignment_1");
					put(grammarAccess.getIntervalAccess().getUpperBoundAssignment_3(), "rule__Interval__UpperBoundAssignment_3");
					put(grammarAccess.getLinearDataConversionAccess().getScalingFactorAssignment_2(), "rule__LinearDataConversion__ScalingFactorAssignment_2");
					put(grammarAccess.getLinearDataConversionAccess().getOffsetAssignment_5(), "rule__LinearDataConversion__OffsetAssignment_5");
					put(grammarAccess.getLinearDataConversionWithIntervalAccess().getFromIntervalAssignment_1(), "rule__LinearDataConversionWithInterval__FromIntervalAssignment_1");
					put(grammarAccess.getLinearDataConversionWithIntervalAccess().getToIntervalAssignment_3(), "rule__LinearDataConversionWithInterval__ToIntervalAssignment_3");
					put(grammarAccess.getLinearDataConversionWithIntervalAccess().getDataTypeAssignment_5(), "rule__LinearDataConversionWithInterval__DataTypeAssignment_5");
				}
			};
		}
		return nameMappings.get(element);
	}
	
	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			de.fzi.sensidl.language.ui.contentassist.antlr.internal.InternalSensidlParser typedParser = (de.fzi.sensidl.language.ui.contentassist.antlr.internal.InternalSensidlParser) parser;
			typedParser.entryRuleSensorInterface();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}		
	}
	
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}
	
	public SensidlGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(SensidlGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
