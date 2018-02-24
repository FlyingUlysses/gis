//==============================================================================
// YcadvPaneBeanInfo.java
//
// BeanInfo for YcadvPane
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/app/ycadv/YcadvPaneBeanInfo.java,v 1.5 2003/06/05 11:36:43 ekarlo Exp $
// $Log: YcadvPaneBeanInfo.java,v $
// Revision 1.5  2003/06/05 11:36:43  ekarlo
// Remove tabs.
//
// Revision 1.4  2003/05/08 08:46:20  ekarlo
// Remove warnings.
//
// Revision 1.3  2003/04/14 12:37:26  ekarlo
// Update source file header for OSI release.
//
// Revision 1.2  2001/10/18 05:19:49  ekarlo
// Change package paths to lower case.
//
// Revision 1.1  2001-10-17 22:10:54-06  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.app.ycadv;


import java.beans.*;


public class YcadvPaneBeanInfo extends java.beans.SimpleBeanInfo
{
    private final Class beanYcadvPaneClass = YcadvPane.class;


    public YcadvPaneBeanInfo()
    {
        System.out.println("YcadvPaneBeanInfo() constuctor");
    }


    /**
    * Gets the BeanDescriptor for this bean.
    * Knowledge about the class and customizer.
    * Superclass returns null.
    * @return an object of type BeanDescriptor
    * @see java.beans.BeanDescriptor
    */
    public BeanDescriptor getBeanDescriptor()
    {
        BeanDescriptor bd = new BeanDescriptor(beanYcadvPaneClass);
        System.out.println("YcadvPaneBeanInfo.getBeanDescriptor,bd=[" + bd + "]");
        return bd;
    }


    /**
    * Gets the PropertyDescriptor[] for this bean.
    * Knowledge about the properties.
    * Superclass returns null.
    * @return an object of type PropertyDescriptor[]
    * @see java.beans.PropertyDescriptor
    */
    public PropertyDescriptor[] getPropertyDescriptors() {       
        try {
            PropertyDescriptor booleanValue = new PropertyDescriptor("booleanValue", beanYcadvPaneClass);
            booleanValue.setConstrained(false);
            booleanValue.setBound(false);


            PropertyDescriptor baseURL = new PropertyDescriptor("baseURL", beanYcadvPaneClass);
            baseURL.setConstrained(false);
            baseURL.setBound(false);


            PropertyDescriptor src = new PropertyDescriptor("src", beanYcadvPaneClass);
            src.setConstrained(false);
            src.setBound(false);

            PropertyDescriptor srcFile = new PropertyDescriptor("srcFile", beanYcadvPaneClass);
            srcFile.setConstrained(false);
            srcFile.setBound(false);

            PropertyDescriptor srcURL = new PropertyDescriptor("srcURL", beanYcadvPaneClass);
            srcURL.setConstrained(false);
            srcURL.setBound(false);


            PropertyDescriptor printHeader = new PropertyDescriptor("printHeader", beanYcadvPaneClass);
            printHeader.setConstrained(false);
            printHeader.setBound(false);

            PropertyDescriptor printLayers = new PropertyDescriptor("printLayers", beanYcadvPaneClass);
            printLayers.setConstrained(false);
            printLayers.setBound(false);

            PropertyDescriptor printLtypes = new PropertyDescriptor("printLtypes", beanYcadvPaneClass);
            printLtypes.setConstrained(false);
            printLtypes.setBound(false);

            PropertyDescriptor printVports = new PropertyDescriptor("printVports", beanYcadvPaneClass);
            printVports.setConstrained(false);
            printVports.setBound(false);

            PropertyDescriptor printStyles = new PropertyDescriptor("printStyles", beanYcadvPaneClass);
            printStyles.setConstrained(false);
            printStyles.setBound(false);

            PropertyDescriptor printBlocks = new PropertyDescriptor("printBlocks", beanYcadvPaneClass);
            printBlocks.setConstrained(false);
            printBlocks.setBound(false);

            PropertyDescriptor printEntities = new PropertyDescriptor("printEntities", beanYcadvPaneClass);
            printEntities.setConstrained(false);
            printEntities.setBound(false);

            PropertyDescriptor drawDspMinMax = new PropertyDescriptor("drawDspMinMax", beanYcadvPaneClass);
            drawDspMinMax.setConstrained(false);
            drawDspMinMax.setBound(false);

            PropertyDescriptor drawExtMinMax = new PropertyDescriptor("drawExtMinMax", beanYcadvPaneClass);
            drawExtMinMax.setConstrained(false);
            drawExtMinMax.setBound(false);

            PropertyDescriptor drawLimMinMax = new PropertyDescriptor("drawLimMinMax", beanYcadvPaneClass);
            drawLimMinMax.setConstrained(false);
            drawLimMinMax.setBound(false);


            PropertyDescriptor rv[] = { booleanValue,
                                        baseURL,
                                        src, srcFile, srcURL,
                                        printHeader, printLayers, printLtypes, printVports, printStyles, printBlocks, printEntities,
                                        drawDspMinMax, drawExtMinMax, drawLimMinMax };
            System.out.println("YcadvPaneBeanInfo.getPropertyDescriptors,rv=[" + rv + "]");
            return rv;
        } catch (IntrospectionException e) {
             throw new Error(e.toString());
        }
    }


