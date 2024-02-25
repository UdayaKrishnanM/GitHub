package Main;

import java.sql.*;

public class NewCass {
    private Connection connection;

    public NewCass() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establishing connection with the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/sportsmanagement", "root", "PHW#84#jeor");
            System.out.println("Connected to the database.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Function to add a new player
    public void addPlayer(String name, int age, String country, String role) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO players (name, age, country, role) VALUES (?, ?, ?, ?)");
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, country);
            statement.setString(4, role);
            statement.executeUpdate();
            System.out.println("Player added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to retrieve player information
    public void getPlayer(int playerId) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM players WHERE id = ?");
            statement.setInt(1, playerId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Player ID: " + resultSet.getInt("id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Age: " + resultSet.getInt("age"));
                System.out.println("Country: " + resultSet.getString("country"));
                System.out.println("Role: " + resultSet.getString("role"));
            } else {
                System.out.println("Player not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to update player information
    public void updatePlayer(int playerId, String name, int age, String country, String role) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE players SET name=?, age=?, country=?, role=? WHERE id=?");
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, country);
            statement.setString(4, role);
            statement.setInt(5, playerId);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Player information updated successfully.");
            } else {
                System.out.println("Player not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to delete a player
    public void deletePlayer(int playerId) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM players WHERE id=?");
            statement.setInt(1, playerId);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Player deleted successfully.");
            } else {
                System.out.println("Player not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Function to list all players
    public void listAllPlayers() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM players");

            while (resultSet.next()) {
                System.out.println("Player ID: " + resultSet.getInt("id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Age: " + resultSet.getInt("age"));
                System.out.println("Country: " + resultSet.getString("country"));
                System.out.println("Role: " + resultSet.getString("role"));
                System.out.println("---------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NewCass system = new NewCass();

        // Adding a player
        system.addPlayer("Virat Kohli", 32, "India", "Batsman");

        // Listing all players
        System.out.println("List of all players:");
        system.listAllPlayers();

        // Updating player information
        system.updatePlayer(1, "Virat Kohli", 33, "India", "Captain");

        // Getting player information
        System.out.println("Player information:");
        system.getPlayer(1);

        // Deleting a player
        system.deletePlayer(1);

        // Listing all players after deletion
        System.out.println("List of all players after deletion:");
        system.listAllPlayers();
    }
}
