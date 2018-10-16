package http.security

import scalaoauth2.provider._

class SecureEndpoint extends TokenEndpoint {
  override val handlers = Map(
    OAuthGrantType.AUTHORIZATION_CODE -> new AuthorizationCode(),
    OAuthGrantType.REFRESH_TOKEN -> new RefreshToken(),
    OAuthGrantType.CLIENT_CREDENTIALS -> new ClientCredentials(),
    OAuthGrantType.PASSWORD -> new Password(),
    OAuthGrantType.IMPLICIT -> new Implicit()
  )
}