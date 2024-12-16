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
package org.attoparser.html.bulk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.attoparser.MarkupParser;
import org.attoparser.config.ParseConfiguration;
import org.attoparser.prettyhtml.PrettyHtmlMarkupHandler;

public class HtmlBulkDisplayer {





    public static void main(final String[] args) throws Exception {

        if (args.length != 1) {
            System.err.println("Syntax: java " + HtmlBulkDisplayer.class.getName() + " [test_folder]");
            System.exit(1);
        }

        final String testFolderName = args[0];

        final File testFolder = new File(testFolderName);

        if (!testFolder.exists() || !testFolder.isDirectory()) {
            System.err.println("Folder " + testFolderName + " does not exist or is not a folder");
            System.exit(1);
        }

        final MarkupParser parser = new MarkupParser(ParseConfiguration.htmlConfiguration());

        final File displayFolder = new File(testFolder.getAbsolutePath() + File.separator + "display");
        displayFolder.mkdir();

        System.out.println("Using folder for output: " + displayFolder.getAbsolutePath());

        final File[] filesInTestFolder = testFolder.listFiles();
        for (final File fileInTestFolder : filesInTestFolder) {

            if (!fileInTestFolder.getName().endsWith(".html")) {
                continue;
            }

            final String fileInTestFolderName = fileInTestFolder.getName();
            final FileInputStream fileInTestFolderStream = new FileInputStream(fileInTestFolder);
            final InputStreamReader fileInTestFolderReader = new InputStreamReader(fileInTestFolderStream, "UTF-8");


            final File testOutput =
                    new File(displayFolder.getAbsolutePath() + File.separator + fileInTestFolderName);
            testOutput.createNewFile();

            final FileOutputStream testOutputStream = new FileOutputStream(testOutput);
            final OutputStreamWriter testOutputWriter = new OutputStreamWriter(testOutputStream, "UTF-8");

            final PrettyHtmlMarkupHandler handler = new PrettyHtmlMarkupHandler(testOutput.getName(), testOutputWriter);

            System.out.print(fileInTestFolderName);

            System.out.print("[PROCESSING]");

            parser.parse(fileInTestFolderReader, handler);

            // Input stream will be closed by parser
            testOutputWriter.close();
            testOutputStream.close();

            System.out.print("[PROCESSED]");

            System.out.println();

        }


    }




    private HtmlBulkDisplayer() {
        super();
    }
    
}
