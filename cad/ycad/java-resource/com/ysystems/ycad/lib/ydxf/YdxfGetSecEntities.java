//==============================================================================
// YdxfGetSecEntities.java
//
// Get ENTITIES section
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfGetSecEntities.java,v 1.23 2003/05/08 09:38:16 ekarlo Exp $
// $Log: YdxfGetSecEntities.java,v $
// Revision 1.23  2003/05/08 09:38:16  ekarlo
// Remove warnings.
//
// Revision 1.22  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.21  2002/10/08 00:47:49  ekarlo
// Fix Javadoc comment.
//
// Revision 1.20  2000-10-17 01:44:21-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.19  1999-10-26 10:19:43-06  ekarlo
// Add MTEXT.
//
// Revision 1.18  1999-10-25 17:56:36-06  ekarlo
// Add TRACE.
//
// Revision 1.17  1999-10-22 01:28:03-06  ekarlo
// API rework - phase 1.
//
// Revision 1.16  1999-09-22 22:57:54-06  walter
// Added JavaDoc comments.
//
// Revision 1.15  1999-08-07 15:01:20-06  ekarlo
// Add LWPOLYLINE.
//
// Revision 1.14  1999/07/09  20:09:43  ekarlo
// Rearrange package names.
//
// Revision 1.13  1999/06/27  08:28:14  ekarlo
// Rename class.
//
// Revision 1.12  1999/06/20  22:32:38  ekarlo
// Rearrange package names.
//
// Revision 1.11  1999/06/15  04:51:35  ekarlo
// User Interface - phase 1.
//
// Revision 1.10  1999/02/09  14:47:49  ekarlo
// Deactivate console print.
//
// Revision 1.9  1999/02/08  04:55:09  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.8  1999/01/28  04:35:19  ekarlo
// Text - phase 4.
//
// Revision 1.7  1998/12/22  14:43:16  ekarlo
// Renames - add "Get" to name.
//
// Revision 1.6  1998/08/25  18:15:50  ekarlo
// Add status display.
//
// Revision 1.5  1998/08/24  20:42:06  ekarlo
// Add status display.
//
// Revision 1.4  1998/02/02  18:51:38  ekarlo
// Implement paper space - phase 2.
// Rename view handler.
//
// Revision 1.3  1997/12/26  21:25:21  ekarlo
// Implement paper space - phase 1.
// Implement VIEWPORT.
//
// Revision 1.2  1997/12/07  20:04:17  ekarlo
// Experiment with get/draw thread concurrency.
//
// Revision 1.1  1997/07/21  22:30:27  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import com.ysystems.ycad.lib.yxxf.*;


/**
 * Get ENTITIES section.
 * This class may not be instantiated.
 * @author Ed Karlo - Y Systems, LLC    
 */
public class YdxfGetSecEntities
{
    /**
     * Constructor - may not be instantiated.
     */
    private // Defeat instantiation
    YdxfGetSecEntities()
    {
    }


    /**
     * Set the Drawing state while getting the Section Entities data 
     * and setting it in the GetBuffer.
     * @param getbfr The get buffer object.
     */
    static public
    void get(YdxfGetBuffer getbfr)
    {
        Yxxf D = getbfr.getDrawing();

//d     System.out.println("TGet :BEG get_entities_section");

        getbfr.get_doing = YdxfGetBuffer.GET_DOING_ENTITY_SECTION;
        getbfr.currEntBlock = null; // determined by entity header flag


        // Get entities list
        YdxfGetSecEntities.get_entities(getbfr);


        // Mark lists as complete
        D.secEntities.insPSpace.block.setBlockComplete(true);
        D.secEntities.insMSpace.block.setBlockComplete(true);


        // Tell drawing thread it's good to go
        D.secEntities.insPSpace.block.drawNotify();
        D.secEntities.insMSpace.block.drawNotify();


//d     System.out.println("TGet :END get_entities_section");
    }


