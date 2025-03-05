package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderProcessorTest {

    public static OrderProcessor ORDER_PROCESSOR;

    @BeforeAll
    static void setUp() {
        ORDER_PROCESSOR = new OrderProcessor();
    }

    @Test
    void testNoItems() {
        assertThrows(RuntimeException.class,
                () -> ORDER_PROCESSOR.computeTotal(Collections.emptyList()));
    }


    //negative price
    @Test
    void testNegativePrice() {
        Item item1 = new Item("itemA", -75, 1, false);
        List<Item> itemList = new ArrayList<>();
        itemList.add(item1);

        assertEquals(5, ORDER_PROCESSOR.computeTotal(itemList));
    }


    //negative quantity
    @Test
    void testNegativeQuantity() {
        Item item1 = new Item("itemA", 75, -1, false);
        List<Item> itemList = new ArrayList<>();
        itemList.add(item1);

        assertEquals(5, ORDER_PROCESSOR.computeTotal(itemList));
    }

    // over $100
    @Test
    void TestOver100() {
        Item item1 = new Item("itemA", 200, 1, false);
        List<Item> itemList = new ArrayList<>();
        itemList.add(item1);

        assertEquals(180, ORDER_PROCESSOR.computeTotal(itemList));
    }

    // Discount eligible
    @Test
    void testDiscount() {
        Item item1 = new Item("itemA", 100, 1, true);
        List<Item> itemList = new ArrayList<>();
        itemList.add(item1);

        assertEquals(95, ORDER_PROCESSOR.computeTotal(itemList));
    }

    // After discount, total < 50 adds $5 fee
    @Test
    void testUnder50() {
        Item item1 = new Item("itemA", 25, 1, false);
        List<Item> itemList = new ArrayList<>();
        itemList.add(item1);

        assertEquals(30, ORDER_PROCESSOR.computeTotal(itemList));
    }

    static void printNodes(int[] nodeList) {
        System.out.println("Instrumentation report for getTotal():");
        for (int i = 0; i < nodeList.length; ++i) {
            int curNode = i+1;
            System.out.println("Node " + curNode + ": " + nodeList[i]);
        }
    }

    @AfterAll
    static void nodeTest() {
        printNodes(OrderProcessor.nodes);
    }
}