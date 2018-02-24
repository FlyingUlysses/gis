//==============================================================================
// YxxfTblLayer.java
//
// LAYER table
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfTblLayer.java,v 1.12 2003/05/08 11:24:03 ekarlo Exp $
// $Log: YxxfTblLayer.java,v $
// Revision 1.12  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.11  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.10  2000/10/17 07:43:39  ekarlo
// Change package paths to lower case.
//
// Revision 1.9  1999-09-08 13:18:47-06  walter
// Add JavaDoc comments.
//
// Revision 1.8  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.7  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.6  1999/02/08  05:11:39  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.5  1999/01/28  04:32:33  ekarlo
// Text - phase 4.
//
// Revision 1.4  1997/07/21  22:54:16  ekarlo
// Make fields public for static get.
//
// Revision 1.3  1996/10/26  00:43:44  ekarlo
// Correct color and layer handling.
//
// Revision 1.2  1996/09/26  02:06:31  ekarlo
// 1) Reformat.
// 2) Change variable access.
//
// Revision 1.1  1996/07/02  02:20:22  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * LAYER table.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfTblLayer extends YxxfTblLayerKey
{
    // code   2 - Layer name - (in key class)

    /**
     * code   5 - Handle.
     */
    public
    String                      handle          = null;

    /**
     * code  70 - Standard flags.
     *            In addition to the standard flags, the following values apply to layers<br>
     *            (bit-coded values).
     * <UL>
     *   <LI>1 = If set, layer is frozen, otherwise layer is thawed.
     *   <LI>2 = If set, layer is frozen by default in new viewports.
     *   <LI>4 = If set, layer is locked.
     * <UL>
     */
    public
    int                         flags           = 0;

    /**
     * code  62 - Color number (if negative, layer is Off).
     */
    public
    int                         aci             = YxxfGfxPalette.ACI_WHITE;

    /**
     * code   6 - Linetype name.
     */
    public
    YxxfTblLtype                ltype           = null;


    /**
     * Constructor
     */
    public
    YxxfTblLayer()
    {
        super();
    }


    /**
     * Constructor
     * @param layername The Layer name.
     */
    public
    YxxfTblLayer(String layername)
    {
        super(layername);
    }


    /**
     * Constructor
     * @param layername The Layer name.
     */
    public
    YxxfTblLayer(char[] layername)
    {
        super(layername);
    }


    /**
     * Copy a Layer table into this Layer table.
     * @param lay A Layer table.
     */
    public
    void copyInto(YxxfTblLayer lay)
    {
        super.copyInto(lay);
        lay.handle              = handle;
        lay.flags               = flags;
        lay.aci                 = aci;
        lay.ltype               = ltype;
    }


    /**
     * Stringify this Layer table.
     * @return The Layer table.
     */
    public
    String toString()
    {
        return
              "    YxxfTblLayer name=["             + getName() + "]" +
            "\n    YxxfTblLayer handle=["           + handle + "]" +
            "\n    YxxfTblLayer flags="             + flags +
            "\n    YxxfTblLayer aci="               + aci +
            "\n    YxxfTblLayer ltype=["            + ltype.getName() + "]";
    }
}



