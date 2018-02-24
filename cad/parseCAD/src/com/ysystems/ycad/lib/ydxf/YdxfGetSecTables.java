//==============================================================================
// YdxfGetSecTables.java
//
// Get TABLES section.
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfGetSecTables.java,v 1.20 2003/05/08 09:38:16 ekarlo Exp $
// $Log: YdxfGetSecTables.java,v $
// Revision 1.20  2003/05/08 09:38:16  ekarlo
// Remove warnings.
//
// Revision 1.19  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.18  2001/10/18 12:26:41  ekarlo
// Remove keyword value compare method so that only the value return methods
// get the value part of the code/value pair.
//
// Revision 1.17  2000-10-17 01:44:20-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.16  1999-09-22 22:58:43-06  walter
// Added JavaDoc comments.
//
// Revision 1.15  1999-08-10 08:02:41-06  ekarlo
// Remove unused var warning.
//
// Revision 1.14  1999/07/09  20:09:43  ekarlo
// Rearrange package names.
//
// Revision 1.13  1999/07/08  03:03:47  ekarlo
// Fix shape load.
//
// Revision 1.12  1999/06/27  08:28:14  ekarlo
// Rename class.
//
// Revision 1.11  1999/06/20  22:32:38  ekarlo
// Rearrange package names.
//
// Revision 1.10  1999/06/15  04:51:35  ekarlo
// User Interface - phase 1.
//
// Revision 1.9  1999/02/09  14:47:49  ekarlo
// Deactivate console print.
//
// Revision 1.8  1999/02/08  04:55:09  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.7  1999/01/28  04:35:19  ekarlo
// Text - phase 4.
//
// Revision 1.6  1998/12/22  14:43:27  ekarlo
// Renames - add "Get" to name.
//
// Revision 1.5  1998/12/21  15:33:46  ekarlo
// Text - phase 3.
//
// Revision 1.4  1998/08/25  18:15:50  ekarlo
// Add status display.
//
// Revision 1.3  1998/08/24  20:42:06  ekarlo
// Add status display.
//
// Revision 1.2  1997/08/30  14:05:24  ekarlo
// Add LTYPE table.
//
// Revision 1.1  1997/07/21  22:30:27  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import com.ysystems.ycad.lib.yxxf.*;


/**
 * Get TABLES section.
 * This class may not be instantiated.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YdxfGetSecTables
{
    /**
     * Constructor to defeat instantiation
     */
    private 
    YdxfGetSecTables()
    {
    }

    /**
     * Main get. TODO 
     * @param getbfr The buffer containing all possible types of
     * drawing information. TODO
     */
    static public
    void get(YdxfGetBuffer getbfr)
    {
        Yxxf D = getbfr.getDrawing();
        int kwval;

        getbfr.get();
        while (true)
        {
            if (getbfr.codEquals(0))
            {
                kwval = getbfr.keywordValue();

                if (kwval == YdxfKeyword.KW_I_ENDSEC)
                {
                    getbfr.get();
                    return;
                }

                if (kwval == YdxfKeyword.KW_I_TABLE)
                {
                    getbfr.get();
                    if (getbfr.codEquals(2))
                    {
                        kwval = getbfr.keywordValue();

                        if (kwval == YdxfKeyword.KW_I_VPORT)
                        {
                            D.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                                YxxfDrwViewHandlerEvent.GET_TBLNAME_BEG,
                                                YdxfKeyword.KW_S_VPORT));

                            get_vport_table(getbfr);
                            continue;
                        }

                        if (kwval == YdxfKeyword.KW_I_LTYPE)
                        {
                            D.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                                YxxfDrwViewHandlerEvent.GET_TBLNAME_BEG,
                                                YdxfKeyword.KW_S_LTYPE));

                            get_ltype_table(getbfr);
                            continue;
                        }

                        if (kwval == YdxfKeyword.KW_I_LAYER)
                        {
                            D.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                                YxxfDrwViewHandlerEvent.GET_TBLNAME_BEG,
                                                YdxfKeyword.KW_S_LAYER));

                            get_layer_table(getbfr);
                            get_layer_table_fixup(getbfr);
                            continue;
                        }

                        if (kwval == YdxfKeyword.KW_I_STYLE)
                        {
                            D.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                                YxxfDrwViewHandlerEvent.GET_TBLNAME_BEG,
                                                YdxfKeyword.KW_S_STYLE));

                            get_style_table(getbfr);
