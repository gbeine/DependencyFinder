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

import junit.framework.*;

public class TestNode extends TestCase {
	Node a;
	Node a_A;
	Node a_A_a;
	Node a_A_b;
	Node a_B;
	Node a_B_a;
	Node a_B_b;

	Node b;
	Node b_B;
	Node b_B_b;

	protected void setUp() throws Exception {
		NodeFactory factory = new NodeFactory();

		a     = factory.createPackage("a");
		a_A   = factory.createClass("a.A");
		a_A_a = factory.createFeature("a.A.a");
		a_A_b = factory.createFeature("a.A.b");
		a_B   = factory.createClass("a.B");
		a_B_a = factory.createFeature("a.B.a");
		a_B_b = factory.createFeature("a.B.b");

		b     = factory.createPackage("b");
		b_B   = factory.createClass("b.B");
		b_B_b = factory.createFeature("b.B.b");
	}

	public void testPackageCanAddDependency() {
		assertFalse(a.canAddDependencyTo(a));
		assertTrue(a.canAddDependencyTo(a_A));
		assertTrue(a.canAddDependencyTo(a_A_a));
		assertTrue(a.canAddDependencyTo(a_A_b));
		assertTrue(a.canAddDependencyTo(a_B));
		assertTrue(a.canAddDependencyTo(a_B_a));
		assertTrue(a.canAddDependencyTo(a_B_b));

		assertTrue(a.canAddDependencyTo(b));
		assertTrue(a.canAddDependencyTo(b_B));
		assertTrue(a.canAddDependencyTo(b_B_b));
	}

	public void testClassCanAddDependency() {
		assertFalse(a_A.canAddDependencyTo(a));
		assertFalse(a_A.canAddDependencyTo(a_A));
		assertTrue(a_A.canAddDependencyTo(a_A_a));
		assertTrue(a_A.canAddDependencyTo(a_A_b));
		assertTrue(a_A.canAddDependencyTo(a_B));
		assertTrue(a_A.canAddDependencyTo(a_B_a));
		assertTrue(a_A.canAddDependencyTo(a_B_b));

		assertTrue(a_A.canAddDependencyTo(b));
		assertTrue(a_A.canAddDependencyTo(b_B));
		assertTrue(a_A.canAddDependencyTo(b_B_b));
	}

	public void testFeatureCanAddDependency() {
		assertFalse(a_A_a.canAddDependencyTo(a));
		assertFalse(a_A_a.canAddDependencyTo(a_A));
		assertFalse(a_A_a.canAddDependencyTo(a_A_a));
		assertTrue(a_A_a.canAddDependencyTo(a_A_b));
		assertTrue(a_A_a.canAddDependencyTo(a_B));
		assertTrue(a_A_a.canAddDependencyTo(a_B_a));
		assertTrue(a_A_a.canAddDependencyTo(a_B_b));

		assertTrue(a_A_a.canAddDependencyTo(b));
		assertTrue(a_A_a.canAddDependencyTo(b_B));
		assertTrue(a_A_a.canAddDependencyTo(b_B_b));
	}
}
