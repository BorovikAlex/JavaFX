package server.Database;

import java.sql.*;
import java.util.ArrayList;


public class Database {
    private static Connection connection;

    public static void connect(String database, String user, String password, String port) {
        try {
            connection = DriverManager.getConnection(("jdbc:mysql://localhost:" + port + "/" +
                    database + "?serverTimezone=UTC"), user, password);
        } catch (SQLException sqlexc) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            sqlexc.printStackTrace();
        }
    }


    public static boolean addUser(String name, String surname, String login, String password, String email, String phone) {
        String insertClient = "INSERT INTO furniture.users (name, surname, login, password, email, phone) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatementClient = connection.prepareStatement(insertClient);

            preparedStatementClient.setString(1, name);
            preparedStatementClient.setString(2, surname);
            preparedStatementClient.setString(3, login);
            preparedStatementClient.setString(4, password);
            preparedStatementClient.setString(5, email);
            preparedStatementClient.setString(6, phone);
            preparedStatementClient.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean addAdmin(String login, String password) {
        String insertAdmin = "INSERT INTO furniture.admin (login, password) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatementClient = connection.prepareStatement(insertAdmin);

            preparedStatementClient.setString(1, login);
            preparedStatementClient.setString(2, password);
            preparedStatementClient.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean login(String login, String password) {
        ResultSet resultSet;

        String select = "SELECT * FROM users WHERE login=? and password=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("login func exception");
            e.printStackTrace();
        }
        return true;
    }

    public static boolean loginAdm(String login, String password) {
        ResultSet resultSet;

        String select = "SELECT * FROM admin WHERE login=? and password=?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("login func exception");
            e.printStackTrace();
        }
        return true;
    }

    public static boolean delAdmin(String login) {

        String delete = "DELETE FROM furniture.admin WHERE login=? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static ArrayList<String> getAdmin() {
        ResultSet resultSet;
        ArrayList<String> admin = new ArrayList<>(0);

        String select = "SELECT * FROM admin";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                admin.add(resultSet.getString("login"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    public static ArrayList<String> getUsers() {
        ResultSet resultSet;
        ArrayList<String> usersList = new ArrayList<>(0);

        String select = "SELECT * FROM furniture.users";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder info = new StringBuilder();
                info.append(resultSet.getString("name")).append(" ").
                        append(resultSet.getString("surname")).append(" ").
                        append(resultSet.getString("login")).append(" ").
                        append(resultSet.getString("email")).append(" ").
                        append(resultSet.getString("phone")).append(" ").
                        append(resultSet.getString("password"));
                usersList.add(info.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public static ArrayList<String> getFurniture() {
        ResultSet resultSet;
        ArrayList<String> furniture = new ArrayList<>(0);

        String select = "SELECT * FROM furniture";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                furniture.add(resultSet.getString("furniture"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return furniture;
    }

    public static ArrayList<String> getMaterial() {
        ResultSet resultSet;
        ArrayList<String> material = new ArrayList<>(0);

        String select = "SELECT * FROM furniture.material";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                material.add(resultSet.getString("material"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return material;
    }

    public static boolean addMaterial(String material) {
        String insertCountry = "INSERT INTO furniture.material (material) VALUES (?)";
        try {
            PreparedStatement preparedStatementClient = connection.prepareStatement(insertCountry);
            preparedStatementClient.setString(1, material);
            preparedStatementClient.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static boolean delMaterial(String material) {

        String delete = "DELETE FROM furniture.material WHERE material=? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, material);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static ArrayList<String> getFirm() {
        ResultSet resultSet;
        ArrayList<String> firmList = new ArrayList<>(0);

        String select = "SELECT * FROM furniture.firm";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder info = new StringBuilder();
                info.append(resultSet.getString("id_firm")).append(" ").
                        append(resultSet.getString("firm")).append(" ").
                        append(resultSet.getString("city")).append(" ").
                        append(resultSet.getString("street")).append(" ").
                        append(resultSet.getString("house"));
                firmList.add(info.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return firmList;
    }

    public static ArrayList<String> getFirmName() {
        ResultSet resultSet;
        ArrayList<String> firm = new ArrayList<>(0);

        String select = "SELECT firm FROM furniture.firm";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                firm.add(resultSet.getString("firm"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return firm;
    }

    public static boolean addFirm(String firm) {
        String[] infoDetails = firm.split(" ", 5);
        String insert = "INSERT INTO furniture.firm (firm, city, street, house) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, infoDetails[1]);
            preparedStatement.setString(2, infoDetails[2]);
            preparedStatement.setString(3, infoDetails[3]);
            preparedStatement.setString(4, infoDetails[4]);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean setFirm(String firm) {
        String[] infoDetails = firm.split(" ", 5);
        String insert = "UPDATE firm SET city=?, street=?, house=? WHERE firm=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, infoDetails[2]);
            preparedStatement.setString(2, infoDetails[3]);
            preparedStatement.setString(3, infoDetails[4]);
            preparedStatement.setString(4, infoDetails[1]);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean delFirm(String firm) {

        String delete = "DELETE FROM furniture.firm WHERE firm=? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setString(1, firm);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public static ArrayList<String> getProfit() {
        ResultSet resultSet;
        ArrayList<String> infoList = new ArrayList<>(0);

        String select = "SELECT * FROM furniture.profit";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StringBuilder info = new StringBuilder();
                info.append(resultSet.getInt("id_profit")).append(" ").
                        append(resultSet.getString("firm")).append(" ").
                        append(resultSet.getString("furniture")).append(" ").
                        append(resultSet.getString("material")).append(" ").
                        append(resultSet.getDouble("costs")).append(" ").
                        append(resultSet.getDouble("price")).append(" ").
                        append(resultSet.getDouble("profit"));
                infoList.add(info.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return infoList;
    }

    public static ArrayList<String> getId() {
        ResultSet resultSet;
        ArrayList<String> id = new ArrayList<>(0);

        String select = "SELECT id_profit FROM furniture.profit";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id.add(resultSet.getString("id_profit"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static boolean addProfit(String profit) {
        String[] infoDetails = profit.split(" ", 7);
        String insert = "INSERT INTO furniture.profit (firm, furniture, material, costs, price, profit)" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, infoDetails[1]);
            preparedStatement.setString(2, infoDetails[2]);
            preparedStatement.setString(3, infoDetails[3]);
            preparedStatement.setString(4, infoDetails[4]);
            preparedStatement.setString(5, infoDetails[5]);
            preparedStatement.setString(6, infoDetails[6]);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean setProfit(String profit) {
        String[] infoDetails = profit.split(" ", 8);
        String insert = "UPDATE profit SET  firm=?, furniture=?, material=?, costs=?, price=?, profit=? WHERE id_profit=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, infoDetails[1]);
            preparedStatement.setString(2, infoDetails[2]);
            preparedStatement.setString(3, infoDetails[3]);
            preparedStatement.setString(4, infoDetails[4]);
            preparedStatement.setString(5, infoDetails[5]);
            preparedStatement.setString(6, infoDetails[6]);
            preparedStatement.setString(7, infoDetails[7]);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<String> getStat() {
        ResultSet resultSet;
        ArrayList<String> list = new ArrayList<>(0);
        String select = "SELECT furniture FROM furniture.profit ";
        double table = 0;
        double chair = 0;
        double bed = 0;
        double sofa = 0;
        double cupboard = 0;
        double armchair = 0;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("furniture").equalsIgnoreCase("стол")) table++;
                if (resultSet.getString("furniture").equalsIgnoreCase("стул")) chair++;
                if (resultSet.getString("furniture").equalsIgnoreCase("кровать")) bed++;
                if (resultSet.getString("furniture").equalsIgnoreCase("диван")) sofa++;
                if (resultSet.getString("furniture").equalsIgnoreCase("шкаф")) cupboard++;
                if (resultSet.getString("furniture").equalsIgnoreCase("кресло")) armchair++;


            }
            double tablePart = table / (table + chair + bed + sofa + cupboard + armchair);
            double chairPart = chair / (table + chair + bed + sofa + cupboard + armchair);
            double bedPart = bed / (table + chair + bed + sofa + cupboard + armchair);
            double sofaPart = sofa / (table + chair + bed + sofa + cupboard + armchair);
            double cupboardPart = cupboard / (table + chair + bed + sofa + cupboard + armchair);
            double armchairPart = armchair / (table + chair + bed + sofa + cupboard + armchair);
            list.add(Double.toString(tablePart));
            list.add(Double.toString(chairPart));
            list.add(Double.toString(bedPart));
            list.add(Double.toString(sofaPart));
            list.add(Double.toString(cupboardPart));
            list.add(Double.toString(armchairPart));

        } catch (SQLException e) {
        }
        return list;
    }
}