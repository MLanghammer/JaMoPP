/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.commons.bcel6_RC_4.verifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.bcel6_RC_4.classfile.JavaClass;
import org.apache.commons.bcel6_RC_4.verifier.statics.Pass1Verifier;
import org.apache.commons.bcel6_RC_4.verifier.statics.Pass2Verifier;
import org.apache.commons.bcel6_RC_4.verifier.statics.Pass3aVerifier;
import org.apache.commons.bcel6_RC_4.verifier.structurals.Pass3bVerifier;

/**
 * A Verifier instance is there to verify a class file according to The Java Virtual
 * Machine Specification, 2nd Edition.
 *
 * Pass-3b-verification includes pass-3a-verification;
 * pass-3a-verification includes pass-2-verification;
 * pass-2-verification includes pass-1-verification.
 *
 * A Verifier creates PassVerifier instances to perform the actual verification.
 * Verifier instances are usually generated by the VerifierFactory.
 *
 * @version $Id: Verifier.java 1694911 2015-08-09 21:21:10Z chas $
 * @author Enver Haase
 * @see org.apache.commons.bcel6_RC_4.verifier.VerifierFactory
 * @see org.apache.commons.bcel6_RC_4.verifier.PassVerifier
 */
public class Verifier {

    /**
     * The name of the class this verifier operates on.
     */
    private final String classname;
    /** A Pass1Verifier for this Verifier instance. */
    private Pass1Verifier p1v;
    /** A Pass2Verifier for this Verifier instance. */
    private Pass2Verifier p2v;
    /** The Pass3aVerifiers for this Verifier instance. Key: Interned string specifying the method number. */
    private final Map<String, Pass3aVerifier> p3avs = new HashMap<String, Pass3aVerifier>();
    /** The Pass3bVerifiers for this Verifier instance. Key: Interned string specifying the method number. */
    private final Map<String, Pass3bVerifier> p3bvs = new HashMap<String, Pass3bVerifier>();


    /** Returns the VerificationResult for the given pass. */
    public VerificationResult doPass1() {
        if (p1v == null) {
            p1v = new Pass1Verifier(this);
        }
        return p1v.verify();
    }


    /** Returns the VerificationResult for the given pass. */
    public VerificationResult doPass2() {
        if (p2v == null) {
            p2v = new Pass2Verifier(this);
        }
        return p2v.verify();
    }


    /** Returns the VerificationResult for the given pass. */
    public VerificationResult doPass3a( int method_no ) {
        String key = Integer.toString(method_no);
        Pass3aVerifier p3av;
        p3av = p3avs.get(key);
        if (p3avs.get(key) == null) {
            p3av = new Pass3aVerifier(this, method_no);
            p3avs.put(key, p3av);
        }
        return p3av.verify();
    }


    /** Returns the VerificationResult for the given pass. */
    public VerificationResult doPass3b( int method_no ) {
        String key = Integer.toString(method_no);
        Pass3bVerifier p3bv;
        p3bv = p3bvs.get(key);
        if (p3bvs.get(key) == null) {
            p3bv = new Pass3bVerifier(this, method_no);
            p3bvs.put(key, p3bv);
        }
        return p3bv.verify();
    }


    /**
     * Instantiation is done by the VerifierFactory.
     *
     * @see VerifierFactory
     */
    Verifier(String fully_qualified_classname) {
        classname = fully_qualified_classname;
        flush();
    }


    /**
     * Returns the name of the class this verifier operates on.
     * This is particularly interesting when this verifier was created
     * recursively by another Verifier and you got a reference to this
     * Verifier by the getVerifiers() method of the VerifierFactory.
     * @see VerifierFactory
     */
    public final String getClassName() {
        return classname;
    }


    /**
     * Forget everything known about the class file; that means, really
     * start a new verification of a possibly different class file from
     * BCEL's repository.
     *
     */
    public void flush() {
        p1v = null;
        p2v = null;
        p3avs.clear();
        p3bvs.clear();
    }


