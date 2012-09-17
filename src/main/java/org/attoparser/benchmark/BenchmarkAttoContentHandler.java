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

import org.attoparser.content.IAttoContentHandler;
import org.attoparser.exception.AttoParseException;

public class BenchmarkAttoContentHandler implements IAttoContentHandler {

    
    public BenchmarkAttoContentHandler() {
        super();
    }
    
    
    public void startDocument() throws AttoParseException {

    }

    public void endDocument() throws AttoParseException {

    }

    public void startElement(char[] buffer, int offset, int len,
            boolean hasBody, int line, int pos) throws AttoParseException {

    }

    public void endElement(char[] buffer, int offset, int len, int line, int pos)
            throws AttoParseException {

    }

    public void attribute(char[] nameBuffer, int nameOffset, int nameLen,
            char[] valueBuffer, int valueOffset, int valueLen, int line, int pos)
            throws AttoParseException {

    }

    public void text(char[] buffer, int offset, int len, int line, int pos)
            throws AttoParseException {

    }

    public void comment(char[] buffer, int offset, int len, int line, int pos)
            throws AttoParseException {

    }

    public void cdata(char[] buffer, int offset, int len, int line, int pos)
            throws AttoParseException {

    }

}
