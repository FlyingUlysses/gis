//==============================================================================
// YxxfGfxPalette.java
//
// Graphics palette
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfGfxPalette.java,v 1.13 2003/04/14 12:38:42 ekarlo Exp $
// $Log: YxxfGfxPalette.java,v $
// Revision 1.13  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.12  2002/09/28 08:27:26  ekarlo
// Fix missing method error on MSIE with built in VM.
//
// Revision 1.11  2002-09-25 13:36:26-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.10  2002-09-12 23:39:25-06  ekarlo
// Imprinter development check-in.
//
// Revision 1.9  2002-09-12 15:06:22-06  ekarlo
// Change palette array access.
//
// Revision 1.8  2002-09-12 14:41:13-06  ekarlo
// Imprinter development check-in.
// Add palette types.
//
// Revision 1.7  2000-10-17 01:43:45-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.6  1999-09-29 17:09:37-06  walter
// Added JavaDoc comments.
//
// Revision 1.5  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.4  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.3  1999/01/28  04:29:02  ekarlo
// Text - phase 4.
//
// Revision 1.2  1996/08/14  19:37:50  ekarlo
// Add constants for common colors.
//
// Revision 1.1  1996/07/02  02:20:22  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import java.awt.*;

import com.ysystems.lib.yutil.*;


/**
 * Graphics palette.
 * @author Ed Karlo - Y Systems, LLC
 */

public class YxxfGfxPalette
{
    /**
     * AutoCAD Color Index by block.
     */
    public static final int     ACI_BYBLOCK = 0;
    /**
     * AutoCAD Color Index by layer.
     */
    public static final int     ACI_BYLAYER = 256;

    /**
     * AutoCAD Color Index red.
     */
    public static final int     ACI_RED     = 1;
    /**
     * AutoCAD Color Index yellow.
     */
    public static final int     ACI_YELLOW  = 2;
    /**
     * AutoCAD Color Index green.
     */
    public static final int     ACI_GREEN   = 3;
    /**
     * AutoCAD Color Index cyan.
     */
    public static final int     ACI_CYAN    = 4;
    /**
     * AutoCAD Color Index blue.
     */
    public static final int     ACI_BLUE    = 5;
    /**
     * AutoCAD Color Index magenta.
     */
    public static final int     ACI_MAGENTA = 6;
    /**
     * AutoCAD Color Index white.
     */
    public static final int     ACI_WHITE   = 7;


    /**
     * Color palette.
     */
    public
    Color                       jcolorarr[] = new Color[256];


    /**
     * Palette type UNKNOWN
     */
    public static final int     PALETTETYPE_UNKNOWN         = 0;
    /**
     * Palette type DEFAULT
     */
    public static final int     PALETTETYPE_ACI             = 1;
    /**
     * Palette type DEFAULT_HIGHINV
     */
    public static final int     PALETTETYPE_ACIHIGHINVERSE  = 2;
    /**
     * Palette type SINGLECOLOR
     */
    public static final int     PALETTETYPE_SINGLECOLOR     = 3;

    /**
     * Palette type
     */
    public int                  palettetype =
                                PALETTETYPE_UNKNOWN;


    /**
     * Default Color palette.
     */
    private
    Color                       jcolorarr_aci[] = null;

    /**
     * Default Color palette.
     */
    private
    Color                       jcolorarr_acihighinverse[] = null;

    /**
     * Single Color value.
     */
    private
    Color                       jcolor_singlecolor = null;


    /**
     * Constructor
     */
    protected
    YxxfGfxPalette()
    {
        setPalette_aci();
    }


    /**
     * Constructor
     * @param arg_palettetype type of pallette to create.
     * @param arg_obj optional passed Object depends on type.
     */
    protected
    YxxfGfxPalette(int arg_palettetype, Object arg_obj)
    {
        if (arg_palettetype == PALETTETYPE_ACI)
            setPalette_aci();
        else
        
        if (arg_palettetype == PALETTETYPE_ACIHIGHINVERSE)
            setPalette_acihighinverse();
        else
        
        if (arg_palettetype == PALETTETYPE_SINGLECOLOR)
        {
            if (arg_obj instanceof String)
                setPalette_singlecolor((String)arg_obj);
            else
                setPalette_singlecolor((Color)arg_obj); // Error if not Color
        }
        else

            setPalette_unknown();
    }


