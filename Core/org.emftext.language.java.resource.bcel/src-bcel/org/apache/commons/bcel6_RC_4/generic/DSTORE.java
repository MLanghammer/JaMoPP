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
package org.apache.commons.bcel6_RC_4.generic;

/** 
 * DSTORE - Store double into local variable
 * <pre>Stack: ..., value.word1, value.word2 -&gt; ... </PRE>
 *
 * @version $Id: DSTORE.java 1694911 2015-08-09 21:21:10Z chas $
 * @author  <A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>
 */
public class DSTORE extends StoreInstruction {

    private static final long serialVersionUID = 2593414593903082469L;


    /**
     * Empty constructor needed for the Class.newInstance() statement in
     * Instruction.readInstruction(). Not to be used otherwise.
     */
    DSTORE() {
        super(org.apache.commons.bcel6_RC_4.Constants.DSTORE, org.apache.commons.bcel6_RC_4.Constants.DSTORE_0);
    }


    /** Store double into local variable
     * @param n index of local variable
     */
    public DSTORE(int n) {
        super(org.apache.commons.bcel6_RC_4.Constants.DSTORE, org.apache.commons.bcel6_RC_4.Constants.DSTORE_0, n);
    }


    /**
     * Call corresponding visitor method(s). The order is:
     * Call visitor methods of implemented interfaces first, then
     * call methods according to the class hierarchy in descending order,
     * i.e., the most specific visitXXX() call comes last.
     *
     * @param v Visitor object
     */
    @Override
    public void accept( Visitor v ) {
        super.accept(v);
        v.visitDSTORE(this);
    }
}