/*
 *  Copyright (c) 2001-2003, Jean Tessier
 *  All rights reserved.
 *  
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  
 *  	* Redistributions of source code must retain the above copyright
 *  	  notice, this list of conditions and the following disclaimer.
 *  
 *  	* Redistributions in binary form must reproduce the above copyright
 *  	  notice, this list of conditions and the following disclaimer in the
 *  	  documentation and/or other materials provided with the distribution.
 *  
 *  	* Neither the name of Jean Tessier nor the names of his contributors
 *  	  may be used to endorse or promote products derived from this software
 *  	  without specific prior written permission.
 *  
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *  "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 *  LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 *  A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE REGENTS OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 *  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 *  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.jeantessier.diff;

import java.io.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import org.apache.oro.text.perl.*;

import junit.framework.*;

public class TestReport extends TestCase implements ErrorHandler {
	private static final String READER_CLASSNAME = "org.apache.xerces.parsers.SAXParser";

	private static final String SPECIFIC_ENCODING   = "iso-latin-1";
	private static final String SPECIFIC_DTD_PREFIX = "./etc";

	private Visitor   printer;
	private XMLReader reader;

	private Perl5Util perl;

	protected void setUp() throws Exception {
		reader = XMLReaderFactory.createXMLReader(READER_CLASSNAME);
		reader.setFeature("http://xml.org/sax/features/validation", true);
		reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", true);
		reader.setErrorHandler(this);

		perl = new Perl5Util();
	}

	public void testDefaultDTDPrefix() {
		printer = new Report();

		String xml_document = printer.toString();
		assertTrue(xml_document + "Missing DTD", perl.match("/DOCTYPE \\S+ SYSTEM \"(.*)\"/", xml_document));
		assertTrue("DTD \"" + perl.group(1) + "\" does not have prefix \"" + Report.DEFAULT_DTD_PREFIX + "\"", perl.group(1).startsWith(Report.DEFAULT_DTD_PREFIX));
		
		try {
			reader.parse(new InputSource(new StringReader(xml_document)));
		} catch (SAXException ex) {
			fail("Could not parse XML Document: " + ex.getMessage() + "\n" + xml_document);
		} catch (IOException ex) {
			fail("Could not read XML Document: " + ex.getMessage() + "\n" + xml_document);
		}
	}
	
	public void testSpecificDTDPrefix() {
		printer = new Report(Report.DEFAULT_ENCODING, SPECIFIC_DTD_PREFIX);

		String xml_document = printer.toString();
		assertTrue(xml_document + "Missing DTD", perl.match("/DOCTYPE \\S+ SYSTEM \"(.*)\"/", xml_document));
		assertTrue("DTD \"" + perl.group(1) + "\" does not have prefix \"./etc\"", perl.group(1).startsWith(SPECIFIC_DTD_PREFIX));
		
		try {
			reader.parse(new InputSource(new StringReader(xml_document)));
		} catch (SAXException ex) {
			fail("Could not parse XML Document: " + ex.getMessage() + "\n" + xml_document);
		} catch (IOException ex) {
			fail("Could not read XML Document: " + ex.getMessage() + "\n" + xml_document);
		}
	}

	public void testDefaultEncoding() {
		printer = new Report();

		String xml_document = printer.toString();
		assertTrue(xml_document + "Missing encoding", perl.match("/encoding=\"([^\"]*)\"/", xml_document));
		assertEquals("Encoding", Report.DEFAULT_ENCODING, perl.group(1));
		
		try {
			reader.parse(new InputSource(new StringReader(xml_document)));
		} catch (SAXException ex) {
			fail("Could not parse XML Document: " + ex.getMessage() + "\n" + xml_document);
		} catch (IOException ex) {
			fail("Could not read XML Document: " + ex.getMessage() + "\n" + xml_document);
		}
	}

	public void testSpecificEncoding() {
		printer = new Report(SPECIFIC_ENCODING, Report.DEFAULT_DTD_PREFIX);

		String xml_document = printer.toString();
		assertTrue(xml_document + "Missing encoding", perl.match("/encoding=\"([^\"]*)\"/", xml_document));
		assertEquals("Encoding", SPECIFIC_ENCODING, perl.group(1));
		
		try {
			reader.parse(new InputSource(new StringReader(xml_document)));
		} catch (SAXException ex) {
			fail("Could not parse XML Document: " + ex.getMessage() + "\n" + xml_document);
		} catch (IOException ex) {
			fail("Could not read XML Document: " + ex.getMessage() + "\n" + xml_document);
		}
	}

	public void error(SAXParseException ex) {
		// Ignore
	}

	public void fatalError(SAXParseException ex) {
		// Ignore
	}

	public void warning(SAXParseException ex) {
		// Ignore
	}
}