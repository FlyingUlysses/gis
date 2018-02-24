//==============================================================================
// Yxxf.java
//
// Drawing
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/Yxxf.java,v 1.36 2003/06/05 11:36:44 ekarlo Exp $
// $Log: Yxxf.java,v $
// Revision 1.36  2003/06/05 11:36:44  ekarlo
// Remove tabs.
//
// Revision 1.35  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.34  2000/10/17 07:44:08  ekarlo
// Change package paths to lower case.
//
// Revision 1.33  1999-10-22 01:27:59-06  ekarlo
// API rework - phase 1.
//
// Revision 1.32  1999-10-04 20:18:10-06  ekarlo
// Add JavaDoc Comments.
//
// Revision 1.31  1999-09-28 11:10:05-06  walter
// Add JavaDoc comments.
//
// Revision 1.30  1999-09-27 10:29:55-06  ekarlo
// Add JavaDoc comments.
//
// Revision 1.29  1999-09-07 01:42:28-06  ekarlo
// Readd line.
//
// Revision 1.28  1999-09-07 01:34:16-06  walter
// Added JavaDoc comments.
//
// Revision 1.27  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.26  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.25  1999/02/08  05:09:51  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.24  1999/01/28  04:24:45  ekarlo
// Text - phase 4.
//
// Revision 1.23  1998/12/21  15:43:26  ekarlo
// Text - phase 3.
//
// Revision 1.22  1998/11/24  19:33:19  ekarlo
// Text - phase 2.
//
// Revision 1.21  1998/07/12  00:04:43  ekarlo
// Always calculate transform for lines.
//
// Revision 1.20  1998/02/02  19:00:40  ekarlo
// Implement papaer space - phase 2.
// Rename view handler.
//
// Revision 1.19  1997/12/26  21:28:18  ekarlo
// Implement paper space - phase 1.
// Implement VIEWPORT.
//
// Revision 1.18  1997/09/11  21:00:18  ekarlo
// Call calculate transform for SOLID.
//
// Revision 1.17  1997/08/30  14:07:34  ekarlo
// Add Vport Handler event processing.
//
// Revision 1.16  1997/07/23  14:19:28  ekarlo
// Move get thread out of main thread.
//
// Revision 1.15  1997/07/21  22:34:33  ekarlo
// Rename from Ydxf.java to Yxxf.java.
// MVC-VH rework - phase 2.
//
// Revision 1.14  1997/07/13  17:57:24  ekarlo
// MVC-VH rework - phase 1.
//
// Revision 1.13  1997/06/11  15:35:29  ekarlo
// Make Ydxf object passive rather than thread.
// Remove command line set methods (moved to YxxfGfxContext.java).
// Move in generate test methods.
//
// Revision 1.12  1996/10/26  00:07:29  ekarlo
// 1) Changes for parameter processing.
// 2) Correct color and layer handling.
//
// Revision 1.11  1996/09/27  09:37:03  ekarlo
// Add text - v1.
//
// Revision 1.10  1996/09/26  18:00:54  ekarlo
// Add user parameters:
//     vport, vportnum
//     printlayers, printvports, printblocks
//     drawdspminmax, drawextminmax, drawlimminmax
// Implement thickness.
//
// Revision 1.9  1996/09/26  01:40:48  ekarlo
// 1) Implement proper color activity.
// 2) Implement ESpace, PSpace, MSpace.
//
// Revision 1.8  1996/09/14  03:26:01  ekarlo
// Connect layer in add entity methods.
//
// Revision 1.7  1996/09/13  06:18:08  ekarlo
// 1) Use new base entity class structure.
// 2) Redo user parameter processing and add bgcolor parameter.
//
// Revision 1.6  1996/08/25  05:29:36  ekarlo
// Test procedure - draw ellipse.
//
// Revision 1.5  1996/08/19  04:58:25  ekarlo
// 1) Wait to stop get thread until loaded flag is set.
// 2) Called moved calcAAA().
//
// Revision 1.4  1996/08/18  02:28:33  ekarlo
// Different test procedure.
//
// Revision 1.3  1996/08/13  01:49:11  ekarlo
// Add test function (for 3D transform development).
//
// Revision 1.2  1996/07/30  06:15:33  ekarlo
// 1) Remove unused variable.
// 2) Call function to draw status indicators.
// 3) Pause get thread until draw thread finishes
//    (page fault workaround).
//
// Revision 1.1  1996/07/02  01:58:28  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import java.util.*;

import com.ysystems.lib.yutil.*;


