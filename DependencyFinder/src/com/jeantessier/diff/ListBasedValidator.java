/*
 *  Copyright (c) 2001-2002, Jean Tessier
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
import java.util.*;

public class ListBasedValidator implements Validator {
	private Collection allowed_elements = new HashSet();

	private String current_class;

	public ListBasedValidator(String filename) throws IOException {
		this(new BufferedReader(new InputStreamReader(new FileInputStream(filename))));
	}

	public ListBasedValidator(BufferedReader in) throws IOException {
		try {
			String line;
			while ((line = in.readLine()) != null) {
				if (line.length() > 0) {
					allowed_elements.add(line.trim());
				}
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	public void CurrentClass(String name) {
		current_class = name;
	}
	
	public boolean IsPackageAllowed(String name) {
		return IsAllowed(name);
	}
    
	public boolean IsClassAllowed(String name) {
		return IsAllowed(name);
	}
    
	public boolean IsFeatureAllowed(String name) {
		// return IsAllowed(current_class + "." + name);
		return IsAllowed(name);
	}
    
	private boolean IsAllowed(String name) {
		boolean result = allowed_elements.size() == 0 || allowed_elements.contains(name);

		// System.err.println("Is " + name + " in " + allowed_elements + "? " + result);
		System.err.println(name + "? " + result);
		
		return result;
	}
}