    /**
     * Set palette to default
     */
    protected
    void setPalette_aci()
    {
        if (palettetype == PALETTETYPE_ACI)
            return;

        if (jcolorarr_aci == null)
        {
            jcolorarr_aci = new Color[256];
            fillPaletteArray_aci(jcolorarr_aci);
        }

        for (int i = 0; i < 256; i++)
            jcolorarr[i] = jcolorarr_aci[i];

        palettetype = PALETTETYPE_ACI;
    }


    /**
     * Set palette to default with high inverse (for printing)
     */
    protected
    void setPalette_acihighinverse()
    {
        if (palettetype == PALETTETYPE_ACIHIGHINVERSE)
            return;

        if (jcolorarr_acihighinverse == null)
        {
            jcolorarr_acihighinverse = new Color[256];
            fillPaletteArray_aci(jcolorarr_acihighinverse);
            Color col;
            int r, g, b;
            for (int i = 0; i < 256; i++)
            {
                col = jcolorarr_acihighinverse[i];
                if (col == null)
                    continue;

                r = col.getRed();
                g = col.getGreen();
                b = col.getBlue();
                if (r >= 192 && g >= 192 && b >= 192)
                    jcolorarr_acihighinverse[i] = new Color(255 - r, 255 - g, 255 - b);
            }
        }

        for (int i = 0; i < 256; i++)
            jcolorarr[i] = jcolorarr_acihighinverse[i];

        palettetype = PALETTETYPE_ACIHIGHINVERSE;
    }


    /**
     * Set palette to all the same Color
     * @param arg_singlecolor Color value.
     */
    protected
    void setPalette_singlecolor(Color arg_singlecolor_col)
    {
        jcolor_singlecolor = arg_singlecolor_col;

        jcolorarr[0] = null;
        for (int i = 1; i < 256; i++)
            jcolorarr[i] = jcolor_singlecolor;

        palettetype = PALETTETYPE_SINGLECOLOR;
    }


    /**
     * Set palette to all the same Color
     * @param arg_singlecolor Color value.
     */
    protected
    void setPalette_singlecolor(String arg_singlecolor_str)
    {
        Color col = YutilProperties.convertStringToColor(arg_singlecolor_str);
        setPalette_singlecolor(col);
    }


    /**
     * Set palette to unknown (cleared) state
     */
    protected
    void setPalette_unknown()
    {
        for (int i = 0; i < 256; i++)
            jcolorarr[i] = null;

        jcolorarr_aci = null;
        jcolorarr_acihighinverse = null;
        jcolor_singlecolor = null;

        palettetype = PALETTETYPE_UNKNOWN;
    }


