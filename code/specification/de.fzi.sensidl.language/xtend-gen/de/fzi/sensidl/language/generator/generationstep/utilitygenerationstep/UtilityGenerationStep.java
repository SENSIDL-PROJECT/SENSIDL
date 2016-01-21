package de.fzi.sensidl.language.generator.generationstep.utilitygenerationstep;

import de.fzi.sensidl.design.sensidl.dataRepresentation.MeasurementData;
import de.fzi.sensidl.language.generator.IExecuter;
import de.fzi.sensidl.language.generator.SensIDLConstants;
import de.fzi.sensidl.language.generator.elementfilter.ElementFilter;
import de.fzi.sensidl.language.generator.factory.java.JavaGenerator;
import de.fzi.sensidl.language.generator.generationstep.GenerationStep;
import java.util.HashMap;
import java.util.List;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class UtilityGenerationStep extends GenerationStep {
  private final List<MeasurementData> data;
  
  public UtilityGenerationStep(final ElementFilter filter) {
    List<MeasurementData> _filterData = filter.<MeasurementData>filterData();
    this.data = _filterData;
  }
  
  @Override
  public void startGenerationTask() {
    int _size = this.data.size();
    boolean _lessEqualsThan = (_size <= 0);
    if (_lessEqualsThan) {
      return;
    }
    HashMap<SensIDLConstants.GenerationLanguage, IExecuter> _initExecuter = this.initExecuter();
    this.startGenerationTask(_initExecuter);
  }
  
  private HashMap<SensIDLConstants.GenerationLanguage, IExecuter> initExecuter() {
    HashMap<SensIDLConstants.GenerationLanguage, IExecuter> _hashMap = new HashMap<SensIDLConstants.GenerationLanguage, IExecuter>();
    final Procedure1<HashMap<SensIDLConstants.GenerationLanguage, IExecuter>> _function = new Procedure1<HashMap<SensIDLConstants.GenerationLanguage, IExecuter>>() {
      @Override
      public void apply(final HashMap<SensIDLConstants.GenerationLanguage, IExecuter> it) {
        final IExecuter _function = new IExecuter() {
          @Override
          public void execute() {
            final JavaGenerator generator = new JavaGenerator();
            final Procedure1<HashMap<String, CharSequence>> _function = new Procedure1<HashMap<String, CharSequence>>() {
              @Override
              public void apply(final HashMap<String, CharSequence> it) {
                HashMap<String, CharSequence> _generateUtilityClass = generator.generateUtilityClass(UtilityGenerationStep.this.data);
                it.putAll(_generateUtilityClass);
              }
            };
            ObjectExtensions.<HashMap<String, CharSequence>>operator_doubleArrow(
              GenerationStep.filesToGenerate, _function);
          }
        };
        it.put(SensIDLConstants.GenerationLanguage.JAVA, _function);
        final IExecuter _function_1 = new IExecuter() {
          @Override
          public void execute() {
          }
        };
        it.put(SensIDLConstants.GenerationLanguage.C, _function_1);
        final IExecuter _function_2 = new IExecuter() {
          @Override
          public void execute() {
          }
        };
        it.put(SensIDLConstants.GenerationLanguage.CSHARP, _function_2);
        final IExecuter _function_3 = new IExecuter() {
          @Override
          public void execute() {
          }
        };
        it.put(SensIDLConstants.GenerationLanguage.JAVASCRIPT, _function_3);
      }
    };
    return ObjectExtensions.<HashMap<SensIDLConstants.GenerationLanguage, IExecuter>>operator_doubleArrow(_hashMap, _function);
  }
}
