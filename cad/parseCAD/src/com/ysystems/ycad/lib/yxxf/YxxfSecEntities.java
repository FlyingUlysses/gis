//==============================================================================
// YxxfSecEntities.java
//
// ENTITIES section
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfSecEntities.java,v 1.14 2003/04/14 12:38:42 ekarlo Exp $
// $Log: YxxfSecEntities.java,v $
// Revision 1.14  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.13  2002/09/29 05:20:54  ekarlo
// Change prefix of entity print options to "list".
//
// Revision 1.12  2000-10-17 01:43:42-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.11  1999-09-08 13:18:03-06  walter
// Add JavaDoc comments.
//
// Revision 1.10  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.9  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.8  1999/02/08  05:11:30  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.7  1998/10/11  17:19:40  ekarlo
// Fix comment.
//
// Revision 1.6  1998/07/12  00:06:32  ekarlo
// Rearrange print.
//
// Revision 1.5  1997/12/26  21:38:39  ekarlo
// Implement paper space - phase 1.
// Implement VIEWPORT.
//
// Revision 1.4  1997/07/21  22:51:37  ekarlo
// Rename from YdxfSecEntities.java to YxxfSecEntities.java.
//
// Revision 1.3  1996/10/26  00:09:46  ekarlo
// Correct color and layer handling.
//
// Revision 1.2  1996/09/26  01:43:16  ekarlo
// Implement ESpace, PSpace, MSpace.
//
// Revision 1.1  1996/07/02  01:58:26  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import java.util.*;


/**
 * ENTITIES section.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfSecEntities
{
    /**
     * Constructor (empty)
     */
    public
    YxxfSecEntities()
    {
    }


    /**
     * Paper space root insert.
     */
    public
    YxxfEntInsert               insPSpace;


    /**
     * Model space root insert.
     */
    public
    YxxfEntInsert               insMSpace;
    
    
    /**
     * List of VIEWPORT entities.
     */
    public
    Vector                      entViewportList = new Vector();

    /**
     * List the contents of the Viewports in the Viewport list.
     */
    public
    void listViewports()
    {
        System.out.println("Viewport size=" + entViewportList.size());
        Enumeration E = entViewportList.elements();
        YxxfEntViewport viewport;

        while (E.hasMoreElements())
        {
            viewport = (YxxfEntViewport)E.nextElement();
            System.out.println("Viewport =====================================");
            System.out.println(viewport);
            System.out.println("Viewport =====================================");
        }
    }

    /**
     * List the number of PaperSpace and ModelSpace entities.
     */
    public
    void listEntities()
    {
        System.out.println("insPSpace.block.size=" + insPSpace.block.size());
        System.out.println("insMSpace.block.size=" + insMSpace.block.size());
    }
}

