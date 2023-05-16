package com.company.server.db;

import com.company.server.enums.Category;
import com.company.server.models.Ad;
import com.company.server.models.Comment;
import com.company.server.models.User;

import java.util.ArrayList;
import java.util.List;

public abstract class database {
    private database(){}
    private static final List<User> users = new ArrayList<>();
    private static final List<Ad> ads = new ArrayList<>();
    private static final List<Comment> comments = new ArrayList<>();
    static {
        User boburjon = new User("Boburjon", "998999057761", "1234");
        users.add(boburjon);
        User asror = new User("Asror", "78945612356", "1234");
        users.add(asror);
        User guli = new User("Guli", "795123654565", "1234");
        users.add(guli);

        Ad elonBoburjon = new Ad("Avtomat kir moshina sotiladi. Narxi 150 $",
                Category.TEXNIKA.category_id(), boburjon.getId());
        ads.add(elonBoburjon);
        Ad elonAsror = new Ad("Yozgi futbolkalar yangi kolleksiya",
                Category.ODEJDA.category_id(), asror.getId());
        ads.add(elonAsror);
        Ad elonGuli = new Ad("Allergiya bermaydigon koreyski kosmetikalar", Category.KOSMETIKA.category_id(), guli.getId());
        ads.add(elonGuli);

        Comment commentBoburjon1 = new Comment("Kir moshina yangimi?", elonBoburjon.getId());
        comments.add(commentBoburjon1);
        Comment commentBoburjon2 = new Comment("Garantiya berolismi?", elonBoburjon.getId());
        comments.add(commentBoburjon2);

        Comment commentAsror1 = new Comment("XL rasmerlari ham bormi?", elonAsror.getId());
        comments.add(commentAsror1);
        Comment commentAsror2 = new Comment("bir sezonga yareydimi?", elonAsror.getId());
        comments.add(commentAsror2);

        Comment commentGuli1 = new Comment("narxi qancha?", elonGuli.getId());
        comments.add(commentGuli1);
        Comment commentGuli2 = new Comment("qimmatku!", elonGuli.getId());
        comments.add(commentGuli2);
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(User user) {
        users.add(user);
    }

    public static List<Ad> getAds() {
        return ads;
    }

    public static void setAds(Ad elon) {
        ads.add(elon);
    }

    public static List<Comment> getComments() {
        return comments;
    }

    public static void setComments(Comment comment) {
        comments.add(comment);
    }
}
