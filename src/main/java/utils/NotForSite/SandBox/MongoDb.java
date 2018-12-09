package utils.NotForSite.SandBox;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.entity.Message;
import model.entity.Room;
import org.bson.Document;
import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MongoDb {
    private static final String ROOMS_FILE_NAME = "rooms";
    private static final String ROOMS_TREE_NAME = "rooms";


    private static DB roomsDb = DBMaker.newFileDB(new File(ROOMS_FILE_NAME)).closeOnJvmShutdown().make();

    private static final BTreeMap<String, Room> rooms = roomsDb.getTreeMap(ROOMS_TREE_NAME);




    public static void main(String args[]){
        System.out.println(rooms);

        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("onplay");
        MongoCollection<Document> room_coll = database.getCollection("room");
        MongoCollection<Document> messages_coll = database.getCollection("messages");

//        Document document = new Document("name", "Café Con Leche")
//                .append("contact", new Document("phone", "228-555-0149")
//                        .append("email", "cafeconleche@example.com")
//                        .append("location",Arrays.asList(-73.92502, 40.8279556)))
//                .append("stars", 3)
//                .append("categories", Arrays.asList("Bakery", "Coffee", "Pastries"));
        //For one room
//        Room room = rooms.get("bhahcibjbiajadeajg");
//        ArrayList<Message> messages = room.messages;
//
//        Document room_doc = new Document("id", room.getRoomUrl())
//                .append("videoId", room.getVideoId())
//                .append("provider", room.getProvider());
//        room_coll.insertOne(room_doc);
//
//        List<Document> documents = new ArrayList<Document>();
//        for (int idx = 0; idx < messages.size(); idx++) {
//            Message message = messages.get(idx);
//            Document message_doc = new Document("room_id", room.getRoomUrl())
//                    .append("nickname", message.getNickName())
//                    .append("content", message.getText());
//            documents.add(message_doc);
//        }
//
//        messages_coll.insertMany(documents);


        //For many rooms
//        rooms.values().forEach(room_item -> System.out.println(room_item));
        rooms.values().forEach(room -> {
            System.out.println(room);
            //For rooms
//            Document room_doc = new Document("id", room.getRoomUrl())
//                    .append("videoId", room.getVideoId())
//                    .append("provider", room.getProvider());
//            room_coll.insertOne(room_doc);

            //For messages
            ArrayList<Message> messages = room.messages;
            if(messages.size() > 0){

                List<Document> documents = new ArrayList<Document>();
                for (int idx = 0; idx < messages.size(); idx++) {
                    Message message = messages.get(idx);
                    Document message_doc = new Document("room_id", room.getRoomUrl())
                            .append("nickname", message.getNickName())
                            .append("content", message.getText());
                    documents.add(message_doc);
                }

                messages_coll.insertMany(documents);
            }
        });
    }




}
