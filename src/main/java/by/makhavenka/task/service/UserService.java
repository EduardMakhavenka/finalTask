package by.makhavenka.task.service;

import by.makhavenka.task.servlet.RequestController;
import by.makhavenka.task.exception.ServiceException;

public interface UserService {

    String login(RequestController content) throws ServiceException;
    String upUserRating(RequestController content) throws ServiceException;
    String downUserRating(RequestController content) throws ServiceException;
    String editProfile(RequestController content) throws ServiceException;
}
