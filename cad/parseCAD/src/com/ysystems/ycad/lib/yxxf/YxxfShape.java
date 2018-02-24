//==============================================================================
// YxxfShape.java
//
// Shape object
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfShape.java,v 1.9 2003/04/14 12:38:42 ekarlo Exp $
// $Log: YxxfShape.java,v $
// Revision 1.9  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.8  2000/10/17 07:43:40  ekarlo
// Change package paths to lower case.
//
// Revision 1.7  1999-10-25 11:03:52-06  ekarlo
// Implement shx shapes 1.0 (like bold.shx).
//
// Revision 1.6  1999-09-08 13:18:59-06  walter
// Add JavaDoc comments.
//
// Revision 1.5  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.4  1999/07/08  02:58:44  ekarlo
// Fix shape load.
//
// Revision 1.3  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.2  1999/02/08  05:11:30  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.1  1999/01/28  04:30:49  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import java.util.*;

import com.ysystems.lib.yutil.*;


/**
 * SHAPE object.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfShape extends Observable
{
    //==========================================================================
    // Shape

    /**
     * Unknown Shape type.
     */
    public final static int     TYPE_UNKNOWN    = 0;
    /**
     * Font Shape type.
     */
    public final static int     TYPE_FONT       = 1;
    /**
     * Shape type.
     */
    public final static int     TYPE_SHAPE      = 2;

    /**
     * Shape Shape type.
     */
    public
    int                         type            = TYPE_UNKNOWN;


    /**
     * Header fields.
     * <br>These are the fields found in the header of .shx files.<br>
     * Determined from scant documentation, study and guesswork.<br>
     * Two types are defined called 1.0 and 1.1.<br>
     *<br>
     * Text identifier string at start of file.
     * Seen so far (stored here without trailing crlf):
     *<UL>
     *  <LI>"AutoCAD-86 shapes 1.0\r\n"
     *  <LI>"AutoCAD-86 shapes 1.1\r\n"
     *  <LI>"AutoCAD-86 unifont 1.0\r\n"
     *</UL>
     */
    public
    String                      hdr_id          = "";

    /**
     * Unknown - seems to always be 0x1A in file.
     *<UL>
     *  <LI>shapes 1.0
     *  <LI>shapes 1.1
     *  <LI>unifont 1.0 
     *</UL>
     */
    public
    char                        hdr_c1          = '\0';

    /**
     * Unknown short - seems to contain 0x0000.
     *<UL>
     *  <LI>1.1 only
     *</UL>
     */
    public
    int                         hdr_int1        = 0;

    /**
     * Unknown short - seems to contain 0x0201.
     *<UL>
     *  <LI>1 only
     *</UL>
     */
    public
    int                         hdr_int2        = 0;

    /**
     * Short containing number of defined shapes in file.
     *<UL>
     *  <LI>1.0 
     *  <LI>1.1
     *</UL>
     */
    public
    int                         hdr_size        = 0;


    /**
     * Shape Elements.
     * Both key and val is reference to same YxxfShapeChar.<br>
     *
     * In file:<br>
     *<UL>
     *  <LI>1.0 - array of (4 byte elem header, desc/geometry code list)
     *  <LI>1.1 - array of all 4 byte elem headers then
     *            array of all desc/geometry code lists
     *<UL>
     */
    public
    Hashtable                   elem            = new Hashtable();
    //==========================================================================


    //==========================================================================
    // Get and put shape items

    /**
     * Shape source.
     */
    public
    YutilIOHandlerName          ioname          = null;

    /**
     * I/O Handler.
     */
    public
    YutilIOHandler              iohandler       = null;


    /**
     * Status and control ready.
     */
    private
    boolean                     ready           = false;

    /**
     * Status and control loaded.
     */
    private
    boolean                     loaded          = false;

    /**
     * Status and control written.
     */
    private
    boolean                     written         = false;
    //==========================================================================


    /**
     * Constructor (empty)
     */
    public
    YxxfShape()
    {
    }

    //==========================================================================

    /**
     * Reset shape empty and unused.
     */
    public
    void setShapeEmpty()
    {
        // Null all
        type = TYPE_UNKNOWN;
        hdr_id = "";
        hdr_c1 = '\0';
        hdr_int1 = 0;
        hdr_int2 = 0;
        hdr_size = 0;
        elem.clear();
        
        ready           = false;
        loaded          = false;
        written         = false;
    }
    //==========================================================================


    //==========================================================================
    // Ready wait/set/get

    /**
     * Wait until shape is ready.
     */
    public synchronized
    void waitShapeReady()
    {
        if (ready)
            return;

        while (true)
        {
            try { wait(); }
            catch(InterruptedException e) { System.out.println(e); }

            if (ready)
                return;
        }
    }


    /**
     * Set shape to a ready state.
     * @param ready The ready state.
     * @return The ready state.
     */
    public synchronized
    boolean setShapeReady(boolean ready)
    {
        this.ready = ready;
        notifyAll();
        return this.ready;
    }


    /**
     * The the shape ready state.
     * @return true if shape is ready.
     */
    public // synchronized
    boolean getShapeReady()
    {
        return ready;
    }
    //==========================================================================




    //==========================================================================
    // Loaded wait/set/get

    /**
     * Wait until shape is loaded.
     */
    public synchronized
    void waitShapeLoaded()
    {
        if (loaded)
            return;

        while (true)
        {
            try { wait(); }
            catch(InterruptedException e) { System.out.println(e); }

            if (loaded)
                return;
        }
    }


    /**
     * Set the loaded state of the shape.
     * @param loaded The shape loaded state.
     * @return true if the shape is loaded.
     */
    public synchronized
    boolean setShapeLoaded(boolean loaded)
    {
        this.loaded = loaded;
        notifyAll();
        return this.loaded;
    }


    /**
     * Get the loaded state of the shape.
     * @return true if the shape is loaded.
     */
    public // synchronized
    boolean getShapeLoaded()
    {
        return loaded;
    }
    //==========================================================================




    //==========================================================================
    // Written wait/set/get

    /**
     * Wait until the shape is written.
     */
    public synchronized
    void waitShapeWritten()
    {
        if (written)
            return;

        while (true)
        {
            try { wait(); }
            catch(InterruptedException e) { System.out.println(e); }

            if (written)
                return;
        }
    }


    /**
     * Set the written state of the shape.
     * @param written The written state of the shape.
     * @return true if the shape is written.
     */
    public synchronized
    boolean setShapeWritten(boolean written)
    {
        this.written = written;
        notifyAll();
        return this.written;
    }

    /**
     * Get the written state of the shape.
     * @return true if the shape is written.
     */
    public // synchronized
    boolean getShapeWritten()
    {
        return written;
    }
    //==========================================================================


    //==========================================================================

    /**
     * Lookup shape character.
     * @param c The shape character.
     * @return The shape character.
     */
    public
    YxxfShapeChar findShapeChar(char c)
    {
        YxxfShapeChar lookupshapechar = new YxxfShapeChar(c);
        return findShapeChar(lookupshapechar);
    }

    /**
     * Lookup shape character.
     * @param lookupshapechar The shape character.
     * @return The shape character.
     */
    public
    YxxfShapeChar findShapeChar(YxxfShapeChar lookupshapechar)
    {
        return (YxxfShapeChar)elem.get(lookupshapechar);
    }

    /**
     * Lookup shape character or set it if it doesn't exist.
     * @param lookupshapechar The shape character to find.
     * @return The shape character.
     */
    public
    YxxfShapeChar findShapeChar_set(YxxfShapeChar lookupshapechar)
    {
        YxxfShapeChar foundshapechar = (YxxfShapeChar)elem.get(lookupshapechar);
        if (foundshapechar != null)
        {
            lookupshapechar.setDesc(foundshapechar.getDesc());
            lookupshapechar.setGeom(foundshapechar.getGeom());
        }
        return foundshapechar;
    }
    //==========================================================================


    //==========================================================================
    /**
     * Get shape attribute TODO.
     * @return The shape attribute or the default attribute if no
     * shape character.
     */

    public
    char getShapeDesc_above_size()
    {
        // Find the shape description element
        YxxfShapeChar descshapechar = findShapeChar((char)0);
        if (descshapechar == null)
        {   // not found - return default
            return (char)6;
        }
        return descshapechar.getGeom()[0];
    }

    /**
     * Get shape attribute TODO.
     * @return The shape attribute or the default attribute if no 
     * shape character.
     */
    public
    char getShapeDesc_below_size()
    {
        // Find the shape description element
        YxxfShapeChar descshapechar = findShapeChar((char)0);
        if (descshapechar == null)
        {   // not found - return default
            return (char)2;
        }
        return descshapechar.getGeom()[1];
    }

    /**
     * Find the shape description element.
     * @return The shape description element or default if no shape character.
     */
    public
    boolean getShapeDesc_vert_mode_ok()
    {
        // Find the shape description element
        YxxfShapeChar descshapechar = findShapeChar((char)0);
        if (descshapechar == null)
        {   // not found - return default
            return false;
        }
        return descshapechar.getGeom()[2] == 2;
    }
    //==========================================================================
}

