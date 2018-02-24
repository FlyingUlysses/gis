//==============================================================================
// YxxfGfxMatrix.java
//
// 3D Transformation Matrix
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
// $Header: /cvsroot/ycad/ycad/src/com/ysystems/ycad/lib/yxxf/YxxfGfxMatrix.java,v 1.16 2003/04/14 12:38:42 ekarlo Exp $
// $Log: YxxfGfxMatrix.java,v $
// Revision 1.16  2003/04/14 12:38:42  ekarlo
// Update source file header for OSI release.
//
// Revision 1.15  2000/10/17 07:43:46  ekarlo
// Change package paths to lower case.
//
// Revision 1.14  1999-09-29 17:06:04-06  walter
// Added JavaDoc comments.
//
// Revision 1.13  1999-07-09 14:10:50-06  ekarlo
// Rearrange package names.
//
// Revision 1.12  1999/06/20  22:34:10  ekarlo
// Rearrange package names.
//
// Revision 1.11  1998/08/21  20:21:55  ekarlo
// Eliminate use of global work arrays in matrix calculations
// and use local doubles instead of arrays.
//
// Revision 1.10  1998/08/20  23:47:04  ekarlo
// Change structure of matrix from 4 x 4 array to 16 doubles.
//
// Revision 1.9  1997/12/13  18:59:21  ekarlo
// Move work matrix arrays out of YxxfGfxMatrix Objects.
//
// Revision 1.8  1997/12/07  20:06:28  ekarlo
// Optimize by eliminating use of local temp matrices and
// removing unneeded assignments.
// [Needs checking]
//
// Revision 1.7  1997/08/02  18:28:06  ekarlo
// Unwind loops.
//
// Revision 1.6  1996/09/26  02:05:44  ekarlo
// Add constructor to create matrix from existing matrix.
//
// Revision 1.5  1996/08/19  04:53:08  ekarlo
// 1) Add set and mtxPreMultiply methods.
// 2) Improve print.
//
// Revision 1.4  1996/08/18  02:20:40  ekarlo
// 1) New rotate axes methods.
// 2) Print all elements.
//
// Revision 1.3  1996/08/13  02:27:07  ekarlo
// Redo for 3D.
//
// Revision 1.2  1996/07/30  06:55:52  ekarlo
// Initialize matrix.
//
// Revision 1.1  1996/07/11  22:50:08  ekarlo
// Initial revision
//
//==============================================================================


package com.ysystems.ycad.lib.yxxf;


/**
 * 3D Transformation Matrix.
 * Code adapted from:
 * <br>_Computer Graphics, Hearn and Baker, Second Edition_
 * <br>_Graphics Gems, 3D viewing and rotation using orthonormal bases, I.516_
 * @author Ed Karlo - Y Systems, LLC
 */

public class YxxfGfxMatrix
{
    /**
     * Transformation matrix element.
     */
    private double              m00             = 1;
    /**
     * Transformation matrix element.
     */
    private double              m01             = 0;
    /**
     * Transformation matrix element.
     */
    private double              m02             = 0;
    /**
     * Transformation matrix element.
     */
    private double              m03             = 0;
    
    /**
     * Transformation matrix element.
     */
    private double              m10             = 0;
    /**
     * Transformation matrix element.
     */
    private double              m11             = 1;
    /**
     * Transformation matrix element.
     */
    private double              m12             = 0;
    /**
     * Transformation matrix element.
     */
    private double              m13             = 0;
    
    /**
     * Transformation matrix element.
     */
    private double              m20             = 0;
    /**
     * Transformation matrix element.
     */
    private double              m21             = 0;
    /**
     * Transformation matrix element.
     */
    private double              m22             = 1;
    /**
     * Transformation matrix element.
     */
    private double              m23             = 0;
    
    /**
     * Transformation matrix element.
     */
    private double              m30             = 0;
    /**
     * Transformation matrix element.
     */
    private double              m31             = 0;
    /**
     * Transformation matrix element.
     */
    private double              m32             = 0;
    /**
     * Transformation matrix element.
     */
    private double              m33             = 1;
    
//  private
//  double                      m[][]           = { { 1, 0, 0, 0 },
//                                                  { 0, 1, 0, 0 },
//                                                  { 0, 0, 1, 0 },
//                                                  { 0, 0, 0, 1 } };


    //==============================================================================
    // C O N S T R U C T O R S
    //==============================================================================

    /**
     * Constructor
     */
    public
    YxxfGfxMatrix()
    {
    }


    /**
     * Constructor
     * @param mp The transforation matrix.
     */
    public
    YxxfGfxMatrix(YxxfGfxMatrix mp)
    {
//      for (int r = 0; r < 4; r++)
//          for (int c = 0; c < 4; c++)
//              m[r][c] = mp.m[r][c];

        m00 = mp.m00; m01 = mp.m01; m02 = mp.m02; m03 = mp.m03;
        m10 = mp.m10; m11 = mp.m11; m12 = mp.m12; m13 = mp.m13;
        m20 = mp.m20; m21 = mp.m21; m22 = mp.m22; m23 = mp.m23;
        m30 = mp.m30; m31 = mp.m31; m32 = mp.m32; m33 = mp.m33;
    }


    /**
     * Initialize the matrix TODO.
     */
    public
    void mtxSetIdentity()
    {
//      for (int r = 0; r < 4; r++)
//          for (int c = 0; c < 4; c++)
//              m[r][c] = (r == c) ? 1 : 0;

        m00 = 1; m01 = 0; m02 = 0; m03 = 0;
        m10 = 0; m11 = 1; m12 = 0; m13 = 0;
        m20 = 0; m21 = 0; m22 = 1; m23 = 0;
        m30 = 0; m31 = 0; m32 = 0; m33 = 1;
    }


    /**
     * Set transformation matrix values.
     * @param mp The transformation matrix source.
     */
    public
    void set(YxxfGfxMatrix mp)
    {
//      for (int r = 0; r < 4; r++)
//          for (int c = 0; c < 4; c++)
//              m[r][c] = mp.m[r][c];

        m00 = mp.m00; m01 = mp.m01; m02 = mp.m02; m03 = mp.m03;
        m10 = mp.m10; m11 = mp.m11; m12 = mp.m12; m13 = mp.m13;
        m20 = mp.m20; m21 = mp.m21; m22 = mp.m22; m23 = mp.m23;
        m30 = mp.m30; m31 = mp.m31; m32 = mp.m32; m33 = mp.m33;
    }

    //==============================================================================


    //==============================================================================
    // P R E M U L T I P L Y
    //==============================================================================

