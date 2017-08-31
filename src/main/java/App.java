import dao.Sql2oBuyerDao;
import dao.Sql2oCartDao;
import dao.Sql2oItemsDao;
import dao.Sql2oSellerDao;
import exceptions.ApiException;
import models.Buyer;
import models.Cart;
import models.Items;
import models.Seller;
import org.sql2o.Sql2o;
import org.sql2o.Connection;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.*;
import static spark.route.HttpMethod.get;
import static spark.route.HttpMethod.post;

public class App {

    public static void main(String[] args) {
        staticFileLocation("/public");
        Sql2oCartDao cartDao;
        Sql2oItemsDao itemsDao;
        Sql2oSellerDao sellerDao;
        Sql2oBuyerDao buyerDao;

        String connectionString = "jdbc:h2:~/baked.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        cartDao = new Sql2oCartDao(sql2o);
        itemsDao = new Sql2oItemsDao(sql2o);
        sellerDao = new Sql2oSellerDao(sql2o);
        buyerDao = new Sql2oBuyerDao(sql2o);

//delete routes

        //delete a seller
        //delete a buyer


        ///-------------------------------------///
        ///..index..///
//        get("/", (request, response) -> {
//                    Map<String, Object> model = new HashMap<String, Object>();
////            List<Cart> allCarts = CartDao.getAllCarts();
////            model.put("carts", allCarts);
//                    List<Items> allItems = itemsDao.getAllItems();
//                    model.put("items", allItems);
//                    return new ModelAndView(model, "index.hbs");
//                },
//                new HandlebarsTemplateEngine());
//
//        get("/home", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            return new ModelAndView(model, "home.hbs");
//        }, new HandlebarsTemplateEngine());
//
//
//        ///-------------------------------------///
//        ///..GET seller CREATE..///
//        get("sellerportal/newseller", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            List<Seller> sellers = sellerDao.getAll();
//            model.put("sellers", sellers);
//            return new ModelAndView(model, "newseller-form.hbs");
//        }, new HandlebarsTemplateEngine());
//        ///..POST seller CREATE..///
//        post("sellerportal/newseller", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            String name = request.queryParams("name");
//            String address = request.queryParams("address");
//            String email = request.queryParams("email");
//            String dietaryPreference = request.queryParams("dietaryPreference");
//            String goodsCategory = request.queryParams("goodsCategory");
//            Seller newSeller = new Seller(name, address, dietaryPreference, email, goodsCategory);
//            sellerDao.add(newSeller);
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());
//        ///-------------------------------------///
//        ///..GET seller READ..///
//        get("/sellerportal/:id", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            int sellerId = Integer.parseInt(request.params("id"));
//            List<Seller> sellers = sellerDao.getAll();
//            model.put("sellers", sellers);
//            Seller foundSeller = sellerDao.findById(sellerId);
//            model.put("seller", foundSeller);
//            return new ModelAndView(model, "seller-details.hbs");
//        }, new HandlebarsTemplateEngine());
//        ///-------------------------------------///
//        ///..GET seller UPDATE..///
//        get("/sellerportal/:id/update", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            int sellerId2 = Integer.parseInt(request.params("id"));
//            model.put("editSeller", true);
//            List<Seller> allSellers = sellerDao.getAll();
//            model.put("sellers", allSellers);
//            return new ModelAndView(model, "seller-form.hbs");
//        }, new HandlebarsTemplateEngine());
//        ///..POST seller UPDATE..///
//        post("/sellerportal/update", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//            int sellerId3 = Integer.parseInt(request.queryParams("newSellerId"));
//            String newName = request.queryParams("newName");
//            String newAddress = request.queryParams("newAddress");
//            String newEmail = request.queryParams("newEmail");
//            String newDietaryPreference = request.queryParams("newDietaryPreference");
//            String newGoodsCategory = request.queryParams("newGoodsCategory");
//            sellerDao.update(sellerDao.findById(sellerId3).getId(), newName, newAddress, newEmail, newDietaryPreference, newGoodsCategory);
//            List<Seller> allSellers = sellerDao.getAll();
//            model.put("sellers", allSellers);
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());

//cart routes

//buyer routes

        //show a new buyer form
        Spark.get("/buyers/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "newbuyer-form.hbs");
        }, new HandlebarsTemplateEngine());

        //process a new buyer form
        post("/buyers/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Cart cart = new Cart(0);
            cartDao.add(cart);
            String name = request.queryParams("name");
            String address = request.queryParams("address");
            String dietaryPreference = request.queryParams("dietaryPreference");
            String email = request.queryParams("email");
            Buyer buyer = new Buyer(name, address, dietaryPreference, email, cart.getId());
            buyerDao.add(buyer);
            model.put("currentBuyer", buyer);
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //show buyers
        Spark.get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Buyer> allBuyers = buyerDao.getAll();
            model.put("allBuyers", allBuyers);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        Spark.get("/items", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Items> items = itemsDao.getAllItems();
            model.put("items", items);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

     

    }

}