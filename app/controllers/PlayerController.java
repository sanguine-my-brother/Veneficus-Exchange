package controllers;

import play.libs.openid.OpenIdClient;
import play.libs.openid.UserInfo;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class PlayerController extends Controller {
    @Inject
    OpenIdClient openIdClient;

    public CompletionStage<Result> loginPost() {


        CompletionStage<String> redirectUrlPromise =
                openIdClient.redirectURL("http://steamcommunity.com/openid", routes.PlayerController.loginCallback().absoluteURL(request()));

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
