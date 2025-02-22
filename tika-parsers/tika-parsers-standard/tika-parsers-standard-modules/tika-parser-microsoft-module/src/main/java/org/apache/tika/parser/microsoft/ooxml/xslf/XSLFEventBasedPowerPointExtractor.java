/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.tika.parser.microsoft.ooxml.xslf;

import java.io.IOException;
import java.util.Date;

import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.xmlbeans.XmlException;

import org.apache.tika.parser.microsoft.ooxml.OOXMLWordAndPowerPointTextHandler;
import org.apache.tika.parser.microsoft.ooxml.ParagraphProperties;
import org.apache.tika.parser.microsoft.ooxml.RunProperties;

public class XSLFEventBasedPowerPointExtractor extends POIXMLTextExtractor {


    private OPCPackage container;
    private POIXMLProperties properties;

    public XSLFEventBasedPowerPointExtractor(OPCPackage container)
            throws XmlException, OpenXML4JException, IOException {
        super((POIXMLDocument) null);
        this.container = container;
        this.properties = new POIXMLProperties(container);
    }

    public OPCPackage getPackage() {
        return this.container;
    }

    public POIXMLProperties.CoreProperties getCoreProperties() {
        return this.properties.getCoreProperties();
    }

    public POIXMLProperties.ExtendedProperties getExtendedProperties() {
        return this.properties.getExtendedProperties();
    }

    public POIXMLProperties.CustomProperties getCustomProperties() {
        return this.properties.getCustomProperties();
    }


    @Override
    public String getText() {
        //TODO
        return "";
    }

    private static class XSLFToTextContentHandler
            implements OOXMLWordAndPowerPointTextHandler.XWPFBodyContentsHandler {
        private final StringBuilder buffer;

        public XSLFToTextContentHandler(StringBuilder buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run(RunProperties runProperties, String contents) {
            buffer.append(contents);
        }

        @Override
        public void hyperlinkStart(String link) {
            //no-op
        }

        @Override
        public void hyperlinkEnd() {
            //no-op
        }

        @Override
        public void startParagraph(ParagraphProperties paragraphProperties) {
            //no-op
        }

        @Override
        public void endParagraph() {
            buffer.append("\n");
        }

        @Override
        public void startTable() {

        }

        @Override
        public void endTable() {

        }

        @Override
        public void startTableRow() {

        }

        @Override
        public void endTableRow() {
            buffer.append("\n");
        }

        @Override
        public void startTableCell() {

        }

        @Override
        public void endTableCell() {
            buffer.append("\t");
        }

        @Override
        public void startSDT() {

        }

        @Override
        public void endSDT() {

        }

        @Override
        public void startEditedSection(String editor, Date date,
                                       OOXMLWordAndPowerPointTextHandler.EditType editType) {

        }

        @Override
        public void endEditedSection() {

        }

        @Override
        public boolean isIncludeDeletedText() {
            return false;
        }

        @Override
        public void footnoteReference(String id) {

        }

        @Override
        public void endnoteReference(String id) {

        }

        @Override
        public boolean isIncludeMoveFromText() {
            return false;
        }


        @Override
        public void embeddedOLERef(String refId) {
            //no-op
        }

        @Override
        public void embeddedPicRef(String picFileName, String picDescription) {
            //no-op
        }

        @Override
        public void startBookmark(String id, String name) {

        }

        @Override
        public void endBookmark(String id) {

        }

    }
}
