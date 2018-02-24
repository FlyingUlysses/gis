//==============================================================================
// YxxfEntxCommand.java
//
// Special COMMAND entity
//==============================================================================

//==============================================================================
// Ycad - Java CAD library
// Copyright (c) 2003 - Ed Karlo - mailto:ekarlo@ysystems.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
//==============================================================================

//==============================================================================
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfEntxCommand.java,v 1.10 2003/04/14 12:38:42 ekarlo Exp $
// $Log: YxxfEntxCommand.java,v $
// Revision 1.10  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.9  2002/10/12 06:59:02  ekarlo
// Fix comment.
//
// Revision 1.8  2002-09-12 14:03:26-06  ekarlo
// MTEXT development check-in.
//
// Revision 1.7  2001-05-17 17:27:14-06  ekarlo
// Rename.
//
// Revision 1.6  2000-10-17 01:43:56-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.5  1999-11-24 23:05:38-07  ekarlo
// Fix insert calc timing problem.
//
// Revision 1.4  1999-10-22 01:27:51-06  ekarlo
// API rework - phase 1.
//
// Revision 1.3  1999-09-29 17:03:48-06  walter
// Added JavaDoc comments.
//
// Revision 1.2  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.1  1999/07/06  02:53:51  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * Special COMMENT entity.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfEntxCommand extends YxxfEntHeader implements YxxfEnt
{
    //==========================================================================
    /**
     * Command type.
     */
    private
    int                         type            = 0;

    /**
     * Object 1.
     */
    private
    Object                      obj1            = null;

    /**
     * Object 2.
     */
    private
    Object                      obj2            = null;

    /**
     * int 1.
     */
    private
    int                         int1            = 0;

    /**
     * int 2.
     */
    private
    int                         int2            = 0;
    //==========================================================================


    //==========================================================================
    // Command result
    
    /**
     * Command result code.
     */
    public
    int                         rc              = 0;

    /**
     * Command result Object.
     */
    public
    Object                      rc_obj          = null;

    /**
     * Command done flag.
     */
    private
    boolean                     command_done    = false;
    //==========================================================================


    //==========================================================================
    /**
     * Command type Unknown.
     */
    public static final int     UNKNOWN                 =   0;


    /**
     * Command type TODO.
     */
    public static final int     COMMAND_PUSH_GC_DOING_FLAG_CALC_EXTENTS
                                                        =   101;
    /**
     * Command type TODO.
     */
    public static final int     COMMAND_POP_GC_DOING_FLAG_CALC_EXTENTS
                                                        =   102;


    /**
     * Command type TODO.
     */
    public static final int     COMMAND_DRAWEXTMINMAX
                                                        =   201;
    /**
     * Command type TODO.
     */
    public static final int     COMMAND_DRAWLIMMINMAX
                                                        =   202;


    /**
     * Command type TODO.
     */
    public static final int     COMMAND_SLEEP            
                                                        =   301;


    /**
     * Command type TODO.
     */
    public static final int     COMMAND_VHANDLER         
                                                        =   401;
    /**
     * Command type for MTEXT format codes.
     */
    public static final int     COMMAND_MTEXT_TEXT                  =   501; /* single word */
    public static final int     COMMAND_MTEXT_SPACE                 =   501; /* white space */
    public static final int     COMMAND_MTEXT_CODE                  =   501; /* format code */

    public static final int     COMMAND_MTEXT_OVERLINE_BEG          =   501; /* \O */
    public static final int     COMMAND_MTEXT_OVERLINE_END          =   501; /* \o */

    public static final int     COMMAND_MTEXT_UNDERLINE_BEG         =   501; /* \I */
    public static final int     COMMAND_MTEXT_UNDERLINE_END         =   501; /* \i */

    public static final int     COMMAND_MTEXT_NONBREAKING_SPACE     =   501; /* \~ */


    //==========================================================================
    /**
     * Transmat for command - computed during get or load.
     */
    public
    YxxfGfxMatrix               M_command       = null;
    //==========================================================================


    //==========================================================================
    /**
     * Constructor (empty)
     */
    public
    YxxfEntxCommand()
    {
    }


    /**
     * Constructor
     * @param type The command type.
     */
    public
    YxxfEntxCommand(int type)
    {
        this.type = type;
    }


    /**
     * Constructor
     * @param type The command type.
     * @param obj1 The argument TODO.
     */
    public
    YxxfEntxCommand(int type, Object obj1)
    {
        this.type = type;
        this.obj1 = obj1;
    }


    /**
     * Consturctor TODO
     * @param type The command type.
     * @param obj1 The first argument.
     * @param obj2 The second argument.
     * @param int1 The third argument.
     * @param int2 The fourth argument.
     */
    public
    YxxfEntxCommand(int type, Object obj1, Object obj2, int int1, int int2)
    {
        this.type = type;
        this.obj1 = obj1;
        this.obj2 = obj2;
        this.int1 = int1;
        this.int2 = int2;
    }


    /**
     * Set the command type.
     * @param type The command type.
     * @return The command type.
     */
    public
    int setType(int type)
    {
        this.type = type;
        return type;
    }

    /**
     * Get the command type.
     * @return The command type.
     */
    public
    int getType()
    {
        return type;
    }


    /**
     * Set Object1.
     * @param obj1 The first argument.
     * @return The first argument.
     */
    public
    Object setObj1(Object obj1)
    {
        this.obj1 = obj1;
        return this.obj1;
    }

    /**
     * Get obj1 TODO.
     * @return The first argument.
     */
    public
    Object getObj1()
    {
        return obj1;
    }


    /**
     * Set Object2 TODO.
     * @param obj2 The second argument.
     * @return The second argument.
     */
    public
    Object setObj2(Object obj2)
    {
        this.obj2 = obj2;
        return this.obj2;
    }

    /**
     * Get obj2.
     * @return The second argument.
     */
    public
    Object getObj2()
    {
        return obj2;
    }


    /**
     * Set int1 TODO.
     * @param int1 The third argument.
     * @return The third argument.
     */
    public
    int setInt1(int int1)
    {
        this.int1 = int1;
        return int1;
    }

    /**
     * Get int1 TODO.
     * @return The third argument.
     */
    public
    int getInt1()
    {
        return int1;
    }


    /**
     * Set int2 TODO.
     * @param int2 The fourth argument.
     * @return The fourth argument.
     */
    public
    int setInt2(int int2)
    {
        this.int2 = int2;
        return int2;
    }

    /**
     * Get int2.
     * @return The fourth argument.
     */
    public
    int getInt2()
    {
        return int2;
    }


    /**
     * Set the argument values.
     * @param obj1 The first argument.
     * @param obj2 The second argument.
     * @param int1 The third argument.
     * @param int2 The fourth argument.
     * @return The Command entity.
     */
    public
    YxxfEntxCommand setArgs(Object obj1, Object obj2, int int1, int int2)
    {
        this.obj1 = obj1;
        this.obj2 = obj2;
        this.int1 = int1;
        this.int2 = int2;
        return this;
    }
    //==========================================================================


    //==========================================================================
    // Event wait/set/get

    /**
     * Wait until Command done.
     */
    public synchronized
    void waitCommandDone()
    {
        if (command_done)
            return;

        while (true)
        {
            try { wait(); }
            catch(InterruptedException e) { System.out.println(e); }

            if (command_done)
                return;
        }
    }


    /**
     * Set the falue of the command_done flag.
     * @param command_done The command_done flag.
     * @return The value of the command_done flag.
     */
    public synchronized
    boolean setCommandDone(boolean command_done)
    {
        this.command_done = command_done;
        notifyAll();
        return this.command_done;
    }


    /**
     * Get the value of the command_done flag.
     * @return The value of the command_done flag.
     */
    public // synchronized
    boolean getCommandDone()
    {
        return command_done;
    }
    //==========================================================================


    //==========================================================================
    /**
     * Draw (execute) command.
     * @param gc The graphics context.
     */
    public
    void draw(YxxfGfxContext gc)
    {
        if (type == COMMAND_PUSH_GC_DOING_FLAG_CALC_EXTENTS)
        {
            gc.pushCalcExtentsStack(((Boolean)obj1).booleanValue());
            return;
        }

        if (type == COMMAND_POP_GC_DOING_FLAG_CALC_EXTENTS)
        {
            gc.popCalcExtentsStack();
            return;
        }

        if (type == COMMAND_DRAWEXTMINMAX)
        {
            if (gc.setupEntity(this, null) == false)
                return;

            gc.pushCalcExtentsStack(false);
            gc.pushModelStack(M_command, YxxfGfxPointW.W0, YxxfGfxPointW.W0);
            gc.setModel(M_command);
            gc.setEntityMatrix(M_command);

            gc.drawBox_ECS(gc.getDrawing().secHeader.extmin,
                           gc.getDrawing().secHeader.extmax);

            gc.popModelStack();
            gc.popCalcExtentsStack();

            return;
        }

        if (type == COMMAND_DRAWLIMMINMAX)
        {
            if (gc.setupEntity(this, null) == false)
                return;

            gc.pushCalcExtentsStack(false);
            gc.pushModelStack(M_command, YxxfGfxPointW.W0, YxxfGfxPointW.W0);
            gc.setModel(M_command);
            gc.setEntityMatrix(M_command);

            gc.drawBox_ECS(gc.getDrawing().secHeader.limmin,
                           gc.getDrawing().secHeader.limmax);

            gc.popModelStack();
            gc.popCalcExtentsStack();

            return;
        }

        if (type == COMMAND_SLEEP)
        {
            if (gc.setupEntity(this, null) == false)
                return;

            try
            { 
                Thread.sleep(int1);
            }
            catch(InterruptedException e)
            {
            }

            return;
        }

        if (type == COMMAND_VHANDLER)
        {
            if (gc.setupEntity(this, null) == false)
                return;

            return;
        }
    }


    /**
     * Calculate command matrix.
     * @param D The drawing.
     */
    public
    void calc(Yxxf D)
    {
        //
        // connect
        //
        hdr_calc(D);


        //
        // Setup command transform
        //

        // Initialize command matrix
        if (M_command == null)
            M_command = new YxxfGfxMatrix();
        else
            M_command.mtxSetIdentity();
    }
    //==========================================================================
}

