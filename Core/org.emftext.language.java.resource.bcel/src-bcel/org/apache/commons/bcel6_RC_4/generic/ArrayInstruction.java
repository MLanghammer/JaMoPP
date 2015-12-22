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
 * Super class for instructions dealing with array access such as IALOAD.
 *
 * @version $Id: ArrayInstruction.java 1694911 2015-08-09 21:21:10Z chas $
 * @author  <A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>
 */
public abstract class ArrayInstruction extends Instruction implements ExceptionThrower,
        TypedInstruction {

    private static final long serialVersionUID = 1355074014869910296L;


    /**
     * Empty constructor needed for the Class.newInstance() statement in
     * Instruction.readInstruction(). Not to be used otherwise.
     */
    ArrayInstruction() {
    }


    /**
     * @param opcode of instruction
     */
    protected ArrayInstruction(short opcode) {
        super(opcode, (short) 1);
    }


    public Class<?>[] getExceptions() {
        return org.apache.commons.bcel6_RC_4.ExceptionConstants.EXCS_ARRAY_EXCEPTION;
    }


    /** @return type associated with the instruction
     */
    public Type getType( ConstantPoolGen cp ) {
        switch (opcode) {
            case org.apache.commons.bcel6_RC_4.Constants.IALOAD:
            case org.apache.commons.bcel6_RC_4.Constants.IASTORE:
                return Type.INT;
            case org.apache.commons.bcel6_RC_4.Constants.CALOAD:
            case org.apache.commons.bcel6_RC_4.Constants.CASTORE:
                return Type.CHAR;
            case org.apache.commons.bcel6_RC_4.Constants.BALOAD:
            case org.apache.commons.bcel6_RC_4.Constants.BASTORE:
                return Type.BYTE;
            case org.apache.commons.bcel6_RC_4.Constants.SALOAD:
            case org.apache.commons.bcel6_RC_4.Constants.SASTORE:
                return Type.SHORT;
            case org.apache.commons.bcel6_RC_4.Constants.LALOAD:
            case org.apache.commons.bcel6_RC_4.Constants.LASTORE:
                return Type.LONG;
            case org.apache.commons.bcel6_RC_4.Constants.DALOAD:
            case org.apache.commons.bcel6_RC_4.Constants.DASTORE:
                return Type.DOUBLE;
            case org.apache.commons.bcel6_RC_4.Constants.FALOAD:
            case org.apache.commons.bcel6_RC_4.Constants.FASTORE:
                return Type.FLOAT;
            case org.apache.commons.bcel6_RC_4.Constants.AALOAD:
            case org.apache.commons.bcel6_RC_4.Constants.AASTORE:
                return Type.OBJECT;
            default:
                throw new ClassGenException("Oops: unknown case in switch" + opcode);
        }
    }
}
