package service.factory;

import service.IdsService;
import service.RoomService;
import service.UserService;
import service.impl.IdsServiceImpl;
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

    public static IdsService getIdsService() { return new IdsServiceImpl(); }

}