    /**
     * Premultiply TODO.
     * @param mp The transformation matrix source.
     */
    public
    void mtxPreMultiply(YxxfGfxMatrix mp)
    {
//      for (int r = 0; r < 4; r++)
//          for (int c = 0; c < 4; c++)
//              n[r][c] =
//                  mp.m[r][0] * m[0][c] +
//                  mp.m[r][1] * m[1][c] +
//                  mp.m[r][2] * m[2][c] +
//                  mp.m[r][3] * m[3][c];

        double n00 = mp.m00 * m00 + mp.m01 * m10 + mp.m02 * m20 + mp.m03 * m30;
        double n01 = mp.m00 * m01 + mp.m01 * m11 + mp.m02 * m21 + mp.m03 * m31;
        double n02 = mp.m00 * m02 + mp.m01 * m12 + mp.m02 * m22 + mp.m03 * m32;
        double n03 = mp.m00 * m03 + mp.m01 * m13 + mp.m02 * m23 + mp.m03 * m33;
        double n10 = mp.m10 * m00 + mp.m11 * m10 + mp.m12 * m20 + mp.m13 * m30;
        double n11 = mp.m10 * m01 + mp.m11 * m11 + mp.m12 * m21 + mp.m13 * m31;
        double n12 = mp.m10 * m02 + mp.m11 * m12 + mp.m12 * m22 + mp.m13 * m32;
        double n13 = mp.m10 * m03 + mp.m11 * m13 + mp.m12 * m23 + mp.m13 * m33;
        double n20 = mp.m20 * m00 + mp.m21 * m10 + mp.m22 * m20 + mp.m23 * m30;
        double n21 = mp.m20 * m01 + mp.m21 * m11 + mp.m22 * m21 + mp.m23 * m31;
        double n22 = mp.m20 * m02 + mp.m21 * m12 + mp.m22 * m22 + mp.m23 * m32;
        double n23 = mp.m20 * m03 + mp.m21 * m13 + mp.m22 * m23 + mp.m23 * m33;
        double n30 = mp.m30 * m00 + mp.m31 * m10 + mp.m32 * m20 + mp.m33 * m30;
        double n31 = mp.m30 * m01 + mp.m31 * m11 + mp.m32 * m21 + mp.m33 * m31;
        double n32 = mp.m30 * m02 + mp.m31 * m12 + mp.m32 * m22 + mp.m33 * m32;
        double n33 = mp.m30 * m03 + mp.m31 * m13 + mp.m32 * m23 + mp.m33 * m33;

//      for (int r = 0; r < 4; r++)
//          for (int c = 0; c < 4; c++)
//              m[r][c] = n[r][c];

        m00 = n00; m01 = n01; m02 = n02; m03 = n03;
        m10 = n10; m11 = n11; m12 = n12; m13 = n13;
        m20 = n20; m21 = n21; m22 = n22; m23 = n23;
        m30 = n30; m31 = n31; m32 = n32; m33 = n33;
    }


    /**
     * Premultiply TODO
     * @param mp The transformation matrix source.
     */
    public
    void mtxPreMultiplyApply(YxxfGfxMatrix mp)
    {
//      for (int r = 0; r < 4; r++)
//          for (int c = 0; c < 4; c++)
//              n[r][c] =
//                  m[r][0] * mp.m[0][c] +
//                  m[r][1] * mp.m[1][c] +
//                  m[r][2] * mp.m[2][c] +
//                  m[r][3] * mp.m[3][c];

        double n00 = m00 * mp.m00 + m01 * mp.m10 + m02 * mp.m20 + m03 * mp.m30;
        double n01 = m00 * mp.m01 + m01 * mp.m11 + m02 * mp.m21 + m03 * mp.m31;
        double n02 = m00 * mp.m02 + m01 * mp.m12 + m02 * mp.m22 + m03 * mp.m32;
        double n03 = m00 * mp.m03 + m01 * mp.m13 + m02 * mp.m23 + m03 * mp.m33;
        double n10 = m10 * mp.m00 + m11 * mp.m10 + m12 * mp.m20 + m13 * mp.m30;
        double n11 = m10 * mp.m01 + m11 * mp.m11 + m12 * mp.m21 + m13 * mp.m31;
        double n12 = m10 * mp.m02 + m11 * mp.m12 + m12 * mp.m22 + m13 * mp.m32;
        double n13 = m10 * mp.m03 + m11 * mp.m13 + m12 * mp.m23 + m13 * mp.m33;
        double n20 = m20 * mp.m00 + m21 * mp.m10 + m22 * mp.m20 + m23 * mp.m30;
        double n21 = m20 * mp.m01 + m21 * mp.m11 + m22 * mp.m21 + m23 * mp.m31;
        double n22 = m20 * mp.m02 + m21 * mp.m12 + m22 * mp.m22 + m23 * mp.m32;
        double n23 = m20 * mp.m03 + m21 * mp.m13 + m22 * mp.m23 + m23 * mp.m33;
        double n30 = m30 * mp.m00 + m31 * mp.m10 + m32 * mp.m20 + m33 * mp.m30;
        double n31 = m30 * mp.m01 + m31 * mp.m11 + m32 * mp.m21 + m33 * mp.m31;
        double n32 = m30 * mp.m02 + m31 * mp.m12 + m32 * mp.m22 + m33 * mp.m32;
        double n33 = m30 * mp.m03 + m31 * mp.m13 + m32 * mp.m23 + m33 * mp.m33;

//      for (int r = 0; r < 4; r++)
//          for (int c = 0; c < 4; c++)
//              m[r][c] = n[r][c];

        m00 = n00; m01 = n01; m02 = n02; m03 = n03;
        m10 = n10; m11 = n11; m12 = n12; m13 = n13;
        m20 = n20; m21 = n21; m22 = n22; m23 = n23;
        m30 = n30; m31 = n31; m32 = n32; m33 = n33;
    }


