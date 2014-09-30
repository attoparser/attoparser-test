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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.lang.time.StopWatch;
import org.attoparser.IMarkupAttoParser;
import org.attoparser.IMarkupAttoHandler;
import org.attoparser.MarkupAttoParser;
import org.attoparser.MarkupParsingConfiguration;
import org.attoparser.dom.DOMBuilderMarkupAttoHandler;
import org.attoparser.dom.IDocument;
import org.attoparser.dom.XmlDOMWriter;
import org.xml.sax.InputSource;

public class AttoParserVSStandardSAXBenchmark {




    private static MarkupParsingConfiguration MARKUP_PARSING_CONFIG;

    static {
        MARKUP_PARSING_CONFIG = new MarkupParsingConfiguration();
        MARKUP_PARSING_CONFIG.setCaseSensitive(false);
        MARKUP_PARSING_CONFIG.setElementBalancing(MarkupParsingConfiguration.ElementBalancing.AUTO_CLOSE);
        MARKUP_PARSING_CONFIG.setRequireUniqueAttributesInElement(false);
        MARKUP_PARSING_CONFIG.setRequireXmlWellFormedAttributeValues(false);
        MARKUP_PARSING_CONFIG.setUniqueRootElementPresence(MarkupParsingConfiguration.UniqueRootElementPresence.NOT_VALIDATED);
        MARKUP_PARSING_CONFIG.getPrologParsingConfiguration().setValidateProlog(false);
        MARKUP_PARSING_CONFIG.getPrologParsingConfiguration().setPrologPresence(MarkupParsingConfiguration.PrologPresence.ALLOWED);
        MARKUP_PARSING_CONFIG.getPrologParsingConfiguration().setXmlDeclarationPresence(MarkupParsingConfiguration.PrologPresence.ALLOWED);
        MARKUP_PARSING_CONFIG.getPrologParsingConfiguration().setDoctypePresence(MarkupParsingConfiguration.PrologPresence.ALLOWED);
        MARKUP_PARSING_CONFIG.getPrologParsingConfiguration().setRequireDoctypeKeywordsUpperCase(false);
    }





