//==============================================================================
// YxxfDrwViewHandlerEvent.java
//
// View Handler events
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfDrwViewHandlerEvent.java,v 1.21 2003/05/26 02:58:10 ekarlo Exp $
// $Log: YxxfDrwViewHandlerEvent.java,v $
// Revision 1.21  2003/05/26 02:58:10  ekarlo
// Add methods to turn rendering on and off.
// Improve calculation of extents.
//
// Revision 1.20  2003/05/16 05:12:02  ekarlo
// Add options to control border view colors.
// Add methods to set current view.
//
// Revision 1.19  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.18  2002/09/29 05:20:54  ekarlo
// Change prefix of entity print options to "list".
//
// Revision 1.17  2002-09-26 01:32:32-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.16  2001-05-21 04:35:45-06  ekarlo
// Imprinter development - draw all view borders.
//
// Revision 1.15  2001-05-20 02:42:59-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.14  2000-10-17 01:44:01-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.13  1999-10-22 01:27:57-06  ekarlo
// API rework - phase 1.
//
// Revision 1.12  1999-10-06 20:08:52-06  walter
// Added JavaDoc comments.
//
// Revision 1.11  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.10  1999/07/06  02:46:06  ekarlo
// Add calc and zoom extents.
// Fix method name.
//
// Revision 1.9  1999/06/29  19:44:34  ekarlo
// Move dbprint command to general toolbar line.
//
// Revision 1.8  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.7  1999/06/15  04:58:33  ekarlo
// User Interface - phase 1.
//
// Revision 1.6  1998/12/21  15:43:26  ekarlo
// Text - phase 3.
//
// Revision 1.5  1998/11/24  19:51:08  ekarlo
// Text - phase 2.
//
// Revision 1.4  1998/08/25  18:15:06  ekarlo
// Add status display.
//
// Revision 1.3  1998/08/24  20:41:06  ekarlo
// Add status display.
//
// Revision 1.2  1998/02/02  19:00:40  ekarlo
// Implement papaer space - phase 2.
// Rename view handler.
//
// Revision 1.1  1997/08/30  14:12:28  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * View Handler events.
 */
public class YxxfDrwViewHandlerEvent
{
    //==========================================================================
    /**
     * Event type
     */
    private
    int                         type            = 0;

    /**
     * Object 1
     */
    private
    Object                      obj1            = null;

    /**
     * Object 2
     */
    private
    Object                      obj2            = null;

    /**
     * int 1
     */
    private
    int                         int1            = 0;

    /**
     * int 2
     */
    private
    int                         int2            = 0;

    /**
     * int 3
     */
    private
    int                         int3            = 0;

    /**
     * int 4
     */
    private
    int                         int4            = 0;

    /**
     * double 1
     */
    private
    double                      dbl1            = 0.0;

    /**
     * double 2
     */
    private
    double                      dbl2            = 0.0;

    /**
     * double 3
     */
    private
    double                      dbl3            = 0.0;

    /**
     * double 4
     */
    private
    double                      dbl4            = 0.0;
    //==========================================================================


    //==========================================================================
    // Event result
    
    /**
     * Event result code
     */
    public
    int                         rc              = 0;

    /**
     * Event result Object
     */
    public
    Object                      rc_obj          = null;

    /**
     * Event done flag.
     */
    private
    boolean                     event_done      = false;
    //==========================================================================


    //==========================================================================
    /**
     * Event type UNKNOWN
     */
    public static final int     UNKNOWN                 =    0;


    /**
     * Event type COMMAND_INIT
     */
    public static final int     COMMAND_INIT            =   11;
    /**
     * Event type COMMAND_START
     */
    public static final int     COMMAND_START           =   12;
    /**
     * Event type COMMAND_INIT_DRAWING
     */
    public static final int     COMMAND_INIT_DRAWING    =   13;
    /**
     * Event type COMMAND_START_DRAWING
     */
    public static final int     COMMAND_START_DRAWING   =   14;
    /**
     * Event type COMMAND_LIST_STATS
     */
    public static final int     COMMAND_LIST_STATS      =   15;
    /**
     * Event type COMMAND_SET_RENDERING
     */
    public static final int     COMMAND_SET_RENDERING   =   16;