    /**
     * Premultiply TODO.
     * @param mp The transformation matrix source.
     */
    public
    void mtxPreMultiply(double mp[][])
    {
//      for (int r = 0; r < 4; r++)
//          for (int c = 0; c < 4; c++)
//              n[r][c] =
//                  mp[r][0] * m[0][c] +
//                  mp[r][1] * m[1][c] +
//                  mp[r][2] * m[2][c] +
//                  mp[r][3] * m[3][c];

        double n00 = mp[0][0] * m00 + mp[0][1] * m10 + mp[0][2] * m20 + mp[0][3] * m30;
        double n01 = mp[0][0] * m01 + mp[0][1] * m11 + mp[0][2] * m21 + mp[0][3] * m31;
        double n02 = mp[0][0] * m02 + mp[0][1] * m12 + mp[0][2] * m22 + mp[0][3] * m32;
        double n03 = mp[0][0] * m03 + mp[0][1] * m13 + mp[0][2] * m23 + mp[0][3] * m33;
        double n10 = mp[1][0] * m00 + mp[1][1] * m10 + mp[1][2] * m20 + mp[1][3] * m30;
        double n11 = mp[1][0] * m01 + mp[1][1] * m11 + mp[1][2] * m21 + mp[1][3] * m31;
        double n12 = mp[1][0] * m02 + mp[1][1] * m12 + mp[1][2] * m22 + mp[1][3] * m32;
        double n13 = mp[1][0] * m03 + mp[1][1] * m13 + mp[1][2] * m23 + mp[1][3] * m33;
        double n20 = mp[2][0] * m00 + mp[2][1] * m10 + mp[2][2] * m20 + mp[2][3] * m30;
        double n21 = mp[2][0] * m01 + mp[2][1] * m11 + mp[2][2] * m21 + mp[2][3] * m31;
        double n22 = mp[2][0] * m02 + mp[2][1] * m12 + mp[2][2] * m22 + mp[2][3] * m32;
        double n23 = mp[2][0] * m03 + mp[2][1] * m13 + mp[2][2] * m23 + mp[2][3] * m33;
        double n30 = mp[3][0] * m00 + mp[3][1] * m10 + mp[3][2] * m20 + mp[3][3] * m30;
        double n31 = mp[3][0] * m01 + mp[3][1] * m11 + mp[3][2] * m21 + mp[3][3] * m31;
        double n32 = mp[3][0] * m02 + mp[3][1] * m12 + mp[3][2] * m22 + mp[3][3] * m32;
        double n33 = mp[3][0] * m03 + mp[3][1] * m13 + mp[3][2] * m23 + mp[3][3] * m33;

//      for (int r = 0; r < 4; r++)
//          for (int c = 0; c < 4; c++)
//              m[r][c] = n[r][c];

        m00 = n00; m01 = n01; m02 = n02; m03 = n03;
        m10 = n10; m11 = n11; m12 = n12; m13 = n13;
        m20 = n20; m21 = n21; m22 = n22; m23 = n23;
        m30 = n30; m31 = n31; m32 = n32; m33 = n33;
    }


    /**
     * Premultiply apply TODO
     * @param mp The transformation matrix source.
     */
    public
    void mtxPreMultiplyApply(double mp[][])
    {
//      for (int r = 0; r < 4; r++)
//          for (int c = 0; c < 4; c++)
//              n[r][c] =
//                  m[r][0] * mp[0][c] +
//                  m[r][1] * mp[1][c] +
//                  m[r][2] * mp[2][c] +
//                  m[r][3] * mp[3][c];

        double n00 = m00 * mp[0][0] + m01 * mp[1][0] + m02 * mp[2][0] + m03 * mp[3][0];
        double n01 = m00 * mp[0][1] + m01 * mp[1][1] + m02 * mp[2][1] + m03 * mp[3][1];
        double n02 = m00 * mp[0][2] + m01 * mp[1][2] + m02 * mp[2][2] + m03 * mp[3][2];
        double n03 = m00 * mp[0][3] + m01 * mp[1][3] + m02 * mp[2][3] + m03 * mp[3][3];
        double n10 = m10 * mp[0][0] + m11 * mp[1][0] + m12 * mp[2][0] + m13 * mp[3][0];
        double n11 = m10 * mp[0][1] + m11 * mp[1][1] + m12 * mp[2][1] + m13 * mp[3][1];
        double n12 = m10 * mp[0][2] + m11 * mp[1][2] + m12 * mp[2][2] + m13 * mp[3][2];
        double n13 = m10 * mp[0][3] + m11 * mp[1][3] + m12 * mp[2][3] + m13 * mp[3][3];
        double n20 = m20 * mp[0][0] + m21 * mp[1][0] + m22 * mp[2][0] + m23 * mp[3][0];
        double n21 = m20 * mp[0][1] + m21 * mp[1][1] + m22 * mp[2][1] + m23 * mp[3][1];
        double n22 = m20 * mp[0][2] + m21 * mp[1][2] + m22 * mp[2][2] + m23 * mp[3][2];
        double n23 = m20 * mp[0][3] + m21 * mp[1][3] + m22 * mp[2][3] + m23 * mp[3][3];
        double n30 = m30 * mp[0][0] + m31 * mp[1][0] + m32 * mp[2][0] + m33 * mp[3][0];
        double n31 = m30 * mp[0][1] + m31 * mp[1][1] + m32 * mp[2][1] + m33 * mp[3][1];
        double n32 = m30 * mp[0][2] + m31 * mp[1][2] + m32 * mp[2][2] + m33 * mp[3][2];
        double n33 = m30 * mp[0][3] + m31 * mp[1][3] + m32 * mp[2][3] + m33 * mp[3][3];

//      for (int r = 0; r < 4; r++)
//          for (int c = 0; c < 4; c++)
//              m[r][c] = n[r][c];

        m00 = n00; m01 = n01; m02 = n02; m03 = n03;
        m10 = n10; m11 = n11; m12 = n12; m13 = n13;
        m20 = n20; m21 = n21; m22 = n22; m23 = n23;
        m30 = n30; m31 = n31; m32 = n32; m33 = n33;
    }


    /**
     * Premultiply.
     * @param mp1 The transformation matrix source.
     * @param mp2 The transformation matrix source.
     */
    public
    void mtxPreMultiply(YxxfGfxMatrix mp1, YxxfGfxMatrix mp2)
    {
//      for (int r = 0; r < 4; r++)
//          for (int c = 0; c < 4; c++)
//              n[r][c] =
//                  mp1.m[r][0] * mp2.m[0][c] +
//                  mp1.m[r][1] * mp2.m[1][c] +
//                  mp1.m[r][2] * mp2.m[2][c] +
//                  mp1.m[r][3] * mp2.m[3][c];

        m00 = mp1.m00 * mp2.m00 + mp1.m01 * mp2.m10 + mp1.m02 * mp2.m20 + mp1.m03 * mp2.m30;
        m01 = mp1.m00 * mp2.m01 + mp1.m01 * mp2.m11 + mp1.m02 * mp2.m21 + mp1.m03 * mp2.m31;
        m02 = mp1.m00 * mp2.m02 + mp1.m01 * mp2.m12 + mp1.m02 * mp2.m22 + mp1.m03 * mp2.m32;
        m03 = mp1.m00 * mp2.m03 + mp1.m01 * mp2.m13 + mp1.m02 * mp2.m23 + mp1.m03 * mp2.m33;
        m10 = mp1.m10 * mp2.m00 + mp1.m11 * mp2.m10 + mp1.m12 * mp2.m20 + mp1.m13 * mp2.m30;
        m11 = mp1.m10 * mp2.m01 + mp1.m11 * mp2.m11 + mp1.m12 * mp2.m21 + mp1.m13 * mp2.m31;
        m12 = mp1.m10 * mp2.m02 + mp1.m11 * mp2.m12 + mp1.m12 * mp2.m22 + mp1.m13 * mp2.m32;
        m13 = mp1.m10 * mp2.m03 + mp1.m11 * mp2.m13 + mp1.m12 * mp2.m23 + mp1.m13 * mp2.m33;
        m20 = mp1.m20 * mp2.m00 + mp1.m21 * mp2.m10 + mp1.m22 * mp2.m20 + mp1.m23 * mp2.m30;
        m21 = mp1.m20 * mp2.m01 + mp1.m21 * mp2.m11 + mp1.m22 * mp2.m21 + mp1.m23 * mp2.m31;
        m22 = mp1.m20 * mp2.m02 + mp1.m21 * mp2.m12 + mp1.m22 * mp2.m22 + mp1.m23 * mp2.m32;
        m23 = mp1.m20 * mp2.m03 + mp1.m21 * mp2.m13 + mp1.m22 * mp2.m23 + mp1.m23 * mp2.m33;
        m30 = mp1.m30 * mp2.m00 + mp1.m31 * mp2.m10 + mp1.m32 * mp2.m20 + mp1.m33 * mp2.m30;
        m31 = mp1.m30 * mp2.m01 + mp1.m31 * mp2.m11 + mp1.m32 * mp2.m21 + mp1.m33 * mp2.m31;
        m32 = mp1.m30 * mp2.m02 + mp1.m31 * mp2.m12 + mp1.m32 * mp2.m22 + mp1.m33 * mp2.m32;
        m33 = mp1.m30 * mp2.m03 + mp1.m31 * mp2.m13 + mp1.m32 * mp2.m23 + mp1.m33 * mp2.m33;

//      for (int r = 0; r < 4; r++)
//          for (int c = 0; c < 4; c++)
//              m[r][c] = n[r][c];

//      m00 = n00; m01 = n01; m02 = n02; m03 = n03;
//      m10 = n10; m11 = n11; m12 = n12; m13 = n13;
//      m20 = n20; m21 = n21; m22 = n22; m23 = n23;
//      m30 = n30; m31 = n31; m32 = n32; m33 = n33;
    }