    /**
     * Fill palette array with aci values
     * @param parr Palette array to fill.
     */
    protected
    void fillPaletteArray_aci(Color parr[])
    {
        // ToDo: verify colors
        parr[  0] = null;
        parr[  1] = Color.red;
        parr[  2] = Color.yellow;
        parr[  3] = Color.green;
        parr[  4] = Color.cyan;
        parr[  5] = Color.blue;
        parr[  6] = Color.magenta;
        parr[  7] = Color.white;
        parr[  8] = new Color(  0,  76,  76);
        parr[  9] = new Color(192, 192, 192);

        parr[ 10] = new Color(255,   0,   0);
        parr[ 11] = new Color(255, 127, 127);
        parr[ 12] = new Color(165,   0,   0);
        parr[ 13] = new Color(165,  82,  82);
        parr[ 14] = new Color(127,   0,   0);
        parr[ 15] = new Color(127,  63,  63);
        parr[ 16] = new Color( 76,   0,   0);
        parr[ 17] = new Color( 76,  38,  38);
        parr[ 18] = new Color( 38,   0,   0);
        parr[ 19] = new Color( 38,  19,  19);

        parr[ 20] = new Color(255,  63,   0);
        parr[ 21] = new Color(255, 159, 127);
        parr[ 22] = new Color(165,  41,   0);
        parr[ 23] = new Color(165, 103,  82);
        parr[ 24] = new Color(127,  31,   0);
        parr[ 25] = new Color(127,  79,  63);
        parr[ 26] = new Color( 76,  19,   0);
        parr[ 27] = new Color( 76,  48,  38);
        parr[ 28] = new Color( 38,   9,   0);
        parr[ 29] = new Color( 38,  23,  19);

        parr[ 30] = new Color(255, 127,   0);
        parr[ 31] = new Color(255, 191, 127);
        parr[ 32] = new Color(165,  82,   0);
        parr[ 33] = new Color(165, 124,  82);
        parr[ 34] = new Color(127,  63,   0);
        parr[ 35] = new Color(127,  95,  63);
        parr[ 36] = new Color( 76,  38,   0);
        parr[ 37] = new Color( 76,  57,  38);
        parr[ 38] = new Color( 38,  19,   0);
        parr[ 39] = new Color( 38,  28,  19);

        parr[ 40] = new Color(255, 191,   0);
        parr[ 41] = new Color(255, 223, 125);
        parr[ 42] = new Color(165, 124,   0);
        parr[ 43] = new Color(165, 145,  82);
        parr[ 44] = new Color(127,  95,   0);
        parr[ 45] = new Color(127, 111,  63);
        parr[ 46] = new Color( 76,  57,   0);
        parr[ 47] = new Color( 76,  66,  38);
        parr[ 48] = new Color( 38,   0,   0);
        parr[ 49] = new Color( 38,  28,   0);

        parr[ 50] = new Color(255, 255,   0);
        parr[ 51] = new Color(255, 255, 127);
        parr[ 52] = new Color(165, 165,   0);
        parr[ 53] = new Color(165, 165,  82);
        parr[ 54] = new Color(127, 127,   0);
        parr[ 55] = new Color(127, 127,  63);
        parr[ 56] = new Color( 76,  76,   0);
        parr[ 57] = new Color( 76,  76,  38);
        parr[ 58] = new Color( 38,  38,   0);
        parr[ 59] = new Color( 38,  38,  19);

        parr[ 60] = new Color(191, 255,   0);
        parr[ 61] = new Color(223, 255, 127);
        parr[ 62] = new Color(124, 165,   0);
        parr[ 63] = new Color(145, 165,  82);
        parr[ 64] = new Color( 95, 127,   0);
        parr[ 65] = new Color(111, 127,  63);
        parr[ 66] = new Color( 57,  76,   0);
        parr[ 67] = new Color( 66,  76,  38);
        parr[ 68] = new Color( 28,  38,   0);
        parr[ 69] = new Color( 33,  38,  19);

        parr[ 70] = new Color(127, 255,   0);
        parr[ 71] = new Color(191, 255, 127);
        parr[ 72] = new Color( 82, 165,   0);
        parr[ 73] = new Color(124, 165,  82);
        parr[ 74] = new Color( 63, 127,   0);
        parr[ 75] = new Color( 95, 127,  63);
        parr[ 76] = new Color( 38,  76,   0);
        parr[ 77] = new Color( 57,  76,  38);
        parr[ 78] = new Color( 19,  38,   0);
        parr[ 79] = new Color( 28,  38,  19);

        parr[ 80] = new Color( 63, 255,   0);
        parr[ 81] = new Color(159, 255, 127);
        parr[ 82] = new Color( 41, 165,   0);
        parr[ 83] = new Color(103, 165,  82);
        parr[ 84] = new Color( 31, 127,   0);
        parr[ 85] = new Color( 79, 127,  63);
        parr[ 86] = new Color( 19,  76,   0);
        parr[ 87] = new Color( 47,  76,  38);
        parr[ 88] = new Color(  9,  38,   0);
        parr[ 89] = new Color( 23,  38,  19);

        parr[ 90] = new Color(  0, 255,   0);
        parr[ 91] = new Color(127, 255, 127);
        parr[ 92] = new Color(  0, 165,   0);
        parr[ 93] = new Color( 82, 165,  82);
        parr[ 94] = new Color(  0, 127,   0);
        parr[ 95] = new Color( 63, 127,  63);
        parr[ 96] = new Color(  0,  76,   0);
        parr[ 97] = new Color( 38,  76,  38);
        parr[ 98] = new Color(  0,  38,   0);
        parr[ 99] = new Color( 19,  38,  19);

        parr[100] = new Color(  0, 255,  63);
        parr[101] = new Color(127, 255, 159);
        parr[102] = new Color(  0, 165,  41);
        parr[103] = new Color( 82, 165, 103);
        parr[104] = new Color(  0, 127,  31);
        parr[105] = new Color( 63, 127,  79);
        parr[106] = new Color(  0,  76,  19);
        parr[107] = new Color( 38,  76,  47);
        parr[108] = new Color(  0,  38,   9);
        parr[109] = new Color( 19,  38,  23);

        parr[110] = new Color(  0, 255, 127);
        parr[111] = new Color(127, 255, 191);
        parr[112] = new Color(  0, 165,  82);
        parr[113] = new Color( 82, 165, 124);
        parr[114] = new Color(  0, 127,  63);
        parr[115] = new Color( 63, 127,  95);
        parr[116] = new Color(  0,  76,  38);
        parr[117] = new Color( 38,  76,  57);
        parr[118] = new Color(  0,  38,  19);
        parr[119] = new Color( 19,  38,  28);

        parr[120] = new Color(  0, 255, 191);
        parr[121] = new Color(127, 255, 223);
        parr[122] = new Color(  0, 165, 124);
        parr[123] = new Color( 82, 165, 145);
        parr[124] = new Color(  0, 127,  95);
        parr[125] = new Color( 63, 127, 111);
        parr[126] = new Color(  0,  76,  57);
        parr[127] = new Color( 38,  76,  66);
        parr[128] = new Color(  0,  38,  28);
        parr[129] = new Color( 19,  38,  33);

        parr[130] = new Color(  0, 255, 191);
        parr[131] = new Color(127, 255, 255);
        parr[132] = new Color(  0, 165, 165);
        parr[133] = new Color( 82, 165, 165);
        parr[134] = new Color(  0, 127, 127);
        parr[135] = new Color( 63, 127, 127);
        parr[136] = new Color(  0,  76,  76);
        parr[137] = new Color( 38,  76,  76);
        parr[138] = new Color(  0,  38,  38);
        parr[139] = new Color( 19,  38,  38);

        parr[140] = new Color(  0, 191, 255);
        parr[141] = new Color(127, 223, 255);
        parr[142] = new Color(  0, 124, 165);
        parr[143] = new Color( 82, 145, 165);
        parr[144] = new Color(  0,  95, 127);
        parr[145] = new Color( 63, 111, 127);
        parr[146] = new Color(  0,  57,  76);
        parr[147] = new Color( 38,  66,  76);
        parr[148] = new Color(  0,  28,  38);
        parr[149] = new Color( 19,  33,  38);

        parr[150] = new Color(  0, 127, 255);
        parr[151] = new Color(127, 191, 255);
        parr[152] = new Color(  0,  82, 165);
        parr[153] = new Color( 82, 124, 165);
        parr[154] = new Color(  0,  63, 127);
        parr[155] = new Color( 63,  95, 127);
        parr[156] = new Color(  0,  38,  76);
        parr[157] = new Color( 38,  57,  76);
        parr[158] = new Color(  0,  19,  38);
        parr[159] = new Color( 19,  28,  38);

        parr[160] = new Color(  0,  63, 255);
        parr[161] = new Color(127, 159, 255);
        parr[162] = new Color(  0,  41, 165);
        parr[163] = new Color( 82, 103, 165);
        parr[164] = new Color(  0,  31, 127);
        parr[165] = new Color( 63,  79, 127);
        parr[166] = new Color(  0,  19,  76);
        parr[167] = new Color( 38,  47,  76);
        parr[168] = new Color(  0,   9,  38);
        parr[169] = new Color( 19,  23,  38);

        parr[170] = new Color(  0,   0, 255);
        parr[171] = new Color(127, 127, 255);
        parr[172] = new Color(  0,   0, 165);
        parr[173] = new Color( 82,  82, 165);
        parr[174] = new Color(  0,   0, 127);
        parr[175] = new Color( 63,  63, 127);
        parr[176] = new Color(  0,   0,  76);
        parr[177] = new Color( 38,  38,  76);
        parr[178] = new Color(  0,   0,  38);
        parr[179] = new Color( 19,  19,  38);

        parr[180] = new Color( 63,   0, 255);
        parr[181] = new Color(159, 127, 255);
        parr[182] = new Color( 41,   0, 165);
        parr[183] = new Color(103,  82, 165);
        parr[184] = new Color( 31,   0, 127);
        parr[185] = new Color( 79,  63, 127);
        parr[186] = new Color( 19,   0,  76);
        parr[187] = new Color( 47,  38,  76);
        parr[188] = new Color(  9,   0,  38);
        parr[189] = new Color( 23,  19,  38);

        parr[190] = new Color(127,   0, 255);
        parr[191] = new Color(191, 127, 255);
        parr[192] = new Color( 82,   0, 165);
        parr[193] = new Color(124,  82, 165);
        parr[194] = new Color( 63,   0, 127);
        parr[195] = new Color( 95,  63, 127);
        parr[196] = new Color( 38,   0,  76);
        parr[197] = new Color( 57,  38,  76);
        parr[198] = new Color( 19,   0,  38);
        parr[199] = new Color( 28,  19,  38);

        parr[200] = new Color(191,   0, 255);
        parr[201] = new Color(223, 127, 255);
        parr[202] = new Color(124,   0, 165);
        parr[203] = new Color(145,  82, 165);
        parr[204] = new Color( 95,   0, 127);
        parr[205] = new Color(111,  63, 127);
        parr[206] = new Color( 57,   0,  76);
        parr[207] = new Color( 66,  38,  76);
        parr[208] = new Color( 28,   0,  38);
        parr[209] = new Color( 33,  19,  38);

        parr[210] = new Color(255,   0, 255);
        parr[211] = new Color(255, 127, 255);
        parr[212] = new Color(165,   0, 165);
        parr[213] = new Color(165,  82, 165);
        parr[214] = new Color(127,   0, 127);
        parr[215] = new Color(127,  63, 127);
        parr[216] = new Color( 76,   0,  76);
        parr[217] = new Color( 76,  38,  76);
        parr[218] = new Color( 38,   0,  38);
        parr[219] = new Color( 38,  19,  38);

        parr[220] = new Color(255,   0, 191);
        parr[221] = new Color(255, 127, 223);
        parr[222] = new Color(165,   0, 124);
        parr[223] = new Color(165,  82, 145);
        parr[224] = new Color(127,   0,  95);
        parr[225] = new Color(127,  63, 111);
        parr[226] = new Color( 76,   0,  57);
        parr[227] = new Color( 76,  38,  66);
        parr[228] = new Color( 38,   0,  28);
        parr[229] = new Color( 38,  19,  33);

        parr[230] = new Color(255,   0, 127);
        parr[231] = new Color(255, 127, 191);
        parr[232] = new Color(165,   0,  82);
        parr[233] = new Color(165,  82, 124);
        parr[234] = new Color(127,   0,  63);
        parr[235] = new Color(127,  63,  95);
        parr[236] = new Color( 76,   0,  38);
        parr[237] = new Color( 76,  38,  57);
        parr[238] = new Color( 38,   0,  19);
        parr[239] = new Color( 38,  19,  28);

        parr[240] = new Color(255,   0,  63);
        parr[241] = new Color(255, 127, 159);
        parr[242] = new Color(165,   0,  41);
        parr[243] = new Color(165,  82, 103);
        parr[244] = new Color(127,   0,  31);
        parr[245] = new Color(127,  63,  79);
        parr[246] = new Color( 76,   0,  19);
        parr[247] = new Color( 76,  38,  47);
        parr[248] = new Color( 38,   0,   9);
        parr[249] = new Color( 38,  19,  23);

        parr[250] = new Color( 84,  84,  84);
        parr[251] = new Color(118, 118, 118);
        parr[252] = new Color(160, 160, 160);
        parr[253] = new Color(192, 192, 192);
        parr[254] = new Color(222, 200, 222);
        parr[255] = new Color(255, 255, 255);
    }


    /**
     * Get palettetype
     */
    protected
    int getPaletteType()
    {
        return palettetype;
    }


    /**
     * Get jcolor_singlecolor
     */
    protected
    Color getSingleColor()
    {
        return jcolor_singlecolor;
    }


    /**
     * Get jcolorarr
     */
    protected
    Color[] getPaletteArray()
    {
        return jcolorarr;
    }
}

