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
					put(grammarAccess.getDataAccess().getAlternatives(), "rule__Data__Alternatives");
					put(grammarAccess.getNonMeasurementDataAccess().getAlternatives_2(), "rule__NonMeasurementData__Alternatives_2");
					put(grammarAccess.getDataAdjustmentAccess().getAlternatives(), "rule__DataAdjustment__Alternatives");
					put(grammarAccess.getDataConversionAccess().getAlternatives(), "rule__DataConversion__Alternatives");
					put(grammarAccess.getCodingAccess().getAlternatives(), "rule__Coding__Alternatives");
					put(grammarAccess.getEndiannessAccess().getAlternatives(), "rule__Endianness__Alternatives");
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
					put(grammarAccess.getMeasurementDataAccess().getGroup(), "rule__MeasurementData__Group__0");
					put(grammarAccess.getMeasurementDataAccess().getGroup_5(), "rule__MeasurementData__Group_5__0");
					put(grammarAccess.getMeasurementDataAccess().getGroup_6(), "rule__MeasurementData__Group_6__0");
					put(grammarAccess.getMeasurementDataAccess().getGroup_6_2(), "rule__MeasurementData__Group_6_2__0");
					put(grammarAccess.getNonMeasurementDataAccess().getGroup(), "rule__NonMeasurementData__Group__0");
					put(grammarAccess.getNonMeasurementDataAccess().getGroup_4(), "rule__NonMeasurementData__Group_4__0");
					put(grammarAccess.getNonMeasurementDataAccess().getGroup_5(), "rule__NonMeasurementData__Group_5__0");
					put(grammarAccess.getMeasurementDataNotAdjustableAccess().getGroup(), "rule__MeasurementDataNotAdjustable__Group__0");
					put(grammarAccess.getMeasurementDataNotAdjustableAccess().getGroup_5(), "rule__MeasurementDataNotAdjustable__Group_5__0");
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
					put(grammarAccess.getDataSetAccess().getParentDataSetAssignment_2_1(), "rule__DataSet__ParentDataSetAssignment_2_1");
					put(grammarAccess.getDataSetAccess().getIDAssignment_3_2(), "rule__DataSet__IDAssignment_3_2");
					put(grammarAccess.getDataSetAccess().getDescriptionAssignment_4(), "rule__DataSet__DescriptionAssignment_4");
					put(grammarAccess.getDataSetAccess().getDataAssignment_6(), "rule__DataSet__DataAssignment_6");
					put(grammarAccess.getMeasurementDataAccess().getNameAssignment_0(), "rule__MeasurementData__NameAssignment_0");
					put(grammarAccess.getMeasurementDataAccess().getDataTypeAssignment_2(), "rule__MeasurementData__DataTypeAssignment_2");
					put(grammarAccess.getMeasurementDataAccess().getUnitAssignment_4(), "rule__MeasurementData__UnitAssignment_4");
					put(grammarAccess.getMeasurementDataAccess().getIDAssignment_5_2(), "rule__MeasurementData__IDAssignment_5_2");
					put(grammarAccess.getMeasurementDataAccess().getAdjustmentsAssignment_6_1(), "rule__MeasurementData__AdjustmentsAssignment_6_1");
					put(grammarAccess.getMeasurementDataAccess().getAdjustmentsAssignment_6_2_1(), "rule__MeasurementData__AdjustmentsAssignment_6_2_1");
					put(grammarAccess.getMeasurementDataAccess().getDescriptionAssignment_7(), "rule__MeasurementData__DescriptionAssignment_7");
					put(grammarAccess.getNonMeasurementDataAccess().getNameAssignment_0(), "rule__NonMeasurementData__NameAssignment_0");
					put(grammarAccess.getNonMeasurementDataAccess().getDataTypeAssignment_2_0(), "rule__NonMeasurementData__DataTypeAssignment_2_0");
					put(grammarAccess.getNonMeasurementDataAccess().getDataTypeAssignment_2_1(), "rule__NonMeasurementData__DataTypeAssignment_2_1");
					put(grammarAccess.getNonMeasurementDataAccess().getConstantAssignment_3(), "rule__NonMeasurementData__ConstantAssignment_3");
					put(grammarAccess.getNonMeasurementDataAccess().getValueAssignment_4_2(), "rule__NonMeasurementData__ValueAssignment_4_2");
					put(grammarAccess.getNonMeasurementDataAccess().getIDAssignment_5_2(), "rule__NonMeasurementData__IDAssignment_5_2");
					put(grammarAccess.getNonMeasurementDataAccess().getDescriptionAssignment_6(), "rule__NonMeasurementData__DescriptionAssignment_6");
					put(grammarAccess.getMeasurementDataNotAdjustableAccess().getNameAssignment_0(), "rule__MeasurementDataNotAdjustable__NameAssignment_0");
					put(grammarAccess.getMeasurementDataNotAdjustableAccess().getDataTypeAssignment_2(), "rule__MeasurementDataNotAdjustable__DataTypeAssignment_2");
					put(grammarAccess.getMeasurementDataNotAdjustableAccess().getUnitAssignment_4(), "rule__MeasurementDataNotAdjustable__UnitAssignment_4");
					put(grammarAccess.getMeasurementDataNotAdjustableAccess().getIDAssignment_5_2(), "rule__MeasurementDataNotAdjustable__IDAssignment_5_2");
					put(grammarAccess.getMeasurementDataNotAdjustableAccess().getDescriptionAssignment_6(), "rule__MeasurementDataNotAdjustable__DescriptionAssignment_6");
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
