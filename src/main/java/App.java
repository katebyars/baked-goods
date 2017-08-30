import com.google.gson.Gson;
import com.sun.tools.internal.xjc.model.Model;
import dao.Sql2oCartDao;
import dao.Sql2oItemsDao;
import dao.Sql2oSellerDao;
import exceptions.ApiException;
import models.Cart;
import models.Items;
import models.Seller;
import org.sql2o.Sql2o;
import org.sql2o.Connection;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        staticFileLocation("/public");
        Sql2oCartDao cartDao;
        Sql2oItemsDao itemsDao;
        Sql2oSellerDao sellerDao;
//        Connection conn;
//        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/studyabroad.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        cartDao = new Sql2oCartDao(sql2o);
        itemsDao = new Sql2oItemsDao(sql2o);
        sellerDao = new Sql2oSellerDao(sql2o);
//        conn = sql2o.open();

        ///-------------------------------------///
        ///..index..///
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
//            List<Cart> allCarts = CartDao.getAllCarts();
//            model.put("carts", allCarts);
            List<Items> allItems = itemsDao.getAllItems();
            model.put("items", allItems);
            return new ModelAndView(model, "index.hbs");
        },
        new HandlebarsTemplateEngine());

        get("/home", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "home.hbs");
        }, new HandlebarsTemplateEngine());



        ///-------------------------------------///
        ///..GET seller CREATE..///
        get("sellerportal/newseller", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Seller> sellers = sellerDao.getAll();
            model.put("sellers", sellers);
            return new ModelAndView(model, "newseller-form.hbs");
        }, new HandlebarsTemplateEngine());
        ///..POST seller CREATE..///
        post("sellerportal/newseller", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String address = request.queryParams("address");
            String email = request.queryParams("email");
            String dietaryPreference = request.queryParams("dietaryPreference");
            String goodsCategory = request.queryParams("goodsCategory");
            Seller newSeller = new Seller(name, address, dietaryPreference, email, goodsCategory);
            sellerDao.add(newSeller);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        ///-------------------------------------///
        ///..GET seller READ..///
        get("/sellerportal/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int sellerId = Integer.parseInt(request.params("id"));
            List<Seller> sellers = sellerDao.getAll();
            model.put("sellers", sellers);
            Seller foundSeller = sellerDao.findById(sellerId);
            model.put("seller", foundSeller);
            return new ModelAndView(model, "seller-details.hbs");
        }, new HandlebarsTemplateEngine());
        ///-------------------------------------///
        ///..GET seller UPDATE..///
        get("/sellerportal/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int sellerId2 = Integer.parseInt(request.params("id"));
            model.put("editSeller", true);
            List<Seller> allSellers = sellerDao.getAll();
            model.put("sellers", allSellers);
            return new ModelAndView(model, "seller-form.hbs");
        }, new HandlebarsTemplateEngine());
        ///..POST seller UPDATE..///
        post("/sellerportal/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int sellerId3 = Integer.parseInt(request.queryParams("newSellerId"));
            String newName = request.queryParams("newName");
            String newAddress = request.queryParams("newAddress");
            String newEmail = request.queryParams("newEmail");
            String newDietaryPreference = request.queryParams("newDietaryPreference");
            String newGoodsCategory = request.queryParams("newGoodsCategory");
            sellerDao.update(sellerDao.findById(sellerId3).getId(), newName, newAddress, newEmail, newDietaryPreference, newGoodsCategory);
            List<Seller> allSellers = sellerDao.getAll();
            model.put("sellers", allSellers);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());





//        ///..items CREATE..///
//        post("/seller/items/new", (request, response) -> {
//            Map<String, Object> model = new HashMap<>();
//
//            ItemsDao.add(items);
//            return new ModelAndView(model, "bread-form.hbs");
        ///-------------------------------------///
