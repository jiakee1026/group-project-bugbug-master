package my.uum;
    import java.sql.*;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.SQLException;

public class DatabaseManager {


    private Connection connect() {
        String url = "jdbc:sqlite:C:\\Users\\JUN\\IdeaProjects\\group-project-bugbug\\database\\bot.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;

}public String[] insertUser(String User_ID,String ICNO,String User_Name,String Mobile_TelNo,String Email,String pin) {
        String respond="";
        int min = 0;
        int max = 999999;
        int random = (int) (Math.random() * (max - min + 1) + min);
        pin = String.format("%06d", random);

        String sql2 = "INSERT INTO User(User_ID,ICNO,User_Name,Mobile_TelNo,Email,pin) VALUES(?,?,?,?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql2)) {
            pstmt.setString(1, User_ID);
            pstmt.setString(2, ICNO);
            pstmt.setString(3, User_Name);
            pstmt.setString(4, Mobile_TelNo);
            pstmt.setString(5, Email);
            pstmt.setString(6, pin);
            pstmt.executeUpdate();
            System.out.println("in"+pin);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new String[0];
    }
    public String checkUserPin(String User_ID) {
        //String sql = "SELECT pin FROM User WHERE User_ID = ? AND pin = ?";
        String respond="";
        String sql = "SELECT pin FROM User WHERE User_ID = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, User_ID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("pin");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return respond;
    }
    public boolean checkPin(String pin) {
        String sql = "SELECT pin FROM User WHERE pin = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pin);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println(pin);
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void insertBooking( String Booking_ID,String Room_ID, String User_ID, String Purpose_of_booking, String Booking_Time, String Booking_Date,String pin) {
        if(checkPin(pin)) {
            String sql = "INSERT INTO Booking(Booking_ID, Room_ID, User_ID, Purpose_of_booking, Booking_Time, Booking_Date,pin) VALUES(?,?,?,?,?,?,?)";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, pin);
                pstmt.setString(1, Booking_ID);
                pstmt.setString(2, Room_ID);
                pstmt.setString(3, User_ID);
                pstmt.setString(4, Purpose_of_booking);
                pstmt.setString(5, Booking_Time);
                pstmt.setString(6, Booking_Date);
                pstmt.setString(7, pin);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }else {
            System.out.println("The pin you entered is not exist in the User table, please insert the User First.");
        }
    }