/**
 * The Yxxf class represents a single instance of a Ycad drawing.
 * <p>
 * The Yxxf drawing object is a hierarchical data structure based
 * on AutoDesk's DXF format.
 * The Yxxf drawing object contains references to the various DXF
 * sections and flags describing it's state.
 * A familiarity with the DXF format is required to fully understand and
 * use the Yxxf object.
 * See the Ycad User's Guide for information on the DXF format.
 * <p>The code:<pre>
 * Yxxf D = new Yxxf();</pre>
 * will create an empty drawing object, D, that has the state
 * ready=false, complete=false, written=false.
 * <p>Only the DXF sections
 * needed for the viewing the drawing are implemented and they are:
 * <TABLE BORDER="1" WIDTH="704">
 *     <TR>
 *         <TH WIDTH="299">
 *             <P>Section Name / Description
 *         </TH>
 *         <TH WIDTH="169">
 *             <P>Object Type
 *         </TH>
 *         <TH WIDTH="214">
 *             <P>Accessed as (D is type Yxxf)
 *         </TH>
 *     </TR>
 *     <TR>
 *         <TD WIDTH="299"><FONT FACE="Courier New, Courier">HEADER<BR>
 *             </FONT>A collection of individual fields such as current line type, current color, linetype scale.</TD>
 *         <TD WIDTH="169"><FONT FACE="Courier New, Courier">YxxfSecHeader</FONT></TD>
 *         <TD WIDTH="214"><FONT FACE="Courier New, Courier">D.secHEADER</FONT></TD>
 *     </TR>
 *     <TR>
 *         <TD WIDTH="299"><FONT FACE="Courier New, Courier">TABLES<BR>
 *             </FONT>A collection of tables including: layer table, line type table, text style (fonta), Vport table.</TD>
 *         <TD WIDTH="169"><FONT FACE="Courier New, Courier">YxxfSecTables</FONT></TD>
 *         <TD WIDTH="214"><FONT FACE="Courier New, Courier">D.secTABLES</FONT></TD>
 *     </TR>
 *     <TR>
 *         <TD WIDTH="299"><FONT FACE="Courier New, Courier">BLOCKS<BR>
 *             </FONT>A named sequence of drawing entities.</TD>
 *         <TD WIDTH="169"><FONT FACE="Courier New, Courier">YxxfSecBlocks</FONT></TD>
 *         <TD WIDTH="214"><FONT FACE="Courier New, Courier">D.secBLOCKS</FONT></TD>
 *     </TR>
 *     <TR>
 *         <TD WIDTH="299"><FONT FACE="Courier New, Courier">ENTITIES<BR>
 *             </FONT>A sequence of drawing entities such as line, circle, polyline, insert block. There are two types of entities,
 *             Paper Space and Model Space.</TD>
 *         <TD WIDTH="169"><FONT FACE="Courier New, Courier">YxxfSecEntities</FONT></TD>
 *         <TD WIDTH="214"><FONT FACE="Courier New, Courier">D.secENTITIES</FONT></TD>
 *     </TR>
 * </TABLE>
 * <p>Thus:<pre>
 * YxxfGfxPointW extpnt = D.secHeader.extmin;</pre>
 * will access a field within the HEADER section and<pre>
 * YxxfTblLayer layer = D.secTables.findLayer_add("FLOORPLAN");</pre>
 * <p>will call a method in the TABLES section.
 * <p>See the individual section class descriptions for field and method summaries.
 * 
 * @author Ed Karlo - Y Systems, LLC
 */
public class Yxxf extends Observable
{
    /**
     * Paper space root layer name (char array)
     */
    public static final char[]  CHR_LAYERNAME__PAPER_SPACE_ROOT_LAYER
                                                            = { '*','*','P','A','P','E','R','_','S','P','A','C','E','_','R','O','O','T','_','L','A','Y','E','R' } ;
    /**
     * Paper space root layer name (String)
     */
    public static final String  STR_LAYERNAME__PAPER_SPACE_ROOT_LAYER = "**PAPER_SPACE_ROOT_LAYER";

    /**
     * Paper space root layer name (char array)
     */
    public static final char[]  CHR_LAYERNAME__MODEL_SPACE_ROOT_LAYER
                                                            = { '*','*','M','O','D','E','L','_','S','P','A','C','E','_','R','O','O','T','_','L','A','Y','E','R' } ;
    /**
     * Paper space root layer name (String)
     */
    public static final String  STR_LAYERNAME__MODEL_SPACE_ROOT_LAYER = "**MODEL_SPACE_ROOT_LAYER";

