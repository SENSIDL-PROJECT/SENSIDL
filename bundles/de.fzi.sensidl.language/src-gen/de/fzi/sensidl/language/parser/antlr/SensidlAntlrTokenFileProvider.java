/*
 * generated by Xtext 2.20.0
 */
package de.fzi.sensidl.language.parser.antlr;

import java.io.InputStream;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class SensidlAntlrTokenFileProvider implements IAntlrTokenFileProvider {

	@Override
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		return classLoader.getResourceAsStream("de/fzi/sensidl/language/parser/antlr/internal/InternalSensidl.tokens");
	}
}
