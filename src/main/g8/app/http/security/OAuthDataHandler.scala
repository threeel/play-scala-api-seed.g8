package http.security

import data.models.User
import scalaoauth2.provider._

import scala.concurrent.Future

class OAuthDataHandler extends DataHandler[User] {

  def validateClient(maybeClientCredential: Option[ClientCredential], request: AuthorizationRequest): Future[Boolean] = ???

  def findUser(maybeClientCredential: Option[ClientCredential], request: AuthorizationRequest): Future[Option[User]] = ???

  def createAccessToken(authInfo: AuthInfo[User]): Future[AccessToken] = ???

  def getStoredAccessToken(authInfo: AuthInfo[User]): Future[Option[AccessToken]] = ???

  def refreshAccessToken(authInfo: AuthInfo[User], refreshToken: String): Future[AccessToken] = ???

  def findAuthInfoByCode(code: String): Future[Option[AuthInfo[User]]] = ???

  def findAuthInfoByRefreshToken(refreshToken: String): Future[Option[AuthInfo[User]]] = ???

  def deleteAuthCode(code: String): Future[Unit] = ???

  def findAccessToken(token: String): Future[Option[AccessToken]] = ???

  def findAuthInfoByAccessToken(accessToken: AccessToken): Future[Option[AuthInfo[User]]] = ???

}