//                          get_style_table_fixup(getbfr);
                            continue;
                        }
                    }
                }
            }
            getbfr.get();
        } // End of while true loop
    }


    /**
     * Get the viewport table. TODO
     * @param getbfr The buffer containing all possible types of
     * drawing information. TODO
     */
    static private
    void get_vport_table(YdxfGetBuffer getbfr)
    {
        YxxfSecTables secTables = getbfr.getDrawing().secTables;

        int vportcnt = 0;
        int kwval;

        getbfr.get();
        while (true)
        {
            if (getbfr.codEquals(0))
            {
                kwval = getbfr.keywordValue();

                if (kwval == YdxfKeyword.KW_I_ENDTAB)
                {
                    getbfr.get();
                    return;
                }

                if (kwval == YdxfKeyword.KW_I_VPORT)
                {
                    // Ignore first vport entry - contains vport count
                    if (++vportcnt > 1)
                    {
                        YxxfTblVport new_vport = new YxxfTblVport();
                        YdxfGetTblVport.get(getbfr, new_vport);

                        secTables.tblVport.addElement(new_vport);
                    }
                    continue;
                }
            }
            getbfr.get();
        }
    }


    /**
     * Get the ltype table. TODO
     * @param getbfr The buffer containing all possible types of
     * drawing information. TODO
     */
    static private
    void get_ltype_table(YdxfGetBuffer getbfr)
    {
        YxxfSecTables secTables = getbfr.getDrawing().secTables;

        int ltypecnt = 0;
        int kwval;

        getbfr.get();
        while (true)
        {
            if (getbfr.codEquals(0))
            {
                kwval = getbfr.keywordValue();

                if (kwval == YdxfKeyword.KW_I_ENDTAB)
                {
                    getbfr.get();
                    return;
                }

                if (kwval == YdxfKeyword.KW_I_LTYPE)
                {
                    // Ignore first ltype entry - contains ltype count
                    if (++ltypecnt > 1)
                    {
                        YxxfTblLtype new_ltype = new YxxfTblLtype();
                        YdxfGetTblLtype.get(getbfr, new_ltype);

                        YxxfTblLtype find_ltype = secTables.findLtype(new_ltype);
                        if (find_ltype == null)
                            secTables.tblLtype.put(new_ltype, new_ltype);
                        else
                            new_ltype.copyInto(find_ltype);
                    }
                    continue;
                }
            }
            getbfr.get();
        }
    }


    /**
     * Get the layer table. TODO
     * @param getbfr The buffer containing all possible types of
     * drawing information. TODO
     */
    static private
    void get_layer_table(YdxfGetBuffer getbfr)
    {
        YxxfSecTables secTables = getbfr.getDrawing().secTables;

        int layercnt = 0;
        int kwval;

        getbfr.get();
        while (true)
        {
            if (getbfr.codEquals(0))
            {
                kwval = getbfr.keywordValue();

                if (kwval == YdxfKeyword.KW_I_ENDTAB)
                {
                    getbfr.get();
                    return;
                }

                if (kwval == YdxfKeyword.KW_I_LAYER)
                {
                    // Ignore first layer entry - contains layer count
                    if (++layercnt > 1)
                    {
                        YxxfTblLayer new_layer = new YxxfTblLayer();
                        YdxfGetTblLayer.get(getbfr, new_layer);

                        YxxfTblLayer find_layer = secTables.findLayer(new_layer);
                        if (find_layer == null)
                            secTables.tblLayer.put(new_layer, new_layer);
                        else
                            new_layer.copyInto(find_layer);
                    }
                    continue;
                }
            }
            getbfr.get();
        }
    }


    /**
     * Get the style table. TODO
     * @param getbfr The buffer containing all possible types of
     * drawing information. TODO
     */
    static private
    void get_style_table(YdxfGetBuffer getbfr)
    {
        YxxfSecTables secTables = getbfr.getDrawing().secTables;

        int stylecnt = 0;
        int kwval;

        getbfr.get();
        while (true)
        {
            if (getbfr.codEquals(0))
            {
                kwval = getbfr.keywordValue();

                if (kwval == YdxfKeyword.KW_I_ENDTAB)
                {
                    getbfr.get();
                    return;
                }

                if (kwval == YdxfKeyword.KW_I_STYLE)
                {
                    // Ignore first style entry - contains style count
                    if (++stylecnt > 1)
                    {
                        YxxfTblStyle new_style = new YxxfTblStyle();
                        YdxfGetTblStyle.get(getbfr, new_style);

                        YxxfTblStyle find_style = secTables.findStyle(new_style);
                        if (find_style == null)
                            secTables.tblStyle.put(new_style, new_style);
                        else
                            new_style.copyInto(find_style);
                    }
                    continue;
                }
            }
            getbfr.get();
        }
    }


    // Scan through all layer table entries and turn off the DEFPOINTS layer.
    static private
    void get_layer_table_fixup(YdxfGetBuffer getbfr)
    {
        YxxfSecTables secTables = getbfr.getDrawing().secTables;

        YxxfTblLayer find_layer = secTables.findLayer("DEFPOINTS");
        if (find_layer != null)
            find_layer.aci = -Math.abs(find_layer.aci); // turn it off
    }


/* ====
    // Scan through all style table entries and link any that do not have
    // an associated and loaded font shape file to the default shape entry (name == "").
    // Create the default if necessary.
    // It is assumed that the actual font shapes have been set and loaded externally.
    //
    // DEACTIVATED - but available and called after style table load -
    // shape is now set at entity calc() time.
    //
    static private
    void get_style_table_fixup(YdxfGetBuffer getbfr)
    {
        YxxfSecTables secTables = getbfr.getDrawing().secTables;

        secTables.tblStyle_default_style = secTables.findStyle_add(""); // find default
        if (secTables.tblStyle_default_style.shape == null) // and set empty shape if not set
        {
            secTables.tblStyle_default_style.shape = new YxxfShape();
            secTables.tblStyle_default_style.shape.setShapeReady(true);
            secTables.tblStyle_default_style.shape.setShapeLoaded(true);
        }
        
        for (Enumeration e_ioname = secTables.tblStyle.elements(); e_ioname.hasMoreElements(); ) // eieio
        {
            YxxfTblStyle style = (YxxfTblStyle)e_ioname.nextElement();
            
            if (style.shape == null)
            {   // not set, link to default
                style.shape = secTables.tblStyle_default_style.shape;
            }
        }
    }
==== */
}

