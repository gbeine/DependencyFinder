/*
 *  Copyright (c) 2001-2005, Jean Tessier
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

package com.jeantessier.classreader;

import java.io.*;
import java.util.*;

public class Field_info extends Feature_info {
	public static final int ACC_VOLATILE  = 0x0040;
	public static final int ACC_TRANSIENT = 0x0080;

	public Field_info(Classfile classfile, DataInputStream in) throws IOException {
		super(classfile, in);
	}

	public String getFeatureType() {
		return "field";
	}

	public boolean isVolatile() {
		return (getAccessFlag() & ACC_VOLATILE) != 0;
	}

	public boolean isTransient() {
		return (getAccessFlag() & ACC_TRANSIENT) != 0;
	}

	public String getType() {
		return SignatureHelper.getType(getDescriptor());
	}

	public String getDeclaration() {
		StringBuffer result = new StringBuffer();

		if (isPublic()) result.append("public ");
		if (isProtected()) result.append("protected ");
		if (isPrivate()) result.append("private ");
		if (isStatic()) result.append("static ");
		if (isFinal()) result.append("final ");
		if (isVolatile()) result.append("volatile ");
		if (isTransient()) result.append("transient ");
	
		result.append(getType()).append(" ");
		result.append(getName());

		return result.toString();
	}

	public String getSignature() {
		return getName();
	}

	public void accept(Visitor visitor) {
		visitor.visitField_info(this);
	}
}