    /**
     * Event type COMMAND_SET_CURR_VIEW_NUM
     */
    public static final int     COMMAND_SET_CURR_VIEW_NUM   =   21;
    /**
     * Event type COMMAND_SET_CURR_VIEW
     */
    public static final int     COMMAND_SET_CURR_VIEW       =   22;
    /**
     * Event type COMMAND_HILITE_CURR_VIEW
     */
    public static final int     COMMAND_HILITE_CURR_VIEW    =   23;
    /**
     * Event type COMMAND_HILITE_ALL_VIEWS
     */
    public static final int     COMMAND_HILITE_ALL_VIEWS    =   24;
    /**
     * Event type COMMAND_MOUSEDOWN
     */
    public static final int     COMMAND_MOUSEDOWN       =   25;
    /**
     * Event type COMMAND_MOUSEDRAG
     */
    public static final int     COMMAND_MOUSEDRAG       =   26;
    /**
     * Event type COMMAND_MOUSEUP
     */
    public static final int     COMMAND_MOUSEUP         =   27;


    /**
     * Event type TOOLBAR_START
     */
    public static final int     TOOLBAR_START           =  101;
    /**
     * Event type TOOLBAR_REDRAW
     */
    public static final int     TOOLBAR_REDRAW          =  102;
    /**
     * Event type TOOLBAR_RESTORE
     */
    public static final int     TOOLBAR_RESTORE         =  103;
    /**
     * Event type TOOLBAR_CALC_EXTENTS
     */
    public static final int     TOOLBAR_CALC_EXTENTS    =  104;
    /**
     * Event type TOOLBAR_LISTSTATS
     */
    public static final int     TOOLBAR_LISTSTATS       =  105;
    /**
     * Event type TOOLBAR_PRINT
     */
    public static final int     TOOLBAR_PRINT           =  106;

    /**
     * Event type TOOLBAR_ZOOM_IN
     */
    public static final int     TOOLBAR_ZOOM_IN         =  201;
    /**
     * Event type TOOLBAR_ZOOM_OUT
     */
    public static final int     TOOLBAR_ZOOM_OUT        =  202;
    /**
     * Event type TOOLBAR_ZOOM_TO_HEAD_EXTENTS
     */
    public static final int     TOOLBAR_ZOOM_TO_HEAD_EXTENTS    =  203;
    /**
     * Event type TOOLBAR_ZOOM_TO_CALC_EXTENTS
     */
    public static final int     TOOLBAR_ZOOM_TO_CALC_EXTENTS    =  204;

    /**
     * Event type TOOLBAR_PAN_L
     */
    public static final int     TOOLBAR_PAN_L           =  301;
    /**
     * Event type TOOLBAR_PAN_R
     */
    public static final int     TOOLBAR_PAN_R           =  302;
    /**
     * Event type TOOLBAR_PAN_U
     */
    public static final int     TOOLBAR_PAN_U           =  303;
    /**
     * Event type TOOLBAR_PAN_D
     */
    public static final int     TOOLBAR_PAN_D           =  304;


    /**
     * Event type TOOLBAR_ROTATE_X_P
     */
    public static final int     TOOLBAR_ROTATE_X_P      =  401;
    /**
     * Event type TOOLBAR_ROTATE_X_M
     */
    public static final int     TOOLBAR_ROTATE_X_M      =  402;
    /**
     * Event type TOOLBAR_ROTATE_Y_P
     */
    public static final int     TOOLBAR_ROTATE_Y_P      =  403;
    /**
     * Event type TOOLBAR_ROTATE_Y_M
     */
    public static final int     TOOLBAR_ROTATE_Y_M      =  404;
    /**
     * Event type TOOLBAR_ROTATE_Z_P
     */
    public static final int     TOOLBAR_ROTATE_Z_P      =  405;
    /**
     * Event type TOOLBAR_ROTATE_Z_M
     */
    public static final int     TOOLBAR_ROTATE_Z_M      =  406;


