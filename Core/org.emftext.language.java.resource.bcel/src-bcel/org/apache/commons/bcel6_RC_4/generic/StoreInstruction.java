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
 * Denotes an unparameterized instruction to store a value into a local variable,
 * e.g. ISTORE.
 *
 * @version $Id: StoreInstruction.java 1694911 2015-08-09 21:21:10Z chas $
 * @author  <A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>
 */
public abstract class StoreInstruction extends LocalVariableInstruction implements PopInstruction {

    private static final long serialVersionUID = -774241740383612113L;


    /**
     * Empty constructor needed for the Class.newInstance() statement in
     * Instruction.readInstruction(). Not to be used otherwise.
     * tag and length are defined in readInstruction and initFromFile, respectively.
     */
    StoreInstruction(short canon_tag, short c_tag) {
        super(canon_tag, c_tag);
    }


    /**
     * @param opcode Instruction opcode
     * @param c_tag Instruction number for compact version, ASTORE_0, e.g.
     * @param n local variable index (unsigned short)
     */
    protected StoreInstruction(short opcode, short c_tag, int n) {
        super(opcode, c_tag, n);
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
        v.visitStackConsumer(this);
        v.visitPopInstruction(this);
        v.visitTypedInstruction(this);
        v.visitLocalVariableInstruction(this);
        v.visitStoreInstruction(this);
    }
}