    /**
     * Paper space block name (char array)
     */
    public static final char[]  CHR_BLOCKNAME__PAPER_SPACE
                                                            = { '*','P','A','P','E','R','_','S','P','A','C','E' } ;
    /**
     * Paper space block name (String)
     */
    public static final String  STR_BLOCKNAME__PAPER_SPACE = "*PAPER_SPACE";

    /**
     * Paper space block name (char array)
     */
    public static final char[]  CHR_BLOCKNAME__MODEL_SPACE
                                                            = { '*','M','O','D','E','L','_','S','P','A','C','E' } ;
    /**
     * Paper space block name (String)
     */
    public static final String  STR_BLOCKNAME__MODEL_SPACE = "*MODEL_SPACE";


    //==========================================================================
    // Drawing

    /**
     * Drawing type unknown (not currently used)
     */
    public final static int     TYPE_UNKNOWN    = 0;

    /**
     * Drawing type MAIN (not currently used)
     */
    public final static int     TYPE_MAIN       = 1;

    /**
     * Drawing type FONT (not currently used)
     */
    public final static int     TYPE_FONT       = 2;

    /**
     * A drawing type (not currently used)
     */
    public
    int                         type            = TYPE_UNKNOWN;

    /**
     * Header Section
     * A collection of individual fields such as current line type, 
     * current color, linetype scale.
     */
    public
    YxxfSecHeader               secHeader       = null;

    /**
     * Tables Section
     * A collection of tables including: layer table, 
     * line type table, text style(font), ViewPort.
     */
    public
    YxxfSecTables               secTables       = null;

    /**
     * Blocks Section
     * A named sequence of drawing entities. Blocks are either Paper 
     * Space or Model Space blocks.
     */
    public
    YxxfSecBlocks               secBlocks       = null;

    /**
     * Entities Section
     * A sequence of drawing entities such as line, circle, polyline, 
     * insert block. There are two types of entities, Paper Space and Model 
     * Space.
     */
    public
    YxxfSecEntities             secEntities     = null;
    //==========================================================================


    //==========================================================================
    // Get and put drawing items

    /**
     * Drawing source
     */
    public
    YutilIOHandlerName          ioname          = null;

    /**
     * I/O Handler
     */
    public
    YutilIOHandler              iohandler       = null;

    /**
     * Ready flag
     */
    private
    boolean                     ready           = false;
    
    /**
     * Complete flag
     */
    private
    boolean                     complete        = false;

    /**
     * Written flag
     */
    private
    boolean                     written         = false;
    //==========================================================================




    //==========================================================================
    /**
     * Constructor
     * Sets up entity 
     */
    public
    Yxxf()
    {
        setDrawingEmpty();
    }


    /**
     * Set to initial empty state.
     */
    public
    void setDrawingEmpty()
    {
        type            = TYPE_UNKNOWN;

        secHeader       = new YxxfSecHeader();
        secTables       = new YxxfSecTables();
        secBlocks       = new YxxfSecBlocks();
        secEntities     = new YxxfSecEntities();
        
        ready           = false;
        complete        = false;
        written         = false;

        setupESpace();
    }
    //==========================================================================




    //==========================================================================
    // Ready wait/set/get

    /**
     * Wait until drawing is ready.
     */
    public synchronized
    void waitDrawingReady()
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
     * Set the value of the ready flag.
     * @param ready Is the drawing ready
     * @return true if ready
     */
    public synchronized
    boolean setDrawingReady(boolean ready)
    {
        this.ready = ready;
        notifyAll();
        return this.ready;
    }


    /**
     * Return value of ready flag.
     * @return true if ready
     */
    public // synchronized
    boolean getDrawingReady()
    {
        return ready;
    }
    //==========================================================================




    //==========================================================================
    // Complete wait/set/get

    /**
     * Wait until drawing is complete.
     */
    public synchronized
    void waitDrawingComplete()
    {
        if (complete)
            return;

        while (true)
        {
            try { wait(); }
            catch(InterruptedException e) { System.out.println(e); }

            if (complete)
                return;
        }
    }


    /**
     * Set the value of the complete flag and tell listeners it's changed.
     * @param complete The value of the complete flag
     * @return true if complete 
     */
    public synchronized
    boolean setDrawingComplete(boolean complete)
    {
        this.complete = complete;

        // Mark lists as complete
        secEntities.insPSpace.block.setBlockComplete(this.complete);
        secEntities.insMSpace.block.setBlockComplete(this.complete);

        // Notify it's good to go
        secEntities.insPSpace.block.drawNotify();
        secEntities.insMSpace.block.drawNotify();

        notifyAll();

        return this.complete;
    }


