package by.makhavenka.task.service;

import by.makhavenka.task.exception.ServiceException;
import by.makhavenka.task.servlet.RequestController;

public interface SupportService {
    String locale(RequestController content) throws ServiceException;
    String registration(RequestController content) throws ServiceException;
    String logout(RequestController content) throws ServiceException;
    String showProfile(RequestController content) throws ServiceException;

}
