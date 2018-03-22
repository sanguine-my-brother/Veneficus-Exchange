package controllers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import models.Session;
import models.User;
import org.joda.time.DateTime;
import play.api.Play;
import play.libs.openid.OpenIdClient;
import play.libs.openid.UserInfo;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.IOException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

public class PlayerController extends Controller {
    @Inject
    OpenIdClient openIdClient;

    public CompletionStage<Result> login() {
        CompletionStage<String> redirectUrlPromise =
                openIdClient.redirectURL("https://steamcommunity.com/openid", routes.PlayerController.loginCallback().absoluteURL(request()));

        return redirectUrlPromise.thenApply(Controller::redirect);
    }

    public Result loginCallback(){


        CompletionStage<UserInfo> userInfoPromise = openIdClient.verifiedId();

        try {
            UserInfo userInfo = userInfoPromise.toCompletableFuture().get();
            String id = userInfo.id().substring(36);
            User user = User.find.query().where().like("steamid", id).findOne();
            if (user == null){
                System.out.println(Play.current().configuration().underlying().getString("steam.key"));
                String steamkey = Play.current().configuration().underlying().getString("steam.key");
                String response = Unirest.get("http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/").queryString("key", steamkey).queryString("steamids", id + "").asJson().getBody().toString();
                ObjectMapper mapper = new ObjectMapper();
                String playerNode = mapper.readTree(response).path("response").get("players").get(0).toString();
                User newUser = mapper.readValue(playerNode, User.class);
                newUser.newUser();
                user = newUser;
            }
            Session session = new Session(user, request().getHeaders().get("User-Agent").get());
            session().clear();
            session().put("sessionkey", session.getSessioncode());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return redirect(routes.HomeController.index().url());
    }

    public static User getUser() {
        Session session = (Session) Session.find.query().where().like("sessioncode", session().get("sessionkey")).findOne();
        if(session != null) {
            if (session.isValid()) {
                return session.getUser();
            }
        }
        return null;
    }


}
