package com.jays.demo.Utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringFieldProcessTest {

    @Test
    public void testNormalizeField() {
        String expect1 = "dynamic programming";

        assertEquals(expect1, StringFieldProcess.normalizeField("dynamic programming"));
        assertEquals(expect1, StringFieldProcess.normalizeField("dynamic        programming"));
        assertEquals(expect1, StringFieldProcess.normalizeField("Dynamic Programming"));
        assertEquals(expect1, StringFieldProcess.normalizeField("dynamic_programming"));
        assertEquals(expect1, StringFieldProcess.normalizeField("dynamic-programming"));
        assertEquals(expect1, StringFieldProcess.normalizeField("dynamicProgramming"));
        assertEquals(expect1, StringFieldProcess.normalizeField("DynamicProgramming"));
        assertEquals(expect1, StringFieldProcess.normalizeField("dynamic_Programming"));
        assertEquals(expect1, StringFieldProcess.normalizeField("dynamic__Programming"));
        assertEquals(expect1, StringFieldProcess.normalizeField("dynamic-_Programming"));
        assertEquals(expect1, StringFieldProcess.normalizeField("dynamic--Programming"));
        assertEquals(expect1, StringFieldProcess.normalizeField("dynamicProgramming_"));
        assertEquals(expect1, StringFieldProcess.normalizeField("_dynamicProgramming"));
        assertEquals(expect1, StringFieldProcess.normalizeField("_dynamic_Programming_"));

        String expect2 = "union find in a two d matrix";
        assertEquals(expect2, StringFieldProcess.normalizeField("union find in a two d matrix"));
        assertEquals(expect2, StringFieldProcess.normalizeField("union_find_in_a_two_d_matrix"));
        assertEquals(expect2, StringFieldProcess.normalizeField("union-find-in-a-two-d-matrix"));
        assertEquals(expect2, StringFieldProcess.normalizeField("unionFindInATwoDMatrix"));
        assertEquals(expect2, StringFieldProcess.normalizeField("union_Find_InATwoD_Matrix"));
        assertEquals(expect2, StringFieldProcess.normalizeField("_union_Find_InATwoD_Matrix"));
        assertEquals(expect2, StringFieldProcess.normalizeField("union_Find_InATwoD_Matrix_"));
        assertEquals(expect2, StringFieldProcess.normalizeField("_union_Find_InATwoD_Matrix_"));
    }
}