    //==============================================================================


    //==============================================================================
    // T R A N S L A T E
    //==============================================================================

    /**
     * Translate
     * @param p1 The 3D point.
     */
    public
    void mtxTranslate(YxxfGfxPointW p1)
    {
        double mt00 = 1; double mt01 = 0; double mt02 = 0; double mt03 = p1.x;
        double mt10 = 0; double mt11 = 1; double mt12 = 0; double mt13 = p1.y;
        double mt20 = 0; double mt21 = 0; double mt22 = 1; double mt23 = p1.z;
        double mt30 = 0; double mt31 = 0; double mt32 = 0; double mt33 = 1;

//      mtxPreMultiply(m_t);
        double ma00 = mt00 * m00 + mt01 * m10 + mt02 * m20 + mt03 * m30;
        double ma01 = mt00 * m01 + mt01 * m11 + mt02 * m21 + mt03 * m31;
        double ma02 = mt00 * m02 + mt01 * m12 + mt02 * m22 + mt03 * m32;
        double ma03 = mt00 * m03 + mt01 * m13 + mt02 * m23 + mt03 * m33;
        double ma10 = mt10 * m00 + mt11 * m10 + mt12 * m20 + mt13 * m30;
        double ma11 = mt10 * m01 + mt11 * m11 + mt12 * m21 + mt13 * m31;
        double ma12 = mt10 * m02 + mt11 * m12 + mt12 * m22 + mt13 * m32;
        double ma13 = mt10 * m03 + mt11 * m13 + mt12 * m23 + mt13 * m33;
        double ma20 = mt20 * m00 + mt21 * m10 + mt22 * m20 + mt23 * m30;
        double ma21 = mt20 * m01 + mt21 * m11 + mt22 * m21 + mt23 * m31;
        double ma22 = mt20 * m02 + mt21 * m12 + mt22 * m22 + mt23 * m32;
        double ma23 = mt20 * m03 + mt21 * m13 + mt22 * m23 + mt23 * m33;
        double ma30 = mt30 * m00 + mt31 * m10 + mt32 * m20 + mt33 * m30;
        double ma31 = mt30 * m01 + mt31 * m11 + mt32 * m21 + mt33 * m31;
        double ma32 = mt30 * m02 + mt31 * m12 + mt32 * m22 + mt33 * m32;
        double ma33 = mt30 * m03 + mt31 * m13 + mt32 * m23 + mt33 * m33;

        m00 = ma00; m01 = ma01; m02 = ma02; m03 = ma03;
        m10 = ma10; m11 = ma11; m12 = ma12; m13 = ma13;
        m20 = ma20; m21 = ma21; m22 = ma22; m23 = ma23;
        m30 = ma30; m31 = ma31; m32 = ma32; m33 = ma33;
    }


    /**
     * Translate
     * @param p1_x The x coordinate of a 3D point.
     * @param p1_y The y coordinate of a 3D point.
     * @param p1_z The z coordinate of a 3D point.
     */
    public
    void mtxTranslate(double p1_x, double p1_y, double p1_z)
    {
        double mt00 = 1; double mt01 = 0; double mt02 = 0; double mt03 = p1_x;
        double mt10 = 0; double mt11 = 1; double mt12 = 0; double mt13 = p1_y;
        double mt20 = 0; double mt21 = 0; double mt22 = 1; double mt23 = p1_z;
        double mt30 = 0; double mt31 = 0; double mt32 = 0; double mt33 = 1;

//      mtxPreMultiply(m_t);
        double ma00 = mt00 * m00 + mt01 * m10 + mt02 * m20 + mt03 * m30;
        double ma01 = mt00 * m01 + mt01 * m11 + mt02 * m21 + mt03 * m31;
        double ma02 = mt00 * m02 + mt01 * m12 + mt02 * m22 + mt03 * m32;
        double ma03 = mt00 * m03 + mt01 * m13 + mt02 * m23 + mt03 * m33;
        double ma10 = mt10 * m00 + mt11 * m10 + mt12 * m20 + mt13 * m30;
        double ma11 = mt10 * m01 + mt11 * m11 + mt12 * m21 + mt13 * m31;
        double ma12 = mt10 * m02 + mt11 * m12 + mt12 * m22 + mt13 * m32;
        double ma13 = mt10 * m03 + mt11 * m13 + mt12 * m23 + mt13 * m33;
        double ma20 = mt20 * m00 + mt21 * m10 + mt22 * m20 + mt23 * m30;
        double ma21 = mt20 * m01 + mt21 * m11 + mt22 * m21 + mt23 * m31;
        double ma22 = mt20 * m02 + mt21 * m12 + mt22 * m22 + mt23 * m32;
        double ma23 = mt20 * m03 + mt21 * m13 + mt22 * m23 + mt23 * m33;
        double ma30 = mt30 * m00 + mt31 * m10 + mt32 * m20 + mt33 * m30;
        double ma31 = mt30 * m01 + mt31 * m11 + mt32 * m21 + mt33 * m31;
        double ma32 = mt30 * m02 + mt31 * m12 + mt32 * m22 + mt33 * m32;
        double ma33 = mt30 * m03 + mt31 * m13 + mt32 * m23 + mt33 * m33;

        m00 = ma00; m01 = ma01; m02 = ma02; m03 = ma03;
        m10 = ma10; m11 = ma11; m12 = ma12; m13 = ma13;
        m20 = ma20; m21 = ma21; m22 = ma22; m23 = ma23;
        m30 = ma30; m31 = ma31; m32 = ma32; m33 = ma33;
    }


