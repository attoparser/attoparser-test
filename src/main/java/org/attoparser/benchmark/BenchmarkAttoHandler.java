/*
 * =============================================================================
 * 
 *   Copyright (c) 2012, The ATTOPARSER team (http://www.attoparser.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.attoparser.benchmark;

import org.attoparser.markup.AbstractDetailedMarkupAttoHandler;
import org.attoparser.markup.MarkupParsingConfiguration;
import org.attoparser.markup.html.AbstractStandardNonValidatingHtmlAttoHandler;
import org.attoparser.markup.html.HtmlParsing;

public class BenchmarkAttoHandler extends AbstractDetailedMarkupAttoHandler {

    private static MarkupParsingConfiguration CONFIG;

    static {
        CONFIG = new MarkupParsingConfiguration();
        CONFIG.setCaseSensitive(false);
        CONFIG.setElementBalancing(MarkupParsingConfiguration.ElementBalancing.NO_BALANCING);
        CONFIG.setRequireUniqueAttributesInElement(false);
        CONFIG.setRequireXmlWellFormedAttributeValues(false);
        CONFIG.setUniqueRootElementPresence(MarkupParsingConfiguration.UniqueRootElementPresence.NOT_VALIDATED);
        CONFIG.getPrologParsingConfiguration().setValidateProlog(false);
        CONFIG.getPrologParsingConfiguration().setPrologPresence(MarkupParsingConfiguration.PrologPresence.ALLOWED);
        CONFIG.getPrologParsingConfiguration().setXmlDeclarationPresence(MarkupParsingConfiguration.PrologPresence.ALLOWED);
        CONFIG.getPrologParsingConfiguration().setDoctypePresence(MarkupParsingConfiguration.PrologPresence.ALLOWED);
        CONFIG.getPrologParsingConfiguration().setRequireDoctypeKeywordsUpperCase(false);
    }

    
    public BenchmarkAttoHandler() {
        super(CONFIG);
    }
    

}
