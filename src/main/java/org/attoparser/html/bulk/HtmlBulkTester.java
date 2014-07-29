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
package org.attoparser.html.bulk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.attoparser.markup.MarkupAttoParser;
import org.attoparser.markup.duplicate.DuplicatingDetailedMarkupAttoHandler;

public class HtmlBulkTester {



    public static void main(final String[] args) throws Exception {

        if (args.length != 1) {
            System.err.println("Syntax: java " + HtmlBulkTester.class.getName() + " [test_folder]");
            System.exit(1);
        }

        final String testFolderName = args[0];

        final File testFolder = new File(testFolderName);

        if (!testFolder.exists() || !testFolder.isDirectory()) {
            System.err.println("Folder " + testFolderName + " does not exist or is not a folder");
            System.exit(1);
        }

        final MarkupAttoParser parser = new MarkupAttoParser();

        System.out.println("Using temporary folder for output: " + System.getProperty("java.io.tmpdir"));

        final File[] filesInTestFolder = testFolder.listFiles();
        for (final File fileInTestFolder : filesInTestFolder) {

            if (!fileInTestFolder.getName().endsWith(".html")) {
                continue;
            }

            final String fileInTestFolderName = fileInTestFolder.getName();
            final FileInputStream fileInTestFolderStream = new FileInputStream(fileInTestFolder);
            final InputStreamReader fileInTestFolderReader = new InputStreamReader(fileInTestFolderStream, "UTF-8");


            final File testOutput = File.createTempFile("attoparser-testing-" + fileInTestFolderName + "-", ".html");
            testOutput.deleteOnExit();
            final FileOutputStream testOutputStream = new FileOutputStream(testOutput);
            final OutputStreamWriter testOutputWriter = new OutputStreamWriter(testOutputStream, "UTF-8");

            final DuplicatingDetailedMarkupAttoHandler handler = new DuplicatingDetailedMarkupAttoHandler(testOutputWriter);

            System.out.print(fileInTestFolderName);

            System.out.print("[PARSING]");

            parser.parse(fileInTestFolderReader, handler);

            // Input stream will be closed by parser
            testOutputWriter.close();
            testOutputStream.close();

            System.out.print("[PARSED]");

            final FileInputStream testOutputCheckStream = new FileInputStream(testOutput);
            final List<String> outputLines = IOUtils.readLines(testOutputCheckStream, "UTF-8");

            final FileInputStream testInputCheckStream = new FileInputStream(fileInTestFolder);
            final List<String> inputLines = IOUtils.readLines(testInputCheckStream, "UTF-8");

            System.out.print("[CHECKING]");

            if (outputLines.equals(inputLines)) {
                System.out.print("[OK]");
            } else {
                System.out.print("[KO]");
            }

            System.out.println();

        }


    }




    private HtmlBulkTester() {
        super();
    }
    
}