    /**
     * Translate inverse TODO
     * @param p1 The 3D point.
     */
    public
    void mtxTranslateInverse(YxxfGfxPointW p1)
    {
        double mt00 = 1; double mt01 = 0; double mt02 = 0; double mt03 = -p1.x;
        double mt10 = 0; double mt11 = 1; double mt12 = 0; double mt13 = -p1.y;
        double mt20 = 0; double mt21 = 0; double mt22 = 1; double mt23 = -p1.z;
        double mt30 = 0; double mt31 = 0; double mt32 = 0; double mt33 = 1;

//      mtxPreMultiply(m_t);
        double ma00 = mt00 * m00 + mt01 * m10 + mt02 * m20 + mt03 * m30;
        double ma01 = mt00 * m01 + mt01 * m11 + mt02 * m21 + mt03 * m31;
        double ma02 = mt00 * m02 + mt01 * m12 + mt02 * m22 + mt03 * m32;
        double ma03 = mt00 * m03 + mt01 * m13 + mt02 * m23 + mt03 * m33;
        double ma10 = mt10 * m00 + mt11 * m10 + mt12 * m20 + mt13 * m30;
        double ma11 = mt10 * m01 + mt11 * m11 + mt12 * m21 + mt13 * m31;
        double ma12 = mt10 * m02 + mt11 * m12 + mt12 * m22 + mt13 * m32;
        double ma13 = mt10 * m03 + mt11 * m13 + mt12 * m23 + mt13 * m33;
        double ma20 = mt20 * m00 + mt21 * m10 + mt22 * m20 + mt23 * m30;
        double ma21 = mt20 * m01 + mt21 * m11 + mt22 * m21 + mt23 * m31;
        double ma22 = mt20 * m02 + mt21 * m12 + mt22 * m22 + mt23 * m32;
        double ma23 = mt20 * m03 + mt21 * m13 + mt22 * m23 + mt23 * m33;
        double ma30 = mt30 * m00 + mt31 * m10 + mt32 * m20 + mt33 * m30;
        double ma31 = mt30 * m01 + mt31 * m11 + mt32 * m21 + mt33 * m31;
        double ma32 = mt30 * m02 + mt31 * m12 + mt32 * m22 + mt33 * m32;
        double ma33 = mt30 * m03 + mt31 * m13 + mt32 * m23 + mt33 * m33;

        m00 = ma00; m01 = ma01; m02 = ma02; m03 = ma03;
        m10 = ma10; m11 = ma11; m12 = ma12; m13 = ma13;
        m20 = ma20; m21 = ma21; m22 = ma22; m23 = ma23;
        m30 = ma30; m31 = ma31; m32 = ma32; m33 = ma33;
    }


    /**
     * Translate inverse.
     * @param p1_x The x coordinate of a 3D point.
     * @param p1_y The y coordinate of a 3D point.
     * @param p1_z The z coordinate of a 3D point.
     */
    public
    void mtxTranslateInverse(double p1_x, double p1_y, double p1_z)
    {
        double mt00 = 1; double mt01 = 0; double mt02 = 0; double mt03 = -p1_x;
        double mt10 = 0; double mt11 = 1; double mt12 = 0; double mt13 = -p1_y;
        double mt20 = 0; double mt21 = 0; double mt22 = 1; double mt23 = -p1_z;
        double mt30 = 0; double mt31 = 0; double mt32 = 0; double mt33 = 1;

//      mtxPreMultiply(m_t);
        double ma00 = mt00 * m00 + mt01 * m10 + mt02 * m20 + mt03 * m30;
        double ma01 = mt00 * m01 + mt01 * m11 + mt02 * m21 + mt03 * m31;
        double ma02 = mt00 * m02 + mt01 * m12 + mt02 * m22 + mt03 * m32;
        double ma03 = mt00 * m03 + mt01 * m13 + mt02 * m23 + mt03 * m33;
        double ma10 = mt10 * m00 + mt11 * m10 + mt12 * m20 + mt13 * m30;
        double ma11 = mt10 * m01 + mt11 * m11 + mt12 * m21 + mt13 * m31;
        double ma12 = mt10 * m02 + mt11 * m12 + mt12 * m22 + mt13 * m32;
        double ma13 = mt10 * m03 + mt11 * m13 + mt12 * m23 + mt13 * m33;
        double ma20 = mt20 * m00 + mt21 * m10 + mt22 * m20 + mt23 * m30;
        double ma21 = mt20 * m01 + mt21 * m11 + mt22 * m21 + mt23 * m31;
        double ma22 = mt20 * m02 + mt21 * m12 + mt22 * m22 + mt23 * m32;
        double ma23 = mt20 * m03 + mt21 * m13 + mt22 * m23 + mt23 * m33;
        double ma30 = mt30 * m00 + mt31 * m10 + mt32 * m20 + mt33 * m30;
        double ma31 = mt30 * m01 + mt31 * m11 + mt32 * m21 + mt33 * m31;
        double ma32 = mt30 * m02 + mt31 * m12 + mt32 * m22 + mt33 * m32;
        double ma33 = mt30 * m03 + mt31 * m13 + mt32 * m23 + mt33 * m33;

        m00 = ma00; m01 = ma01; m02 = ma02; m03 = ma03;
        m10 = ma10; m11 = ma11; m12 = ma12; m13 = ma13;
        m20 = ma20; m21 = ma21; m22 = ma22; m23 = ma23;
        m30 = ma30; m31 = ma31; m32 = ma32; m33 = ma33;
    }
    
    //==============================================================================


    //==============================================================================
    // S C A L E
    //==============================================================================

