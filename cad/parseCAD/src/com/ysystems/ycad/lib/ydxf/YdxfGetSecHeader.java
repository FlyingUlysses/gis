//==============================================================================
// YdxfGetSecHeader.java
//
// Get HEADER section
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/ydxf/YdxfGetSecHeader.java,v 1.14 2003/04/14 12:37:55 ekarlo Exp $
// $Log: YdxfGetSecHeader.java,v $
// Revision 1.14  2003/04/14 12:37:55  ekarlo
// Update source file header for OSI release.
//
// Revision 1.13  2001/10/18 12:26:42  ekarlo
// Remove keyword value compare method so that only the value return methods
// get the value part of the code/value pair.
//
// Revision 1.12  2000-10-17 01:44:20-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.11  1999-09-22 22:57:30-06  walter
// Added JavaDoc comments.
//
// Revision 1.10  1999-08-07 15:03:51-06  ekarlo
// Add $SPLFRAME.
//
// Revision 1.9  1999/07/09  20:09:43  ekarlo
// Rearrange package names.
//
// Revision 1.8  1999/06/27  08:28:14  ekarlo
// Rename class.
//
// Revision 1.7  1999/06/20  22:32:38  ekarlo
// Rearrange package names.
//
// Revision 1.6  1999/02/08  04:55:09  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.5  1999/01/28  04:35:19  ekarlo
// Text - phase 4.
//
// Revision 1.4  1998/12/22  14:43:27  ekarlo
// Renames - add "Get" to name.
//
// Revision 1.3  1998/07/12  00:02:23  ekarlo
// Add FILLMODE.
//
// Revision 1.2  1998/02/12  17:45:12  ekarlo
// Add LTSCALE.
//
// Revision 1.1  1997/07/21  22:30:27  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.ydxf;


import com.ysystems.ycad.lib.yxxf.*;