    public String displayRoomID() {
        String respond = " ";

        try (Connection conn = this.connect();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT Room_ID FROM Room WHERE Status = 'Available'")) {

            while (resultSet.next()) {
                respond += "Room ID: "+ resultSet.getString("Room_ID") + "\n";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return respond;
    }

    public void updateStatus(String Room_ID) {
        String sql = "UPDATE Room SET status = 'Reserved' WHERE Room_ID = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, Room_ID);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        public String checkingUser(String User_ID ) {
        String respond  ="";
        String selectSql = "SELECT * FROM User WHERE User_ID = ?";
        try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(selectSql)) {
            pstmt.setString(1, User_ID);

            // Execute the select statement and get the result set
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                respond += "Congratulations! Record " + User_ID  + " already been search."+ "\n\n";
                respond += "These the User_ID  about " + User_ID  + ": " + "\n\n";

                respond += " User Details" + "\n";
                respond += " ~~~~~~~~~~" + "\n";
                respond += " \u21aa\ufe0f Pin: "+ resultSet.getString("pin") + "\n";
                respond += " \u21aa\ufe0f User_ID: "+ resultSet.getString("User_ID") + "\n";
                respond += " \u21aa\ufe0f ICNO: " + resultSet.getString("ICNO") + "\n";
                respond += " \u21aa\ufe0f User_Name: " + resultSet.getString("User_Name") + "\n\n";
                respond += " \u21aa\ufe0f Mobile_TelNo: " + resultSet.getString("Mobile_TelNo") + "\n";
                respond += " \u21aa\ufe0f Email: " + resultSet.getString("Email") + "\n";

            }
            if(respond.equals(" ")){
                respond += "Sorry, there is no record of " + User_ID  + ". Please try another ID.";
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return respond ;
    }
    public String updateUser(String ICNO ,String User_ID) {
        String updateSql = "UPDATE User SET ICNO =? WHERE  User_ID = ?";


        // Check if the user ID exists in the database
        try (Connection conn = this.connect();

        PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
            System.out.println("yes");
            // The user ID is valid, so update the user's profile in the users table

            updateStmt.setString(1, ICNO);
            updateStmt.setString(2, User_ID);

            updateStmt.executeUpdate();
            System.out.println("not");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return updateSql;
    }

    public void UpdateProfileUser_Name(String User_Name,String User_ID) {

        String updateSql = "UPDATE User SET User_Name =? WHERE  User_ID = ?";

        try (Connection conn = this.connect();

             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            // The user ID is valid, so update the user's profile in the users table
            updateStmt.setString(1, User_Name);

            updateStmt.setString(2, User_ID);
            updateStmt.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println(e.getMessage());

        }
    }
    public void UpdateProfileMobile_TelNo(String Mobile_TelNo,String User_ID) {

        String updateSql = "UPDATE User SET Mobile_TelNo =? WHERE  User_ID = ?";

        try (Connection conn = this.connect();

             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            // The user ID is valid, so update the user's profile in the users table
            updateStmt.setString(1, Mobile_TelNo);

            updateStmt.setString(2, User_ID);
            updateStmt.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println(e.getMessage());

        }
    }
    public void UpdateProfileEmail(String User_ID,String Email) {

        String updateSql = "UPDATE User SET Email =? WHERE  User_ID = ?";

        try (Connection conn = this.connect();

             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            // The user ID is valid, so update the user's profile in the users table
            updateStmt.setString(1, Email);

            updateStmt.setString(2, User_ID);
            updateStmt.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println(e.getMessage());

        }
    }

    public void deleteUsers(String User_ID) {
        String sql = "DELETE FROM User WHERE User_ID = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, User_ID);
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    // pin,Booking_ID, Room_ID, User_ID, Purpose_of_booking, Booking_Time, Booking_Date


    public void updateBookingPurpose_of_booking(String pin,String Purpose_of_booking) {

        String updateSql = "UPDATE Booking SET Purpose_of_booking =? WHERE  pin= ?";

        try (Connection conn = this.connect();

             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            // The user ID is valid, so update the user's profile in the users table
            updateStmt.setString(1, Purpose_of_booking);

            updateStmt.setString(2, pin);
            updateStmt.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println(e.getMessage());

        }
    }
    public void updateBookingBooking_Date(String pin,String Booking_Date) {

        String updateSql = "UPDATE Booking  SET Booking_Date =? WHERE  pin= ?";

        try (Connection conn = this.connect();

             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            // The user ID is valid, so update the user's profile in the users table
            updateStmt.setString(1, Booking_Date);

            updateStmt.setString(2, pin);
            updateStmt.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println(e.getMessage());

        }
    }
    public void updateBookingBooking_Time(String pin,String Booking_Time) {

        String updateSql = "UPDATE Booking  SET Booking_Time =? WHERE  pin= ?";

        try (Connection conn = this.connect();

             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            // The user ID is valid, so update the user's profile in the users table
            updateStmt.setString(1, Booking_Time);

            updateStmt.setString(2, pin);
            updateStmt.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println(e.getMessage());

        }
    }
    public void updateStatusDelete(String User_ID)  {
        String sql = "UPDATE Room SET status = 'Available' WHERE room_id =(SELECT Room.Room_ID from Room INNER JOIN Booking on Booking.Room_ID = Room.Room_ID WHERE Booking.User_ID = ?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, User_ID );
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
                public void deleteBooking(String User_ID) {
            String sql = "DELETE FROM Booking WHERE User_ID= ?";

            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // set the corresponding param
                pstmt.setString(1, User_ID);
                // execute the delete statement
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }



}

