//==============================================================================
// YxxfEntHeader.java
//
// Common entity header items
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfEntHeader.java,v 1.15 2003/05/08 11:24:03 ekarlo Exp $
// $Log: YxxfEntHeader.java,v $
// Revision 1.15  2003/05/08 11:24:03  ekarlo
// Remove warnings.
//
// Revision 1.14  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.13  2001/10/10 03:37:28  ekarlo
// Update copyright.
//
// Revision 1.12  2000-10-17 01:43:55-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.11  1999-09-29 17:04:16-06  walter
// Added JavaDoc comments.
//
// Revision 1.10  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.9  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.8  1999/02/08  05:11:02  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.7  1999/01/28  04:29:02  ekarlo
// Text - phase 4.
//
// Revision 1.6  1998/02/02  21:12:03  ekarlo
// Deactivate header handle.
//
// Revision 1.5  1997/08/30  14:16:10  ekarlo
// Change linetype default.
//
// Revision 1.4  1996/10/26  00:42:38  ekarlo
// Correct color and layer handling.
//
// Revision 1.3  1996/09/26  02:02:31  ekarlo
// Implement proper color activity.
//
// Revision 1.2  1996/09/13  05:58:05  ekarlo
// Use new base entity class structure.
//
// Revision 1.1  1996/07/02  02:20:19  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * Common entity header items.
 */
public class YxxfEntHeader
{
//  // code   5 - Object handle expressed as a hexadecimal string (fixed).
//  public
//  String                      hdr_handle;
//
    /**
     * code   6 - Linetype name.
     *            Set to ltype table reference - default "BYLAYER".
     */
    public
    YxxfTblLtype                hdr_ltype;

    /**
     * code   8 - Layer name.
     *            Set to layer table reference - default "0".
     */
    public
    YxxfTblLayer                hdr_layer;

    /**
     * code  48 - Linetype scale.
     */
    public
    double                      hdr_ltypescale  = 1.0;

    /**
     * code  60 - Entity visibility. Integer value.
     *            Absence or 0 indicates visibility, 1 indicates invisibility.
     */
    public
    boolean                     hdr_visible     = true;

    /**
     * code  62 - AutoCAD Color Index.
     */
    public
    int                         hdr_aci         = YxxfGfxPalette.ACI_BYLAYER;

    /**
     * code  67 - 0 = model space, 1 = paper space.
     */
    public
    int                         hdr_space       = 0;


    /**
     * Copy this header information into another header.
     * @param hdr The target header.
     */
    public
    void copyHeaderInto(YxxfEntHeader hdr)
    {
//      hdr.hdr_handle          = hdr_handle;
        hdr.hdr_ltype           = hdr_ltype;
        hdr.hdr_layer           = hdr_layer;
        hdr.hdr_ltypescale      = hdr_ltypescale;
        hdr.hdr_visible         = hdr_visible;
        hdr.hdr_aci             = hdr_aci;
        hdr.hdr_space           = hdr_space;
    }


    //
    /**
     * Header calculate.
     * @param D The drawing.
     */
    //
    public
    void hdr_calc(Yxxf D)
    {
        // connect ltype
        if (hdr_ltype == null)
            hdr_ltype = D.secTables.findLtype_add(YxxfTblLtypeKey.STR_LTYPENAME__BYLAYER);

        // connect layer
        if (hdr_layer == null)
            hdr_layer = D.secTables.findLayer_add(YxxfTblLayerKey.STR_LAYERNAME__0);
    }


    /**
     * Stringify the header.
     * @return The header description.
     */
    public
    String toString()
    {
        return
              "    hdr_aci="         + hdr_aci +
              ",hdr_ltype.name=["    + ((hdr_ltype != null) ? hdr_ltype.getName() : "hdr_ltype==null") + "]" +
              ",hdr_layer.name=["    + ((hdr_layer != null) ? hdr_layer.getName() : "hdr_layer==null") + "]";
    }
}

