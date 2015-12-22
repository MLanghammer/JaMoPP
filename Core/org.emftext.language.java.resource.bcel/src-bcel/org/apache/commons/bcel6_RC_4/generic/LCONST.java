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
 * LCONST - Push 0 or 1, other values cause an exception
 *
 * <PRE>Stack: ... -&gt; ..., </PRE>
 *
 * @version $Id: LCONST.java 1694911 2015-08-09 21:21:10Z chas $
 * @author  <A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>
 */
public class LCONST extends Instruction implements ConstantPushInstruction {

    private static final long serialVersionUID = 909025807621177822L;
    private long value;


    /**
     * Empty constructor needed for the Class.newInstance() statement in
     * Instruction.readInstruction(). Not to be used otherwise.
     */
    LCONST() {
    }


    public LCONST(long l) {
        super(org.apache.commons.bcel6_RC_4.Constants.LCONST_0, (short) 1);
        if (l == 0) {
            opcode = org.apache.commons.bcel6_RC_4.Constants.LCONST_0;
        } else if (l == 1) {
            opcode = org.apache.commons.bcel6_RC_4.Constants.LCONST_1;
        } else {
            throw new ClassGenException("LCONST can be used only for 0 and 1: " + l);
        }
        value = l;
    }


    public Number getValue() {
        return Long.valueOf(value);
    }


    /** @return Type.LONG
     */
    public Type getType( ConstantPoolGen cp ) {
        return Type.LONG;
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
        v.visitPushInstruction(this);
        v.visitStackProducer(this);
        v.visitTypedInstruction(this);
        v.visitConstantPushInstruction(this);
        v.visitLCONST(this);
    }
}
