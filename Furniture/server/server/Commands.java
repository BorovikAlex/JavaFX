package server.server;

import server.Database.Database;

public class Commands {

    public static Object split(String command) {
        String[] commandNumber = command.split(" ", 2);
        String[] commands;
        Object result = true;
        switch (commandNumber[0]) {
            case "addAdmin":
                commands = command.split(" ", 5);
                result = Database.addAdmin(commands[1], commands[2]);
                break;
            case "addclient":
                commands = command.split(" ", 7);
                result = Database.addUser(commands[1], commands[2], commands[3], commands[4], commands[5], commands[6]);
                break;
            case "login":
                commands = command.split(" ", 3);
                result = Database.login(commands[1], commands[2]);
                break;
            case "loginAdm":
                commands = command.split(" ", 3);
                result = Database.loginAdm(commands[1], commands[2]);
                break;
            case "delAdmin":
                commands = command.split(" ", 2);
                result = Database.delAdmin(commands[1]);
                break;
            case "getAdmin":
                result = Database.getAdmin();
                break;
            case "getUsers":
                result = Database.getUsers();
                break;
            case "getFurniture":
                result = Database.getFurniture();
                break;
            case "getMaterial":
                result = Database.getMaterial();
                break;
            case "addMaterial":
                commands = command.split(" ", 2);
                result = Database.addMaterial(commands[1]);
                break;
            case "delMaterial":
                commands = command.split(" ", 2);
                result = Database.delMaterial(commands[1]);
                break;
            case "getFirm":
                result = Database.getFirm();
                break;
            case "getFirmName":
                result = Database.getFirmName();
                break;
            case "addFirm":
                result = Database.addFirm(command);
                break;
            case "setFirm":
                result = Database.setFirm(command);
                break;
            case "delFirm":
                commands = command.split(" ", 2);
                result = Database.delFirm(commands[1]);
                break;
            case "getProfit":
                result = Database.getProfit();
                break;
            case "getId":
                result = Database.getId();
                break;
            case "addProfit":
                result = Database.addProfit(command);
                break;
            case "setProfit":
                result = Database.setProfit(command);
                break;
            case "getStat":
                result = Database.getStat();
                break;
        }
        return result;
    }
}