    /**
     * Event type TOOLBAR_ROTATE_X_VALUE
     */
    public static final int     TOOLBAR_ROTATE_X_VALUE  =  501;
    /**
     * Event type TOOLBAR_ROTATE_X_VALUE
     */
    public static final int     TOOLBAR_ROTATE_Y_VALUE  =  502;
    /**
     * Event type TOOLBAR_ROTATE_X_VALUE
     */
    public static final int     TOOLBAR_ROTATE_Z_VALUE  =  503;


    /**
     * Event type GET_SECHEADER_BEG
     */
    public static final int     GET_SECHEADER_BEG       = 1110;
    /**
     * Event type GET_SECHEADER_END
     */
    public static final int     GET_SECHEADER_END       = 1115;

    /**
     * Event type GET_SECTABLES_BEG
     */
    public static final int     GET_SECTABLES_BEG       = 1210;
    /**
     * Event type GET_SECTABLES_END
     */
    public static final int     GET_SECTABLES_END       = 1215;
    
    /**
     * Event type GET_TBLNAME_BEG
     */
    public static final int     GET_TBLNAME_BEG         = 1220;
    /**
     * Event type GET_TBLNAME_END
     */
    public static final int     GET_TBLNAME_END         = 1225;
    
    /**
     * Event type GET_SECBLOCKS_BEG
     */
    public static final int     GET_SECBLOCKS_BEG       = 1310;
    /**
     * Event type GET_SECBLOCKS_END
     */
    public static final int     GET_SECBLOCKS_END       = 1315;

    /**
     * Event type GET_BLKNAME_BEG
     */
    public static final int     GET_BLKNAME_BEG         = 1320;
    /**
     * Event type GET_BLKNAME_END
     */
    public static final int     GET_BLKNAME_END         = 1325;
    
    /**
     * Event type GET_SECENTITIES_BEG
     */
    public static final int     GET_SECENTITIES_BEG     = 1410;
    /**
     * Event type GET_SECENTITIES_END
     */
    public static final int     GET_SECENTITIES_END     = 1415;

    /**
     * Event type GET_DRAWING_EOF
     */
    public static final int     GET_DRAWING_EOF         = 1910;
    //==========================================================================


    //==========================================================================
    /**
     * Constructor (empty)
     */
    public
    YxxfDrwViewHandlerEvent()
    {
    }


    /**
     * Constructor
     * @param type Event type.
     */
    public
    YxxfDrwViewHandlerEvent(int type)
    {
        this.type = type;
    }


    /**
     * Constructor
     * @param type Event type.
     * @param obj1 First Event argument.
     */
    public
    YxxfDrwViewHandlerEvent(int type, Object obj1)
    {
        this.type = type;
        this.obj1 = obj1;
    }


    /**
     * Constructor
     * @param type Event type.
     * @param obj1 First Event argument.
     * @param obj2 Second Event argument.
     * @param int1 Third Event argument.
     * @param int2 Fourth Event argument.
     */
    public
    YxxfDrwViewHandlerEvent(int type, Object obj1, Object obj2, int int1, int int2, double dbl1, double dbl2)
    {
        this.type = type;
        this.obj1 = obj1;
        this.obj2 = obj2;
        this.int1 = int1;
        this.int2 = int2;
        this.dbl1 = dbl1;
        this.dbl2 = dbl2;
    }


    /**
     * Set the value of the event type.
     * @param type The value of the event type.
     * @return A the event type.
     */
    public
    int setType(int type)
    {
        this.type = type;
        return type;
    }

    /**
     * Get the event type.
     */
    public
    int getType()
    {
        return type;
    }


    /**
     * Set the first Event Handler argument.
     * @param obj1 The first argument.
     * @return A reference to the first argument.
     */
    public
    Object setObj1(Object obj1)
    {
        this.obj1 = obj1;
        return this.obj1;
    }

    /**
     * Get the first Event Handler argument.
     * @return A reference to the first argument.
     */
    public
    Object getObj1()
    {
        return obj1;
    }


    /**
     * Set the second Event Handler argument.
     * @param obj2 The second argment.
     * @return A reference to the second argument. 
     */
    public
    Object setObj2(Object obj2)
    {
        this.obj2 = obj2;
        return this.obj2;
    }

