/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered.org <http://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.api.util;

import static org.junit.Assert.assertEquals;
import static org.spongepowered.api.math.Vectors.create3d;

import org.junit.Test;
import org.spongepowered.api.math.Vector3d;

public class DirectionTest {

    @Test
    public void testHorizontal() {
        for (int i = 0; i < 16; i++) {
            final Direction direction = Direction.values()[i];
            final double angle = i / 8D * Math.PI; // = i / (16 / 2 PI)
            final Vector3d vector = direction.toVector3d();
            assertEquals(direction.name() + "'s direction does not match the expected direction.", create3d(Math.sin(angle), 0, -Math.cos(angle)).normalize(), vector);
        }
    }

    @Test
    public void testGetOpposite() {
        for (final Direction direction : Direction.values()) {
            assertEquals("The opposite of the opposite should be the original value.", direction, direction.getOpposite().getOpposite());
        }
    }

    @Test
    public void testToVector3d() {
        for (final Direction direction : Direction.values()) {
            assertEquals("The length of the sum of the original and the opposite should be zero.", 0, direction.toVector3d().add(direction.getOpposite().toVector3d()).lengthSquared(), 0);
        }
    }

    @Test
    public void testGetClosest() {
        // assertEquals("The closest horizontal direction for the 0-Vector should be NORTH.",
        // Direction.NORTH, new Vector3d(0, 0, 0));
        for (final Direction direction : Direction.values()) {
            assertEquals("The closest direction for a direction should be the direction itself.", direction, Direction.getClosest(direction.toVector3d()));
        }
    }

    @Test
    public void testGetClosestHorizonal() {
        // assertEquals("The closest horizontal direction for the 0-Vector should be NORTH.",
        // Direction.NORTH, new Vector3d(0, 0, 0));
        for (final Direction direction : Direction.values()) {
            if (direction.isUpright()) {
                assertEquals("The closest horizontal direction for a vertical direction should be NORTH.", Direction.NORTH, Direction.getClosest(direction.toVector3d()));
            } else {
                assertEquals("The closest horizontal direction for a direction should be the direction itself.", direction, Direction.getClosest(direction.toVector3d()));
            }
        }
    }

}