//        ///..items READ..///
//        ///..items (all)..///
//        get("/items", "application/json", (request, response) -> {
//            return gson.toJson(ItemsDao.getAllItems());
//        });
//        ///..items (by id)..///
//        get("items/:id", "application/json", (request, response) -> {
//            int itemsId = Integer.parseInt(request.params("id"));
//            Items items = ItemsDao.findById(itemsId);
//            if (items == null) {
//                throw new ApiException(String.format("No items with id '%d' found", itemsId), 404);
//            }
//            return gson.toJson(items);
//            });
//        ///-------------------------------------///
//        ///..items UPDATE..///
//        post("/items/:id/", "application/json", (request, response) -> {
//            int itemsId = Integer.parseInt(request.params("id"));
//            Items items = gson.fromJson(request.body(), Items.class);
//            ItemsDao.update(itemsId, items.getItemName(), items.getItemCategory(), items.getItemPrice());
//            response.status(201);
//            return gson.toJson(items);
//        });
//        ///-------------------------------------///
//        ///..items DELETE..///
//        ///..items (by id)..///
//        get("/items/:id/delete", "application/json", (request, response) -> {
//            int itemsId = Integer.parseInt(request.params(("id"));
//            ItemsDao.deleteById(itemsId);
//            return itemsId;
//        });
//        ///..items (all)..///
//        get("/items/:id/delete/all", "application/json", (request, response) -> {
//            ItemsDao.deleteAll();
//            return ItemsDao.getAllItems().size();
//        });
//        ///-------------------------------------///


//        //CREATE
//
//        //a new word
//        post("/languages/:languageId/words/new", "application/json", (req, res) -> {
//            int languageId = Integer.parseInt(req.params("languageId"));
//            Word word = gson.fromJson(req.body(), Word.class);
//            word.setLanguageId(languageId);
//            wordDao.add(word);
//            res.status(201);
//            return gson.toJson(word); });
//        //a new phrase
//        post("/languages/:languageid/phrases/new", "application/json", (req, res) -> {
//            int languageid = Integer.parseInt(req.params("languageid"));
//            Phrase phrase = gson.fromJson(req.body(), Phrase.class);
//            phrase.setLanguageid(languageid);
//            phraseDao.add(phrase);
//            res.status(201);
//            return gson.toJson(phrase); });
//        //a new language
//        post("/languages/new", "application/json", (req, res) -> {
//            Language language = gson.fromJson(req.body(), Language.class);
//            languageDao.add(language);
//            res.status(201);;
//            return gson.toJson(language); });
//        //READ
//        //all languages
//        get("/languages", "application/json", (req, res) -> {
//            return gson.toJson(languageDao.getAll()); });
//        //languages by id
//        get("/languages/:id", "application/json", (req, res) -> {
//            int languageId = Integer.parseInt(req.params("id"));
//            Language languageToFind = languageDao.findById(languageId);
//            if (languageToFind == null){
//                throw new ApiException(404, String.format("No langyage with the id: \"%s\" exists", req.params("id"))); }
//            return gson.toJson(languageToFind); });
//        //all words
//        get("/words", "application/json", (req, res) -> {
//            return gson.toJson(wordDao.getAll()); });
//        //words by id
//        get("/words/:id", "application/json", (req, res) -> {
//            int wordId = Integer.parseInt(req.params("id"));
//            Word wordToFind = wordDao.findById(wordId);
//            if (wordToFind == null){
//                throw new ApiException(404, String.format("No langyage with the id: \"%s\" exists", req.params("id"))); }
//            return gson.toJson(wordToFind); });
//        //all phrases
//        get("/phrases", "application/json", (req, res) -> {
//            return gson.toJson(phraseDao.getAll());});
//        get("/phrases/:id", "application/json", (req, res) -> {
//            int phraseId = Integer.parseInt(req.params("id"));
//            Word phraseToFind = wordDao.findById(phraseId);
//            if (phraseToFind == null){
//                throw new ApiException(404, String.format("No langyage with the id: \"%s\" exists", req.params("id"))); }
//            return gson.toJson(phraseToFind); });


        ///-------------------------------------///
//        ///..FILTERS..///
//        exception(ApiException.class, (exception, req, res) -> {
//            ApiException err = (ApiException) exception;
//            Map<String, Object> jsonMap = new HashMap<>();
//            jsonMap.put("status", err.getStatusCode());
//            jsonMap.put("errorMessage", err.getMessage());
//            res.type("application/json");
//            res.status(err.getStatusCode());
//            res.body(gson.toJson(jsonMap));
//        });
//
//        after((req, res) ->{
//            res.type("application/json");
//        });
//    }
///-------------------------------------///
    }
}