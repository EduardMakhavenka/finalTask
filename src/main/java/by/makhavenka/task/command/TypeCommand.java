package by.makhavenka.task.command;

import by.makhavenka.task.command.impl.*;

/**
 * All application commands
 */
public enum TypeCommand {

    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    LOCALE(new LocaleCommand()),
    REGISTRATION(new RegistrationCommand()),

    ADD_COMMENT(new AddCommentCommand()),
    BLOCK_ACCOUNT(new BlockAccountCommand()),
    SEARCH_HERO(new SearchHeroCommand()),
    DELETE_COMMENT(new DeleteCommentCommand()),

    DOWN_HERO_RATING(new DownHeroRatingCommand()),
    DOWN_USER_RATING(new DownUserRatingCommand()),
    SHOW_DESCRIPTION_HERO(new ShowDescriptionHeroCommand()),
    SHOW_AUTHOR(new ShowAuthorProfileCommand()),

    SHOW_MARVEL_CONTENT(new ShowMarvelContentCommand()),
    SHOW_MKX_CONTENT(new ShowMKXContentCommand()),
    SHOW_RICK_AND_MORTY_CONTENT(new ShowRickAndMortyContentCommand()),
    UNBLOCK_ACCOUNT(new UnblockAccountCommand()),

    UP_HERO_RATING(new UpHeroRatingCommand()),
    UP_USER_RATING(new UpUserRatingCommand()),
    SHOW_PROFILE(new ShowProfileCommand()),
    DELETE_HERO(new DeleteHeroCommand()),

    EDIT_PROFILE(new EditProfileCommand()),
    EDIT_HERO(new EditHeroCommand()),
    SHOW_DELETED_HEROES(new ShowDeletedHeroesCommand()),
    SHOW_DELETED_COMMENTS(new ShowDeletedCommentsCommand()),

    SHOW_BANNED_USERS(new ShowBannedUsersCommand()),
    SHOW_USERS(new ShowUsersCommand()),
    ADD_HERO(new AddHeroCommand()),
    RESTORE_HERO(new RestoreHeroCommand());



    private Command command;

    TypeCommand(Command command){
        this.command=command;
    }

    public Command getCommand(){
        return command;
    }
}
