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

import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.DefaultHandler2;

public class BenchmarkStandardSaxContentHandler extends DefaultHandler2 {

    private int eventCounter = 0;
    
    public BenchmarkStandardSaxContentHandler() {
        super();
    }


    public int getEventCounter() {
        return this.eventCounter;
    }

    @Override
    public void startCDATA() throws SAXException {
        this.eventCounter++;
    }

    @Override
    public void endCDATA() throws SAXException {
        this.eventCounter++;
    }

    @Override
    public void startDTD(final String name, final String publicId, final String systemId) throws SAXException {
        this.eventCounter++;
    }

    @Override
    public void endDTD() throws SAXException {
        this.eventCounter++;
    }

    @Override
    public void startEntity(final String name) throws SAXException {
        this.eventCounter++;
    }

    @Override
    public void endEntity(final String name) throws SAXException {
        this.eventCounter++;
    }

    @Override
    public void comment(final char[] ch, final int start, final int length) throws SAXException {
        this.eventCounter++;
    }

    @Override
    public void attributeDecl(final String eName, final String aName, final String type, final String mode, final String value) throws SAXException {
        this.eventCounter++;
    }

    @Override
    public void elementDecl(final String name, final String model) throws SAXException {
        this.eventCounter++;
    }

    @Override
    public void externalEntityDecl(final String name, final String publicId, final String systemId) throws SAXException {
        this.eventCounter++;
    }

    @Override
    public void internalEntityDecl(final String name, final String value) throws SAXException {
        this.eventCounter++;
    }

    @Override
    public InputSource getExternalSubset(final String name, final String baseURI) throws SAXException, IOException {
        this.eventCounter++;
        return super.getExternalSubset(name, baseURI);
    }

    @Override
    public InputSource resolveEntity(final String name, final String publicId, final String baseURI, final String systemId) throws SAXException, IOException {
        this.eventCounter++;
        return super.resolveEntity(name, publicId, baseURI, systemId);
    }

    @Override
    public InputSource resolveEntity(final String publicId, final String systemId) throws SAXException, IOException {
        this.eventCounter++;
        return super.resolveEntity(publicId, systemId);
    }

    @Override
    public void notationDecl(final String name, final String publicId, final String systemId) throws SAXException {
        this.eventCounter++;
    }

    @Override
    public void unparsedEntityDecl(final String name, final String publicId, final String systemId, final String notationName) throws SAXException {
        this.eventCounter++;
    }

    @Override
    public void setDocumentLocator(final Locator locator) {
        this.eventCounter++;
    }

    @Override
    public void startDocument() throws SAXException {
        this.eventCounter++;
    }

    @Override
    public void endDocument() throws SAXException {
        this.eventCounter++;
    }

    @Override
    public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
        this.eventCounter++;
    }

    @Override
    public void endPrefixMapping(final String prefix) throws SAXException {
        this.eventCounter++;
    }

    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) throws SAXException {
        this.eventCounter++;
    }

    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        this.eventCounter++;
    }

    @Override
    public void characters(final char[] ch, final int start, final int length) throws SAXException {
        this.eventCounter++;
    }

    @Override
    public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
        this.eventCounter++;
    }

    @Override
    public void processingInstruction(final String target, final String data) throws SAXException {
        this.eventCounter++;
    }

    @Override
    public void warning(final SAXParseException e) throws SAXException {
        this.eventCounter++;
    }

    @Override
    public void skippedEntity(final String name) throws SAXException {
        this.eventCounter++;
    }

    @Override
    public void error(final SAXParseException e) throws SAXException {
        this.eventCounter++;
    }

    @Override
    public void fatalError(final SAXParseException e) throws SAXException {
        this.eventCounter++;
    }

}
