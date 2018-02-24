//==============================================================================
// YdxfGetSecBlocks.java
//
// Get BLOCKS section
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfGetSecBlocks.java,v 1.12 2003/05/08 09:38:16 ekarlo Exp $
// $Log: YdxfGetSecBlocks.java,v $
// Revision 1.12  2003/05/08 09:38:16  ekarlo
// Remove warnings.
//
// Revision 1.11  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.10  2000/10/17 07:44:21  ekarlo
// Change package paths to lower case.
//
// Revision 1.9  1999-09-22 22:58:22-06  walter
// Added JavaDoc comments.
//
// Revision 1.8  1999-08-10 08:02:41-06  ekarlo
// Remove unused var warning.
//
// Revision 1.7  1999/07/09  20:09:43  ekarlo
// Rearrange package names.
//
// Revision 1.6  1999/06/27  08:28:14  ekarlo
// Rename class.
//
// Revision 1.5  1999/06/20  22:32:38  ekarlo
// Rearrange package names.
//
// Revision 1.4  1999/06/15  04:51:35  ekarlo
// User Interface - phase 1.
//
// Revision 1.3  1999/02/08  04:55:09  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.2  1998/12/22  14:43:16  ekarlo
// Renames - add "Get" to name.
//
// Revision 1.1  1997/07/21  22:30:27  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


/**
 * Get BLOCKS section.
 * This class may not be instantiated.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YdxfGetSecBlocks
{
    /**
     * Constructor - may not be instantiated.
     */
    private 
    YdxfGetSecBlocks()
    {
    }


    /**
     * Get Doing Blocks Section.
     * @param getbfr The buffer containing all possible types of
     * drawing information. TODO 
     */
    static public
    void get(YdxfGetBuffer getbfr)
    {
//      Yxxf D = getbfr.getDrawing();

        getbfr.get_doing = YdxfGetBuffer.GET_DOING_BLOCKS_SECTION;
        getbfr.currEntBlock = null; // determined by block entity
        YdxfGetSecEntities.get_entities(getbfr);
    }
}