    /**
     * Apply scale - scale values in PointW.
     * @param p1 Scaled using values in point.
     * @param ctr Scaled around this point.
     */
    public
    void mtxScale(YxxfGfxPointW p1, YxxfGfxPointW ctr)
    {
        double ms00 = p1.x;   double ms01 = 0;      double ms02 = 0;      double ms03 = (1 - p1.x) * ctr.x;
        double ms10 = 0;      double ms11 = p1.y;   double ms12 = 0;      double ms13 = (1 - p1.y) * ctr.y;
        double ms20 = 0;      double ms21 = 0;      double ms22 = p1.z;   double ms23 = (1 - p1.z) * ctr.z;
        double ms30 = 0;      double ms31 = 0;      double ms32 = 0;      double ms33 = 1;

//      mtxPreMultiply(m_s);
        double ma00 = ms00 * m00 + ms01 * m10 + ms02 * m20 + ms03 * m30;
        double ma01 = ms00 * m01 + ms01 * m11 + ms02 * m21 + ms03 * m31;
        double ma02 = ms00 * m02 + ms01 * m12 + ms02 * m22 + ms03 * m32;
        double ma03 = ms00 * m03 + ms01 * m13 + ms02 * m23 + ms03 * m33;
        double ma10 = ms10 * m00 + ms11 * m10 + ms12 * m20 + ms13 * m30;
        double ma11 = ms10 * m01 + ms11 * m11 + ms12 * m21 + ms13 * m31;
        double ma12 = ms10 * m02 + ms11 * m12 + ms12 * m22 + ms13 * m32;
        double ma13 = ms10 * m03 + ms11 * m13 + ms12 * m23 + ms13 * m33;
        double ma20 = ms20 * m00 + ms21 * m10 + ms22 * m20 + ms23 * m30;
        double ma21 = ms20 * m01 + ms21 * m11 + ms22 * m21 + ms23 * m31;
        double ma22 = ms20 * m02 + ms21 * m12 + ms22 * m22 + ms23 * m32;
        double ma23 = ms20 * m03 + ms21 * m13 + ms22 * m23 + ms23 * m33;
        double ma30 = ms30 * m00 + ms31 * m10 + ms32 * m20 + ms33 * m30;
        double ma31 = ms30 * m01 + ms31 * m11 + ms32 * m21 + ms33 * m31;
        double ma32 = ms30 * m02 + ms31 * m12 + ms32 * m22 + ms33 * m32;
        double ma33 = ms30 * m03 + ms31 * m13 + ms32 * m23 + ms33 * m33;

        m00 = ma00; m01 = ma01; m02 = ma02; m03 = ma03;
        m10 = ma10; m11 = ma11; m12 = ma12; m13 = ma13;
        m20 = ma20; m21 = ma21; m22 = ma22; m23 = ma23;
        m30 = ma30; m31 = ma31; m32 = ma32; m33 = ma33;
    }


    /**
     * Apply scale - scale values in separate doubles.
     * @param p1_x The x coordinate of a 3D point.
     * @param p1_y The y coordinate of a 3D point.
     * @param p1_z The z coordinate of a 3D point.
     */
    public
    void mtxScale(double p1_x, double p1_y, double p1_z, YxxfGfxPointW ctr)
    {
        double ms00 = p1_x;   double ms01 = 0;      double ms02 = 0;      double ms03 = (1 - p1_x) * ctr.x;
        double ms10 = 0;      double ms11 = p1_y;   double ms12 = 0;      double ms13 = (1 - p1_y) * ctr.y;
        double ms20 = 0;      double ms21 = 0;      double ms22 = p1_z;   double ms23 = (1 - p1_z) * ctr.z;
        double ms30 = 0;      double ms31 = 0;      double ms32 = 0;      double ms33 = 1;

//      mtxPreMultiply(m_s);
        double ma00 = ms00 * m00 + ms01 * m10 + ms02 * m20 + ms03 * m30;
        double ma01 = ms00 * m01 + ms01 * m11 + ms02 * m21 + ms03 * m31;
        double ma02 = ms00 * m02 + ms01 * m12 + ms02 * m22 + ms03 * m32;
        double ma03 = ms00 * m03 + ms01 * m13 + ms02 * m23 + ms03 * m33;
        double ma10 = ms10 * m00 + ms11 * m10 + ms12 * m20 + ms13 * m30;
        double ma11 = ms10 * m01 + ms11 * m11 + ms12 * m21 + ms13 * m31;
        double ma12 = ms10 * m02 + ms11 * m12 + ms12 * m22 + ms13 * m32;
        double ma13 = ms10 * m03 + ms11 * m13 + ms12 * m23 + ms13 * m33;
        double ma20 = ms20 * m00 + ms21 * m10 + ms22 * m20 + ms23 * m30;
        double ma21 = ms20 * m01 + ms21 * m11 + ms22 * m21 + ms23 * m31;
        double ma22 = ms20 * m02 + ms21 * m12 + ms22 * m22 + ms23 * m32;
        double ma23 = ms20 * m03 + ms21 * m13 + ms22 * m23 + ms23 * m33;
        double ma30 = ms30 * m00 + ms31 * m10 + ms32 * m20 + ms33 * m30;
        double ma31 = ms30 * m01 + ms31 * m11 + ms32 * m21 + ms33 * m31;
        double ma32 = ms30 * m02 + ms31 * m12 + ms32 * m22 + ms33 * m32;
        double ma33 = ms30 * m03 + ms31 * m13 + ms32 * m23 + ms33 * m33;

        m00 = ma00; m01 = ma01; m02 = ma02; m03 = ma03;
        m10 = ma10; m11 = ma11; m12 = ma12; m13 = ma13;
        m20 = ma20; m21 = ma21; m22 = ma22; m23 = ma23;
        m30 = ma30; m31 = ma31; m32 = ma32; m33 = ma33;
    }

    //==============================================================================


    //==============================================================================
    // R O T A T E
    //==============================================================================

