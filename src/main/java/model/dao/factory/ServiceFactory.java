package model.dao.factory;

import service.RoomService;
import service.UserService;
import service.impl.RoomServiceImpl;
import service.impl.UserServiceImpl;

/**
 * Created by denis on 21/10/15.
 */
public class ServiceFactory {

    public static UserService getUserService() {
        return new UserServiceImpl();
    }

    public static RoomService getRoomService() {
        return new RoomServiceImpl();
    }

}
