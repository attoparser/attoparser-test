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
package org.attoparser.html.codedisplay;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;

import org.attoparser.MarkupAttoParser;
import org.attoparser.MarkupParsingConfiguration;
import org.attoparser.prettyhtmldisplay.PrettyHtmlDisplayMarkupAttoHandler;

public class HtmlCodeDisplayTest {
    
    
    private static final String FILE_NAME_TEST1 = "test1.html";
    private static final String FILE_NAME_TEST2 = "test2.html";
    private static final String FILE_NAME_TEST3 = "test3.html";
    private static final String FILE_NAME_TEST4 = "test4.html";

    
    
    public static void main(final String[] args) throws Exception {
        
        if (args.length != 1) {
            System.err.println("Syntax: java " + HtmlCodeDisplayTest.class.getName() + " [output_folder]");
            System.exit(1);
        }
        
        final String outputFolder = args[0];

        Reader test1Reader = null;
        Reader test2Reader = null;
        Reader test3Reader = null;
        Reader test4Reader = null;
        Writer test1Writer = null;
        Writer test2Writer = null;
        Writer test3Writer = null;
        Writer test4Writer = null;
        try {
            
            final InputStream test1IS = Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_NAME_TEST1);
            final InputStream test2IS = Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_NAME_TEST2);
            final InputStream test3IS = Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_NAME_TEST3);
            final InputStream test4IS = Thread.currentThread().getContextClassLoader().getResourceAsStream(FILE_NAME_TEST4);

            test1Reader = new InputStreamReader(test1IS);
            test2Reader = new InputStreamReader(test2IS);
            test3Reader = new InputStreamReader(test3IS);
            test4Reader = new InputStreamReader(test4IS);

            final File test1File = new File(outputFolder +  File.separator + "result_" + FILE_NAME_TEST1); 
            final File test2File = new File(outputFolder +  File.separator + "result_" + FILE_NAME_TEST2); 
            final File test3File = new File(outputFolder +  File.separator + "result_" + FILE_NAME_TEST3);
            final File test4File = new File(outputFolder +  File.separator + "result_" + FILE_NAME_TEST4);

            test1Writer = new FileWriter(test1File);
            test2Writer = new FileWriter(test2File);
            test3Writer = new FileWriter(test3File);
            test4Writer = new FileWriter(test4File);

            final PrettyHtmlDisplayMarkupAttoHandler test1Handler = new PrettyHtmlDisplayMarkupAttoHandler(FILE_NAME_TEST1, test1Writer, false);
            final PrettyHtmlDisplayMarkupAttoHandler test2Handler = new PrettyHtmlDisplayMarkupAttoHandler(FILE_NAME_TEST2, test2Writer, false);
            final PrettyHtmlDisplayMarkupAttoHandler test3Handler = new PrettyHtmlDisplayMarkupAttoHandler(FILE_NAME_TEST3, test3Writer, false);
            final PrettyHtmlDisplayMarkupAttoHandler test4Handler = new PrettyHtmlDisplayMarkupAttoHandler(FILE_NAME_TEST4, test4Writer, false);

            final MarkupParsingConfiguration config = MarkupParsingConfiguration.defaultHtmlConfiguration();

            final MarkupParsingConfiguration autoCloseConfig = MarkupParsingConfiguration.defaultHtmlConfiguration();
            autoCloseConfig.setElementBalancing(MarkupParsingConfiguration.ElementBalancing.AUTO_CLOSE);

            final MarkupAttoParser htmlStandardParser = new MarkupAttoParser(config);
            final MarkupAttoParser htmlAutoCloseStandardParser = new MarkupAttoParser(autoCloseConfig);

            htmlStandardParser.parse(test1Reader, test1Handler);
            htmlStandardParser.parse(test2Reader, test2Handler);
            htmlStandardParser.parse(test3Reader, test3Handler);
            htmlAutoCloseStandardParser.parse(test4Reader, test4Handler);

            
        } finally {
            try { if (test1Reader != null) { test1Reader.close(); } } catch (final Exception ignored) { /* ignored */ }
            try { if (test2Reader != null) { test2Reader.close(); } } catch (final Exception ignored) { /* ignored */ }
            try { if (test3Reader != null) { test3Reader.close(); } } catch (final Exception ignored) { /* ignored */ }
            try { if (test4Reader != null) { test4Reader.close(); } } catch (final Exception ignored) { /* ignored */ }
            try { if (test1Writer != null) { test1Writer.close(); } } catch (final Exception ignored) { /* ignored */ }
            try { if (test2Writer != null) { test2Writer.close(); } } catch (final Exception ignored) { /* ignored */ }
            try { if (test3Writer != null) { test3Writer.close(); } } catch (final Exception ignored) { /* ignored */ }
            try { if (test4Writer != null) { test4Writer.close(); } } catch (final Exception ignored) { /* ignored */ }
        }
        
        
    }
    
    
    
    
    private HtmlCodeDisplayTest() {
        super();
    }
    
}