    /**
     * Rotate
     * @param p1 The 3D point TODO.
     * @param p2 The 3D point TODO.
     * @param angle The rotation angle.
     */
    public
    void mtxRotate(YxxfGfxPointW p1, YxxfGfxPointW p2, double angle)
    {
        double vecx = p2.x - p1.x;
        double vecy = p2.y - p1.y;
        double vecz = p2.z - p1.z;

        double veclen = Math.sqrt(vecx * vecx + vecy * vecy + vecz * vecz);

        double s     = Math.cos(angle / 2.0);
        double sinA2 = Math.sin(angle / 2.0);

        double a = sinA2 * vecx / veclen;
        double b = sinA2 * vecy / veclen;
        double c = sinA2 * vecz / veclen;


        //
        // Translate
        //
        // mtxTranslate(-p1.x, -p1.y, -p1.z);
        double mt00 = 1; double mt01 = 0; double mt02 = 0; double mt03 = -p1.x;
        double mt10 = 0; double mt11 = 1; double mt12 = 0; double mt13 = -p1.y;
        double mt20 = 0; double mt21 = 0; double mt22 = 1; double mt23 = -p1.z;
        double mt30 = 0; double mt31 = 0; double mt32 = 0; double mt33 = 1;

        // mtxPreMultiply(m_t);
        double ma00 = mt00 * m00 + mt01 * m10 + mt02 * m20 + mt03 * m30;
        double ma01 = mt00 * m01 + mt01 * m11 + mt02 * m21 + mt03 * m31;
        double ma02 = mt00 * m02 + mt01 * m12 + mt02 * m22 + mt03 * m32;
        double ma03 = mt00 * m03 + mt01 * m13 + mt02 * m23 + mt03 * m33;
        double ma10 = mt10 * m00 + mt11 * m10 + mt12 * m20 + mt13 * m30;
        double ma11 = mt10 * m01 + mt11 * m11 + mt12 * m21 + mt13 * m31;
        double ma12 = mt10 * m02 + mt11 * m12 + mt12 * m22 + mt13 * m32;
        double ma13 = mt10 * m03 + mt11 * m13 + mt12 * m23 + mt13 * m33;
        double ma20 = mt20 * m00 + mt21 * m10 + mt22 * m20 + mt23 * m30;
        double ma21 = mt20 * m01 + mt21 * m11 + mt22 * m21 + mt23 * m31;
        double ma22 = mt20 * m02 + mt21 * m12 + mt22 * m22 + mt23 * m32;
        double ma23 = mt20 * m03 + mt21 * m13 + mt22 * m23 + mt23 * m33;
        double ma30 = mt30 * m00 + mt31 * m10 + mt32 * m20 + mt33 * m30;
        double ma31 = mt30 * m01 + mt31 * m11 + mt32 * m21 + mt33 * m31;
        double ma32 = mt30 * m02 + mt31 * m12 + mt32 * m22 + mt33 * m32;
        double ma33 = mt30 * m03 + mt31 * m13 + mt32 * m23 + mt33 * m33;


        //
        // Rotate
        //
        double mr00 = 1.0 - 2.0 * b * b - 2.0 * c * c;
        double mr01 = 2.0 * a * b - 2.0 * s * c;
        double mr02 = 2.0 * a * c + 2.0 * s * b;
        double mr03 = 0;
        double mr10 = 2.0 * a * b + 2.0 * s * c;
        double mr11 = 1.0 - 2.0 * a * a - 2.0 * c * c;
        double mr12 = 2.0 * b * c - 2.0 * s * a;
        double mr13 = 0;
        double mr20 = 2.0 * a * c - 2.0 * s * b;
        double mr21 = 2.0 * b * c + 2.0 * s * a;
        double mr22 = 1.0 - 2.0 * a * a - 2.0 * b * b;
        double mr23 = 0;
        double mr30 = 0; 
        double mr31 = 0;
        double mr32 = 0;
        double mr33 = 1;

        // mtxPreMultiply(m_r);
        double mb00 = mr00 * ma00 + mr01 * ma10 + mr02 * ma20 + mr03 * ma30;
        double mb01 = mr00 * ma01 + mr01 * ma11 + mr02 * ma21 + mr03 * ma31;
        double mb02 = mr00 * ma02 + mr01 * ma12 + mr02 * ma22 + mr03 * ma32;
        double mb03 = mr00 * ma03 + mr01 * ma13 + mr02 * ma23 + mr03 * ma33;
        double mb10 = mr10 * ma00 + mr11 * ma10 + mr12 * ma20 + mr13 * ma30;
        double mb11 = mr10 * ma01 + mr11 * ma11 + mr12 * ma21 + mr13 * ma31;
        double mb12 = mr10 * ma02 + mr11 * ma12 + mr12 * ma22 + mr13 * ma32;
        double mb13 = mr10 * ma03 + mr11 * ma13 + mr12 * ma23 + mr13 * ma33;
        double mb20 = mr20 * ma00 + mr21 * ma10 + mr22 * ma20 + mr23 * ma30;
        double mb21 = mr20 * ma01 + mr21 * ma11 + mr22 * ma21 + mr23 * ma31;
        double mb22 = mr20 * ma02 + mr21 * ma12 + mr22 * ma22 + mr23 * ma32;
        double mb23 = mr20 * ma03 + mr21 * ma13 + mr22 * ma23 + mr23 * ma33;
        double mb30 = mr30 * ma00 + mr31 * ma10 + mr32 * ma20 + mr33 * ma30;
        double mb31 = mr30 * ma01 + mr31 * ma11 + mr32 * ma21 + mr33 * ma31;
        double mb32 = mr30 * ma02 + mr31 * ma12 + mr32 * ma22 + mr33 * ma32;
        double mb33 = mr30 * ma03 + mr31 * ma13 + mr32 * ma23 + mr33 * ma33;


        //
        // Translate
        //
        // mtxTranslate(p1.x, p1.y, p1.z);
        mt03 = p1.x;
        mt13 = p1.y;
        mt23 = p1.z;
        
        // mtxPreMultiply(m_t);
        m00 = mt00 * mb00 + mt01 * mb10 + mt02 * mb20 + mt03 * mb30;
        m01 = mt00 * mb01 + mt01 * mb11 + mt02 * mb21 + mt03 * mb31;
        m02 = mt00 * mb02 + mt01 * mb12 + mt02 * mb22 + mt03 * mb32;
        m03 = mt00 * mb03 + mt01 * mb13 + mt02 * mb23 + mt03 * mb33;
        m10 = mt10 * mb00 + mt11 * mb10 + mt12 * mb20 + mt13 * mb30;
        m11 = mt10 * mb01 + mt11 * mb11 + mt12 * mb21 + mt13 * mb31;
        m12 = mt10 * mb02 + mt11 * mb12 + mt12 * mb22 + mt13 * mb32;
        m13 = mt10 * mb03 + mt11 * mb13 + mt12 * mb23 + mt13 * mb33;
        m20 = mt20 * mb00 + mt21 * mb10 + mt22 * mb20 + mt23 * mb30;
        m21 = mt20 * mb01 + mt21 * mb11 + mt22 * mb21 + mt23 * mb31;
        m22 = mt20 * mb02 + mt21 * mb12 + mt22 * mb22 + mt23 * mb32;
        m23 = mt20 * mb03 + mt21 * mb13 + mt22 * mb23 + mt23 * mb33;
        m30 = mt30 * mb00 + mt31 * mb10 + mt32 * mb20 + mt33 * mb30;
        m31 = mt30 * mb01 + mt31 * mb11 + mt32 * mb21 + mt33 * mb31;
        m32 = mt30 * mb02 + mt31 * mb12 + mt32 * mb22 + mt33 * mb32;
        m33 = mt30 * mb03 + mt31 * mb13 + mt32 * mb23 + mt33 * mb33;
    }


