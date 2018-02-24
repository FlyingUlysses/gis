//==============================================================================
// YdxfGet.java
//
// Main DXF get
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfGet.java,v 1.19 2003/05/08 09:38:16 ekarlo Exp $
// $Log: YdxfGet.java,v $
// Revision 1.19  2003/05/08 09:38:16  ekarlo
// Remove warnings.
//
// Revision 1.18  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.17  2001/10/18 12:26:42  ekarlo
// Remove keyword value compare method so that only the value return methods
// get the value part of the code/value pair.
//
// Revision 1.16  2000-10-17 01:44:31-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.15  1999-10-22 01:27:50-06  ekarlo
// API rework - phase 1.
//
// Revision 1.14  1999-10-21 21:34:23-06  walter
// Added JavaDoc comments.
//
// Revision 1.13  1999-07-09 14:09:43-06  ekarlo
// Rearrange package names.
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
// Revision 1.9  1999/02/08  04:58:27  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.8  1999/01/28  04:10:04  ekarlo
// Text - phase 4.
//
// Revision 1.7  1998/12/22  14:42:23  ekarlo
// Renames - add "Get" to name.
//
// Revision 1.6  1998/08/24  20:42:06  ekarlo
// Add status display.
//
// Revision 1.5  1998/02/02  18:51:38  ekarlo
// Implement paper space - phase 2.
// Rename view handler.
//
// Revision 1.4  1997/12/26  21:20:29  ekarlo
// Implement paper space - phase 1.
// Move out print of entities section end stats.
//
// Revision 1.3  1997/12/07  20:03:32  ekarlo
// Experiment with get/draw thread concurrency.
//
// Revision 1.2  1997/08/30  14:03:13  ekarlo
// Add Vport Handler event processing.
//
// Revision 1.1  1997/07/21  22:24:42  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import com.ysystems.ycad.lib.yxxf.*;


/**
 * Main DXF get.
 * This class may not be instantiated.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YdxfGet
{
    /**
     * Constructor to prevent instantiation.
     */
    private // Defeat instantiation
    YdxfGet()
    {
    }


    //==========================================================================
    /**
     * Inflate the drawing.
     * @param getbfr The buffer to hold the drawing.
     */
    static public
    void get(YdxfGetBuffer getbfr)
    {
        Yxxf D = getbfr.getDrawing();

        getbfr.get();
        while (true)
        {
            if (getbfr.codEquals(0))
            {
                int kwval = getbfr.keywordValue();

                if (kwval == YdxfKeyword.KW_I_EOF)
                    break;

                if (kwval == YdxfKeyword.KW_I_SECTION)
                {
                    getbfr.get();

                    kwval = getbfr.keywordValue();

                    if (kwval == YdxfKeyword.KW_I_HEADER)
                    {
                        D.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                            YxxfDrwViewHandlerEvent.GET_SECHEADER_BEG,
                                            D));

                        YdxfGetSecHeader.get(getbfr);

                        D.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                            YxxfDrwViewHandlerEvent.GET_SECHEADER_END,
                                            D));

                        continue;
                    }

                    if (kwval == YdxfKeyword.KW_I_TABLES)
                    {
                        D.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                            YxxfDrwViewHandlerEvent.GET_SECTABLES_BEG,
                                            D));

                        YdxfGetSecTables.get(getbfr);

                        D.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                            YxxfDrwViewHandlerEvent.GET_SECTABLES_END,
                                            D));

                        continue;
                    }

                    if (kwval == YdxfKeyword.KW_I_BLOCKS)
                    {
                        D.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                            YxxfDrwViewHandlerEvent.GET_SECBLOCKS_BEG,
                                            D));

                        YdxfGetSecBlocks.get(getbfr);

                        D.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                            YxxfDrwViewHandlerEvent.GET_SECBLOCKS_END,
                                            D));

                        continue;
                    }

                    if (kwval == YdxfKeyword.KW_I_ENTITIES)
                    {
                        D.setDrawingReady(true);

                        if (getbfr.get_type == YdxfGetBuffer.GET_TYPE_FONT) // quit if load font drawing
                            break;
                            
                        D.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                            YxxfDrwViewHandlerEvent.GET_SECENTITIES_BEG,
                                            D));
                                             
                        YdxfGetSecEntities.get(getbfr);

                        D.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                            YxxfDrwViewHandlerEvent.GET_SECENTITIES_END,
                                            D));

                        continue;
                    }
                }
            }
            getbfr.get();
        }

        D.setDrawingReady(true);
        D.setDrawingComplete(true);

        getbfr.close();

        D.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                            YxxfDrwViewHandlerEvent.GET_DRAWING_EOF,
                            D));
    }
    //==========================================================================
}

