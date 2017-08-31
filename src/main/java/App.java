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

        //index
        Spark.get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Seller> allSellers = sellerDao.getAll();
            List<Items> allItems = itemsDao.getAllItems();
            model.put("allItems", allItems);
            model.put("allSellers", allSellers);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //delete an individual seller

        //delete all sellers
        Spark.get("/sellerportal/delete", (request, response) ->{
            Map<String, Object> model = new HashMap<>();
            sellerDao.deleteAll();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //delete an individual cart

        //delete all carts

        //delete a buyer
        Spark.get("/buyers/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            buyerDao.deleteAll();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete individual buyer
        Spark.get("/buyers/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfBuyer = Integer.parseInt(request.params("id"));
            buyerDao.findById(idOfBuyer);
            buyerDao.deleteById(idOfBuyer);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        ///..SELLERS..///

        //show a form for a new seller
        Spark.get("/sellerportal/newseller", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "seller-form.hbs");
        }, new HandlebarsTemplateEngine());

        //process a form for a new seller
        post("/sellerportal/newseller", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String address = request.queryParams("address");
            String email = request.queryParams("email");
            String dietaryPreference = request.queryParams("dietaryPreference");
            String goodsCategory = request.queryParams("goodsCategory");
            Seller newSeller = new Seller(name, address, dietaryPreference, email, goodsCategory);
            sellerDao.add(newSeller);
            response.redirect("/sellerportal");
            return null;
        }, new HandlebarsTemplateEngine());

        //get seller by id
        Spark.get("/sellerportal/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int sellerId = Integer.parseInt(request.params("id"));
            List<Seller> sellers = sellerDao.getAll();
            model.put("sellers", sellers);
            Seller foundSeller = sellerDao.findById(sellerId);
            model.put("seller", foundSeller);
            return new ModelAndView(model, "seller-details.hbs");
        }, new HandlebarsTemplateEngine());

        //update individual seller
        Spark.get("/sellerportal/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int sellerId = Integer.parseInt(request.params("id"));
            Seller editSeller = sellerDao.findById(sellerId);
            model.put("editSeller", editSeller);
            return new ModelAndView(model, "seller-form.hbs");
        }, new HandlebarsTemplateEngine());

        //process a seller update
        post("/sellerportal/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int sellerId = Integer.parseInt(request.params("id"));
            String name = request.queryParams("name");
            String address = request.queryParams("address");
            String email = request.queryParams("email");
            String dietaryPreference = request.queryParams("dietaryPreference");
            String goodsCategory = request.queryParams("goodsCategory");
            Seller editSeller = sellerDao.findById(sellerId);
            sellerDao.update(sellerId, name, address, dietaryPreference, email, goodsCategory);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //show the seller portal with all sellers
        Spark.get("/sellerportal", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Seller> allSellers = sellerDao.getAll();
            model.put("allSellers", allSellers);
            return new ModelAndView(model, "sellerportal.hbs");
        }, new HandlebarsTemplateEngine());

        //show a form to add a new item
        Spark.get("/sellerportal/items/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "items-form.hbs");
        }, new HandlebarsTemplateEngine());

        //process a form to add a new item
        post("/sellerportal/items/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String category = request.queryParams("category");
            Double price = Double.parseDouble(request.queryParams("itemPrice"));
            Items items = new Items(name, category, price);
            itemsDao.add(items);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

//cart routes

//buyer routes

        //show a new buyer form
        Spark.get("/buyers/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "newbuyer-form.hbs");
        }, new HandlebarsTemplateEngine());

        //show buyer dash
        Spark.get("/buyers", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Buyer> allBuyers = buyerDao.getAll();
            model.put("allBuyers", allBuyers);
            return new ModelAndView(model, "buyerDash.hbs");
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
            response.redirect("/buyers");
            return null;
        }, new HandlebarsTemplateEngine());

        //show a form to update a buyer
        Spark.get("/buyers/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfBuyer = Integer.parseInt(req.params("id"));
            Buyer editBuyer = buyerDao.findById(idOfBuyer);
            model.put("editBuyer", editBuyer);
            return new ModelAndView(model, "update-buyer.hbs");
        }, new HandlebarsTemplateEngine());

        //process a form to update a buyer
        post("/buyers/:id/update", (request, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newName = request.queryParams("name");
            String newAddress = request.queryParams("address");
            String newDietaryPreference = request.queryParams("dietaryPreference");
            String newEmail = request.queryParams("email");
            int idOfBuyer = Integer.parseInt(request.params("id"));
            buyerDao.update(idOfBuyer, newName, newAddress, newDietaryPreference, newEmail);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: delete individual buyer
        Spark.get("/buyers/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfBuyer = Integer.parseInt(req.params("id"));
            buyerDao.findById(idOfBuyer);
            buyerDao.deleteById(idOfBuyer);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

    }
}