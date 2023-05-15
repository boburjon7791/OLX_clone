package com.company.client;

import com.company.server.db.database;
import com.company.server.enums.Category;
import com.company.server.models.Ad;
import com.company.server.models.Comment;
import com.company.server.models.User;
import com.company.server.services.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.UUID;

public class Application implements MyScanner{
    public static void main(String[] args) {
        menu();
        }
    private static void menu() {
        while (true) {
            try {
                database.getUsers().forEach(user -> {
                    System.out.println(user.getFirstName() + ", " + user.getPhoneNumber() + ", " + user.getPassword());
                });
                System.out.println("""
                        1. Login
                        2. Register
                                        
                        0. Stop
                        """);
                String s = scannerSTR.nextLine();
                if (s.equals("0")) {
                    break;
                }
                switch (s) {
                    case "1" -> {
                        loginPage();
                        return;
                    }
                    case "2" -> {
                        registerPage();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                menu();
            }
            scannerSTR.nextLine();
        }
    }

    private static void registerPage() {
        System.out.print("Input first name - ");
        String firstName = scannerSTR.nextLine();

        System.out.print("Input phone number - ");
        String phoneNumber = scannerSTR.nextLine();

        System.out.print("Input password - ");
        String password = scannerSTR.nextLine();

        User user = new User(firstName, phoneNumber, password);
        RegisterService service = new RegisterService();
        Boolean registered = service.registerService(user);
        if (registered == null) {
            return;
        }
        if (registered) {
            System.out.println("Muvaffaqiyatli ro'yxatdan o'tdingiz");
        }else {
            System.out.println("Error");
        }
    }

    private static void loginPage() throws InterruptedException {
        System.out.print("Input phone number = +");
        String phoneNumber = scannerSTR.nextLine();

        System.out.print("Input password = ");
        String password = scannerSTR.nextLine();

        AuthService service = new AuthService();
        User user = service.login(phoneNumber, password);

        if(user == null){
            System.out.println("User not found!");
            Thread.sleep(5000);
            System.out.println("\n\n\n\n\n\n\n\n");
            menu();
            return;
        }
        landingPage(user);
    }

    private static void landingPage(User user) {
        if (user == null){
            menu();
        }
        while (true) {
            System.out.println("""
                    1. Show all ads
                    2. Show my ads
                    3. Add ad
                    
                    0. Logout
                    """);
            String s = scannerSTR.nextLine();
            if (s.equals("0")) {
                menu();
                break;
            }
            switch (s) {
                case "1" -> {
                    showAds(user);
                }
                case "2" -> {
                    myAds(user);
                }
                case "3" ->{
                    adAdd(user);
                }
                default -> throw new InputMismatchException();
            }
            scannerSTR.nextLine();
        }
    }

    private static void adAdd(User user) {
        if (user == null) {
            return;
        }
        System.out.print("Input title of your ad - ");
        String adText = scannerSTR.nextLine();

        System.out.println("Select category of your ad");
        for (int i = 0; i < Category.values().length; i++) {
            System.out.println(i+". "+Category.values()[i]);
        }
        int select = scannerNUM.nextInt();
        UUID category_id = Category.values()[select].category_id();

        Ad ad = new Ad(adText, category_id, user.getId());

        AdService adService = new AdService();
        Boolean added = adService.addAd(user, ad);
        if (added == null) {
            return;
        }
        if (added) {
            System.out.println("Success");
        }else {
            System.out.println("Error");
        }
    }

    private static void myAds(User user) {
        AdService service = new AdService();
        List<Ad> ads = service.showMyAds(user);
        if (objCheck(ads)) return;
        System.out.println("Select");
        int s = scannerNUM.nextInt();
        CommentService service1 = new CommentService();
        List<Comment> comments = service1.myAdComments(user.getId(), ads.get(s).getId());
        if (extracted(user)) return;
        commentsShow(comments,user,ads.get(s));
        int sel = scannerNUM.nextInt();
        commentReplyLike(user, ads.get(s), comments.get(sel));
    }
    private static void commentAdd(User user,Ad ad){
        System.out.print("Input - ");
        String comment = scannerSTR.nextLine();
        CommentService commentService = new CommentService();
        Boolean setComment = commentService.setComment(user, ad, comment);
        if (setComment == null) {
            System.out.println("Error");
            return;
        }
        if (setComment){
            System.out.println("Qo'shildi");
            return;
        }
        System.out.println("Qo'shilmadi");
    }
    private static void commentsShow(List<Comment> comments,User user, Ad ad) {

        for (int i = 0; i < comments.size(); i++) {
            System.out.println(i+". "+comments.get(i).getComment_text());
            System.out.println(comments.get(i).getCout_of_comment_like() + " likes");
            if (!comments.get(i).getResponses().isEmpty()) {
                System.out.println("***\nResponses");
                for (Comment response : comments.get(i).getResponses()) {
                    System.out.println(response.getComment_text());
                }
                System.out.println("***");
            }
            System.out.println();
        }
        System.out.println("1. Add comment\n2. Continue\n0. Home page");
        int select = scannerNUM.nextInt();
        switch (select) {
            case 0 ->{
                landingPage(user);
            }
            case 1 ->{
                commentAdd(user,ad);
            }
            case 2 ->{
                System.out.println("Select comment");
            }
        }
        /*if (select == 1) {
            commentAdd(user,ad);
        } else if (select == 2) {
            System.out.println("Select comment");
        } else if (select == 0) {
            landingPage(user);
        }*/
    }

    private static void showAds(User user) {
        if(user == null){
            menu();
        }
        UserService service = new UserService();
        List<Ad> ads = service.showAds(user);
        if (objCheck(ads)) return;
        System.out.println("***\nSelect ad\n***");
        int s = scannerNUM.nextInt();
        Ad ad = ads.get(s);
        UUID user_id = ad.getUser_id();
        User showUser = service.showUser(user_id);
        System.out.println(showUser.getFirstName());
        System.out.println(ad.getText());

        if (extracted(user)) return;

        List<Comment> comments = service.showAdComments(ad);
        if (comments == null) {
            landingPage(user);
            return;
        }
        if (comments.isEmpty()) {
            System.out.println("Komentariyalar yo'q");
            landingPage(user);
            return;
        }
        commentsShow(comments,user,ad);
        int se = scannerNUM.nextInt();
        commentReplyLike(user, ad, comments.get(se));
    }

    private static boolean extracted(User user) {
        System.out.println("""
                1. View comments
                2. Home page
                """);
        if (scannerNUM.nextInt() != 1) {
            landingPage(user);
            return true;
        }
        return false;
    }

    private static boolean objCheck(List<Ad> ads) {
        if(ads == null){
            return true;
        }
        if(ads.isEmpty()){
            System.out.println("Ro'yxat bo'sh");
            return true;
        }
        for (int i = 0; i < ads.size(); i++) {
            System.out.println(i+". "+ads.get(i).getText());
        }
        return false;
    }

    private static void commentReplyLike(User user, Ad ad, Comment comments) {
        System.out.println(comments.getComment_text());
        System.out.println("""
                1. Reply
                2. Like
                
                0. Back to Home page
                """);
        int select = scannerNUM.nextInt();
        if (select!=1 && select!=2) {
            landingPage(user);
            return;
        }
        switch (select) {
            case 1 -> {
                System.out.print("Input comment - ");
                String comment = scannerSTR.nextLine();
                CommentService service1 = new CommentService();
                service1.setComment(user, ad, comment,comments.getId());
                    System.out.println("Qo'shildi");
            }
            case 2 ->{
                CommentService service1 = new CommentService();
                service1.likeComment(user,comments);
                    System.out.println("Liked");
            }
        }
    }
  }