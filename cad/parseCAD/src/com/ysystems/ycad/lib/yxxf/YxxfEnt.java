//==============================================================================
// YxxfEnt.java
//
// Entity XXF class interface
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfEnt.java,v 1.7 2003/04/14 12:38:42 ekarlo Exp $
// $Log: YxxfEnt.java,v $
// Revision 1.7  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.6  2000/10/17 07:44:00  ekarlo
// Change package paths to lower case.
//
// Revision 1.5  1999-09-28 11:11:27-06  walter
// Add JavaDoc comments.
//
// Revision 1.4  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.3  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.2  1999/02/08  05:10:53  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.1  1996/07/02  02:20:19  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * Entity XXF class interface.
 * @author Ed Karlo - Y Systems, LLC
 */
public interface YxxfEnt
{
    public
    void draw(YxxfGfxContext gc);

    public
    void calc(Yxxf D);
}



