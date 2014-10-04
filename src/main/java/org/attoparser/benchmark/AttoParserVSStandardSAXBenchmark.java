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
import org.attoparser.IMarkupHandler;
import org.attoparser.IMarkupParser;
import org.attoparser.MarkupParser;
import org.attoparser.config.ParseConfiguration;
import org.attoparser.dom.DOMBuilderMarkupHandler;
import org.attoparser.dom.IDocument;
import org.attoparser.dom.XmlDOMWriter;
import org.xml.sax.InputSource;

public class AttoParserVSStandardSAXBenchmark {




    private static ParseConfiguration MARKUP_PARSING_CONFIG;
    private static ParseConfiguration HTML_MARKUP_PARSING_CONFIG;

    static {

        MARKUP_PARSING_CONFIG = new ParseConfiguration();
        MARKUP_PARSING_CONFIG.setMode(ParseConfiguration.ParsingMode.XML);
        MARKUP_PARSING_CONFIG.setCaseSensitive(true);
        MARKUP_PARSING_CONFIG.setElementBalancing(ParseConfiguration.ElementBalancing.NO_BALANCING);
        MARKUP_PARSING_CONFIG.setRequireUniqueAttributesInElement(false);
        MARKUP_PARSING_CONFIG.setRequireXmlWellFormedAttributeValues(false);
        MARKUP_PARSING_CONFIG.setUniqueRootElementPresence(ParseConfiguration.UniqueRootElementPresence.NOT_VALIDATED);
        MARKUP_PARSING_CONFIG.getPrologParseConfiguration().setValidateProlog(false);
        MARKUP_PARSING_CONFIG.getPrologParseConfiguration().setPrologPresence(ParseConfiguration.PrologPresence.ALLOWED);
        MARKUP_PARSING_CONFIG.getPrologParseConfiguration().setXmlDeclarationPresence(ParseConfiguration.PrologPresence.ALLOWED);
        MARKUP_PARSING_CONFIG.getPrologParseConfiguration().setDoctypePresence(ParseConfiguration.PrologPresence.ALLOWED);
        MARKUP_PARSING_CONFIG.getPrologParseConfiguration().setRequireDoctypeKeywordsUpperCase(false);

        HTML_MARKUP_PARSING_CONFIG = new ParseConfiguration();
        HTML_MARKUP_PARSING_CONFIG.setMode(ParseConfiguration.ParsingMode.HTML);
        HTML_MARKUP_PARSING_CONFIG.setCaseSensitive(false);
        HTML_MARKUP_PARSING_CONFIG.setElementBalancing(ParseConfiguration.ElementBalancing.AUTO_CLOSE);
        HTML_MARKUP_PARSING_CONFIG.setRequireUniqueAttributesInElement(false);
        HTML_MARKUP_PARSING_CONFIG.setRequireXmlWellFormedAttributeValues(false);
        HTML_MARKUP_PARSING_CONFIG.setUniqueRootElementPresence(ParseConfiguration.UniqueRootElementPresence.NOT_VALIDATED);
        HTML_MARKUP_PARSING_CONFIG.getPrologParseConfiguration().setValidateProlog(false);
        HTML_MARKUP_PARSING_CONFIG.getPrologParseConfiguration().setPrologPresence(ParseConfiguration.PrologPresence.ALLOWED);
        HTML_MARKUP_PARSING_CONFIG.getPrologParseConfiguration().setXmlDeclarationPresence(ParseConfiguration.PrologPresence.ALLOWED);
        HTML_MARKUP_PARSING_CONFIG.getPrologParseConfiguration().setDoctypePresence(ParseConfiguration.PrologPresence.ALLOWED);
        HTML_MARKUP_PARSING_CONFIG.getPrologParseConfiguration().setRequireDoctypeKeywordsUpperCase(false);

    }





    public static String standardSaxBenchmark(final String fileName, final int iterations) throws Exception {
        
        final SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        final SAXParser parser = parserFactory.newSAXParser();


        /*
         * WARMUP BEGIN
         */
        System.out.println("Warming up phase for SAX STARTED");
        for (int i = 0; i < 10000; i++) {

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

                parser.parse(inputSource, handler);
                parser.reset();

                handler.getEventCounter();

            } finally {
                try { if (reader != null) reader.close(); } catch (final Exception ignored) { /* ignored */}
                try { if (is != null) is.close(); } catch (final Exception ignored) { /* ignored */}
            }

        }
        /*
         * WARMUP END
         */
        System.out.println("Warming up phase for SAX FINISHED");



        final StopWatch sw = new StopWatch();
        boolean started = false;

        int eventCounter = 0;
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

