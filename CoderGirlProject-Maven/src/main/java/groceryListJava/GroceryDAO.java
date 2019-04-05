package groceryListJava;

import groceryListJava.Grocery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GroceryDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public GroceryDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this. jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean insertGrocery(Grocery grocery) throws SQLException {
        String sql = "INSERT INTO grocery (item, amount, isle, hasCoupon, onSale) VALUES (?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, grocery.getItem());
        statement.setString(2, grocery.getAmount());
        statement.setString(3, grocery.getIsle());
        statement.setBoolean(4, grocery.getHasCoupon());
        statement.setBoolean(5, grocery.getOnSale());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }

    public List<Grocery> listAllGroceryItems() throws SQLException {
        List<Grocery> groceryList = new ArrayList<>();

        String sql = "SELECT * FROM grocery";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String item = resultSet.getString("item");
            String amount = resultSet.getString("amount");
            String isle = resultSet.getString("isle");
            boolean hasCoupon = resultSet.getBoolean("hasCoupon");
            boolean onSale = resultSet.getBoolean("onSale");
            System.out.println(resultSet);
            Grocery grocery = new Grocery(id, item, amount, isle, hasCoupon, onSale);
            groceryList.add(grocery);
        }

        resultSet.close();
        statement.close();

        disconnect();
        System.out.println("DAO: " + groceryList);

        return groceryList;
    }

    public boolean deleteGrocery(Grocery grocery) throws SQLException {
        String sql = "DELETE FROM grocery where id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, grocery.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean updateGrocery(Grocery grocery) throws SQLException {
        String sql = "UPDATE grocery SET item = ?, amount = ?, isle = ?, hasCoupon = ?, onSale = ?";
        sql += " WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, grocery.getItem());
        statement.setString(2, grocery.getAmount());
        statement.setString(3, grocery.getIsle());
        statement.setBoolean(4, grocery.getHasCoupon());
        statement.setBoolean(5, grocery.getOnSale());
        statement.setInt(6, grocery.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public Grocery getGrocery(int id) throws SQLException {
        Grocery grocery = null;
        String sql = "SELECT * FROM grocery WHERE id = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String item = resultSet.getString("item");
            String amount = resultSet.getString("amount");
            String isle = resultSet.getString("isle");
            boolean hasCoupon = resultSet.getBoolean("hasCoupon");
            boolean onSale = resultSet.getBoolean("onSale");
            grocery = new Grocery(id, item, amount, isle, hasCoupon, onSale);
        }

        resultSet.close();
        statement.close();

        return grocery;
    }
}