    /**
     * Get entities from entity section or block.
     * @param getbfr The get buffer object.
     */
    static public
    void get_entities(YdxfGetBuffer getbfr)
    {
        Yxxf D = getbfr.getDrawing();

        getbfr.get();
        while (true)
        {
            if (getbfr.codEquals(0))
            {
                int kwd = getbfr.keywordValue();

                if (kwd == YdxfKeyword.KW_I_ENDSEC)
                {
                    getbfr.get();
                    return;
                }


                if (kwd == YdxfKeyword.KW_I_3DFACE)
                {
                    YxxfEnt3Dface new_3dface = new YxxfEnt3Dface();
                    YdxfGetEnt3Dface.get(getbfr, new_3dface);
                    new_3dface.calc(D);
                    getbfr.setCurrEntBlock(new_3dface);
                    getbfr.currEntBlock.addEntity(new_3dface);
                    continue;
                } else // end of 3dface


                if (kwd == YdxfKeyword.KW_I_ATTDEF)
                {
                    YxxfEntAttdef new_attdef = new YxxfEntAttdef();
                    YdxfGetEntAttdef.get(getbfr, new_attdef);
                    new_attdef.calc(D);
                    getbfr.setCurrEntBlock(new_attdef);
                    getbfr.currEntBlock.addEntityAttdef(new_attdef);
                    continue;
                } else // end of attdef


                if (kwd == YdxfKeyword.KW_I_ATTRIB)
                {
                    YxxfEntAttrib new_attrib = new YxxfEntAttrib();
                    YdxfGetEntAttrib.get(getbfr, new_attrib);
                    if (getbfr.currEntInsert != null)
                    {
                        // if attrib is on layer 0 user layer of insert
                        // ToDo: redo
                        if (new_attrib.hdr_layer.equals(YxxfTblLayerKey.STR_LAYERNAME__0))
                            new_attrib.hdr_layer = getbfr.currEntInsert.hdr_layer;
                        new_attrib.calc(D);
                        getbfr.currEntInsert.addAttrib(new_attrib);
                    }
                    continue;
                } else // end of attrib


                if (kwd == YdxfKeyword.KW_I_ARC)
                {
                    YxxfEntArc new_arc = new YxxfEntArc();
                    YdxfGetEntArc.get(getbfr, new_arc);
                    new_arc.calc(D);
                    getbfr.setCurrEntBlock(new_arc);
                    getbfr.currEntBlock.addEntity(new_arc);
                    continue;
                } else // end of arc


                if (kwd == YdxfKeyword.KW_I_BLOCK)
                {
                    YxxfEntBlock new_block = new YxxfEntBlock();
                    YdxfGetEntBlock.get(getbfr, new_block);
                    new_block.calc(D);

                    // connect block
                    YxxfEntBlock find_block = D.secBlocks.findBlock(new_block);
                    if (find_block == null)
                    {
                        D.secBlocks.addBlock(new_block);
                        getbfr.currEntBlock = new_block;
                    }
                    else
                    {
                        new_block.copyInto(find_block);
                        getbfr.currEntBlock = find_block;
                    }

                    D.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                        YxxfDrwViewHandlerEvent.GET_BLKNAME_BEG,
                                        getbfr.currEntBlock.getBlockname2()));
                    continue;
                } else // end of block


                if (kwd == YdxfKeyword.KW_I_CIRCLE)
                {
                    YxxfEntCircle new_circle = new YxxfEntCircle();
                    YdxfGetEntCircle.get(getbfr, new_circle);
                    new_circle.calc(D);
                    getbfr.setCurrEntBlock(new_circle);
                    getbfr.currEntBlock.addEntity(new_circle);
                    continue;
                } else // end of circle


                if (kwd == YdxfKeyword.KW_I_DIMENSION)
                {
                    YxxfEntDimension new_dimension = new YxxfEntDimension();
                    YdxfGetEntDimension.get(getbfr, new_dimension);
                    new_dimension.calc(D);
                    getbfr.setCurrEntBlock(new_dimension);
                    getbfr.currEntBlock.addEntity(new_dimension);
                    continue;
                } else // end of dimension


                if (kwd == YdxfKeyword.KW_I_ENDBLK)
                {
                    getbfr.get();
                    
                    D.notifyViewHandler(new YxxfDrwViewHandlerEvent(
                                        YxxfDrwViewHandlerEvent.GET_BLKNAME_END,
                                        getbfr.currEntBlock.getBlockname2()));

                    if (!getbfr.currEntBlock.equals("*PAPER_SPACE") &&
                        !getbfr.currEntBlock.equals("*MODEL_SPACE"))
                    {
                        getbfr.currEntBlock.setBlockComplete(true);
                    }
                                        
                    getbfr.currEntBlock = null;
                    continue;
                } else // end of endblk


                if (kwd == YdxfKeyword.KW_I_INSERT)
                {
                    getbfr.currEntInsert = new YxxfEntInsert();
                    YdxfGetEntInsert.get(getbfr, getbfr.currEntInsert);
                    // If insert attFollow == 1 then
                    //     new insert is added to current block list during seqend processing
                    // else
                    //     new insert is added here
                    if (getbfr.currEntInsert.attFollow == 0)
                    {
                        getbfr.currEntInsert.calc(D);
                        getbfr.setCurrEntBlock(getbfr.currEntInsert);
                        getbfr.currEntBlock.addEntity(getbfr.currEntInsert);
                        getbfr.currEntInsert = null;
                    }
                    continue;
                } else // end of insert


                if (kwd == YdxfKeyword.KW_I_LINE)
                {
                    YxxfEntLine new_line = new YxxfEntLine();
                    YdxfGetEntLine.get(getbfr, new_line);
                    new_line.calc(D);
                    getbfr.setCurrEntBlock(new_line);
                    getbfr.currEntBlock.addEntity(new_line);
                    continue;
                } else // end of line


                if (kwd == YdxfKeyword.KW_I_LWPOLYLINE)
                {
                    YxxfEntLwpolyline new_lwpolyline = new YxxfEntLwpolyline();
                    YdxfGetEntLwpolyline.get(getbfr, new_lwpolyline);
                    new_lwpolyline.calc(D);
                    getbfr.setCurrEntBlock(new_lwpolyline);
                    getbfr.currEntBlock.addEntity(new_lwpolyline);
                    continue;
                } else // end of lwpolyline


                if (kwd == YdxfKeyword.KW_I_MTEXT)
                {
                    YxxfEntMtext new_mtext = new YxxfEntMtext();
                    YdxfGetEntMtext.get(getbfr, new_mtext);
                    new_mtext.calc(D);
                    getbfr.setCurrEntBlock(new_mtext);
                    getbfr.currEntBlock.addEntity(new_mtext);
                    continue;
                } else // end of mtext


                if (kwd == YdxfKeyword.KW_I_POINT)
                {
                    YxxfEntPoint new_point = new YxxfEntPoint();
                    YdxfGetEntPoint.get(getbfr, new_point);
                    new_point.calc(D);
                    getbfr.setCurrEntBlock(new_point);
                    getbfr.currEntBlock.addEntity(new_point);
                    continue;
                } else // end of point


                if (kwd == YdxfKeyword.KW_I_POLYLINE)
                {
                    getbfr.currEntPolyline = new YxxfEntPolyline();
                    YdxfGetEntPolyline.get(getbfr, getbfr.currEntPolyline);
                    // new polyline is added to current block list during seqend processing
                    continue;
                } else // end of polyline


                if (kwd == YdxfKeyword.KW_I_SEQEND)
                {
                    if (getbfr.currEntPolyline != null)
                    {
                        getbfr.currEntPolyline.calc(D);
                        getbfr.setCurrEntBlock(getbfr.currEntPolyline);
                        getbfr.currEntBlock.addEntity(getbfr.currEntPolyline);
                        getbfr.currEntPolyline = null;
                    }
                    else
                    if (getbfr.currEntInsert != null)
                    {
                        getbfr.currEntInsert.calc(D);
                        getbfr.setCurrEntBlock(getbfr.currEntInsert);
                        getbfr.currEntBlock.addEntity(getbfr.currEntInsert);
                        getbfr.currEntInsert = null;
                    }
                    getbfr.get();
                    continue;
                } else // end of seqend


                if (kwd == YdxfKeyword.KW_I_SOLID)
                {
                    YxxfEntSolid new_solid = new YxxfEntSolid();
                    YdxfGetEntSolid.get(getbfr, new_solid);
                    new_solid.calc(D);
                    getbfr.setCurrEntBlock(new_solid);
                    getbfr.currEntBlock.addEntity(new_solid);
                    continue;
                } else // end of solid


                if (kwd == YdxfKeyword.KW_I_TEXT)
                {
                    YxxfEntText new_text = new YxxfEntText();
                    YdxfGetEntText.get(getbfr, new_text);
                    new_text.calc(D);
                    getbfr.setCurrEntBlock(new_text);
                    getbfr.currEntBlock.addEntity(new_text);
                    continue;
                } else // end of text


                if (kwd == YdxfKeyword.KW_I_TRACE)
                {
                    YxxfEntTrace new_trace = new YxxfEntTrace();
                    YdxfGetEntTrace.get(getbfr, new_trace);
                    new_trace.calc(D);
                    getbfr.setCurrEntBlock(new_trace);
                    getbfr.currEntBlock.addEntity(new_trace);
                    continue;
                } else // end of trace


                if (kwd == YdxfKeyword.KW_I_VERTEX)
                {
                    YxxfEntVertex new_vertex = new YxxfEntVertex();
                    YdxfGetEntVertex.get(getbfr, new_vertex);
                    if (getbfr.currEntPolyline != null)
                    {
                        new_vertex.calc(D);
                        getbfr.currEntPolyline.addVertex(new_vertex);
                    }
                    continue;
                } else // end of vertex

                
                if (kwd == YdxfKeyword.KW_I_VIEWPORT)
                {
                    YxxfEntViewport new_viewport = new YxxfEntViewport();
                    YdxfGetEntViewport.get(getbfr, new_viewport);
                    new_viewport.calc(D);
                    getbfr.setCurrEntBlock(new_viewport);
                    getbfr.currEntBlock.addEntity(new_viewport);
                    // Add to viewport entity list
                    D.secEntities.entViewportList.addElement(new_viewport);
                    continue;
                } // end of arc
            }

            getbfr.get();
        }
    }
}

