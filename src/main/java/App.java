import com.google.gson.Gson;
import dao.Sql2oCartDao;
import dao.Sql2oItemsDao;
import dao.Sql2oSellerDao;
import exceptions.ApiException;
import models.Items;
import org.sql2o.Sql2o;
import org.sql2o.Connection;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        Sql2oCartDao CartDao;
        Sql2oItemsDao ItemsDao;
        Sql2oSellerDao sellerDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:h2:~/studyabroad.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        CartDao = new Sql2oCartDao(sql2o);
        ItemsDao = new Sql2oItemsDao(sql2o);
        sellerDao = new Sql2oSellerDao(sql2o);
        conn = sql2o.open();

        ///-------------------------------------///
        ///..items CREATE..///
        post("/items/new", "application/json", (request, response) -> {
            Items items = gson.fromJson(request.body(), Items.class);
            ItemsDao.add(items);
            response.status(201);
            return gson.toJson(items);
                });
        ///-------------------------------------///
        ///..items READ..///
        get("/items", "application/json", (request, response) -> {
            return gson.toJson(ItemsDao.getAllItems());
        });
        get("items/:id", "application/json", (request, response) -> {
            int itemsId = Integer.parseInt(request.params("id"));
            Items items = ItemsDao.findById(itemsId);
            if (items == null) {
                throw new ApiException(String.format("No items with id '%d' found", itemsId), 404);
            }
            return gson.toJson(items);
            });
        ///-------------------------------------///
        ///..items UPDATE..///
        post("/items/:id/", "application/json", (request, response) -> {
            int itemsId = Integer.parseInt(request.params("id"));
            Items items = gson.fromJson(request.body(), Items.class);
            ItemsDao.update(itemsId, items.getItemName(), items.getItemCategory());
            response.status(201);
            return gson.toJson(items);
        });



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
        ///..FILTERS..///
        exception(ApiException.class, (exception, req, res) -> {
            ApiException err = (ApiException) exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });

        after((req, res) ->{
            res.type("application/json");
        });
    }
///-------------------------------------///

}