    /**
    * Gets the default property index for this bean.
    * Superclass returns -1.
    * @return int index
    * @see java.beans.SimpleBeanInfo
    */
    public int getDefaultPropertyIndex()
    {
        System.out.println("YcadvPaneBeanInfo.getDefaultPropertyIndex=" + super.getDefaultPropertyIndex());
        return super.getDefaultPropertyIndex();
    }


    /**
    * Gets the EventSetDescriptor[] for this bean.
    * Knowledge about the event sets.
    * Superclass returns null.
    * @return an object of type EventSetDescriptor[]
    * @see java.beans.EventSetDescriptor
    */
    public EventSetDescriptor[] getEventSetDescriptors() {       
        System.out.println("YcadvPaneBeanInfo.getEventSetDescriptors=[" + super.getEventSetDescriptors() + "]");
        return super.getEventSetDescriptors();
    }


    /**
    * Gets the default event index for this bean.
    * Superclass returns -1.
    * @return int index
    * @see java.beans.SimpleBeanInfo
    */
    public int getDefaultEventIndex()
    {
        System.out.println("YcadvPaneBeanInfo.getDefaultEventIndex=" + super.getDefaultEventIndex());
        return super.getDefaultEventIndex();
    }


    /**
    * Gets the MethodDescriptor[] for this bean.
    * Knowledge about methods.
    * Superclass returns null.
    * @return an object of type MethodDescriptor[]
    * @see java.beans.MethodDescriptor
    */
    public MethodDescriptor[] getMethodDescriptors() {       
        System.out.println("YcadvPaneBeanInfo.getMethodDescriptors=[" + super.getMethodDescriptors() + "]");
        return super.getMethodDescriptors();
    }


    /**
    * Gets a BeanInfo for the superclass of this bean.
    * Superclass returns null.
    * @return BeanInfo[] containing this bean's superclass BeanInfo
    */
    public BeanInfo[] getAdditionalBeanInfo()
    {
        try
        {
            BeanInfo[] bi = new BeanInfo[1];
            bi[0] = Introspector.getBeanInfo(beanYcadvPaneClass.getSuperclass());
            System.out.println("YcadvPaneBeanInfo.getAdditionalBeanInfo,bi=[" + bi + "]");
            return bi;
        }
        catch (IntrospectionException e)
        {
            throw new Error(e.toString());
        }
    }


    /**
     * Gets an image that may be used to visually represent this bean
     * (in the toolbar, on a form, etc).
     * Superclass returns null.
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
//      java.awt.Image img = null;
//      return img;
        System.out.println("YcadvPaneBeanInfo.getIcon=" + nIconKind);
        return super.getIcon(nIconKind);
    }


    /**
     * This is a utility method to help in loading icon images.
     * It takes the name of a resource file associated with the
     * current object's class file and loads an image object
     * from that file.  Typically images will be GIFs.
     * @param resourceName  A pathname relative to the directory
     *      holding the class file of the current class.  For example,
     *      "wombat.gif".
     * @return  an image object.  May be null if the load failed.
     */
    public java.awt.Image loadImage(String resourceName)
    {
        System.out.println("YcadvPaneBeanInfo.loadImage=[" + resourceName + "]");
        return super.loadImage(resourceName);
    }
}
