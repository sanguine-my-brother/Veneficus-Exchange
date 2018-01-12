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



    /**
     https://github.com/mohiva/play-silhouette
     */
    public Result index() {
        return ok(views.html.index.render());
    }




}