/**
 * Get HEADER section.
 * This class may not be instantiated.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YdxfGetSecHeader
{
    /**
     * Constructor - may not be instantiated.
     */
    private // Defeat instantiation
    YdxfGetSecHeader()
    {
    }


    /**
     * Get Header Section info from the Get Buffer. TODO
     * @param getbfr The buffer containing all possible types of 
     * drawing information.
     */
    static public
    void get(YdxfGetBuffer getbfr)
    {
        YxxfSecHeader secHeader = getbfr.getDrawing().secHeader;
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
                else
                {
                    getbfr.get();
                    continue;
                }
            }

            if (getbfr.codEquals(9))
            {
                kwval = getbfr.keywordValue();



                if (kwval == YdxfKeyword.KW_I_$ACADVER)
                {
                    getbfr.get();
                    while (true)
                    {
                        if (getbfr.codEquals(0) || getbfr.codEquals(9))
                            break;

                        if (getbfr.codEquals(1))
                        {
                            secHeader.acadver = getbfr.stringValue();
                        }

                        getbfr.get();
                    }
                    continue;
                } else



                if (kwval == YdxfKeyword.KW_I_$AUNITS)
                {
                    getbfr.get();
                    while (true)
                    {
                        if (getbfr.codEquals(0) || getbfr.codEquals(9))
                            break;

                        if (getbfr.codEquals(70))
                        {
                            secHeader.aunits = getbfr.intValue();
                        }

                        getbfr.get();
                    }
                    continue;
                } else



                if (kwval == YdxfKeyword.KW_I_$CECOLOR)
                {
                    getbfr.get();
                    while (true)
                    {
                        if (getbfr.codEquals(0) || getbfr.codEquals(9))
                            break;

                        if (getbfr.codEquals(62))
                        {
                            secHeader.cecolor = getbfr.intValue();
                        }

                        getbfr.get();
                    }
                    continue;
                } else



                if (kwval == YdxfKeyword.KW_I_$CELTYPE)
                {
                    getbfr.get();
                    while (true)
                    {
                        if (getbfr.codEquals(0) || getbfr.codEquals(9))
                            break;

                        if (getbfr.codEquals(6))
                        {
                            secHeader.celtype = getbfr.stringValue();
                        }

                        getbfr.get();
                    }
                    continue;
                } else



                if (kwval == YdxfKeyword.KW_I_$CLAYER)
                {
                    getbfr.get();
                    while (true)
                    {
                        if (getbfr.codEquals(0) || getbfr.codEquals(9))
                            break;

                        if (getbfr.codEquals(8))
                        {
                            secHeader.clayer = getbfr.stringValue();
                        }

                        getbfr.get();
                    }
                    continue;
                } else



                if (kwval == YdxfKeyword.KW_I_$EXTMIN)
                {
                    getbfr.get();
                    while (true)
                    {
                        if (getbfr.codEquals(0) || getbfr.codEquals(9))
                            break;

                        if (getbfr.codEquals(10))
                        {
                            secHeader.extmin.x = getbfr.doubleValue();
                        } else

                        if (getbfr.codEquals(20))
                        {
                            secHeader.extmin.y = getbfr.doubleValue();
                        } else

                        if (getbfr.codEquals(30))
                        {
                            secHeader.extmin.z = getbfr.doubleValue();
                        }

                        getbfr.get();
                    }
                    continue;
                } else



                if (kwval == YdxfKeyword.KW_I_$EXTMAX)
                {
                    getbfr.get();
                    while (true)
                    {
                        if (getbfr.codEquals(0) || getbfr.codEquals(9))
                            break;

                        if (getbfr.codEquals(10))
                        {
                            secHeader.extmax.x = getbfr.doubleValue();
                        } else

                        if (getbfr.codEquals(20))
                        {
                            secHeader.extmax.y = getbfr.doubleValue();
                        } else

                        if (getbfr.codEquals(30))
                        {
                            secHeader.extmax.z = getbfr.doubleValue();
                        }

                        getbfr.get();
                    }
                    continue;
                }



                if (kwval == YdxfKeyword.KW_I_$FILLMODE)
                {
                    getbfr.get();
                    while (true)
                    {
                        if (getbfr.codEquals(0) || getbfr.codEquals(9))
                            break;

                        if (getbfr.codEquals(70))
                        {
                            secHeader.fillmode = getbfr.intValue();
                        }

                        getbfr.get();
                    }
                    continue;
                } else



                if (kwval == YdxfKeyword.KW_I_$LIMMIN)
                {
                    getbfr.get();
                    while (true)
                    {
                        if (getbfr.codEquals(0) || getbfr.codEquals(9))
                            break;

                        if (getbfr.codEquals(10))
                        {
                            secHeader.limmin.x = getbfr.doubleValue();
                        } else

                        if (getbfr.codEquals(20))
                        {
                            secHeader.limmin.y = getbfr.doubleValue();
                        }

                        getbfr.get();
                    }
                    continue;
                } else



                if (kwval == YdxfKeyword.KW_I_$LIMMAX)
                {
                    getbfr.get();
                    while (true)
                    {
                        if (getbfr.codEquals(0) || getbfr.codEquals(9))
                            break;

                        if (getbfr.codEquals(10))
                        {
                            secHeader.limmax.x = getbfr.doubleValue();
                        } else

                        if (getbfr.codEquals(20))
                        {
                            secHeader.limmax.y = getbfr.doubleValue();
                        }

                        getbfr.get();
                    }
                    continue;
                } else



                if (kwval == YdxfKeyword.KW_I_$LTSCALE)
                {
                    getbfr.get();
                    while (true)
                    {
                        if (getbfr.codEquals(0) || getbfr.codEquals(9))
                            break;

                        if (getbfr.codEquals(40))
                        {
                            secHeader.ltscale = getbfr.doubleValue();
                        }

                        getbfr.get();
                    }
                    continue;
                } else



                if (kwval == YdxfKeyword.KW_I_$SPLFRAME)
                {
                    getbfr.get();
                    while (true)
                    {
                        if (getbfr.codEquals(0) || getbfr.codEquals(9))
                            break;

                        if (getbfr.codEquals(70))
                        {
                            secHeader.splframe = getbfr.intValue();
                        }

                        getbfr.get();
                    }
                    continue;
                } else



                if (kwval == YdxfKeyword.KW_I_$TILEMODE)
                {
                    getbfr.get();
                    while (true)
                    {
                        if (getbfr.codEquals(0) || getbfr.codEquals(9))
                            break;

                        if (getbfr.codEquals(70))
                        {
                            secHeader.tilemode = getbfr.intValue();
                        }

                        getbfr.get();
                    }
                    continue;
                }



            }
            getbfr.get();
        }
    }
}

