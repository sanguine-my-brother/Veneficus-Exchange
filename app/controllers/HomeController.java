package controllers;

import java.util.*;
import java.util.concurrent.CompletionStage;

import play.data.*;
import play.libs.openid.*;
import play.mvc.*;

import javax.inject.Inject;






/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {


    @Inject
    OpenIdClient openIdClient;
    /**
     https://github.com/mohiva/play-silhouette
     */
    public Result index() {
        return ok(views.html.index.render());
    }

    public CompletionStage<Result> loginPost() {

        CompletionStage<String> redirectUrlPromise =
                openIdClient.redirectURL("http://steamcommunity.com/openid", routes.HomeController.loginCallback().absoluteURL(request()));

        return redirectUrlPromise.thenApply(Controller::redirect);

    }

    public CompletionStage<Result> loginCallback(){
        CompletionStage<UserInfo> userInfoPromise = openIdClient.verifiedId();
        CompletionStage<Result> resultPromise = userInfoPromise.thenApply(userInfo ->
                ok(userInfo.id() + "\n" + userInfo.attributes())
        );

        return resultPromise;
    }


}
