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

package com.jeantessier.dependency;

import java.util.*;

public class AndCompositeSelectionCriteria extends CompositeSelectionCriteria {
	public AndCompositeSelectionCriteria(Collection subcriteria) {
		super(subcriteria);
	}
	
	public boolean isMatchingPackages() {
		boolean result = true;

		Iterator i = getSubcriteria().iterator();
		while (result && i.hasNext()) {
			result = ((SelectionCriteria) i.next()).isMatchingPackages();
		}
		
		return result;
	}
	
	public boolean isMatchingClasses() {
		boolean result = true;

		Iterator i = getSubcriteria().iterator();
		while (result && i.hasNext()) {
			result = ((SelectionCriteria) i.next()).isMatchingClasses();
		}
		
		return result;
	}
	
	public boolean isMatchingFeatures() {
		boolean result = true;

		Iterator i = getSubcriteria().iterator();
		while (result && i.hasNext()) {
			result = ((SelectionCriteria) i.next()).isMatchingFeatures();
		}
		
		return result;
	}

	public boolean matches(PackageNode node) {
		boolean result = true;

		Iterator i = getSubcriteria().iterator();
		while (result && i.hasNext()) {
			result = ((SelectionCriteria) i.next()).matches(node);
		}
		
		return result;
	}
	
	public boolean matches(ClassNode node) {
		boolean result = true;

		Iterator i = getSubcriteria().iterator();
		while (result && i.hasNext()) {
			result = ((SelectionCriteria) i.next()).matches(node);
		}
		
		return result;
	}
	
	public boolean matches(FeatureNode node) {
		boolean result = true;

		Iterator i = getSubcriteria().iterator();
		while (result && i.hasNext()) {
			result = ((SelectionCriteria) i.next()).matches(node);
		}
		
		return result;
	}

	public boolean matchesPackageName(String name) {
		boolean result = true;

		Iterator i = getSubcriteria().iterator();
		while (result && i.hasNext()) {
			result = ((SelectionCriteria) i.next()).matchesPackageName(name);
		}
		
		return result;
	}
	
	public boolean matchesClassName(String name) {
		boolean result = true;

		Iterator i = getSubcriteria().iterator();
		while (result && i.hasNext()) {
			result = ((SelectionCriteria) i.next()).matchesClassName(name);
		}
		
		return result;
	}
	
	public boolean matchesFeatureName(String name) {
		boolean result = true;

		Iterator i = getSubcriteria().iterator();
		while (result && i.hasNext()) {
			result = ((SelectionCriteria) i.next()).matchesFeatureName(name);
		}
		
		return result;
	}
}
