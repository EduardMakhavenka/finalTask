package by.makhavenka.task.service;

import by.makhavenka.task.exception.ServiceException;
import by.makhavenka.task.servlet.RequestController;

public interface AdminService {
    String blockAccount(RequestController content) throws ServiceException;
    String deleteComment(RequestController content) throws ServiceException;
    String deleteHero(RequestController content) throws ServiceException;
    String editHero(RequestController content) throws ServiceException;
    String unBlockAccount(RequestController content) throws ServiceException;

    String addHero(RequestController content) throws ServiceException;
    String showBannedUsers(RequestController content) throws ServiceException;
    String showDeletedComments(RequestController content) throws ServiceException;
    String showDeletedHeroes(RequestController content) throws ServiceException;
    String showUsers(RequestController content) throws ServiceException;
    String restoreHero(RequestController content) throws ServiceException;
}
