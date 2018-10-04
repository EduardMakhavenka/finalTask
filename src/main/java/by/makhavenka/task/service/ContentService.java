package by.makhavenka.task.service;

import by.makhavenka.task.exception.ServiceException;
import by.makhavenka.task.servlet.RequestController;

public interface ContentService {
    String addComment(RequestController content) throws ServiceException;
    String downHeroRating(RequestController content) throws ServiceException;
    String showDescriptionHero(RequestController content) throws ServiceException;
    String showMKXContent(RequestController content) throws ServiceException;
    String showMarvelContent(RequestController content) throws ServiceException;
    String showRickAndMortyContent(RequestController content) throws ServiceException;
    String upHeroRating(RequestController content) throws ServiceException;
    String searchHero(RequestController content) throws ServiceException;
    String showAuthorProfile(RequestController content) throws ServiceException;
}