    public static String standardSaxBenchmark(final String fileName, final int iterations) throws Exception {
        
        final SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        final SAXParser parser = parserFactory.newSAXParser();
        
        final StopWatch sw = new StopWatch();
        boolean started = false;
        
        for (int i = 0; i < iterations; i++) {
            
            InputStream is = null; 
            Reader reader = null;
            
            try {
                
                is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
                reader = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));
                
                final InputSource inputSource = new InputSource(reader);

                
                final BenchmarkStandardSaxContentHandler handler = new BenchmarkStandardSaxContentHandler();
                parser.setProperty(
                    "http://xml.org/sax/properties/lexical-handler", handler);
                parser.setProperty(
                        "http://xml.org/sax/properties/declaration-handler", handler);

                if (started) {
                    sw.resume();
                } else {
                    started = true;
                    sw.start();
                }
                
                parser.parse(inputSource, handler);
                parser.reset();
                
                sw.suspend();
                
            } finally {
                try { if (reader != null) reader.close(); } catch (final Exception ignored) { /* ignored */}
                try { if (is != null) is.close(); } catch (final Exception ignored) { /* ignored */}
            }

        }
        
        sw.stop();
        
        return sw.toString();
        
    }
    
    

    public static String attoParserBenchmark(final String fileName, final int iterations) throws Exception {
        
        final IMarkupAttoParser parser = new MarkupAttoParser(MARKUP_PARSING_CONFIG);
        
        final StopWatch sw = new StopWatch();
        boolean started = false;
        
        for (int i = 0; i < iterations; i++) {
            
            InputStream is = null; 
            Reader reader = null;
            
            try {
                
                is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
                reader = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));
                
                final IMarkupAttoHandler handler = BenchmarkAttoHandlerFactory.getBenchmkarpMarkupAttoHandler();

                if (started) {
                    sw.resume();
                } else {
                    started = true;
                    sw.start();
                }
                
                parser.parse(reader, handler);
                
                sw.suspend();

            } finally {
                try { if (reader != null) reader.close(); } catch (final Exception ignored) { /* ignored */}
                try { if (is != null) is.close(); } catch (final Exception ignored) { /* ignored */}
            }

        }
        
        sw.stop();
        
        return sw.toString();
        
    }
    
    

    public static void attoDOMOutput(final String fileName) throws Exception {
        
        final IMarkupAttoParser parser = new MarkupAttoParser(MARKUP_PARSING_CONFIG);
        
        InputStream is = null; 
        Reader reader = null;
        
        final StopWatch sw = new StopWatch();
        
        try {
            
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            reader = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));

            sw.start();
            
            final DOMBuilderMarkupAttoHandler handler = new DOMBuilderMarkupAttoHandler();
            parser.parse(reader, handler);
            
            final IDocument document = handler.getDocument();
            
            final StringWriter writer = new StringWriter();
            final XmlDOMWriter xmlWriter = new XmlDOMWriter();

            xmlWriter.write(document, writer);
            System.out.println(writer.toString());
            sw.stop();

        } finally {
            try { if (reader != null) reader.close(); } catch (final Exception ignored) { /* ignored */}
            try { if (is != null) is.close(); } catch (final Exception ignored) { /* ignored */}
        }
        
    }
    

    private static final String getSAXParserClassName() throws Exception {
        final SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        final SAXParser parser = parserFactory.newSAXParser();
        return parser.getClass().getName();
    }

    
    
    
    
    
    public static void main(final String[] args) {
    
        try {
            
            final int iterations = 100000;

            {
                final String fileName = "test1.html";
                attoDOMOutput(fileName);
                final String attoTime = attoParserBenchmark(fileName, iterations);
                final String saxTime = standardSaxBenchmark(fileName, iterations);
                final String saxParserClass = getSAXParserClassName();
                
                System.out.println("\n***TEST 1***");
                System.out.println(" * FILE:       " + fileName);
                System.out.println(" * ITERATIONS: " + iterations);
                System.out.println(" * SAX Impl.:  " + saxParserClass);
                System.out.println(" * RESULTS:  ");
                System.out.println("   > ATTO: " + attoTime);
                System.out.println("   > SAX: " + saxTime);
                System.out.println("************\n");
                
            }

            {
                final String fileName = "test2.html";
                final String attoTime = attoParserBenchmark(fileName, iterations);
                final String saxTime = standardSaxBenchmark(fileName, iterations);
                final String saxParserClass = getSAXParserClassName();
                
                System.out.println("\n***TEST 2***");
                System.out.println(" * FILE:       " + fileName);
                System.out.println(" * ITERATIONS: " + iterations);
                System.out.println(" * SAX Impl.:  " + saxParserClass);
                System.out.println(" * RESULTS:  ");
                System.out.println("   > ATTO: " + attoTime);
                System.out.println("   > SAX: " + saxTime);
                System.out.println("************\n");

            }

            {
                final String fileName = "test3.html";
                final String attoTime = attoParserBenchmark(fileName, iterations);
                final String saxTime = standardSaxBenchmark(fileName, iterations);
                final String saxParserClass = getSAXParserClassName();
                
                System.out.println("\n***TEST 3***");
                System.out.println(" * FILE:       " + fileName);
                System.out.println(" * ITERATIONS: " + iterations);
                System.out.println(" * SAX Impl.:  " + saxParserClass);
                System.out.println(" * RESULTS:  ");
                System.out.println("   > ATTO: " + attoTime);
                System.out.println("   > SAX: " + saxTime);
                System.out.println("************\n");

            }
            
        } catch (final Throwable t) {
            t.printStackTrace(System.err);
        }
        
    }

    
    private AttoParserVSStandardSAXBenchmark() {
        super();
    }
    
    
    
}
