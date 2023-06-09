package restaurant.controllers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


import restaurant.models.Table;

class TableManagementTest {
  
    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all test methods");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before each test method");
    }

    @AfterEach
    void afterEach() {
        System.out.println("After each test method");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all test methods");
    }


        @Test
        void checksAddTableToSeeIfThereIsSuccessfullyAddedTable () {
            // Arrange
            Table table = new Table(1, 4, Table.Status.AVAILABLE);
            // Assert
            Assertions.assertEquals(1, table.getTableId());
            Assertions.assertEquals(4, table.getTableSize());
            Assertions.assertEquals("AVAILABLE", table.getStatus().toString());
        }


        private TableManagement tableManagement;

        //@Before
        public void setup() {
            tableManagement = new TableManagement();
        }

// Not working fully...
//        @Test
//        public void testAssignGuestToTable() {
//
//            int tableID = 1;
//            int tableSize = 4;
//            Table.Status tableStatus = Table.Status.AVAILABLE;
//
//            Table table = new Table(1, 4, Table.Status.AVAILABLE);
//            List<Table> tables = new ArrayList<>();
//            tables.add(table);
//
//            tableManagement.tables = tables;
//
//            String input = "3";
//            InputStream in = new ByteArrayInputStream(input.getBytes());
//            System.setIn(in);
//
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            PrintStream printStream = new PrintStream(outputStream);
//            PrintStream originalOut = System.out;
//            System.setOut(printStream);
//
//            // Call the method
//            tableManagement.assignGuestToTable();
//
//            // Reset the input and output streams
//            System.setIn(System.in);
//            System.setOut(originalOut);
//            Assertions.assertEquals(Table.Status.OCCUPIED, table.getStatus());
//        }
    }
