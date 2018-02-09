//==============================================================================
// YshxLibBeanInfo.java
//
// BeanInfo for YshxLib
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yshx/YshxLibBeanInfo.java,v 1.5 2003/06/05 11:36:44 ekarlo Exp $
// $Log: YshxLibBeanInfo.java,v $
// Revision 1.5  2003/06/05 11:36:44  ekarlo
// Remove tabs.
//
// Revision 1.4  2003/05/08 09:38:17  ekarlo
// Remove warnings.
//
// Revision 1.3  2003/04/14 12:38:24  ekarlo
// Update source file header for OSI release.
//
// Revision 1.2  2001/10/18 05:16:44  ekarlo
// Change package paths to lower case.
//
// Revision 1.1  2001-10-17 22:32:15-06  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yshx;


import java.beans.*;


public class YshxLibBeanInfo extends java.beans.SimpleBeanInfo
{
    public YshxLibBeanInfo()
    {
    }

    /**
     * Gets a BeanInfo for the superclass of this bean.
     * @return BeanInfo[] containing this bean's superclass BeanInfo
     */
    public BeanInfo[] getAdditionalBeanInfo()
    {
        try
        {
            BeanInfo[] bi = new BeanInfo[1];
            bi[0] = Introspector.getBeanInfo(beanClass.getSuperclass());
            return bi;
        }
        catch (IntrospectionException e)
        {
            throw new Error(e.toString());
        }
    }

    /**
    * Gets the BeanDescriptor for this bean.
    * @return an object of type BeanDescriptor
    * @see java.beans.BeanDescriptor
    */
    public BeanDescriptor getBeanDescriptor()
    {
        BeanDescriptor bd = new BeanDescriptor(beanClass);
        return bd;
    }

    /**
     * Gets an image that may be used to visually represent this bean
     * (in the toolbar, on a form, etc).
     * @param iconKind the type of icon desired, one of: BeanInfo.ICON_MONO_16x16,
     * BeanInfo.ICON_COLOR_16x16, BeanInfo.ICON_MONO_32x32, or BeanInfo.ICON_COLOR_32x32.
     * @return an image for this bean
     * @see BeanInfo#ICON_MONO_16x16
     * @see BeanInfo#ICON_COLOR_16x16
     * @see BeanInfo#ICON_MONO_32x32
     * @see BeanInfo#ICON_COLOR_32x32
     */
    public java.awt.Image getIcon(int nIconKind)
    {
        java.awt.Image img = null;
        return img;
    }

    private final Class beanClass = YshxLib.class;
}