    // Set the matrix to rotate a point to match the A axes
    public
    void mtxRotateAxes_World_to_Local(YxxfGfxPointW Ax, YxxfGfxPointW Ay, YxxfGfxPointW Az)
    {
        double mr00 = Ax.x; double mr01 = Ay.x; double mr02 = Az.x; double mr03 = 0;
        double mr10 = Ax.y; double mr11 = Ay.y; double mr12 = Az.y; double mr13 = 0;
        double mr20 = Ax.z; double mr21 = Ay.z; double mr22 = Az.z; double mr23 = 0;
        double mr30 = 0;    double mr31 = 0;    double mr32 = 0;    double mr33 = 1;

        // mtxPreMultiply(m_r);
        double ma00 = mr00 * m00 + mr01 * m10 + mr02 * m20 + mr03 * m30;
        double ma01 = mr00 * m01 + mr01 * m11 + mr02 * m21 + mr03 * m31;
        double ma02 = mr00 * m02 + mr01 * m12 + mr02 * m22 + mr03 * m32;
        double ma03 = mr00 * m03 + mr01 * m13 + mr02 * m23 + mr03 * m33;
        double ma10 = mr10 * m00 + mr11 * m10 + mr12 * m20 + mr13 * m30;
        double ma11 = mr10 * m01 + mr11 * m11 + mr12 * m21 + mr13 * m31;
        double ma12 = mr10 * m02 + mr11 * m12 + mr12 * m22 + mr13 * m32;
        double ma13 = mr10 * m03 + mr11 * m13 + mr12 * m23 + mr13 * m33;
        double ma20 = mr20 * m00 + mr21 * m10 + mr22 * m20 + mr23 * m30;
        double ma21 = mr20 * m01 + mr21 * m11 + mr22 * m21 + mr23 * m31;
        double ma22 = mr20 * m02 + mr21 * m12 + mr22 * m22 + mr23 * m32;
        double ma23 = mr20 * m03 + mr21 * m13 + mr22 * m23 + mr23 * m33;
        double ma30 = mr30 * m00 + mr31 * m10 + mr32 * m20 + mr33 * m30;
        double ma31 = mr30 * m01 + mr31 * m11 + mr32 * m21 + mr33 * m31;
        double ma32 = mr30 * m02 + mr31 * m12 + mr32 * m22 + mr33 * m32;
        double ma33 = mr30 * m03 + mr31 * m13 + mr32 * m23 + mr33 * m33;

        m00 = ma00; m01 = ma01; m02 = ma02; m03 = ma03;
        m10 = ma10; m11 = ma11; m12 = ma12; m13 = ma13;
        m20 = ma20; m21 = ma21; m22 = ma22; m23 = ma23;
        m30 = ma30; m31 = ma31; m32 = ma32; m33 = ma33;
    }


    // Set the matrix to rotate a point from the A axes to the World (0, 0, 1)
    public
    void mtxRotateAxes_Local_to_World(YxxfGfxPointW Ax, YxxfGfxPointW Ay, YxxfGfxPointW Az)
    {
        double mr00 = Ax.x; double mr01 = Ax.y; double mr02 = Ax.z; double mr03 = 0;
        double mr10 = Ay.x; double mr11 = Ay.y; double mr12 = Ay.z; double mr13 = 0;
        double mr20 = Az.x; double mr21 = Az.y; double mr22 = Az.z; double mr23 = 0;
        double mr30 = 0;    double mr31 = 0;    double mr32 = 0;    double mr33 = 1;

        // mtxPreMultiply(m_r);
        double ma00 = mr00 * m00 + mr01 * m10 + mr02 * m20 + mr03 * m30;
        double ma01 = mr00 * m01 + mr01 * m11 + mr02 * m21 + mr03 * m31;
        double ma02 = mr00 * m02 + mr01 * m12 + mr02 * m22 + mr03 * m32;
        double ma03 = mr00 * m03 + mr01 * m13 + mr02 * m23 + mr03 * m33;
        double ma10 = mr10 * m00 + mr11 * m10 + mr12 * m20 + mr13 * m30;
        double ma11 = mr10 * m01 + mr11 * m11 + mr12 * m21 + mr13 * m31;
        double ma12 = mr10 * m02 + mr11 * m12 + mr12 * m22 + mr13 * m32;
        double ma13 = mr10 * m03 + mr11 * m13 + mr12 * m23 + mr13 * m33;
        double ma20 = mr20 * m00 + mr21 * m10 + mr22 * m20 + mr23 * m30;
        double ma21 = mr20 * m01 + mr21 * m11 + mr22 * m21 + mr23 * m31;
        double ma22 = mr20 * m02 + mr21 * m12 + mr22 * m22 + mr23 * m32;
        double ma23 = mr20 * m03 + mr21 * m13 + mr22 * m23 + mr23 * m33;
        double ma30 = mr30 * m00 + mr31 * m10 + mr32 * m20 + mr33 * m30;
        double ma31 = mr30 * m01 + mr31 * m11 + mr32 * m21 + mr33 * m31;
        double ma32 = mr30 * m02 + mr31 * m12 + mr32 * m22 + mr33 * m32;
        double ma33 = mr30 * m03 + mr31 * m13 + mr32 * m23 + mr33 * m33;

        m00 = ma00; m01 = ma01; m02 = ma02; m03 = ma03;
        m10 = ma10; m11 = ma11; m12 = ma12; m13 = ma13;
        m20 = ma20; m21 = ma21; m22 = ma22; m23 = ma23;
        m30 = ma30; m31 = ma31; m32 = ma32; m33 = ma33;
    }

    //==============================================================================


    //==============================================================================
    // T R A N S F O R M
    //==============================================================================

    /**
     * Transform TODO.
     * @param p1 The 3D point.
     * @return The transformed point.
     */
    public
    YxxfGfxPointW mtxTransformPoint(YxxfGfxPointW p1)
    {
        double ptx = m00 * p1.x + m01 * p1.y + m02 * p1.z + m03;
        double pty = m10 * p1.x + m11 * p1.y + m12 * p1.z + m13;
        double ptz = m20 * p1.x + m21 * p1.y + m22 * p1.z + m23;

        p1.x = ptx;
        p1.y = pty;
        p1.z = ptz;

        return p1;
    }


    /**
     * Transform TODO
     * @param p1 The 3D point TODO
     * @param p2 The 3D point TODO
     * @return The transformed point.
     */
    public
    YxxfGfxPointW mtxTransformPoint(YxxfGfxPointW p1, YxxfGfxPointW p2)
    {
        p2.x = m00 * p1.x + m01 * p1.y + m02 * p1.z + m03;
        p2.y = m10 * p1.x + m11 * p1.y + m12 * p1.z + m13;
        p2.z = m20 * p1.x + m21 * p1.y + m22 * p1.z + m23;

        return p2;
    }

    //==============================================================================




    //==============================================================================
    // M I S C E L L A N E O U S
    //==============================================================================

    /**
     * Stringify this matrix.
     * @return The matrix.
     */
    public
    String toString()
    {
        return "YxxfGfxMatrix 0[" + m00 + " " + m01 + " " + m02 + " " + m03 + "]\n" +
               "              1[" + m10 + " " + m11 + " " + m12 + " " + m13 + "]\n" +
               "              2[" + m20 + " " + m21 + " " + m22 + " " + m23 + "]\n" +
               "              3[" + m30 + " " + m31 + " " + m32 + " " + m33 + "]";
    }
    
    //==============================================================================


}