                eventCounter = handler.getEventCounter();

            } finally {
                try { if (reader != null) reader.close(); } catch (final Exception ignored) { /* ignored */}
                try { if (is != null) is.close(); } catch (final Exception ignored) { /* ignored */}
            }

        }
        
        sw.stop();
        
        return "[" + eventCounter + "] " + sw.toString();
        
    }
    
    

    public static String attoParserBenchmark(final String fileName, final int iterations) throws Exception {
        
        final IMarkupParser parser = new MarkupParser(MARKUP_PARSING_CONFIG, false, MarkupParser.DEFAULT_POOL_SIZE, MarkupParser.DEFAULT_BUFFER_SIZE * 2);


        /*
         * WARMUP BEGIN
         */
        System.out.println("Warming up phase for ATTO STARTED");
        for (int i = 0; i < 10000; i++) {

            InputStream is = null;
            Reader reader = null;

            try {

                is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
                reader = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));

                final BenchmarkMarkupHandler handler =  new BenchmarkMarkupHandler();
                parser.parse(reader, handler);

                handler.getEventCounter();

            } finally {
                try { if (reader != null) reader.close(); } catch (final Exception ignored) { /* ignored */}
                try { if (is != null) is.close(); } catch (final Exception ignored) { /* ignored */}
            }

        }
        /*
         * WARMUP END
         */
        System.out.println("Warming up phase for ATTO FINISHED");



        final StopWatch sw = new StopWatch();
        boolean started = false;

        int eventCounter = 0;
        for (int i = 0; i < iterations; i++) {
            
            InputStream is = null; 
            Reader reader = null;
            
            try {
                
                is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
                reader = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));

                final BenchmarkMarkupHandler benchmarkHandler = new BenchmarkMarkupHandler();
                final IMarkupHandler handler = benchmarkHandler;

                if (started) {
                    sw.resume();
                } else {
                    started = true;
                    sw.start();
                }
                
                parser.parse(reader, handler);
                
                sw.suspend();

                eventCounter = benchmarkHandler.getEventCounter();

            } finally {
                try { if (reader != null) reader.close(); } catch (final Exception ignored) { /* ignored */}
                try { if (is != null) is.close(); } catch (final Exception ignored) { /* ignored */}
            }

        }
        
        sw.stop();

        return "[" + eventCounter + "] " + sw.toString();
        
    }



    public static String attoParserHtmlBenchmark(final String fileName, final int iterations) throws Exception {

        final IMarkupParser parser = new MarkupParser(HTML_MARKUP_PARSING_CONFIG, false, MarkupParser.DEFAULT_POOL_SIZE, MarkupParser.DEFAULT_BUFFER_SIZE * 2);


        /*
         * WARMUP BEGIN
         */
        System.out.println("Warming up phase for ATTO(HTML) STARTED");
        for (int i = 0; i < 10000; i++) {

            InputStream is = null;
            Reader reader = null;

            try {

                is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
                reader = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));

                final BenchmarkMarkupHandler handler =  new BenchmarkMarkupHandler();
                parser.parse(reader, handler);

                handler.getEventCounter();

            } finally {
                try { if (reader != null) reader.close(); } catch (final Exception ignored) { /* ignored */}
                try { if (is != null) is.close(); } catch (final Exception ignored) { /* ignored */}
            }

        }
        /*
         * WARMUP END
         */
        System.out.println("Warming up phase for ATTO(HTML) FINISHED");

        final StopWatch sw = new StopWatch();
        boolean started = false;

        int eventCounter = 0;
        for (int i = 0; i < iterations; i++) {

            InputStream is = null;
            Reader reader = null;

            try {

                is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
                reader = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));

                final BenchmarkMarkupHandler handler = new BenchmarkMarkupHandler();

                if (started) {
                    sw.resume();
                } else {
                    started = true;
                    sw.start();
                }

                parser.parse(reader, handler);

                sw.suspend();

                eventCounter = handler.getEventCounter();

            } finally {
                try { if (reader != null) reader.close(); } catch (final Exception ignored) { /* ignored */}
                try { if (is != null) is.close(); } catch (final Exception ignored) { /* ignored */}
            }

        }

        sw.stop();

        return "[" + eventCounter + "] " + sw.toString();

    }

    

    public static void attoDOMOutput(final String fileName) throws Exception {
        
        final IMarkupParser parser = new MarkupParser(MARKUP_PARSING_CONFIG);
        
        InputStream is = null; 
        Reader reader = null;
        
        final StopWatch sw = new StopWatch();
        
        try {
            
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            reader = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));

            sw.start();
            
            final DOMBuilderMarkupHandler handler = new DOMBuilderMarkupHandler();
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
    

    private static String getSAXParserClassName() throws Exception {
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
                final String attoHtmlTime = attoParserHtmlBenchmark(fileName, iterations);
                final String saxTime = standardSaxBenchmark(fileName, iterations);
                final String saxParserClass = getSAXParserClassName();
                
                System.out.println("\n***TEST 1***");
                System.out.println(" * FILE:       " + fileName);
                System.out.println(" * ITERATIONS: " + iterations);
                System.out.println(" * SAX Impl.:  " + saxParserClass);
                System.out.println(" * RESULTS:  ");
                System.out.println("   > ATTO:       " + attoTime);
                System.out.println("   > ATTO(HTML): " + attoHtmlTime);
                System.out.println("   > SAX:        " + saxTime);
                System.out.println("************\n");
                
            }

            {
                final String fileName = "test2.html";
                final String attoTime = attoParserBenchmark(fileName, iterations);
                final String attoHtmlTime = attoParserHtmlBenchmark(fileName, iterations);
                final String saxTime = standardSaxBenchmark(fileName, iterations);
                final String saxParserClass = getSAXParserClassName();
                
                System.out.println("\n***TEST 2***");
                System.out.println(" * FILE:       " + fileName);
                System.out.println(" * ITERATIONS: " + iterations);
                System.out.println(" * SAX Impl.:  " + saxParserClass);
                System.out.println(" * RESULTS:  ");
                System.out.println("   > ATTO:       " + attoTime);
                System.out.println("   > ATTO(HTML): " + attoHtmlTime);
                System.out.println("   > SAX:        " + saxTime);
                System.out.println("************\n");

            }

            {
                final String fileName = "test3.html";
                final String attoTime = attoParserBenchmark(fileName, iterations);
                final String attoHtmlTime = attoParserHtmlBenchmark(fileName, iterations);

                System.out.println("\n***TEST 3***");
                System.out.println(" * FILE:       " + fileName);
                System.out.println(" * ITERATIONS: " + iterations);
                System.out.println(" * RESULTS:  ");
                System.out.println("   > ATTO:       " + attoTime);
                System.out.println("   > ATTO(HTML): " + attoHtmlTime);
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
