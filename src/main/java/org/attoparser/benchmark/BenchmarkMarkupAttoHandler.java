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

import org.attoparser.AbstractMarkupAttoHandler;
import org.attoparser.AttoParseException;
import org.attoparser.IAttoHandleResult;
import org.attoparser.IElementPreparationResult;
import org.attoparser.IMarkupAttoHandler;
import org.attoparser.html.HtmlMarkupAttoHandler;


public class BenchmarkMarkupAttoHandler extends AbstractMarkupAttoHandler {


    private int eventCounter = 0;


    public BenchmarkMarkupAttoHandler() {
        super();
    }

    public int getEventCounter() {
        return this.eventCounter;
    }


    @Override
    public IAttoHandleResult handleDocumentStart(final long startTimeNanos, final int line, final int col) throws AttoParseException {
        this.eventCounter++;
        return null;
    }

    @Override
    public IAttoHandleResult handleDocumentEnd(final long endTimeNanos, final long totalTimeNanos, final int line, final int col) throws AttoParseException {
        this.eventCounter++;
        return null;
    }

    @Override
    public IAttoHandleResult handleXmlDeclaration(final char[] buffer, final int keywordOffset, final int keywordLen, final int keywordLine, final int keywordCol, final int versionOffset, final int versionLen, final int versionLine, final int versionCol, final int encodingOffset, final int encodingLen, final int encodingLine, final int encodingCol, final int standaloneOffset, final int standaloneLen, final int standaloneLine, final int standaloneCol, final int outerOffset, final int outerLen, final int line, final int col) throws AttoParseException {
        this.eventCounter++;
        return null;
    }

    @Override
    public IAttoHandleResult handleDocType(final char[] buffer, final int keywordOffset, final int keywordLen, final int keywordLine, final int keywordCol, final int elementNameOffset, final int elementNameLen, final int elementNameLine, final int elementNameCol, final int typeOffset, final int typeLen, final int typeLine, final int typeCol, final int publicIdOffset, final int publicIdLen, final int publicIdLine, final int publicIdCol, final int systemIdOffset, final int systemIdLen, final int systemIdLine, final int systemIdCol, final int internalSubsetOffset, final int internalSubsetLen, final int internalSubsetLine, final int internalSubsetCol, final int outerOffset, final int outerLen, final int outerLine, final int outerCol) throws AttoParseException {
        this.eventCounter++;
        return null;
    }

    @Override
    public IAttoHandleResult handleCDATASection(final char[] buffer, final int contentOffset, final int contentLen, final int outerOffset, final int outerLen, final int line, final int col) throws AttoParseException {
        this.eventCounter++;
        return null;
    }

    @Override
    public IAttoHandleResult handleComment(final char[] buffer, final int contentOffset, final int contentLen, final int outerOffset, final int outerLen, final int line, final int col) throws AttoParseException {
        this.eventCounter++;
        return null;
    }

    @Override
    public IAttoHandleResult handleText(final char[] buffer, final int offset, final int len, final int line, final int col) throws AttoParseException {
        this.eventCounter++;
        return null;
    }

    @Override
    public IElementPreparationResult prepareForElement(final char[] buffer, final int nameOffset, final int nameLen, final int line, final int col) throws AttoParseException {
        this.eventCounter++;
        return null;
    }

    @Override
    public IAttoHandleResult handleStandaloneElementStart(final char[] buffer, final int nameOffset, final int nameLen, final boolean minimized, final int line, final int col) throws AttoParseException {
        this.eventCounter++;
        return null;
    }

    @Override
    public IAttoHandleResult handleStandaloneElementEnd(final char[] buffer, final int nameOffset, final int nameLen, final boolean minimized, final int line, final int col) throws AttoParseException {
        this.eventCounter++;
        return null;
    }

    @Override
    public IAttoHandleResult handleOpenElementStart(final char[] buffer, final int nameOffset, final int nameLen, final int line, final int col) throws AttoParseException {
        this.eventCounter++;
        return null;
    }

    @Override
    public IAttoHandleResult handleOpenElementEnd(final char[] buffer, final int nameOffset, final int nameLen, final int line, final int col) throws AttoParseException {
        this.eventCounter++;
        return null;
    }

    @Override
    public IAttoHandleResult handleCloseElementStart(final char[] buffer, final int nameOffset, final int nameLen, final int line, final int col) throws AttoParseException {
        this.eventCounter++;
        return null;
    }

    @Override
    public IAttoHandleResult handleCloseElementEnd(final char[] buffer, final int nameOffset, final int nameLen, final int line, final int col) throws AttoParseException {
        this.eventCounter++;
        return null;
    }

    @Override
    public IAttoHandleResult handleAutoCloseElementStart(final char[] buffer, final int nameOffset, final int nameLen, final int line, final int col) throws AttoParseException {
        this.eventCounter++;
        return null;
    }

    @Override
    public IAttoHandleResult handleAutoCloseElementEnd(final char[] buffer, final int nameOffset, final int nameLen, final int line, final int col) throws AttoParseException {
        this.eventCounter++;
        return null;
    }

    @Override
    public IAttoHandleResult handleUnmatchedCloseElementStart(final char[] buffer, final int nameOffset, final int nameLen, final int line, final int col) throws AttoParseException {
        this.eventCounter++;
        return null;
    }

    @Override
    public IAttoHandleResult handleUnmatchedCloseElementEnd(final char[] buffer, final int nameOffset, final int nameLen, final int line, final int col) throws AttoParseException {
        this.eventCounter++;
        return null;
    }

    @Override
    public IAttoHandleResult handleAttribute(final char[] buffer, final int nameOffset, final int nameLen, final int nameLine, final int nameCol, final int operatorOffset, final int operatorLen, final int operatorLine, final int operatorCol, final int valueContentOffset, final int valueContentLen, final int valueOuterOffset, final int valueOuterLen, final int valueLine, final int valueCol) throws AttoParseException {
        this.eventCounter++;
        return null;
    }

    @Override
    public IAttoHandleResult handleInnerWhiteSpace(final char[] buffer, final int offset, final int len, final int line, final int col) throws AttoParseException {
        this.eventCounter++;
        return null;
    }

    @Override
    public IAttoHandleResult handleProcessingInstruction(final char[] buffer, final int targetOffset, final int targetLen, final int targetLine, final int targetCol, final int contentOffset, final int contentLen, final int contentLine, final int contentCol, final int outerOffset, final int outerLen, final int line, final int col) throws AttoParseException {
        this.eventCounter++;
        return null;
    }

}