    /**
     * Get the second Event Handler argument.
     * @return A reference to the second argument.
     */
    public
    Object getObj2()
    {
        return obj2;
    }


    /**
     * Set the third Event Handler argument.
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
     * Get the third Event Handler argument.
     * @return The third argument.
     */
    public
    int getInt1()
    {
        return int1;
    }


    /**
     * Set the fourth Event Handler argument.
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
     * Get the fourth Event Handler argument.
     * @return The fourth argument.
     */
    public
    int getInt2()
    {
        return int2;
    }


    /**
     * Set the third Event Handler argument.
     * @param int3 The third argument.
     * @return The third argument.
     */
    public
    int setInt3(int int3)
    {
        this.int3 = int3;
        return int3;
    }

    /**
     * Get the third Event Handler argument.
     * @return The third argument.
     */
    public
    int getInt3()
    {
        return int3;
    }


    /**
     * Set the third Event Handler argument.
     * @param int4 The third argument.
     * @return The third argument.
     */
    public
    int setInt4(int int4)
    {
        this.int4 = int4;
        return int4;
    }

    /**
     * Get the third Event Handler argument.
     * @return The third argument.
     */
    public
    int getInt4()
    {
        return int4;
    }


    /**
     * Set the third Event Handler argument.
     * @param int1 The third argument.
     * @return The third argument.
     */
    public
    double setDbl1(double dbl1)
    {
        this.dbl1 = dbl1;
        return dbl1;
    }

    /**
     * Get the third Event Handler argument.
     * @return The third argument.
     */
    public
    double getDbl1()
    {
        return dbl1;
    }


    /**
     * Set the fourth Event Handler argument.
     * @param int2 The fourth argument.
     * @return The fourth argument.
     */
    public
    double setDbl2(double dbl2)
    {
        this.dbl2 = dbl2;
        return dbl2;
    }

    /**
     * Get the fourth Event Handler argument.
     * @return The fourth argument.
     */
    public
    double getDbl2()
    {
        return dbl2;
    }


    /**
     * Set the value of all the arguments to the event handler.
     * @param obj1 The first argument to the Event Handler.
     * @param obj2 The second argument to the Event Handler.
     * @param int1 The third argument to the Event Handler.
     * @param int2 The fourth argument to the Event Handler.
     * @return A reference to the Event Handler.
     */
    public
    YxxfDrwViewHandlerEvent setArgs(Object obj1, Object obj2, int int1, int int2)
    {
        this.obj1 = obj1;
        this.obj2 = obj2;
        this.int1 = int1;
        this.int2 = int2;
        return this;
    }


    /**
     * Set the value of all the arguments to the event handler.
     * @param obj1 The first argument to the Event Handler.
     * @param obj2 The second argument to the Event Handler.
     * @param int1 The third argument to the Event Handler.
     * @param int2 The fourth argument to the Event Handler.
     * @return A reference to the Event Handler.
     */
    public
    YxxfDrwViewHandlerEvent setArgs(Object obj1, Object obj2, int int1, int int2, double dbl1, double dbl2)
    {
        this.obj1 = obj1;
        this.obj2 = obj2;
        this.int1 = int1;
        this.int2 = int2;
        this.dbl1 = dbl1;
        this.dbl2 = dbl2;
        return this;
    }
    //==========================================================================


    //==========================================================================
    // Event wait/set/get

    /**
     * Wait until the event handling is completed.
     */
    public synchronized
    void waitEventDone()
    {
        if (event_done)
            return;

        while (true)
        {
            try { wait(); }
            catch(InterruptedException e) { System.out.println(e); }

            if (event_done)
                return;
        }
    }


    /**
     * Set the value of the event_done flag.
     * @param event_done true if the event is done.
     * @return The value of the event_done flag.
     */
    public synchronized
    boolean setEventDone(boolean event_done)
    {
        this.event_done = event_done;
        notifyAll();
        return this.event_done;
    }


    /**
     * Get the value of the event_done flag.
     * @return The value of the event_done flag.
     */
    public // synchronized
    boolean getEventDone()
    {
        return event_done;
    }
    //==========================================================================


}

