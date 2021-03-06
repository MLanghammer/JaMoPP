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
 * BASTORE -  Store into byte or boolean array
 * <PRE>Stack: ..., arrayref, index, value -&gt; ...</PRE>
 *
 * @version $Id: BASTORE.java 1694911 2015-08-09 21:21:10Z chas $
 * @author  <A HREF="mailto:m.dahm@gmx.de">M. Dahm</A>
 */
public class BASTORE extends ArrayInstruction implements StackConsumer {

    private static final long serialVersionUID = 1127770065463906050L;


    /** Store byte or boolean into array
     */
    public BASTORE() {
        super(org.apache.commons.bcel6_RC_4.Constants.BASTORE);
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
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitBASTORE(this);
    }
}
