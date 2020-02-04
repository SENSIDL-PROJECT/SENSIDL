/*
 * generated by Xtext
 */
package de.fzi.sensidl.language.generator

import com.google.inject.Inject
import de.fzi.sensidl.design.sensidl.SensorInterface
import de.fzi.sensidl.language.SensidlRuntimeModule
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import org.apache.log4j.Logger
import org.apache.log4j.Level
import de.fzi.sensidl.language.generator.SensIDLConstants.GenerationLanguage

/**
 * Der Generator für SensIDL.
 * <p>
 * Stößt nacheinander zwei Generatoren an:
 * <ol>
 *   <li>{@link EcorePersistenceHelper} - persistiert das Modell.</li>
 *   <li>{@link SensidlCodeGenerationExecutor} - erzeugt Code für das Modell.</li>
 * </ol>
 * @author Dominik Werle
 */
class SensidlGenerator implements IGenerator {
	/**
	 * Der SensidlCodeGenerationExecutor, wird durch Guice gebunden.
	 * @see SensidlRuntimeModule
	 */
	@Inject private SensidlCodeGenerationExecutor codeGenerator
	
	private static Logger logger = Logger.getLogger(SensidlGenerator);
	
	private static String EXTENSION = "sensidl"
	
	private GenerationLanguage generationLanguage = GenerationLanguage.ALL
//	
	/**
	 * Der Einstiegspunkt für den Generator für SensIDL.
	 * <p>
	 * Stößt nacheinander zwei Generatoren an:
	 * <ol>
	 *   <li>{@link EcorePersistenceHelper} - persistiert das Modell.</li>
	 *   <li>{@link SensidlPseudoCodeGenerator} - erzeugt Pseudocode für das Modell.</li>
	 * </ol>
	 * @param resource die zu verarbeitende Resource
	 * @param fsa Dateizugriff für die Dateigeneration
	 */
	override void doGenerate(Resource resource, IFileSystemAccess fsa) {
		setUpLogger()
		
		codeGenerator.generationLanguage = generationLanguage
		codeGenerator.doGenerate(resource, fsa)
		
		logger.info("Code was generated")
		
		val dataModel = resource
			.contents
			.filter(SensorInterface)
			.head
			
		if (dataModel == null)
			return
		
			
		val fileNameBase = resource.URI.trimQuery.trimFragment.trimFileExtension.lastSegment
		val fileName = fileNameBase + "." + EXTENSION
		
		EcorePersistenceHelper.persistEcoreModel(dataModel, URI.createURI(fileName), fsa)
		
		logger.info("Model was successfully persisted.")
	}
	
	def setGenerationLanguage(GenerationLanguage generationLanguage) {
		this.generationLanguage = generationLanguage
	}
	
	def setUpLogger() {
		var logger = Logger.getLogger(typeof(SensidlGenerator).package.name)
		logger.setLevel(Level.ALL)
	}
	
}