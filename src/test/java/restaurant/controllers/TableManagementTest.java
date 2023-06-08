package restaurant.controllers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import restaurant.models.Table;
import restaurant.controllers.TableManagement;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
class TableManagementTest {

    @org.junit.jupiter.api.Test
    void checksAddTableToSeeIfThereIsSuccessfullyAddedTable() {
        // Arrange
        TableManagement tableManagement = new TableManagement();
        Table table = new Table(1, 4, Table.Status.AVAILABLE);

        // Act
        tableManagement.addTable(table);
        int tableCount = tableManagement.getTables().size();

        // Assert
        Assertions.assertEquals(1, tableCount);
    }

    @org.junit.jupiter.api.Test
    void testingIfMultipleTablesCanBeAdded() { //does not test count of total tables
        // Arrange
        TableManagement tableManagement = new TableManagement();
        Table table1 = new Table(1, 4, Table.Status.AVAILABLE);
        Table table2 = new Table(2, 6, Table.Status.AVAILABLE);

        // Act
        tableManagement.addTable(table1);
        tableManagement.addTable(table2);
        List<Table> tables = tableManagement.getTables();

        // Assert
        assertTrue(tables.contains(table1));
        assertTrue(tables.contains(table2));

    }


    @org.junit.jupiter.api.Test
    void testTableCountAfterAdditionsOfMultipleTables() {
        // Arrange
        TableManagement tableManagement = new TableManagement();
        Table table1 = new Table(1, 4, Table.Status.AVAILABLE);
        Table table2 = new Table(2, 6, Table.Status.AVAILABLE);

        // Act
        tableManagement.addTable(table1);
        tableManagement.addTable(table2);
        int tableCount = tableManagement.getTables().size();

        // Assert
        Assertions.assertEquals(2, tableCount);
    }
}