    /**
     * This returns all the (warning) messages collected during verification.
     * A prefix shows from which verifying pass a message originates.
     */
    public String[] getMessages() throws ClassNotFoundException {
        List<String> messages = new ArrayList<String>();
        if (p1v != null) {
            String[] p1m = p1v.getMessages();
            for (String element : p1m) {
                messages.add("Pass 1: " + element);
            }
        }
        if (p2v != null) {
            String[] p2m = p2v.getMessages();
            for (String element : p2m) {
                messages.add("Pass 2: " + element);
            }
        }
        for (Pass3aVerifier pv : p3avs.values()) {
            String[] p3am = pv.getMessages();
            int meth = pv.getMethodNo();
            for (String element : p3am) {
                messages.add("Pass 3a, method " + meth + " ('"
                        + org.apache.commons.bcel6_RC_4.Repository.lookupClass(classname).getMethods()[meth]
                        + "'): " + element);
            }
        }
        for (Pass3bVerifier pv : p3bvs.values()) {
            String[] p3bm = pv.getMessages();
            int meth = pv.getMethodNo();
            for (String element : p3bm) {
                messages.add("Pass 3b, method " + meth + " ('"
                        + org.apache.commons.bcel6_RC_4.Repository.lookupClass(classname).getMethods()[meth]
                        + "'): " + element);
            }
        }
        String[] ret = new String[messages.size()];
        for (int i = 0; i < messages.size(); i++) {
            ret[i] = messages.get(i);
        }
        return ret;
    }


    /**
     * Verifies class files.
     * This is a simple demonstration of how the API of BCEL's
     * class file verifier "JustIce" may be used.
     * You should supply command-line arguments which are
     * fully qualified namea of the classes to verify. These class files
     * must be somewhere in your CLASSPATH (refer to Sun's
     * documentation for questions about this) or you must have put the classes
     * into the BCEL Repository yourself (via 'addClass(JavaClass)').
     */
    public static void main( String[] args ) {
        System.out
                .println("JustIce by Enver Haase, (C) 2001-2002.\n<http://bcel.sourceforge.net>\n<http://commons.apache.org/bcel>\n");
        for (int k = 0; k < args.length; k++) {
            try {
                if (args[k].endsWith(".class")) {
                    int dotclasspos = args[k].lastIndexOf(".class");
                    if (dotclasspos != -1) {
                        args[k] = args[k].substring(0, dotclasspos);
                    }
                }
                args[k] = args[k].replace('/', '.');
                System.out.println("Now verifying: " + args[k] + "\n");
                Verifier v = VerifierFactory.getVerifier(args[k]);
                VerificationResult vr;
                vr = v.doPass1();
                System.out.println("Pass 1:\n" + vr);
                vr = v.doPass2();
                System.out.println("Pass 2:\n" + vr);
                if (vr == VerificationResult.VR_OK) {
                    JavaClass jc = org.apache.commons.bcel6_RC_4.Repository.lookupClass(args[k]);
                    for (int i = 0; i < jc.getMethods().length; i++) {
                        vr = v.doPass3a(i);
                        System.out.println("Pass 3a, method number " + i + " ['"
                                + jc.getMethods()[i] + "']:\n" + vr);
                        vr = v.doPass3b(i);
                        System.out.println("Pass 3b, method number " + i + " ['"
                                + jc.getMethods()[i] + "']:\n" + vr);
                    }
                }
                System.out.println("Warnings:");
                String[] warnings = v.getMessages();
                if (warnings.length == 0) {
                    System.out.println("<none>");
                }
                for (String warning : warnings) {
                    System.out.println(warning);
                }
                System.out.println("\n");
                // avoid swapping.
                v.flush();
                org.apache.commons.bcel6_RC_4.Repository.clearCache();
                System.gc();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
