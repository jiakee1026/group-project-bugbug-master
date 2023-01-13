package my.uum;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class STIW3054_bugbug_bot extends TelegramLongPollingBot {

    private String User_NO,User_ID, ICNO,User_Name, Mobile_TelNo, Email,pin;
   // private String User.User_NO,User.User_ID, User.ICNO,User.User_Name, User.Mobile_TelNo,User.Email;
    private String School_Admin_ID,Staff_ID,Staff_Name,Office_TelNo,Staff_Mobile_TelNo,Staff_Email,School_Name,Building_Location,Room_ID;
    private String Room_Description,Maximum_Capacity,Room_Type,Status;
    private String Booking_ID,Purpose_of_booking, Booking_Date, Booking_Time,TimeStamp;

    @Override
    public String getBotUsername() {
        // TODO
        return "STIW3054_bugbug_bot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "5837126857:AAH1cFR5CCBYfG2t_VKXbTL8ATw-6TQ21FM";
    }
    @Override
    public void onUpdateReceived(Update update) {

        Message message;
        if (update.hasMessage()) {
            message = update.getMessage();
        } else
            return;
        String msg = "";
        String[] com = message.getText().split(" - ");



        String chatId = message.getChatId().toString();
        String command = update.getMessage().getText();
        SendMessage sendMessage = new SendMessage();
        DatabaseManager app = new DatabaseManager();

        switch (com[0]) {
            case "/start":
                sendMessage.setText("Hello, " + update.getMessage().getFrom().getFirstName() + ". \ud83d\udc4b\n" +
                        "Welcome to Meeting Room Booking System!\n\n" +
                        "What is your position ? \n 1./schooladmin  2./user \n");
                sendMessage.setChatId(chatId);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

                break;
            // Add other cases here

            case "/schooladmin":
                sendMessage.setText("School Administrator Rights\n\n" +
                        "/registerAdmin -provide admin information\n" +
                        "/registerRoom  -Admin manage booking a meeting room\n" +
                        "/updateRoom -Update booking by enter staff ID\n" +
                        "/CreateNewRoom- Admin create new Meeting Room\n" +
                        "/displayRoom -Checking the list of room\n");
                sendMessage.setChatId(chatId);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "/user":
                sendMessage.setText("User Rights\n\n" +
                        "/received  -receiveBookingMessage (select room)\n" +
                        "/registerUser  -provide user details Booking a meeting room\n" +
                        "/checkingUser  -checkingUser by enter UserID\n" +
                        "/updateInfo  -Updating booking by enter UserID\n" +
                        "/checkingPin  -checkingPin by enter UserID\n" +
                        "/updateBooking -Updating booking by enter UserID\n" +
                        "/cancelBooking -Cancel the booking by enter UserID\n" + "" +
                        "/displayUserList -display the list of users (room&booking)\n" +
                        "/displayAvailableRoom   -（select day/week/month)\n" +
                        "\n");
                sendMessage.setChatId(chatId);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;



/*

*/
            case "/updateRoom":


                break;



            case "/received":

                break;

            case "/registerUser":
                sendMessage.setText("Hi " + update.getMessage().getFrom().getFirstName() + ", let's proceed with booking-making! " +
                        "\nPlease provide the following information to book the meeting room." +
                        "\n\n\t\ud83d\udc49 Please enter your User ID. (Format: ID - xxxxx) ");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "ID":
                User_ID = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());

                sendMessage.setText("Your staff ID is " + User_ID + ". " +
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb User_ID: " + User_ID +
                        "\n\nSo, we continue with the information." +
                        "\n\t\ud83d\udc49 Please enter your IC number. (Format: IC - xxxxxx-xx-xxxx) ");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "IC":
                ICNO = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("Your IC Number is " + ICNO + ". " +
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Staff ID: " + User_ID +
                        "\n  \ud83d\udccb IC Number: " + ICNO +
                        "\n\nSo, we continue with the information." +
                        "\n\t\ud83d\udc49 Please enter your name. (Format: Name - xxxxxx) ");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "Name":
                User_Name = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("Your name is " + User_Name + ". " +
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Staff ID: " + User_ID +
                        "\n  \ud83d\udccb IC Number: " + ICNO +
                        "\n  \ud83d\udccb Name: " + User_Name +
                        "\n\nSo, we continue with the information." +
                        "\n\t\ud83d\udc49  Please enter your phone number. (Format: Phone - xxxxxxxxxx) ");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;


            case "Phone":
                Mobile_TelNo = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("Your phone number is " + Mobile_TelNo + ". " +
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Staff ID: " + User_ID +
                        "\n  \ud83d\udccb IC Number: " + ICNO +
                        "\n  \ud83d\udccb Name: " + User_Name +
                        "\n  \ud83d\udccb Phone Number: " + Mobile_TelNo +
                        "\n\nSo, we continue with the information." +
                        "\n\t\ud83d\udc49 Please enter your email. (Format: Email - xxxxxxxxxx) ");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "Email":
                Email = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("Your email is " + Email + ". " +
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Staff ID: " + User_ID +
                        "\n  \ud83d\udccb IC Number: " + ICNO +
                        "\n  \ud83d\udccb Name: " + User_Name +
                        "\n  \ud83d\udccb Phone Number: " + Mobile_TelNo +
                        "\n  \ud83d\udccb Email: " + Email +
                        "\n\nSo, we continue with the information." +"Enter your user id is " + pin + "  "
                        +"\n\t\ud83d\udc49 /pin "
                        + "Your pin is " + pin + "  "+"\n\t\ud83d\udc49 /pin +"+
                        "\n\n" + "These are the room that is ready to booking: ");


                app.insertUser(User_ID, ICNO, User_Name, Mobile_TelNo, Email,pin);
                pin = app.checkUserPin(User_ID);

                //app.checkUserPin(User_ID,pin);
                sendMessage.setText("Your pin is " + pin + "  "+"\n\t\ud83d\udc49 /pin ");

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "/pin":
                app.checkPin(pin);
                sendMessage.setText(" Which pin you want to booking? (Format: Pin - xxxxx)");
                sendMessage.setChatId(chatId);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "Pin":
                pin= com[1];
                sendMessage.setText("Your booking room is " + pin+ ". "+
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Staff ID: " + User_ID +
                        "\n  \ud83d\udccb IC Number: " + ICNO +
                        "\n  \ud83d\udccb Name: " + User_Name +
                        "\n  \ud83d\udccb Phone Number: " + Mobile_TelNo +
                        "\n  \ud83d\udccb Email: " + Email +
                        "\n\n  Booking Details" +
                        "\n  =============" +
                        "\n  \ud83c\udfdb\ufe0f pin: " + pin +
                        "\n\n" + "These are the room that is ready to booking: "+
                        "\n\nSo, we continue with the information."+
                        "\n\n" + "These are the room that is ready to booking: "+
                        "\n" + app.displayRoomID()+
                        "\n\t\ud83d\udc49 Which room you want to booking? (Format: Room - xxxxx) ");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "Room":
                Room_ID= com[1];
                sendMessage.setText("Your booking room is " + Room_ID + ". "+
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Staff ID: " + User_ID +
                        "\n  \ud83d\udccb IC Number: " + ICNO +
                        "\n  \ud83d\udccb Name: " + User_Name +
                        "\n  \ud83d\udccb Phone Number: " + Mobile_TelNo +
                        "\n  \ud83d\udccb Email: " + Email +
                        "\n\n  Booking Details" +
                        "\n  =============" +
                        "\n  \ud83c\udfdb\ufe0f Room ID: " + Room_ID +
                        "\n\n" + "These are the room that is ready to booking: "+
                        "\n\nSo, we continue with the information."+
                        "\n\t\ud83d\udc49 Please enter the date you want to booking. (Format: Purpose - xxxxxxx) ");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "Purpose":
                Purpose_of_booking = com[1];
                sendMessage.setText("Your  booking purpose  is " + Purpose_of_booking + ". "+
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Staff ID: " + User_ID +
                        "\n  \ud83d\udccb IC Number: " + ICNO +
                        "\n  \ud83d\udccb Name: " + User_Name +
                        "\n  \ud83d\udccb Phone Number: " + Mobile_TelNo +
                        "\n  \ud83d\udccb Email: " + Email +
                        "\n\n  Booking Details" +
                        "\n  =============" +
                        "\n  \ud83c\udfdb\ufe0f Room ID: " + Room_ID +
                        "\n  \ud83c\udfdb\ufe0f Purpose: " + Purpose_of_booking +
                        "\n\nSo, we continue with the information."+
                        "\n\t\ud83d\udc49 Please enter the time you want to booking. (Format: Date - xx-xx-xxxx)  ");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "Date":
                Booking_Date = com[1];
                sendMessage.setText("Your booking date is " + Booking_Date + ". "+
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Staff ID: " + User_ID +
                        "\n  \ud83d\udccb IC Number: " + ICNO +
                        "\n  \ud83d\udccb Name: " + User_Name +
                        "\n  \ud83d\udccb Phone Number: " + Mobile_TelNo +
                        "\n  \ud83d\udccb Email: " + Email +
                        "\n\n  Booking Details" +
                        "\n  =============" +
                        "\n  \ud83c\udfdb\ufe0f Room ID: " + Room_ID +
                        "\n  \ud83c\udfdb\ufe0f Purpose: " + Purpose_of_booking +
                        "\n  \ud83c\udfdb\ufe0f Booking Date: " + Booking_Date +
                        "\n\nSo, we continue with the information."+
                        "\n\t\ud83d\udc49 Please enter the time you want to booking. (Format: Time - xx:xxAM/ xx:xxPM) ");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "Time":
               Booking_Time = com[1];
                sendMessage.setText("Your booking time is " + Booking_Time + ". "+
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Staff ID: " + User_ID +
                        "\n  \ud83d\udccb IC Number: " + ICNO +
                        "\n  \ud83d\udccb Name: " + User_Name +
                        "\n  \ud83d\udccb Phone Number: " + Mobile_TelNo +
                        "\n  \ud83d\udccb Email: " + Email +
                        "\n\n  Booking Details" +
                        "\n  =============" +
                        "\n  \ud83c\udfdb\ufe0f Room ID: " + Room_ID +
                        "\n  \ud83c\udfdb\ufe0f Purpose: " + Purpose_of_booking +
                        "\n  \ud83c\udfdb\ufe0f Booking Date: " + Booking_Date +
                        "\n  \ud83c\udfdb\ufe0f Booking Time: " + Booking_Time +
                        "\n\n\ud83d\udc4f Congratulations!! You have successfully booking the meeting room!");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                //app.checkPin(pin);
                //app.insertUser( User_ID, ICNO, User_Name, Mobile_TelNo, Email,pin);
                app.insertBooking(Booking_ID, Room_ID, User_ID, Purpose_of_booking, Booking_Time, Booking_Date,pin);
                app.updateStatus(Room_ID);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "/checkingUser":
                if (command.equals("/checkingUser")) {
                    sendMessage.setText("If you want to check the information of user. Please enter the User_ID to check about the information. " +
                            "\n \ud83d\udc49 Example format: /checkingUser - User_ID"+"");
                  //  sendMessage.setText("If you want to update ICNO"+"/updateICNO");
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    User_ID = com[1];
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());

                    String checkingUser = app.checkingUser(User_ID);
                    sendMessage.setText(checkingUser);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    sendMessage.setText("/updateInfo");
                    //  sendMessage.setText("If you want to update ICNO"+"/updateICNO");
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                //command
            case "/updateInfo":
                if (command.equals("/updateInfo")) {
                    sendMessage.setText("If you want to update the information of user. Please click /checkingUser to check User_ID " +
                            "\n \ud83d\udc49 There are four options for the you choose to update" +
                            "\n  /updateICNO " +
                            "\n  /updateUserName" +
                            "\n /updateMobile_TelNo "+
                            "\n  /updateEmail");
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();

                    }
                }

            case "/updateICNO":
                if (command.equals("/updateICNO")) {
                    sendMessage.setText("If you want to check the information of booking. Please enter the staff ID to check about the information. " +
                            "\n \ud83d\udc49 Example format: updateICNO - 123458");
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }else {
                    ICNO = com[1];
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());

                    //  String updateUser = app.updateUser(User_ID, ICNO);
                    //   sendMessage.setText(updateUser);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
             break;
                      //true
                case "updateICNO":
                    if (command.equals("updateICNO")) {

                    sendMessage.setText("Please provide your ICNO want to update\n" + "(Format: updateICNO - xxxxxx-xx-xxxx)  ");
                    sendMessage.setChatId(chatId);
                        System.out.println("");
                    sendMessage.setChatId(update.getMessage().getChatId().toString());

                        System.out.println("ww");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                     }
                       } else {
                        ICNO = com[1];

                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                        System.out.println("wwww");
                    sendMessage.setText("Your ICNO is " + ICNO + ". ");

                    app.updateUser(ICNO,User_ID);

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            case "/updateUserName":
                if (command.equals("/updateUserName")) {
                    sendMessage.setText("If you want to check the information of booking. Please enter the staff ID to check about the information. " +
                            "\n \ud83d\udc49 Example format: updateUserName - 123458");
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }else {
                    ICNO = com[1];
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());

                    //  String updateUser = app.updateUser(User_ID, ICNO);
                    //   sendMessage.setText(updateUser);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                break;
            //true
            case "updateUserName":
                if (command.equals("updateUserName")) {

                    sendMessage.setText("Please provide your ICNO want to update\n" + "(Format: updateUserName - xxxxxx-xx-xxxx)  ");
                    sendMessage.setChatId(chatId);
                    System.out.println("");
                    sendMessage.setChatId(update.getMessage().getChatId().toString());

                    System.out.println("ww");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    User_Name = com[1];

                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());

                    sendMessage.setText("Your User_Name is " + User_Name + ". ");
                    app.UpdateProfileUser_Name(User_Name,User_ID);


                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            case "/updateMobile_TelNo":
                if (command.equals("/updateMobile_TelNo")) {
                    sendMessage.setText("If you want to check the information of User. Please enter the User_ID to check about the information. " +
                            "\n \ud83d\udc49 Example format: updateMobile_TelNo- xxxxxxxxxxx");
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }else {
                    Email = com[1];
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());

                    //  String updateUser = app.updateUser(User_ID, ICNO);
                    //   sendMessage.setText(updateUser);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                break;
            //true
            case "updateMobile_TelNo":
                if (command.equals("updateMobile_TelNo")) {

                    sendMessage.setText("Please provide your updateEmail want to update\n" + "(Format: updateMobile_TelNo- xxxxxx-xx-xxxx)  ");
                    sendMessage.setChatId(chatId);
                    System.out.println("");
                    sendMessage.setChatId(update.getMessage().getChatId().toString());

                    System.out.println("Mobile_TelNo");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    Mobile_TelNo= com[1];

                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    System.out.println(" Mobile_TelNo");
                    sendMessage.setText("Your  Mobile_TelNo is " +  Mobile_TelNo + ". ");
                    app.UpdateProfileMobile_TelNo(Mobile_TelNo,User_ID);



                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            case "/updateEmail":
                if (command.equals("/updateEmail")) {
                    sendMessage.setText("If you want to check the information of booking. Please enter the staff ID to check about the information. " +
                            "\n \ud83d\udc49 Example format: updateEmail - xxxxxxxxxxxx");
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }else {
                    Email = com[1];
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());

                    //  String updateUser = app.updateUser(User_ID, ICNO);
                    //   sendMessage.setText(updateUser);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                break;
            //true
            case "updateEmail":
                if (command.equals("updateEmail")) {

                    sendMessage.setText("Please provide your updateEmail want to update\n" + "(Format: updateEmail - xxxxxxxxxxxx)  ");
                    sendMessage.setChatId(chatId);
                    System.out.println("");
                    sendMessage.setChatId(update.getMessage().getChatId().toString());


                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    Email= com[1];

                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    System.out.println(" Email");
                    sendMessage.setText("Your  Email is " +  Email + ". ");

                    app.UpdateProfileEmail(Email,User_ID);


                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            case "/checkingPin":
                if (command.equals("/checkingPin")) {
                    sendMessage.setText("If you want to check the information of booking. Please enter the Pin to check about the information. " +
                            "\n \ud83d\udc49 Example format: /checkingPin- Pin"+"If you want to check the pin Please click /checkingUser to check User_ID ");

                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    pin = com[1];
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());

                    String checkingUser = app.checkingUser(pin);
                    sendMessage.setText(checkingUser);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    sendMessage.setText("/updateBooking");

                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

            case "/updateBooking":
                if (command.equals("/updateBooking")) {
                    sendMessage.setText("If you want to update the information of user. Please click /checkingPin to check pin" +
                            "\n \ud83d\udc49 There are four options for the you choose to update" +
                            "\n  /updateBookingPurpose_of_booking" +
                            "\n  /updateBookingBooking_Date" +
                            "\n /updateBookingBooking_Time");
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();

                    }
                }
        break;

            case "/updateBookingPurpose_of_booking":
                if (command.equals("/updateBookingPurpose_of_booking")) {
                    sendMessage.setText("If you want to check the information of booking. Please enter the staff ID to check about the information. " +
                            "\n \ud83d\udc49 Example format: updateBookingPurpose_of_booking - 123458");
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }else {
                   Purpose_of_booking = com[1];
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());


                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                break;
            //true
            case "updateBookingPurpose_of_booking":
                if (command.equals("updateBookingPurpose_of_booking")) {

                    sendMessage.setText("Please provide your ICNO want to update\n" + "(Format: updateBookingPurpose_of_booking - xxxxxx-xx-xxxx)  ");
                    sendMessage.setChatId(chatId);
                    System.out.println("");
                    sendMessage.setChatId(update.getMessage().getChatId().toString());

                    System.out.println("ww");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    Purpose_of_booking = com[1];

                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    System.out.println("wwww");
                    sendMessage.setText("Your ICNO is " + Purpose_of_booking + ". ");

                    app.updateBookingPurpose_of_booking(pin,Purpose_of_booking);

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            case "/updateBookingBooking_Date":
                if (command.equals("/updateBookingBooking_Date")) {
                    sendMessage.setText("If you want to check the information of booking. Please enter the staff ID to check about the information. " +
                            "\n \ud83d\udc49 Example format: updateBookingBooking_Date - 123458");
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }else {
                    Booking_Date= com[1];
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());


                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                break;
            //true
            case "updateBookingBooking_Date":
                if (command.equals("updateBookingBooking_Date")) {

                    sendMessage.setText("Please provide your ICNO want to update\n" + "(Format: updateBookingBooking_Date - xxxxxx-xx-xxxx)  ");
                    sendMessage.setChatId(chatId);
                    System.out.println("");
                    sendMessage.setChatId(update.getMessage().getChatId().toString());

                    System.out.println("ww");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    Booking_Date= com[1];

                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    System.out.println("wwww");
                    sendMessage.setText("Your Booking_Dateis " + Booking_Date + ". ");

                    app.updateBookingBooking_Date(pin,Booking_Date);

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                } case "/updateBookingBooking_Time":
                if (command.equals("/updateBookingBooking_Time")) {
                    sendMessage.setText("If you want to check the information of booking. Please enter the staff ID to check about the information. " +
                            "\n \ud83d\udc49 Example format: updateBookingBooking_Time - 123458");
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }else {
                    Booking_Date= com[1];
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());


                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                break;
            //true
            case "updateBookingBooking_Time":
                if (command.equals("updateBookingBooking_Time")) {

                    sendMessage.setText("Please provide your pin want to update\n" + "(Format: updateBookingBooking_Time - xxxxxx-xx-xxxx)  ");
                    sendMessage.setChatId(chatId);
                    System.out.println("");
                    sendMessage.setChatId(update.getMessage().getChatId().toString());


                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
            Booking_Time= com[1];

                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());

                    sendMessage.setText("Your Booking_Time " + Booking_Time + ". ");

                    app.updateBookingBooking_Time(pin,Booking_Time);

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            case "/cancelBooking": //"Please enter your user ID to cancel your booking:"

                if(command.equals("/cancelBooking")){
                    sendMessage.setText("If you want to cancel booking, please enter the User_ID  to cancel it. "+
                            "\n \ud83d\udc49 Example format: /cancelBooking - User_ID");
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }else{
                    User_ID = com[1];
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    app.updateStatusDelete(User_ID);
                     app.deleteUsers(User_ID);
                    app.deleteBooking(User_ID);

                    sendMessage.setText("Booking of " + User_ID + " already been cancel."+ "\n\n");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                break;


            case "/displayAvailableRoom ":
                sendMessage.setText("AvailableRoom   "+

                        "\n" + app.displayRoomID()+
                        " ");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            default:
                sendMessage.setText("Please enter command available with the correct format. \u2764\ufe0f");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

        }



}}

