/*
 * =============================================================================
 *
 *   Copyright (c) 2012-2025 Attoparser (http://www.attoparser.org)
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

import org.attoparser.AbstractMarkupHandler;
import org.attoparser.ParseException;


public class BenchmarkMarkupHandler extends AbstractMarkupHandler {


    private int eventCounter = 0;


    public BenchmarkMarkupHandler() {
        super();
    }

    public int getEventCounter() {
        return this.eventCounter;
    }


    @Override
    public void handleDocumentStart(final long startTimeNanos, final int line, final int col) throws ParseException {
        this.eventCounter++;
    }

    @Override
    public void handleDocumentEnd(final long endTimeNanos, final long totalTimeNanos, final int line, final int col) throws ParseException {
        this.eventCounter++;
    }

    @Override
    public void handleXmlDeclaration(final char[] buffer, final int keywordOffset, final int keywordLen, final int keywordLine, final int keywordCol, final int versionOffset, final int versionLen, final int versionLine, final int versionCol, final int encodingOffset, final int encodingLen, final int encodingLine, final int encodingCol, final int standaloneOffset, final int standaloneLen, final int standaloneLine, final int standaloneCol, final int outerOffset, final int outerLen, final int line, final int col) throws ParseException {
        this.eventCounter++;
    }

    @Override
    public void handleDocType(final char[] buffer, final int keywordOffset, final int keywordLen, final int keywordLine, final int keywordCol, final int elementNameOffset, final int elementNameLen, final int elementNameLine, final int elementNameCol, final int typeOffset, final int typeLen, final int typeLine, final int typeCol, final int publicIdOffset, final int publicIdLen, final int publicIdLine, final int publicIdCol, final int systemIdOffset, final int systemIdLen, final int systemIdLine, final int systemIdCol, final int internalSubsetOffset, final int internalSubsetLen, final int internalSubsetLine, final int internalSubsetCol, final int outerOffset, final int outerLen, final int outerLine, final int outerCol) throws ParseException {
        this.eventCounter++;
    }

    @Override
    public void handleCDATASection(final char[] buffer, final int contentOffset, final int contentLen, final int outerOffset, final int outerLen, final int line, final int col) throws ParseException {
        this.eventCounter++;
    }

    @Override
    public void handleComment(final char[] buffer, final int contentOffset, final int contentLen, final int outerOffset, final int outerLen, final int line, final int col) throws ParseException {
        this.eventCounter++;
    }

    @Override
    public void handleText(final char[] buffer, final int offset, final int len, final int line, final int col) throws ParseException {
        this.eventCounter++;
    }

    @Override
    public void handleStandaloneElementStart(final char[] buffer, final int nameOffset, final int nameLen, final boolean minimized, final int line, final int col) throws ParseException {
        this.eventCounter++;
    }

    @Override
    public void handleStandaloneElementEnd(final char[] buffer, final int nameOffset, final int nameLen, final boolean minimized, final int line, final int col) throws ParseException {
        this.eventCounter++;
    }

    @Override
    public void handleOpenElementStart(final char[] buffer, final int nameOffset, final int nameLen, final int line, final int col) throws ParseException {
        this.eventCounter++;
    }

    @Override
    public void handleOpenElementEnd(final char[] buffer, final int nameOffset, final int nameLen, final int line, final int col) throws ParseException {
        this.eventCounter++;
    }

    @Override
    public void handleCloseElementStart(final char[] buffer, final int nameOffset, final int nameLen, final int line, final int col) throws ParseException {
        this.eventCounter++;
    }

    @Override
    public void handleCloseElementEnd(final char[] buffer, final int nameOffset, final int nameLen, final int line, final int col) throws ParseException {
        this.eventCounter++;
    }

    @Override
    public void handleAutoCloseElementStart(final char[] buffer, final int nameOffset, final int nameLen, final int line, final int col) throws ParseException {
        this.eventCounter++;
    }

    @Override
    public void handleAutoCloseElementEnd(final char[] buffer, final int nameOffset, final int nameLen, final int line, final int col) throws ParseException {
        this.eventCounter++;
    }

    @Override
    public void handleUnmatchedCloseElementStart(final char[] buffer, final int nameOffset, final int nameLen, final int line, final int col) throws ParseException {
        this.eventCounter++;
    }

    @Override
    public void handleUnmatchedCloseElementEnd(final char[] buffer, final int nameOffset, final int nameLen, final int line, final int col) throws ParseException {
        this.eventCounter++;
    }

    @Override
    public void handleAttribute(final char[] buffer, final int nameOffset, final int nameLen, final int nameLine, final int nameCol, final int operatorOffset, final int operatorLen, final int operatorLine, final int operatorCol, final int valueContentOffset, final int valueContentLen, final int valueOuterOffset, final int valueOuterLen, final int valueLine, final int valueCol) throws ParseException {
        this.eventCounter++;
    }

    @Override
    public void handleInnerWhiteSpace(final char[] buffer, final int offset, final int len, final int line, final int col) throws ParseException {
        this.eventCounter++;
    }

    @Override
    public void handleProcessingInstruction(final char[] buffer, final int targetOffset, final int targetLen, final int targetLine, final int targetCol, final int contentOffset, final int contentLen, final int contentLine, final int contentCol, final int outerOffset, final int outerLen, final int line, final int col) throws ParseException {
        this.eventCounter++;
    }

}
