//==============================================================================
// YxxfSecBlocks.java
//
// BLOCKS section
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfSecBlocks.java,v 1.15 2003/04/14 12:38:42 ekarlo Exp $
// $Log: YxxfSecBlocks.java,v $
// Revision 1.15  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.14  2002/09/29 05:20:56  ekarlo
// Change prefix of entity print options to "list".
//
// Revision 1.13  2000-10-17 01:43:43-06  ekarlo
// Change package paths to lower case.
//
// Revision 1.12  1999-09-08 13:19:37-06  walter
// Add JavaDoc comments.
//
// Revision 1.11  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.10  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.9  1999/02/08  05:11:21  ekarlo
// Improve table and keyword lookups.
//
// Revision 1.8  1999/01/28  04:31:28  ekarlo
// Text - phase 4.
//
// Revision 1.7  1998/10/11  17:19:22  ekarlo
// Fix comment.
//
// Revision 1.6  1997/07/21  22:51:37  ekarlo
// Rename from YdxfSecBlocks.java to YxxfSecBlocks.java.
//
// Revision 1.5  1996/10/26  00:08:15  ekarlo
// Change block list print.
//
// Revision 1.4  1996/09/26  01:42:32  ekarlo
// Change lookups to use supertype.
//
// Revision 1.3  1996/07/30  06:24:19  ekarlo
// Use point class.
//
// Revision 1.2  1996/07/02  04:48:41  ekarlo
// Add basepnt to print blocks list.
//
// Revision 1.1  1996/07/02  01:58:27  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


import java.util.*;


/**
 * BLOCKS section.
 * @author Ed Karlo - Y Systems, LLC
 */
public class YxxfSecBlocks
{
    /**
     * A collection of Drawing Blocks.
     */
    public
    Hashtable                   tblBlocks       = new Hashtable();

    /**
     * Add a Drawing Block to the collection of Drawing Blocks.
     * @param blk An entity block.
     */
    public
    void addBlock(YxxfEntBlock blk)
    {
        tblBlocks.put(blk, blk);
    }


    /**
     * Find a Drawing Block in the collection of Drawing Blocks.
     * @param name A block name.
     * @return An entity block.
     */
    public
    YxxfEntBlock findBlock(String name)
    {
        return (YxxfEntBlock)tblBlocks.get(new YxxfEntBlock(name));
    }


    /**
     * Find a Drawing Block in the collection or create one if it doesn't exist.
     * @param name A block name.
     * @return A entity block.
     */
    public
    YxxfEntBlock findBlock_add(String name)
    {
        YxxfEntBlock foundblock = (YxxfEntBlock)tblBlocks.get(new YxxfEntBlock(name));

        if (foundblock != null) // found - return it
            return foundblock;

        // not found - create a new block with default values
        foundblock = new YxxfEntBlock(name);
        tblBlocks.put(foundblock, foundblock); // block is it's own key
        return foundblock;
    }


    /**
     * Find a Drawing Block in the collection.
     * @param name A block name.
     * @return A entity block.
     */
    public
    YxxfEntBlock findBlock(char[] name)
    {
        return (YxxfEntBlock)tblBlocks.get(new YxxfEntBlock(name));
    }


    /**
     * Find a Drawing Block in the collection or create one if it doesn't exist.
     * @param name A block name.
     * @return An entity block.
     */
    public
    YxxfEntBlock findBlock_add(char[] name)
    {
        YxxfEntBlock foundblock = (YxxfEntBlock)tblBlocks.get(new YxxfEntBlock(name));

        if (foundblock != null) // found - return it
            return foundblock;

        // not found - create a new block with default values
        foundblock = new YxxfEntBlock(name);
        tblBlocks.put(foundblock, foundblock); // block is it's own key
        return foundblock;
    }


    /**
     * Find a Drawing Block in the collection.
     * @param block An entity block.
     * @return An entity block.
     */
    public
    YxxfEntBlock findBlock(YxxfEntBlock block)
    {
        return (YxxfEntBlock)tblBlocks.get(block);
    }


    /**
     * Find a Drawing Block in the collection or create one if it doesn't exist.
     * @param block An entity block.
     * @return An entity block.
     */
    public
    YxxfEntBlock findBlock_add(YxxfEntBlock block)
    {
        YxxfEntBlock foundblock = (YxxfEntBlock)tblBlocks.get(block);

        if (foundblock != null) // found - return it
            return foundblock;

        // not found - create a new block with default values
        foundblock = new YxxfEntBlock(block.getBlockname2());
        tblBlocks.put(foundblock, foundblock); // block is it's own key
        return foundblock;
    }


    /**
     * List the Drawing Blocks in the collection.
     */
    public synchronized
    void listBlocks()
    {
        Enumeration E = tblBlocks.elements();
        YxxfEntBlock block;

        System.out.println("secBlocks.size=" + tblBlocks.size());

        while (E.hasMoreElements())
        {
            block = (YxxfEntBlock)E.nextElement();
            System.out.println("Block name=[" + block.getBlockname2() + "],basepnt=[" +
                block.basepnt.x + ":" + block.basepnt.y + ":" + block.basepnt.z + "],size=" + block.size());
        }
    }
}

