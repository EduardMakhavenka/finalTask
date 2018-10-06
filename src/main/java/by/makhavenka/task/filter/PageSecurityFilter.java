package by.makhavenka.task.filter;

import by.makhavenka.task.service.ServiceConstants;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebFilter( urlPatterns = {"/jsp/*"})

/**
 * filter restricts access to pages by roles
 */
public class PageSecurityFilter implements Filter {

    private ArrayList<String> prohibitedPageForUser = new ArrayList<>();
    private ArrayList<String> prohibitedPageForVisitor = new ArrayList<>();
    private ArrayList<String> prohibitedPageForAll = new ArrayList<>();

    /**
     * adds pages to levels lists
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig){
        prohibitedPageForUser.add(FilterConstant.HEADER_JSP);
        prohibitedPageForUser.add(FilterConstant.HEADER_JSP);
        prohibitedPageForUser.add(FilterConstant.FOOTER_JSP);
        prohibitedPageForUser.add(FilterConstant.ADD_HERO_PAGE_JSP);
        prohibitedPageForUser.add(FilterConstant.ADMIN_PAGE_JSP);
        prohibitedPageForUser.add(FilterConstant.BANNED_USERS_LIST_JSP);
        prohibitedPageForUser.add(FilterConstant.DELETED_COMMENTS_JSP);
        prohibitedPageForUser.add(FilterConstant.DELETED_HEROES_JSP);
        prohibitedPageForUser.add(FilterConstant.EDIT_DESCRIPTION_JSP);
        prohibitedPageForUser.add(FilterConstant.USERS_LIST_JSP);

        prohibitedPageForVisitor.addAll(prohibitedPageForUser);
        prohibitedPageForVisitor.add(FilterConstant.EDIT_PROFILE_JSP);
        prohibitedPageForVisitor.add(FilterConstant.PROFILE_JSP);

        prohibitedPageForAll.add(FilterConstant.AUTHOR_PROFILE_JSP);
        prohibitedPageForAll.add(FilterConstant.DESCRIPTION_HERO_JSP);
     }

    /**
     * restricts access to the page by roles
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest =(HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse =(HttpServletResponse) servletResponse;

          if(httpRequest.getSession().getAttribute(ServiceConstants.ROLE_STATUS)==null){
              String url = httpRequest.getRequestURI();
              for(String urlPage:prohibitedPageForVisitor){
                  if(url.equals(urlPage)){
                      httpResponse.sendRedirect(ServiceConstants.HOME);
                  }
              }
              for (String urlPage:prohibitedPageForAll){
                  if(url.equals(urlPage) && httpRequest.getSession().getAttribute(FilterConstant.FILTER_ATTRIBUTE)==null){
                      httpResponse.sendRedirect(ServiceConstants.HOME);
                  }
              }
          } else if((int)httpRequest.getSession().getAttribute(ServiceConstants.ROLE_STATUS)==1
                  || (int)httpRequest.getSession().getAttribute(ServiceConstants.ROLE_STATUS)==2){
              String url = httpRequest.getRequestURI();
              for(String urlPage:prohibitedPageForUser){
                  if(url.equals(urlPage) &&(int)httpRequest.getSession().getAttribute(ServiceConstants.ROLE_STATUS)!=2 ){
                      httpResponse.sendRedirect(ServiceConstants.HOME);
                      }
              }
                  if(url.equals(FilterConstant.DESCRIPTION_HERO_JSP)
                          && (httpRequest.getSession().getAttribute(FilterConstant.FILTER_ATTRIBUTE)==null )){
                      httpResponse.sendRedirect(ServiceConstants.HOME);
                  }

                  if(url.equals(FilterConstant.AUTHOR_PROFILE_JSP)
                          && (httpRequest.getSession().getAttribute(FilterConstant.FILTER_ATTRIBUTE_PROFILE)==null )){
                      httpResponse.sendRedirect(ServiceConstants.HOME);
                  }
          }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