    /**
     * Get the value of complete flag
     * @return true if complete
     */
    public // synchronized
    boolean getDrawingComplete()
    {
        return complete;
    }
    //==========================================================================




    //==========================================================================
    // Written wait/set/get

    /**
     * Wait until drawing is written.
     */
    public synchronized
    void waitDrawingWritten()
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
     * Set the value of the written flag and notify listeners that 
     * it has changed.
     * @param written The value of the written flag
     * @return true if written
     */
    public synchronized
    boolean setDrawingWritten(boolean written)
    {
        this.written = written;
        notifyAll();
        return this.written;
    }


    /**
     * Get the value of the written flag.
     * @return true if written
     */
    public // synchronized
    boolean getDrawingWritten()
    {
        return written;
    }
    //==========================================================================




    //==========================================================================
    /**
     * Initial insert where the drawing 
     * Set up inserts for paper space and model space.
     * <UL>
     *   <LI>Check if layer "0" exists and create if necessary.
     *   <LI>Check if layers "**PAPER_SPACE_ROOT_LAYER" and 
     *       "**MODEL_SPACE_ROOT_LAYER" exist and create if necessary.
     *   <LI>Check if blocks "*PAPER_SPACE" and "*MODEL_SPACE" exist and
     *       create if necessary.
     *   <LI>Create PSpace and MSpace inserts.
     * </UL>
     */
    public
    void setupESpace()
    {
        // Create default line types CONTINUOUS and BYLAYER
        YxxfTblLtype def_ltype_continuous =
            secTables.findLtype_add(YxxfTblLtypeKey.STR_LTYPENAME__CONTINUOUS);
        secTables.findLtype_add(YxxfTblLtypeKey.STR_LTYPENAME__BYLAYER);


        // Create default layer 0
        secTables.findLayer_add(YxxfTblLayerKey.STR_LAYERNAME__0);


        //
        // PSpace
        //

        // Create the root layer for PSpace.
        YxxfTblLayer
        layPSpaceRoot                       = secTables.findLayer_add(STR_LAYERNAME__PAPER_SPACE_ROOT_LAYER);
        layPSpaceRoot.ltype                 = def_ltype_continuous;
        layPSpaceRoot.aci                   = YxxfGfxPalette.ACI_WHITE;

        // Set up block for PSpace
        YxxfEntBlock
        blkPSpace                           = secBlocks.findBlock(STR_BLOCKNAME__PAPER_SPACE);
        if (blkPSpace == null)
        {
            blkPSpace                       = secBlocks.findBlock_add(STR_BLOCKNAME__PAPER_SPACE);
            blkPSpace.hdr_layer             = layPSpaceRoot;
            blkPSpace.calc(this);
        }

        // Create the insert for PSpace
        if (secEntities.insPSpace == null)
            secEntities.insPSpace           = new YxxfEntInsert(blkPSpace);
        secEntities.insPSpace.hdr_layer     = layPSpaceRoot;
        secEntities.insPSpace.hdr_aci       = YxxfGfxPalette.ACI_WHITE;
        secEntities.insPSpace.calc(this);


        //
        // MSpace
        //

        // Create the root layer for MSpace
        YxxfTblLayer
        layMSpaceRoot                       = secTables.findLayer_add(STR_LAYERNAME__MODEL_SPACE_ROOT_LAYER);
        layMSpaceRoot.ltype                 = def_ltype_continuous;
        layMSpaceRoot.aci                   = YxxfGfxPalette.ACI_WHITE;

        // Set up block for MSpace
        YxxfEntBlock
        blkMSpace                           = secBlocks.findBlock(STR_BLOCKNAME__MODEL_SPACE);
        if (blkMSpace == null)
        {
            blkMSpace                       = secBlocks.findBlock_add(STR_BLOCKNAME__MODEL_SPACE);
            blkMSpace.hdr_layer             = layMSpaceRoot;
            blkMSpace.calc(this);
        }

        // Create the insert for MSpace
        if (secEntities.insMSpace == null)
            secEntities.insMSpace           = new YxxfEntInsert(blkMSpace);
        secEntities.insMSpace.hdr_layer     = layMSpaceRoot;
        secEntities.insMSpace.hdr_aci       = YxxfGfxPalette.ACI_WHITE;
        secEntities.insMSpace.calc(this);
    }
    //==========================================================================


    //==========================================================================
    /**
     * Notify listeners that view has changed.
     * @param vhevt A View handler event.
     */
    public
    void notifyViewHandler(YxxfDrwViewHandlerEvent vhevt)
    {
        setChanged();
        notifyObservers(vhevt);
    }
    //==========================================================================
}

