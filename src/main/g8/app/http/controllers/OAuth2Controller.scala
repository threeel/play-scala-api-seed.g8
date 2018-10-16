package http.controllers

import http.api.ApiController
import http.security.{OAuthDataHandler, SecureEndpoint}
import javax.inject.Inject
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import scalaoauth2.provider.OAuth2Provider

import scala.concurrent.ExecutionContext

/**
  *
  * User api controller for Play Scala
  */
class OAuth2Controller @Inject()(cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) with ApiController with I18nSupport with OAuth2Provider {

  override val tokenEndpoint = new SecureEndpoint()

  def accessToken: Action[AnyContent] = Action.async { implicit request =>
    issueAccessToken(new OAuthDataHandler())
  }

}