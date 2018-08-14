package controllers

import http.{ApiController, ApiRequest}
import javax.inject.Inject
import play.api.i18n.I18nSupport
import play.api.libs.json.JsValue
import play.api.mvc._
import models.$model;format="UpperCamel"$

// NOTE: Add the following to conf/routes to enable compilation of this class:
/*
GET       /$model;format="lower,hyphen"$          controllers.$model;format="UpperCamel"$Controller.index
GET       /$model;format="lower,hyphen"$/:$model;format="lowerCamel"$Id          controllers.$model;format="UpperCamel"$Controller.show($model;format="lowerCamel"$Id:Int)
POST      /$model;format="lower,hyphen"$          controllers.$model;format="UpperCamel"$Controller.store
PUT       /$model;format="lower,hyphen"$/:$model;format="lowerCamel"$Id          controllers.$model;format="UpperCamel"$Controller.update($model;format="lowerCamel"$Id:Int)
DELETE    /$model;format="lower,hyphen"$/:$model;format="lowerCamel"$Id          controllers.$model;format="UpperCamel"$Controller.delete($model;format="lowerCamel"$Id:Int)
*/

/**
  *
  * $model;format="UpperCamel"$ api controller for Play Scala
*/
class $model;format="UpperCamel"$Controller @Inject()(cc: ControllerComponents) extends AbstractController(cc) with ApiController with I18nSupport {

  /**
    * Get a Collection of $model;format="UpperCamel"$
    * @return
    */
  def index: Action[Unit] = ApiAction { implicit request: ApiRequest[Unit] =>
    ok("Paging OK")
  }

  /**
    * Save $model;format="UpperCamel"$
    * @return
    */
  def store: Action[JsValue] = ApiActionWithBody { implicit request =>
    readFromRequest[$model;format="UpperCamel"$] { form =>
      // handle form
      created()
    }
  }
  /**
    * Get $model;format="UpperCamel"$ By id
    * @return
    */
  def show($model;format="lowerCamel"$Id: Int): Action[Unit] = ApiAction { implicit request =>

    ok("OK")
  }

  /**
    * Update $model;format="UpperCamel"$ By Id
    * @return
    */
  def update($model;format="lowerCamel"$Id: Int): Action[JsValue] = ApiActionWithBody { implicit request =>
    readFromRequest[$model;format="UpperCamel"$] { form =>
      // handle form
      maybeItem(Some("OK"))
    }
  }


  /**
    * Delete $model;format="UpperCamel"$ By Id
    * @return
    */
  def delete($model;format="lowerCamel"$Id: Int): Action[JsValue] = ApiActionWithBody { implicit request =>

    maybeItem(Some("OK"))
  }

}