package restaurant;

import java.util.*;
import java.util.Scanner;
import restaurant.models.OrderItem;
import restaurant.controllers.MenuManagement;
import restaurant.models.MenuItem;

import restaurant.controllers.UserLogin;

import restaurant.utils.ConsoleColors;//This file is located in utils

import static restaurant.controllers.MenuManagement.*;


public class App {

    //We can handle the 'view' here (◑‿◐)
    public static void main(String[] args) {
        UserLogin.findUser();
    }

}
